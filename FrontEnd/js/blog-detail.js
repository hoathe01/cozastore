$(document).ready(function () {
    const url = new URLSearchParams(window.location.search);
    const idBlog = url.get('idBlog')
    let sizeComment = 3
    const content = document.getElementById('blog-content')
    const title = document.getElementById('content-title')
    const commentList = document.getElementById('comment-display')

    $.ajax({
        method: "GET",
        url: `http://localhost:8080/blog/detail/${idBlog}`,
    })
        .done(function (msg) {
            const blog = msg.data;
            let htmlDisplay = '';
            let tagsDisplay = '';
            tags = []
            blog.listTag.forEach((tag) => {
                tags.push(tag.name)
                tagsDisplay += `<a href="#" class="flex-c-m stext-107 cl6 size-301 bor7 p-lr-15 hov-tag1 trans-04 m-r-5 m-b-5">
                    ${tag.name}
                    </a>`
            })
            htmlDisplay = `
                    <div class="wrap-pic-w how-pos5-parent">
                    <img src="../../assets/file/${blog.image}" alt="IMG-BLOG">
                    <div class="flex-col-c-m size-123 bg9 how-pos5">
                    <span class="ltext-107 cl2 txt-center">${blog.createDate.date}</span>
                    <span class="stext-109 cl3 txt-center">${blog.createDate.month} ${blog.createDate.year}</span>
                    </div>
                    </div>
                    <div class="p-t-32">
                    <span class="flex-w flex-m stext-111 cl2 p-b-19"><span>
                    <span class="cl4">By</span> ${blog.user.username}
                    <span class="cl12 m-l-4 m-r-6">|</span>
                    </span>
                    <span>${blog.createDate.date} ${blog.createDate.month}, ${blog.createDate.year}
                    <span class="cl12 m-l-4 m-r-6">|</span>
                    </span>
                    <span>${tags.join(', ')}
                    <span class="cl12 m-l-4 m-r-6">|</span>
                    </span>
                    <span>${blog.listComment.length} Comments</span>
                    </span>
                    <h4 class="ltext-109 cl2 p-b-28">
                    ${blog.title}
                    </h4>
                    <p class="stext-117 cl6 p-b-26">
                    ${blog.content}
                    </p>
                    </div>
                    <div class="flex-w flex-t p-t-16">
                    <span class="size-216 stext-116 cl8 p-t-4">Tags</span>
                    <div class="flex-w size-217">
                    ${tagsDisplay}
                    </div>
                    </div>
                `
            content.innerHTML = htmlDisplay;
            title.innerHTML = `${blog.title}`
            getListComment(idBlog,sizeComment)
            showMoreComments(idBlog,sizeComment)
            addComment()
        }).fail(function (jqXHR, textStatus, error) {
        // Handle error here
        alert('Error: ' + error)
    });

    function showMoreComments(idBlog,size){
        $('#show-more').click(function () {
            size +=5
            getListComment(idBlog,size)
        })
    }

    function getListComment(id,size) {
        $.ajax({
            method: "GET",
            url: `http://localhost:8080/comment/id=${id}/size=${size}`
        })
            .done(function (msg) {
                const comments = msg.data
                let htmlDisplay = '';
                if (size > comments.length){$('#show-more').hide()}
                comments.forEach((comment) => {
                    htmlDisplay += ` 
                                  <div class="flex-w flex-t p-b-68" id="comment-${comment.id}">
                                    <div class="wrap-pic-s size-109 bor0 of-hidden m-r-18 m-t-6">
                                    <img src="images/avatar-00.png" alt="AVATAR">
                                    </div>
                                    <div class="size-207">
                                    <div class="flex-w flex-sb-m p-b-17">
                                    <span class="mtext-107 cl2 p-r-20">${comment.name}</span>
                                    </div>
                                    <p class="stext-102 cl6">
                                    ${comment.content}
                                    </p>
                                    </div>
                                    </div>`
                });
                commentList.innerHTML = htmlDisplay;
            });
    }
    function addComment() {
        $('#form-comment').submit(function (e) {
            e.preventDefault();

            let data = $(this).serializeArray()
            let jsonData = {}
            for (let item of data) {
                jsonData[item.name] = item.value;
            }
            jsonData['idBlog'] = idBlog
            $.ajax({
                method: "POST",
                url: `http://localhost:8080/comment/add`,
                contentType: "application/json",
                data: JSON.stringify(jsonData)
            })
                .done(function (msg) {
                    let isSUccess = msg.data
                    if (isSUccess === true) {
                        getListComment(idBlog,10)
                    } else {
                        alert('Comment Failed')
                    }

                })
        })
    }

})