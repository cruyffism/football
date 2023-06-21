package com.minki.football.dto.board;

import lombok.Data;

import java.time.LocalDate;

@Data
public class BoardRes {
    private Integer board_id;
    private String title;
    private String content;
    private String nickname;
    private Integer click;
    private LocalDate create_date;
    private LocalDate update_date;
}
