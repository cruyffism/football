package com.minki.football.service.league;

import com.minki.football.dto.league.LeagueRes;
import com.minki.football.mapper.league.LeagueMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service //서비스라고 이름 선언
public class LeagueService {

    @Autowired // LeagueService랑 leagueMapper랑 연결해서 leagueMapper의 메소드를 사용하기 위해 !
    private LeagueMapper leagueMapper; // 접근제한자 파일이름 변수명

    public List<LeagueRes> list() { // 접근제한자 리턴값 변수명(){}

        List<LeagueRes> leagueList = leagueMapper.list(); // leagueMapper.list()를 leagueList에 저장한다.

        return leagueList; // 리그컨트롤러에서 List<LeagueDto> leagueList = leagueService.list(); 이 파트가 실행이 되면
                            // 여기 메소드가 실행되고 리턴값으로 leagueList를 뿌려준다.( A = B = C)
    }

    public LeagueRes info(Integer leagueId) {

        LeagueRes leagueRes = leagueMapper.info(leagueId);

        return leagueRes;
    }
}
