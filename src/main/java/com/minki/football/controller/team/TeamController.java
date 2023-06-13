package com.minki.football.controller.team;

import com.minki.football.dto.team.PlayerRes;
import com.minki.football.dto.team.TeamRes;
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
    @GetMapping("/list")
    public String list() {
        return "team/teamList";
    }

    //팀 리스트 조회
    @GetMapping("/listAjax/{leagueId}")
    public String listAjax(Model model, @PathVariable Integer leagueId) {
        List<TeamRes> teamList = teamService.list(leagueId);

        model.addAttribute("teamList", teamList);
        System.out.println("팀 리스트 : " + teamList);
        return "team/teamListAjax";
    }

    //해당 팀 상세 조회
    @GetMapping("/info/{teamId}")
    public String info(Model model, @PathVariable Integer teamId) { // @RequestParam url뒤에 ?오는 값의 이름에 맞춰서 값이 들어옴
        TeamRes teamRes = teamService.info(teamId);

        model.addAttribute("info", teamRes);
        System.out.println("팀정보 : " + teamRes);
        return "team/teamInfo";
    }

    //해당 팀 상세 조회
    @GetMapping("/infoAjax/{teamId}")
    public String infoAjax(Model model, @PathVariable Integer teamId, @RequestParam String position) { // @RequestParam url뒤에 ?오는 값의 이름에 맞춰서 값이 들어옴
        List<PlayerRes> playerList = teamService.playerList(teamId, position);

        model.addAttribute("playerList", playerList);
        System.out.println("팀정보2 : " + playerList);
        return "team/teamInfoAjax";
    }

    //플레이어 조회
    @GetMapping("/playerInfo/{playerId}")
    public String playerInfo(Model model, @PathVariable Integer playerId) {
        PlayerRes playerRes = teamService.playerInfo(playerId);

        model.addAttribute("info", playerRes);
        System.out.println("선수정보 : " + playerRes);
        return "team/playerInfo";

    }

}




