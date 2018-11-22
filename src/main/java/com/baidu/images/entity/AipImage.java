package com.baidu.images.entity;

import com.baidu.aip.imagesearch.AipImageSearch;

public class AipImage {
    // 设置APPID/AK/SK
    private static final String APP_ID = "你的APP_ID";
    private static final String API_KEY = "你的API_KEY";
    private static final String SECRET_KEY = "你的SECRET_KEY";

    private volatile static  AipImageSearch client = null;

    private AipImage(){

    }

    public synchronized static AipImageSearch getInstance(){

        if (null == client){
            // 初始化一个AipImageSearch
            client = new AipImageSearch(APP_ID, API_KEY, SECRET_KEY);
        }
        // 可选：设置网络连接参数
        client.setConnectionTimeoutInMillis(2000);
        client.setSocketTimeoutInMillis(60000);

        // 可选：设置代理服务器地址, http和socket二选一，或者均不设置
//        client.setHttpProxy("proxy_host", proxy_port);  // 设置http代理
//        client.setSocketProxy("proxy_host", proxy_port);  // 设置socket代理

        // 可选：设置log4j日志输出格式，若不设置，则使用默认配置
        // 也可以直接通过jvm启动参数设置此环境变量
        //System.setProperty("aip.log4j.conf", "classpath:log4j.properties");

        return client;
    }
}
