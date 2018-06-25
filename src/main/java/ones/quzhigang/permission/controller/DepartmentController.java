/**
 * Copyright (C), 2018, 上海米袋融资有限公司
 * ProjectName: permission
 * FileName: DepartmentController
 * Author:   屈志刚
 * Date:     2018/6/22 0022 9:38
 * Description: 部门控制器
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package ones.quzhigang.permission.controller;

import lombok.extern.slf4j.Slf4j;
import ones.quzhigang.permission.common.JsonData;
import ones.quzhigang.permission.service.SysDeptService;
import ones.quzhigang.permission.service.SysTreeService;
import ones.quzhigang.permission.vo.DepartmentLevelVo;
import ones.quzhigang.permission.vo.DepartmmentVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("department")
@Slf4j
public class DepartmentController {

    @Autowired
    private SysDeptService SysDeptService;

    @Autowired
    private SysTreeService sysTreeService;


    /**
     * 功能描述: <br>
     * 〈保存部门信息〉
     *
     * @param vo
     * @return: ones.quzhigang.permission.common.JsonData
     * @@throws:
     * @Version: 1.0.0
     * @Author: 屈志刚
     * @Date: 2018/6/22 0022 13:25
     */
    @RequestMapping("save.json")
    @ResponseBody
    public JsonData save(@RequestBody  DepartmmentVo vo){

        SysDeptService.insert(vo);
        return JsonData.sucess();

    }

    @RequestMapping("/update.json")
    @ResponseBody
    public JsonData update(@RequestBody DepartmmentVo vo){
        SysDeptService.update(vo);
        return JsonData.sucess();
    }

    /**
     * 功能描述: <br>
     * 〈获取所有的部门树〉
     *
     * @param
     * @return: ones.quzhigang.permission.common.JsonData
     * @@throws:
     * @Version: 1.0.0
     * @Author: 屈志刚
     * @Date: 2018/6/22 0022 13:28
     */
    @RequestMapping("/tree")
    @ResponseBody
    public JsonData tree(){

        List<DepartmentLevelVo> departmentLevelVoList = sysTreeService.deptTree();
        return JsonData.sucess(departmentLevelVoList);
    }


}
