/**
 * Copyright (C), 2018, 上海米袋融资有限公司
 * ProjectName: permission
 * FileName: AclRoleVo
 * Author:   屈志刚
 * Date:     2018/7/6 0006 11:08
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package ones.quzhigang.permission.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import ones.quzhigang.permission.model.SysAclModel;
import org.springframework.beans.BeanUtils;

@Getter
@Setter
@ToString
public class AclRoleVo extends SysAclModel {

    // 是否默认选中
    private boolean checked = false;

    // 是否有操作权限
    private boolean hasAcl = false;

    public static AclRoleVo adept(SysAclModel acl){
        AclRoleVo aclRoleVo = new AclRoleVo();
        BeanUtils.copyProperties(acl,aclRoleVo);
        return aclRoleVo;
    }
}
