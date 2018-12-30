package com.lmt.service;

import com.lmt.dao.NoteDao;
import com.lmt.entity.Note;
import com.lmt.entity.NoteResult;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by 热带雨林 on 2018/12/8.
 */
@Service("recycleService")
//@Transactional
public class RecycleServiceImpl implements RecycleService{
    @Resource
    private NoteDao noteDao;

    public NoteResult recycleNote(String noteId) {
        //noteDao.recycle(noteId);
        Note note = new Note();
        note.setCn_note_id(noteId);
        note.setCn_note_status_id("2");
        noteDao.dynamicUpdate(note);
        NoteResult result = new NoteResult();
        result.setStatus(0);
        result.setMsg("将笔记放入回收站成功");
        return result;
    }
}
