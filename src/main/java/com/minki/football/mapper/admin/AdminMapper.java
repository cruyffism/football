package com.minki.football.mapper.admin;


import com.minki.football.dto.team.TeamRes;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface AdminMapper {

    // 1. 팀 관리 리스트 조회
    List<TeamRes> list(Integer leagueId);

    // 팀 정보 수정폼 조회
    TeamRes teamInfo(Integer teamId);

    // 팀 정보 수정
    Integer teamUpdate(TeamRes teamRes); // 리턴타입 메소드이름(파라메터타입 변수명);

}
