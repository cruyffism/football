package com.minki.football.controller.user;

import com.minki.football.service.user.UserService;
import com.minki.football.vo.UserVo;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.IOException;
import java.io.PrintWriter;

@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/index")
    public String home(Model model) { // 인증된 사용자의 정보를 보여줌
//        String username = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//        // token에 저장되어 있는 인증된 사용자의 id 값
//        System.out.println("id : " + username);
//        UserVo userVo = userService.getUserById(username);
//        userVo.setPassword(null); // password는 보이지 않도록 null로 설정
//        model.addAttribute("user", userVo);
        return "index";
    }

    // 로그인 페이지
    @GetMapping("/login")
    public String loginPage() {
        return "user/loginPage";
    }

    // 회원가입 페이지
    @GetMapping("/signup")
    public String signupPage() {  // 회원 가입 페이지
        return "user/signupPage";
    }

    @PostMapping("/signup")
    public String signup(UserVo userVo, HttpServletResponse response) throws IOException { // 회원 가입
        try {
            userService.signup(userVo);
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

        return "user/loginPage";
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
