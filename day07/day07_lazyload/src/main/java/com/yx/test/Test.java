package com.yx.test;

import com.yx.mapper.PlayerMapper;
import com.yx.model.Player;
import com.yx.util.SessionUtil;
import org.apache.ibatis.session.SqlSession;

import java.io.IOException;

public class Test {
    public static void main(String[] args) throws IOException {
        SqlSession session = SessionUtil.session(true);
        PlayerMapper mapper = session.getMapper(PlayerMapper.class);
        Player player = mapper.selectId(1);
        System.out.println(player);
    }
}
