package com.yunqing.demoleetcode;

import java.util.*;

/**
 * 力扣周赛专用
 * @author yunqing
 * @since 2020/8/9 9:53
 */
public class LeetCodeMatch {
    public static void main(String[] args) {
        SolutionMatch solutionMatch = new SolutionMatch();
        solutionMatch.checkArithmeticSubarrays(new int[] {4,6,5,9,3,7}, new int[] {0, 0, 2}, new int[] {2, 3, 5});
    }
}

class SolutionMatch {
    public List<Boolean> checkArithmeticSubarrays(int[] nums, int[] l, int[] r) {
        List<Boolean> res = new ArrayList<>();
        int left, right;
        for (int i = 0; i < l.length; i++) {
            left = l[i];
            right = r[i];
            int[] arr = new int[right - left + 1];
            int a = left;
            int index = 0;
            while (a <= right) {
                arr[index++] = nums[a];
                a++;
            }
            boolean b = jisuan(arr);
            res.add(b);
        }
        return res;

    }

    private boolean jisuan(int[] nums) {
        Arrays.sort(nums);
        int val = nums[1] - nums[0];

        for (int i = 1; i < nums.length; i++) {
            if (val != nums[i] - nums[i - 1]) {
                return false;
            }
        }
        return true;
    }

}

/**
//int[] intArr = new int[]{ };
//创建一个栈或者队列
//Deque<Character> stack = new ArrayDeque<>();
//stack.push('a');//入栈
//Character first = stack.peekFirst();//栈顶元素
//stack.pop();//出栈

//queue.offer(1);//入队列
//Integer remove = queue.remove();//出队列获取值

//Collections.reverse(list); //反转列表

 * 先看ASCII码：a~z是97~122的二进制，而A~Z是65~90的二进制编码，
 * 于是我们就得出：大写字母=小写字母-32 ；这个公式了。
 * 当然这里的32我也可以这么写‘Z’=‘z’-‘空格’。因为空格的ASCII码是32对应的二进制编码
 *
 * 又因为 ‘0’ 的ASCII码对应48
 * 而‘1’-‘9’分别对应49 - 57
 * 所以实际上减去‘0’相当于减去48
 */