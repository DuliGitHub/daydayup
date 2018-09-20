package com.jiagouedu.lock;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/*
 * ━━━━━━神兽保佑━━━━━━
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
 * 图灵学院-悟空老师
 * 以往视频加小乔老师QQ：895900009
 * JAVA学习交流群号：656951213
 */
public class LikeSearch<T> {

   private final CharColumn<T> [] columns=new CharColumn[Character.MAX_VALUE];

   public CharColumn<T>[] getColumns(){
       return  columns;
   }

    private final ReentrantReadWriteLock rwl = new ReentrantReadWriteLock();
    private final Lock r = rwl.readLock();
    private final Lock w = rwl.writeLock();

    public void put(T t,String value){
      w.lock();
        char[] chars = value.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            char c = chars[i];
            CharColumn<T> column = columns[c];
            if (null == column) {
                column = new CharColumn<T>();
                columns[c] = column;
            }
            column.add(t, (byte) i);
        }
        w.unlock();
    }



    /**
     * 修改数据. <br>
     *
     * @param id 主键id
     * @param newValue 新字符串
     */
    public void update(T id, String newValue) {

            remove(id);
            put(id, newValue);

    }




    /**
     * 删除数据. <br>
     * 删除成功,返回true; 不包含,返回false .<br>
     *
     * @param id 主键id
     * @return
     */
    public boolean remove(T id) {

            boolean sign = false;
            for (CharColumn<T> column : columns) {
                if (column != null) {
                    if (column.remove(id)) {
                        sign = true;
                    }
                }
            }
        return sign;


    }


    /***
     *
     * @param word
     * @param limit
     * @return
     */
    public Collection<T> search(String word,int limit){

            char chars[]=word.toCharArray();
            int n=word.length();
            Context context=new Context();
            for (int i=0;i<chars.length;i++){
                CharColumn<T> column = columns[chars[i]];
                if(column==null){
                    break;
                }
                if(!context.filter(column)){
                    break;
                }
                n--;
            }
            if(n==0){
                return  context.limit(limit);
            }
            return Collections.emptySet();

    }

    private class  Context{
        Map<T,byte[]> result;
        boolean used=false;
        private boolean filter(CharColumn<T> columns){
            if(this.used==false){
                this.result=new TreeMap<T, byte[]>(columns.poxIndex);
                this.used=true;
                return true;
            }
            boolean flag=false;
            Map<T, byte[]> newResult = new TreeMap<T, byte[]>();
            Set<Map.Entry<T,byte[]>> entrySet=columns.poxIndex.entrySet();
            for(Map.Entry<T,byte[]> entry:entrySet){
                T id=entry.getKey();
                byte[] charPox=entry.getValue();
                if(!result.containsKey(id)){
                    continue;
                }
                byte[] before= result.get(id);
                boolean in=false;
                for(byte pox:before){
                    if(contain(charPox,(byte)(pox+1))){
                        in=true;
                        break;
                    }
                }
                if(in){
                    flag=true;
                    newResult.put(id,charPox);
                }
            }
            result=newResult;
            return flag;
        }
        public Collection<T> limit(int limit){
            if(result.size()<=limit){
                return result.keySet();
            }
            Collection<T> ids=new TreeSet<T>();
            for(T id:result.keySet()){
                ids.add(id);
                if(ids.size()>=limit){
                    break;
                }
            }
            return ids;
        }

    }
 private class  CharColumn<T> {


        ConcurrentHashMap<T, byte[]> poxIndex = new ConcurrentHashMap<T, byte[]>();

     /***
      *
      * @param t
      * @param pox
      */
        private void add(T t, byte pox) {
            byte[] arr = poxIndex.get(t);
            if (null == arr) {
                arr = new byte[]{pox};
            } else {
                arr = copy(arr, pox);
            }
            poxIndex.put(t, arr);

        }
         private boolean remove(T id) {
             if (poxIndex.remove(id) != null) {
                 return true;
             }
             return false;
         }




    }


    private static byte[] copy(byte[] arr, byte value) {
        Arrays.sort(arr);
        if (contain(arr, value)) {
            return arr;
        }
        byte[] newArr = new byte[arr.length + 1];
        newArr[newArr.length - 1] = value;
        System.arraycopy(arr, 0, newArr, 0, arr.length);
        Arrays.sort(newArr);
        return newArr;
    }


    private static boolean contain(byte[] arr, byte value) {
        int pox = Arrays.binarySearch(arr, value);
        return (pox >=0) ? true : false;
    }
}
