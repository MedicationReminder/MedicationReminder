package com.renqi.takemedicine.bean.response;

import java.util.List;

/**
 * Created by zsj on 2017/7/26.
 */

public class SearchDrugStoresResponseBean {

    /**
     * stores : [{"id":"56fe326fe8f1112c78db48f0","image":"http://syso.qiniudn.com/nopicture/drugstore.jpg","name":"上海海王星辰药房有限公司","star":3,"location":"31.174528375825,121.43438410273","disance":12954.304,"license":"","telephone":"","url":"","is_medical_insurance":false,"is_24":true,"is_transport":false},{"id":"56fe3246e8f1112c78db4225","image":"http://syso.qiniudn.com/nopicture/drugstore.jpg","name":"上海海王星辰药房有限公司七莘路二店","star":3,"location":"31.165007204415,121.3483041569","disance":12946.813,"license":"","telephone":"","url":"","is_medical_insurance":false,"is_24":true,"is_transport":false},{"id":"56fe3246e8f1112c78db421d","image":"http://syso.qiniudn.com/nopicture/drugstore.jpg","name":"上海海王星辰药房有限公司七莘路店","star":3,"location":"31.155952164997,121.35839958372","disance":12948.032,"license":"","telephone":"","url":"","is_medical_insurance":false,"is_24":true,"is_transport":false},{"id":"56fe322be8f1112c78db3d7d","image":"http://syso.qiniudn.com/nopicture/drugstore.jpg","name":"上海海王星辰药房有限公司万航渡路店","star":3,"location":"31.230634570899,121.42634031588","disance":12951.692,"license":"","telephone":"","url":"","is_medical_insurance":false,"is_24":true,"is_transport":false},{"id":"56fe326ee8f1112c78db48dc","image":"http://syso.qiniudn.com/nopicture/drugstore.jpg","name":"上海海王星辰药房有限公司上中路店","star":3,"location":"31.143145284613,121.43826711813","disance":12955.709,"license":"","telephone":"","url":"","is_medical_insurance":false,"is_24":true,"is_transport":false},{"id":"56fe3261e8f1112c78db469b","image":"http://syso.qiniudn.com/nopicture/drugstore.jpg","name":"上海海王星辰药房有限公司东新路店","star":3,"location":"31.248443157678,121.43388437538","disance":12951.777,"license":"","telephone":"","url":"","is_medical_insurance":false,"is_24":true,"is_transport":false},{"id":"56fe3234e8f1112c78db3f18","image":"http://syso.qiniudn.com/nopicture/drugstore.jpg","name":"上海海王星辰药房有限公司临潼路店","star":3,"location":"31.259610735513,121.51954409389","disance":12959.162,"license":"","telephone":"","url":"","is_medical_insurance":false,"is_24":true,"is_transport":false},{"id":"56fe3278e8f1112c78db4a7d","image":"http://syso.qiniudn.com/nopicture/drugstore.jpg","name":"上海海王星辰药房有限公司交城路店","star":3,"location":"31.299391492563,121.43200754568","disance":12949.894,"license":"","telephone":"","url":"","is_medical_insurance":false,"is_24":true,"is_transport":false},{"id":"56fe3266e8f1112c78db4787","image":"http://syso.qiniudn.com/nopicture/drugstore.jpg","name":"上海海王星辰药房有限公司公园路店","star":3,"location":"31.154785727842,121.12710028778","disance":12927.071,"license":"","telephone":"","url":"","is_medical_insurance":false,"is_24":true,"is_transport":false},{"id":"56fe3248e8f1112c78db426c","image":"http://syso.qiniudn.com/nopicture/drugstore.jpg","name":"上海海王星辰药房有限公司兴梅路店","star":3,"location":"31.112355515364,121.41864758681","disance":12954.959,"license":"","telephone":"","url":"","is_medical_insurance":false,"is_24":true,"is_transport":false},{"id":"56fe3256e8f1112c78db44bf","image":"http://syso.qiniudn.com/nopicture/drugstore.jpg","name":"上海海王星辰药房有限公司利津路店","star":3,"location":"31.280439623458,121.59600031314","disance":12965.381,"license":"","telephone":"","url":"","is_medical_insurance":false,"is_24":true,"is_transport":false},{"id":"56fe326de8f1112c78db48a4","image":"http://syso.qiniudn.com/nopicture/drugstore.jpg","name":"上海海王星辰药房有限公司医学院路店","star":3,"location":"31.206418699816,121.46653679253","disance":12956.149,"license":"","telephone":"","url":"","is_medical_insurance":false,"is_24":true,"is_transport":false},{"id":"56fe3246e8f1112c78db4226","image":"http://syso.qiniudn.com/nopicture/drugstore.jpg","name":"上海海王星辰药房有限公司华光路店","star":3,"location":"31.193031248943,121.38836822333","disance":12949.51,"license":"","telephone":"","url":"","is_medical_insurance":false,"is_24":true,"is_transport":false},{"id":"56fe3225e8f1112c78db3c73","image":"http://syso.qiniudn.com/nopicture/drugstore.jpg","name":"上海海王星辰药房有限公司华灵路店","star":3,"location":"31.280947607797,121.4290299324","disance":12950.245,"license":"","telephone":"","url":"","is_medical_insurance":false,"is_24":true,"is_transport":false},{"id":"56fe3231e8f1112c78db3e70","image":"http://syso.qiniudn.com/nopicture/drugstore.jpg","name":"上海海王星辰药房有限公司南亭公路店","star":3,"location":"30.912315698922,121.45151460915","disance":12964.628,"license":"","telephone":"","url":"","is_medical_insurance":false,"is_24":true,"is_transport":false},{"id":"56fe326fe8f1112c78db4904","image":"http://syso.qiniudn.com/nopicture/drugstore.jpg","name":"上海海王星辰药房有限公司双峰路店","star":3,"location":"31.187417548963,121.45538055674","disance":12955.776,"license":"","telephone":"","url":"","is_medical_insurance":false,"is_24":true,"is_transport":false},{"id":"56fe324de8f1112c78db434a","image":"http://syso.qiniudn.com/nopicture/drugstore.jpg","name":"上海海王星辰药房有限公司古美路二店","star":3,"location":"31.148698665372,121.41237229401","disance":12953.173,"license":"","telephone":"","url":"","is_medical_insurance":false,"is_24":true,"is_transport":false},{"id":"56fe3245e8f1112c78db41f6","image":"http://syso.qiniudn.com/nopicture/drugstore.jpg","name":"上海海王星辰药房有限公司古美路店","star":3,"location":"31.153578097042,121.41017602687","disance":12952.81,"license":"","telephone":"","url":"","is_medical_insurance":false,"is_24":true,"is_transport":false},{"id":"56fe3272e8f1112c78db4991","image":"http://syso.qiniudn.com/nopicture/drugstore.jpg","name":"上海海王星辰药房有限公司吉浦路店","star":3,"location":"31.31091039805,121.49713712058","disance":12955.402,"license":"","telephone":"","url":"","is_medical_insurance":false,"is_24":true,"is_transport":false},{"id":"56fe3224e8f1112c78db3c50","image":"http://syso.qiniudn.com/nopicture/drugstore.jpg","name":"上海海王星辰药房有限公司同泰北路店","star":3,"location":"31.387162853764,121.50317112852","disance":12953.373,"license":"","telephone":"","url":"","is_medical_insurance":false,"is_24":true,"is_transport":false},{"id":"56fe323ae8f1112c78db4006","image":"http://syso.qiniudn.com/nopicture/drugstore.jpg","name":"上海海王星辰药房有限公司城中路店","star":3,"location":"31.379387852827,121.26020814401","disance":12931.653,"license":"","telephone":"","url":"","is_medical_insurance":false,"is_24":true,"is_transport":false},{"id":"56fe322ae8f1112c78db3d5c","image":"http://syso.qiniudn.com/nopicture/drugstore.jpg","name":"上海海王星辰药房有限公司天山支路店","star":3,"location":"31.220471128496,121.403972868","disance":12950.005,"license":"","telephone":"","url":"","is_medical_insurance":false,"is_24":true,"is_transport":false},{"id":"56fe322ce8f1112c78db3db2","image":"http://syso.qiniudn.com/nopicture/drugstore.jpg","name":"上海海王星辰药房有限公司安顺路店","star":3,"location":"31.206385651505,121.41658795194","disance":12951.621,"license":"","telephone":"","url":"","is_medical_insurance":false,"is_24":true,"is_transport":false},{"id":"56fe322be8f1112c78db3d84","image":"http://syso.qiniudn.com/nopicture/drugstore.jpg","name":"上海海王星辰药房有限公司安龙路店","star":3,"location":"31.217889311267,121.39394436495","disance":12949.183,"license":"","telephone":"","url":"","is_medical_insurance":false,"is_24":true,"is_transport":false},{"id":"56fe324ee8f1112c78db4360","image":"http://syso.qiniudn.com/nopicture/drugstore.jpg","name":"上海海王星辰药房有限公司宝城路店","star":3,"location":"31.114318353572,121.39834139076","disance":12953.05,"license":"","telephone":"","url":"","is_medical_insurance":false,"is_24":true,"is_transport":false}]
     * meta : {"count":2217,"page":1,"size":10}
     */

