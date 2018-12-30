package com.lmt.service;

import com.lmt.dao.BookDao;
import com.lmt.entity.NoteBook;
import com.lmt.entity.NoteResult;
import com.lmt.util.NoteUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.sql.Timestamp;
import java.util.List;

/**
 * Created by 张洲徽 on 2018/12/4.
 */
@Service("bookService")
@Transactional
public class BookServiceImpl implements BookService{
    @Resource
    private BookDao bookDao;

    public NoteResult loadUserBooks(String userId) {
        NoteResult result = new NoteResult();
        List<NoteBook> books = bookDao.findByUserId(userId);
        result.setStatus(0);
        result.setData(books);
        result.setMsg("加载用户笔记本成功");
        return result;
    }

    public NoteResult addBook(String userId, String bookName) {
        NoteResult result = new NoteResult();
        //TODO 检测是否重名
        NoteBook book = new NoteBook();
        book.setCn_user_id(userId);//用户ID
        book.setCn_notebook_name(bookName);//笔记本名
        String bookId = NoteUtil.createId();
        book.setCn_notebook_id(bookId);//笔记本ID
        book.setCn_notebook_type_id("5");//normal
        book.setCn_notebook_desc("");//描述
        Timestamp time = new Timestamp(System.currentTimeMillis());
        book.setCn_notebook_createtime(time);//创建时间
        bookDao.save(book);//添加
        result.setStatus(0);
        result.setMsg("创建笔记本成功");
        result.setData(bookId);//返回笔记本ID
        return result;
    }
}
