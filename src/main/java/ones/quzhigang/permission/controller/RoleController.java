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


import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import lombok.extern.slf4j.Slf4j;
import ones.quzhigang.permission.common.JsonData;
import ones.quzhigang.permission.model.SysUserModel;
import ones.quzhigang.permission.service.*;
import ones.quzhigang.permission.vo.RoleVo;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/sys/role")
@Slf4j
public class RoleController {


    @Autowired
    private SysRoleService sysRoleService;

    @Autowired
    private SysTreeService sysTreeService;

    @Autowired
    private SysRoleAclService sysRoleAclService;

    @Autowired
    private SysRoleUserService sysRoleUserService;

    @Autowired
    private SysUserService sysUserService;

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

    @RequestMapping("/roleTree.json")
    @ResponseBody
    public JsonData roleTree(@Param("roleId") Long roleId){
        return JsonData.sucess(sysTreeService.roleTrss(roleId));
    }

    @RequestMapping("/changeAcls.json")
    @ResponseBody
    public JsonData changeAcls(@Param("roleId") Long roleId, @RequestParam(value = "aclIds", required = false, defaultValue = "") String aclIds){
        sysRoleAclService.changeAcls(roleId, aclIds);
        return JsonData.sucess();
    }

    @RequestMapping("/users.json")
    @ResponseBody
    public JsonData users(@RequestParam("roleId") Long roleId){
        List<SysUserModel> selectUserList = sysRoleUserService.getListByRoleId(roleId);
        List<SysUserModel> allUserList = sysUserService.getAllUser();
        List<SysUserModel> unSelectUserList = Lists.newArrayList();

        Set<Long> selectedUserIdSet = selectUserList.stream().map(sysUserModel -> sysUserModel.getId()).collect(Collectors.toSet());

        for (SysUserModel sysUserModel : allUserList){
            if(sysUserModel.getStatus() == 1 && !selectedUserIdSet.contains(sysUserModel.getId())){
                unSelectUserList.add(sysUserModel);
            }
        }

        Map<String, List<SysUserModel>> resultmap = Maps.newHashMap();
        resultmap.put("selected", selectUserList);
        resultmap.put("unselected", unSelectUserList);
        return JsonData.sucess(resultmap);
    }

    @RequestMapping("/changeUsers.json")
    @ResponseBody
    public JsonData changeUser(@Param("roleId") Long roleId, @RequestParam(value = "userIds", required = false, defaultValue = "") String userIds){
        sysRoleUserService.changeRoleUsers(roleId, userIds);
        return JsonData.sucess();
    }

    public static void main(String args[]){
        List<Long> cities = Arrays.asList(1L, 2L, 3L, 6L);
        List<String> listIds = cities.stream().map(s -> String.valueOf(s)).collect(Collectors.toList());
        String citiesCommaSeparated = String.join(",", listIds);
        System.out.println(citiesCommaSeparated);
    }
}
