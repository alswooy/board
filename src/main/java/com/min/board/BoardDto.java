package com.min.board;

import java.util.Date;
import java.util.Objects;

public class BoardDto {
    private int bno;
    private String title;
    private String content;
    private int view_cnt;
    private Date reg_date;

    BoardDto(){}
    BoardDto(String title, String content){
        bno = bno+1;
        this.title = title;
        this.content = content;
        view_cnt=0;
    }

    public int getBno() {
        return bno;
    }

    public void setBno(int bno) {
        this.bno = bno;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getView_cnt() {
        return view_cnt;
    }

    public void setView_cnt(int view_cnt) {
        this.view_cnt = view_cnt;
    }

    public Date getReg_date() {
        return reg_date;
    }

    public void setReg_date(Date reg_date) {
        this.reg_date = reg_date;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BoardDto boardDto = (BoardDto) o;
        return bno == boardDto.bno && view_cnt == boardDto.view_cnt && Objects.equals(title, boardDto.title) && Objects.equals(content, boardDto.content) && Objects.equals(reg_date, boardDto.reg_date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(bno, title, content, view_cnt, reg_date);
    }

    @Override
    public String toString() {
        return "BoardDto{" +
                "bno=" + bno +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", view_cnt=" + view_cnt +
                ", reg_date=" + reg_date +
                '}';
    }

}
