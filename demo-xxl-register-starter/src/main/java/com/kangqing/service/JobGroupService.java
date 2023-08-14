package com.kangqing.service;

import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;
import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.kangqing.common.XxlAutoRegisterProperties;
import com.kangqing.model.XxlJobGroup;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * 执行器接口
 * @author kangqing
 * @since 2023/8/14 15:31
 */
@Component
public class JobGroupService {

    @Resource
    private XxlAutoRegisterProperties xxlAutoRegisterProperties;

    @Resource
    private JobLoginService jobLoginService;

    public List<XxlJobGroup> getJobGroup() {
        String url= xxlAutoRegisterProperties.getUrl()+"/jobgroup/pageList";
        HttpResponse response = HttpRequest.post(url)
                .form("appname", xxlAutoRegisterProperties.getAppName())
                .form("title", xxlAutoRegisterProperties.getTitle())
                .cookie(jobLoginService.getCookie())
                .execute();

        String body = response.body();
        JSONArray array = JSONUtil.parse(body).getByPath("data", JSONArray.class);
        List<XxlJobGroup> list = array.stream()
                .map(o -> JSONUtil.toBean((JSONObject) o, XxlJobGroup.class))
                .collect(Collectors.toList());
        return list;
    }

    /**
     * 我们在后面要根据配置文件中的 appName 和 title 判断当前执行器是否已经被注册到调度中心过，如果已经注册过那么则跳过，
     * 而/jobgroup/pageList 接口是一个模糊查询接口，所以在查询列表的结果列表中，还需要再进行一次精确匹配。
     * @return
     */
    public boolean preciselyCheck() {
        List<XxlJobGroup> jobGroup = getJobGroup();
        Optional<XxlJobGroup> has = jobGroup.stream()
                .filter(xxlJobGroup -> xxlJobGroup.getAppname().equals(xxlAutoRegisterProperties.getAppName())
                        && xxlJobGroup.getTitle().equals(xxlAutoRegisterProperties.getTitle()))
                .findAny();
        return has.isPresent();
    }

    /**
     * 注册新的 执行器到调度中心
     * @return
     */
    public boolean autoRegisterGroup() {
        String url= xxlAutoRegisterProperties.getUrl()+"/jobgroup/save";
        HttpResponse response = HttpRequest.post(url)
                .form("appname", xxlAutoRegisterProperties.getAppName())
                .form("title", xxlAutoRegisterProperties.getTitle())
                .cookie(jobLoginService.getCookie())
                .execute();
        Object code = JSONUtil.parse(response.body()).getByPath("code");
        return code.equals(200);
    }
}
