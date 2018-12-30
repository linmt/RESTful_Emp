package com.lmt.controller.note;

import com.lmt.entity.NoteResult;
import com.lmt.service.NoteService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

/**
 * Created by 热带雨林 on 2018/12/6.
 */
@Controller
@RequestMapping("/note")
public class AddNoteController {
    @Resource
    private NoteService noteService;

    @RequestMapping("/add.form")
    @ResponseBody
    public NoteResult execute(String bookId, String userId, String noteTitle){
        NoteResult result = noteService.addNote(bookId, userId, noteTitle);
        return result;
    }
}
