package cn.jay.huawei.HJ16;

import java.util.Scanner;

// 注意类名必须为 Main, 不要有任何 package xxx 信息
public class Main {
    static int N = 3201; // 金钱
    static int m = 61; // 物品个数
    static int[][] v = new int[m][4]; // 价格
    static int[] p = new int[m]; // 重要度
    static int[][] r = new int[m][4]; // 单个物品满意度
    static int[] R = new int[N]; // 总满意度

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        // 注意 hasNext 和 hasNextLine 的区别
        N = in.nextInt() / 10;
        m = in.nextInt();
        for (int i = 1; i <= m; i++) {
            int q_temp, v_temp, p_temp;
            v_temp = in.nextInt() / 10;
            p_temp = in.nextInt();
            q_temp = in.nextInt();
            put(i, q_temp, p_temp, v_temp);
        }

        for (int i = 1; i <= m; i++) {
            for (int j = N; j >= v[i][0]; j--) {
                for (int k = 0; k <= 3; k++) {
                    if (j >= v[i][k]) {
                        R[j] = Math.max(R[j], R[j - v[i][k]] + r[i][k]);
                    }
                }
            }
        }

        System.out.println(R[N] * 10);

    }

    static void put(int i, int q, int p_temp, int v_temp) {
        int r_temp = p_temp * v_temp;

        if (q == 0) {
            p[i] = p_temp;
            for (int j = 0; j < v[i].length; j++){
                v[i][j] += v_temp;
                r[i][j] += v_temp*p_temp;
            }
        } else {
            if (v[q][1] == v[q][0]) {
                v[q][1] = v[q][0] + v_temp;
                r[q][1] = r_temp + r[q][0];
            } else if (v[q][2] == v[q][0]) {
                v[q][2] = v[q][0] + v_temp;
                r[q][2] = r_temp + r[q][0];

                v[q][3] = v[q][1] + v_temp;
                r[q][3] = r_temp + r[q][1];
            }
        }
    }
}