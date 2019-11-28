package com.homelife.xhs.bean;

import java.util.List;

public class ViewData {

    private List<TechBean> tech;
    private List<AutoBean> auto;
    private List<MoneyBean> money;
    private List<SportsBean> sports;
    private List<DyBean> dy;
    private List<WarBean> war;
    private List<EntBean> ent;
    private List<ToutiaoBean> toutiao;

    public List<TechBean> getTech() {
        return tech;
    }

    public void setTech(List<TechBean> tech) {
        this.tech = tech;
    }

    public List<AutoBean> getAuto() {
        return auto;
    }

    public void setAuto(List<AutoBean> auto) {
        this.auto = auto;
    }

    public List<MoneyBean> getMoney() {
        return money;
    }

    public void setMoney(List<MoneyBean> money) {
        this.money = money;
    }

    public List<SportsBean> getSports() {
        return sports;
    }

    public void setSports(List<SportsBean> sports) {
        this.sports = sports;
    }

    public List<DyBean> getDy() {
        return dy;
    }

    public void setDy(List<DyBean> dy) {
        this.dy = dy;
    }

    public List<WarBean> getWar() {
        return war;
    }

    public void setWar(List<WarBean> war) {
        this.war = war;
    }

    public List<EntBean> getEnt() {
        return ent;
    }

    public void setEnt(List<EntBean> ent) {
        this.ent = ent;
    }

    public List<ToutiaoBean> getToutiao() {
        return toutiao;
    }

    public void setToutiao(List<ToutiaoBean> toutiao) {
        this.toutiao = toutiao;
    }

    public static class TechBean {
        /**
         * liveInfo : null
         * tcount : 0
         * picInfo : [{"ref":null,"width":null,"url":"http://cms-bucket.ws.126.net/2019/06/03/f8557471b0814fc8afe32ddd7af79fdf.png","height":null}]
         * docid : EGOP5I3I00097U7S
         * videoInfo : null
         * channel : tech
         * link : https://3g.163.com/all/article/EGOP5I3I00097U7S.html
         * source : 环球时报-环球网
         * title : 台积电：美商务部没有派员到公司调查供货华为
         * type : doc
         * imgsrcFrom : null
         * imgsrc3gtype : 1
         * unlikeReason : 重复、旧闻/6,内容质量差/6
         * isTop : null
         * digest : 【环球网报道记者张丽媛】台湾《经济日报》3日引述消息人士话称
         * typeid :
         * addata : null
         * tag :
         * category : 科技
         * ptime : 2019-06-03 15:45:09
         */

        private Object liveInfo;
        private int tcount;
        private String docid;
        private Object videoInfo;
        private String channel;
        private String link;
        private String source;
        private String title;
        private String type;
        private Object imgsrcFrom;
        private int imgsrc3gtype;
        private String unlikeReason;
        private Object isTop;
        private String digest;
        private String typeid;
        private Object addata;
        private String tag;
        private String category;
        private String ptime;
        private List<PicInfoBean> picInfo;

        public Object getLiveInfo() {
            return liveInfo;
        }

        public void setLiveInfo(Object liveInfo) {
            this.liveInfo = liveInfo;
        }

        public int getTcount() {
            return tcount;
        }

        public void setTcount(int tcount) {
            this.tcount = tcount;
        }

        public String getDocid() {
            return docid;
        }

        public void setDocid(String docid) {
            this.docid = docid;
        }

        public Object getVideoInfo() {
            return videoInfo;
        }

        public void setVideoInfo(Object videoInfo) {
            this.videoInfo = videoInfo;
        }

        public String getChannel() {
            return channel;
        }

        public void setChannel(String channel) {
            this.channel = channel;
        }

        public String getLink() {
            return link;
        }

        public void setLink(String link) {
            this.link = link;
        }

        public String getSource() {
            return source;
        }

