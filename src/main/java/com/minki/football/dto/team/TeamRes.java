package com.minki.football.dto.team;


import lombok.Data;

import java.util.List;

@Data
public class TeamRes {
    private Integer team_id;
    private Integer league_id;
    private Integer game_rank;
    private String team_name;
    private String team_style;
    private Integer game_match;
    private Integer point;
    private Integer win;
    private Integer draw;
    private Integer lose;
    private Integer plus_goal;
    private Integer minus_goal;
    private Integer diff_goal;
    private String logo_image_path;
    private String best_image_path;

    private List<PlayerRes> playerRes;

}
