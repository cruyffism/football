package com.minki.football.controller.league;

import com.minki.football.dto.league.LeagueDto;
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

    @Autowired   //  오토와이어드를 사용해서 LeagueController에다가 LeagueService 연결 >> 메소드를 사용하기 위해서
    private LeagueService leagueService;

    // 리그페이지 리스트 조회
    @GetMapping("/list") // GET(조회), POST(생성, 자장), PUT(수정), DELETE(삭제) 뒤에다가 경로 설정하기! ex) "/list"
    public String list(Model model) { // 접근제한자 리턴값 메소드명(매개변수){}  >> 이게 하나의 메소드이다.
        List<LeagueDto> leagueList = leagueService.list(); // 리그리스트를 조회한 결과 값(우측값)을 leagueList(좌측값)에 담았다.
        // 데이터 타입     변수명       호출메소드

        //System.out.println("leagueDto : " + leagueList);
        model.addAttribute("list",leagueList); // leagueList를 list란 이름으로 프론트엔드 즉 아래에 있는 "league/list" 여기다가 보내줌.

        return "league/list"; // league 폴더안에 list.html로 보낸다.
    }

    // 리그페이지 상세 조회
     @GetMapping("/info/{leagueId}")
    public String info(Model model, @PathVariable Integer leagueId) { // info 경로에서 leagueId를 사용하겠다.
         LeagueDto leagueDto = leagueService.info(leagueId);
         System.out.println("leagueDto22 : " + leagueDto);
         model.addAttribute("info", leagueDto);

         return "league/leagueInfo"; //league 폴더안에 info.html로 보낸다.
     }
}
