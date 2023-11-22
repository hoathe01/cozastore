$(document).ready(()=>{
    $.ajax({
        url:"http://localhost:8080/cart/1",
        method:"get",
      
    }).done((data)=>{
        console.log(data)
    })
}) 