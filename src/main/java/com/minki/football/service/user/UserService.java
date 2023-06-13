package com.minki.football.service.user;

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

//    public List<UserVo> getUserList() {
//        return userMapper.getUserList();
//    }

    public UserReq getUserById(String username) {
        return userMapper.getUserById(username);
    }

//    public UserVo getUserByNickname(String nickname) {
//        return userMapper.getUserByNickname(nickname);
//    }

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

//    public void edit(UserVo userVo) { // 회원 정보 수정
//        // password는 암호화해서 DB에 저장
//        userVo.setPassword(passwordEncoder.encode(userVo.getPassword()));
//        userMapper.updateUser(userVo);
//    }
//
//    public void withdraw(Long id) { // 회원 탈퇴
//        userMapper.deleteUser(id);
//    }

}
