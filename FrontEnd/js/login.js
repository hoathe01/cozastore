// Khi nội dung file html đã được hiển thị trên browser thì sẽ kích hoạt
$(document).ready(function(){
    
    $("#btn-sign-in").click(function(){
        var username = $("#username").val()
        var password = $("#password").val()
        console.log("username : ",username, " password : ",password);
        $.ajax({
            url: "http://localhost:8080/login/signin",
            method: "POST",
            data: {
                email: username,
                password: password
            }
        }).done(function(data){
            console.log("server tra ve ", data)
            console.log(data.data)
          if (data.data != null){
              localStorage.setItem('token',data.data)
              window.location.href = "blog.html"
          } else {
              alert('Mật khẩu hoặc tên người dùng nhập vào không đúng')
          }
        })
    })
    $("#form-signup").submit(function (e) {
        e.preventDefault()
        let form = $(this).serializeArray()
        let data = {}
        for (let item of form){
            data[item.name] = item.value
        }
        console.log(data['email'])

        $.ajax({
            url: "http://localhost:8080/login/signup",
            method: "POST",
            contentType: "application/json",
            data: JSON.stringify(data)
        }).done(function(msg){
            const isSuccess = msg.data
            if (isSuccess === true){
                $.ajax({
                    url: "http://localhost:8080/login/signin",
                    method: "POST",
                    data: {
                        email: data['email'],
                        password: data['password']
                    }
                }).done(function(res){
                    if (res.data != null){
                        localStorage.setItem('token',res.data)
                        window.location.href = "blog.html"
                    }
                })
            } else {
                alert('Đăng Kí thất Bại')
            }
        })
    })

})