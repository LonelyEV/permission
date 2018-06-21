/**
 * Copyright (C), 2018, 上海米袋融资有限公司
 * ProjectName: permission
 * FileName: JsonMapper
 * Author:   屈志刚
 * Date:     2018/6/21 0021 17:54
 * Description: json工具类
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package ones.quzhigang.permission.common;

import lombok.extern.slf4j.Slf4j;
import org.codehaus.jackson.map.DeserializationConfig;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.SerializationConfig;
import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.codehaus.jackson.map.ser.impl.SimpleFilterProvider;
import org.codehaus.jackson.type.TypeReference;


@Slf4j
public class JsonMapper {

    private static ObjectMapper objectMapper = new ObjectMapper();

    static{
        objectMapper.disable(DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES);
        objectMapper.configure(SerializationConfig.Feature.FAIL_ON_EMPTY_BEANS, false);
        objectMapper.setFilters(new SimpleFilterProvider().setFailOnUnknownId(false));
        objectMapper.setSerializationInclusion(JsonSerialize.Inclusion.NON_EMPTY);
    }

    public static <T> String obj2String(T src){

        if(src == null){
            return null;
        }

        try{
            return src instanceof String ? (String) src : objectMapper.writeValueAsString(src);
        }catch (Exception e){
            log.warn("parse object to json exception", e);
            return null;
        }

    }

    public static <T> T string2Obj(String src, TypeReference<T> typeReference){

        if(src == null || typeReference == null){
            return null;
        }

        try{
            return (T) (typeReference.getType().equals(String.class) ? src : objectMapper.readValue(src, typeReference));
        }catch (Exception e){
            log.warn("parse String to Object exception, String:{}, TypeReference<T>:{}, error:{}", src, typeReference.getType(), e);
            return null;
        }
    }
}
