/**
 * Copyright (C), 2018, 上海米袋融资有限公司
 * ProjectName: permission
 * FileName: RoleController
 * Author:   屈志刚
 * Date:     2018/7/6 0006 9:40
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package ones.quzhigang.permission.controller;


import lombok.extern.slf4j.Slf4j;
import ones.quzhigang.permission.common.JsonData;
import ones.quzhigang.permission.service.SysRoleService;
import ones.quzhigang.permission.vo.RoleVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/sys/role")
@Slf4j
public class RoleController {


    @Autowired
    private SysRoleService sysRoleService;

    @RequestMapping("/role.page")
    public ModelAndView role(){
        return new ModelAndView("role");
    }

    @RequestMapping("/save.json")
    @ResponseBody
    public JsonData save(RoleVo vo){
        sysRoleService.insert(vo);
        return JsonData.sucess();
    }

    @RequestMapping("/update.json")
    @ResponseBody
    public JsonData update(RoleVo vo){
        sysRoleService.update(vo);
        return JsonData.sucess();
    }

    @RequestMapping("/list.json")
    @ResponseBody
    public JsonData list(){
        return JsonData.sucess(sysRoleService.getAll());
    }
}
