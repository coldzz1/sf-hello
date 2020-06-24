package ys.JsonUtil;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class JsonUtil {

    private final static Logger logger = LoggerFactory.getLogger(JsonUtil.class);


    public static String toJsonFromObject(Object obj) {
        if (obj != null && !"".equals(obj)) {
            try {
                return JSON.toJSONString(obj, new SerializerFeature[]{SerializerFeature.WriteNullStringAsEmpty, SerializerFeature.WriteNullBooleanAsFalse});
            } catch (Exception var2) {
                logger.error("Json转对象出错，obj=" + obj, var2);
                return null;
            }
        } else {
            return "";
        }
    }



}
