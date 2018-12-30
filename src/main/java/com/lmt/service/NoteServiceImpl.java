package com.lmt.service;

/**
 * Created by 张洲徽 on 2018/12/6.
 */

import com.lmt.dao.NoteDao;
import com.lmt.dao.ShareDao;
import com.lmt.entity.Note;
import com.lmt.entity.NoteResult;
import com.lmt.entity.Share;
import com.lmt.util.NoteUtil;
import org.apache.commons.collections.map.HashedMap;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.sql.Date;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by 张洲徽 on 2018/12/6.
 */

@Service("noteService")
//@Transactional(readOnly=true)
public class NoteServiceImpl implements NoteService{
    @Resource
    private NoteDao noteDao;
    @Resource
    private ShareDao shareDao;
/*
    public NoteResult loadBookNotes(String bookId) {
        NoteResult result = new NoteResult();
        List<Note> list = noteDao.findByBookId(bookId);
        result.setData(list);
        result.setStatus(0);
        result.setMsg("加载笔记列表成功");
        return result;
    }
    */
    public NoteResult loadBookNotes(String bookId) {
        NoteResult result = new NoteResult();
        Note note=new Note();
        note.setCn_notebook_id(bookId);
        note.setCn_note_type_id("1");
        List<Map> list = noteDao.findByBookId(note);
        /*
        for(Map m:list){
            for (Object key : m.keySet()) {
                System.out.println("Key = " + key);
            }
            for (Object value : m.values()) {
                System.out.println("Value = " + value);
            }
        }
        */
        result.setData(list);
        result.setStatus(0);
        result.setMsg("加载笔记列表成功");
        return result;
    }

    //@Transactional(isolation=Isolation.REPEATABLE_READ)
    public NoteResult addNote(String bookId, String userId,String noteTitle) {
        Note note = new Note();
        note.setCn_notebook_id(bookId);
        note.setCn_user_id(userId);
        note.setCn_note_title(noteTitle);
        String noteId = NoteUtil.createId();
        note.setCn_note_id(noteId);
        note.setCn_note_status_id("1");//normal
        //1-正常  2-喜欢  3-分享
        note.setCn_note_type_id("1");//normal
        note.setCn_note_body("");
        long time = System.currentTimeMillis();
        note.setCn_note_create_time(time);
        note.setCn_note_last_modify_time(time);
        noteDao.save(note);

        NoteResult result = new NoteResult();
        result.setStatus(0);
        result.setMsg("创建笔记成功");
        result.setData(noteId);
        return result;
    }

    public NoteResult loadNoteDetail(String id) {
        NoteResult result = new NoteResult();
        Note note = noteDao.findById(id);
        if(note == null){
            result.setStatus(1);
            result.setMsg("找不到笔记信息");
            return result;
        }
        result.setStatus(0);
        result.setData(note);
        result.setMsg("查找笔记成功");
        return result;
    }

    //@Transactional
    public NoteResult updateNote(String noteTitle, String noteBody,String noteId) {
        Note note = new Note();
        note.setCn_note_id(noteId);
        note.setCn_note_title(noteTitle);
        note.setCn_note_body(noteBody);
        long time = System.currentTimeMillis();
        note.setCn_note_last_modify_time(time);
        int num=noteDao.dynamicUpdate(note);

        NoteResult result = new NoteResult();
        if(num==1){
            result.setStatus(0);
            result.setMsg("保存笔记成功");
            return result;
        }else {
            result.setStatus(1);
            result.setMsg("保存笔记失败，插入0条数据");
            return result;
        }
    }

