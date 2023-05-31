package com.minki.football.mapper;

import com.minki.football.vo.UserVo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserMapper {
    List<UserVo> getUserList();  // User 테이블 가져오기
    void insertUser(UserVo userVo); // 회원 가입
//    UserVo getUserByNickname(String nickname); // 회원 정보 가져오기
    UserVo getUserById(String id);
    void updateUser(UserVo userVo); // 회원 정보 수정
    void deleteUser(Long id); // 회원 탈퇴
}
