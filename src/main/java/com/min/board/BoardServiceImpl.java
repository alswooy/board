package com.min.board;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BoardServiceImpl implements BoardService{

    @Autowired
    private BoardDao boardDao;

    @Override
    public BoardDto find(int bno) throws Exception {

        return boardDao.select(bno);
    }
    @Override
    public BoardDto oldBno()throws Exception{
        return boardDao.selectOld();
    }
    @Override
    public int write(BoardDto boardDto) throws Exception {
        if(boardDao.count() >= 100){
            boardDao.delete(boardDao.selectOld().getBno());
        }
        int rowCnt = boardDao.insert(boardDto);
        if (rowCnt != 1){
            throw new Exception("board service write fail");
        }
        return rowCnt;
    }

    @Override
    public int modify(int bno, BoardDto boardDto) throws Exception {
        return boardDao.update(bno, boardDto);
    }

    @Override
    public int remove(int bno) throws Exception {
        return boardDao.delete(bno);
    }

    @Override
    public int views(int bno) throws Exception {
        return boardDao.viewCnt(bno);
    }

    @Override
    public int removeAll() throws Exception {
        return boardDao.deleteAll();
    }

    @Override
    public int postCntAll() throws Exception {
        return boardDao.count();
    }

    @Override
    public List<BoardDto> findAll() throws Exception {
        return boardDao.selectAll();
    }
}