        public void setSource(String source) {
            this.source = source;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public Object getImgsrcFrom() {
            return imgsrcFrom;
        }

        public void setImgsrcFrom(Object imgsrcFrom) {
            this.imgsrcFrom = imgsrcFrom;
        }

        public int getImgsrc3gtype() {
            return imgsrc3gtype;
        }

        public void setImgsrc3gtype(int imgsrc3gtype) {
            this.imgsrc3gtype = imgsrc3gtype;
        }

        public String getUnlikeReason() {
            return unlikeReason;
        }

        public void setUnlikeReason(String unlikeReason) {
            this.unlikeReason = unlikeReason;
        }

        public Object getIsTop() {
            return isTop;
        }

        public void setIsTop(Object isTop) {
            this.isTop = isTop;
        }

        public String getDigest() {
            return digest;
        }

        public void setDigest(String digest) {
            this.digest = digest;
        }

        public String getTypeid() {
            return typeid;
        }

        public void setTypeid(String typeid) {
            this.typeid = typeid;
        }

        public Object getAddata() {
            return addata;
        }

        public void setAddata(Object addata) {
            this.addata = addata;
        }

        public String getTag() {
            return tag;
        }

        public void setTag(String tag) {
            this.tag = tag;
        }

        public String getCategory() {
            return category;
        }

        public void setCategory(String category) {
            this.category = category;
        }

        public String getPtime() {
            return ptime;
        }

        public void setPtime(String ptime) {
            this.ptime = ptime;
        }

        public List<PicInfoBean> getPicInfo() {
            return picInfo;
        }

        public void setPicInfo(List<PicInfoBean> picInfo) {
            this.picInfo = picInfo;
        }

        public static class PicInfoBean {
            /**
             * ref : null
             * width : null
             * url : http://cms-bucket.ws.126.net/2019/06/03/f8557471b0814fc8afe32ddd7af79fdf.png
             * height : null
             */

            private Object ref;
            private Object width;
            private String url;
            private Object height;

            public Object getRef() {
                return ref;
            }

            public void setRef(Object ref) {
                this.ref = ref;
            }

            public Object getWidth() {
                return width;
            }

            public void setWidth(Object width) {
                this.width = width;
            }

            public String getUrl() {
                return url;
            }

            public void setUrl(String url) {
                this.url = url;
            }

            public Object getHeight() {
                return height;
            }

            public void setHeight(Object height) {
                this.height = height;
            }
        }
    }

    public static class AutoBean {
        /**
         * liveInfo : null
         * tcount : 6
         * picInfo : [{"ref":null,"width":null,"url":"http://cms-bucket.ws.126.net/2019/06/03/9970fd6fdcdc4533aee35a6b5bc0875a.jpeg","height":null}]
         * docid : EGOTUHFK0008856R
         * videoInfo : null
         * channel : auto
         * link : https://3g.163.com/all/article/EGOTUHFK0008856R.html
         * source : 网易汽车
         * title : 31.39万元起 全新宝马3系公布预售价
         * type : doc
         * imgsrcFrom : null
         * imgsrc3gtype : 1
         * unlikeReason : 重复、旧闻/6,内容质量差/6
         * isTop : null
         * digest : 宝马官方发布了全新一代BME 3系的预售信息，预售车型共有3
         * typeid :
         * addata : null
         * tag :
         * category : 汽车
         * ptime : 2019-06-03 17:08:42
         */

        private Object liveInfo;
        private int tcount;
        private String docid;
        private Object videoInfo;
        private String channel;
        private String link;
        private String source;
        private String title;
        private String type;
        private Object imgsrcFrom;
        private int imgsrc3gtype;
        private String unlikeReason;
        private Object isTop;
        private String digest;
        private String typeid;
        private Object addata;
        private String tag;
        private String category;
        private String ptime;
        private List<PicInfoBeanX> picInfo;

        public Object getLiveInfo() {
            return liveInfo;
        }

        public void setLiveInfo(Object liveInfo) {
            this.liveInfo = liveInfo;
        }

        public int getTcount() {
            return tcount;
        }

        public void setTcount(int tcount) {
            this.tcount = tcount;
        }

        public String getDocid() {
            return docid;
        }

        public void setDocid(String docid) {
            this.docid = docid;
        }

        public Object getVideoInfo() {
            return videoInfo;
        }

        public void setVideoInfo(Object videoInfo) {
            this.videoInfo = videoInfo;
        }

        public String getChannel() {
            return channel;
        }

        public void setChannel(String channel) {
            this.channel = channel;
        }

        public String getLink() {
            return link;
        }

        public void setLink(String link) {
            this.link = link;
        }

        public String getSource() {
            return source;
        }

        public void setSource(String source) {
            this.source = source;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public Object getImgsrcFrom() {
            return imgsrcFrom;
        }

        public void setImgsrcFrom(Object imgsrcFrom) {
            this.imgsrcFrom = imgsrcFrom;
        }

        public int getImgsrc3gtype() {
            return imgsrc3gtype;
        }

        public void setImgsrc3gtype(int imgsrc3gtype) {
            this.imgsrc3gtype = imgsrc3gtype;
        }

        public String getUnlikeReason() {
            return unlikeReason;
        }

        public void setUnlikeReason(String unlikeReason) {
            this.unlikeReason = unlikeReason;
        }

        public Object getIsTop() {
            return isTop;
        }

        public void setIsTop(Object isTop) {
            this.isTop = isTop;
        }

        public String getDigest() {
            return digest;
        }

        public void setDigest(String digest) {
            this.digest = digest;
        }

        public String getTypeid() {
            return typeid;
        }

        public void setTypeid(String typeid) {
            this.typeid = typeid;
        }

        public Object getAddata() {
            return addata;
        }

        public void setAddata(Object addata) {
            this.addata = addata;
        }

        public String getTag() {
            return tag;
        }

        public void setTag(String tag) {
            this.tag = tag;
        }

        public String getCategory() {
            return category;
        }

        public void setCategory(String category) {
            this.category = category;
        }

        public String getPtime() {
            return ptime;
        }

        public void setPtime(String ptime) {
            this.ptime = ptime;
        }

        public List<PicInfoBeanX> getPicInfo() {
            return picInfo;
        }

        public void setPicInfo(List<PicInfoBeanX> picInfo) {
            this.picInfo = picInfo;
        }

        public static class PicInfoBeanX {
            /**
             * ref : null
             * width : null
             * url : http://cms-bucket.ws.126.net/2019/06/03/9970fd6fdcdc4533aee35a6b5bc0875a.jpeg
             * height : null
             */

            private Object ref;
            private Object width;
            private String url;
            private Object height;

            public Object getRef() {
                return ref;
            }

            public void setRef(Object ref) {
                this.ref = ref;
            }

            public Object getWidth() {
                return width;
            }

            public void setWidth(Object width) {
                this.width = width;
            }

            public String getUrl() {
                return url;
            }

            public void setUrl(String url) {
                this.url = url;
            }

            public Object getHeight() {
                return height;
            }

            public void setHeight(Object height) {
                this.height = height;
            }
        }
    }

