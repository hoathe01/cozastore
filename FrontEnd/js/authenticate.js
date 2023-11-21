$(document).ready(function () {
    $('#logout').hide()
    const nav = document.getElementById('menu-nav');
    const token = localStorage.getItem('token')
    const logout = document.getElementById('logout')
    if (token.length > 0){
        $('#login-user').hide()
        $('#logout').show()
    }
    $('#logout').click(function () {
        $.ajax({
            method: "POST",
            url: "http://localhost:8080/login/signout",
            headers: {
                'Authorization': "Bearer " + token
            }
        })
            .done(function (msg) {
                localStorage.setItem('token', '')
                window.location.reload()
            });
    })
    $.ajax({
        method: "POST",
        url: "http://localhost:8080/login/authen",
        headers: {
            'Authorization': "Bearer " + token
        }
    })
        .done(function (msg) {
            console.log(msg)
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