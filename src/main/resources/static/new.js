
async function showRole() {

    const inputRoles = document.getElementById('nRoles');
    let dbRoles = await showAllRole();
    inputRoles.innerHTML = `
            <option value="${dbRoles[0].id}">${dbRoles[0].shortName}</option>
            <option value="${dbRoles[1].id}">${dbRoles[1].shortName}</option>
            `
}


document.getElementById('profile-tab').addEventListener('click', showRole)

document.getElementById('addNewUser').addEventListener('click', createUser)

async function createUser() {
    const inputFirstName = document.getElementById('nFirstName');
    const inputLastName = document.getElementById('nLastName');
    const inputAge = document.getElementById('nAge');
    const inputEmail = document.getElementById('nEmail');
    const inputPassword = document.getElementById('nPassword');


    const firstName = inputFirstName.value;
    const lastName = inputLastName.value;
    const age = inputAge.value;
    const email = inputEmail.value;
    const password = inputPassword.value;
    let listRoles = await roleArray(document.getElementById('nRoles'));


    if (firstName && lastName && age && email && password && (listRoles.length !== 0)) {

        let res = await fetch(`http://localhost:8080/api/v1/users`, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify({firstName, lastName, age, email, password, roles: listRoles})
        });
        const result = await res.json();
        await list()
        $('#nav-home-tab').click();
    }

    inputFirstName.value = ''
    inputLastName.value = ''
    inputAge.value = ''
    inputEmail.value = ''
    inputPassword.value = ''

}
