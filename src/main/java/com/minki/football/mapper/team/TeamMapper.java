package com.minki.football.mapper.team;


import com.minki.football.dto.team.PlayerRes;
import com.minki.football.dto.team.TeamRes;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface TeamMapper {
    //팀 리스트 조회
    List<TeamRes> list(Integer leagueId);  // 타입 메소드명 매개변수
    // Integer라는 타입을 leagueId로 변수 선언
    //list라는 메소드를 실행한 후 나온 값들을  List<TeamRes>에 넣는다
    // TeamService에서 이 메소도를 호출하면서 leagueId라는 변수를 사용할 예정

    //해당 팀 상세 조회
    TeamRes info(Integer teamId);  // 타입 메소드명 매개변수
    // Integer라는 타입을 teamId로 변수 선언
    //info라는 메소드를 실행한 후 나온 값들을  TeamRes에 넣는다
    // TeamService에서 이 메소도를 호출하면서 teamId라는 변수를 사용할 예정

    //해당 팀 상세 조회
    List<PlayerRes> playerList(@Param("teamId") Integer teamId, @Param("position") String position);  // 타입 메소드명 매개변수
    // Integer라는 타입을 teamId로 변수 선언, String타입을 position으로 변수 선언
    // playerList라는 메소드를 실행한 후 나온 값들을 List<PlayerRes>에 넣는다

    //플레이어 조회
    PlayerRes playerInfo(Integer playerId);  // 타입 메소드명 매개변수
    // Integer라는 타입을 playerId로 변수 선언
    // playerInfo라는 메소드를 실행한 후 나온 값들을 PlayerRes에 넣는다
}

