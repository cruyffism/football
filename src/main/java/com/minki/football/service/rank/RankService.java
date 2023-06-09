package com.minki.football.service.rank;

import com.minki.football.dto.team.PlayerDto;
import com.minki.football.dto.team.TeamDto;
import com.minki.football.mapper.rank.RankMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RankService {

    @Autowired
    private RankMapper rankMapper;

    public List<TeamDto> list(Integer leagueId) {

        List<TeamDto> rankList = rankMapper.list(leagueId);

        return rankList;
    }

    public List<PlayerDto> player(){
        List<PlayerDto> playerDto = rankMapper.rank();
        return playerDto;
    }
}
