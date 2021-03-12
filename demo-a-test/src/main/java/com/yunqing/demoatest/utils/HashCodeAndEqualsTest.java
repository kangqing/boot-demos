package com.yunqing.demoatest.utils;

import lombok.Data;

import java.util.HashMap;
import java.util.Map;

/**
 * @author kangqing
 * @since 2021/3/13 04:53
 */
public class HashCodeAndEqualsTest {
    public static void main(String[] args) {
        // 业务认为 id 相同的钥匙能开同一个门
        Key k1 = new Key(1, "能开同一个门的钥匙1");
        Key k2 = new Key(1, "能开同一个门的另一把钥匙2");
        Map<Key,String> map = new HashMap<>();
        map.put(k1, "我是门的钥匙");
        //
        System.out.println(map.get(k2));
    }
}
// 自定义钥匙类
@Data
class Key {
    private Integer id;
    private String name;

    public Key () {}

    public Key (Integer id, String name) {
        this.id = id;
        this.name = name;
    }
    // 重写 hashcode
    public int hashCode() {
        return id.hashCode();
    }

    // 重写 equals
    public boolean equals(Object obj) {
        if (!(obj instanceof Key))
            return false;
        return this.getId().equals(((Key)obj).getId());
    }

}
