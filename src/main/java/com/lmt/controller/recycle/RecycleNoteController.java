package com.lmt.controller.recycle;

import com.lmt.entity.Note;
import com.lmt.entity.NoteResult;
import com.lmt.service.RecycleService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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

    @RequestMapping(value = "/recycle",method = RequestMethod.PUT)
    //@RequestMapping("/recycle.form")
    @ResponseBody
    //两种方式都可以
    //public NoteResult execute(@RequestBody Note note){
    public NoteResult execute(@RequestBody String cn_note_id){
        System.out.println(cn_note_id);
        NoteResult result = recycleService.recycleNote(cn_note_id);
        return result;
    }
}
