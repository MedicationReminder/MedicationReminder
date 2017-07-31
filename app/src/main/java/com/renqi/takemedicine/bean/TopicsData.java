package com.renqi.takemedicine.bean;

/**
 * Created by Xu Wei on 2017/7/30.
 */

public class TopicsData {
    public String id;
    public String title;
    public String created_at;
    public String topic_category_name;
    public String content;
    public int status;
    public int replies_count;

    public TopicsData(String title, String created_at, String topic_category_name, String content, int status, int replies_count) {
        this.title = title;
        this.created_at = created_at;
        this.topic_category_name = topic_category_name;
        this.content = content;
        this.status = status;
        this.replies_count = replies_count;
    }

    public TopicsData(String id, String title, String created_at, String topic_category_name, String content, int status, int replies_count) {

        this.id = id;
        this.title = title;
        this.created_at = created_at;
        this.topic_category_name = topic_category_name;
        this.content = content;
        this.status = status;
        this.replies_count = replies_count;
    }
}
