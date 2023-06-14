package com.minki.football.dto.team;

import lombok.Data;

@Data
public class PlayerRes {
    private Integer team_id;
    private Integer player_id;
    private String name;
    private Integer height;
    private Integer weight;
    private Integer game_match;
    private Integer playing_time;
    private Integer goal;
    private Integer assist;
    private Integer yellow_card;
    private Integer red_card;
    private Integer mvp;
    private double rating;
    private Integer age;
    private Integer total_point;
    private String nationality;
    private String play_style;
    private String strength;
    private String weakness;
    private Integer back_number;
    private String position;
    private String file_name;
    private Long file_size;
    private byte[] file_bytes;
    private String mime_type;
}
