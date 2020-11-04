package com.yunqing.demohutool;

import cn.hutool.core.date.ChineseDate;
import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.io.FileUtil;
import cn.hutool.core.lang.Console;
import cn.hutool.core.lang.Snowflake;
import cn.hutool.core.lang.Validator;
import cn.hutool.core.lang.tree.Tree;
import cn.hutool.core.lang.tree.TreeNodeConfig;
import cn.hutool.core.lang.tree.TreeUtil;
import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.SecureUtil;
import cn.hutool.extra.qrcode.QrCodeUtil;
import cn.hutool.extra.qrcode.QrConfig;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.awt.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@SpringBootTest
class DemoHutoolApplicationTests {

    @Test
    void contextLoads() {
        String str = SecureUtil.md5("123456");
        Console.log("加密后的字符串:{}", str);
    }

    @Data
    @AllArgsConstructor
    static class Dept {
        private Integer id;

        private String name;

        private String parentId;
    }

    /**
     * 树形结构输出
     */
    @Test
    void TreeUtilTest() {
        List<Dept> list = new ArrayList<>();
        list.add(new Dept(1, "技术部", ""));
        list.add(new Dept(2, "财务部", ""));
        list.add(new Dept(3, "采购部", ""));
        list.add(new Dept(11, "开发部", "1"));
        list.add(new Dept(12, "测试部", "1"));
        list.add(new Dept(21, "会计部", "2"));
        list.add(new Dept(31, "采购文具部", "3"));
        list.add(new Dept(111, "前端开发部", "11"));
        list.add(new Dept(112, "后端开发部", "11"));

        //配置
        TreeNodeConfig treeNodeConfig = new TreeNodeConfig();
        // 自定义属性名 都要默认值的
        treeNodeConfig.setIdKey("id");
        treeNodeConfig.setNameKey("deptName");
        treeNodeConfig.setParentIdKey("pId");
        // 最大递归深度
        //treeNodeConfig.setDeep(3);

        //转换器
        List<Tree<String>> treeNodes = TreeUtil.build(list, "", treeNodeConfig,
                (treeNode, tree) -> {
                    tree.setId(treeNode.getId().toString());
                    tree.setParentId(treeNode.getParentId());
                    tree.setName(treeNode.getName());
                    // 扩展属性 ...
                    tree.putExtra("checked", true);
                });
        treeNodes.forEach(System.out::println);
        //return treeNodes;
    }

    /**
     * 时间相关
     */
    @Test
    void dateUtilTest() {
        //当前时间
        Date date = DateUtil.date();
        Console.log("当前时间是：{}\n----------------------", date);

        //当前时间字符串，格式：yyyy-MM-dd HH:mm:ss
        String now = DateUtil.now();
        //当前日期字符串，格式：yyyy-MM-dd
        String today= DateUtil.today();
        Console.log("当前时间：{}\n当前日期：{}\n---------------------", now, today);

        DateTime dateTime = DateUtil.beginOfMonth(date);
        DateTime dateTime1 = DateUtil.beginOfQuarter(DateUtil.parse("2020-03-02"));
        DateTime dateTime2 = DateUtil.endOfYear(DateUtil.date());
        Console.log("月初：{}\n季度初：{}\n年末：{}\n-------------------", dateTime, dateTime1,dateTime2);

        String format = DateUtil.format(date, "yyyy/MM/dd");
        ChineseDate chineseDate = new ChineseDate(DateUtil.parseDate("2020-01-25"));
        Console.log("格式化时间：{}\n农历时间：{}", format, chineseDate);
    }

    /**
     * 字符串相关
     */
    @Test
    void strUtilTest() {
        //只判断null和""空字符串为空
        boolean b1 = StrUtil.hasEmpty("1", "     ", "2");
        //把不可见字符也判断为空
        boolean b2 = StrUtil.hasBlank("1", "     ", "2");
        Console.log("是否有为空的：\n{}\n{}\n--------------------", b1, b2);

        String fileName1 = StrUtil.removePrefix("pretty_girl.jpg", "pretty_");
        String fileName2 = StrUtil.removeSuffix("pretty_girl.jpg", ".jpg");
        Console.log("删除前缀：{}\n删除后缀{}\n--------------------", fileName1, fileName2);

        String str = "abcdefgh";
        String strSub1 = StrUtil.sub(str, 2, 3);
        String strSub2 = StrUtil.sub(str, 2, -3);
        String strSub3 = StrUtil.sub(str, 3, 2);
        Console.log("从{}中截取字符串：\n{}\n{}\n{}\n----------------------", str, strSub1, strSub2, strSub3);

        String template = "今天是{}，{}刚刚获取到的";
        String format = StrUtil.format(template, DateUtil.date(), "tools");
        Console.log(format);
    }

    /**
     * 主键 id
     */
    @Test
    void IdUtilTest() {
        //生成的UUID是不带-的字符串
        String s = IdUtil.simpleUUID();
        //生成的UUID是带-的字符串
        String s1 = IdUtil.randomUUID();
        //MongoDB数据库的一种唯一ID生成策略
        String s3 = IdUtil.objectId();

        //参数1为终端id,参数2为数据中心id
        Snowflake snowflake = IdUtil.createSnowflake(1, 1);
        long lon = snowflake.nextId();
        String str = snowflake.nextIdStr();
        Console.log("不带 - 的UUID:{}\n带 - 的UUID:{}\nMongoDB数据库的一种唯一ID生成策略:{}\n分布式雪花算法全局唯一id:{}" +
                "\n雪花算法String类型id:{}", s, s1, s3, lon, str);
    }

    /**
     * email验证
     */
    @Test
    void validatorTest() {
        boolean isEmail = Validator.isEmail("10001@qq.com");
        //正则匹配正整数
        boolean mactchRegex = Validator.isMactchRegex("^[1-9]\\d*$", "3.14");
        boolean isUUID = Validator.isUUID(IdUtil.simpleUUID());
        Console.log("验证邮箱：{}\n正则匹配：{}\n验证UUID：{}", isEmail, mactchRegex, isUUID);
    }

    /**
     * 生成二维码
     */
    @Test
    void QrCodeUtilTest() {
        QrConfig config = new QrConfig(300, 300);
        // 设置边距，既二维码和背景之间的边距
        config.setMargin(3);
        // 设置前景色，既二维码颜色（蓝色）
        config.setForeColor(Color.blue);
        // 设置背景色（灰色）
        config.setBackColor(Color.gray);
        // 生成二维码到文件，也可以到流
        QrCodeUtil.generate("http://www.baidu.com", config, FileUtil.file("e:/qrcode.jpg"));
    }

}
