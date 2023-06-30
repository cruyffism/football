
//아이디 체크여부 확인 (아이디 중복일 경우 = 0 , 중복이 아닐경우 = 1 )
function idCheckAjax() {
    const username = $("#username").val();  // 내가 회원가입한 아이디 alsrl가 username으로 들어감
    if(username !== ''){ // username이 빈값이 아닐때 아작스 호출하라는 의미
        $.ajax({
            url: "/user/idCheckAjax",  //우리가 만든 백엔드 url
            type: 'GET',//get매핑이니까 get 타입
            cache: false,
            data: {username : username}, //프론트엔드에서 백엔드로 보내주는 값(signup.html >> UserController)
                                         // 이제 username 에 alsrl가 들어가서 백엔드에서 @RequestParam String username 요렇게 매개변수로 받을수잇져!
            dataType:'json',   //return 타입을 의미한다!
            // 여기까지 api 쏠 준비 완료

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

$(document).ready(function () {
    const forms = document.querySelectorAll('.needs-validation')
    Array.from(forms).forEach(form => {
        form.addEventListener('submit', event => {
            console.log("submit : ")
            if (!form.checkValidity()) {
                event.preventDefault()
                event.stopPropagation()
            }
            form.classList.add('was-validated')
        }, false)
    })
})
