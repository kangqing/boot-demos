package com.kangqing.demozk;

import org.apache.curator.framework.CuratorFramework;
import org.apache.zookeeper.CreateMode;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.nio.charset.StandardCharsets;
import java.util.List;

@SpringBootTest
class DemoZkApplicationTests {


    @Resource
    private CuratorFramework curatorFramework;

    // 创建节点
    @Test
    void createNode() throws Exception {
        // 默认添加持久节点
        String path0 = curatorFramework.create().forPath("/test1");
        // 添加持久顺序节点
        String path1 = curatorFramework.create().withMode(CreateMode.PERSISTENT_SEQUENTIAL)
                .forPath("/test2", "abc".getBytes(StandardCharsets.UTF_8));
        // 临时节点
        // CreateMode.EPHEMERAL
        // 临时顺序节点
        // CreateMode.EPHEMERAL_SEQUENTIAL
        System.out.println(path0);
        System.out.println(path1);

    }

    //查询子节点
    @Test
    public void testQueryChildNode() throws Exception {

        List<String> list = curatorFramework.getChildren().forPath("/");
        System.out.println(" list "+list);
    }

    /**
     * 获取节点数据
     * @throws Exception
     */
    @Test
    void getData(String name) throws Exception {
        if (name == null) {
            name = "/test20000000002";
        }
        final byte[] bytes = curatorFramework.getData().forPath(name);
        System.out.println(new String(bytes));
    }

    /**
     * 设置节点数据
     * @throws Exception
     */
    @Test
    void setData() throws Exception {
        curatorFramework.setData().forPath("/test1", "test1".getBytes(StandardCharsets.UTF_8));
        getData("/test1");
    }

    /**
     * 如果需要父节点一并创建
     * @throws Exception
     */
    @Test
    void createWithParent() throws Exception {
        final String s = curatorFramework.create().creatingParentsIfNeeded().forPath("/test3/test3_1",
                "333".getBytes(StandardCharsets.UTF_8));
        System.out.println(s);
        getData("/test3/test3_1");
    }


    @Test
    void delNode() throws Exception {
        curatorFramework.delete().guaranteed().deletingChildrenIfNeeded().forPath("/test3");
    }



    @Test
    void contextLoads() {
    }

}
