package com.yunqing.demo.elasticsearch;

import org.apache.commons.lang3.StringUtils;

@SuppressWarnings("rawtypes")
public class AjaxResult<T>
{

    public static final String SUCCESS_CODE = "0";

    /** 业务校验错误 **/
    public static final String FAILED_CODE = "1";

    /** 校验错误 **/
    public static final String VALIDATE_ERROR_CODE = "2";

    /** 会话超时 **/
    public static final String TIMEOUT_CODE = "9";

    public static final String TOKEN_ERROR_CODE = "99";

    /** 结果码 如上 **/
    private String code;

    /** 信息 **/
    private String message;

    /** 数据 **/
    private Object data;

    public AjaxResult()
    {
    }

    /**
     * Description:构造函数
     * @param code 状态码
     * @param message 信息
     * @param data 数据【可不填,可数组（第一个有效）】
     */
    public AjaxResult(String code, String message, Object... data)
    {
        super();
        this.code = code;
        this.message = message;
        if (data != null && data.length > 1)
        {
            this.data = data;
        }
        if (data != null && data.length == 1)
        {
            this.data = data[0];
        }
    }

    /**
     * MethodName: OK </br>
     * Description: 正常返回
     * @param data 数据
     * @return 数据对象
     */
    public static AjaxResult OK(Object... data)
    {
        return new AjaxResult(SUCCESS_CODE, "", data);
    }

    /**
     * MethodName: ERROR </br>
     * Description: 错误返回
     * @param code 状态码
     * @param message 信息
     * @return 数据对象
     */
    public static AjaxResult ERROR(String code, String message, Object... data)
    {
        if (StringUtils.isBlank(code))
        {
            code = FAILED_CODE;
        }
        return new AjaxResult(code, message, data);
    }

    public static AjaxResult ERROR(String message, Object... data)
    {
        return new AjaxResult(FAILED_CODE, message, data);
    }

    public String getCode()
    {
        return code;
    }

    public void setCode(String code)
    {
        this.code = code;
    }

    public String getMessage()
    {
        return message;
    }

    public void setMessage(String message)
    {
        this.message = message;
    }

    public Object getData()
    {
        return data;
    }

    public void setData(Object data)
    {
        this.data = data;
    }

}
