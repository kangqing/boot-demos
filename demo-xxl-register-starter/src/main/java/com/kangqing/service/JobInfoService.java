package com.kangqing.service;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.convert.Convert;
import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;
import cn.hutool.json.JSON;
import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.kangqing.common.XxlAutoRegisterProperties;
import com.kangqing.model.XxlJobInfo;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 创建一个 JobInfoService，根据执行器 id，jobHandler 名称查询任务列表，和上面一样，也是模糊查询：
 * @author kangqing
 * @since 2023/8/14 15:35
 */
@Component
public class JobInfoService {

    @Resource
    private XxlAutoRegisterProperties xxlAutoRegisterProperties;

    @Resource
    private JobLoginService jobLoginService;

    public List<XxlJobInfo> getJobInfo(Integer jobGroupId, String executorHandler) {
        String url= xxlAutoRegisterProperties.getUrl()+"/jobinfo/pageList";
        HttpResponse response = HttpRequest.post(url)
                .form("jobGroup", jobGroupId)
                .form("executorHandler", executorHandler)
                .form("triggerStatus", -1)
                .cookie(jobLoginService.getCookie())
                .execute();

        String body = response.body();
        JSONArray array = JSONUtil.parse(body).getByPath("data", JSONArray.class);
        List<XxlJobInfo> list = array.stream()
                .map(o -> JSONUtil.toBean((JSONObject) o, XxlJobInfo.class))
                .collect(Collectors.toList());

        return list;
    }

    /**
     * 注册一个新任务，最终返回创建的新任务的 id：
     * @param xxlJobInfo
     * @return
     */
    public Integer addJobInfo(XxlJobInfo xxlJobInfo) {
        String url= xxlAutoRegisterProperties.getUrl()+"/jobinfo/add";
        Map<String, Object> paramMap = BeanUtil.beanToMap(xxlJobInfo);
        HttpResponse response = HttpRequest.post(url)
                .form(paramMap)
                .cookie(jobLoginService.getCookie())
                .execute();

        JSON json = JSONUtil.parse(response.body());
        Object code = json.getByPath("code");
        if (code.equals(200)){
            return Convert.toInt(json.getByPath("content"));
        }
        throw new RuntimeException("add jobInfo error!");
    }
}
