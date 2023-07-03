package com.minki.football.service.board;

import com.minki.football.dto.board.BoardReq;
import com.minki.football.dto.board.BoardRes;
import com.minki.football.dto.page.Criteria;
import com.minki.football.mapper.board.BoardMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BoardService {

    @Autowired  //  BoardService와 BoardMapper를 연결한 것
    private BoardMapper boardMapper; // (접근제한자 파일이름 변수명) BoardMapper라는 파일을 오른쪽 변수명(boardMapper)으로 정의한다.

    //게시판 리스트 조회
    public List<BoardRes> boardList(Criteria criteria) { // 접근제한자 리턴값 변수명(){}, BoardService 파일 안에다가 boardList()라는 메소드를 만듦
      List<BoardRes> boardList  = boardMapper.boardList(criteria);
      //   타입        변수명 = boardMapper. boardList(criteria)를 호출한 결과 ; >> 호출한 결과를 왼쪽 boardList에 담은 것
        return boardList; // 그리고 이 결과값을 리턴
    }

    //  게시판 총 게시글 개수 가져오기
    public Integer selectBoardCount(Criteria criteria) { // 접근제한자 리턴값 변수명(){}, BoardService 파일 안에다가 selectBoardCount()라는 메소드를 만듦
        Integer selectBoardCount = boardMapper.selectBoardCount(criteria);
        //타입     변수명          = boardMapper. selectBoardCount()를 호출한 결과 ; >> 호출한 결과를 왼쪽 selectBoardCount에 담은 것
        return selectBoardCount; //  그리고 이 결과값을 리턴
    }

    //게시판 단건 조회웅
    public BoardRes boardInfo(Integer boardId) { //  접근제한자 리턴값 변수명(){}, BoardService 파일 안에다가 boardInfo(Integer boardId)라는 메소드를 만듦
        BoardRes boardInfo = boardMapper.boardInfo(boardId); // 위에 이미 타입을 Integer로 지정했기에 그냥 변수명만 안에 쓰면 됨!
        // 타입    변수명    = boardMapper.boardInfo(boardId)를 호출한 결과 ; >> boardMapper에있는 boardInfo(boardId)를 호출한 결과를 왼쪽 변수명 boardInfo에 담은것
        return boardInfo;  // 그리고 이 결과값을 리턴
                            // 위에 public BoardRes boardInfo(Integer boardId) >> 접근제한자 리턴값 메소드명  여기서 말하는 리턴값을 리턴해주면 됨
    }

    //게시판 글 등록(작성)
    public Integer boardRegister(BoardReq boardReq) { // 접근제한자 리턴값 변수명(){}, BoardService 파일 안에다가 boardRegister(BoardReq boardReq)라는 메소드를 만듦
        Integer boardRegister = boardMapper.boardRegister(boardReq);
        // 타입 변수명 = boardMapper.boardRegister(boardReq)를 저장한 결과 ; >> boardMapper에 있는 boardRegister(boardReq)를 저장한 결과를 왼쪽 변수명 boardRegister에 담은 것
        return boardRegister; // 그리고 이 결과값을 리턴
    }

    //게시판 업데이트
    public Integer boardUpdate(BoardReq boardReq) { // 접근제한자 리턴값 변수명(){}, BoardService 파일 안에다가  boardUpdate(BoardReq boardReq)라는 메소드를 만듦
        Integer boardUpdate = boardMapper.boardUpdate(boardReq);
        //타입 변수명 = boardMapper.boardUpdate(boardReq)를 저장한 결과 ; >> boardMapper에 있는 boardUpdate(boardReq)를 저장한 결과를 왼쪽 변수명 boardUpdate에 담은 것
        return boardUpdate;  // 그리고 이 결과값을 리턴
    }

    // 게시판 삭제
    public Integer boardDelete(Integer boardId) { // 접근제한자 리턴값 변수명(){}, BoardService 파일 안에다가 boardDelete(Integer boardId)라는 메소드를 만듦
        Integer boardDelete = boardMapper.boardDelete(boardId);
        return boardDelete;
    }

}
