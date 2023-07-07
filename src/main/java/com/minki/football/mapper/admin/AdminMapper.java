package com.minki.football.mapper.admin;


import com.minki.football.dto.page.Criteria;
import com.minki.football.dto.team.PlayerRes;
import com.minki.football.dto.team.TeamRes;
import com.minki.football.dto.user.UserRes;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface AdminMapper {

    // 1. 팀 관리 리스트 조회
    List<TeamRes> teamList(Integer leagueId);

    // 팀 정보 수정폼 조회
    TeamRes teamInfo(Integer teamId);

    // 팀 정보 수정
    Integer teamUpdate(TeamRes teamRes); //

    // <!-- //선수 관리 리스트 조회-->
    List<PlayerRes> playerList(Criteria criteria); //리절트타입 id(매개변수)

    // <!--//전체 플레이어수 조회-->
    Integer playerCount(Criteria criteria);

    //<!--// 플레이어 수정폼 조회-->
    PlayerRes playerInfo(Integer playerId);

    // 플레이어 정보 수정 및 저장
    Integer playerUpdate(PlayerRes playerRes);

    //<!--// 회원 정보 조회-->
    List<UserRes> memberList(Criteria criteria);

    // <!--//전체 회원수 조회-->
    Integer memberCount(Criteria criteria);
}
