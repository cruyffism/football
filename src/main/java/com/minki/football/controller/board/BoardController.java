package com.minki.football.controller.board;


import com.minki.football.dto.board.BoardReq;
import com.minki.football.dto.board.BoardRes;
import com.minki.football.dto.page.Criteria;
import com.minki.football.dto.page.PageMaker;
import com.minki.football.service.board.BoardService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@Controller //얘가 컨트롤러다 라고 별명을 지어줌
@RequestMapping("/board")// 공통적인 path(board)를 중복해서 안 쓰기 위해 빼 놓은 것.
public class BoardController { // 기본 클래스 이름

    @Autowired
    private BoardService boardService;  // BoardService를  boardService라고 지정함.

    @GetMapping("/list") // 게시판 없는 기본 화면
    public String boardList(Model model, @ModelAttribute("criteria") Criteria criteria) {
        model.addAttribute("criteria", criteria);
        return "board/list";
    }


    //게시판 리스트 조회
    @GetMapping("/listAjax")  // 우리가 임의로 지정한 경로
    public String boardListAjax(Model model, @ModelAttribute Criteria criteria) { //접근제한자 리턴값 메소드명(매개변수) >> 이게 하나의 메소드다.
        System.out.println("criteria : "+criteria);
        List<BoardRes> boardList = boardService.boardList(criteria); // boardService 파일 안에 있는 boardList(criteria)라는 메소드를 호출한 결과값이 왼쪽 List<BoardRes>타입의 boardList라는 변수명으로 담긴다.
        PageMaker pageMaker = new PageMaker(); //PageMaker 클래스파일을 사용하기위해 선언한 것 >> 선언해주면 PageMaker 클래스파일의 변수들을 get, set해서 사용가능하다.
        pageMaker.setCriteria(criteria); // criteria라는 변수에다가 우리가 @ModelAttribute를 통해 매개변수로 받은 criteria 값을 저장
        pageMaker.setTotalCount(boardService.selectBoardCount(criteria));// pageMaker에 있는 totalcount라는 변수에다가 ㅇ우리가 아까만든 게시ㅋ물 총개수 결과값을 넣은거야
        model.addAttribute("PageMaker", pageMaker);

       model.addAttribute("boardList", boardList); //  "/board/list"경로에다가 boardList를  "boardList" 여기 변수명에 담아서 프론트로 보냄
        System.out.println("게시판 조회 : " + boardList);
        return "board/listAjax"; // 프론트엔드로 가는 경로 (템플릿 밑에 경로)

    }

    //게시판 단건 조회
    @GetMapping("/info/{boardId}")  // 우리가 임의로 지정한 경로
    public String info(Model model, @PathVariable Integer boardId) { // @pathvariable을 통해 boardId라는 이름으로 저기 위에 path에 있는 {boardId}를 가져다 쓴다
                                                                     // 위 경로에 "/info/1" 이렇게 들어오면 매개변수에 쓴 boardId가 1로 들어가져서 결국 xml에  #{boardId}가 1로 변한다.
        BoardRes boardInfo = boardService.boardInfo(boardId); // boardService 파일 안에 있는 boardInfo(boardId) 메소드를 호출한 결과값이 BoardRes 타입의 boardInfo라는 변수명으로 담긴다!
        model.addAttribute("info", boardInfo); // "board/boardInfo"라는 경로에다가 boardInfo를 "info"라는 변수명에 담아서 프론트로 보냄
        return "board/boardInfo"; //여기서 리턴값은 프론트엔드로 가는 경로(템플릿 밑에 경로 : templates > board > boardInfo.html)
        // 즉 모델 어트리뷰트에서 boardInfo를 "info"라는 변수명에 담아서 프론트 엔드로 보내고 그 보낸 값이 리턴값 경로를 타서 프론트엔드 html로 간다!
        // 그리고 html에서 ${info}로 연결해서 사용한다.
    }

    //게시판 등록폼
    @GetMapping("/boardRegister") // 우리가 임의로 지정한 경로
    public String boardRegisterForm() {
        return "board/boardRegister";
    }


