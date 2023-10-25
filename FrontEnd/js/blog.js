$(document).ready(function () {
    $.ajax({
        method: "GET",
        url: "http://localhost:8080/blog"
    }).done(function (res) {
            dataView = res.data;
        var listTag = "";
            // console.log(dataView);
        const element = document.getElementById('blog-view');
        let htmlAdd = "";
        for (let x of dataView) {
            console.log(x)
            let date = Date.parse(x.createDate)
            for (let t of x.listTag) {
                listTag += t.name +", ";
            }
            htmlAdd +=`
                    <div class="p-b-63">
                        <a href="blog-detail.html" class="hov-img0 how-pos5-parent">
                            <img src="../assets/file/${x.image}" alt="IMG-BLOG">
                            <div class="flex-col-c-m size-123 bg9 how-pos5">
                                <span class="ltext-107 cl2 txt-center">${x.createDate.date}</span>
                                <span class="stext-109 cl3 txt-center">${x.createDate.month} ${x.createDate.year}</span>
                            </div>
                        </a>
                        <div class="p-t-32">
                            <h4 class="p-b-15">
                                <a href="blog-detail.html" class="ltext-108 cl2 hov-cl1 trans-04">
                                    ${x.title}
                                </a>
                            </h4>
                            <p class="stext-117 cl6">
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
                                <a href="blog-detail.html" class="stext-101 cl2 hov-cl1 trans-04 m-tb-10">
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
})