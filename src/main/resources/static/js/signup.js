
//아이디 체크여부 확인 (아이디 중복일 경우 = 0 , 중복이 아닐경우 = 1 )
var idck = 0;function idCheckAjax() {
    const username = $("#username").val();
    $.ajax({
        url: "/user/idCheckAjax",
        type: 'GET',
        cache: false,
        data: username,
        dataType:"json",   //return 타입을 의미한다!
        async: false,
        success: function (data) {
            if (data.cnt > 0) {

                alert("아이디가 존재합니다. 다른 아이디를 입력해주세요.");
                //아이디가 존제할 경우 빨깡으로 , 아니면 파랑으로 처리하는 디자인
                $("#divInputId").addClass("has-error")
                $("#divInputId").removeClass("has-success")
                $("#username").focus();


            } else {
                alert("사용가능한 아이디입니다.");
                //아이디가 존제할 경우 빨깡으로 , 아니면 파랑으로 처리하는 디자인
                $("#divInputId").addClass("has-success")
                $("#divInputId").removeClass("has-error")
                $("#name").focus();
                //아이디가 중복하지 않으면  idck = 1
                idck = 1;
            }


            setTimeout(function () {
            }, 1000)
        },
        error: function (e) {
            $(innerHtml).html("")
        }
    })
}
