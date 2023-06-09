package com.minki.football.controller.rank;

import com.minki.football.dto.league.LeagueDto;
import com.minki.football.dto.team.PlayerDto;
import com.minki.football.dto.team.TeamDto;
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
        List<TeamDto> rankList = rankService.list(leagueId);

        model.addAttribute("list",rankList);
        System.out.println("순위정보 : " + rankList);
        return "rank/list";
    }


    //선수 순위 조회
    @GetMapping("/player")
    public String info(Model model) {
        List<PlayerDto> playerDto = rankService.player();

        model.addAttribute("player", playerDto);
        System.out.println("선수순위 : " + playerDto);
        return "rank/player"; //league 폴더안에 info.html로 보낸다.
    }
}
