package com.lmt.controller.note;

import com.lmt.entity.NoteResult;
import com.lmt.service.NoteService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

/**
 * Created by 热带雨林 on 2018/12/9.
 */
@Controller
@RequestMapping("/note")
public class LoadDeleteNoteController {
    @Resource
    private NoteService noteService;

    @RequestMapping("/loadDelete.form")
    @ResponseBody
    public NoteResult execute(String userId){
        NoteResult result = noteService.loadDeleteNote(userId);
        return result;
    }
}