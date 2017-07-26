package com.renqi.takemedicine.bean.response;

import java.util.List;

/**
 * Created by Xu Wei on 2017/7/23.
 */

public class App_drugbestsResponseBean {
    public List<App_drugbests> app_drugreminds;



    public  class App_drugbests{
        public String id;
        public String name;
        public String time;
        public String time_code;

        public App_drugbests(String id, String name, String time, String time_code) {
            this.id = id;
            this.name = name;
            this.time = time;
            this.time_code = time_code;
        }
    }




}
