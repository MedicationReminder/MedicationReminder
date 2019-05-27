package com.renqi.takemedicine.bean.response;

/**
 * Created by zsj on 2017/8/9.
 */

public class EditMedRemRequestBean {

    /**
     * id : 58118895bc54f41aa766cce3
     * app_drugremind : {"alert_mode":3,"app_contact_id":"","count":"1颗","eat_count":"每日一次","eat_time":"2017-08-09 17:07:43","name":"12","time_to":"1天","token":"426426426"}
     */

    private String id;
    private AppDrugremindBean app_drugremind;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public AppDrugremindBean getApp_drugremind() {
        return app_drugremind;
    }

    public void setApp_drugremind(AppDrugremindBean app_drugremind) {
        this.app_drugremind = app_drugremind;
    }

    public static class AppDrugremindBean {
        /**
         * alert_mode : 3
         * app_contact_id :
         * count : 1颗
         * eat_count : 每日一次
         * eat_time : 2017-08-09 17:07:43
         * name : 12
         * time_to : 1天
         * token : 426426426
         */

        private int alert_mode;
        private String app_contact_id;
        private String count;
        private String eat_count;
        private String eat_time;
        private String name;
        private String time_to;
        private String token;

        public int getAlert_mode() {
            return alert_mode;
        }

        public void setAlert_mode(int alert_mode) {
            this.alert_mode = alert_mode;
        }

        public String getApp_contact_id() {
            return app_contact_id;
        }

        public void setApp_contact_id(String app_contact_id) {
            this.app_contact_id = app_contact_id;
        }

        public String getCount() {
            return count;
        }

        public void setCount(String count) {
            this.count = count;
        }

        public String getEat_count() {
            return eat_count;
        }

        public void setEat_count(String eat_count) {
            this.eat_count = eat_count;
        }

        public String getEat_time() {
            return eat_time;
        }

        public void setEat_time(String eat_time) {
            this.eat_time = eat_time;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getTime_to() {
            return time_to;
        }

        public void setTime_to(String time_to) {
            this.time_to = time_to;
        }

        public String getToken() {
            return token;
        }

        public void setToken(String token) {
            this.token = token;
        }
    }
}