    private MetaBean meta;
    private List<StoresBean> stores;

    public MetaBean getMeta() {
        return meta;
    }

    public void setMeta(MetaBean meta) {
        this.meta = meta;
    }

    public List<StoresBean> getStores() {
        return stores;
    }

    public void setStores(List<StoresBean> stores) {
        this.stores = stores;
    }

    public static class MetaBean {
        /**
         * count : 2217
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

    public static class StoresBean {
        /**
         * id : 56fe326fe8f1112c78db48f0
         * image : http://syso.qiniudn.com/nopicture/drugstore.jpg
         * name : 上海海王星辰药房有限公司
         * star : 3
         * location : 31.174528375825,121.43438410273
         * disance : 12954.304
         * license :
         * telephone :
         * url :
         * is_medical_insurance : false
         * is_24 : true
         * is_transport : false
         */

        private String id;
        private String image;
        private String name;
        private int star;
        private String location;
        private double disance;
        private String license;
        private String telephone;
        private String url;
        private boolean is_medical_insurance;
        private boolean is_24;
        private boolean is_transport;

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

        public String getLocation() {
            return location;
        }

        public void setLocation(String location) {
            this.location = location;
        }

        public double getDisance() {
            return disance;
        }

        public void setDisance(double disance) {
            this.disance = disance;
        }

        public String getLicense() {
            return license;
        }

        public void setLicense(String license) {
            this.license = license;
        }

        public String getTelephone() {
            return telephone;
        }

        public void setTelephone(String telephone) {
            this.telephone = telephone;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public boolean isIs_medical_insurance() {
            return is_medical_insurance;
        }

        public void setIs_medical_insurance(boolean is_medical_insurance) {
            this.is_medical_insurance = is_medical_insurance;
        }

        public boolean isIs_24() {
            return is_24;
        }

        public void setIs_24(boolean is_24) {
            this.is_24 = is_24;
        }

        public boolean isIs_transport() {
            return is_transport;
        }

        public void setIs_transport(boolean is_transport) {
            this.is_transport = is_transport;
        }
    }
}
