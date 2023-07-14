package com.minki.football.dto.user;

import lombok.Data;

@Data
public class UserReq {
    private Integer memberId;
    private String username;
    private String name;
    private String nickname;
    private String password;
    private String birthday;
    private String gender;
    private String phone;
    private String email;
    private Integer type;

}
