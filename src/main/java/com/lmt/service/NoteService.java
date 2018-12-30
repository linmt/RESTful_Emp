package com.lmt.service;

import com.lmt.entity.NoteResult;

/**
 * Created by 张洲徽 on 2018/12/6.
 */
public interface NoteService {
    public NoteResult loadBookNotes(String bookId);
    public NoteResult addNote(String bookId, String userId, String noteTitle);
    public NoteResult loadNoteDetail(String id);
    public NoteResult updateNote(String noteTitle, String noteBody, String noteId);
    public NoteResult shareNote(String noteId);
    //public NoteResult searchShare(String keyword);
    //改成带分页功能的
    public NoteResult searchShare(String keyword, int page);
    public NoteResult loadShare(String shareId);
    public NoteResult loadDeleteNote(String userId);
    /*
    public NoteResult searchNotes(SearchNoteParams params);
    public NoteResult moveNote(String noteId,String bookId);
    */

    //测试动态SQL，组合查询
    public NoteResult search(String title, String type, String begin, String end);
}
