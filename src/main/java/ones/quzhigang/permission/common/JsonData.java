/**
 * Copyright (C), 2018, 上海米袋融资有限公司
 * ProjectName: permission
 * FileName: JsonData
 * Author:   屈志刚
 * Date:     2018/6/21 0021 16:12
 * Description: json返回结果
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package ones.quzhigang.permission.common;

import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;
import java.util.Map;

@Getter
@Setter
public class JsonData {

    private boolean ret;

    private String msg;

    private Object data;

    public JsonData(boolean ret) {
        this.ret = ret;
    }

    public static JsonData sucess(Object data, String msg) {

        JsonData jsonData = new JsonData(true);
        jsonData.data = data;
        jsonData.msg = msg;
        return jsonData;

    }

    public static JsonData sucess(Object data) {
        JsonData jsonData = new JsonData(true);
        jsonData.data = data;
        return jsonData;
    }

    public static JsonData sucess() {
        return new JsonData(true);
    }



    public static JsonData fail(String msg){
        JsonData jsonData = new JsonData(false);
        jsonData.setMsg(msg);
        return jsonData;
    }

    public Map<String, Object> toMap(){
        HashMap<String, Object> result = new HashMap<String, Object>();
        result.put("ret", ret);
        result.put("msg", msg);
        result.put("data", data);

        return result;
    }
}
