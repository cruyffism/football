package com.minki.football.mapper.rank;


import com.minki.football.dto.team.PlayerRes;
import com.minki.football.dto.team.TeamRes;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface RankMapper {
    List<TeamRes> list(Integer leagueId);

    List<PlayerRes> rank();
}
