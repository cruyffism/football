package com.minki.football.controller.user;

import com.minki.football.dto.league.LeagueRes;
import com.minki.football.dto.user.UserRes;
import com.minki.football.service.league.LeagueService;
import com.minki.football.service.user.UserService;
import com.minki.football.dto.user.UserReq;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
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

    private final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Autowired
    private JavaMailSender javaMailSender;

    @Value("${spring.mail.username}")
    private String from;

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

    // 회원가입 페이지
    @GetMapping("/signUpAjax/{type}")
    public String signupAjax(Model model, @PathVariable Integer type) {  // 회원 가입 페이지
        if (type == 1) {
            model.addAttribute("type", type);
            return "user/userSignUpAjax";
        } else {
            model.addAttribute("type", type);
            return "user/adminSignUpAjax";
        }

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

        return "index";
    }

    //회원정보 조회
    @GetMapping("/info")  //백엔드 경로
    public String getUserById(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication(); // 백엔드에서  글 저장하려할ㅈ때 로그인 정보 가져와서 아이디 값을 디비에 넣어주는거!
        String username = auth.getName();
        UserRes userRes = userService.getUserById(username);
        model.addAttribute("info", userRes);
        return "user/mypage"; //프론트 경로

    }

    //회원정보 수정폼
    @GetMapping("/updateInfoForm")
    public String updateInfoForm(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication(); // 백엔드에서  글 저장하려할ㅈ때 로그인 정보 가져와서 아이디 값을 디비에 넣어주는거!
        String username = auth.getName();
        UserRes userRes = userService.getUserById(username);
        model.addAttribute("info", userRes);
        return "user/updateInfoForm";
    }

    //회원정보 수정
    @PostMapping("/updateInfo") //백엔드 경로
    public String updateUser(Model model, @ModelAttribute UserReq userReq, HttpServletResponse response) throws IOException {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        // 백엔드에서  글 저장하려할ㅈ때 로그인 정보 가져와서 아이디 값을 디비에 넣어주는거!
        String username = auth.getName(); // auth애서 아이디값만 가져와서 좌측 username에 넣어준거
        userReq.setUsername(username);
        Integer result = userService.updateUser(userReq);
        UserRes userRes = userService.getUserById(username);
        model.addAttribute("info", userRes);

        response.setContentType("text/html; charset=UTF-8");
        PrintWriter writer = response.getWriter();
        writer.println("<script>alert('정보가 수정되었습니다.');</script>");
        writer.flush();

        return "user/mypage"; //프론트 경로
    }


    //회원정보 삭제폼
    @GetMapping("/deleteInfoForm") // 조회,삭제가 get
    public String deleteUserForm() {
        return "user/deleteForm";
    }

    //회원정보 삭제
    @PostMapping("/deleteInfo") // 조회,삭제가 get
    public String deleteUser(Model model, @ModelAttribute UserReq userReq, HttpSession session, HttpServletResponse response) throws IOException {
        UserRes userRes = userService.getUserById(userReq.getUsername());
        if (passwordEncoder.matches(userReq.getPassword(), userRes.getPassword())) {
            Integer deleteRole = userService.deleteRole(userRes.getMember_id());
            Integer deleteUser = userService.deleteUser(userReq.getUsername());
//            model.addAttribute("msg", "탈퇴되었습니다.");
//            model.addAttribute("url", "redirect:/");
            session.invalidate();
//            return "user/deleteAlert";
            return "redirect:/";
        } else {
            response.setContentType("text/html; charset=UTF-8");
            PrintWriter writer = response.getWriter();
            writer.println("<script>alert('비밀번호가 틀렸습니다.');</script>");
            writer.flush();
            return "user/deleteForm";
        }
    }

    // 아이디 중복 체크
    @GetMapping("/idCheckAjax")
    @ResponseBody  // 리스폰스바디와 json은 한 쌍 !! dataType:"json" >> 이쪽으로 결과를 리턴해주는 역할을 하므로 밑에 return에 프론트 경로 안써준다!
    public Integer idCheck(@RequestParam String username) {
        int cnt = userService.idCheck(username);
        return cnt;   // cnt라는 이름으로 결과값을 ajax로 보냈다!
    }

    // 이메일 중복 체크
    @GetMapping("/emailCheckAjax")
    @ResponseBody  // 리스폰스바디와 json은 한 쌍 !! dataType:"json" >> 이쪽으로 결과를 리턴해주는 역할을 하므로 밑에 return에 프론트 경로 안써준다!
    public Integer emailCheck(@RequestParam String email) {
        int cnt = userService.emailCheck(email);
        return cnt;   // cnt라는 이름으로 결과값을 ajax로 보냈다!
    }

    // 닉네임 중복 체크
    @GetMapping("/nicknameCheckAjax")
    @ResponseBody  // 리스폰스바디와 json은 한 쌍 !! dataType:"json" >> 이쪽으로 결과를 리턴해주는 역할을 하므로 밑에 return에 프론트 경로 안써준다!
    public Integer nicknameCheck(@RequestParam String nickname) {
        int cnt = userService.nicknameCheck(nickname);
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
        if (userRes == null) {
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
        UserRes userRes = userService.findPw(userReq); // 1. 아이디, 이메일이 일치하는 회원정보가 있는지 체크
        if (userRes == null) { // 회원정보가 없다면
            response.setContentType("text/html; charset=UTF-8");
            PrintWriter writer = response.getWriter();
            writer.println("<script>alert('해당정보가 존재하지 않습니다.');</script>");
            writer.flush();
            return "user/findPwForm";
        } else { // 회원정보가 있다면

            // 2. 임시 비밀번호를 만들어서 이메일로 전송
            // 2-1. 임시비밀번호 생성
            String tempPw = getTempPassword();

            // 2-2. 이메일로 임시비밀번호 내용 전송
            SimpleMailMessage message = new SimpleMailMessage();
            message.setTo(userReq.getEmail());
            message.setSubject("[total football] 임시 비밀번호 안내드립니다.");
            message.setText("안녕하세요. \n\n" + userReq.getUsername() + "님의 임시비밀번호는 " + tempPw + " 입니다.\n\n 로그인 후에 비밀번호를 변경을 해주세요");
            message.setFrom(from);
            message.setReplyTo(from);
            System.out.println("message: " + message);
            javaMailSender.send(message);

            // 3. 임시 비밀번호 db에 수정
            userRes.setPassword(passwordEncoder.encode(tempPw));
            Integer updatePw = userService.updatePw(userRes);

            // 4. 성공하면 alert창 띄우기
            response.setContentType("text/html; charset=UTF-8");
            PrintWriter writer = response.getWriter();
            writer.println("<script>alert('가입 된 이메일로 임시 비밀번호가 전송되었습니다.');</script>");
            writer.flush();
            return "index";
        }
    }

    // 임시 비밀번호 랜덤으로 만들었음
    public String getTempPassword() {
        char[] charSet = new char[]{'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F',
                'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'};

        String str = "";

        // 문자 배열 길이의 값을 랜덤으로 10개를 뽑아 구문을 작성함
        int idx = 0;
        for (int i = 0; i < 10; i++) {
            idx = (int) (charSet.length * Math.random());
            str += charSet[idx];
        }
        return str;
    }

    @GetMapping("/updatePwForm")
    public String updatePwForm() {
        return "user/updatePwForm";
    }

    @PostMapping("/updatePw")
    public String updatePw(Model model, @ModelAttribute UserRes userRes, HttpSession session, HttpServletResponse response) throws IOException {
        System.out.println("userRes : " + userRes);
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();
        userRes.setUsername(username);
        userRes.setPassword(passwordEncoder.encode(userRes.getPassword()));

        Integer updatePassword = userService.updatePw(userRes);

        if (updatePassword == 0) {
            response.setContentType("text/html; charset=UTF-8");
            PrintWriter writer = response.getWriter();
            writer.println("<script>alert('비밀번호가 변경되지 않았습니다.');</script>");
            writer.flush();
            return "user/updatePwForm";
        }
        session.invalidate();

        return "redirect:/";
    }
}
