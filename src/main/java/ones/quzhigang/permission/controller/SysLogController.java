/**
 * Copyright (C), 2018, 上海米袋融资有限公司
 * ProjectName: permission
 * FileName: SysLogController
 * Author:   屈志刚
 * Date:     2018/7/9 0009 11:01
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package ones.quzhigang.permission.controller;

import ones.quzhigang.permission.common.JsonData;
import ones.quzhigang.permission.service.SysLogService;
import ones.quzhigang.permission.vo.SearchLogQueryVo;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;


@Controller
@RequestMapping("/sys/log")
public class SysLogController {

    @Autowired
    private SysLogService sysLogService;


    @RequestMapping("/log.page")
    public ModelAndView page(){
        return new ModelAndView("log");
    }


    @RequestMapping("/page.json")
    @ResponseBody
    public JsonData search(SearchLogQueryVo queryVo){
        return JsonData.sucess(sysLogService.searchpageList(queryVo));
    }

    @RequestMapping("/recover.json")
    @ResponseBody
    public JsonData recover(@Param("id") Long id){
        sysLogService.recover(id);
        return JsonData.sucess();
    }
}
