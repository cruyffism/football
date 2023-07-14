package com.minki.football.service.user;

import com.minki.football.dto.user.UserRes;
import com.minki.football.mapper.user.UserMapper;
import com.minki.football.dto.user.UserReq;
import com.minki.football.dto.user.UserRoleReq;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class UserService {
    @Autowired
    private UserMapper userMapper;

    private final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    //  회원정보조회
    public UserRes getUserById(String username) {

        return userMapper.getUserById(username);
    }

    // 회원정보수정
    public Integer updateUser(UserReq userReq) {

        return userMapper.updateUser(userReq);
    }

    // 회원정보삭제
    public Integer deleteUser(String username) {
        return userMapper.deleteUser(username);
    }

    // 회원권한삭제
    public Integer deleteRole(Integer memberId) {
        return userMapper.deleteRole(memberId);
    }

    // 아이디 중복 체크
    public Integer idCheck(String username) {

        return userMapper.idCheck(username);
    }

    public void signup(UserReq userReq) { // 회원 가입
        if (!userReq.getName().equals("") && !userReq.getUsername().equals("")) {
            // password는 암호화해서 DB에 저장
            userReq.setPassword(passwordEncoder.encode(userReq.getPassword()));
            userMapper.insertUser(userReq); // 회원정보 저장
            UserRoleReq userRoleReq = new UserRoleReq();
            userRoleReq.setMemberId(userReq.getMemberId());
            userRoleReq.setRoleId(1);
            userMapper.insertUserRole(userRoleReq); // 권한정보 저장
        }
    }

    //조건(이름/전번/비번)에 맞는 아이디 출력
    public UserRes findId(UserReq userReq) {

        return userMapper.findId(userReq);
    }

    // 비밀번호 찾기
    public UserRes findPw(UserReq userReq) {

        return userMapper.findPw(userReq);
    }

    public Integer emailCheck(String email) {
        return userMapper.emailCheck(email);
    }
    //비밀번호 업데이트
    public  Integer updatePw(UserRes userRes) {
        return userMapper.updatePw(userRes);
    }

}