    public static class MoneyBean {
        /**
         * liveInfo : null
         * tcount : 42
         * picInfo : [{"ref":null,"width":null,"url":"http://cms-bucket.ws.126.net/2019/06/03/2d2dbe60bc324ed1971265442c0afe14.jpeg","height":null}]
         * docid : EGONCE3K00258105
         * videoInfo : null
         * channel : money
         * link : https://3g.163.com/all/special/S1559541988946.html
         * source : 证券时报网
         * title : 5G商用牌照发放时间大幅提前 概念股狂欢
         * type : special
         * imgsrcFrom : null
         * imgsrc3gtype : 1
         * unlikeReason : 重复、旧闻/6,内容质量差/6
         * isTop : null
         * digest : 新华社6月3日午间消息，工业和信息化部将于近期发放5G商用牌
         * typeid : S1559541988946
         * addata : null
         * tag : 专题
         * category : 财经
         * ptime : 2019-06-03 15:13:57
         */

        private Object liveInfo;
        private int tcount;
        private String docid;
        private Object videoInfo;
        private String channel;
        private String link;
        private String source;
        private String title;
        private String type;
        private Object imgsrcFrom;
        private int imgsrc3gtype;
        private String unlikeReason;
        private Object isTop;
        private String digest;
        private String typeid;
        private Object addata;
        private String tag;
        private String category;
        private String ptime;
        private List<PicInfoBeanXX> picInfo;

        public Object getLiveInfo() {
            return liveInfo;
        }

        public void setLiveInfo(Object liveInfo) {
            this.liveInfo = liveInfo;
        }

        public int getTcount() {
            return tcount;
        }

        public void setTcount(int tcount) {
            this.tcount = tcount;
        }

        public String getDocid() {
            return docid;
        }

        public void setDocid(String docid) {
            this.docid = docid;
        }

        public Object getVideoInfo() {
            return videoInfo;
        }

        public void setVideoInfo(Object videoInfo) {
            this.videoInfo = videoInfo;
        }

        public String getChannel() {
            return channel;
        }

        public void setChannel(String channel) {
            this.channel = channel;
        }

        public String getLink() {
            return link;
        }

        public void setLink(String link) {
            this.link = link;
        }

        public String getSource() {
            return source;
        }

        public void setSource(String source) {
            this.source = source;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public Object getImgsrcFrom() {
            return imgsrcFrom;
        }

        public void setImgsrcFrom(Object imgsrcFrom) {
            this.imgsrcFrom = imgsrcFrom;
        }

        public int getImgsrc3gtype() {
            return imgsrc3gtype;
        }

        public void setImgsrc3gtype(int imgsrc3gtype) {
            this.imgsrc3gtype = imgsrc3gtype;
        }

        public String getUnlikeReason() {
            return unlikeReason;
        }

        public void setUnlikeReason(String unlikeReason) {
            this.unlikeReason = unlikeReason;
        }

        public Object getIsTop() {
            return isTop;
        }

        public void setIsTop(Object isTop) {
            this.isTop = isTop;
        }

        public String getDigest() {
            return digest;
        }

        public void setDigest(String digest) {
            this.digest = digest;
        }

        public String getTypeid() {
            return typeid;
        }

        public void setTypeid(String typeid) {
            this.typeid = typeid;
        }

        public Object getAddata() {
            return addata;
        }

        public void setAddata(Object addata) {
            this.addata = addata;
        }

        public String getTag() {
            return tag;
        }

        public void setTag(String tag) {
            this.tag = tag;
        }

        public String getCategory() {
            return category;
        }

        public void setCategory(String category) {
            this.category = category;
        }

        public String getPtime() {
            return ptime;
        }

        public void setPtime(String ptime) {
            this.ptime = ptime;
        }

        public List<PicInfoBeanXX> getPicInfo() {
            return picInfo;
        }

        public void setPicInfo(List<PicInfoBeanXX> picInfo) {
            this.picInfo = picInfo;
        }

        public static class PicInfoBeanXX {
            /**
             * ref : null
             * width : null
             * url : http://cms-bucket.ws.126.net/2019/06/03/2d2dbe60bc324ed1971265442c0afe14.jpeg
             * height : null
             */

            private Object ref;
            private Object width;
            private String url;
            private Object height;

            public Object getRef() {
                return ref;
            }

            public void setRef(Object ref) {
                this.ref = ref;
            }

            public Object getWidth() {
                return width;
            }

            public void setWidth(Object width) {
                this.width = width;
            }

            public String getUrl() {
                return url;
            }

            public void setUrl(String url) {
                this.url = url;
            }

            public Object getHeight() {
                return height;
            }

            public void setHeight(Object height) {
                this.height = height;
            }
        }
    }

