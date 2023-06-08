package com.minki.football.mapper.rank;


import com.minki.football.dto.team.TeamDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface RankMapper {
    List<TeamDto> list(Integer leagueId);
}
