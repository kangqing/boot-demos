package com.yunqing.demoatest.utils;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.cglib.beans.BeanGenerator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.math.RoundingMode;
import java.net.InetAddress;
import java.net.URLEncoder;
import java.net.UnknownHostException;
import java.sql.Timestamp;
import java.text.*;
import java.util.*;

public class CommonUtils
{
    private static final Logger LOGGER = LoggerFactory.getLogger(CommonUtils.class);

    public static String getUUID()
    {
        return UUID.randomUUID().toString().replaceAll("-", "").toUpperCase();
    }

    /**
     * 格式化日期 pattern如果为null则默认格式为yyyy/MM/dd HH:mm:ss
     * @param date
     * @param pattern
     * @return
     */
    public static String dateFormat(Date date, String pattern)
    {
        if (pattern == null)
        {
            pattern = "yyyy/MM/dd HH:mm:ss";
        }
        return DateFormatUtils.format(date, pattern);
    }

    /**
     * 格式化日期 pattern如果为null则默认格式为yyyy/MM/dd HH:mm:ss
     * @param date
     * @param pattern
     * @return
     */
    public static String dateFormat(Timestamp date, String pattern)
    {
        if (pattern == null)
        {
            pattern = "yyyy/MM/dd HH:mm:ss";
        }
        return DateFormatUtils.format(date, pattern);
    }

    /**
     * 将String类型的日期格式化为Date类型 pattern如果为null则默认格式为yyyy/MM/dd HH:mm:ss
     * @param date
     * @param pattern
     * @return
     * @throws ParseException
     */
    public static Date stringToDate(String date, String pattern) throws ParseException
    {
        if (pattern == null)
        {
            pattern = "yyyy/MM/dd HH:mm:ss";
        }
        DateFormat formater = new SimpleDateFormat(pattern);
        return formater.parse(date);
    }

    /**
     * 格式化数字
     * @param num
     * @param value 保留的小数位
     * @return
     */
    public static String numberFormat(double num, int value)
    {
        NumberFormat formater = DecimalFormat.getInstance();
        formater.setMaximumFractionDigits(value);
        formater.setGroupingUsed(false);
        return formater.format(num);
    }

    /**
     * 格式化数字
     * @param str
     * @param value 保留的小数位
     * @return
     */
    public static String numberFormat(String str, int value)
    {
        double num = Double.parseDouble(str);
        NumberFormat formater = DecimalFormat.getInstance();
        formater.setMaximumFractionDigits(value);
        formater.setGroupingUsed(false);
        return formater.format(num);
    }

    /**
     * 格式化数字
     * @param str
     * @param formatStyle 传入null时默认为0.00
     * @return
     */
    public static String numberFormat(String str, String formatStyle)
    {
        if (str == null || ("").equals(str))
        {
            return "";
        }
        if (formatStyle == null)
        {
            formatStyle = "0.00";
        }
        Double num = Double.parseDouble(str);
        DecimalFormat formater = new DecimalFormat(formatStyle);
        formater.setRoundingMode(RoundingMode.HALF_UP);
        return formater.format(num);
    }

    /**
     * 格式化数字
     * @param num
     * @param formatStyle 传入null时默认为0.00
     * @return
     */
    public static String numberFormat(double num, String formatStyle)
    {
        if (formatStyle == null)
        {
            formatStyle = "0.00";
        }
        DecimalFormat formater = new DecimalFormat(formatStyle);
        formater.setRoundingMode(RoundingMode.HALF_UP);
        return formater.format(num);
    }

    /**
     * util.Date转换为sql.Date
     * @param date
     * @return
     */
    public static java.sql.Date utilToSql(Date date)
    {
        return new java.sql.Date(date.getTime());
    }

