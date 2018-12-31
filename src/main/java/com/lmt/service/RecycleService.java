package com.lmt.service;

import com.lmt.entity.Note;
import com.lmt.entity.NoteResult;

/**
 * Created by 热带雨林 on 2018/12/8.
 */
public interface RecycleService {
    //public NoteResult recycleNote(Note note);
    public NoteResult recycleNote(String cn_note_id);
}
