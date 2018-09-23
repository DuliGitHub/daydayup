package com.jiagouedu.lock;

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