    /**
     * @Title: getFutureSecond
     * @Description: 获取n秒前/后的时间
     * @param appDate
     * @param format
     * @param seconds
     * @return
     */
    public static String getFutureSecond(String appDate, String format, int seconds)
    {
        String future = "";
        try
        {
            Calendar calendar = GregorianCalendar.getInstance();
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);
            Date date = simpleDateFormat.parse(appDate);
            calendar.setTime(date);
            calendar.add(Calendar.SECOND, seconds);
            date = calendar.getTime();
            future = simpleDateFormat.format(date);
        }
        catch (Exception e)
        {
            LOGGER.error("日期转换失败", e);
        }
        return future;
    }

    /**
     * 返回传入的日期的前N天或后N天的日期
     * @param appDate
     * @param format
     * @param days
     * @return
     */
    public static String getFutureDay(String appDate, String format, int days)
    {
        String future = "";
        try
        {
            Calendar calendar = GregorianCalendar.getInstance();
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);
            Date date = simpleDateFormat.parse(appDate);
            calendar.setTime(date);
            calendar.add(Calendar.DATE, days);
            date = calendar.getTime();
            future = simpleDateFormat.format(date);
        }
        catch (Exception e)
        {
            LOGGER.error("日期转换失败", e);
        }
        return future;
    }

    /**
     * @Title: getFutureDay
     * @Description: 返回传入的日期的前N天或后N天的日期
     * @param date
     * @param days
     * @return
     */
    public static Date getFutureDay(Date date, int days)
    {
        try
        {
            Calendar calendar = GregorianCalendar.getInstance();
            calendar.setTime(date);
            calendar.add(Calendar.DATE, days);
            return date = calendar.getTime();
        }
        catch (Exception e)
        {
            return null;
        }
    }

    /**
     * @Title: getFutureMonth
     * @Description: 返回传入的日期的前N月或后N月的日期
     * @param appDate
     * @param format
     * @param months
     * @return
     */
    public static String getFutureMonth(String appDate, String format, int months)
    {
        String future = "";
        try
        {
            Calendar calendar = GregorianCalendar.getInstance();
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);
            Date date = simpleDateFormat.parse(appDate);
            calendar.setTime(date);
            calendar.add(Calendar.MONTH, months);
            date = calendar.getTime();
            future = simpleDateFormat.format(date);
        }
        catch (Exception e)
        {
            LOGGER.error("日期转换失败", e);
        }
        return future;
    }

    /**
     * 返回当前的时间戳
     * @add by lz
     * @return
     * @throws Exception
     */
    public static Timestamp getCurrentTimestamp()
    {
        return new Timestamp(System.currentTimeMillis());
    }

    /**
     * 返回当前日期时间
     * @add by xx
     * @return
     * @throws Exception
     */
    public static String getCurrentDateTime()
    {
        return dateFormat(new Date(), null);
    }

    /**
     * 返回当前日期
     * @add by xx
     * @return
     * @throws Exception
     */
    public static String getCurrentDate()
    {
        return dateFormat(new Date(), "yyyy/MM/dd");
    }

    /**
     * 取得随机字符串
     * @param len 长度
     * @param type 类型 1:数字+字母混合 2:字母 3:数字
     * @return
     */
    public static String getRandomStr(int len, int type)
    {
        String str = "";
        Random random = new Random();
        for (int i = 0; i < len; i++)
        {
            if (type == 1)
            {
                String charOrNum = random.nextInt(2) % 2 == 0 ? "char" : "num";
                int choice = random.nextInt(2) % 2 == 0 ? 65 : 97; // 取得大写字母还是小写字母
                if (("char").equals(charOrNum))
                {
                    str += (char) (choice + random.nextInt(26));
                }
                else if (("num").equals(charOrNum))
                {
                    str += String.valueOf(random.nextInt(10));
                }
            }
            else if (type == 2)
            {
                int choice = random.nextInt(2) % 2 == 0 ? 65 : 97;
                str += (char) (choice + random.nextInt(26));
            }
            else if (type == 3)
            {
                str += String.valueOf(random.nextInt(10));
            }
            else
            {
                str = "";
            }
        }
        return str;
    }

    /**
     * MethodName: getRandomStr </br>
     * Description: 根据长度和给定的字符数组生成随机字符串
     * @param len 字符串长度
     * @param charStr 字符数据组
     * @return
     */
    public static String getRandomStr(int len, String charStr)
    {
        String result = "";
        if (charStr == null)
        {
            return result;
        }
        int max = charStr.length();
        if (max == 0)
        {
            return result;
        }

        Random random = new Random();
        for (int i = 0; i < len; i++)
        {
            int choice = random.nextInt(max) % max;
            result += charStr.charAt(choice);
        }
        return result;
    }

    /**
     * @Title: replace @Description: 将字符串的指定位置替换成相同的字符 @param @param
     *         str @param @param 开始替换的位置 @param @param 结束替换的位置 @param @param
     *         替换成的字符 @param @return @param @throws Throwable 设定文件 @return
     *         String 返回类型 @throws
     */
    public static String strReplace(String str, int startindex, int endindex, String newChar) throws Throwable
    {
        String s1 = "";
        String s2 = "";
        try
        {
            s1 = str.substring(0, startindex - 1);
            s2 = str.substring(endindex, str.length());
        }
        catch (Exception ex)
        {
            throw new Throwable("替换的位数大于字符串的位数");
        }
        Integer length = endindex - startindex;
        String charTmp = newChar;
        for (int i = 0; i < length; i++)
        {
            newChar += charTmp;
        }
        return s1 + newChar + s2;
    }

    /**
     * 检测密码强度
     * @param pwd
     * @return
     */
    public static int checkPwd(String pwd)
    {
        String regex = "^(?:([a-z])|([A-Z])|([0-9])|(.)){6,}|(.)+$";
        return pwd.replaceAll(regex, "$1$2$3$4$5").length();
    }

    public static Map<String, Object> doParameters(HttpServletRequest request)
    {
        Map<String, Object> param = new HashMap<String, Object>();
        Map<String, String[]> paramMap = request.getParameterMap();
        for (String key : paramMap.keySet())
        {
            String[] value = paramMap.get(key);
            if (value != null && !"".equals(value[0]))
            {
                param.put(key, StringUtils.join(value));
            }
        }
        return param;
    }

    public static String getCurrentYearAndMonth()
    {
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH) + 1;// 月份别忘了+1

        return year + "-" + (month < 10 ? "0" + month : month);

    }

    /**
     * @Title: getMonday
     * @Description: 获取本周一
     * @param date
     * @return
     */
    public static Date getMonday(Date date)
    {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int week = calendar.get(Calendar.DAY_OF_WEEK) - 1;
        if (week == 0)
        {
            week = 7;
        }
        calendar.add(Calendar.DATE, 1 - week);
        return calendar.getTime();
    }

    /**
     * @Title: getMonday
     * @Description: 获取本周一
     * @param dateStr
     * @param pattern
     * @return
     */
    public static String getMonday(String dateStr, String pattern)
    {
        try
        {
            Date date = stringToDate(dateStr, pattern);
            date = getMonday(date);
            return dateFormat(date, pattern);
        }
        catch (ParseException e)
        {
            return null;
        }
    }

    /**
     * @Title: getDayOfWeek
     * @Description: 获取日期是星期几
     * @param dateStr
     * @param pattern
     * @return 1-7对应周一-周日
     */
    public static int getDayOfWeek(String dateStr, String pattern)
    {
        try
        {
            Date date = stringToDate(dateStr, pattern);
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);
            int week = calendar.get(Calendar.DAY_OF_WEEK) - 1;
            if (week == 0)
            {
                week = 7;
            }
            return week;
        }
        catch (ParseException e)
        {
            return -1;
        }
    }

    public static String getNowMaxYM()
    {
        int nowyear = Integer.parseInt(new SimpleDateFormat("yyyy").format(new Date()));
        int nowmonth = Integer.parseInt(new SimpleDateFormat("MM").format(new Date()));
        int nowdate = Integer.parseInt(new SimpleDateFormat("dd").format(new Date()));
        if (nowdate < 20)
        {
            if (nowmonth == 1)
            {
                return nowyear - 1 + "-" + 11;
            }
            else if (nowmonth == 2)
            {
                return nowyear - 1 + "-" + 12;
            }
            else
            {
                return nowyear + "-" + (nowmonth - 2);
            }
        }
        else
        {
            if (nowmonth == 1)
            {
                return nowyear - 1 + "" + 12;
            }
            else
            {
                return nowyear + "-" + (nowmonth - 1);
            }
        }
    }

    /**
     * 获取字符在字符串中出现的位置
     * @author qiaopeng
     * @param str
     * @param pattern
     * @return
     */
    public static List<Integer> indexOf(String str, String pattern)
    {
        List<Integer> indexs = new ArrayList<Integer>();
        for (int i = 0; i < (str.length() - pattern.length()); i++)
        {
            int j = 0;
            while (j < pattern.length())
            {
                if (str.charAt(i + j) != pattern.charAt(j))
                {
                    break;
                }

                j++;
            }
            if (j == pattern.length())
            {
                indexs.add(i);
            }
        }
        return indexs;
    }

    /**
     * 获取客户端真实IP
     * @param request
     * @return
     */
    public static String getIpAddr(HttpServletRequest request)
    {
        String ip = request.getHeader("X-Forwarded-For");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip))
        {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip))
        {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip))
        {
            ip = request.getHeader("HTTP_CLIENT_IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip))
        {
            ip = request.getHeader("HTTP_X_FORWARDED_FOR");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip))
        {
            ip = request.getRemoteAddr();
            if (ip.equals("127.0.0.1"))
            {
                InetAddress inet = null;
                try
                {
                    inet = InetAddress.getLocalHost();
                }
                catch (UnknownHostException e)
                {
                    LOGGER.error("", e);
                }
                ip = inet.getHostAddress();
            }
        }
        if (ip != null && ip.length() > 15)
        {
            if (ip.indexOf(",") > 0)
            {
                ip = ip.substring(0, ip.indexOf(","));
            }
        }
        return ip;
    }

    public static boolean isBaseClass(Class<?> clz)
    {
        return clz != null && clz.getClassLoader() == null;
    }

    /**
     * 判断是否为数值
     * @param str
     * @return
     */
    public static boolean isNumber(String str)
    {
        String reg = "^[0-9]+(.[0-9]+)?$";
        return str.matches(reg);
    }

    /**
     * 动态添加属性值
     * @param t 实体
     * @param columns 属性数组
     * @return
     */
    @SuppressWarnings("unchecked")
    public static <T> T generateBean(T t, String... columns)
    {
        BeanGenerator generator = new BeanGenerator();
        generator.setSuperclass(t.getClass());
        for (String str : columns)
        {
            generator.addProperty(str, String.class);
        }
        Object o = generator.create();
        BeanUtils.copyProperties(t, o);
        return (T) o;
    }

    /**
     * 下载文件
     * @param request
     * @param response
     * @param realPath 文件真实路径
     * @param fileName 给下载的文件起个名字,需要带后缀
     * @throws IOException
     */
    public static void downFile(HttpServletRequest request, HttpServletResponse response, String realPath,
            String fileName) throws IOException
    {
        String agent = request.getHeader("USER-AGENT");
        response.setContentType("application/x-download");
        if (agent != null && (agent.indexOf("Firefox") > -1 || agent.indexOf("Safari") > -1))
        {
            response.setHeader("Content-disposition",
                    "attachment; filename=" + new String(fileName.getBytes("UTF-8"), "ISO8859-1"));
        }
        else
        {
            response.setHeader("Content-disposition", "attachment; filename=" + URLEncoder.encode(fileName, "UTF-8"));
        }
        InputStream in = new BufferedInputStream(new FileInputStream(realPath));
        OutputStream os = response.getOutputStream();
        byte[] bit = new byte[1024];
        int i = 0;
        while (-1 != (i = in.read(bit, 0, bit.length)))
        {
            os.write(bit, 0, i);
        }
        os.flush();
        os.close();
        in.close();
    }

    /**
     * 将\r\n替换为<br>
     * @param str
     * @return
     */
    public static String replaceRN(String str)
    {
        if (str == null)
        {
            return str;
        }
        return str.replaceAll("\r\n", "<br>").replaceAll("\n\r", "<br>").replaceAll("\n", "<br>").replaceAll("\r",
                "<br>");
    }
}
