$(document).ready(function () {
    const message = $("#msg").val();
    const url = $("#url").val();
    console.log("msg : ", message)
    console.log("url : ", url)
    alert(message);
    document.location.href = url;
})