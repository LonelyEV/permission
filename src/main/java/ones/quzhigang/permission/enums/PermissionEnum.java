/**
 * Copyright (C), 2018, 上海米袋融资有限公司
 * ProjectName: permission
 * FileName: PermissionEnum
 * Author:   屈志刚
 * Date:     2018/6/21 0021 13:57
 * Description: 权限表有效状态
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package ones.quzhigang.permission.enums;

import lombok.Getter;

@Getter
public enum  PermissionEnum {

    NORMAL(1, "正常"),
    FREEZE(2, "冻结")
    ;

    private int stateCode;

    private String stateName;

    PermissionEnum(int stateCode, String stateName) {
        this.stateCode = stateCode;
        this.stateName = stateName;
    }

    public PermissionEnum getPermissionEnumForStateCode(int stateCode){

        PermissionEnum[] enums = values();

        for (PermissionEnum e : enums){
            if(e.getStateCode() == stateCode){
                return e;
            }
        }
        return null;
    }
}
