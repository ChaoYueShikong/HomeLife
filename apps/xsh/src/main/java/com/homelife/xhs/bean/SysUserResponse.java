package com.homelife.xhs.bean;

import java.util.List;

/**
 * 功能：
 * 作者：HDM
 * 创建时间：2019/10/8 17:31
 */
public class SysUserResponse {

    /**
     * code : 0
     * data : {"userInfo":{"gender":0,"phone":"15868159329","name":"邵晓艳","avatar":{"image":"","url":"/gpy/c0445adf77ce4fffb729d554f4c99a4e.jpg"},"userid":10814},"ads":[{"imgurl":"http://122.112.196.223:80/cppt-web/cpptupload/img/2018-09-07/b116162f472b5864497606937149ff33919346.jpeg","contenturl":"http://122.112.196.223:80/cppt-web/cpptupload/a/7e32ef8b7d6b9dd03511f82874da7af9.html","title":"心生活"},{"imgurl":"http://122.112.196.223:80/cppt-web/cpptupload/img/2018-12-04/a664719908739ee479423a815b03ac097ca4e9.jpg","contenturl":"http://122.112.196.223:80/cppt-web/cpptupload/a/a454fd59478b792744d204e1750e1236.html","title":"心生活"},{"imgurl":"http://122.112.196.223:80/cppt-web/cpptupload/img/2018-11-08/624662276a3b15143031fc99ffa575b0ebad1f.jpg","contenturl":null,"title":"心生活"},{"imgurl":"http://122.112.196.223:80/cppt-web/cpptupload/img/2019-06-28/91ae343e713e1ad4690b54a4c9bca9eae0e161.png","contenturl":null,"title":"未来社区建筑场景\u2014\u2014集约开发"}],"districts":[{"rooms":[{"roomlabel":"1102","unitlabel":"心生活21","roomid":10757},{"roomlabel":"2202","unitlabel":"心生活31","roomid":10779},{"roomlabel":"1101","unitlabel":"心生活51","roomid":10755},{"roomlabel":"1101","unitlabel":"心生活41","roomid":10770},{"roomlabel":"1101","unitlabel":"心生活11","roomid":10750}],"districtid":10289,"districtlabel":"心生活测试1"},{"rooms":[{"roomlabel":"1102","unitlabel":"九匠31","roomid":10830},{"roomlabel":"1102","unitlabel":"九匠11","roomid":10831},{"roomlabel":"1102","unitlabel":"九匠41","roomid":10828},{"roomlabel":"1102","unitlabel":"九匠21","roomid":10829}],"districtid":10331,"districtlabel":"九匠"},{"rooms":[{"roomlabel":"1101","unitlabel":"小巷 客厅11","roomid":10920},{"roomlabel":"1102","unitlabel":"小巷 客厅11","roomid":10950}],"districtid":10380,"districtlabel":"小巷客厅"}]}
     */

    private int code;
    private DataBean data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * userInfo : {"gender":0,"phone":"15868159329","name":"邵晓艳","avatar":{"image":"","url":"/gpy/c0445adf77ce4fffb729d554f4c99a4e.jpg"},"userid":10814}
         * ads : [{"imgurl":"http://122.112.196.223:80/cppt-web/cpptupload/img/2018-09-07/b116162f472b5864497606937149ff33919346.jpeg","contenturl":"http://122.112.196.223:80/cppt-web/cpptupload/a/7e32ef8b7d6b9dd03511f82874da7af9.html","title":"心生活"},{"imgurl":"http://122.112.196.223:80/cppt-web/cpptupload/img/2018-12-04/a664719908739ee479423a815b03ac097ca4e9.jpg","contenturl":"http://122.112.196.223:80/cppt-web/cpptupload/a/a454fd59478b792744d204e1750e1236.html","title":"心生活"},{"imgurl":"http://122.112.196.223:80/cppt-web/cpptupload/img/2018-11-08/624662276a3b15143031fc99ffa575b0ebad1f.jpg","contenturl":null,"title":"心生活"},{"imgurl":"http://122.112.196.223:80/cppt-web/cpptupload/img/2019-06-28/91ae343e713e1ad4690b54a4c9bca9eae0e161.png","contenturl":null,"title":"未来社区建筑场景\u2014\u2014集约开发"}]
         * districts : [{"rooms":[{"roomlabel":"1102","unitlabel":"心生活21","roomid":10757},{"roomlabel":"2202","unitlabel":"心生活31","roomid":10779},{"roomlabel":"1101","unitlabel":"心生活51","roomid":10755},{"roomlabel":"1101","unitlabel":"心生活41","roomid":10770},{"roomlabel":"1101","unitlabel":"心生活11","roomid":10750}],"districtid":10289,"districtlabel":"心生活测试1"},{"rooms":[{"roomlabel":"1102","unitlabel":"九匠31","roomid":10830},{"roomlabel":"1102","unitlabel":"九匠11","roomid":10831},{"roomlabel":"1102","unitlabel":"九匠41","roomid":10828},{"roomlabel":"1102","unitlabel":"九匠21","roomid":10829}],"districtid":10331,"districtlabel":"九匠"},{"rooms":[{"roomlabel":"1101","unitlabel":"小巷 客厅11","roomid":10920},{"roomlabel":"1102","unitlabel":"小巷 客厅11","roomid":10950}],"districtid":10380,"districtlabel":"小巷客厅"}]
         */

