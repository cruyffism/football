package com.minki.football.mapper.board;

import com.minki.football.dto.board.BoardReq;
import com.minki.football.dto.board.BoardRes;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface BoardMapper {

    // 게시판 리스트 조회
    List<BoardRes> boardList(); //xml과 연결하려는 메소드(인터페이스)를 하나 만들었음. 메소드 괄호 안에 원래 메개변수를 넣을 수 있지만 현재 변수를 사용 안해서 안 넣음.
                                // 타입은 List<BoardRes>

    //게시판 단건 조회
    BoardRes boardInfo(Integer boardId); // 타입   메소드명    매개변수
    // Integer라는 타입을 boardId로 변수 선언

    //게시판 글 등록(작성)
    Integer boardRegister(BoardReq boardReq); // 타입 메소드명 매개변수
    // BoardReq라는 타입을 boardReq로 변수 선언
    // BoardService에서 이 메소도를 호출하면서 boardReq라는 변수를 사용할 예정
}
