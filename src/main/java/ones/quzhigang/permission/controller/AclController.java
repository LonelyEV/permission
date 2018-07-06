/**
 * Copyright (C), 2018, 上海米袋融资有限公司
 * ProjectName: permission
 * FileName: AclController
 * Author:   屈志刚
 * Date:     2018/7/5 0005 15:13
 * Description: 权限点模块控制器
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package ones.quzhigang.permission.controller;


import lombok.extern.slf4j.Slf4j;
import ones.quzhigang.permission.beans.PageQuery;
import ones.quzhigang.permission.beans.PageResult;
import ones.quzhigang.permission.common.JsonData;
import ones.quzhigang.permission.model.SysAclModel;
import ones.quzhigang.permission.service.SysAclService;
import ones.quzhigang.permission.vo.AclVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/sys/acl")
@Slf4j
public class AclController {

    @Autowired
    private SysAclService sysAclService;


    @RequestMapping("/save.json")
    @ResponseBody
    public JsonData save(AclVo vo){
        sysAclService.insert(vo);
        return JsonData.sucess();
    }

    @RequestMapping("/update.json")
    @ResponseBody
    public JsonData update(AclVo vo){
        sysAclService.update(vo);
        return JsonData.sucess();
    }

    @RequestMapping("/page.json")
    @ResponseBody
    public JsonData list(@RequestParam("aclModuleId") Integer aclModuleId, PageQuery query){

        PageResult<SysAclModel> result = sysAclService.getPageByAclModuleId(aclModuleId, query);
        return JsonData.sucess(result);
    }


}
