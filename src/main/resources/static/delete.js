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
    let dbRoles = showAllRole();
    let href = `http://localhost:8080/api/v1/users/${id}`

    // let url = `http://localhost:8080/api/v1/users/${id}`
    // let response = await fetch(url);
    // let {firstName, lastName, age, email, roles} = await response.json()
    // const dId = document.getElementById("dId")
    // dId.textContent = id

    $.get(href, function (user) {
        $('.myDeleteForm #dId').val(user.id);
        $('.myDeleteForm #dFirstName').val(user.firstName);
        $('.myDeleteForm #dLastName').val(user.lastName);
        $('.myDeleteForm #dAge').val(user.age);
        $('.myDeleteForm #dEmail').val(user.email);


        //  const inputRoles = document.getElementById('dRoles');
        //  inputRoles.innerHTML = `
        const inputRoles = $('#dRoles')
        inputRoles.append(`
         <option value="${dbRoles[0].id}" name="ROLE_USER" >${dbRoles[0].name}</option>
        <option value="${dbRoles[1].id}" name="ROLE_ADMIN" >${dbRoles[1].name}</option>
        `)
    })

    document.getElementById('delete-user-button').addEventListener('click', async () => {
        const res = await fetch(`http://localhost:8080/api/v1/users/${id}`, {
            method: "DELETE",
            headers: {
                'Content-Type': 'application/json',
            }
        });
        // document.getElementById(`user${id}`).remove();
        // $('#deleteModal').modal('toggle');
        $('#deleteFormCloseButton').click();
        await list();
    })

}
