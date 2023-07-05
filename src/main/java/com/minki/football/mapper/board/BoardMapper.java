package com.minki.football.mapper.board;

import com.minki.football.dto.board.BoardReq;
import com.minki.football.dto.board.BoardRes;
import com.minki.football.dto.page.Criteria;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface BoardMapper {

    // 게시판 리스트 조회
    List<BoardRes> boardList(Criteria criteria); //리절트타입 아이디 매개변수

    //  게시판 총 게시글 개수 가져오기
    Integer selectBoardCount(Criteria criteria);// 타입   메소드명    매개변수
    // Integer라는 타입을 selectBoardCount로 변수 선언

    //게시판 단건 조회
    BoardRes boardInfo(Integer boardId); // 타입   메소드명    매개변수
    // Integer라는 타입을 boardId로 변수 선언
    // boardInfo라는 메소드를 실행한 후 나온 값들을  BoardRes에 넣는다

    //게시판 글 등록(작성)
    Integer boardRegister(BoardReq boardReq); // 타입 메소드명 매개변수
    // BoardReq라는 타입을 boardReq로 변수 선언
    // boardRegister라는 메소드를 실행한 후 나온 값들을  Integer에 넣는다
    // BoardService에서 이 메소도를 호출하면서 boardReq라는 변수를 사용할 예정

    //게시판 업데이트
    Integer boardUpdate(BoardReq boardReq); // 타입 메소드명 매개변수
    // BoardReq라는 타입을 boardReq로 변수 선언
    // boardUpdate라는 메소드를 실행한 후 나온 값들을  Integer에 넣는다
    // BoardService에서 이 메소도를 호출하면서 boardReq라는 변수를 사용할 예정

    // 게시판 삭제
    Integer boardDelete(Integer boardId); // 타입 메소드명 매개변수
    //Integer라는 타입을 boardId로 변수 선언
    // boardDelete라는 메소드를 실행한 후 나온 값들을  Integer에 넣는다
    // BoardService에서 이 메소도를 호출하면서 boardId라는 변수를 사용할 예정
}
