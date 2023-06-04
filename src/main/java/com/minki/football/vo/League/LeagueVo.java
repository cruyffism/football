package com.minki.football.vo.League;

import lombok.Data;

@Data
public class LeagueVo {

        private Integer leagueId;		//리그 아이디
        private String leagueName;	//리그 이름
        private String content;	//내용
        private String history;	//리그 역사
        private String competitionMethod;	// 리그 운영 방식
        private String logoImagePath;		//로고 이미지 경로
}
