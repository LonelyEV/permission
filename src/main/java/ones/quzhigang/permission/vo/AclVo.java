/**
 * Copyright (C), 2018, 上海米袋融资有限公司
 * ProjectName: permission
 * FileName: AclVo
 * Author:   屈志刚
 * Date:     2018/7/5 0005 15:14
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package ones.quzhigang.permission.vo;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@ToString
public class AclVo {

    private Long id;

    @NotBlank(message = "权限点名称不能为空")
    @Length(min = 2, max = 64, message = "权限点名称长度必须在2至64之间")
    private String name;

    @NotNull(message = "必须指定权限模块")
    private Integer aclModuleId;

    @Length(min = 6, max = 256, message = "权限点URL长度需要在6-256个字符之间")
    private String url;


    @NotNull(message = "权限类型不能为空")
    @Min(value = 0, message = "权限类型不合法")
    @Max(value = 3, message = "权限类型不合法")
    private Integer type;

    @NotNull(message = "权限状态不能为空")
    @Min(value = 0, message = "权限状态不合法")
    @Max(value = 1, message = "权限状态不合法")
    private Integer status;

    @NotNull(message = "权限序列不能为空")
    private Integer seq;

    @Length(min = 0, max = 200, message = "权限点备注文本长度只能在0-200之间")
    private String remark;
}
