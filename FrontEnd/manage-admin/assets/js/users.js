$(document).ready(function () {
    const idElTable = document.getElementById("idUserTable");
    const token = localStorage.getItem('token')
    console.log(token)
    $.ajax({
        url: `http://localhost:8080/user`,
        method: "get",
        headers: {
            'Authorization': "Bearer " + token
        }
    }).done(function (data) {
        const users = data.data;
        let index = 0
        users.forEach((user, index) => {
            let htmlDisplay = "";
            // create tr element
            let tr = document.createElement("tr");
            tr.setAttribute("id", `idUser-${user.id}`);
            idElTable.appendChild(tr);
            const idElUser = document.getElementById(`idUser-${user.id}`);
            let role = user.role.name.substring(5).toLowerCase()
            htmlDisplay += `
                <td>${index + 1}</td>
                <td></td>
                <td>${user.username}</td>
                <td>PROTECTED</td>
                <td>${user.email}</td>
                <td>${role}</td>
                <td>
                    <div class="dropdown">
                        <button type="button" class="btn p-0 dropdown-toggle hide-arrow" data-bs-toggle="dropdown">
                            <i class="bx bx-dots-vertical-rounded"></i>
                        </button>
                        <div class="dropdown-menu">
                            <a class="dropdown-item" href="../../html/update-user.html?id=${user.id}">
                                <i class="bx bx-edit-alt me-1"></i> Edit
                            </a>
                            <a class="dropdown-item" href="../../html/user-detail.html?id=${user.id}">
                                <i class="bx bx-detail me-1"></i> Detail
                            </a>
                        </div>
                    </div>
                </td>
            `;

            idElUser.innerHTML = htmlDisplay;
        });
    }).fail(function (jqXHR, textStatus, errorThrown) {
        alert("Error " + errorThrown);
    });
})