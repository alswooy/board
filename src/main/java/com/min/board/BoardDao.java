package com.min.board;

import java.util.List;

public interface BoardDao {
    String now() throws Exception;//현재시각 mapper 연결 테스트
    int insert(BoardDto dto) throws Exception; //글 삽입
    BoardDto select(int bno) throws Exception; //글 선택 내용
    int update(int bno, BoardDto dto) throws Exception; //글 수정
    int delete(int bno) throws Exception; //글 삭제
    int viewCnt(int bno) throws Exception; //view수가 올라가는 cnt
    BoardDto selectOld() throws Exception;

    int deleteAll() throws Exception; //글 전부 삭제
    int count() throws Exception; //글 수 확인
    List<BoardDto> selectAll() throws Exception; //글 목록
}
