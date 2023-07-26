// 비밀번호 일치 여부 및 유효성 검사
function passwordCheck(pwd){
    const pw1 = $("#password").val() // 비밀번호 값
    const pw2 = $("#passwordConfirm").val() // 비밀번호 확인 값

    if(pw1 !== pw2){ // 비밀번호가 일치하지 않을때
        $('#password-error').show(); // 비번확인 에러문구 보여
        $('#password-success').hide(); // 비번확인 성공문구 숨겨
        $("#deleteBtn").attr("disabled",true) // 버튼 비활성화
    } else if(pw1 === "" || pw2 === ""){// 비밀번호칸이 공백일 때
        $('#password-error').show(); // 비번확인 에러문구 보여
        $('#password-success').hide(); // 비번확인 성공문구 숨겨
        $("#deleteBtn").attr("disabled",true) // 버튼 비활성화
    }
    else {// 비밀번호 일치할 때
        $('#password-success').show();// 비번확인 성공문구 보여
        $('#password-error').hide();
        $("#deleteBtn").attr("disabled",false) // 버튼 활성화
    }
}
