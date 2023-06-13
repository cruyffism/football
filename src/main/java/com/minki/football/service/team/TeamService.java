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

    public List<TeamRes> list(Integer leagueId) {

        List<TeamRes> teamList = teamMapper.list(leagueId);

        return teamList;
    }

    public TeamRes info(Integer teamId) {
        TeamRes teamRes = teamMapper.info(teamId);

        return teamRes;
    }

    public List<PlayerRes> playerList(Integer teamId, String position) {
        List<PlayerRes> playerList = teamMapper.playerList(teamId, position);

        return playerList;
    }

    public PlayerRes playerInfo(Integer playerId) {
        PlayerRes playerRes = teamMapper.playerInfo(playerId);


        return playerRes;
    }


}

