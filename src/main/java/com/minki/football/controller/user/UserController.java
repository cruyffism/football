package com.minki.football.controller.user;

import com.minki.football.dto.league.LeagueRes;
import com.minki.football.service.league.LeagueService;
import com.minki.football.service.user.UserService;
import com.minki.football.dto.user.UserReq;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;  //UserService를 userService라고 지정함.

    @Autowired
    private LeagueService leagueService; // LeagueService를  leagueService라고 지정함.

    // 메인 페이지
    @GetMapping("/index") // GET(조회), POST(생성, 자장), PUT(수정), DELETE(삭제) 뒤에다가 경로 설정하기! ex) "/index"
    public String home(Model model) { // 접근제한자 리턴값 메소드명(매개변수){}  >> 이게 하나의 메소드이다.
        List<LeagueRes> leagueList = leagueService.list(); // leagueService.list()라는 메소드를 호출해서  leagueList(좌측값)에 담았다.
        // 데이터 타입     변수명       호출메소드
        model.addAttribute("leagueList", leagueList); //leagueList를 "leagueList"란 변수로 담은 후에 프론트엔드, 즉 아래에 있는 "index" 여기다가 보내줌.

        return "index"; // 리턴값은 프론트엔드로 가는 경로 (템플릿 밑에 경로), index.html로 보낸다.
    }

    // 로그인 페이지
    @GetMapping("/login")
    public String loginPage(
            @RequestParam(value = "error", required = false) String error,
            @RequestParam(value = "exception", required = false) String exception,
                            Model model) {
        model.addAttribute("error", error);
        model.addAttribute("exception", exception);

        System.out.println("error : " + error);
        System.out.println("exception : " + exception);

        return "user/login";
    }

    // 회원가입 페이지
    @GetMapping("/signup")
    public String signupPage() {  // 회원 가입 페이지
        return "user/signup";
    }

    // 회원가입 진행
    @PostMapping("/signup")
    public String signup(UserReq userReq, HttpServletResponse response) throws IOException { // 회원 가입
        try {
            userService.signup(userReq);
        } catch (DuplicateKeyException e) {
            return "redirect:/user/signup?error_code=-1";
        } catch (Exception e) {
            e.printStackTrace();
            return "redirect:/user/signup?error_code=-99";
        }

        response.setContentType("text/html; charset=UTF-8");
        PrintWriter writer = response.getWriter();
        writer.println("<script>alert('회원가입되었습니다.');</script>");
        writer.flush();

        return "user/login";
    }

    //    @GetMapping("/userList")
//    public String getUserList(Model model) { // User 테이블의 전체 정보를 보여줌
//        List<UserVo> userList = userService.getUserList();
//        model.addAttribute("list", userList);
//        return "userListPage";
//    }

//    @GetMapping("/update")
//    public String editPage(Model model) { // 회원 정보 수정 페이지
//        Long id = (Long) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//        UserVo userVo = userService.getUserById(id);
//        model.addAttribute("user", userVo);
//        return "editPage";
//    }
//
//    @PostMapping("/update")
//    public String edit(UserVo userVo) { // 회원 정보 수정
//        Long id = (Long) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//        userVo.setId(id);
//        userService.edit(userVo);
//        return "redirect:/";
//    }
//
//    @PostMapping("/delete")
//    public String withdraw(HttpSession session) { // 회원 탈퇴
//        Long id = (Long) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//        if (id != null) {
//            userService.withdraw(id);
//        }
//        SecurityContextHolder.clearContext(); // SecurityContextHolder에 남아있는 token 삭제
//        return "redirect:/";
//    }
}
