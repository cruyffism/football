package com.minki.football.mapper.league;

import com.minki.football.dto.league.LeagueRes;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface LeagueMapper {

    //리그리스트 조회
    List<LeagueRes> list(); //xml과 연결하려는 메소드(인터페이스)를 하나 만들었음. 메소드 괄호 안에 원래 메개변수를 넣을 수 있지만 현재 변수를 사용 안해서 안 넣음.
                            // 타입 매소드명

    //리그 상세 조회
    LeagueRes info(Integer leagueId); // 타입   메소드명    매개변수
}
