package com.kele.tkmybatis.utils;


import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.UnsupportedEncodingException;

/**
 *@author kele

 *@创建时间 2020/6/9
 */
public class CharsetUtil {

    private static final Logger logger = LoggerFactory.getLogger(CharsetUtil.class);


    public static String UTF_8 = "UTF-8";
    public static String GBK = "GBK";
    public static String ISO_8859_1 = "ISO-8859-1";

    /**
     * 将参数转码成utf-8 格式
     * @param args
     * @return
     */
    public static String  convertUnCharset(String args){
        if(StringUtils.isNotBlank(args)){
            try {
                if (args.equals(new String(args.getBytes(ISO_8859_1), ISO_8859_1))) {
                    args = new String(args.getBytes(ISO_8859_1), UTF_8);
                }
            }catch (UnsupportedEncodingException e){
                logger.error("参数解码错误: " + e.getMessage(), e);
            }
        }
        return args;
    }
}
