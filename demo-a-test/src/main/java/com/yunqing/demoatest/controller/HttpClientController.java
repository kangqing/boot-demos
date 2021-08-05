package com.yunqing.demoatest.controller;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 测试http client
 * @author kangqing
 * @since 2021/2/4 10:46
 */
@RestController
@RequestMapping("/hc")
public class HttpClientController {

    @GetMapping("/hello")
    public String getHello() {
        return "hello";
    }

    @GetMapping("/heap")
    public void heap() {
        List<Cat> cats = new ArrayList<>();
        for (;;) {
            Cat cat = new Cat();
            cats.add(cat);
        }
    }


    @PostMapping("/postTest")
    public String postStr(@RequestBody String name) {
        return "Hello, " + name;
    }

    @GetMapping("/getTest")
    public String getStr() throws InterruptedException {
        TimeUnit.SECONDS.sleep(3);
        return "get ... httpclient";
    }

    @GetMapping("/testNow")
    public String getStrNow() {
        return "立刻返回结果";
    }

    @GetMapping("/version")
    public Map<String,String> getUserAgent(HttpServletRequest req){
        Map<String,String> Sys= new HashMap<String, String>();
        String ua = req.getHeader("User-Agent").toLowerCase();
        System.out.println("ua====" + ua);
        String s;
        String msieP = "msie ([\\d.]+)";
        String ie11 = "rv:([\\d.]+)";
        String firefoxP = "firefox\\/([\\d.]+)";
        String chromeP = "chrome\\/([\\d.]+)";
        String operaP = "opera.([\\d.]+)";
        String safariP = "version\\/([\\d.]+).*safari";

        Pattern pattern = Pattern.compile(msieP);
        Matcher mat = pattern.matcher(ua);
        if(mat.find()){
            s=mat.group();
            Sys.put("type", "ie");
            Sys.put("version", s.split(" ")[1]);
            return Sys;
        }

        pattern = Pattern.compile(ie11);
        mat = pattern.matcher(ua);
        if (mat.find()) {
            s=mat.group();
            Sys.put("type", "ie11");
            Sys.put("version", s.split(":")[1]);
            return Sys;
        }

        pattern = Pattern.compile(firefoxP);
        mat=pattern.matcher(ua);
        if(mat.find()){
            s=mat.group();
            System.out.println(s);
            Sys.put("type", "firefox");
            Sys.put("version", s.split("/")[1]);
            return Sys;
        }
        pattern = Pattern.compile(chromeP);
        mat=pattern.matcher(ua);
        if(mat.find()){
            s=mat.group();
            Sys.put("type", "chrome");
            Sys.put("version", s.split("/")[1]);
            return Sys;
        }
        pattern = Pattern.compile(operaP);
        mat=pattern.matcher(ua);
        if(mat.find()){
            s=mat.group();
            System.out.println(s);
            Sys.put("type", "opera");
            Sys.put("version", s.split("\\.")[1]);
            return Sys;
        }
        pattern = Pattern.compile(safariP);
        mat=pattern.matcher(ua);
        if(mat.find()){
            s=mat.group();
            Sys.put("type", "safari");
            Sys.put("version", s.split("/")[1].split(" ")[0]);
            return Sys;
        }
        return Sys;
    }
}

@Data
@NoArgsConstructor
class Cat {
    private String name;
}
