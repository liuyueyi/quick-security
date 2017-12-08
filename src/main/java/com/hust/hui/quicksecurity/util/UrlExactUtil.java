package com.hust.hui.quicksecurity.util;

import org.springframework.util.StringUtils;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * url 域名解析工具类
 *
 * Created by yihui on 2017/12/8.
 */
public class UrlExactUtil {


    /**
     * 获取url的域名
     * @param url
     * @return
     */
    public static String getDomain(String url) {
        if (StringUtils.isEmpty(url)) {
            return "";
        }

        if (!url.startsWith("http://") && !url.startsWith("https://")) {
            url = "http://" + url;
        }


        try{
            URI uri = new URI(url);
            return uri.getHost();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }

        return "";
    }



    /**
     * 从url中提取域名
     * <p/>
     * @param url
     * @return
     */
    public static String getDomainBySub(String url) {
        if (StringUtils.isEmpty(url)) {
            return null;
        }

        int first = url.startsWith("http") ? url.indexOf(':') + 3 : 0;


        // 参数的索引
        int paramIndex = url.indexOf("?");
        if(paramIndex < 0) { // 没有url参数
            paramIndex = url.length();
        }

        // path的索引
        int pathIndex = url.indexOf('/', first);
        if(pathIndex < 0) { // 没有path
            pathIndex = url.length();
        }


        // 域名中的端口号

        String domain = url.substring(first, Math.min(paramIndex, pathIndex));

        int portIndex = domain.indexOf(":", first);
        if (portIndex < 0) {
            return domain;
        } else {
            return domain.substring(0, portIndex);
        }
    }


    private static final Pattern FIRST_DOMAIN_PATTERN = Pattern.compile("([0-9a-zA-Z\\-_]+\\.)*([0-9a-zA-Z\\-_]+\\.[0-9a-zA-Z\\-_]+)");

    /**
     * 获取一级域名
     * <p>
     * eg: www.zbang.online  返回   zbang.online
     * eg: www.blog.zbang.online 返回 zbang.online
     * eg: local.b_new.z-bang.online 返回 z-bang.online
     * eg: http://www.test.baidu.com?z-bang.online 返回 baidu.com
     *
     * @param domain
     * @return
     */
    public static String getRootDomain(String domain) {
        if (StringUtils.isEmpty(domain)) {
            return "";
        }

        Matcher matcher = FIRST_DOMAIN_PATTERN.matcher(domain);
        if (matcher.find()) {
            if (matcher.groupCount() > 1) {
                return matcher.group(2);
            } else {
                return matcher.group(1);
            }
        }

        return "";
    }



}
