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
              window.location.replace("../index.html")
          } else {
              alert('Mật khẩu hoặc tên người dùng nhập vào không đúng')
          }
        })
    })

})