$(document).ready(function () {
    // get id user
    const queryString = window.location.search;
    const urlParams = new URLSearchParams(queryString);
    const idBlog = Number(urlParams.get("id"));

    const idElForm = document.getElementById("userInfo");

    $.ajax({
        url: `http://localhost:8080/blog/detail/${idBlog}`,
        method: "get",
    }).done(function (data) {
        const blog = data?.data;
        let htmlDisplay = "";

        if (blog != null) {


            htmlDisplay = `
                <div class="row g-0">
                    <div class="col-md-4">
                        <img class="card-img card-img-left" src="http://localhost:8080/file" alt="Card image" />
                    </div>
                    <div class="col-md-8">
                        <div class="card-body">
                            <h5 class="card-title">${blog.title}</h5>
                            <p class="card-text">
                                <div class="col-lg-12">
                                    <div class="card mb-4">
                                        <div class="card-body">
                                             <div class="row">
                                                <div class="col-sm-3">
                                                    <p class="mb-0">User</p>
                                                </div>
                                                <div class="col-sm-9">
                                                    <p class="text-muted mb-0">${blog.user.username}</p>
                                                </div>
                                                <div class="col-sm-3">
                                                    <p class="mb-0">Role</p>
                                                </div>
                                                <div class="col-sm-9">
                                                    <p class="text-muted mb-0">${blog.user.role.name.substring(5).toLowerCase()}</p>
                                                </div>
                                            </div>
                                            <hr>
                                            <div class="row">
                                                <div class="col-sm-3">
                                                    <p class="mb-0">User</p>
                                                </div>
                                                <div class="col-sm-9">
                                                    <p class="text-muted mb-0">${blog.listTag}</p>
                                                </div>
                                            </div>
                                            <hr>
                                        </div>
                                    </div>
                                </div>
                            </p>
                            <div class="mt-2">
                                <button type="reset" class="btn btn-outline-secondary btn-back">Back</button>
                            </div>
                        </div>
                    </div>
                </div>
            `;

            idElForm.innerHTML = htmlDisplay;
        }
    }).fail(function(jqXHR, textStatus, errorThrown) {
        alert("Error " + errorThrown);
    });

    $(document).on("click", ".btn-back", function (e) {
        // prevent reload page
        e.preventDefault();

        window.location.replace("http://127.0.0.1:5502/html/users.html");
    })
})