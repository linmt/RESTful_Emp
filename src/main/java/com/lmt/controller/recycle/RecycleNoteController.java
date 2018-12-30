package com.lmt.controller.recycle;

import com.lmt.entity.NoteResult;
import com.lmt.service.RecycleService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

/**
 * Created by 热带雨林 on 2018/12/8.
 */
@Controller
@RequestMapping("/recycle")
public class RecycleNoteController {
    @Resource
    private RecycleService recycleService;

    @RequestMapping("/recycle.form")
    @ResponseBody
    public NoteResult execute(String noteId){
        NoteResult result = recycleService.recycleNote(noteId);
        return result;
    }
}
