package com.minki.football.dto.league;

import lombok.Data;

@Data // get set을 자동으로 만들어줘서 변수만 선언해주며 된다!
public class LeagueDto {
    private Integer league_id;		//리그 아이디
    private String league_name;	//리그 이름
    private String content;	//내용
    private String history;	//리그 역사
    private String competition_method;	// 리그 운영 방식
    private String logo_image_path;		//로고 이미지 경로
    private String info_image_path;		//로고 이미지 경로
}