    @Transactional
    public NoteResult shareNote(String noteId) {
        NoteResult result = new NoteResult();
        //检查是否已分享过
        Share has_share = shareDao.findByNoteId(noteId);
        if(has_share!=null){
            result.setStatus(1);
            result.setMsg("该笔记已分享过");
            return result;
        }
        //未分享过，分享操作
        Note note = noteDao.findById(noteId);
        if(note==null){
            result.setStatus(2);
            result.setMsg("该笔记不存在");
            return result;
        }
        Share share = new Share();
        share.setCn_note_id(note.getCn_note_id());
        share.setCn_share_title(note.getCn_note_title());
        share.setCn_share_body(note.getCn_note_body());
        String shareId = NoteUtil.createId();
        share.setCn_share_id(shareId);

        shareDao.save(share);//插入分享表

        //模拟异常
        String str=null;
        str.length();

        result.setStatus(0);
        result.setMsg("分享笔记成功");
        return result;
    }
/*
    public NoteResult searchShare(String keyword) {
        String title = "";
        if(!"".equals(keyword) && keyword !=null){
            title = "%"+keyword+"%";
        }else{
            //搜索不为空的数据
            title = "%";
        }
        //List<Share> list = shareDao.findLikeTitle(title);
        List<Map> list = shareDao.findLikeTitle(title);
        NoteResult result = new NoteResult();
        result.setStatus(0);
        result.setMsg("检索成功");
        result.setData(list);
        return result;
    }
*/
    //改成带分页功能的
    public NoteResult searchShare(String keyword,int page) {
        String title = "";
        if(!"".equals(keyword) && keyword !=null){
            title = "%"+keyword+"%";
        }else{
            //搜索不为空的数据
            title = "%";
        }
        Map<String,Object> params=new HashedMap();
        params.put("title",title);
        params.put("page",page);
        List<Map> list = shareDao.findLikeTitle(params);
        NoteResult result = new NoteResult();
        result.setStatus(0);
        result.setMsg("检索成功");
        result.setData(list);
        return result;
    }

    public NoteResult loadShare(String shareId) {
        NoteResult result = new NoteResult();
        Share share = shareDao.findById(shareId);
        result.setStatus(0);
        result.setMsg("查询成功");
        result.setData(share);
        return result;
    }

    public NoteResult loadDeleteNote(String userId){
        List<Map> list = noteDao.findDeleteNote(userId);
        NoteResult result = new NoteResult();
        result.setStatus(0);
        result.setMsg("查找回收站笔记成功");
        result.setData(list);
        return result;
    }

/*
    public NoteResult moveNote(
            String noteId, String bookId) {
//		Map<String,Object> params = new HashMap<String, Object>();
//		params.put("note_id", noteId);
//		params.put("book_id", bookId);
//		noteDao.updateBookId(params);
        Note note = new Note();
        note.setCn_note_id(noteId);
        note.setCn_notebook_id(bookId);
        noteDao.dynamicUpdate(note);
        NoteResult result = new NoteResult();
        result.setStatus(0);
        result.setMsg("转移笔记成功");
        return result;
    }

    public NoteResult searchNotes(
            SearchNoteParams params) {
        NoteBean noteBean = new NoteBean();
        //设置查询参数
        //标题不为空时追加sql条件参数值
        if(params.getTitle() != null && !"".equals(params.getTitle())){
            noteBean.setCn_note_title("%"+params.getTitle()+"%");
        }
        //状态不为all时追加sql条件参数值
        if(!"-1".equals(params.getStatus())){
            noteBean.setCn_note_status_id(params.getStatus());
        }
        //起始日期不为空追加sql条件参数值
        if(params.getBeginTime()!=null
                && !"".equals(params.getBeginTime())){
            Date beginDate = Date.valueOf(params.getBeginTime());
            Long beginTime = beginDate.getTime();
            noteBean.setBeginTime(beginTime);
        }
        //结束日期不为空追加sql条件参数值
        if(params.getEndTime()!=null
                && !"".equals(params.getEndTime())){
            Date endDate = Date.valueOf(params.getEndTime());
            Long endTime = endDate.getTime();
            noteBean.setEndTime(endTime);
        }
        //执行查询
        List<Note> list = noteDao.findNotes(noteBean);
        //返回NoteResult统一结果
        NoteResult result = new NoteResult();
        result.setData(list);
        result.setStatus(0);
        result.setMsg("检索笔记成功");
        return result;
    }
    */

    //测试动态SQL，组合查询
    public NoteResult search(String title,String type,String begin,String end){
        Map map=new HashMap();
        if(title!=null&&!"".equals(title)){
            title="%"+title+"%";
            map.put("title",title);
        }
        if(type!=null&&!"".equals(type)){
            map.put("type",type);
        }
        if(begin!=null&&!"".equals(begin)){
            Date beginDate= Date.valueOf(begin);
            map.put("beginDate",beginDate.getTime());
        }
        if(end!=null&&!"".equals(end)){
            Date endDate= Date.valueOf(end);
            Calendar c=Calendar.getInstance();
            c.setTime(endDate);
            c.add(Calendar.DATE,1);
            map.put("endDate",c.getTimeInMillis());
        }
        List<Note> lists=noteDao.search(map);
        NoteResult result = new NoteResult();
        result.setData(lists);
        result.setStatus(0);
        result.setMsg("检索笔记成功");
        return result;
    }
}
