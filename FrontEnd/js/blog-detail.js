$(document).ready(function () {
    const url = new URLSearchParams(window.location.search);
    const idBlog = url.get('idBlog')

    const content = document.getElementById('blog-content')
    const title = document.getElementById('content-title')

    $.ajax({
        method: "GET",
        url: `http://localhost:8080/blog/detail/${idBlog}`,
    })
        .done(function( msg ) {
            const blog = msg.data;
            let htmlTitle = '';
            let htmlDisplay = '';
            tags = []
            blog.listTag.forEach((tag) => {tags.push(tag.name)})

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
                    <a href="#" class="flex-c-m stext-107 cl6 size-301 bor7 p-lr-15 hov-tag1 trans-04 m-r-5 m-b-5">
                    Streetstyle
                    </a>
                    <a href="#" class="flex-c-m stext-107 cl6 size-301 bor7 p-lr-15 hov-tag1 trans-04 m-r-5 m-b-5">
                    Crafts
                    </a>
                    </div>
                    </div>
                `
            content.innerHTML = htmlDisplay;
            title.innerHTML = `${blog.title}`
        });
})