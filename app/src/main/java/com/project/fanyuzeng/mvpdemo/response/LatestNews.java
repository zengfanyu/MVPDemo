package com.project.fanyuzeng.mvpdemo.response;

import java.util.List;

/**
 * Created by fanyuzeng on 2017/10/20.
 * Function:
 */
public class LatestNews {

    /**
     * date : 20171020
     * stories : [{"images":["https://pic4.zhimg.com/v2-281acaabd1ae0188544610440f38eec3.jpg"],"type":0,"id":9652803,"ga_prefix":"102010","title":"做到这四点，在华尔街工作也能保持旺盛的精力"},{"images":["https://pic3.zhimg.com/v2-b1955856e3cc87bc464dd79e052de8a6.jpg"],"type":0,"id":9653002,"ga_prefix":"102009","title":"给每个人发放「无条件基本收入」会是件好事吗？听听李开复怎么说"},{"images":["https://pic1.zhimg.com/v2-9058b5a1800317747b3a9b1ea0cee378.jpg"],"type":0,"id":9653028,"ga_prefix":"102008","title":"行走衣架、王牌特工，迷妹簇拥之下，他让编剧绞尽脑汁"},{"images":["https://pic4.zhimg.com/v2-8ceaf0c097ffceaba75478d7af00ca53.jpg"],"type":0,"id":9652642,"ga_prefix":"102007","title":"人人都在用的「北京时间」，其实诞生在\u2026\u2026西安"},{"title":"LOL 主持人余霜：大家创造的激情和爱，让我站到舞台中央","ga_prefix":"102007","images":["https://pic3.zhimg.com/v2-f5519f187d3447a9c504589b3d978c06.jpg"],"multipic":true,"type":0,"id":9653039},{"images":["https://pic2.zhimg.com/v2-8f11b41f995ca5340510c1def1c003d1.jpg"],"type":0,"id":9652878,"ga_prefix":"102007","title":"投下 1000 亿建「达摩院」，阿里说：既要赚钱又要做科研"},{"images":["https://pic2.zhimg.com/v2-71b8e37a6c2bc7a21bcad1568e228881.jpg"],"type":0,"id":9652858,"ga_prefix":"102006","title":"瞎扯 · 如何正确地吐槽"}]
     * top_stories : [{"image":"https://pic1.zhimg.com/v2-8c8ac5b34e41c832d23e49eabcc2dcb8.jpg","type":0,"id":9653039,"ga_prefix":"102007","title":"LOL 主持人余霜：大家创造的激情和爱，让我站到舞台中央"},{"image":"https://pic4.zhimg.com/v2-92061a3a892db43428aac5a3e98780c7.jpg","type":0,"id":9652878,"ga_prefix":"102007","title":"投下 1000 亿建「达摩院」，阿里说：既要赚钱又要做科研"},{"image":"https://pic2.zhimg.com/v2-34b55073133fc1eed22289a2b54c6e55.jpg","type":0,"id":9653002,"ga_prefix":"102009","title":"给每个人发放「无条件基本收入」会是件好事吗？听听李开复怎么说"},{"image":"https://pic3.zhimg.com/v2-d4e414cb596af27f9d4f56318290d916.jpg","type":0,"id":9652295,"ga_prefix":"101916","title":"一见钟情的时候，人的大脑中都发生了什么？"},{"image":"https://pic4.zhimg.com/v2-63ef9606522e58dbfd09c83717b1e9d3.jpg","type":0,"id":9652880,"ga_prefix":"101914","title":"搜狗要上市：天时地利人和都有了，待验证的故事才刚开始"}]
     */

    private String date;
    private List<StoriesBean> stories;
    private List<TopStoriesBean> top_stories;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public List<StoriesBean> getStories() {
        return stories;
    }

    public void setStories(List<StoriesBean> stories) {
        this.stories = stories;
    }

    public List<TopStoriesBean> getTop_stories() {
        return top_stories;
    }

    public void setTop_stories(List<TopStoriesBean> top_stories) {
        this.top_stories = top_stories;
    }

    /**
     * 最新新闻
     */
    public static class StoriesBean {
        /**
         * images : ["https://pic4.zhimg.com/v2-281acaabd1ae0188544610440f38eec3.jpg"]
         * type : 0
         * id : 9652803
         * ga_prefix : 102010
         * title : 做到这四点，在华尔街工作也能保持旺盛的精力
         * multipic : true
         */

        private int type;
        private int id;
        private String ga_prefix;
        private String title;
        private boolean multipic;
        private List<String> images;

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getGa_prefix() {
            return ga_prefix;
        }

        public void setGa_prefix(String ga_prefix) {
            this.ga_prefix = ga_prefix;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public boolean isMultipic() {
            return multipic;
        }

        public void setMultipic(boolean multipic) {
            this.multipic = multipic;
        }

        public List<String> getImages() {
            return images;
        }

        public void setImages(List<String> images) {
            this.images = images;
        }

        @Override
        public String toString() {
            return "StoriesBean{" +
                    "type=" + type +
                    ", id=" + id +
                    ", ga_prefix='" + ga_prefix + '\'' +
                    ", title='" + title + '\'' +
                    ", multipic=" + multipic +
                    ", images=" + images +
                    '}';
        }
    }

    /**
     * 顶部ViewPager轮播显示的新闻
     */
    public static class TopStoriesBean {
        /**
         * image : https://pic1.zhimg.com/v2-8c8ac5b34e41c832d23e49eabcc2dcb8.jpg
         * type : 0
         * id : 9653039
         * ga_prefix : 102007
         * title : LOL 主持人余霜：大家创造的激情和爱，让我站到舞台中央
         */

        private String image;
        private int type;
        private int id;
        private String ga_prefix;
        private String title;

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getGa_prefix() {
            return ga_prefix;
        }

        public void setGa_prefix(String ga_prefix) {
            this.ga_prefix = ga_prefix;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }
    }
}
