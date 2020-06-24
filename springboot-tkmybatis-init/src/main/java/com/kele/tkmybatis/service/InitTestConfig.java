package com.kele.tkmybatis.service;

import com.kele.tkmybatis.common.config.Constant;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;

/**
 * Description: 通过 afterPropertiesSet 在将bean的所有属性被初始化后调用
 *              但是会在init前调用
 * <p>
 * Created by ys on 2020/6/9 10:25
 */
public class InitTestConfig implements InitializingBean {

    /**
     * 这里@Value 一般通过配置文件将属性注入 也可以通过Apollo 统一配置
     */
    /**
     * 在将这些变量通过afterPropertiesSet赋值给全局变量 给予调用
     */
    @Value("${test.timeout}")
    private String timeout;

    @Value("${http.oss.url}")
    private String httpOssUrl;

    @Value("${http.oss.key}")
    private String httpOssKey;

    @Value("${http.oss.bucket.name}")
    private String httpOssBucketName;

    @Value("${httpOssUid}")
    private String httpOssUid;


    @Override
    public void afterPropertiesSet() throws Exception {
        Constant.HTTP_OSS_URL=httpOssUrl;
        Constant.HTTP_OSS_KEY=httpOssKey;
        Constant.HTTP_OSS_BUCKET_NAME=httpOssBucketName;
        Constant.HTTP_OSS_UID=httpOssUid;
    }
}
