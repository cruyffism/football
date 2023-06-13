package com.minki.football.controller.rank;

import com.minki.football.dto.team.PlayerRes;
import com.minki.football.dto.team.TeamRes;
import com.minki.football.service.rank.RankService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/rank")
public class RankController {

    @Autowired
    private RankService rankService;

    //팀 순위 조회
    @GetMapping("/team/{leagueId}")
    public String list(Model model, @PathVariable Integer leagueId) {
        List<TeamRes> rankList = rankService.list(leagueId);

        model.addAttribute("list",rankList);
        System.out.println("순위정보 : " + rankList);
        return "rank/teamAjax";
    }


    //선수 순위 조회
    @GetMapping("/player")
    public String info(Model model) {
        List<PlayerRes> playerRes = rankService.player();

        model.addAttribute("player", playerRes);
        System.out.println("선수순위 : " + playerRes);
        return "rank/player"; //league 폴더안에 info.html로 보낸다.
    }

    //랭크페이지 가기
    @GetMapping
    public String rank(Model model) {
        System.out.println("순위페이지" );
        return "rank/playerAjax";
    }
}
