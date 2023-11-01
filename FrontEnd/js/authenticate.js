$(document).ready(function () {
    const nav = document.getElementById('menu-nav');
    const token = localStorage.getItem('token')

    $.ajax({
        method: "POST",
        url: "http://localhost:8080/login/authen",
        headers: {
            'Authorization': "Bearer " + token
        }
    })
        .done(function (msg) {
            if (msg.data === 'isAuthenticated') {
                let contentAdd = `
                    <li>
                        <a href="./manage-admin/html/users.html">Manage</a>
                     </li>`
                console.log(token)
                nav.innerHTML += contentAdd
            }
        });

})