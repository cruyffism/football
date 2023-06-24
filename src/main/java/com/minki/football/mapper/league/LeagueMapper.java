package com.minki.football.mapper.league;

import com.minki.football.dto.league.LeagueRes;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface LeagueMapper {

    //리그리스트 조회
    List<LeagueRes> list();  // 타입   메소드명    매개변수
    //  list라는 메소드를 실행한 후 나온 값들을  List<LeagueRes>에 넣는다

    //리그 상세 조회
    LeagueRes info(Integer leagueId); // 타입   메소드명    매개변수
    //Integer라는 타입을 leagueId로 변수 선언
    // info라는 메소드를 실행한 후 나온 값들을  LeagueRes에 넣는다
}
