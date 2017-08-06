package com.renqi.takemedicine.event;

/**
 * Created by Xu Wei on 2017/6/28.
 */

public interface BaseEvents {
 void    setObject(Object obj);
    Object getObject();
    //事件定义
    enum CommonEvent implements BaseEvents {

        SENDREMARKSDRUGBESTS,
        SENDREMARKSFOODLIST,
        SENDREMARKSWATERLIST
        ,SENDREMARKSPEICALLIST
        ,SENDREMARKSTIMELIST,
        BACK;
        private Object obj;
        @Override
        public void setObject(Object obj) {
            this.obj = obj;
        }
        @Override
        public Object getObject() {
            return obj;
        }
    }
    // ... 其他事件定义
    enum myEvent implements BaseEvents {
         SENDREMARKSDRUGBESTS
        ,SENDREMARKSFOODLIST
        ,SENDREMARKSWATERLIST
        ,SENDREMARKSPEICALLIST
        ,SENDREMARKSTIMELIST;

        private Object obj;
        @Override
        public void setObject(Object obj) {
            this.obj = obj;
        }
        @Override
        public Object getObject() {
            return obj;
        }
    }
    enum sendRemarks implements BaseEvents{
        SEND_REMARKS;
        private Object obj;
        @Override
        public void setObject(Object obj) {
         this.obj=obj;
        }

        @Override
        public Object getObject() {
            return obj;
        }
    }
    enum sendParamsToOnlineConsultingFragment implements BaseEvents{
        ShowQA,
        ShowDemo;
        private Object obj;
        @Override
        public void setObject(Object obj) {
            this.obj=obj;
        }

        @Override
        public Object getObject() {
            return obj;
        }
    }

}
