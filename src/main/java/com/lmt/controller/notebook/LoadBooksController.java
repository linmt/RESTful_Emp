package com.lmt.controller.notebook;

import com.lmt.entity.NoteResult;
import com.lmt.service.BookService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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

    @RequestMapping(value = "/loadbooks/{userId}",method = RequestMethod.GET)
    @ResponseBody
    public NoteResult execute(@PathVariable String userId){
        NoteResult result = bookService.loadUserBooks(userId);
        return result;
    }
}
