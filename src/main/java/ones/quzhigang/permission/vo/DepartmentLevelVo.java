/**
 * Copyright (C), 2018, 上海米袋融资有限公司
 * ProjectName: permission
 * FileName: DepartmentLevelVo
 * Author:   屈志刚
 * Date:     2018/6/22 0022 11:02
 * Description: 部门层级关系VO
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package ones.quzhigang.permission.vo;


import com.google.common.collect.Lists;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import ones.quzhigang.permission.model.SysDeptModel;
import org.springframework.beans.BeanUtils;

import java.util.List;

@Getter
@Setter
@ToString
public class DepartmentLevelVo extends SysDeptModel {

    private List<DepartmentLevelVo> departmentLevelList = Lists.newArrayList();

    public static DepartmentLevelVo adapt(SysDeptModel deptModel){

        DepartmentLevelVo vo = new DepartmentLevelVo();
        BeanUtils.copyProperties(deptModel, vo);
        return vo;
    }
}
