package cn.jay.huawei.HJ97;

import java.io.IOException;
import java.text.DecimalFormat;
import java.util.Scanner;

public class Main {

    static StringBuilder builder = new StringBuilder();

    public static void main(String[] args) throws IOException {
        Scanner in = new Scanner(System.in);
        int n, posSum=0, posNum = 0, nagNum=0;
        double posAvg = 0.0F;
        String line;
        n = in.nextInt();
        in.nextLine();
        line = in.nextLine();

        for (String s : line.split(" ")) {
            if(!s.contains("-")&&!s.contains(".")&&!"0".equals(s)){
                posSum += Integer.parseInt(s);
                posNum++;
            }
            else if(s.contains("-"))
                nagNum++;
        }

        if(posSum!=0)
            posAvg = (posSum/(double)posNum);
        // 格式化输出
        DecimalFormat df= new DecimalFormat("#0.0");
        df.setMaximumFractionDigits(1);
        System.out.println(nagNum + " " + df.format(posAvg));

    }
}
