package com.renqi.takemedicine.bean.request;

/**
 * Created by zsj on 2017/8/9.
 */

public class EditRegRemRequestBean {


    /**
     * id : 58118895bc54f41aa766cce3
     * app_registerremind : {"token":"009","hosptial_name":"jjj","characteristic_name":"123","alert_mode":"1","doctor_name":"2dfd","get_time":" 2016-10-27 15:54:53","app_contact_id":"58118895bc54f41aa766cce3"}
     */

    private String id;
    private AppRegisterremindBean app_registerremind;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public AppRegisterremindBean getApp_registerremind() {
        return app_registerremind;
    }

    public void setApp_registerremind(AppRegisterremindBean app_registerremind) {
        this.app_registerremind = app_registerremind;
    }

    public static class AppRegisterremindBean {
        /**
         * token : 009
         * hosptial_name : jjj
         * characteristic_name : 123
         * alert_mode : 1
         * doctor_name : 2dfd
         * get_time :  2016-10-27 15:54:53
         * app_contact_id : 58118895bc54f41aa766cce3
         */

        private String token;
        private String hosptial_name;
        private String characteristic_name;
        private String alert_mode;
        private String doctor_name;
        private String get_time;
        private String app_contact_id;

        public String getToken() {
            return token;
        }

        public void setToken(String token) {
            this.token = token;
        }

        public String getHosptial_name() {
            return hosptial_name;
        }

        public void setHosptial_name(String hosptial_name) {
            this.hosptial_name = hosptial_name;
        }

        public String getCharacteristic_name() {
            return characteristic_name;
        }

        public void setCharacteristic_name(String characteristic_name) {
            this.characteristic_name = characteristic_name;
        }

        public String getAlert_mode() {
            return alert_mode;
        }

        public void setAlert_mode(String alert_mode) {
            this.alert_mode = alert_mode;
        }

        public String getDoctor_name() {
            return doctor_name;
        }

        public void setDoctor_name(String doctor_name) {
            this.doctor_name = doctor_name;
        }

        public String getGet_time() {
            return get_time;
        }

        public void setGet_time(String get_time) {
            this.get_time = get_time;
        }

        public String getApp_contact_id() {
            return app_contact_id;
        }

        public void setApp_contact_id(String app_contact_id) {
            this.app_contact_id = app_contact_id;
        }
    }
}
