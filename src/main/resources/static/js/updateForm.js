let isEmailCheck = 1; //아이디 체크 여부 확인 (아이디 중복일 경우 = 0 , 중복이 아닐 경우 = 1)
let isNicknameCheck = 1; //아이디 체크 여부 확인 (아이디 중복일 경우 = 0 , 중복이 아닐 경우 = 1)

// 아이디 중복 체크하는 function
function emailCheckAjax() {
    const email = $("#email").val();  // 내가 회원가입한 아이디 alsrl가 username으로 들어감
    if (email !== '') { // username이 빈값이 아닐때 아작스 호출하라는 의미
        $.ajax({
            url: "/user/emailCheckAjax",  //우리가 만든 백엔드 url
            type: 'GET',//get매핑이니까 get 타입
            cache: false,
            data: {email: email}, //프론트엔드에서 백엔드로 보내주는 값(signup.html >> UserController)
            // 이제 username 에 alsrl가 들어가서 백엔드에서 @RequestParam String username 요렇게 매개변수로 받을수잇져!
            dataType: 'json',   //return 타입을 의미한다!
            // 여기까지 api 쏠 준비 완료
            async: true,
            success: function (cnt) {
                if (cnt > 0) { // 아이디 중복
                    alert("이메일이 존재합니다. 다른 이메일을 입력해주세요.");
                    //아이디가 존재할 경우 빨깡으로 , 아니면 초록으로 처리하는 디자인
                    $("#email").addClass("is-invalid")
                    $("#email").removeClass("is-valid")
                    $("#email").attr("readonly", false)
                    $("#emailCheck").attr("disabled", false)
                    $("#email").focus();
                    isEmailCheck = 0;
                } else { // 아이디 사용 가능
                    alert("사용가능한 이메일입니다.");
                    //아이디가 존재할 경우 빨깡으로 , 아니면 초록으로 처리하는 디자인
                    $("#email").addClass("is-valid")
                    $("#email").removeClass("is-invalid")
                    $("#email").attr("readonly", true)
                    $("#emailCheck").attr("disabled", true)
                    $("#gender").focus();
                    isEmailCheck = 1;
                }
                setTimeout(function () {
                }, 1000)
            },
            error: function (e) {
                console.log("error : " + e)
            }
        })
    } else {
        alert("이메일을 입력하세요.");
        $("#email").focus();
    }
}

// 닉네임 중복 체크하는 function
function nicknameCheckAjax() {
    const nickname = $("#nickname").val();  // 내가 회원가입한 아이디 alsrl가 username으로 들어감
    if (nickname !== '') { // username이 빈값이 아닐때 아작스 호출하라는 의미
        $.ajax({
            url: "/user/nicknameCheckAjax",  //우리가 만든 백엔드 url
            type: 'GET',//get매핑이니까 get 타입
            cache: false,
            data: {nickname: nickname}, //프론트엔드에서 백엔드로 보내주는 값(signup.html >> UserController)
            // 이제 username 에 alsrl가 들어가서 백엔드에서 @RequestParam String username 요렇게 매개변수로 받을수잇져!
            dataType: 'json',   //return 타입을 의미한다!
            // 여기까지 api 쏠 준비 완료
            async: true,
            success: function (cnt) {
                if (cnt > 0) { // 아이디 중복
                    alert("닉네임이 존재합니다. 다른 닉네임을 입력해주세요.");
                    //아이디가 존재할 경우 빨깡으로 , 아니면 초록으로 처리하는 디자인
                    $("#nickname").addClass("is-invalid")
                    $("#nickname").removeClass("is-valid")
                    $("#nickname").attr("readonly", false)
                    $("#nicknameCheck").attr("disabled", false)
                    $("#nickname").focus();
                    isNicknameCheck = 0;
                } else { // 아이디 사용 가능
                    alert("사용가능한 닉네임입니다.");
                    //아이디가 존재할 경우 빨깡으로 , 아니면 초록으로 처리하는 디자인
                    $("#nickname").addClass("is-valid")
                    $("#nickname").removeClass("is-invalid")
                    $("#nickname").attr("readonly", true)
                    $("#nicknameCheck").attr("disabled", true)
                    $("#gender").focus();
                    isNicknameCheck = 1;
                }
                setTimeout(function () {
                }, 1000)
            },
            error: function (e) {
                console.log("error : " + e)
            }
        })
    } else {
        alert("닉네임을 입력하세요.");
        $("#nickname").focus();
    }
}

$(document).ready(function () {
    $('#phone-error').hide(); // 폰번호 에러문구 숨기기
    // 회원가입 폼 null 체크 및 아이디 중복체크 문구 나오게
    const updateForm = document.querySelectorAll('#updateForm')
    Array.from(updateForm).forEach(form => {
        form.addEventListener('submit', event => {
            // event.preventDefault()랑 event.stopPropagation() 는 회원가입 저장이 안되게 막는 기능
            if (!form.checkValidity()) { // input에 값을 안 넣었을 때
                event.preventDefault()
                event.stopPropagation()
            }

            if (isEmailCheck === 0) { // 아이디 중복 체크를 안했을 경우
                alert("이메일 중복체크를 해주세요.")
                event.preventDefault()
                event.stopPropagation()
            }

            if (isNicknameCheck === 0) { // 아이디 중복 체크를 안했을 경우
                alert("닉네임 중복체크를 해주세요.")
                event.preventDefault()
                event.stopPropagation()
                form.classList.add('was-validated')
            }
            form.classList.add('was-validated') // input창에 디자인 넣는곳 에러인지 성공인지
        }, false)
    })
})

// 폰번호 유효성 체크하는 function
function phoneCheck(number) {
    const regExp = /^[0-9]{11}$/; // 0-9로 이루어진 11자
    if (!regExp.test(number)) { // regExp 조건에 맞지 않으면
        $('#phone-error').show(); // 에러문구 보여줘  : login.html의 id 가 password-error인  애꺼를 보여줘라!
    } else { // regExp 조건에 맞으면
        $('#phone-error').hide(); // 에러문구 없애줘
    }
}

// 폰번호 유효성 체크하는 function
function emailRealCheck(email) {
    const ori_email = $('#oriEmail').val()

    if (ori_email === email) {
        $("#emailCheck").attr("disabled", true)
        isEmailCheck = 1
    } else {
        $("#emailCheck").attr("disabled", false)
        isEmailCheck = 0
    }

}

// 폰번호 유효성 체크하는 function
function nicknameRealCheck(nickname) {
    const ori_nickname = $('#oriNickname').val()

    if (ori_nickname === nickname) {
        $("#nicknameCheck").attr("disabled", true)
        isNicknameCheck = 1
    } else {
        $("#nicknameCheck").attr("disabled", false)
        isNicknameCheck = 0
    }

}
