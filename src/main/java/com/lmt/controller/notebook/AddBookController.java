package com.lmt.controller.notebook;

import com.lmt.entity.NoteResult;
import com.lmt.service.BookService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

/**
 * Created by 张洲徽 on 2018/12/6.
 */
@Controller
@RequestMapping("/notebook")
public class AddBookController {
    @Resource
    private BookService bookService;
    @RequestMapping("/add.form")
    @ResponseBody
    public NoteResult execute(String userId, String bookName){
        NoteResult result = bookService.addBook(userId, bookName);
        return result;
    }
}
