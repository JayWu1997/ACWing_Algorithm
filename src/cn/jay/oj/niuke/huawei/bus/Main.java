package cn.jay.oj.niuke.huawei.bus;

import java.util.Scanner;

/**
 * 第二题：公交车上有m个座位，经过n个站台，有x条上下车记录 每个记录第一个数是上车点第二个数是下车点，问座位的最高利用率是多少
 * 用例：
 * 2个座位 10个站台
 * 1号乘客0上1下
 * 2号乘客1上9下
 * 3号乘客0上10下
 * 4号乘客3上8下
 * 输出19=10-0+9-1+1-0
 * 1个座位给3号乘客 另一个给1号和2号
 *
 * 作者：匿名用户
 * 链接：https://leetcode.cn/circle/discuss/wwZREW/
 * 来源：力扣（LeetCode）
 * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
 */
public class Main {

    static int m, n, x, result;
    static int[] num = new int[1010];

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        m = in.nextInt();
        n = in.nextInt();
        x = in.nextInt();
        for (int i = 0; i < x; i++) {
            int start = in.nextInt();
            int end = in.nextInt();
            for(int j = start; j < end; j++){
                num[j] ++;
            }
        }

        for (int i = 0; i < n; i++)
            result += Math.min(num[i], m);

        System.out.println(((double) result)/(m*(n-1)));
    }
}
