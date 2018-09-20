package com.jiagouedu.lock;/*
 * ━━━━━━如来保佑━━━━━━
 * 　　　┏┓　　　┏┓
 * 　　┏┛┻━━━┛┻┓
 * 　　┃　　　━　　　┃
 * 　　┃　┳┛　┗┳　┃
 * 　　┃　　　┻　　　┃
 * 　　┗━┓　　　┏━┛
 * 　　　　┃　　　┗━━━┓
 * 　　　　┃　　　　　　　┣┓
 * 　　　　┃　　　　　　　┏┛
 * 　　　　┗┓┓┏━┳┓┏┛
 * 　　　　　┗┻┛　┗┻┛
 * ━━━━━━永无BUG━━━━━━
 * 腾讯课程-图灵学院
 * 每晚8点直播
 * 技好人帅的悟空。
 */

import java.util.Collection;
import java.util.Iterator;

public class Demo {

    public static void main(String[] args) {

     LikeSearch<String> likeSearch = new LikeSearch<String>();
            likeSearch.put("湖北","湖北");
            likeSearch.put("湖南","湖南");
            Collection<String> search = likeSearch.search("南", 10);
            for(Iterator it=search.iterator();it.hasNext();){
                System.out.println(it.next());
            }
    }


}
