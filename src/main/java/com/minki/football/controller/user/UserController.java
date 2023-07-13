package com.minki.football.controller.user;

import com.minki.football.dto.league.LeagueRes;
import com.minki.football.dto.user.UserRes;
import com.minki.football.service.league.LeagueService;
import com.minki.football.service.user.UserService;
import com.minki.football.dto.user.UserReq;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;  //UserService를 userService라고 지정함.

    @Autowired
    private LeagueService leagueService; // LeagueService를  leagueService라고 지정함.

    private final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    // 메인 페이지
    @GetMapping("/index") // GET(조회), POST(생성, 자장), PUT(수정), DELETE(삭제) 뒤에다가 경로 설정하기! ex) "/index"
    public String home(Model model) { // 접근제한자 리턴값 메소드명(매개변수){}  >> 이게 하나의 메소드이다.
        List<LeagueRes> leagueList = leagueService.list(); // leagueService.list()라는 메소드를 호출해서  leagueList(좌측값)에 담았다.
        // 데이터 타입     변수명       호출메소드
        Authentication auth = SecurityContextHolder.getContext().getAuthentication(); // 백엔드에서  글 저장하려할ㅈ때 로그인 정보 가져와서 아이디 값을 디비에 넣어주는거!
        String role = auth.getAuthorities().toString();
        model.addAttribute("leagueList", leagueList); //leagueList를 "leagueList"란 변수로 담은 후에 프론트엔드, 즉 아래에 있는 "index" 여기다가 보내줌.
        model.addAttribute("role", role); //leagueList를 "leagueList"란 변수로 담은 후에 프론트엔드, 즉 아래에 있는 "index" 여기다가 보내줌.

        return "index"; // 리턴값은 프론트엔드로 가는 경로 (템플릿 밑에 경로), index.html로 보낸다.
    }

    // 로그인 페이지
    @GetMapping("/login")
    public String loginPage(@RequestParam(value = "error", required = false) String error,
                            @RequestParam(value = "exception", required = false) String exception, Model model) {
        model.addAttribute("error", error);
        model.addAttribute("exception", exception);

        return "index";
    }

//    // 회원가입 페이지
//    @GetMapping("/signup")
//    public String signupPage() {  // 회원 가입 페이지
//        return "user/signup";
//    }

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

        return "index";
    }

    //회원정보 조회
    @GetMapping("/info")  //백엔드 경로
    public String getUserById(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication(); // 백엔드에서  글 저장하려할ㅈ때 로그인 정보 가져와서 아이디 값을 디비에 넣어주는거!
        String username = auth.getName();
        UserReq userReq = userService.getUserById(username);
        model.addAttribute("info", userReq);
        return "user/mypage"; //프론트 경로

    }

    //회원정보 수정폼
    @GetMapping("/updateInfoForm")
    public String updateInfoForm(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication(); // 백엔드에서  글 저장하려할ㅈ때 로그인 정보 가져와서 아이디 값을 디비에 넣어주는거!
        String username = auth.getName();
        UserReq userReq = userService.getUserById(username);
        model.addAttribute("info", userReq);
        return "user/updateInfoForm";
    }

    //회원정보 수정
    @PostMapping("/updateInfo") //백엔드 경로
    public String updateUser(Model model, @ModelAttribute UserRes userRes) {
        userRes.setPassword(passwordEncoder.encode(userRes.getPassword()));
        //@ModelAttribute에서 받은 userRes에서 담겨온 패스워드를 암호화해서 다시 패스워드라는 변수에 넣어서 저장
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        // 백엔드에서  글 저장하려할ㅈ때 로그인 정보 가져와서 아이디 값을 디비에 넣어주는거!
        String username = auth.getName(); // auth애서 아이디값만 가져와서 좌측 username에 넣어준거
        Integer result = userService.updateUser(userRes);
        UserReq userReq = userService.getUserById(username);
        model.addAttribute("info", userReq);
        return "user/mypage"; //프론트 경로
    }

    //회원정보 삭제
    @GetMapping("/deleteInfo") // 조회,삭제가 get
    public String deleteUser(Model model, @RequestParam String username, HttpSession session) throws IOException {
        Integer deleteUser = userService.deleteUser(username);
        model.addAttribute("deleteUser", username);
        session.invalidate();

        return "redirect:/";
    }

    // 아이디 중복 체크
    @GetMapping("/idCheckAjax")
    @ResponseBody  // 리스폰스바디와 json은 한 쌍 !! dataType:"json" >> 이쪽으로 결과를 리턴해주는 역할을 하므로 밑에 return에 프론트 경로 안써준다!
    public Integer idCheck(@RequestParam String username) {
        int cnt = userService.idCheck(username);
        return cnt;   // cnt라는 이름으로 결과값을 ajax로 보냈다!
    }

    // 아이디 찾기 폼
    @GetMapping("/findIdForm")
    public String findIdForm() {
        return "user/findIdForm";
    }

    // 비번 찾기 폼
    @GetMapping("/findPwForm")
    public String findPwForm() {
        return "user/findPwForm";
    }

    //조건(이름/전번/비번)에 맞는 아이디 출력
    @PostMapping("/findId")
    public String findId(Model model, @ModelAttribute UserReq userReq, HttpServletResponse response) throws IOException {
        UserRes userRes = userService.findId(userReq);
        if(userRes == null){
            response.setContentType("text/html; charset=UTF-8");
            PrintWriter writer = response.getWriter();
            writer.println("<script>alert('해당정보가 존재하지 않습니다.');</script>");
            writer.flush();
            return "user/findIdForm";
        }
        model.addAttribute("id", userRes);
        return "user/findId";
    }

    // 비밀번호 찾기
    @PostMapping("/findPw")
    public String findPw(Model model, @ModelAttribute UserReq userReq, HttpServletResponse response) throws IOException {
        UserRes userRes = userService.findPw(userReq);
        if(userRes == null){
            response.setContentType("text/html; charset=UTF-8");
            PrintWriter writer = response.getWriter();
            writer.println("<script>alert('해당정보가 존재하지 않습니다.');</script>");
            writer.flush();
            return "user/findPwForm";
        }
        response.setContentType("text/html; charset=UTF-8");
        PrintWriter writer = response.getWriter();
        writer.println("<script>alert('가입 된 이메일로 임시 비밀번호가 전송되었습니다.');</script>");
        writer.flush();
        return "index";
    }
}
