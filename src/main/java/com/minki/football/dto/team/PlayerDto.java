package com.minki.football.dto.team;

import lombok.Data;

@Data
public class PlayerDto {
   private Integer team_id;
    private Integer  player_id;
    private String name;
    private Integer back_number;
    private String position;
    private String file_name;
    private Long file_size;
    private byte[] file_bytes;
    private String mime_type;
}
