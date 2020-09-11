package com.yunqing.demoleetcode.algorithm.stack;


/**
 * LeetCode1541 平衡括号字符串的最少插入次数
 * @author yx
 * @date 2020/8/14 16:52
 */
public class LeetCode1541 {
    public static void main(String[] args) {
        Solution1541 solution1541 = new Solution1541();
        var s = ")))))))";
        System.out.println(solution1541.minInsertions(s));
    }
}

/**
 * 因为一个( 需要匹配 ))
 * 所以加入一个)先令其为 ] 再有一个才升级为 )
 * 这样 ()就能对应了
 */
class Solution1541 {
    public int minInsertions(String s) {
        int ans = 0; //补全次数
        int left = 0; //左括号次数
        int len = s.length();
        for (int i = 0; i < len; i++) {
            if (s.charAt(i) == '(') {
                left++;
            } else {
                //右括号
                if (i + 1 < len && s.charAt(i + 1) == ')') {
                    i++;//跳到下下个
                }else {
                    //缺少一个右括号，加一个
                    ans++;
                }
                if (left > 0) {
                    --left;
                }else {
                    //缺少左括号,加一个
                    ans++;
                }
            }
        }
        ans += left * 2;
        return ans;

    }
}
