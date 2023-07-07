package com.minki.football.service.admin;


import com.minki.football.dto.page.Criteria;
import com.minki.football.dto.team.PlayerRes;
import com.minki.football.dto.team.TeamRes;
import com.minki.football.dto.user.UserRes;
import com.minki.football.mapper.admin.AdminMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminService {

    @Autowired
    private AdminMapper adminMapper;


    // 팀 관리 리스트 조회
    public List<TeamRes> teamList(Integer leagueId) {
        List<TeamRes> list = adminMapper.teamList(leagueId);
        return list;
    }

    // 팀 정보 수정폼 조회
    public TeamRes teamInfo(Integer teamId) {
        TeamRes teamInfo = adminMapper.teamInfo(teamId);
        return teamInfo;
    }

    // 팀 정보 수정
    public Integer teamUpdate(TeamRes teamRes) {
        Integer teamUpdate = adminMapper.teamUpdate(teamRes);
        return teamUpdate;
    }

    //  선수 관리 리스트 조회
    public List<PlayerRes> playerList(Criteria criteria) {
        List<PlayerRes> playerList = adminMapper.playerList(criteria);
        return playerList;
    }

    //전체 플레이어수 조회
    public Integer playerCount(Criteria criteria) {
        Integer playerCount = adminMapper.playerCount(criteria);
        return playerCount;
    }

    //플레이어 수정폼 조회
    public PlayerRes playerInfo(Integer playerId) {
        PlayerRes playerInfo = adminMapper.playerInfo(playerId);
        return playerInfo;
    }

    //플레이어 정보 수정 및 저장
    public Integer playerUpdate(PlayerRes playerRes) {
        Integer playerUpdate = adminMapper.playerUpdate(playerRes);
        return playerUpdate;
    }

    // 회원 정보 조회
    public List<UserRes> memberList(Criteria criteria) {
        List<UserRes> memberList = adminMapper.memberList(criteria);
        return memberList;
    }

    //전체 회원수 조회
    public Integer memberCount(Criteria criteria) {
        Integer memberCount = adminMapper.memberCount(criteria);
        return memberCount;
    }
}
