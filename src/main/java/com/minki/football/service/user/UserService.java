package com.minki.football.service.user;

import com.minki.football.mapper.user.UserMapper;
import com.minki.football.vo.UserVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserMapper userMapper;

    private final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

//    public List<UserVo> getUserList() {
//        return userMapper.getUserList();
//    }

    public UserVo getUserById(String username) {
        return userMapper.getUserById(username);
    }

//    public UserVo getUserByNickname(String nickname) {
//        return userMapper.getUserByNickname(nickname);
//    }

    public void signup(UserVo userVo) { // 회원 가입
        if (!userVo.getName().equals("") && !userVo.getUsername().equals("")) {
            System.out.println("userVo : " + userVo);
            // password는 암호화해서 DB에 저장
            userVo.setPassword(passwordEncoder.encode(userVo.getPassword()));
            System.out.println("new userVo : " + userVo);
            userMapper.insertUser(userVo);
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

    public PasswordEncoder passwordEncoder() {
        return this.passwordEncoder;
    }

}
