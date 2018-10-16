package recursion;

public class Recursion {

    int f(int n) {
        if (n == 1) return 1;
        return f(n - 1) + 1;
    }


//写递归代码的关键就是找到如何将大问题分解为小问题的规律,并且基于此写出递推公式，
// 然后再推敲终止条件，最后将递推公式和终止条件翻译成代码

    // 全局变量，表示递归的深度。
    int depth = 0;

    int f1(int n) throws Exception {
        ++depth;
        if (depth > 1000) throw new Exception();

        if (n == 1) return 1;
        return f(n - 1) + 1;
    }

    //避免重复计算
//    public int f3(int n) {
//        if (n == 1) return 1;
//        if (n == 2) return 2;
//        int ret;

        // hasSolvedList 可以理解成一个 Map，key 是 n，value 是 f(n)
//        if (hasSolvedList.containsKey(n)) {
//            return hasSovledList.get(n);
//        }
//
//        int ret = f(n-1) + f(n-2);
//        hasSovledList.put(n, ret);
//        return ret;
//    }
}

