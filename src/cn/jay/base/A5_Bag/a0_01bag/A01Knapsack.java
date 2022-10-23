package cn.jay.base.A5_Bag.a0_01bag;

import java.util.Scanner;

/**
 * 01背包
 */
public class A01Knapsack {
    static int N = 1001;
    static int n,m; // n是物品个数，m是背包体积
    static int[] v = new int[N]; // 每个物品的体积
    static int[] w = new int[N]; // 每个物品的价值
    static int[] status = new int[N];

    public static void main(String[] args) {
        solve();
    }

    public static void solve() {
        Scanner in = new Scanner(System.in);
        n = in.nextInt();
        m = in.nextInt();
        for (int i = 1; i <= n; i++) {
            v[i] = in.nextInt();
            w[i] = in.nextInt();
        }

        for (int i = 1; i <= n; i++){
            for (int j = m; j >= v[i]; j--){
                status[j] = Math.max(status[j], status[j-v[i]] + w[i]);
            }
        }
        System.out.println(status[m]);
    }
}
