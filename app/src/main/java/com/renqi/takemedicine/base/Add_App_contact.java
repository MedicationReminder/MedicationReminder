package com.renqi.takemedicine.base;

import java.util.List;

/**
 * Created by Xu Wei on 2017/7/15.
 */

public class Add_App_contact {

   public  app_contact app_contact;
   public Add_App_contact(app_contact app_contact)
   {
       this.app_contact=app_contact;
   }

    public static class app_contact{
        public app_contact(String name, String token, String relation, String phone, int user_type){
            this.name=name;
            this.token=token;
            this.relation=relation;
            this.phone=phone;
            this.user_type=user_type;
        }
        public String name;//表示联系人
        public String token;//表示机器号
        public String relation;// relation关系
        public String phone;// phone电话
        public int user_type;// user_type表示的是用户的类型{0:"个人使用",1:"药店使用",2:"护士使用",3:"医生使用"}
    }

    public Add_App_contact.app_drugremind app_drugremind;
    public Add_App_contact(app_drugremind app_drugremind) {this.app_drugremind=app_drugremind;}

    public static class app_drugremind {

        public app_drugremind(String token, String name, String count, String alert_mode, String eat_count, String eat_time, String time_to, String app_contact_id) {
            this.token = token;
            this.name = name;
            this.count = count;
            this.alert_mode = alert_mode;
            this.eat_count = eat_count;
            this.eat_time = eat_time;
            this.time_to = time_to;
            this.app_contact_id = app_contact_id;
        }
        /*
           token,用户设备号
           name #药品的名称
           count#用药的数量
           eat_count#服用的次数
           eat_time #吃药的时间
           time_to #间隔时间
           alert_mode #提醒方式 1，表示铃声，2表示震动，3表示短信
           app_contact_id 提醒人的Id*/
        public String token;
        public String name;
        public String count;
        public String alert_mode;
        public String eat_count;
        public String eat_time;
        public String time_to;
        public String app_contact_id;

    }

}
