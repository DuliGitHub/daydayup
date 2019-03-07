package sort;

public class InsertionSort {
    public static void main(String[] args) {
        int[] a = {22,13,5,12,343,22,453,235,236,124};
        insertionSort(a,10);
        for(int i = 0;i  < a.length;i++){
            System.out.print(a[i] + " - ");
        }
    }

    /**
     *插入排序
     * @param a 数组
     * @param n 数组大小
     */
    public static void insertionSort(int[] a,int n){
        if(n <= 1){
            return;
        }


        for(int i = 1;i < n; ++ i){
            int value = a[i];
            int j = i - 1;

            /** 冒泡排序的数据交换比擦入排序的数据移动要复杂 3 ：1
            if(a[j] > a[j+1]){
                int tmp = a[j];
                a[j] = a[j+1];
                a[j+1] = tmp;
            }
             **/

            //查找插入的位置
            for(; j >= 0; --j){
                if(a[j] > value){
                    a[j+1] = a[j]; //数据移动
                }else {
                    break;
                }
            }
            a[j+1] = value;
        }
    }


}
