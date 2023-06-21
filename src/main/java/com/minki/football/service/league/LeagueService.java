package com.minki.football.service.league;

import com.minki.football.dto.league.LeagueRes;
import com.minki.football.mapper.league.LeagueMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service //3-1번. 서비스라고 이름 선언
public class LeagueService {

    @Autowired // LeagueService랑 leagueMapper랑 연결해서 leagueMapper의 메소드를 사용하기 위해 !
    private LeagueMapper leagueMapper; // (접근제한자 파일이름 변수명) LeagueMapper라는 파일을 오른쪽 변수명(leagueMapper)으로 정의한다.

    public List<LeagueRes> list() { // 접근제한자 리턴값 변수명(){}, LeagueService 파일 안에다가 list()라는 메소드를 만듦

        List<LeagueRes> leagueList = leagueMapper.list(); // leagueMapper.list()를 leagueList에 저장한다.
        // 타입은 List<LeagueRes> 변수명 = leagueMapper.list()를 호출한 결과; >> 호출한 결과를 왼쪽 leagueList에 저장한다.
        return leagueList; // 그리고 이 결과값을 리턴
    }

    public LeagueRes info(Integer leagueId) { // 접근제한자 리턴값 변수명(){}, LeagueService 파일 안에다가 info()라는 메소드를 만듦

        LeagueRes leagueRes = leagueMapper.info(leagueId); //leagueMapper.info(leagueId)를 leagueRes에 저장한다.

        return leagueRes; // 그리고 이 결과값을 리턴
    }
}
