package cn.jay.base.A5_Bag.a1_CompletePack;

import java.util.Scanner;

/**
 * @author jay
 * @date 2022/12/04 19:12:50
 * @desc 完全背包
 * f(i, j) = max( f(i-1,j), f(i,j-v[i]) + w[i])
 */
public class CompletePack {
    static int N = 1001;
    static int n, m; // n是物品个数，m是背包体积
    static int[] v = new int[N]; // 每个物品的体积
    static int[] w = new int[N]; // 每个物品的价值
    static int[] f = new int[N]; // 最大价值

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

        for (int i = 1; i <= n; i++) {
            for (int j = v[i]; j <= m; j++) {
                f[j] = Math.max(f[j], f[j - v[i]] + w[i]);
            }
        }
        System.out.println(f[m]);
    }
}
