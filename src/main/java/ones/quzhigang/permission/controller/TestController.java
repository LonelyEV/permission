/**
 * Copyright (C), 2018, 上海米袋融资有限公司
 * ProjectName: permission
 * FileName: TestController
 * Author:   屈志刚
 * Date:     2018/6/20 0020 22:32
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package ones.quzhigang.permission.controller;

import lombok.extern.slf4j.Slf4j;
import ones.quzhigang.permission.common.JsonData;
import ones.quzhigang.permission.exception.PermissionException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/test")
@Slf4j
public class TestController {

    @RequestMapping("/hello.json")
    @ResponseBody
    public JsonData hello(){
        log.info("hello");
        throw new PermissionException("test exception");
        //return JsonData.sucess("hello");
    }
}
