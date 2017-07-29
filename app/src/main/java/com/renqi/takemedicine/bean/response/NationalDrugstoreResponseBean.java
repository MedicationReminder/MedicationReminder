package com.renqi.takemedicine.bean.response;

import java.util.List;

/**
 * Created by zsj on 2017/7/25.
 */

public class NationalDrugstoreResponseBean {

    /**
     * hosptials : [{"id":"56f631efe8f1112870cc725b","image":"http://syso.qiniudn.com/nopicture/hospital.jpg","name":"乌兰浩特市医院","star":0,"characteristic_departments":"无","location":"46.116943359375,122.081535339355","telephone":"：0482-8217010","click_count":54},{"id":"56f6323fe8f1112870cc8072","image":"http://syso.qiniudn.com/nopicture/hospital.jpg","name":"市医院","star":0,"characteristic_departments":"脑外科  泌尿外科  骨科  神经内科  肿瘤科  ","location":"46.116943359375,122.081535339355","telephone":"：0482-8217010","click_count":31},{"id":"56f632dae8f1112870cc9ae1","image":"http://syso.qiniudn.com/nopicture/hospital.jpg","name":"南海区第二人民医院","star":0,"characteristic_departments":"脑外科  心胸外科  肝胆外科  泌尿外科  肛肠外科  骨科  烧伤科  乳腺外科  ","location":"23.0386695861816,113.203414916992","telephone":"：(总机)0757-88386000 (急诊)88387000 (热线)88387001","click_count":37},{"id":"56f6315ce8f1112870cc5928","image":"http://syso.qiniudn.com/nopicture/hospital.jpg","name":"郑州第二中医院","star":0,"characteristic_departments":"妇科  肿瘤科  中医科  ","location":"34.7599678039551,113.669944763184","telephone":"（甲状腺科4006-0371-58 ），（妇科0371-66225120）","click_count":56},{"id":"56f6315de8f1112870cc5942","image":"http://syso.qiniudn.com/nopicture/hospital.jpg","name":"郑州153医院","star":0,"characteristic_departments":"无","location":"34.758472442627,113.629959106445","telephone":"（86）371-67774155 67972225","click_count":49},{"id":"56f633fee8f1112870cccc9a","image":"http://syso.qiniudn.com/nopicture/hospital.jpg","name":"抚顺矿业集团总医院","star":0,"characteristic_departments":"无","location":"41.8623886108398,123.912925720215","telephone":"（86）0413-2533889","click_count":30},{"id":"56f633d5e8f1112870ccc5a9","image":"http://syso.qiniudn.com/hospitals1202010071047.jpg","name":"广州医学院第二附属医院","star":0,"characteristic_departments":"心血管内科、消化内科、血液内科、神经内科、耳鼻咽喉科、外科、心外科、神经外科、急诊科、肝胆外科","location":"23.0973434448242,113.284355163574","telephone":"（8620）34152299","click_count":94},{"id":"56f6329be8f1112870cc9018","image":"http://syso.qiniudn.com/nopicture/hospital.jpg","name":"仁伯爵综合医院","star":0,"characteristic_departments":"无","location":"22.1964340209961,113.553039550781","telephone":"（853）313731","click_count":31},{"id":"56f63338e8f1112870ccaac7","image":"http://syso.qiniudn.com/hospitals120201005113143468154.jpg","name":"酒钢医院","star":0,"characteristic_departments":"无","location":"39.8042488098145,98.273796081543","telephone":"（0937）6712120","click_count":70},{"id":"56f633c3e8f1112870ccc2a6","image":"http://syso.qiniudn.com/nopicture/hospital.jpg","name":"海南男科妇科疾病中心","star":0,"characteristic_departments":"男科","location":"20.0370979309082,110.36026763916","telephone":"（0898）65376311　65328311","click_count":37},{"id":"56f633c3e8f1112870ccc2a7","image":"http://syso.qiniudn.com/nopicture/hospital.jpg","name":"海南男科妇科疾病治疗中心","star":0,"characteristic_departments":"妇科  男科  ","location":"20.0370979309082,110.36026763916","telephone":"（0898）65376311　65328311","click_count":26},{"id":"56f6331ee8f1112870cca64c","image":"http://syso.qiniudn.com/hospital/20140408150532.jpg","name":"李权中医医院","star":0,"characteristic_departments":"无","location":"22.2053966522217,113.553642272949","telephone":"（0853）314117","click_count":31},{"id":"56f63438e8f1112870ccd6b4","image":"http://syso.qiniudn.com/hospitals1202010052983775.jpg","name":"川北医学院附属医院","star":0,"characteristic_departments":"（0817）2222856 2262110","location":"30.7987804412842,106.092575073242","telephone":"（0817）2222856 2262110","click_count":77},{"id":"56f6337fe8f1112870ccb717","image":"http://syso.qiniudn.com/nopicture/hospital.jpg","name":"环江毛南族自治县人民医院","star":0,"characteristic_departments":"骨科  心脑血管神经内科","location":"24.8401012420654,108.262886047363","telephone":"（0778）8821362","click_count":26},{"id":"56f63212e8f1112870cc787a","image":"http://syso.qiniudn.com/nopicture/hospital.jpg","name":"特美口腔?万江?华南摩尔店","star":0,"characteristic_departments":"口腔科","location":"23.0385513305664,113.713005065918","telephone":"（0769）2638 2333","click_count":30},{"id":"56f63212e8f1112870cc787b","image":"http://syso.qiniudn.com/nopicture/hospital.jpg","name":"特美口腔?健民店","star":0,"characteristic_departments":"口腔科","location":"23.0071105957031,113.773818969727","telephone":"（0769）2313 6886","click_count":31},{"id":"56f63212e8f1112870cc787c","image":"http://syso.qiniudn.com/nopicture/hospital.jpg","name":"特美口腔","star":0,"characteristic_departments":"口腔科","location":"23.0359649658203,113.784255981445","telephone":"（0769）2309 2222?","click_count":25},{"id":"56f6345de8f1112870ccdce1","image":"http://syso.qiniudn.com/nopicture/hospital.jpg","name":"禅医","star":0,"characteristic_departments":"无","location":"23.0116767883301,113.09098815918","telephone":"（0757）82703120","click_count":35},{"id":"56f63452e8f1112870ccdb07","image":"http://syso.qiniudn.com/nopicture/hospital.jpg","name":"长阳县高家堰镇卫生院","star":0,"characteristic_departments":"无","location":"30.6043891906738,111.063758850098","telephone":"（0717）5487461-8000","click_count":31},{"id":"56f63172e8f1112870cc5ce4","image":"http://syso.qiniudn.com/nopicture/hospital.jpg","name":"枣庄市市立三院","star":0,"characteristic_departments":"无","location":"34.8582649230957,117.581497192383","telephone":"（0632）3699307 3221432","click_count":29},{"id":"56f633ffe8f1112870ccccb2","image":"http://syso.qiniudn.com/nopicture/hospital.jpg","name":"福州伟达中医肿瘤医院","star":0,"characteristic_departments":"无","location":"26.1085987091064,119.300926208496","telephone":"（0591）87986799","click_count":28},{"id":"56f63139e8f1112870cc5328","image":"http://syso.qiniudn.com/hospitals120201005136068.jpg","name":"舟山市人民医院","star":0,"characteristic_departments":"骨科  烧伤科  呼吸内科  内分泌科  妇产科  肿瘤科  ","location":",","telephone":"（0580）2558005","click_count":79},{"id":"56f63166e8f1112870cc5aec","image":"http://syso.qiniudn.com/nopicture/hospital.jpg","name":"浙江金华广福医院","star":0,"characteristic_departments":"无","location":"29.4653415679932,119.899421691895","telephone":"（0579）82287828","click_count":32},{"id":"56f631f7e8f1112870cc73b7","image":"http://syso.qiniudn.com/nopicture/hospital.jpg","name":"潍坊口腔医院","star":0,"characteristic_departments":"口腔科","location":"36.713695526123,119.106567382813","telephone":"（0536）8322202","click_count":36},{"id":"56f63492e8f1112870cce60b","image":"http://syso.qiniudn.com/nopicture/hospital.jpg","name":"安丘市开发区医院","star":0,"characteristic_departments":"无","location":"36.3350448608398,119.155990600586","telephone":"（0536）4930506","click_count":34}]
     * meta : {"count":49388,"page":1,"size":10}
     */

