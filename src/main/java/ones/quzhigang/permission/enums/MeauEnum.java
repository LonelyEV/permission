/**
 * Copyright (C), 2018, 上海米袋融资有限公司
 * ProjectName: permission
 * FileName: MeauEnum
 * Author:   屈志刚
 * Date:     2018/6/21 0021 13:40
 * Description: 权限类型枚举
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package ones.quzhigang.permission.enums;

import lombok.Getter;

@Getter
public enum MeauEnum {

    MEAU(1, "菜单"),
        BUTTON(2, "按钮"),
            OTHER(3, "其他");


    private int meauId;

    private String meauName;

    MeauEnum(int meauId, String meauName) {
        this.meauId = meauId;
        this.meauName = meauName;
    }

    public MeauEnum getMeauEnumForMeauId(int meauId){
        MeauEnum[] enums = values();

        for(MeauEnum e : enums ){
            if(e.getMeauId() == meauId){
                return e;
            }
        }
        return null;
    }
}
