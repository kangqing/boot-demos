package com.yunqing.demoatest.work;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.*;
import java.util.List;
import java.util.stream.Collectors;
import java.util.zip.ZipOutputStream;

/**
 * 封装一个 json 文件
 * @author kangqing
 * @since 2021/4/20 19:14
 */
public class ToJsonFile {
    public static void main(String[] args) throws Exception {
        List<Record> list = List.of(new Record("1", new String[] {"记录1", "111"}, null),
                new Record("2", new String[] {"记录1", "111"}, "119"),
                new Record("3", new String[] {"记录1", "111"}, "120"));
        //List<Record> collect = list.stream().limit(2).collect(Collectors.toList());
        createJsonFile(list);

    }

    private static void createJsonFile(List<Record> list) throws Exception {
        File file = new File("searchData.json");
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("id", "1111");
        jsonObject.put("ce", null);
        jsonObject.put("checkCode", "checkCode");



        // java 对象列表转成 json 数组，先转成 json 字符串，再转成 jsonArray
        JSONArray objects = JSONArray.parseArray(JSON.toJSONString(list));
        jsonObject.put("data", objects);
        System.out.println(jsonObject);

        try {

            FileWriter fileWriter = new FileWriter(file);
            System.out.println("Writing JSON object to file");
            System.out.println("-----------------------");
            System.out.print(jsonObject);
            // 向文件中写入内容
            fileWriter.write(jsonObject.toString());
            fileWriter.flush();
            fileWriter.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
        // 给压缩包命名 hello.zip 并且创建到指定目录
        // 生成到原文件夹
        File zipFile = new File("/Users/yunqing/Documents" + File.separator + "hello.zip");
        ZipOutputStream zipOut = new ZipOutputStream(new FileOutputStream(zipFile));
        //ZipUtils.compress(file, zipOut, "searchData.json", true);
        ZipUtils.toZip(file, zipOut, true);
        //ZipUtils.toZip(List.of(file), zipOut);
        // 移动到目标文件夹
        File targetDir = new File("/Users/yunqing/Documents/xxx");
        if (targetDir.isDirectory()) {
            if(zipFile.getName().endsWith(".zip")) {
                String cmd = "mv " + zipFile.getAbsolutePath() + " " + "/Users/yunqing/Documents/xxx";
                Runtime.getRuntime().exec(cmd);
            }
        }
    }
}

@Data
@AllArgsConstructor
@NoArgsConstructor
class Record {
    private String id;
    private String[] name;
    private String mobile;
}