    public static class SportsBean {
        /**
         * liveInfo : null
         * tcount : 1367
         * picInfo : [{"ref":null,"width":null,"url":"http://cms-bucket.ws.126.net/2019/06/03/19a516f702e24c39968ee8569e71c93a.jpeg","height":null},{"ref":null,"width":null,"url":"http://cms-bucket.ws.126.net/2019/06/03/c865da9803204b61acdc6a23439ab69a.jpeg","height":null},{"ref":null,"width":null,"url":"http://cms-bucket.ws.126.net/2019/06/03/880188ae4df348498c9135d06b5948a4.jpeg","height":null}]
         * docid : EGO0L6PJwhbai
         * videoInfo : null
         * channel : sports
         * link : https://3g.163.com/all/photoview/0005/165789.html
         * source : 视觉中国
         * title : 勇士北境逆转代价大 一哥出手就索命
         * type : photoset
         * imgsrcFrom : null
         * imgsrc3gtype : 2
         * unlikeReason : 重复、旧闻/6,内容质量差/6
         * isTop : null
         * digest :
         * typeid : 4TM10005|165789
         * addata : null
         * tag : 图集
         * category : 推荐
         * ptime : 2019-06-03 08:36:47
         */

        private Object liveInfo;
        private int tcount;
        private String docid;
        private Object videoInfo;
        private String channel;
        private String link;
        private String source;
        private String title;
        private String type;
        private Object imgsrcFrom;
        private int imgsrc3gtype;
        private String unlikeReason;
        private Object isTop;
        private String digest;
        private String typeid;
        private Object addata;
        private String tag;
        private String category;
        private String ptime;
        private List<PicInfoBeanXXX> picInfo;

        public Object getLiveInfo() {
            return liveInfo;
        }

        public void setLiveInfo(Object liveInfo) {
            this.liveInfo = liveInfo;
        }

        public int getTcount() {
            return tcount;
        }

        public void setTcount(int tcount) {
            this.tcount = tcount;
        }

        public String getDocid() {
            return docid;
        }

        public void setDocid(String docid) {
            this.docid = docid;
        }

        public Object getVideoInfo() {
            return videoInfo;
        }

        public void setVideoInfo(Object videoInfo) {
            this.videoInfo = videoInfo;
        }

        public String getChannel() {
            return channel;
        }

        public void setChannel(String channel) {
            this.channel = channel;
        }

        public String getLink() {
            return link;
        }

        public void setLink(String link) {
            this.link = link;
        }

        public String getSource() {
            return source;
        }

        public void setSource(String source) {
            this.source = source;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public Object getImgsrcFrom() {
            return imgsrcFrom;
        }

        public void setImgsrcFrom(Object imgsrcFrom) {
            this.imgsrcFrom = imgsrcFrom;
        }

        public int getImgsrc3gtype() {
            return imgsrc3gtype;
        }

        public void setImgsrc3gtype(int imgsrc3gtype) {
            this.imgsrc3gtype = imgsrc3gtype;
        }

        public String getUnlikeReason() {
            return unlikeReason;
        }

        public void setUnlikeReason(String unlikeReason) {
            this.unlikeReason = unlikeReason;
        }

        public Object getIsTop() {
            return isTop;
        }

        public void setIsTop(Object isTop) {
            this.isTop = isTop;
        }

        public String getDigest() {
            return digest;
        }

        public void setDigest(String digest) {
            this.digest = digest;
        }

        public String getTypeid() {
            return typeid;
        }

        public void setTypeid(String typeid) {
            this.typeid = typeid;
        }

        public Object getAddata() {
            return addata;
        }

        public void setAddata(Object addata) {
            this.addata = addata;
        }

        public String getTag() {
            return tag;
        }

        public void setTag(String tag) {
            this.tag = tag;
        }

        public String getCategory() {
            return category;
        }

        public void setCategory(String category) {
            this.category = category;
        }

        public String getPtime() {
            return ptime;
        }

        public void setPtime(String ptime) {
            this.ptime = ptime;
        }

        public List<PicInfoBeanXXX> getPicInfo() {
            return picInfo;
        }

        public void setPicInfo(List<PicInfoBeanXXX> picInfo) {
            this.picInfo = picInfo;
        }

        public static class PicInfoBeanXXX {
            /**
             * ref : null
             * width : null
             * url : http://cms-bucket.ws.126.net/2019/06/03/19a516f702e24c39968ee8569e71c93a.jpeg
             * height : null
             */

            private Object ref;
            private Object width;
            private String url;
            private Object height;

            public Object getRef() {
                return ref;
            }

            public void setRef(Object ref) {
                this.ref = ref;
            }

            public Object getWidth() {
                return width;
            }

            public void setWidth(Object width) {
                this.width = width;
            }

            public String getUrl() {
                return url;
            }

            public void setUrl(String url) {
                this.url = url;
            }

            public Object getHeight() {
                return height;
            }

            public void setHeight(Object height) {
                this.height = height;
            }
        }
    }

