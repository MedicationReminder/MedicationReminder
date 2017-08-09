package com.renqi.takemedicine.bean.response;

/**
 * Created by zsj on 2017/8/9.
 */

public class EditContactResponseBean {

    /**
     * app_contact : {"id":"5983e1e3391af004118a253f","name":"'小屋'","device_token":"426426426","relation":"盆友","phone":"123456"}
     * message : 成功
     * status : 200
     */

    private AppContactBean app_contact;
    private String message;
    private int status;

    public AppContactBean getApp_contact() {
        return app_contact;
    }

    public void setApp_contact(AppContactBean app_contact) {
        this.app_contact = app_contact;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public static class AppContactBean {
        /**
         * id : 5983e1e3391af004118a253f
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
