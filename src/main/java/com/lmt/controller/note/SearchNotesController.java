package com.lmt.controller.note;

import com.lmt.entity.NoteResult;
import com.lmt.service.NoteService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

/**
 * Created by 张洲徽 on 2018/12/26.
 */
@Controller
@RequestMapping("/note")
public class SearchNotesController {
    @Resource
    private NoteService noteService;

    @RequestMapping("/searchnotes.form")
    @ResponseBody
    public NoteResult execute(String title,String type,String begin,String end){
        System.out.println(title+type+begin+end);
        NoteResult result = noteService.search(title,type,begin,end);
        return result;
    }
}
