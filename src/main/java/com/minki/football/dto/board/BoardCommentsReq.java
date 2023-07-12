package com.minki.football.dto.board;

import lombok.Data;

import java.time.LocalDate;

@Data
public class BoardCommentsReq {
    private Integer boardCommentsId;
    private Integer boardId;
    private String content;
    private String nickname;
    private LocalDate createDate;
    private LocalDate updateDate;
}
