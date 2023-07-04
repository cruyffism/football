package com.minki.football.service.admin;


import com.minki.football.dto.team.TeamRes;
import com.minki.football.mapper.admin.AdminMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminService {

    @Autowired
    private AdminMapper adminMapper;


    // 1. 팀 관리 리스트 조회
    public List<TeamRes> list(Integer leagueId) {
        List<TeamRes> list = adminMapper.list(leagueId);
        return list;
    }

    //     <!--// 수정폼 조회-->
    public TeamRes teamInfo(Integer teamId) {
        TeamRes teamInfo = adminMapper.teamInfo(teamId);
        return teamInfo;
    }

    //     <!--// 수정 및 저장-->
    


}
