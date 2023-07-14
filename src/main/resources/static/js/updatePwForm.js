// 비밀번호 일치 여부 및 유효성 검사
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
        $('#password-error').hide();// 비번확인 에러문구 숨겨
        $("#passwordChange").attr("disabled",false) // 버튼 활성화
        if(!regExp.test(pwd)){// 비밀번호 유효성 검사 에러면
            $('#password-error2').show(); // 에러문구 보여줘
            $("#passwordChange").attr("disabled",true) // 버튼 비활성화
        }else {// 에러가 아니면
            $('#password-error2').hide(); // 에러문구 없애줘
        }
    }
}
