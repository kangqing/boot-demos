package com.yunqing.demoleetcode;

/**
 * 力扣周赛专用
 * @author yunqing
 * @since 2020/8/9 9:53
 */
public class LeetCodeMatch {
    public static void main(String[] args) {
        SolutionMatch solutionMatch = new SolutionMatch();
        
    }
}

class SolutionMatch {
    
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