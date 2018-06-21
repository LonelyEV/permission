/**
 * Copyright (C), 2018, 上海米袋融资有限公司
 * ProjectName: permission
 * FileName: PermissionUpStateEnum
 * Author:   屈志刚
 * Date:     2018/6/21 0021 14:03
 * Description: 权限更新类型
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package ones.quzhigang.permission.enums;

import lombok.Getter;

@Getter
public enum  PermissionUpStateEnum {

    DEPARTMENT(1,"部门"),
    USER(2,"用户"),
    PERMISSION_MODULE(3,"权限模块"),
    PERMISSION(4,"权限"),
    ROLE(5,"角色"),
    ROLE_AND_USER(6,"角色用户关系"),
    ROLE_AND_PERMISSION(7,"角色权限关系"),

    ;


    private int permissionCode;

    private String permissionName;

    PermissionUpStateEnum(int permissionCode, String permissionName) {
        this.permissionCode = permissionCode;
        this.permissionName = permissionName;
    }

    public PermissionUpStateEnum getPermissionUpStateEnumForPermissionCode(int permissionCode){

        PermissionUpStateEnum[] enums = values();

        for(PermissionUpStateEnum e: enums){
            if(e.getPermissionCode() == permissionCode){
                return e;
            }
        }

        return null;
    }
}
