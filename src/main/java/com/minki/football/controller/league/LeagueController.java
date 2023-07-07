package com.minki.football.controller.league;

import com.minki.football.dto.league.LeagueRes;
import com.minki.football.service.league.LeagueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller // 내가 컨트롤러다 라고 알려줄려고 일단 선언.
@RequestMapping("/league") // 각 메소드 path에 /league를 붙여주는 것.
public class LeagueController {

    @Autowired   // 오토와이어드를 사용해서 LeagueController에다가 LeagueService 연결 >> 메소드를 사용하기 위해서
    private LeagueService leagueService;

    // 리그페이지 상세 조회
     @GetMapping("/info/{leagueId}") // info 경로에서 leagueId를 사용하겠다.
    public String info(Model model, @PathVariable Integer leagueId) { // @pathvariable을 통해 leagueId라는 이름으로 저기 위에 path에 있는 {leagueId}를 가져다 쓴다.
                                                                      // 위 경로에  "/info/1" 이렇게 들어오면 매개변수에 쓴 leagueId가 1로 들어가져서 결국 xml에 #{leagueId}가 1로 변한다.
         LeagueRes leagueRes = leagueService.info(leagueId); // leagueService 파일 안에 있는 info(leagueId) 메소드를 호출한 결과값이 LeagueRes 타입의 leagueRes라는 변수명으로 담긴다!
         model.addAttribute("info", leagueRes); // "league/leagueInfo라는 경로에다가  leagueRes를 "info"라는 변수명에 담아서 프론트로 보냄.
         return "league/leagueInfo"; //여기서 리턴값은 프론트엔드로 가는 경로(템플릿 밑에 경로 : templates > league > leagueInfo.html)
         // 즉 모델 어트리뷰트에서 leagueRes를 "info"라는 변수명에 담아서 프론트 엔드로 보내고 그 보낸 값이 리턴값 경로를 타서 프론트엔드 html로 간다!
         // 그리고 html에서 ${info}로 연결해서 사용한다.
     }
}
