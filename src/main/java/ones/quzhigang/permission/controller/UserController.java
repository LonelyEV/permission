/**
 * Copyright (C), 2018, 上海米袋融资有限公司
 * ProjectName: permission
 * FileName: UserController
 * Author:   屈志刚
 * Date:     2018/7/3 0003 17:03
 * Description: 用户管理控制器
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package ones.quzhigang.permission.controller;


import lombok.extern.slf4j.Slf4j;
import ones.quzhigang.permission.beans.PageQuery;
import ones.quzhigang.permission.beans.PageResult;
import ones.quzhigang.permission.common.JsonData;
import ones.quzhigang.permission.model.SysUserModel;
import ones.quzhigang.permission.service.SysUserService;
import ones.quzhigang.permission.vo.UserVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/sys/user")
@Slf4j
public class UserController {

    @Autowired
    private SysUserService sysUserService;


    @RequestMapping("/save.json")
    @ResponseBody
    public JsonData save(UserVo userVo){
        sysUserService.insert(userVo);
        return JsonData.sucess();
    }

    @RequestMapping("/update.json")
    @ResponseBody
    public JsonData update(UserVo userVo){
        sysUserService.update(userVo);
        return JsonData.sucess();
    }



    @RequestMapping("/page.json")
    @ResponseBody
    public JsonData page(@RequestParam("deptId") Integer deptId, PageQuery query){

        PageResult<SysUserModel> result = sysUserService.getPageByDeptId(deptId,query);

        return JsonData.sucess(result);
    }
}