    public static class DyBean {
        /**
         * liveInfo : null
         * tcount : 24
         * picInfo : [{"ref":null,"width":null,"url":"http://cms-bucket.ws.126.net/2019/06/03/1b8103047e6140719a8d5f53759fe2fb.jpeg","height":null},{"ref":null,"width":null,"url":"http://cms-bucket.ws.126.net/2019/06/03/591f88eb2ae54dd8b1e44f0d795fd491.jpeg","height":null},{"ref":null,"width":null,"url":"http://cms-bucket.ws.126.net/2019/06/03/c4d46f30266c432e90e86097409755e8.jpeg","height":null}]
         * docid : EGOOPIT80517ALKH
         * videoInfo : null
         * channel : dy
         * link : https://3g.163.com/all/article/EGOOPIT80517ALKH.html
         * source : 同相
         * title : 5G带来的“基础设施”升级，对视听行业应用场景带来了什么突破？
         * type : doc
         * imgsrcFrom : null
         * imgsrc3gtype : 2
         * unlikeReason : 重复、旧闻/6,内容质量差/6
         * isTop : null
         * digest : 锋芒智库丨指月在刚刚过去的第七届中国网络视听大会上，5G是被
         * typeid :
         * addata : null
         * tag :
         * category : 推荐
         * ptime : 2019-06-03 15:39:43
         */

        private Object liveInfo;
        private int tcount;
        private String docid;
        private Object videoInfo;
        private String channel;
        private String link;
        private String source;
        private String title;
        private String type;
        private Object imgsrcFrom;
        private int imgsrc3gtype;
        private String unlikeReason;
        private Object isTop;
        private String digest;
        private String typeid;
        private Object addata;
        private String tag;
        private String category;
        private String ptime;
        private List<PicInfoBeanXXXX> picInfo;

        public Object getLiveInfo() {
            return liveInfo;
        }

        public void setLiveInfo(Object liveInfo) {
            this.liveInfo = liveInfo;
        }

        public int getTcount() {
            return tcount;
        }

        public void setTcount(int tcount) {
            this.tcount = tcount;
        }

        public String getDocid() {
            return docid;
        }

        public void setDocid(String docid) {
            this.docid = docid;
        }

        public Object getVideoInfo() {
            return videoInfo;
        }

        public void setVideoInfo(Object videoInfo) {
            this.videoInfo = videoInfo;
        }

        public String getChannel() {
            return channel;
        }

        public void setChannel(String channel) {
            this.channel = channel;
        }

        public String getLink() {
            return link;
        }

        public void setLink(String link) {
            this.link = link;
        }

        public String getSource() {
            return source;
        }

