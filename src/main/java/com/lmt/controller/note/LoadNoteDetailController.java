package com.lmt.controller.note;

import com.lmt.entity.NoteResult;
import com.lmt.service.NoteService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

/**
 * Created by 张洲徽 on 2018/12/7.
 */
@Controller
@RequestMapping("/note")
public class LoadNoteDetailController {
    @Resource
    private NoteService noteService;

    @RequestMapping("/load.form")
    @ResponseBody
    public NoteResult execute(String id){
        NoteResult result = noteService.loadNoteDetail(id);
        return result;
    }
}
