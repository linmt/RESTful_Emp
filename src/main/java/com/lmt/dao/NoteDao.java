package com.lmt.dao;

import com.lmt.annotation.MybatisDao;
import com.lmt.entity.Note;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * Created by 张洲徽 on 2018/12/6.
 */
@MybatisDao
@Repository
public interface NoteDao {
    /*
    public int batchDelete(String[] ids);

    //组合查询
    public List<Note> findNotes(NoteBean note);
    //public int updateBookId(
    //	Map<String, Object> params);
    //public int recycle(String id);
    //public int update(Note note);

    */
    //public List<Note> findByBookId(String bookId);
    //public List<Map> findByBookId(String bookId);
    //测试动态SQL
    public List<Map> findByBookId(Note note);
    public void save(Note note);
    public Note findById(String id);
    //动态更新
    public int dynamicUpdate(Note note);
    public List<Map> findDeleteNote(String userId);

    //测试动态SQL，组合查询
    public List<Note> search(Map map);
}
