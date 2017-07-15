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



}
