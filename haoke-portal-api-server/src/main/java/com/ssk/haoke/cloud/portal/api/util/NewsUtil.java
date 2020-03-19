package com.ssk.haoke.cloud.portal.api.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 抓取新闻内容的辅助类
 * @author geenkDC
 * @time 2015-07-28 15:15:04
 */
public class NewsUtil {
    /**
     * 通过提交的URL来抓取出新闻的链接
     * @param url
     * @return
     * @throws Exception
     */
    public static ArrayList<String> findUrlByUrl(String url) throws Exception
    {
        URL url0=new URL(url);
        ArrayList<String> urlList=new ArrayList<String>();
        URLConnection con;
        BufferedReader br=null;
        try {
            con = url0.openConnection();
            InputStream in=con.getInputStream();
            br=new BufferedReader(new InputStreamReader(in));
            String str="";
            while((str=br.readLine())!=null)
            {
                urlList.addAll(findUrl(str));
            }
        } catch (IOException e) {
            throw new RuntimeException("URL读写错误:"+e.getMessage());
        }
        if(br!=null)
        {
            try {
                br.close();
            } catch (IOException e) {
                throw new RuntimeException("URL流关闭异常:"+e.getMessage());
            }
        }
        return urlList;
    }

    /**抓取新闻URL的真正实现类
     * @param str
     * @return
     */
    public static ArrayList<String> findUrl(String str)
    {
        ArrayList<String> urlList=new ArrayList<String>();
        //匹配新闻的URL
        String regex="http://[a-zA-Z0-9_\\.:\\d/?=&%]+\\.html";
        Pattern p=Pattern.compile(regex);
        Matcher m=p.matcher(str);
        //找符合正则匹配的字串
        while(m.find())
        {
            String subStr=m.group().substring(m.group().lastIndexOf("/")+1, m.group().lastIndexOf(".html"));

            try {
                if (subStr.matches("[0-9]*")) {
                    urlList.add(m.group());

                }
            } catch (Exception e) {
                throw new RuntimeException("匹配新闻URL出错:"+e.getMessage());
            }
        }
        return urlList;
    }

    /**
     * 根据URL找到其的新闻内容
     * @param url
     * @return
     * @throws Exception
     */
    public static ArrayList<String> findContentByUrl(String url) throws Exception {
        URL url1=new URL(url);
        ArrayList<String> conList=new ArrayList<String>();
        URLConnection con;
        BufferedReader br=null;
        try {
            con = url1.openConnection();
            InputStream in=con.getInputStream();
            InputStreamReader isr=new InputStreamReader(in, "utf-8");
            br=new BufferedReader(isr);
            String str="";
            StringBuffer sb=new StringBuffer();
            while((str=br.readLine())!=null)
            {
                sb.append(str);
            }
            conList.addAll(findContent(sb.toString()));
        } catch (IOException e) {
            throw new RuntimeException("URL读写错误:"+e.getMessage());
        }
        if(br!=null)
        {
            try {
                br.close();
            } catch (IOException e) {
                throw new RuntimeException("URL流关闭异常:"+e.getMessage());
            }
        }
        return conList;
    }

    /**
     * 抓取新闻内容的真正实现类
     * @param str
     * @return
     */
    public static ArrayList<String> findContent(String str) {
        ArrayList<String> strList=new ArrayList<String>();
        //匹配新闻内容div
        String regex="<div class=\"con_box\">([\\s\\S]*)</div>([\\s\\S]*)<div class=\"left_con\">";
        Pattern p=Pattern.compile(regex);
        Matcher m=p.matcher(str);
        //找符合正则匹配的字串
        while(m.find())
        {
            try {
                strList.add(new String(m.group()));
            } catch (Exception e) {
                throw new RuntimeException("抓取新闻内容出错:"+e.getMessage());
            }
        }
        return strList;
    }

    public static void main(String[] args) {
        try {
            ArrayList<String> strings = NewsUtil.findUrlByUrl("https://news.sina.com.cn/");
            for (String string : strings) {
                System.out.println(string);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}