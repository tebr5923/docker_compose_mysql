async function roleArray(options) {
    let dbRoles = await showAllRole();
    let array = []
    for (let i = 0; i < options.length; i++) {
        if (options[i].selected) {
            let role = {
                id: options[i].value,
                name: dbRoles[i].name
            }

            array.push(role)
        }
    }
    return array
}

async function editUserData(id) {

    let href = `http://localhost:8080/api/v1/users/${id}`;
    let dbRoles = await showAllRole();
    $.get(href, function (user) {
        $('.myForm #id').val(user.id);
        $('.myForm #firstName').val(user.firstName);
        $('.myForm #lastName').val(user.lastName);
        $('.myForm #age').val(user.age);
        $('.myForm #email').val(user.email);
        $('.myForm #password').val("");
        const inputRoles = document.getElementById('roles');

        inputRoles.innerHTML = `
            <option value="${dbRoles[0].id}">${dbRoles[0].shortName}</option>
            <option value="${dbRoles[1].id}">${dbRoles[1].shortName}</option>
            `
    })

    document.getElementById('edit-user-button').addEventListener('click', async () => {
        const inputId = document.getElementById('id');
        const inputFirstName = document.getElementById('firstName');
        const inputLastName = document.getElementById('lastName');
        const inputAge = document.getElementById('age');
        const inputEmail = document.getElementById('email');
        const inputPassword = document.getElementById('password');


        const id = inputId.value;
        const firstName = inputFirstName.value;
        const lastName = inputLastName.value;
        const age = inputAge.value;
        const email = inputEmail.value;
        const password = inputPassword.value;
        const listRoleEditUser = await roleArray(document.getElementById('roles'))


        if (id && firstName && lastName && age && email) {
            const res = await fetch(`http://localhost:8080/api/v1/users/${id}`, {
                method: 'PUT',
                headers: {
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify({id, firstName, lastName, age, email, password, roles: listRoleEditUser})
            });
            const result = await res.json();

            $('#editFormCloseButton').click()
            await list()

        }
    })
}

