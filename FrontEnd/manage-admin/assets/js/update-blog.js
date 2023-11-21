$(document).ready(function () {
    // get id user
    const queryString = window.location.search;
    const urlParams = new URLSearchParams(queryString);
    const idBlog = Number(urlParams.get("id"));

    const idElForm = document.getElementById("formUser");

    $.ajax({
        url: `http://localhost:8080/blog/detail/${idBlog}`,
        method: "get",
    }).done(function (data) {
        const blogs = data?.data;
        let htmlDisplay = "";

        // getListRole(blogs.idRole);
        var ListUsers = getListUsers()
        var listTags = getListTags()
        console.log(listTags)
        if (!!blogs) {
            htmlDisplay = `
                <div class="row mb-3">
                    <label class="col-sm-2 col-form-label">Blog Title</label>
                    <div class="col-sm-10">
                        <input type="text" class="form-control" id="title-value" placeholder="John" value="${blogs.title}" autofocus />
                    </div>
                </div>
                <div class="row mb-3">
                    <label class="col-sm-2 col-form-label">Author</label>
                    <div class="col-sm-10">
                        <input type="text" class="form-control ui-widget" id="user-value" placeholder="Author's Name" value="${blogs.user.username}" autofocus />
                    </div>
                </div>
                <div class="row mb-3">
                    <label class="col-sm-2 col-form-label">Tags</label>
                    <div class="col-sm-10">
                        <input type="text" class="form-control ui-widget" name='tags' id="tag-value" placeholder="Tag here!" value="" autofocus multiple/>
                    </div>
                </div>
                <div class="row mb-3">
                    <label class="col-sm-2 col-form-label">Image</label>
                    <div class="col-sm-10">
                        <input  type="file" class="form-control" id="file-value" autofocus accept="image/png, image/jpeg"/>
                    </div>
                </div>
                 
                <div class="row mb-6">
                    <label class="col-sm-2 col-form-label">Content</label>
                    <div class="col-sm-10">
                    <textarea class="form-control" id="content-value" style="resize: none;height: 250px;max-height:max-content" contenteditable="true" autofocus>${blogs.content}</textarea>
                    </div>
                </div>
               
                <div class="row justify-content-end">
                    <div class="col-sm-5">
                        <input type="submit" class="btn btn-primary btn-submit-user" value="Submit"/>
                    </div>
                    <div class="col-sm-5">
                        <input type="button" class="btn btn-outline-secondary btn-back" value="Back"/>
                    </div>
                </div>
            `;

            idElForm.innerHTML = htmlDisplay;
        }
        $("#user-value").autocomplete({
            source: ListUsers
        });
        $("#tag-value").autocomplete({
            source: listTags
        });
        getTags()
    }).fail(function (jqXHR, textStatus, errorThrown) {
        alert("Error " + errorThrown);
    });

    function getTags() {
        const input = document.querySelector("input[name='tags']");
        input.addEventListener("change", () => {
            const tags = input.value.split(" ");
        });
    }

    function getListTags() {
        let tags = []
        $.ajax({
            url: `http://localhost:8080/tag`,
            method: "get",
        }).done(function (data) {
            let datas = data?.data;
            datas.forEach((tag, index) => {
                tags.push(tag.name)
            });
            // console.log(users)'
        })
        return tags;
    }

    function getListUsers() {
        let users = []
        $.ajax({
            url: `http://localhost:8080/user`,
            method: "get",
        }).done(function (data) {
            let datas = data?.data;
            datas.forEach((user, index) => {
                users.push(user.username)
            });
            // console.log(users)'
        })
        return users;
    }

    $(document).on("click", ".btn-submit-user", function (e) {
        // prevent reload page
        e.preventDefault();
        let listTag = [{id: 2}]
        const file = document.getElementById('file-value').files[0];
        let blog = JSON.stringify({
            id: idBlog,
            title: $('#title-value').val(),
            content: $('#content-value').val(),
            user: 10,
            listTag: listTag
        })
        const data = new Blob([blog], {type: "application/json"})
        const formData = new FormData();
        formData.append('blogRequest', data)
        formData.append('file', file)
        console.log(data)
        console.log(file)
        $.ajax({
            url: `http://localhost:8080/blog`,
            method: "put",
            data: formData,
            contentType: false,
            processData: false,
            cache: false
        }).done(function (data) {
            const result = data?.data;
            console.log(data)
            if (result) {
                alert("Success");
            } else {
                alert("Error");
            }
        })
    })

    $(document).on("click", ".btn-back", function (e) {
        // prevent reload page
        e.preventDefault();

        window.location.replace("http://127.0.0.1:5502/html/users.html");
    })
})