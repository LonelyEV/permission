/**
 * Copyright (C), 2018, 上海米袋融资有限公司
 * ProjectName: permission
 * FileName: RoleEnum
 * Author:   屈志刚
 * Date:     2018/6/21 0021 14:18
 * Description: 角色类型枚举
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package ones.quzhigang.permission.enums;

import lombok.Getter;

@Getter
public enum RoleEnum {

    ADMIN_ROLE(1, "管理员角色"),
    OTHER_ROLE(2, "其他角色")
    ;

    private int typeId;

    private String name;

    RoleEnum(int typeId, String name) {
        this.typeId = typeId;
        this.name = name;
    }

    public RoleEnum getRoleEnumForTypeId(int typeId){

        RoleEnum[] enums = values();

        for(RoleEnum e: enums){
            if(e.getTypeId() == typeId){
                return e;
            }
        }
        return null;
    }
}
