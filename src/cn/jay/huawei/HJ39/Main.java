package cn.jay.huawei.HJ39;

import java.util.Arrays;
import java.util.Scanner;

// 注意类名必须为 Main, 不要有任何 package xxx 信息
public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String maskStr, ip1Str, ip2Str;
        while (in.hasNextLine()) {
            maskStr = in.nextLine();
            ip1Str = in.nextLine();
            ip2Str = in.nextLine();

            if (!(validateMask(maskStr) && validateIp(ip1Str) &&
                    validateIp(ip2Str))) System.out.println("1");
            else if (checkIfInSameNet(ip1Str, ip2Str, maskStr)) System.out.println("0");
            else System.out.println("2");
        }
    }

    public static boolean checkIfInSameNet(String ip1Str, String ip2Str,
                                           String maskStr) {
        long ip1Long = ipStringToLong(ip1Str);
        long ip2Long = ipStringToLong(ip2Str);
        long maskLong = ipStringToLong(maskStr);

        return (ip1Long & maskLong) == (ip2Long & maskLong);
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
     * 将String类型的ip地址转换为long (0.0.0.255 -> 255)
     */
    public static long ipStringToLong(String ip) {
        String[] strArr = ip.split("\\.");
        long[] nums = new long[4];

        for (int i = 0; i < 4; i++)
            nums[i] = Integer.parseInt(strArr[i]);

        return (nums[0] << 24) + (nums[1] << 16) + (nums[2] << 8) + nums[3];
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
}
