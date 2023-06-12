package com.minki.football.mapper.team;


import com.minki.football.dto.team.PlayerDto;
import com.minki.football.dto.team.TeamDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface TeamMapper {
    List<TeamDto> list(Integer leagueId);

    TeamDto info(Integer teamId, String position);

    PlayerDto playerInfo(Integer playerId);
}

