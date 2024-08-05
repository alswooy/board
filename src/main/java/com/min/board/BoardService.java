package com.min.board;

import org.springframework.stereotype.Service;

import java.util.List;


public interface BoardService {
    BoardDto find(int bno) throws Exception; // 글 하나 팓시
    int write(BoardDto boardDto) throws Exception; //글 작성
    int modify(int bno, BoardDto boardDto) throws Exception; //글 수정
    int remove(int bno) throws Exception; //글 하나 삭제
    int views(int bno) throws Exception; //조회수

    int removeAll() throws Exception; //모든 게시글 삭제
    int postCntAll() throws Exception; //게시글 수
    List<BoardDto> findAll() throws Exception; //게시글 목록
    BoardDto oldBno() throws Exception;
}
