$(async function () {
    await list();
});

const userTableBody = $('#tBodyAllUsersId')

async function list() {
    userTableBody.empty()

    let url = "http://localhost:8080/api/v1/users"
    let response = await fetch(url)
    let users = await response.json()
    users.forEach(user => {
        usersToTableBody(user)
    })
}


function usersToTableBody({id, firstName, lastName, age, email, roles}) {
    let textRoles = ""
    roles.forEach(role => {
        let roleLongName = role.name
        let roleShortName = roleLongName.substring(5) + " "
        textRoles += roleShortName
    })

    const tableBody = `
    <tr>
                <td>${id}</td>
                <td>${firstName}</td>
                <td>${lastName}</td>
                <td>${age}</td>
                <td>${email}</td>
                <td>${textRoles}</td>             
                <td>
                     <button type="button" class="btn btn-info editBtn" data-toggle="modal"
                        data-target="#editModal"
                        onclick="editUserData(${id})">
                        Edit
                    </button>
                </td>
                <td>
                    <button type="button" class="btn btn-danger"
                        data-toggle="modal"
                        data-target="#deleteModal"
                        onclick="deleteUserData(${id})">
                        Delete
                    </button>
                </td>
    </tr>`
    userTableBody.append(tableBody)
}
