package com.renqi.takemedicine.bean.request;

/**
 * Created by zsj on 2017/8/9.
 */

public class EditContactRequestBean {

    /**
     * id : 5983e1e3391af004118a253f
     * app_contact : {"name":"'小屋'","token":"426426426","phone":"123456","relation":"盆友"}
     */

    private String id;
    private AppContactBean app_contact;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public AppContactBean getApp_contact() {
        return app_contact;
    }

    public void setApp_contact(AppContactBean app_contact) {
        this.app_contact = app_contact;
    }

    public static class AppContactBean {
        /**
         * name : '小屋'
         * token : 426426426
         * phone : 123456
         * relation : 盆友
         */

        private String name;
        private String token;
        private String phone;
        private String relation;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getToken() {
            return token;
        }

        public void setToken(String token) {
            this.token = token;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public String getRelation() {
            return relation;
        }

        public void setRelation(String relation) {
            this.relation = relation;
        }
    }
}
