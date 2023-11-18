$(document).ready(function () {
    const idElTable = document.getElementById("idBlogTable");

    $.ajax({
        url: `http://localhost:8080/blog`,
        method: "GET",
    }).done(function (data) {
        const blogs = data.data;
        console.log(blogs)
        // let index = 0
        blogs.forEach((blog, index) => {
            let htmlDisplay = "";
            idBlog = blog.id
            // create tr element
            let tr = document.createElement("tr");
            tr.setAttribute("id", `idBlog-${blog.id}`);
            idElTable.appendChild(tr);


            const idElBlog = document.getElementById(`idBlog-${blog.id}`);

            let commentCount = blog.listComment.length
            htmlDisplay = `
                <td>${index + 1}</td>
                <td>${blog.title}</td>
                <td>${blog.content.substring(0, 20)}</td>
                <td>${blog.user.username}</td>
                <td>${blog.createDate.date} ${blog.createDate.month} ${blog.createDate.year}</td>
                <td>${commentCount}</td>
                <td>
                    <div class="dropdown">
                        <button type="button" class="btn p-0 dropdown-toggle hide-arrow" data-bs-toggle="dropdown">
                            <i class="bx bx-dots-vertical-rounded"></i>
                        </button>
                        <div class="dropdown-menu">
                          <a class="dropdown-item btn-delete" href="#" idDelete="${blog.id}">
                                <i class="bx bx-edit-alt me-1"></i> Delete
                            </a>
                            <a class="dropdown-item" href="blog-detail.html?id=${blog.id}">
                                <i class="bx bx-edit-alt me-1"></i> Edit
                            </a>
                            <a class="dropdown-item" href="../../html/user-detail.html?id=${blog.id}">
                                <i class="bx bx-detail me-1"></i> Detail
                            </a>
                        </div>
                    </div>
                </td>
            `;

            idElBlog.innerHTML = htmlDisplay;
        });
        $('.btn-delete').on('click', function() {
            var idDelete = $(this).attr('idDelete')
            console.log(idDelete)
            var This = $(this)
            $.ajax({
                method: "DELETE",
                url: "http://localhost:8080/blog",
                data: {
                    id: idDelete
                }
            })
                .done(function(res) {
                    if (res.data === true) {
                        This.closest('tr').remove()
                    }
                });

        })
    }).fail(function (jqXHR, textStatus, errorThrown) {
        alert("Error " + errorThrown);
    });
    // onclick="this.closest('tr').remove()"


})
