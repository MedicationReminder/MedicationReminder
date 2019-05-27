package com.renqi.takemedicine.bean.response;

import java.util.List;

/**
 * Created by zsj on 2017/7/15.
 */

public class KitDetialsResponseBean {

    private List<AppDrugremindsBean> app_drugreminds;
    private List<AppRegisterRemindsBean> app_register_reminds;

    public List<AppDrugremindsBean> getApp_drugreminds() {
        return app_drugreminds;
    }

    public void setApp_drugreminds(List<AppDrugremindsBean> app_drugreminds) {
        this.app_drugreminds = app_drugreminds;
    }

    public List<AppRegisterRemindsBean> getApp_register_reminds() {
        return app_register_reminds;
    }

    public void setApp_register_reminds(List<AppRegisterRemindsBean> app_register_reminds) {
        this.app_register_reminds = app_register_reminds;
    }

    public static class AppDrugremindsBean {
        /**
         * id : 596ed7c3391af05475538664
         * name : jjj
         * time : 2017-07-19 15:54:53
         * eat_count : 2
         * counts : 1
         * time_for : 3
         */

        private String id;
        private String name;
        private String time;
        private int eat_count;
        private int counts;
        private int time_for;

        @Override
        public String toString() {
            return "{" +
                    "id=\"" + id + '\"' +
                    ", name=\"" + name + '\"' +
                    ", time=\"" + time + '\"' +
                    ", eat_count=\"" + eat_count + '\"'+
                    ", counts=\"" + counts + '\"' +
                    ", time_for=\"" + time_for+ '\"' +
                    '}';
        }

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

        public String getTime() {
            return time;
        }

        public void setTime(String time) {
            this.time = time;
        }

        public int getEat_count() {
            return eat_count;
        }

        public void setEat_count(int eat_count) {
            this.eat_count = eat_count;
        }

        public int getCounts() {
            return counts;
        }

        public void setCounts(int counts) {
            this.counts = counts;
        }

        public int getTime_for() {
            return time_for;
        }

        public void setTime_for(int time_for) {
            this.time_for = time_for;
        }
    }

    public static class AppRegisterRemindsBean {
        /**
         * id : 596ed5bd391af05475538661
         * hosptial_name : jjj
         * time : 2017-07-18 15:54:53
         * characteristic_name : 123
         * doctor_name : 0
         * time_for : 3
         */

        private String id;
        private String hosptial_name;
        private String time;
        private String characteristic_name;
        private String doctor_name;
        private int time_for;

        @Override
        public String toString() {
            return "{" +
                    "id=\"" + id + '\"' +
                    ", hosptial_name=\"" + hosptial_name + '\"' +
                    ", time=\"" + time + '\"' +
                    ", characteristic_name=\"" + characteristic_name + '\"' +
                    ", doctor_name=\"" + doctor_name + '\"' +
                    ", time_for=\"" + time_for + '\"' +
                    '}';
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getHosptial_name() {
            return hosptial_name;
        }

        public void setHosptial_name(String hosptial_name) {
            this.hosptial_name = hosptial_name;
        }

        public String getTime() {
            return time;
        }

        public void setTime(String time) {
            this.time = time;
        }

        public String getCharacteristic_name() {
            return characteristic_name;
        }

        public void setCharacteristic_name(String characteristic_name) {
            this.characteristic_name = characteristic_name;
        }

        public String getDoctor_name() {
            return doctor_name;
        }

        public void setDoctor_name(String doctor_name) {
            this.doctor_name = doctor_name;
        }

        public int getTime_for() {
            return time_for;
        }

        public void setTime_for(int time_for) {
            this.time_for = time_for;
        }
    }
}
