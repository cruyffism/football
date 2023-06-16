package com.minki.football.service.rank;

import com.minki.football.dto.page.Criteria;
import com.minki.football.dto.team.PlayerRes;
import com.minki.football.dto.team.TeamRes;
import com.minki.football.mapper.rank.RankMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RankService {

    @Autowired
    private RankMapper rankMapper;

    public List<TeamRes> list(Integer leagueId) {

        List<TeamRes> rankList = rankMapper.list(leagueId);

        return rankList;
    }

    public List<PlayerRes> player(Criteria criteria){
        List<PlayerRes> playerRes = rankMapper.rank(criteria);
        return playerRes;
    }

    public Integer selectPlayerTotalCount(Criteria criteria) {
        int cnt = rankMapper.selectPlayerTotalCount(criteria);
        return cnt;
    }

    public List<TeamRes> goal() {
        List<TeamRes> playerGoal = rankMapper.podium("goal");
        return playerGoal;
    }

    public List<TeamRes> assist() {
        List<TeamRes> playerAssist = rankMapper.podium("assist");
        return playerAssist;
    }

    public List<TeamRes> total_point() {
        List<TeamRes> playerTotalPoint = rankMapper.podium("total_point");
        return playerTotalPoint;
    }

    public List<TeamRes> mvp() {
        List<TeamRes> playerMvp = rankMapper.podium("mvp");
        return playerMvp;
    }
}
