package com.yx.Test;

import com.yx.mapper.TeamMapper;
import com.yx.model.Team;
import com.yx.util.SessionUtil;
import org.apache.ibatis.session.SqlSession;

import java.io.IOException;
import java.util.List;

public class TestOneToMany {
    public static void main(String[] args) throws IOException {
        SqlSession session = SessionUtil.session(true);
        TeamMapper mapper = session.getMapper(TeamMapper.class);
        List<Team> teams = mapper.selectList();
        teams.forEach(System.out::println);
    }
}
