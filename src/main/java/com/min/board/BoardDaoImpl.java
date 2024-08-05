package com.min.board;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository("BoardDao")
public class BoardDaoImpl implements BoardDao{

    @Autowired
    private SqlSession session;
    private static String namespace = "com.min.board.BoardMapper.";


    @Override
    public String now() throws Exception {
        return session.selectOne(namespace+"now");
    }

    @Override
    public int insert(BoardDto dto) throws Exception {
        return session.insert(namespace+"insert", dto);
    }

    @Override
    public BoardDto select(int bno) throws Exception {
        return session.selectOne(namespace+"select", bno);
    }

    @Override
    public int update(int bno,BoardDto dto) throws Exception {
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("bno", dto.getBno());
        paramMap.put("title", dto.getTitle());
        paramMap.put("content", dto.getContent());
        return session.update(namespace+"update", paramMap);
    }

    @Override
    public int delete(int bno) throws Exception {
        return session.delete(namespace+"delete", bno);
    }

    @Override
    public int viewCnt(int bno) throws Exception {
        return session.update(namespace+"viewCnt", bno);
    }

    @Override
    public BoardDto selectOld() throws Exception {
        return session.selectOne(namespace+"selectOld");
    }

    @Override
    public int deleteAll() throws Exception {
        return session.delete(namespace+"deleteAll");
    }

    @Override
    public int count() throws Exception {
        return session.selectOne(namespace+"count");
    }

    @Override
    public List<BoardDto> selectAll() throws Exception {
        return session.selectList(namespace+"selectAll");
    }
}
