package com.minki.football.controller.team;

import com.minki.football.dto.league.LeagueDto;
import com.minki.football.dto.team.PlayerDto;
import com.minki.football.dto.team.TeamDto;
import com.minki.football.service.team.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/team")
public class TeamController {

    @Autowired
    private TeamService teamService;

    //팀 리스트 조회
    @GetMapping("/list/{leagueId}")
    public String list(Model model, @PathVariable Integer leagueId) {
        List<TeamDto> teamList = teamService.list(leagueId);

        model.addAttribute("teamList", teamList);
        System.out.println("팀 리스트 : " + teamList);
        return "team/list";
    }

    //팀 리스트 조회
    @GetMapping("/listAjax/{leagueId}")
    public String listAjax(Model model, @PathVariable Integer leagueId) {
        List<TeamDto> teamList = teamService.list(leagueId);

        model.addAttribute("teamList", teamList);
        System.out.println("팀 리스트 : " + teamList);
        return "team/listAjax";
    }

    //해당 팀 상세 조회
    @GetMapping("/info/{teamId}")
    public String info(Model model, @PathVariable Integer teamId, @RequestParam String position) { // @RequestParam url뒤에 ?오는 값의 이름에 맞춰서 값이 들어옴
        TeamDto teamDto = teamService.info(teamId, position);

        model.addAttribute("info", teamDto);
        System.out.println("팀정보 : " + teamDto);
        return "team/info";
    }

    //플레이어 조회
    @GetMapping("/info/player/{playerId}")
    public String playerInfo(Model model, @PathVariable Integer playerId) {
        PlayerDto playerDto = teamService.playerInfo(playerId);

        model.addAttribute("info", playerDto);
       System.out.println("선수정보 : "+ playerDto);
        return "player/info";

    }

}




