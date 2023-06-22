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
    private TeamService teamService; // TeamService를  teamService라고 지정함.

    //팀 리스트 조회 >>
    @GetMapping("/list") // 우리가 임의로 지정한 경로
    public String list() { //접근제한자 리턴값 메소드명 >> 이게 하나의 메소드다.
        return "team/teamList"; // 여기서 리턴값은 프론트엔드로 가는 경로 (템플릿 밑에 경로)
    }

    //팀 리스트 조회 >>
    @GetMapping("/listAjax/{leagueId}") // 우리가 임의로 지정한 경로
    public String listAjax(Model model, @PathVariable Integer leagueId) { // @pathvariable을 통해 leagueId라는 이름으로 저기 위에 path에 있는 {leagueId}를 가져다 쓴다
        // 위 경로에 "/info/1" 이렇게 들어오면 매개변수에 쓴 leagueId가 1로 들어가져서 결국 xml에  #{leagueId}가 1로 변한다.
        List<TeamRes> teamList = teamService.list(leagueId); // teamService 파일 안에 있는 list(leagueId) 메소드를 호출한 결과값이 List<TeamRes> 타입의 teamList라는 변수명으로 담긴다!

        model.addAttribute("teamList", teamList); // "team/teamListAjax"라는 경로에다가 teamList를 "teamList"라는 변수명에 담아서 프론트로 보냄
        System.out.println("팀 리스트 : " + teamList);
        return "team/teamListAjax"; // 여기서 리턴값은 프론트엔드로 가는 경로 (템플릿 밑에 경로)
        // 즉 모델 어트리뷰트에서 teamList를 "teamList"라는 변수명에 담아서 프론트 엔드로 보내고 그 보낸 값이 리턴값 경로를 타서 프론트엔드 html로 간다!
        // 그리고 html에서 ${teamList}로 연결해서 사용한다.
    }


    //해당 팀 상세 조회
    @GetMapping("/info/{teamId}")       // @RequestParam url뒤에 ?오는 값의 이름에 맞춰서 값이 들어옴
    public String info(Model model, @PathVariable Integer teamId) { // @pathvariable을 통해 teamId라는 이름으로 저기 위에 path에 있는 {teamId}를 가져다 쓴다
        // 위 경로에 "/info/1" 이렇게 들어오면 매개변수에 쓴 teamId가 1로 들어가져서 결국 xml에  #{teamId}가 1로 변한다.
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




