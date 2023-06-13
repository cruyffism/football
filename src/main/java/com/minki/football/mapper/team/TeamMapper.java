package com.minki.football.mapper.team;


import com.minki.football.dto.team.PlayerRes;
import com.minki.football.dto.team.TeamRes;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface TeamMapper {
    List<TeamRes> list(Integer leagueId);

    TeamRes info(Integer teamId, String position);

    PlayerRes playerInfo(Integer playerId);
}

