package com.minki.football.controller.admin;

import com.minki.football.dto.page.Criteria;
import com.minki.football.dto.page.PageMaker;
import com.minki.football.dto.team.PlayerRes;
import com.minki.football.dto.team.TeamRes;
import com.minki.football.dto.user.UserRes;
import com.minki.football.service.admin.AdminService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private AdminService adminService;

    // 팀 관리 리스트 기본 화면
    @GetMapping("/teamList")
    public String teamList() {
        return "admin/teamList";
    }

    //  팀 관리 리스트 조회
    @GetMapping("/teamListAjax/{leagueId}")
    public String teamListAjax(Model model, @PathVariable Integer leagueId) {
        List<TeamRes> list = adminService.teamList(leagueId);
        model.addAttribute("list", list);
        return "admin/teamListAjax";
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
        model.addAttribute("teamUpdate", teamUpdate);
        // 수정 후 alert 창 띄우기
        response.setContentType("text/html; charset=UTF-8");
        PrintWriter writer = response.getWriter();
        writer.println("<script>alert('팀 정보가 수정되었습니다.');</script>");
        writer.flush();
        // 수정이 다 됬으면 팀 관리 리스트 화면으로 되돌아감!
        return "admin/teamList";
    }

    // 이미지 조회 >> 플레이어리스트 화면에 선수 이미지를 전부 뿌려주는것
    @GetMapping("/playerImage/{playerId}")
    @ResponseBody
    public ResponseEntity<byte[]> getLogoImage(@PathVariable Integer playerId) {
        PlayerRes playerInfo = adminService.playerInfo(playerId);
        byte[] imageContent = playerInfo.getFile_bytes();
        MediaType mediaType = MediaType.valueOf(playerInfo.getMime_type());
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(mediaType);
        return new ResponseEntity<byte[]>(imageContent, headers, HttpStatus.OK);
    }

    // 2.플레이어쪽 기본 화면
    @GetMapping("/playerList")
    public String playerList() {
        return "admin/playerList";
    }

    // 선수 관리 리스트 조회 and 전체 플레이어수 조회
    @GetMapping("/playerListAjax")
    public String playerListAjax(Model model, @ModelAttribute Criteria criteria) {
        List<PlayerRes> playerList = adminService.playerList(criteria);
        PageMaker pageMaker = new PageMaker(); //PageMaker 클래스파일을 사용하기위해 선언한 것 >> 선언해주면 PageMaker 클래스파일의 변수들을 get, set해서 사용가능하다.
        pageMaker.setCriteria(criteria); // criteria라는 변수에다가 우리가 @ModelAttribute를 통해 매개변수로 받은 criteria 값을 저장
        pageMaker.setTotalCount(adminService.playerCount(criteria));
        model.addAttribute("PageMaker", pageMaker);
        model.addAttribute("playerList", playerList);
        return "admin/playerListAjax";
    }

    // 플레이어 수정폼 조회
    @GetMapping("/playerInfo/{playerId}")
    public String playerInfo(Model model, @PathVariable Integer playerId) {
        PlayerRes playerInfo = adminService.playerInfo(playerId);
        model.addAttribute("playerInfo", playerInfo);
        return "admin/playerInfo";
    }

    //플레이어 정보 수정 및 저장
    @PostMapping("/playerUpdate")
    public String playerUpdate(Model model, @RequestParam(value = "image", required = false) MultipartFile img,
                               @RequestParam(value = "player_id", required = false) Integer playerId,
                               @RequestParam(value = "name", required = false) String name,
                               @RequestParam(value = "height", required = false) Integer height,
                               @RequestParam(value = "weight", required = false) Integer weight,
                               @RequestParam(value = "age", required = false) Integer age,
                               @RequestParam(value = "position", required = false) String position,
                               @RequestParam(value = "game_match", required = false) Integer gameMatch,
                               @RequestParam(value = "playing_time", required = false) Integer playingTime,
                               @RequestParam(value = "goal", required = false) Integer goal,
                               @RequestParam(value = "assist", required = false) Integer assist,
                               @RequestParam(value = "total_point", required = false) Integer totalPoint,
                               @RequestParam(value = "yellow_card", required = false) Integer yellowCard,
                               @RequestParam(value = "red_card", required = false) Integer redCard,
                               @RequestParam(value = "rating", required = false) double rating,
                               @RequestParam(value = "mvp", required = false) Integer mvp,
                               @RequestParam(value = "nationality", required = false) String nationality,
                               @RequestParam(value = "play_style", required = false) String playStyle,
                               @RequestParam(value = "strength", required = false) String strength,
                               @RequestParam(value = "weakness", required = false) String weakness,
                               @RequestParam(value = "back_number", required = false) Integer backNumber
            , HttpServletResponse response) throws IOException {
        PlayerRes playerRes = new PlayerRes();
        playerRes.setPlayer_id(playerId);
        playerRes.setName(name);
        playerRes.setHeight(height);
        playerRes.setWeight(weight);
        playerRes.setAge(age);
        playerRes.setPosition(position);
        playerRes.setGame_match(gameMatch);
        playerRes.setPlaying_time(playingTime);
        playerRes.setGoal(goal);
        playerRes.setAssist(assist);
        playerRes.setTotal_point(totalPoint);
        playerRes.setYellow_card(yellowCard);
        playerRes.setRed_card(redCard);
        playerRes.setRating(rating);
        playerRes.setMvp(mvp);
        playerRes.setNationality(nationality);
        playerRes.setPlay_style(playStyle);
        playerRes.setStrength(strength);
        playerRes.setWeakness(weakness);
        playerRes.setBack_number(backNumber);

        if (img != null && !img.isEmpty()) {
            System.out.println("name : " + img.getName());
            playerRes.setFile_bytes(img.getBytes());
            playerRes.setFile_name(img.getOriginalFilename());
            playerRes.setFile_size(img.getSize());
            playerRes.setMime_type(img.getContentType());
        }

        // 선수 정보 수정
        Integer playerUpdate = adminService.playerUpdate(playerRes);
        model.addAttribute("playerUpdate", playerUpdate);
        // 수정 후 alert 창 띄우기
        response.setContentType("text/html; charset=UTF-8");
        PrintWriter writer = response.getWriter();
        writer.println("<script>alert('선수 정보가 수정되었습니다.');</script>");
        writer.flush();
        // 수정이 다 됬으면 선수 관리 리스트 화면으로!
        return "admin/playerList";
    }

    // 회원 정보 기본 화면
    @GetMapping("/memberList")
    public String memberList() {
        return "admin/memberList";
    }


    // <!--// 회원 정보 리스트 조회 and 전체 회원수 조회-->
    @GetMapping("/memberListAjax")
    public String memberList(Model model, @ModelAttribute Criteria criteria) {
        List<UserRes> memberList = adminService.memberList(criteria);
        PageMaker pageMaker = new PageMaker(); //PageMaker 클래스파일을 사용하기위해 선언한 것 >> 선언해주면 PageMaker 클래스파일의 변수들을 get, set해서 사용가능하다.
        pageMaker.setCriteria(criteria); // criteria라는 변수에다가 우리가 @ModelAttribute를 통해 매개변수로 받은 criteria 값을 저장
        pageMaker.setTotalCount(adminService.memberCount(criteria));
        model.addAttribute("PageMaker", pageMaker);
        model.addAttribute("memberList", memberList);
        return "admin/memberListAjax";
    }

}
