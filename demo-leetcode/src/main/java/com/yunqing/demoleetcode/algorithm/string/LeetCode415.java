package com.yunqing.demoleetcode.algorithm.string;

/**
 * @author kangqing LeetCode415 字符串相加
 * @description
 * @date 2020/8/3 11:06
 */
public class LeetCode415 {
    public static void main(String[] args) {
        Solution415 s = new Solution415();
        System.out.println(s.addStrings("46", "318"));
    }
}

/**
 * 先看ASCII码：a~z是97~122的二进制，而A~Z是65~90的二进制编码，
 * 于是我们就得出：大写字母=小写字母-32 ；这个公式了。
 * 当然这里的32我也可以这么写‘Z’=‘z’-‘空格’。因为空格的ASCII码是32对应的二进制编码
 *
 * 又因为 ‘0’ 的ASCII码对应48
 * 而‘1’-‘9’分别对应49 - 57
 * 所以实际上减去‘0’相当于减去48
 */
class Solution415 {
    public String addStrings(String num1, String num2) {
        int m = num1.length() - 1;
        int n = num2.length() - 1;
        //进位标识符
        int flag = 0;
        StringBuilder sb = new StringBuilder();
        while(m >=0 || n >=0 || flag > 0) {
            int x = m >= 0 ? num1.charAt(m) - '0' : 0;
            int y = n >= 0 ? num2.charAt(n) - '0' : 0;
            sb.append((x + y + flag) % 10);
            flag = (x + y + flag) / 10;
            m--;
            n--;
        }
        return sb.reverse().toString();
    }
}