        private UserInfoBean userInfo;
        private List<AdsBean> ads;
        private List<DistrictsBean> districts;

        public UserInfoBean getUserInfo() {
            return userInfo;
        }

        public void setUserInfo(UserInfoBean userInfo) {
            this.userInfo = userInfo;
        }

        public List<AdsBean> getAds() {
            return ads;
        }

        public void setAds(List<AdsBean> ads) {
            this.ads = ads;
        }

        public List<DistrictsBean> getDistricts() {
            return districts;
        }

        public void setDistricts(List<DistrictsBean> districts) {
            this.districts = districts;
        }

        public static class UserInfoBean {
            /**
             * gender : 0
             * phone : 15868159329
             * name : 邵晓艳
             * avatar : {"image":"","url":"/gpy/c0445adf77ce4fffb729d554f4c99a4e.jpg"}
             * userid : 10814
             */

            private int gender;
            private String phone;
            private String name;
            private AvatarBean avatar;
            private int userid;

            public int getGender() {
                return gender;
            }

            public void setGender(int gender) {
                this.gender = gender;
            }

            public String getPhone() {
                return phone;
            }

            public void setPhone(String phone) {
                this.phone = phone;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public AvatarBean getAvatar() {
                return avatar;
            }

            public void setAvatar(AvatarBean avatar) {
                this.avatar = avatar;
            }

            public int getUserid() {
                return userid;
            }

            public void setUserid(int userid) {
                this.userid = userid;
            }

            public static class AvatarBean {
                /**
                 * image :
                 * url : /gpy/c0445adf77ce4fffb729d554f4c99a4e.jpg
                 */

                private String image;
                private String url;

                public String getImage() {
                    return image;
                }

                public void setImage(String image) {
                    this.image = image;
                }

                public String getUrl() {
                    return url;
                }

                public void setUrl(String url) {
                    this.url = url;
                }
            }
        }

        public static class AdsBean {
            /**
             * imgurl : http://122.112.196.223:80/cppt-web/cpptupload/img/2018-09-07/b116162f472b5864497606937149ff33919346.jpeg
             * contenturl : http://122.112.196.223:80/cppt-web/cpptupload/a/7e32ef8b7d6b9dd03511f82874da7af9.html
             * title : 心生活
             */

            private String imgurl;
            private String contenturl;
            private String title;

            public String getImgurl() {
                return imgurl;
            }

            public void setImgurl(String imgurl) {
                this.imgurl = imgurl;
            }

            public String getContenturl() {
                return contenturl;
            }

            public void setContenturl(String contenturl) {
                this.contenturl = contenturl;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }
        }

        public static class DistrictsBean {
            /**
             * rooms : [{"roomlabel":"1102","unitlabel":"心生活21","roomid":10757},{"roomlabel":"2202","unitlabel":"心生活31","roomid":10779},{"roomlabel":"1101","unitlabel":"心生活51","roomid":10755},{"roomlabel":"1101","unitlabel":"心生活41","roomid":10770},{"roomlabel":"1101","unitlabel":"心生活11","roomid":10750}]
             * districtid : 10289
             * districtlabel : 心生活测试1
             */

            private int districtid;
            private String districtlabel;
            private List<RoomsBean> rooms;

            public int getDistrictid() {
                return districtid;
            }

            public void setDistrictid(int districtid) {
                this.districtid = districtid;
            }

            public String getDistrictlabel() {
                return districtlabel;
            }

            public void setDistrictlabel(String districtlabel) {
                this.districtlabel = districtlabel;
            }

            public List<RoomsBean> getRooms() {
                return rooms;
            }

            public void setRooms(List<RoomsBean> rooms) {
                this.rooms = rooms;
            }

            public static class RoomsBean {
                /**
                 * roomlabel : 1102
                 * unitlabel : 心生活21
                 * roomid : 10757
                 */

                private String roomlabel;
                private String unitlabel;
                private int roomid;

                public String getRoomlabel() {
                    return roomlabel;
                }

                public void setRoomlabel(String roomlabel) {
                    this.roomlabel = roomlabel;
                }

                public String getUnitlabel() {
                    return unitlabel;
                }

                public void setUnitlabel(String unitlabel) {
                    this.unitlabel = unitlabel;
                }

                public int getRoomid() {
                    return roomid;
                }

                public void setRoomid(int roomid) {
                    this.roomid = roomid;
                }
            }
        }
    }
}
