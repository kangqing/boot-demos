package com.yunqing.demoredis.utils;

public class SequenceUtil {

    private static final int DEFAULT_LENGTH = 3;

    /**
     * 将传入的数 seq 格式化成 length 位，不够前边补 0
     * 如果 length < 3 则按照 3 算
     * @param seq
     * @param length
     * @return
     */
    public static String getSequence(long seq, int length) {
        String str = String.valueOf(seq);
        int len = str.length();
        length = Math.max(length, DEFAULT_LENGTH);
        if (len >= length) {
            return str;
        }
        int rest = length - len;
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < rest; i++) {
            sb.append('0');
        }
        sb.append(str);
        return sb.toString();
    }
}
