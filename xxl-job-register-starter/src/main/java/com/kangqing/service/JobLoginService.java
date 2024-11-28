package com.kangqing.service;

import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;
import com.kangqing.common.XxlAutoRegisterProperties;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.net.HttpCookie;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * 在调用业务接口前，需要通过登录接口获取 cookie，并在获取到 cookie 后，缓存到本地的 Map 中
 * @author kangqing
 * @since 2023/8/14 10:13
 */
@Component
public class JobLoginService {

    @Resource
    private XxlAutoRegisterProperties xxlAutoRegisterProperties;

    private final Map<String,String> loginCookie=new HashMap<>();

    public void login() {
        String url= xxlAutoRegisterProperties.getUrl()+"/login";
        HttpResponse response = HttpRequest.post(url)
                .form("userName",xxlAutoRegisterProperties.getUsername())
                .form("password",xxlAutoRegisterProperties.getPassword())
                .execute();
        List<HttpCookie> cookies = response.getCookies();
        Optional<HttpCookie> cookieOpt = cookies.stream()
                .filter(cookie -> cookie.getName().equals("XXL_JOB_LOGIN_IDENTITY")).findFirst();
        if (cookieOpt.isEmpty())
            throw new RuntimeException("get xxl-job cookie error!");

        String value = cookieOpt.get().getValue();
        loginCookie.put("XXL_JOB_LOGIN_IDENTITY",value);
    }

    public String getCookie() {
        for (int i = 0; i < 3; i++) {
            String cookieStr = loginCookie.get("XXL_JOB_LOGIN_IDENTITY");
            if (cookieStr !=null) {
                return "XXL_JOB_LOGIN_IDENTITY="+cookieStr;
            }
            login();
        }
        throw new RuntimeException("get xxl-job cookie error!");
    }
}
