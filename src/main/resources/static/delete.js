async function showAllRole() {
    let dbRoles = [];
    let roles = await fetch("http://localhost:8080/api/v1/roles");
    await roles.json().then(roles => {
        roles.forEach(role =>
            dbRoles.push(role))
    });
    return dbRoles;
}


async function deleteUserData(id) {
    let href = `http://localhost:8080/api/v1/users/${id}`
    let dbRoles =await showAllRole()

    $.get(href, function (user) {
        $('.myDeleteForm #dId').val(user.id)
        $('.myDeleteForm #dFirstName').val(user.firstName)
        $('.myDeleteForm #dLastName').val(user.lastName)
        $('.myDeleteForm #dAge').val(user.age)
        $('.myDeleteForm #dEmail').val(user.email)

        const inputRoles = document.getElementById('dRoles')
        inputRoles.innerHTML = `
        <option value="${dbRoles[0].id}" name="ROLE_USER" >${dbRoles[0].shortName}</option>
        <option value="${dbRoles[1].id}" name="ROLE_ADMIN" >${dbRoles[1].shortName}</option>
        `
    })


    document.getElementById('delete-user-button').addEventListener('click', async () => {
        await fetch(`http://localhost:8080/api/v1/users/${id}`, {
            method: "DELETE",
            headers: {
                'Content-Type': 'application/json',
            }
        });

        $('#deleteFormCloseButton').click()
        await list()
    })

}
