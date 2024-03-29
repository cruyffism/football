let isCheck = 0; //아이디 체크 여부 확인 (아이디 중복일 경우 = 0 , 중복이 아닐 경우 = 1)
let isEmailCheck = 0; //아이디 체크 여부 확인 (아이디 중복일 경우 = 0 , 중복이 아닐 경우 = 1)
let isNicknameCheck = 0; //아이디 체크 여부 확인 (아이디 중복일 경우 = 0 , 중복이 아닐 경우 = 1)

// 아이디 중복 체크하는 function
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
                if (cnt > 0) { // 아이디 중복
                    alert("아이디가 존재합니다. 다른 아이디를 입력해주세요.");
                    //아이디가 존재할 경우 빨깡으로 , 아니면 초록으로 처리하는 디자인
                    $("#username").addClass("is-invalid")
                    $("#username").removeClass("is-valid")
                    $("#username").attr("readonly",false)
                    $("#idCheck").attr("disabled",false)
                    $("#username").focus();
                    isCheck = 0;
                } else { // 아이디 사용 가능
                    alert("사용가능한 아이디입니다.");
                    //아이디가 존재할 경우 빨깡으로 , 아니면 초록으로 처리하는 디자인
                    $("#username").addClass("is-valid")
                    $("#username").removeClass("is-invalid")
                    $("#username").attr("readonly",true)
                    $("#idCheck").attr("disabled",true)
                    $("#name").focus();
                    isCheck = 1;
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

// 아이디 중복 체크하는 function
function emailCheckAjax() {
    const email = $("#email").val();  // 내가 회원가입한 아이디 alsrl가 username으로 들어감
    if(email !== ''){ // username이 빈값이 아닐때 아작스 호출하라는 의미
        $.ajax({
            url: "/user/emailCheckAjax",  //우리가 만든 백엔드 url
            type: 'GET',//get매핑이니까 get 타입
            cache: false,
            data: {email : email}, //프론트엔드에서 백엔드로 보내주는 값(signup.html >> UserController)
                                         // 이제 username 에 alsrl가 들어가서 백엔드에서 @RequestParam String username 요렇게 매개변수로 받을수잇져!
            dataType:'json',   //return 타입을 의미한다!
            // 여기까지 api 쏠 준비 완료
            async: true,
            success: function (cnt) {
                if (cnt > 0) { // 아이디 중복
                    alert("이메일이 존재합니다. 다른 이메일을 입력해주세요.");
                    //아이디가 존재할 경우 빨깡으로 , 아니면 초록으로 처리하는 디자인
                    $("#email").addClass("is-invalid")
                    $("#email").removeClass("is-valid")
                    $("#email").attr("readonly",false)
                    $("#emailCheck").attr("disabled",false)
                    $("#email").focus();
                    isEmailCheck = 0;
                } else { // 아이디 사용 가능
                    alert("사용가능한 이메일입니다.");
                    //아이디가 존재할 경우 빨깡으로 , 아니면 초록으로 처리하는 디자인
                    $("#email").addClass("is-valid")
                    $("#email").removeClass("is-invalid")
                    $("#email").attr("readonly",true)
                    $("#emailCheck").attr("disabled",true)
                    $("#gender").focus();
                    isEmailCheck = 1;
                }
                setTimeout(function () {
                }, 1000)
            },
            error: function (e) {
                console.log("error : "+e)
            }
        })
    }else {
        alert("이메일을 입력하세요.");
        $("#email").focus();
    }
}

// 닉네임 중복 체크하는 function
function nicknameCheckAjax() {
    const nickname = $("#nickname").val();  // 내가 회원가입한 아이디 alsrl가 username으로 들어감
    if(nickname !== ''){ // username이 빈값이 아닐때 아작스 호출하라는 의미
        $.ajax({
            url: "/user/nicknameCheckAjax",  //우리가 만든 백엔드 url
            type: 'GET',//get매핑이니까 get 타입
            cache: false,
            data: {nickname : nickname}, //프론트엔드에서 백엔드로 보내주는 값(signup.html >> UserController)
            // 이제 username 에 alsrl가 들어가서 백엔드에서 @RequestParam String username 요렇게 매개변수로 받을수잇져!
            dataType:'json',   //return 타입을 의미한다!
            // 여기까지 api 쏠 준비 완료
            async: true,
            success: function (cnt) {
                if (cnt > 0) { // 아이디 중복
                    alert("닉네임이 존재합니다. 다른 닉네임을 입력해주세요.");
                    //아이디가 존재할 경우 빨깡으로 , 아니면 초록으로 처리하는 디자인
                    $("#nickname").addClass("is-invalid")
                    $("#nickname").removeClass("is-valid")
                    $("#nickname").attr("readonly",false)
                    $("#nicknameCheck").attr("disabled",false)
                    $("#nickname").focus();
                    isNicknameCheck = 0;
                } else { // 아이디 사용 가능
                    alert("사용가능한 닉네임입니다.");
                    //아이디가 존재할 경우 빨깡으로 , 아니면 초록으로 처리하는 디자인
                    $("#nickname").addClass("is-valid")
                    $("#nickname").removeClass("is-invalid")
                    $("#nickname").attr("readonly",true)
                    $("#nicknameCheck").attr("disabled",true)
                    $("#gender").focus();
                    isNicknameCheck = 1;
                }
                setTimeout(function () {
                }, 1000)
            },
            error: function (e) {
                console.log("error : "+e)
            }
        })
    }else {
        alert("닉네임을 입력하세요.");
        $("#nickname").focus();
    }
}

$(document).ready(function () {
    signUpTabAjax(1); // 회원가입 창 api
    $('#password-error').hide(); // 비밀번호 에러문구 숨기기
    $('#phone-error').hide(); // 폰번호 에러문구 숨기기
    // 회원가입 폼 null 체크 및 아이디 중복체크 문구 나오게
    const signUpForm = document.querySelectorAll('#signUpForm')
    Array.from(signUpForm).forEach(form => {
        form.addEventListener('submit', event => {
            // event.preventDefault()랑 event.stopPropagation() 는 회원가입 저장이 안되게 막는 기능
            if (!form.checkValidity()) { // input에 값을 안 넣었을 때
                event.preventDefault()
                event.stopPropagation()
            }
            if(isCheck === 0){ // 아이디 중복 체크를 안했을 경우
                alert("아이디 중복체크를 해주세요.")
                event.preventDefault()
                event.stopPropagation()
            }

            if(isEmailCheck === 0){ // 아이디 중복 체크를 안했을 경우
                alert("이메일 중복체크를 해주세요.")
                event.preventDefault()
                event.stopPropagation()
            }

            if(isNicknameCheck ===0){ // 아이디 중복 체크를 안했을 경우
                alert("닉네임 중복체크를 해주세요.")
                event.preventDefault()
                event.stopPropagation()
            }
            form.classList.add('was-validated') // input창에 디자인 넣는곳 에러인지 성공인지
        }, false)
    })
    // 로그인 폼 null 체크 문구 나오게
    const loginForm = document.querySelectorAll('#loginForm')
    Array.from(loginForm).forEach(form => {
        form.addEventListener('submit', event => {
            // event.preventDefault()랑 event.stopPropagation() 는 로그인 실행이 안되게 막는 기능
            if (!form.checkValidity()) { // input에 값을 안 넣었을 때
                event.preventDefault()
                event.stopPropagation()
            }
            form.classList.add('was-validated') // input창에 디자인 넣는곳 에러인지 성공인지
        }, false)
    })
})

// 비밀번호 유효성 체크하는 function
// function passwordCheck(pwd){
//     const regExp = /^(?=.*?[a-zA-Z])(?=.*?[0-9])(?=.*?[~?!@#$%^&*_-]).{8,}$/; // A-Z, a-z, 0-9 특수문자가 포함되어 있는지, 8자 이상
//     if(!regExp.test(pwd)){ // regExp 조건에 맞지 않으면
//         $('#password-error').show(); // 에러문구 보여줘
//     }else { // regExp 조건에 맞으면
//         $('#password-error').hide(); // 에러문구 없애줘
//     }
// }
function passwordCheck(pwd){
    const regExp = /^(?=.*?[a-zA-Z])(?=.*?[0-9])(?=.*?[~?!@#$%^&*_-]).{8,}$/; // A-Z, a-z, 0-9 특수문자가 포함되어 있는지, 8자 이상
    const pw1 = $("#password").val() // 비밀번호 값
    const pw2 = $("#passwordConfirm").val() // 비밀번호 확인 값

    if(pw1 !== pw2){ // 비밀번호가 일치하지 않을때
        $('#password-error').show(); // 비번확인 에러문구 보여
        $('#password-success').hide(); // 비번확인 성공문구 숨겨
        $("#passwordChange").attr("disabled",true) // 버튼 비활성화
        if(!regExp.test(pwd)){ // 비밀번호 유효성 검사 에러면
            $('#password-error2').show(); // 에러문구 보여줘
        }else { // 에러가 아니면
            $('#password-error2').hide(); // 에러문구 없애줘
        }
    } else if(pw1 === "" || pw2 === ""){// 비밀번호칸이 공백일 때
        $('#password-error').show(); // 비번확인 에러문구 보여
        $('#password-success').hide(); // 비번확인 성공문구 숨겨
        $("#passwordChange").attr("disabled",true) // 버튼 비활성화
        if(!regExp.test(pwd)){// 비밀번호 유효성 검사 에러면
            $('#password-error2').show(); // 에러문구 보여줘
        }else {// 에러가 아니면
            $('#password-error2').hide(); // 에러문구 없애줘
        }
    }
    else {// 비밀번호 일치할 때
        $('#password-success').show();// 비번확인 성공문구 보여
        $("#passwordChange").attr("disabled",false) // 버튼 활성화
        if(!regExp.test(pwd)){// 비밀번호 유효성 검사 에러면
            $('#password-error2').show(); // 에러문구 보여줘
            $("#passwordChange").attr("disabled",true) // 버튼 비활성화
        }else {// 에러가 아니면
            $('#password-error').hide();// 비번확인 에러문구 숨겨
            $('#password-error2').hide(); // 에러문구 없애줘
        }
    }
}

// 폰번호 유효성 체크하는 function
function phoneCheck(number){
    const regExp = /^[0-9]{11}$/; // 0-9로 이루어진 11자
    if(!regExp.test(number)){ // regExp 조건에 맞지 않으면
        $('#phone-error').show(); // 에러문구 보여줘  : login.html의 id 가 password-error인  애꺼를 보여줘라!
    }else { // regExp 조건에 맞으면
        $('#phone-error').hide(); // 에러문구 없애줘
    }
}

function signUpTabAjax(type){
    const innerHtml = $("#signUpForm")
    $.ajax({
        url: "/user/signUpAjax/" + type,
        type: 'GET',
        cache: false,
        dataType: "html",
        async: false,
        success: function (data) {
            $('.nav-link').removeClass('active');
            document.getElementById("signUpTab" + type).className += " active"
            $(innerHtml).html(data)
            setTimeout(function () {
            }, 1000)
        },
        error: function (e) {
            $(innerHtml).html("")
        }
    })
}