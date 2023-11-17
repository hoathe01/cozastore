$(document).ready(function () {
    const url = new URLSearchParams(window.location.search);
    let index = url.get('index')
    let yearBegin = url.get('begin')
    let yearEnd = url.get('end')
    if (!index) {index = 0}

    const BlogTable = document.getElementById('table-blog')
    const TotalPage = document.getElementById('id-pagination')
    const CategoriesTable = document.getElementById('categories-table')
    const searchResult = document.getElementById('search-result')

    $("#blog-search").on('input',function() {
        let value = $(this).val()
        $("#values").text(value)
        console.log(value)
        if (!value) {searchResult.innerHTML = ""}
            $.ajax({
                method: "GET",
                url: "http://localhost:8080/blog/search",
                data: {keyword: value}
            })
                .done(function (msg) {
                    const datas = msg.data
                    let htmlDisplay = '';
                    datas.forEach((data) => {
                        htmlDisplay += ` 
                                   <li style="border-bottom: 1px solid; padding: 10px">
                                   <a href="blog-detail.html?idBlog=${data.id}" class="stext-103 cl6 plh4 size-116">${data.title}</a>
                                    <br>
                                    <span class="cl4" style="font-size: 12px">By <strong>${data.user.username}</strong></span>
                                    </li>`
                    });
                    searchResult.innerHTML = htmlDisplay;

                });

    });

    const pagination = document.querySelector('select');
    pagination.addEventListener('change', function () {
        localStorage.setItem('size', pagination.value)
        window.location.reload()
    });

    if (!url.has('begin') && !url.has('end')) {
        $.ajax({
            method: "GET",
            url: `http://localhost:8080/blog/index=${index}/size=${localStorage.getItem('size')}/${localStorage.getItem('idTag')}`
        })
            .done(function (msg) {
                const blogs = msg.data

                blogs.forEach((blog, index) => {
                    let htmlDisplay = '';
                    let div = document.createElement('div');
                    div.setAttribute("id", `idBlog-${index}`)

                    BlogTable.appendChild(div)
                    tags = []
                    blog.listTag.forEach((tag) => {
                        tags.push(tag.name)
                    })

                    htmlDisplay = `
                    <div class="p-b-63">
                    <a href="blog-detail.html?idBlog=${blog.id}" class="hov-img0 how-pos5-parent">
                       <img src="../../assets/file/${blog.image}" alt="IMG-BLOG">
                       <div class="flex-col-c-m size-123 bg9 how-pos5">
                       <span class="ltext-107 cl2 txt-center">${blog.createDate.date}</span>
                       <span class="stext-109 cl3 txt-center">${blog.createDate.month} ${blog.createDate.year}</span>
                       </div>
                    </a>
                    <div class="p-t-32">
                        <h4 class="p-b-15">
                        <a href="blog-detail.html?idBlog=${blog.id}" class="ltext-108 cl2 hov-cl1 trans-04">
                            ${blog.title}
                        </a>
                        </h4>
                        <p class="stext-117 cl6">
                            ${blog.content.slice(0, 150)}...
                        </p>
                        <div class="flex-w flex-sb-m p-t-18">
                        <span class="flex-w flex-m stext-111 cl2 p-r-30 m-tb-10">
                        <span>
                        <span class="cl4">By</span> ${blog.user.username}
                        <span class="cl12 m-l-4 m-r-6">|</span>
                        </span>
                        <span>${tags.join(', ')}
                        <span class="cl12 m-l-4 m-r-6">|</span>
                        </span>
                        <span>${blog.listComment.length} Comments</span>
                        </span>
                        <a href="blog-detail.html?idBlog=${blog.id}" class="stext-101 cl2 hov-cl1 trans-04 m-tb-10">
                            Continue Reading
                        <i class="fa fa-long-arrow-right m-l-9"></i>
                        </a>
                        </div>
                        </div>
                        </div>
                `
                    BlogTable.innerHTML += htmlDisplay;

                });
            });

    } else {
        $.ajax({
            method: "GET",
            url: `http://localhost:8080/blog/${yearBegin}to${yearEnd}`
        })
            .done(function (msg) {
                const blogs = msg.data

                blogs.forEach((blog, index) => {
                    let htmlDisplay = '';
                    let div = document.createElement('div');
                    div.setAttribute("id", `idBlog-${index}`)

                    BlogTable.appendChild(div)
                    tags = []
                    blog.listTag.forEach((tag) => {
                        tags.push(tag.name)
                    })

                    htmlDisplay = `
                    <div class="p-b-63">
                    <a href="blog-detail.html?idBlog=${blog.id}" class="hov-img0 how-pos5-parent">
                       <img src="../../assets/file/${blog.image}" alt="IMG-BLOG">
                       <div class="flex-col-c-m size-123 bg9 how-pos5">
                       <span class="ltext-107 cl2 txt-center">${blog.createDate.date}</span>
                       <span class="stext-109 cl3 txt-center">${blog.createDate.month} ${blog.createDate.year}</span>
                       </div>
                    </a>
                    <div class="p-t-32">
                        <h4 class="p-b-15">
                        <a href="blog-detail.html?idBlog=${blog.id}" class="ltext-108 cl2 hov-cl1 trans-04">
                            ${blog.title}
                        </a>
                        </h4>
                        <p class="stext-117 cl6">
                            ${blog.content.slice(0, 150)}...
                        </p>
                        <div class="flex-w flex-sb-m p-t-18">
                        <span class="flex-w flex-m stext-111 cl2 p-r-30 m-tb-10">
                        <span>
                        <span class="cl4">By</span> ${blog.user.username}
                        <span class="cl12 m-l-4 m-r-6">|</span>
                        </span>
                        <span>${tags.join(', ')}
                        <span class="cl12 m-l-4 m-r-6">|</span>
                        </span>
                        <span>${blog.listComment.length} Comments</span>
                        </span>
                        <a href="blog-detail.html?idBlog=${blog.id}" class="stext-101 cl2 hov-cl1 trans-04 m-tb-10">
                            Continue Reading
                        <i class="fa fa-long-arrow-right m-l-9"></i>
                        </a>
                        </div>
                        </div>
                        </div>
                `
                    BlogTable.innerHTML += htmlDisplay;

                });
            });
    }


    $.ajax({
        method: "GET",
        url: "http://localhost:8080/blog/all"
    })
        .done(function (msg) {
            const blog = msg.data.length
            const amountPage = localStorage.getItem('size')
            var paging = Math.ceil(blog / amountPage)
            let htmlDisplay = '';
            if (localStorage.getItem('idTag') !== 0) {
                // alert(localStorage.getItem('idTag'))
                for (let i = 1; i <= paging; i++) {
                    htmlDisplay += `<a href="blog.html?index=${i - 1}" class="flex-c-m how-pagination1 trans-04 m-all-7">${i}</a>`
                }
            }
            TotalPage.innerHTML = htmlDisplay;
        });

    $.ajax({
        method: "GET",
        url: "http://localhost:8080/tag"
    })
        .done(function (msg) {
            const tags = msg.data
            console.log(tags)
            CategoriesTable.innerHTML = `
                            <li class="bor18">
                            <a href="blog.html?index=${index}" class="dis-block stext-115 cl6 hov-cl1 trans-04 p-tb-8 p-lr-4" 
                            onclick="localStorage.setItem('idTag', '0');localStorage.setItem('size', '10')">
                            All
                            </a>
            `
            tags.forEach((tag) => {
                let htmlDisplay = '';
                let li = document.createElement('li')
                li.setAttribute("id", `idTag-${tag.id}`)
                CategoriesTable.appendChild(li)
                let idTag = tag.id
                htmlDisplay = `
                            <li class="bor18">
                            <a href="blog.html?index=0" class="dis-block stext-115 cl6 hov-cl1 trans-04 p-tb-8 p-lr-4" 
                            onclick="localStorage.setItem('idTag', ${idTag});localStorage.setItem('size', '50')">
                            ${tag.name}
                            </a>`

                CategoriesTable.innerHTML += htmlDisplay;
            })
        });

})