/**
 * Copyright (C), 2018, 上海米袋融资有限公司
 * ProjectName: permission
 * FileName: AclModuleLevelVo
 * Author:   屈志刚
 * Date:     2018/7/5 0005 11:37
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package ones.quzhigang.permission.vo;

import com.google.common.collect.Lists;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import ones.quzhigang.permission.model.SysAclModuleModel;
import org.springframework.beans.BeanUtils;

import java.util.List;

@Getter
@Setter
@ToString
public class AclModuleLevelVo extends SysAclModuleModel {

    private List<AclModuleLevelVo> aclModuleList = Lists.newArrayList();

    public static AclModuleLevelVo acl(SysAclModuleModel sysAclModuleModel){

        AclModuleLevelVo vo = new AclModuleLevelVo();
        BeanUtils.copyProperties(sysAclModuleModel, vo);
        return vo;
    }
}
