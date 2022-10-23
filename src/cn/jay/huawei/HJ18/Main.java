package cn.jay.huawei.HJ18;

import java.util.Arrays;
import java.util.Scanner;

// 注意类名必须为 Main, 不要有任何 package xxx 信息
public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String line, maskStr, ipStr;
        int A = 0, B = 0, C = 0, D = 0, E = 0, errorNum = 0, privateNum = 0, firstNum = 0, secondNum;
        while (in.hasNextLine()) {
            line = in.nextLine();
            ipStr = line.split("~")[0];
            maskStr = line.split("~")[1];

            firstNum = Integer.parseInt(ipStr.split("\\.")[0]);
            secondNum = Integer.parseInt(ipStr.split("\\.")[1]);
            // 处理0和127开头的ip
            if(firstNum == 127 || firstNum == 0) continue;

            if (!(validateMask(maskStr) && validateIp(ipStr))) errorNum++;
            else {
                if (firstNum >= 1 && firstNum <= 126) A++;
                else if (firstNum >= 128 && firstNum <= 191) B++;
                else if (firstNum >= 192 && firstNum <= 223) C++;
                else if (firstNum >= 224 && firstNum <= 239) D++;
                else if (firstNum >= 240 && firstNum <= 255) E++;

                if (firstNum == 10
                        || (firstNum == 172 && (secondNum >= 16 && secondNum <= 31))
                        || (firstNum == 192 && secondNum == 168))
                    privateNum++;
            }
        }
        System.out.println(A + " " + B + " " + C + " " + D + " " + E + " " + errorNum + " " + privateNum);
    }

    /**
     * 检查子网合法性
     */
    public static boolean validateMask(String mask) {
        // 检查地址是否合法
        if (!validateIp(mask)) return false;

        // 检查掩码是否符合规范
        long ipLong = ipStringToLong(mask);
        String ipBinaryStr = ipLongToBinaryString(ipLong);
        int first0Index = ipBinaryStr.indexOf('0');
        int last1Index = ipBinaryStr.lastIndexOf('1');
        // 若不存在0或者不存在1则不合法
        if (last1Index == -1 || first0Index == -1) return false;

        // 符合规范的掩码， 最后一个1的下标加一等于第一个0的下标
        return last1Index + 1 == first0Index;
    }

    /**
     * 检查ip合法性
     */
    public static boolean validateIp(String ipStr) {
        // 检查ip中是否是四个数字
        String[] strArr = ipStr.split("\\.");
        if (strArr.length < 4) return false;

        // 检查是否有数字和 '.' 之外的字符
        for (char c : ipStr.toCharArray()) {
            if (!Character.isDigit(c) && c != '.')
                return false;
        }

        // 检查ip中数字是否大于255
        for (String s : strArr) {
            if (Integer.parseInt(s) > 255)
                return false;
        }

        return true;
    }

    /**
     * 将long类型的ip转换为2进制字符串 (255 -> "000...000.11111111")
     */
    public static String ipLongToBinaryString(long ip) {
        char[] charArr = new char[32];
        Arrays.fill(charArr, '0');
        for (int i = 31; i >= 0 && ip > 0; i--) {
            charArr[i] = (ip % 2 == 1 ? '1' : '0');
            ip /= 2;
        }
        return new String(charArr);
    }

    /**
     * 将String类型的ip地址转换为long (0.0.0.255 -> 255)
     */
    public static long ipStringToLong(String ip) {
        String[] strArr = ip.split("\\.");
        long[] nums = new long[4];

        for (int i = 0; i < 4; i++)
            nums[i] = Integer.parseInt(strArr[i]);

        return (nums[0] << 24) + (nums[1] << 16) + (nums[2] << 8) + nums[3];
    }
}
