/**
 * Copyright (C), 2018, 上海米袋融资有限公司
 * ProjectName: permission
 * FileName: AclModuleController
 * Author:   屈志刚
 * Date:     2018/7/5 0005 10:12
 * Description: 权限模块控制器
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package ones.quzhigang.permission.controller;


import lombok.extern.slf4j.Slf4j;
import ones.quzhigang.permission.common.JsonData;
import ones.quzhigang.permission.service.SysAclModuleService;
import ones.quzhigang.permission.service.SysTreeService;
import ones.quzhigang.permission.vo.AclModuleLevelVo;
import ones.quzhigang.permission.vo.AclModuleVo;
import ones.quzhigang.permission.vo.DepartmentLevelVo;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/sys/aclModule")
@Slf4j
public class AclModuleController {

    @Autowired
    private SysAclModuleService SysAclModuleService;

    @Autowired
    private SysTreeService sysTreeService;

    @RequestMapping("/acl.page")
    public ModelAndView page(){
        return new ModelAndView("acl");
    }

    @RequestMapping("save.json")
    @ResponseBody
    public JsonData save(AclModuleVo vo){

        SysAclModuleService.insert(vo);
        return JsonData.sucess();

    }

    @RequestMapping("/update.json")
    @ResponseBody
    public JsonData update(AclModuleVo vo){
        SysAclModuleService.update(vo);
        return JsonData.sucess();
    }

    @RequestMapping("/tree.json")
    @ResponseBody
    public JsonData tree(){
        List<AclModuleLevelVo> aclModuleLevelVoList = sysTreeService.aclModuleTree();
        return JsonData.sucess(aclModuleLevelVoList);
    }

    @RequestMapping("/delete.json")
    @ResponseBody
    public JsonData delete(@Param("id") Long id){
        SysAclModuleService.delById(id);
        return JsonData.sucess();
    }



}
