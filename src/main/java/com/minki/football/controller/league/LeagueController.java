package com.minki.football.controller.league;

import com.minki.football.vo.UserVo;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/league")
public class LeagueController {
    // 리그페이지 리스트 조회
    @GetMapping("/list")
    public String list(Model model) {
        return "league/list";
    }

    // 리그페이지 상세 조회


}
