package com.minki.football.mapper.league;

import com.minki.football.dto.league.LeagueRes;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

// 클래와 인터페이스의 차이 : 클래스는 구현하고 인터페이스는 뼈대만 만든다.(즉 선언만하고 구현 자체를 안 한다. 그래서 중괄호 없어!!)
@Mapper // Mapper입니다 ~~ 선언한거임.(Mapper는 무조건 인터페이스로 만든다.)
public interface LeagueMapper {
    List<LeagueRes> list(); // LeagueMapper.xml의 아이디 값은 이 메소도의 이름 = list와 같아야 한다.

    LeagueRes info(Integer leagueId);
}
