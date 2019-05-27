package com.renqi.takemedicine.app;

/**
 * Created by lzp on 2017/5/4 0004.
 */


public class AppConstants {

    //用户是否禁止权限
   public static  boolean mShowRequestPermission = false;

    //各种变量
    public  static class ToolBarTitle
    {
        public static final String deliveryLogistics="送药物流";
        public static final String remarks="会吃药温馨提醒";
        public static final String medicationReminder="用药提醒";
        public static final String addContacts ="添加联系人";
        public static final String Contacts ="联系人";
        public static final String takemedicationReminder="吃药提醒";
        public static final String  registrationReminder="挂号提醒";
        public static final String  relatedDrugs="相关药品";
        public static final String  relatedDrugs1="搜索药品";
        public static final String  nationalDrugstore="全国药店";
        public static final String  nationalDrugstore1="搜索药店";

        public static final String  medicationIntroduction="用药攻略";
        public static final String  kitDetials="用药提醒详情";
        public static final String  kitDetials2="挂号提醒详情";
        public static final String healthHeadlines="健康头条";

        public static final String doctorBook="查看医生";

        public static final String hospitalEncyclopedia="查看医院";

        public static final String  kitDetialsRemind="切换";
    }
    public static class  iption
    {
        public static final String complete="完成";
    }

    public static  String BASE_ACTION = "http://101.69.181.251/api/v1/";
    public static  String app_contacts="app_contacts";
    public static  String app_drugreminds="app_drugreminds/";
    public static  String DEVICE_TOKENS = "device_tokens";
    public static  String ALL_CONTACTS="app_contacts";
    public static  String DELETE_CONTACTS="app_contacts/destory";
    public static  String APP_CHOICE_NAME="app_choice_name";
    public static  String APP_REGISTERREMINDS="app_registerreminds/";

    public static  String SEARCHDRUGSNAME="/app_drugreminds/search_drugs_name";
    public static  String HOSPTIALS="/app_drugreminds/hosptials";


    public static  String APP_DRUGBEST="app_drugbest";//最佳吃药时间
}
