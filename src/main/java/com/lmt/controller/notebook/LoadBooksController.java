package com.lmt.controller.notebook;

import com.lmt.entity.NoteResult;
import com.lmt.service.BookService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

/**
 * Created by 张洲徽 on 2018/12/4.
 */
@Controller
@RequestMapping("/notebook")
public class LoadBooksController {
    @Resource
    private BookService bookService;

    @RequestMapping("/loadbooks.form")
    @ResponseBody
    public NoteResult execute(String userId){
        NoteResult result = bookService.loadUserBooks(userId);
        return result;
    }
}