        public void setSource(String source) {
            this.source = source;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public Object getImgsrcFrom() {
            return imgsrcFrom;
        }

        public void setImgsrcFrom(Object imgsrcFrom) {
            this.imgsrcFrom = imgsrcFrom;
        }

        public int getImgsrc3gtype() {
            return imgsrc3gtype;
        }

        public void setImgsrc3gtype(int imgsrc3gtype) {
            this.imgsrc3gtype = imgsrc3gtype;
        }

        public String getUnlikeReason() {
            return unlikeReason;
        }

        public void setUnlikeReason(String unlikeReason) {
            this.unlikeReason = unlikeReason;
        }

        public Object getIsTop() {
            return isTop;
        }

        public void setIsTop(Object isTop) {
            this.isTop = isTop;
        }

        public String getDigest() {
            return digest;
        }

        public void setDigest(String digest) {
            this.digest = digest;
        }

        public String getTypeid() {
            return typeid;
        }

        public void setTypeid(String typeid) {
            this.typeid = typeid;
        }

        public Object getAddata() {
            return addata;
        }

        public void setAddata(Object addata) {
            this.addata = addata;
        }

        public String getTag() {
            return tag;
        }

        public void setTag(String tag) {
            this.tag = tag;
        }

        public String getCategory() {
            return category;
        }

        public void setCategory(String category) {
            this.category = category;
        }

        public String getPtime() {
            return ptime;
        }

        public void setPtime(String ptime) {
            this.ptime = ptime;
        }

        public List<PicInfoBeanXXXX> getPicInfo() {
            return picInfo;
        }

        public void setPicInfo(List<PicInfoBeanXXXX> picInfo) {
            this.picInfo = picInfo;
        }

        public static class PicInfoBeanXXXX {
            /**
             * ref : null
             * width : null
             * url : http://cms-bucket.ws.126.net/2019/06/03/1b8103047e6140719a8d5f53759fe2fb.jpeg
             * height : null
             */

            private Object ref;
            private Object width;
            private String url;
            private Object height;

            public Object getRef() {
                return ref;
            }

            public void setRef(Object ref) {
                this.ref = ref;
            }

            public Object getWidth() {
                return width;
            }

            public void setWidth(Object width) {
                this.width = width;
            }

            public String getUrl() {
                return url;
            }

            public void setUrl(String url) {
                this.url = url;
            }

            public Object getHeight() {
                return height;
            }

            public void setHeight(Object height) {
                this.height = height;
            }
        }
    }

    public static class WarBean {
        /**
         * liveInfo : null
         * tcount : 2301
         * picInfo : [{"ref":null,"width":null,"url":"http://cms-bucket.ws.126.net/2019/06/03/dd253522e71b466e8ba244b6c7d06093.jpeg","height":null}]
         * docid : 0001set2302060
         * videoInfo : null
         * channel : war
         * link : https://3g.163.com/all/photoview/0001/2302060.html
         * source : 环球网
         * title : 美F-35在伊朗周边晃荡 有"小弟"护送
         * type : photoset
         * imgsrcFrom : null
         * imgsrc3gtype : 3
         * unlikeReason : 重复、旧闻/6,内容质量差/6
         * isTop : null
         * digest :
         * typeid : 4T8E0001|2302060
         * addata : null
         * tag : 图集
         * category : 推荐
         * ptime : 2019-06-03 10:11:38
         */

        private Object liveInfo;
        private int tcount;
        private String docid;
        private Object videoInfo;
        private String channel;
        private String link;
        private String source;
        private String title;
        private String type;
        private Object imgsrcFrom;
        private int imgsrc3gtype;
        private String unlikeReason;
        private Object isTop;
        private String digest;
        private String typeid;
        private Object addata;
        private String tag;
        private String category;
        private String ptime;
        private List<PicInfoBeanXXXXX> picInfo;

        public Object getLiveInfo() {
            return liveInfo;
        }

        public void setLiveInfo(Object liveInfo) {
            this.liveInfo = liveInfo;
        }

        public int getTcount() {
            return tcount;
        }

        public void setTcount(int tcount) {
            this.tcount = tcount;
        }

        public String getDocid() {
            return docid;
        }

        public void setDocid(String docid) {
            this.docid = docid;
        }

        public Object getVideoInfo() {
            return videoInfo;
        }

        public void setVideoInfo(Object videoInfo) {
            this.videoInfo = videoInfo;
        }

        public String getChannel() {
            return channel;
        }

        public void setChannel(String channel) {
            this.channel = channel;
        }

        public String getLink() {
            return link;
        }

        public void setLink(String link) {
            this.link = link;
        }

        public String getSource() {
            return source;
        }

        public void setSource(String source) {
            this.source = source;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public Object getImgsrcFrom() {
            return imgsrcFrom;
        }

        public void setImgsrcFrom(Object imgsrcFrom) {
            this.imgsrcFrom = imgsrcFrom;
        }

        public int getImgsrc3gtype() {
            return imgsrc3gtype;
        }

        public void setImgsrc3gtype(int imgsrc3gtype) {
            this.imgsrc3gtype = imgsrc3gtype;
        }

        public String getUnlikeReason() {
            return unlikeReason;
        }

        public void setUnlikeReason(String unlikeReason) {
            this.unlikeReason = unlikeReason;
        }

        public Object getIsTop() {
            return isTop;
        }

        public void setIsTop(Object isTop) {
            this.isTop = isTop;
        }

        public String getDigest() {
            return digest;
        }

        public void setDigest(String digest) {
            this.digest = digest;
        }

        public String getTypeid() {
            return typeid;
        }

        public void setTypeid(String typeid) {
            this.typeid = typeid;
        }

        public Object getAddata() {
            return addata;
        }

        public void setAddata(Object addata) {
            this.addata = addata;
        }

        public String getTag() {
            return tag;
        }

        public void setTag(String tag) {
            this.tag = tag;
        }

        public String getCategory() {
            return category;
        }

        public void setCategory(String category) {
            this.category = category;
        }

        public String getPtime() {
            return ptime;
        }

        public void setPtime(String ptime) {
            this.ptime = ptime;
        }

        public List<PicInfoBeanXXXXX> getPicInfo() {
            return picInfo;
        }

        public void setPicInfo(List<PicInfoBeanXXXXX> picInfo) {
            this.picInfo = picInfo;
        }

        public static class PicInfoBeanXXXXX {
            /**
             * ref : null
             * width : null
             * url : http://cms-bucket.ws.126.net/2019/06/03/dd253522e71b466e8ba244b6c7d06093.jpeg
             * height : null
             */

            private Object ref;
            private Object width;
            private String url;
            private Object height;

            public Object getRef() {
                return ref;
            }

            public void setRef(Object ref) {
                this.ref = ref;
            }

            public Object getWidth() {
                return width;
            }

            public void setWidth(Object width) {
                this.width = width;
            }

            public String getUrl() {
                return url;
            }

            public void setUrl(String url) {
                this.url = url;
            }

            public Object getHeight() {
                return height;
            }

            public void setHeight(Object height) {
                this.height = height;
            }
        }
    }

