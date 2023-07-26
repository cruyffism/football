package com.minki.football.mapper.user;

import com.minki.football.dto.user.UserReq;
import com.minki.football.dto.user.UserRes;
import com.minki.football.dto.user.UserRoleReq;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {
    //    List<UserVo> getUserList();  // User 테이블 가져오기
    void insertUser(UserReq userReq); // 회원 가입

    //    UserVo getUserByNickname(String nickname); // 회원 정보 가져오기
    UserRes getUserById(String username); // 회원 정보 가져오기
    // 타입 메소드 매개변수

    Integer updateUser(UserReq userReq); //회원 정보 수정
    // 타입 메소드 매개변수(파라미터타입 변수명지정)

    Integer deleteUser(String username);  //회원 정보 삭제
    Integer deleteRole(Integer memberId);  // 회원권한삭제

    Integer idCheck(String username);               //아이디 중복 체크

    void insertUserRole(UserRoleReq userRoleReq);

    //조건(이름/전번/비번)에 맞는 아이디 출력
    UserRes findId(UserReq userReq);

    // 비밀번호 찾기
    UserRes findPw(UserReq userReq);

    Integer emailCheck(String email);
    //비밀번호 업데이트
    Integer updatePw(UserRes userRes);

    Integer nicknameCheck(String nickname);
}
