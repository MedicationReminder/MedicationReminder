package com.renqi.takemedicine.bean.response;

/**
 * Created by Xu Wei on 2017/8/6.
 */

public class CreateQAParam {

    /**
     * topic : {"title":"1111","content":"小孩子半夜发烧怎么","topic_category_name":"药品分析"}
     */

    private TopicBean topic;

    public TopicBean getTopic() {
        return topic;
    }

    public void setTopic(TopicBean topic) {
        this.topic = topic;
    }

    public static class TopicBean {
        public TopicBean(String title, String content, String topic_category_name) {
            this.title = title;
            this.content = content;
            this.topic_category_name = topic_category_name;
        }

        /**
         * title : 1111
         * content : 小孩子半夜发烧怎么
         * topic_category_name : 药品分析
         */

        private String title;
        private String content;
        private String topic_category_name;

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public String getTopic_category_name() {
            return topic_category_name;
        }

        public void setTopic_category_name(String topic_category_name) {
            this.topic_category_name = topic_category_name;
        }
    }
}