    //게시판 글 등록(작성)
    @PostMapping("/boardRegister") // 우리가 임의로 지정한 경로
    public String boardRegister(Model model, @ModelAttribute BoardReq boardReq) {
        // 게시글 작성하려고 내용을 쓰고 저장 버튼 누르는 순간  그 값들을 @ModelAttribute BoardReq boardReq 여기다가 넣어줌 그리고 이게 xml로 보내진다
        Authentication auth = SecurityContextHolder.getContext().getAuthentication(); //71~73 : 백엔드에서  글 저장하려할ㅈ때 로그인 정보 가져와서 아이디 값을 디비에 넣어주는거!
        String username = auth.getName();
        boardReq.setNickname(username);
        Integer boardRegister = boardService.boardRegister(boardReq); //boardService파일 안에 있는 boardRegister(boardReq)라는 메소드를 호출해서 왼쪽 boardRegister에 담아준 것.
        model.addAttribute("boardRegister",boardRegister); // "/board/boardRegister"라는 경로에다가 boardRegister를 "boardRegister"라는 변수명에 담아서 프론트로 보냄
        return "board/list"; //여기서 리턴값은 프론트엔드로 가는 경로(템플릿 밑에 경로 : templates > board > boardRegister.html)

        //결론적으로 프론트에서 저장버튼 누르면  @ModelAttribute가 매개변수 boardReq를 보내고 그 변수들(타이틀,컨탠트 등등)이 #으로 치환되고 그 내용들이 dbeaver에 들어간다.
        // @ModelAttribute : 프론트가 보낸 변수들을 받을때 사용
        // model.addattribute : 메소드를 실행하고 난 결과를 담아서 프론트에 보내주는 역할
    }

    //게시판 수정폼
    @GetMapping("/boardUpdate/{boardId}") // 우리가 임의로 지정한 경로
    public String boardUpdateForm(Model model, @PathVariable Integer boardId) {
        BoardRes boardInfo = boardService.boardInfo(boardId); // boardService 파일 안에 있는 boardInfo(boardId) 메소드를 호출한 결과값이 BoardRes 타입의 boardInfo라는 변수명으로 담긴다!
        model.addAttribute("info", boardInfo);
        return "board/boardUpdate";
    }

    // 게시판 업데이트
    @PostMapping("/boardUpdate/{boardId}") // 우리가 임의로 지정한 경로
    public String boardUpdate(Model model, @PathVariable Integer boardId,  @ModelAttribute BoardReq boardReq) {
        // 게시글을 수정하고 저장 버튼 누르는 순간  그 값들을 @ModelAttribute BoardReq boardReq 여기다가 넣어줌 그리고 이게 xml로 보내진다
    boardReq.setBoardId(boardId);
    Integer boardUpdate = boardService.boardUpdate(boardReq); //boardService파일 안에 있는 boardUpdate(boardReq)라는 메소드를 호출해서 왼쪽 boardRegister에 담아준 것.
    model.addAttribute("boardUpdate",boardUpdate); //  "/board/boardUpdate"라는 경로에다가 boardUpdate를 "boardUpdate"라는 변수명에 담아서 프론트로 보냄
    return "board/list"; //여기서 리턴값은 프론트엔드로 가는 경로(템플릿 밑에 경로 : templates > board > boardUpdate.html)
    }

    // 게시판 삭제
    @GetMapping ("/boardDelete/{boardId}") // 우리가 임의로 지정한 경로
    public String boardDelete (Model model, @PathVariable Integer boardId, HttpServletResponse response) throws IOException {
    Integer boardDelete =boardService.boardDelete(boardId);
    model.addAttribute("boardDelete", boardDelete);
        response.setContentType("text/html; charset=UTF-8");
        PrintWriter writer = response.getWriter();
        writer.println("<script>alert('게시글이 삭제되었습니다.');</script>");
        writer.flush();
    return "board/list";
    }

}
