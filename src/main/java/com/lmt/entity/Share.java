package com.lmt.entity;

import java.io.Serializable;

/**
 * Created by 热带雨林 on 2018/12/8.
 */
public class Share implements Serializable {
    private String cn_share_id;
    private String cn_share_title;
    private String cn_share_body;
    private String cn_note_id;

    @Override
    public String toString() {
        return "Share{" +
                "cn_share_id='" + cn_share_id + '\'' +
                ", cn_share_title='" + cn_share_title + '\'' +
                ", cn_share_body='" + cn_share_body + '\'' +
                ", cn_note_id='" + cn_note_id + '\'' +
                '}';
    }

    public String getCn_share_id() {
        return cn_share_id;
    }
    public void setCn_share_id(String cnShareId) {
        cn_share_id = cnShareId;
    }
    public String getCn_share_title() {
        return cn_share_title;
    }
    public void setCn_share_title(String cnShareTitle) {
        cn_share_title = cnShareTitle;
    }
    public String getCn_share_body() {
        return cn_share_body;
    }
    public void setCn_share_body(String cnShareBody) {
        cn_share_body = cnShareBody;
    }
    public String getCn_note_id() {
        return cn_note_id;
    }
    public void setCn_note_id(String cnNoteId) {
        cn_note_id = cnNoteId;
    }
}