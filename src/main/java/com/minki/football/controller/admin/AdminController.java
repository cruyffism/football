package com.minki.football.controller.admin;

import com.minki.football.dto.page.Criteria;
import com.minki.football.dto.page.PageMaker;
import com.minki.football.dto.team.PlayerRes;
import com.minki.football.dto.team.TeamRes;
import com.minki.football.service.admin.AdminService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private AdminService adminService;

    // 1.기본 화면
    @GetMapping("/list")
    public String list() {
        return "admin/list";
    }

    //  팀 관리 리스트 조회
    @GetMapping("/listAjax/{leagueId}")
    public String listAjax(Model model, @PathVariable Integer leagueId) {
        List<TeamRes> list = adminService.list(leagueId);
        model.addAttribute("list", list);
        return "admin/listAjax";
    }

    // 팀 정보 수정폼 조회
    @GetMapping("/teamInfo/{teamId}")
    public String teamInfo(Model model, @PathVariable Integer teamId) {
        TeamRes teamInfo = adminService.teamInfo(teamId);
        model.addAttribute("teamInfo", teamInfo);
        return "admin/teamInfo";
    }

    // 팀 정보 수정
    @PostMapping("/teamUpdate")
    public String teamUpdate(Model model, @ModelAttribute TeamRes teamRes, HttpServletResponse response) throws IOException {
        // 팀정보 수정
        Integer teamUpdate = adminService.teamUpdate(teamRes);
        model.addAttribute("teamUpdate",teamUpdate);
        // 수정 후 alert 창 띄우기
        response.setContentType("text/html; charset=UTF-8");
        PrintWriter writer = response.getWriter();
        writer.println("<script>alert('팀 정보가 수정되었습니다.');</script>");
        writer.flush();
        // 수정이 다 됬으면 팀 관리 리스트 화면으로 되돌아감!
        return "admin/list";
    }

    // 2.플레이어쪽 기본 화면
    @GetMapping("/playerList")
    public String playerList() {
        return "admin/playerList";
    }

    // 선수 관리 리스트 조회
    @GetMapping("/playerListAjax")
    public String playerListAjax(Model model, @ModelAttribute Criteria criteria) {
        List<PlayerRes> playerList = adminService.playerList(criteria);
        PageMaker pageMaker = new PageMaker(); //PageMaker 클래스파일을 사용하기위해 선언한 것 >> 선언해주면 PageMaker 클래스파일의 변수들을 get, set해서 사용가능하다.
        pageMaker.setCriteria(criteria); // criteria라는 변수에다가 우리가 @ModelAttribute를 통해 매개변수로 받은 criteria 값을 저장
        model.addAttribute("PageMaker", pageMaker);
        model.addAttribute("playerList", playerList);
        return"admin/playerListAjax";
    }
}
