package com.minki.football.dto.user;

import lombok.Data;

@Data
public class UserRes {
    private Integer member_id;
    private String username;
    private String name;
    private String nickname;
    private String password;
    private String birthday;
    private String gender;
    private String phone;


}
