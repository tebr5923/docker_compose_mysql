$(async function () {
    await userInfo();
});

async function userInfo() {
    let url = "http://localhost:8080/api/v1/user"
    let response = await fetch(url);
    let {id, firstName, lastName, age, email, roles} = await response.json()

    const aEmail = document.getElementById("emailId")
    aEmail.textContent = email

    const aRoles = document.getElementById("rolesId")
    let textRoles = ""
    roles.forEach(role => {
        let roleLongName = role.name
        let roleShortName = roleLongName.substring(5) + " "
        textRoles += roleShortName
    })
    aRoles.textContent = `${textRoles}`

    const userTableBody = document.getElementById("userTableBodyId")
    const tableBody = `
    <tr>
                <td>${id}</td>
                <td>${firstName}</td>
                <td>${lastName}</td>
                <td>${age}</td>
                <td>${email}</td>
                <td>${textRoles}</td>            
    </tr>
    `
    userTableBody.innerHTML = tableBody
    // $(`#userTableBodyId`).append(tableBody);

}