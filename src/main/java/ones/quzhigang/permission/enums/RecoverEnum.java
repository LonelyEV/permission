/**
 * Copyright (C), 2018, 上海米袋融资有限公司
 * ProjectName: permission
 * FileName: RecoverEnum
 * Author:   屈志刚
 * Date:     2018/6/21 0021 14:13
 * Description: 复原状态枚举
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package ones.quzhigang.permission.enums;

import lombok.Getter;

@Getter
public enum  RecoverEnum {

    UN_RECOVER(0, "未复原"),
    RECOVER(1, "复原")
    ;

    private int recoverCode;

    private String recoverName;

    RecoverEnum(int recoverCode, String recoverName) {
        this.recoverCode = recoverCode;
        this.recoverName = recoverName;
    }

    public RecoverEnum getRecoverEnumForRecoverCode(int recoverCode){

        RecoverEnum[] enums = values();

        for (RecoverEnum e: enums){
            if(e.getRecoverCode() == recoverCode){
                return e;
            }
        }
        return null;
    }
}
