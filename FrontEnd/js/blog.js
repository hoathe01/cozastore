$(document).ready(function () {

    $.ajax({
        method: "GET",
        url: "http://localhost:8080/blog"
    }).done(function (res) {
        dataView = res.data;

        let listTag = "";
        const element = document.getElementById('blog-view');
        let htmlAdd = "";
        for (let x of dataView) {
            for (let t of x.listTag) {
                listTag += t.name + ", ";
            }
            let idBlog = x.id;
            htmlAdd += `
                    <div class="p-b-63"">
                        <a href="blog-detail.html?id=${x.id}" class="hov-img0 how-pos5-parent">
                            <img src="../assets/file/${x.image}" alt="IMG-BLOG">
                            <div class="flex-col-c-m size-123 bg9 how-pos5">
                                <span class="ltext-107 cl2 txt-center">${x.createDate.date}</span>
                                <span class="stext-109 cl3 txt-center">${x.createDate.month} ${x.createDate.year}</span>
                            </div>
                        </a>
                        <div class="p-t-32">
                            <h4 class="p-b-15">
                                <a href="blog-detail.html?id=${x.id}" class="ltext-108 cl2 hov-cl1 trans-04">
                                    ${x.title}
                                </a>
                            </h4>
                            <p class="stext-117 cl6" style="height:100px;overflow:hidden;">
                               ${x.content}
                            </p>
                            <div class="flex-w flex-sb-m p-t-18">
                             <span class="flex-w flex-m stext-111 cl2 p-r-30 m-tb-10">
                               <span>
                                <span class="cl4">By</span>
                                  ${x.user.username}
                                <span class="cl12 m-l-4 m-r-6">|</span>
                               </span>
                              <span>
                                 ${listTag}
                               <span class="cl12 m-l-4 m-r-6">|</span>
                              </span>
                              <span> ${x.listComment.length} Comments</span>
                             </span>
                                <a href="blog-detail.html?id=${x.id}" class="stext-101 cl2 hov-cl1 trans-04 m-tb-10">
                                    Continue Reading
                                    <i class="fa fa-long-arrow-right m-l-9"></i>
                                </a>
                            </div>
                        </div>
                    </div>
            `
            listTag = "";
        }
        element.innerHTML = htmlAdd;
    });
    let params = new URLSearchParams(document.location.search);
    let id = params.get("id");

    $.ajax({
        method: "get",
        url: "http://localhost:8080/blog/detail/" + id
    })
        .done(function (res) {
            localStorage.clear()
            dataView = res.data;
            console.log(dataView)
            $('#title').text(dataView.title)
            let listTag = "";
            let htmlTag = "";
            let htmlComment = "";

            for (let t of dataView.listTag) {
                listTag += t.name + ", "
                htmlTag += `<a href="#"
                                   class="flex-c-m stext-107 cl6 size-301 bor7 p-lr-15 hov-tag1 trans-04 m-r-5 m-b-5">
                                     ${t.name}
                                </a>`
            }
            var count = 0
            var limit = 5
            for (let comment of dataView.listComment.reverse()) {
                count +=1
                htmlComment += `<div class="flex-w flex-t p-b-45">
                            <div class="wrap-pic-s size-109 bor0 of-hidden m-r-18 m-t-6">
                               <img src="images/avatar-00.png" alt="AVATAR">
                            </div>
                                <div class="size-207">
                                    <div class="mtext-104">${comment.name}</div>
                                    <div class="mt-auto cl3">${comment.email}</div>
                                    <p class="stext-102 cl6">${comment.content}</p>
                                </div>
                            </div>
                            `
                if (count == limit) { break }
            }
            const element = document.getElementById('BlogDetail-view');
            let htmlAdd = `
            
                        <div class="wrap-pic-w how-pos5-parent">
                            <img src="../assets/file/${dataView.image}" alt="IMG-BLOG">
                            <div class="flex-col-c-m size-123 bg9 how-pos5">
                                <span class="ltext-107 cl2 txt-center">${dataView.createDate.date}</span>
                                <span class="stext-109 cl3 txt-center">${dataView.createDate.month} ${dataView.createDate.year}</span>
                            </div>
                        </div>
                        <div class="p-t-32">
                        <span class="flex-w flex-m stext-111 cl2 p-b-19"><span>
                        <span class="cl4">By</span> ${dataView.user.username}
                        <span class="cl12 m-l-4 m-r-6">|</span>
                        </span>
                            <span>${dataView.createDate.date} ${dataView.createDate.month}, ${dataView.createDate.year}<span class="cl12 m-l-4 m-r-6">|</span>
                            </span>
                        <span>
                            ${listTag}
                            <span class="cl12 m-l-4 m-r-6">|</span>
                        </span>
                            <span> ${dataView.listComment.length} Comments</span>
</span>
                            <h4 class="ltext-109 cl2 p-b-28">
                                ${dataView.title}
                            </h4>
                            <p class="stext-117 cl6 p-b-26">
                                ${dataView.content}
                            </p>
                        </div>
                        <div class="flex-w flex-t p-t-16">
<span class="size-216 stext-116 cl8 p-t-4">
Tags
</span>
                            <div class="flex-w size-217">
                               ${htmlTag}
                            </div>
                        </div>

                        <div class="p-t-40">
                            <h5 class="mtext-113 cl2 p-b-12" id="CommentTable">
                                Leave a Comment
                            </h5>
                            <p class="stext-107 cl6 p-b-40">
                                Your email address will not be published. Required fields are marked *
                            </p>
                            <form id="form-comment" action="#" target="CommentTable">
                                <div class="bor19 m-b-20">
                                <textarea class="stext-111 cl2 plh3 size-124 p-lr-18 p-tb-15"
                                          placeholder="Comment..." id="content-inp"></textarea>
                                </div>
                                <div class="bor19 size-218 m-b-20">
                                    <input class="stext-111 cl2 plh3 size-116 p-lr-18" type="text"
                                           placeholder="Name *" id="name-inp">
                                </div>
                                <div class="bor19 size-218 m-b-20">
                                    <input class="stext-111 cl2 plh3 size-116 p-lr-18" type="text"
                                           placeholder="Email *" id="email-inp">
                                </div>
                                <button type="button" class="flex-c-m stext-101 cl0 size-125 bg3 bor2 hov-btn3 p-lr-15 trans-04">
                                    Post Comment
                                </button>
                            </form>
                            <hr>
                            
                            ${htmlComment}
                              <button type="button" onclick="alert(${limit})" class="flex-c-m stext-101 cl0 size-125 bg1 bor2 hov-btn3 p-lr-15 trans-04">
                                    
                                    SHOW MORE
                                </button>
                        </div>`
            element.innerHTML = htmlAdd;

            $('#form-comment button').click(function () {
                let req = {
                    name: $('#name-inp').val(),
                    email: $('#email-inp').val(),
                    content: $('#content-inp').val(),
                    idBlog: id
                }

                $.ajax({
                    method: "POST",
                    url: "http://localhost:8080/comment/add",
                    contentType: "application/json; charset=utf-8",
                    data: JSON.stringify(req)
                })
                    .done(function (msg) {

                        window.location.reload()
                    });
            })
        });


})