    private MetaBean meta;
    private List<HosptialsBean> hosptials;

    public MetaBean getMeta() {
        return meta;
    }

    public void setMeta(MetaBean meta) {
        this.meta = meta;
    }

    public List<HosptialsBean> getHosptials() {
        return hosptials;
    }

    public void setHosptials(List<HosptialsBean> hosptials) {
        this.hosptials = hosptials;
    }

    public static class MetaBean {
        /**
         * count : 49388
         * page : 1
         * size : 10
         */

        private int count;
        private int page;
        private int size;

        public int getCount() {
            return count;
        }

        public void setCount(int count) {
            this.count = count;
        }

        public int getPage() {
            return page;
        }

        public void setPage(int page) {
            this.page = page;
        }

        public int getSize() {
            return size;
        }

        public void setSize(int size) {
            this.size = size;
        }
    }

    public static class HosptialsBean {
        /**
         * id : 56f631efe8f1112870cc725b
         * image : http://syso.qiniudn.com/nopicture/hospital.jpg
         * name : 乌兰浩特市医院
         * star : 0
         * characteristic_departments : 无
         * location : 46.116943359375,122.081535339355
         * telephone : ：0482-8217010
         * click_count : 54
         */

        private String id;
        private String image;
        private String name;
        private int star;
        private String characteristic_departments;
        private String location;
        private String telephone;
        private int click_count;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getStar() {
            return star;
        }

        public void setStar(int star) {
            this.star = star;
        }

        public String getCharacteristic_departments() {
            return characteristic_departments;
        }

        public void setCharacteristic_departments(String characteristic_departments) {
            this.characteristic_departments = characteristic_departments;
        }

        public String getLocation() {
            return location;
        }

        public void setLocation(String location) {
            this.location = location;
        }

        public String getTelephone() {
            return telephone;
        }

        public void setTelephone(String telephone) {
            this.telephone = telephone;
        }

        public int getClick_count() {
            return click_count;
        }

        public void setClick_count(int click_count) {
            this.click_count = click_count;
        }
    }
}
