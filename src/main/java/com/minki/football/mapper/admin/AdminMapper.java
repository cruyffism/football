package com.minki.football.mapper.admin;


import com.minki.football.dto.team.TeamRes;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface AdminMapper {

    // 1. 팀 관리 리스트 조회
    List<TeamRes> list(Integer leagueId);

//     <!--// 수정폼 조회-->
    TeamRes teamInfo(Integer teamId);

//     <!--// 수정 및 저장-->
    Integer teamUpdate(TeamRes);

}