    public static class EntBean {
        /**
         * liveInfo : null
         * tcount : 1559
         * picInfo : [{"ref":null,"width":null,"url":"http://cms-bucket.ws.126.net/2019/06/03/c33bc408274c4f1893926e0bb56b3582.jpeg","height":null},{"ref":null,"width":null,"url":"http://cms-bucket.ws.126.net/2019/06/03/b167149c7a2a4749b0ffce9a67978f96.png","height":null},{"ref":null,"width":null,"url":"http://cms-bucket.ws.126.net/2019/06/03/93e4537a56a64b73ad4b762ef8c90228.png","height":null}]
         * docid : 0003set666398
         * videoInfo : null
         * channel : ent
         * link : https://3g.163.com/all/photoview/0003/666398.html
         * source : 网易娱乐
         * title : 郎朗妻子童年照曝光 德韩混血颜值高
         * type : photoset
         * imgsrcFrom : null
         * imgsrc3gtype : 2
         * unlikeReason : 重复、旧闻/6,内容质量差/6
         * isTop : null
         * digest :
         * typeid : 00AJ0003|666398
         * addata : null
         * tag : 图集
         * category : 推荐
         * ptime : 2019-06-03 10:59:38
         */

        private Object liveInfo;
        private int tcount;
        private String docid;
        private Object videoInfo;
        private String channel;
        private String link;
        private String source;
        private String title;
        private String type;
        private Object imgsrcFrom;
        private int imgsrc3gtype;
        private String unlikeReason;
        private Object isTop;
        private String digest;
        private String typeid;
        private Object addata;
        private String tag;
        private String category;
        private String ptime;
        private List<PicInfoBeanXXXXXX> picInfo;

        public Object getLiveInfo() {
            return liveInfo;
        }

        public void setLiveInfo(Object liveInfo) {
            this.liveInfo = liveInfo;
        }

        public int getTcount() {
            return tcount;
        }

        public void setTcount(int tcount) {
            this.tcount = tcount;
        }

        public String getDocid() {
            return docid;
        }

        public void setDocid(String docid) {
            this.docid = docid;
        }

        public Object getVideoInfo() {
            return videoInfo;
        }

        public void setVideoInfo(Object videoInfo) {
            this.videoInfo = videoInfo;
        }

        public String getChannel() {
            return channel;
        }

        public void setChannel(String channel) {
            this.channel = channel;
        }

        public String getLink() {
            return link;
        }

        public void setLink(String link) {
            this.link = link;
        }

        public String getSource() {
            return source;
        }

        public void setSource(String source) {
            this.source = source;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public Object getImgsrcFrom() {
            return imgsrcFrom;
        }

        public void setImgsrcFrom(Object imgsrcFrom) {
            this.imgsrcFrom = imgsrcFrom;
        }

        public int getImgsrc3gtype() {
            return imgsrc3gtype;
        }

        public void setImgsrc3gtype(int imgsrc3gtype) {
            this.imgsrc3gtype = imgsrc3gtype;
        }

        public String getUnlikeReason() {
            return unlikeReason;
        }

        public void setUnlikeReason(String unlikeReason) {
            this.unlikeReason = unlikeReason;
        }

        public Object getIsTop() {
            return isTop;
        }

        public void setIsTop(Object isTop) {
            this.isTop = isTop;
        }

        public String getDigest() {
            return digest;
        }

        public void setDigest(String digest) {
            this.digest = digest;
        }

        public String getTypeid() {
            return typeid;
        }

        public void setTypeid(String typeid) {
            this.typeid = typeid;
        }

        public Object getAddata() {
            return addata;
        }

        public void setAddata(Object addata) {
            this.addata = addata;
        }

        public String getTag() {
            return tag;
        }

        public void setTag(String tag) {
            this.tag = tag;
        }

        public String getCategory() {
            return category;
        }

        public void setCategory(String category) {
            this.category = category;
        }

        public String getPtime() {
            return ptime;
        }

        public void setPtime(String ptime) {
            this.ptime = ptime;
        }

        public List<PicInfoBeanXXXXXX> getPicInfo() {
            return picInfo;
        }

        public void setPicInfo(List<PicInfoBeanXXXXXX> picInfo) {
            this.picInfo = picInfo;
        }

        public static class PicInfoBeanXXXXXX {
            /**
             * ref : null
             * width : null
             * url : http://cms-bucket.ws.126.net/2019/06/03/c33bc408274c4f1893926e0bb56b3582.jpeg
             * height : null
             */

            private Object ref;
            private Object width;
            private String url;
            private Object height;

            public Object getRef() {
                return ref;
            }

            public void setRef(Object ref) {
                this.ref = ref;
            }

            public Object getWidth() {
                return width;
            }

            public void setWidth(Object width) {
                this.width = width;
            }

            public String getUrl() {
                return url;
            }

            public void setUrl(String url) {
                this.url = url;
            }

            public Object getHeight() {
                return height;
            }

            public void setHeight(Object height) {
                this.height = height;
            }
        }
    }

