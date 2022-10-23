package cn.jay.huawei.flower;

import java.util.Scanner;

/**
 * {@link <a href="https://leetcode.cn/circle/discuss/wwZREW/">...</a>}
 * 第一题：已知m个花圃，n个植物，每个花圃只能放一个植物，且植物的编号除以m的余数不能等于花圃编号，求满足条件的所有摆放方式
 * 用例：例如，2个花圃编号 0 1，3个植物 编号0 1 2，输出2
 * 0花圃摆1，1花圃摆0
 * 0花圃摆1，1花圃摆2
 * 解题思路： 回溯法
 *
 */
public class Main {

    static int m, n, index, result = 0;
    static int[] arr = new int[1000];

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        m = in.nextInt();
        n = in.nextInt();
        solve();
        System.out.println(result);
    }

    static void solve(){
        // 递归退出条件， 花圃放满了，放满表示一种情况成立，把result加一
        if(index == m){
            result++;
            return;
        }

        for (int i = 0; i < n; i++){
            // 判断该数字是否能用
            if(i%m == index || isUsed(i)) continue;

            // 存入数字, 处理下一位数字
            arr[index++] = i;
            solve();

            // 回溯
            index--;
        }
    }

    // 判断指定数字是否已经被使用了
    static boolean isUsed(int i){
        for (int j = 0; j < index; j++) {
            if(arr[j] == i)
                return true;
        }
        return false;
    }

}
