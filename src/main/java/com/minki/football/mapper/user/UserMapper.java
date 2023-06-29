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
    UserReq getUserById(String username); // 회원 정보 가져오기
    // 타입 메소드 매개변수

    Integer updateUser(UserRes userRes); //회원 정보 수정
    // 타입 메소드 매개변수(파라미터타입 변수명지정)
    void insertUserRole(UserRoleReq userRoleReq);
//    void updateUser(UserVo userVo); // 회원 정보 수정
//    void deleteUser(Long id); // 회원 탈퇴
}
