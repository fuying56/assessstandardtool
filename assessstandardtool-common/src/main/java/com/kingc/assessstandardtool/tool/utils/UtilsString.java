package com.kingc.assessstandardtool.tool.utils;

import org.apache.commons.lang3.StringUtils;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * @author Administrator
 */
@SuppressWarnings({"ALL", "AlibabaCommentsMustBeJavadocFormat"})
public class UtilsString extends StringUtils {

    private static Pattern NUMBER_PATTERN = Pattern.compile("[0-9]+");
    private static Pattern STRING_TO_HUMP_PATTERN = Pattern.compile("_(\\w)");
    private static Pattern STRING_HUMP_PATTERN = Pattern.compile("[A-Z]");

    public static String getUUID() {
        UUID uuid = UUID.randomUUID();
        String s = uuid.toString();
        return s.substring(0, 8) + s.substring(9, 13) + s.substring(14, 18) + s.substring(19, 23) + s.substring(24);
    }

    /**
     * 首字母转大写
     */
    public static String toUpperCaseFirstOne(String s) {
        if (s.length() > 0) {
            return s.replaceFirst(s.substring(0, 1), s.substring(0, 1).toUpperCase());
        }
        return s;
    }

    /**
     * @param s
     * @return
     */
    @SuppressWarnings("AlibabaCommentsMustBeJavadocFormat")
    public static String toLowerCaseFirstOne(String s) {
        if (s.length() > 0) {
            return s.replaceFirst(s.substring(0, 1), s.substring(0, 1).toLowerCase());
        }
        return s;
    }

    /**
     * 下划线转驼峰
     *
     * @param str 下划线字符串
     * @return 驼峰形式的字符串
     */
    @SuppressWarnings("AlibabaAvoidPatternCompileInMethod")
    public static String lineToHump(String str) {
        Pattern linePattern = STRING_TO_HUMP_PATTERN;

        Matcher matcher = linePattern.matcher(str);
        StringBuffer sb = new StringBuffer();
        while (matcher.find()) {
            matcher.appendReplacement(sb, matcher.group(1).toUpperCase());
        }
        matcher.appendTail(sb);
        return sb.toString();
    }

    /**
     * 驼峰转下划线
     *
     * @param str 驼峰形式的字符串
     * @return 下划线形式的字符串
     */
    @SuppressWarnings("AlibabaAvoidPatternCompileInMethod")
    public static String humpToLine(String str) {
        Pattern humpPattern = STRING_HUMP_PATTERN;
        Matcher matcher = humpPattern.matcher(str);
        StringBuffer sb = new StringBuffer();
        while (matcher.find()) {
            matcher.appendReplacement(sb, "_" + matcher.group(0).toLowerCase());
        }
        matcher.appendTail(sb);
        return sb.toString();
    }


    /**
     * 解析出url参数中的键值对
     * 如 "index.jsp?Action=del&id=123"，解析出Action:del,id:123存入map中
     *
     * @param URL url地址
     * @return url请求参数部分
     */
    public static Map<String, String> URLRequest(String URL) {
        Map<String, String> mapRequest = new HashMap<String, String>();

        String[] arrSplit = null;

        String strUrlParam = TruncateUrlPage(URL);
        if (strUrlParam == null) {
            return mapRequest;
        }
        arrSplit = strUrlParam.split("[&]");
        for (String strSplit : arrSplit) {
            String[] arrSplitEqual = null;
            arrSplitEqual = strSplit.split("[=]");

            //解析出键值
            if (arrSplitEqual.length > 1) {
                //正确解析
                mapRequest.put(arrSplitEqual[0], arrSplitEqual[1]);

            } else {
                if (arrSplitEqual[0] != "") {
                    //只有参数没有值，不加入
                    mapRequest.put(arrSplitEqual[0], "");
                }
            }
        }
        return mapRequest;
    }

    /**
     * 去掉url中的路径，留下请求参数部分
     *
     * @param strURL url地址
     * @return url请求参数部分
     */
    private static String TruncateUrlPage(String strURL) {
        if (strURL.indexOf('?') < 0) {
            return strURL;
        }

        String strAllParam = null;
        String[] arrSplit = null;

        strURL = strURL.trim();


        arrSplit = strURL.split("[?]");
        if (strURL.length() > 1) {
            if (arrSplit.length > 1) {
                if (arrSplit[1] != null) {
                    strAllParam = arrSplit[1];
                }
            }
        }

        return strAllParam;
    }
}
