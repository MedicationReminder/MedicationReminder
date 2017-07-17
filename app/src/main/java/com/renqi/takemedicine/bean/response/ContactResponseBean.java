package com.renqi.takemedicine.bean.response;

import java.util.List;

/**
 * Created by zsj on 2017/7/17.
 */

public class ContactResponseBean {

    private List<AppContactBean> app_contact;

    public List<AppContactBean> getApp_contact() {
        return app_contact;
    }

    public void setApp_contact(List<AppContactBean> app_contact) {
        this.app_contact = app_contact;
    }

    public static class AppContactBean {
        /**
         * id : 596c5755391af04a66b5a3d0
         * name : '小屋'
         * device_token : 426426426
         * relation : 盆友
         * phone : 123456
         */

        private String id;
        private String name;
        private String device_token;
        private String relation;
        private String phone;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getDevice_token() {
            return device_token;
        }

        public void setDevice_token(String device_token) {
            this.device_token = device_token;
        }

        public String getRelation() {
            return relation;
        }

        public void setRelation(String relation) {
            this.relation = relation;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }
    }
}
