
//아이디 체크여부 확인 (아이디 중복일 경우 = 0 , 중복이 아닐경우 = 1 )
function idCheckAjax() {
    const username = $("#username").val();
    if(username !== ''){
        $.ajax({
            url: "/user/idCheckAjax",
            type: 'GET',
            cache: false,
            data: {username : username},
            dataType:'json',   //return 타입을 의미한다!
            async: true,
            success: function (cnt) {
                console.log("data : "+cnt)
                if (cnt > 0) {
                    alert("아이디가 존재합니다. 다른 아이디를 입력해주세요.");
                    //아이디가 존재할 경우 빨깡으로 , 아니면 초록으로 처리하는 디자인
                    $("#username").addClass("is-invalid")
                    $("#username").removeClass("is-valid")
                    $("#username").focus();
                } else {
                    alert("사용가능한 아이디입니다.");
                    //아이디가 존재할 경우 빨깡으로 , 아니면 초록으로 처리하는 디자인
                    $("#username").addClass("is-valid")
                    $("#username").removeClass("is-invalid")
                    $("#name").focus();
                }
                setTimeout(function () {
                }, 1000)
            },
            error: function (e) {
                console.log("error : "+e)
            }
        })
    }else {
        alert("아이디를 입력하세요.");
        $("#username").focus();
    }
}
