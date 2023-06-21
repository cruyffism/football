package com.minki.football.dto.board;

import lombok.Data;

import java.time.LocalDate;

@Data
public class BoardReq {
    private Integer boardId;
    private String title;
    private String content;
    private String nickname;
    private Integer click;
    private LocalDate createDate;
    private LocalDate updateDate;
}
