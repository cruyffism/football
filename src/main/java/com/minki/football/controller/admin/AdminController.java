package com.minki.football.controller.admin;

import com.minki.football.dto.team.TeamRes;
import com.minki.football.service.admin.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

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

    // 2. 팀 관리 리스트 조회
    @GetMapping(" /listAjax/{leagueId} ")
    public String listAjax(Model model, @PathVariable Integer leagueId) {
        List<TeamRes> list = adminService.list(leagueId);
        model.addAttribute("list", list);
        return "admin/listAjax";
    }
}
