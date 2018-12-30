package com.lmt.controller.note;

import com.lmt.entity.NoteResult;
import com.lmt.service.NoteService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

/**
 * Created by 张洲徽 on 2018/12/6.
 */
@Controller
@RequestMapping("/note")
public class LoadNotesController {
    @Resource
    private NoteService noteService;

    @RequestMapping("/loadnotes.form")
    @ResponseBody
    public NoteResult execute(String bookId){
        NoteResult result = noteService.loadBookNotes(bookId);
        return result;
    }
}
