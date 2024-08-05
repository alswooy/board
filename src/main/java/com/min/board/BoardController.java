package com.min.board;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
//Just Board CRUD
//java
//spring + mybatis
//mysql

@Controller
public class BoardController {

    BoardService boardService;
    @Autowired
    public BoardController(BoardService boardService) {
        this.boardService = boardService;
    }

    @GetMapping("/")
    public String index(Model model) {
        try {
            List<BoardDto> list = boardService.findAll();
            int cnt = boardService.postCntAll();
            model.addAttribute("list", list);
            model.addAttribute("cnt", cnt);
        }catch (Exception e){
            e.printStackTrace();
        }
        return "board";
    }

    @PostMapping("/write")
    public String write(BoardDto boardDto) {
        System.out.println(boardDto.getBno());
        System.out.println(boardDto.getTitle());
        System.out.println(boardDto.getContent());
        try{
            int rowCnt = boardService.write(boardDto);
            if(rowCnt != 1)
                throw new Exception("board controller write fail");
            return "redirect:/";
        }catch (Exception e){
            e.printStackTrace();
            return "board";
        }
    }

    @PostMapping("/modify")
    public String modify(BoardDto boardDto) {
        try{
            int rowCnt = boardService.modify(boardDto.getBno(), boardDto);
            if(rowCnt != 1)
                throw new Exception("board controller modify fail");
            return "redirect:/";
        }catch (Exception e){
            e.printStackTrace();
            return "board";
        }
    }

    @PostMapping("/remove")
    public String remove(BoardDto boardDto) {
        try{
            int rowCnt = boardService.remove(boardDto.getBno());
            if (rowCnt != 1)
                throw new Exception("board controller remove fail");
            return "redirect:/";
        }catch (Exception e){
            e.printStackTrace();
            return "board";
        }
    }

}