    public static class ToutiaoBean {
        /**
         * liveInfo : null
         * tcount : 362
         * picInfo : []
         * docid : EGOFMMRE000189FH
         * videoInfo : null
         * channel :
         * link : https://3g.163.com/all/article/EGOFMMRE000189FH.html
         * source : 新华网
         * title : 习近平对垃圾分类工作作出重要指示
         * type : doc
         * imgsrcFrom : null
         * imgsrc3gtype : 0
         * unlikeReason : 重复、旧闻/6,内容质量差/6
         * isTop : true
         * digest : 新华社北京6月3日电中共中央总书记、国家主席、中央军委主席习
         * typeid :
         * addata : null
         * tag :
         * category : 新闻
         * ptime : 2019-06-03 12:59:45
         */

        private Object liveInfo;
        private int tcount;
        private String docid;
        private Object videoInfo;
        private String channel;
        private String link;
        private String source;
        private String title;
        private String type;
        private Object imgsrcFrom;
        private int imgsrc3gtype;
        private String unlikeReason;
        private boolean isTop;
        private String digest;
        private String typeid;
        private Object addata;
        private String tag;
        private String category;
        private String ptime;
        private List<?> picInfo;

        public Object getLiveInfo() {
            return liveInfo;
        }

        public void setLiveInfo(Object liveInfo) {
            this.liveInfo = liveInfo;
        }

        public int getTcount() {
            return tcount;
        }

        public void setTcount(int tcount) {
            this.tcount = tcount;
        }

        public String getDocid() {
            return docid;
        }

        public void setDocid(String docid) {
            this.docid = docid;
        }

        public Object getVideoInfo() {
            return videoInfo;
        }

        public void setVideoInfo(Object videoInfo) {
            this.videoInfo = videoInfo;
        }

        public String getChannel() {
            return channel;
        }

        public void setChannel(String channel) {
            this.channel = channel;
        }

        public String getLink() {
            return link;
        }

        public void setLink(String link) {
            this.link = link;
        }

        public String getSource() {
            return source;
        }

        public void setSource(String source) {
            this.source = source;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public Object getImgsrcFrom() {
            return imgsrcFrom;
        }

        public void setImgsrcFrom(Object imgsrcFrom) {
            this.imgsrcFrom = imgsrcFrom;
        }

        public int getImgsrc3gtype() {
            return imgsrc3gtype;
        }

        public void setImgsrc3gtype(int imgsrc3gtype) {
            this.imgsrc3gtype = imgsrc3gtype;
        }

        public String getUnlikeReason() {
            return unlikeReason;
        }

        public void setUnlikeReason(String unlikeReason) {
            this.unlikeReason = unlikeReason;
        }

        public boolean isIsTop() {
            return isTop;
        }

        public void setIsTop(boolean isTop) {
            this.isTop = isTop;
        }

        public String getDigest() {
            return digest;
        }

        public void setDigest(String digest) {
            this.digest = digest;
        }

        public String getTypeid() {
            return typeid;
        }

        public void setTypeid(String typeid) {
            this.typeid = typeid;
        }

        public Object getAddata() {
            return addata;
        }

        public void setAddata(Object addata) {
            this.addata = addata;
        }

        public String getTag() {
            return tag;
        }

        public void setTag(String tag) {
            this.tag = tag;
        }

        public String getCategory() {
            return category;
        }

        public void setCategory(String category) {
            this.category = category;
        }

        public String getPtime() {
            return ptime;
        }

        public void setPtime(String ptime) {
            this.ptime = ptime;
        }

        public List<?> getPicInfo() {
            return picInfo;
        }

        public void setPicInfo(List<?> picInfo) {
            this.picInfo = picInfo;
        }
    }
}
