package com.minki.football.controller.board;


import com.minki.football.dto.board.BoardReq;
import com.minki.football.dto.board.BoardRes;
import com.minki.football.service.board.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller //얘가 컨트롤러다 라고 별명을 지어줌
@RequestMapping("/board")// 공통적인 path(board)를 중복해서 안 쓰기 위해 빼 놓은 것.
public class BoardController { // 기본 클래스 이름

    @Autowired
    private BoardService boardService;  // BoardService를  boardService라고 지정함.

    //게시판 리스트 조회
    @GetMapping("/list")  // 우리가 임의로 지정한 경로
    public String boardList(Model model) { //접근제한자 리턴값 메소드명(매개변수) >> 이게 하나의 메소드다.
       List<BoardRes> boardList = boardService.boardList(); // boardService.boardList라는 메소드를 호출해서 왼쪽 boardList에 담아준 것.
       model.addAttribute("boardList", boardList); //  "/board/list"경로에다가 boardList를  "boardList" 여기 변수명에 담아서 프론트로 보냄
        System.out.println("게시판 조회 : " + boardList);
        return "/board/list"; // 프론트엔드로 가는 경로 (템플릿 밑에 경로)

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

    //게시판 글 등록(작성)
    @PostMapping("/boardRegister") // 우리가 임의로 지정한 경로
    public String boardRegister(Model model, @ModelAttribute BoardReq boardReq ) {
        // 게시글 작성하려고 내용을 쓰고 저장 버튼 누르는 순간  그 값들을 @ModelAttribute BoardReq boardReq 여기다가 넣어줌 그리고 이게 xml로 보내진다
        Integer boardRegister = boardService.boardRegister(boardReq); //boardService파일 안에 있는 boardRegister(boardReq)라는 메소드를 호출해서 왼쪽 boardRegister에 담아준 것.
        model.addAttribute("boardRegister",boardRegister); // "/board/boardRegister"라는 경로에다가 boardRegister를 "boardRegister"라는 변수명에 담아서 프론트로 보냄
        return "/board/boardRegister"; //여기서 리턴값은 프론트엔드로 가는 경로(템플릿 밑에 경로 : templates > board > boardRegister.html)
        //결론적으로 프론트에서 저장버튼 누르면  @ModelAttribute가 매개변수 boardReq를 보내고 그 변수들(타이틀,컨탠트 등등)이 #으로 치환되고 그 내용들이 dbeaver에 들어간다.

        // @ModelAttribute : 론트가 보낸 변수들을 받을때 사용
        // model.addattribute : 메소드를 실행하고 난 결과를 담아서 프론트에 보내주는 역할
    }

}
