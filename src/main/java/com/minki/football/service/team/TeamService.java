package com.minki.football.service.team;


import com.minki.football.dto.team.PlayerRes;
import com.minki.football.dto.team.TeamRes;
import com.minki.football.mapper.team.TeamMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TeamService {

    @Autowired
    private TeamMapper teamMapper;

    //팀 리스트 조회
    public List<TeamRes> list(Integer leagueId) { //  접근제한자 리턴값 변수명(){}, TeamService 파일 안에다가 list(Integer leagueId)라는 메소드를 만듦
        List<TeamRes> teamList = teamMapper.list(leagueId);
        // 타입    변수명    = teamMapper.list(leagueId)를 호출한 결과 ; >> teamMapper에있는 list(leagueId)를 호출한 결과를 왼쪽 변수명 teamList에 담은것
        return teamList; // 그리고 이 결과값을 리턴
    }

    public TeamRes info(Integer teamId) { //  접근제한자 리턴값 변수명(){}, TeamService 파일 안에다가 info(Integer teamId)라는 메소드를 만듦
        TeamRes teamRes = teamMapper.info(teamId); //teamMapper에있는 info(teamId)를 호출한 결과를 왼쪽 변수명 teamRes에 담은것
        return teamRes; // 그리고 이 결과값을 리턴
    }

    public List<PlayerRes> playerList(Integer teamId, String position) { //  접근제한자 리턴값 변수명(){}, TeamService 파일 안에다가 playerList(Integer teamId, String position)라는 메소드를 만듦
        List<PlayerRes> playerList = teamMapper.playerList(teamId, position); //teamMapper에있는 playerList(teamId, position)를 호출한 결과를 왼쪽 변수명 playerList에 담은것
        return playerList; // 그리고 이 결과값을 리턴
    }

    public PlayerRes playerInfo(Integer playerId) { //  접근제한자 리턴값 변수명(){}, TeamService 파일 안에다가 playerInfo(Integer playerId)라는 메소드를 만듦
        PlayerRes playerRes = teamMapper.playerInfo(playerId); //teamMapper에있는 playerInfo(playerId)를 호출한 결과를 왼쪽 변수명 playerRes에 담은것
        return playerRes; // 그리고 이 결과값을 리턴
    }

}

