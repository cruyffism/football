package com.minki.football.dto.board;

import lombok.Data;

import java.time.LocalDate;

@Data
public class BoardCommentsRes {
    private Integer board_comments_id;
    private Integer board_id;
    private String content;
    private String nickname;
    private LocalDate create_date;
    private LocalDate update_date;
}
