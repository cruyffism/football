package com.minki.football.mapper.team;


import com.minki.football.dto.team.PlayerRes;
import com.minki.football.dto.team.TeamRes;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface TeamMapper {
    //팀 리스트 조회
    List<TeamRes> list(Integer leagueId); //xml과 연결하려는 메소드(인터페이스)를 하나 만들었음.
     // 타입 메소드명 매개변수

    TeamRes info(Integer teamId); //xml과 연결하려는 메소드(인터페이스)를 하나 만들었음.
    
    List<PlayerRes> playerList(Integer teamId, String position);

    PlayerRes playerInfo(Integer playerId);
}

