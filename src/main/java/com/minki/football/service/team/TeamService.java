package com.minki.football.service.team;


import com.minki.football.dto.league.LeagueDto;
import com.minki.football.dto.team.TeamDto;
import com.minki.football.mapper.league.LeagueMapper;
import com.minki.football.mapper.team.TeamMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TeamService {

    @Autowired
    private TeamMapper teamMapper;

    public List<TeamDto> list(Integer leagueId) {

        List<TeamDto> teamList = teamMapper.list(leagueId);

        return teamList;
    }

    public TeamDto info(Integer teamId) {
        TeamDto teamDto = teamMapper.info(teamId);

        return teamDto;
    }
}

