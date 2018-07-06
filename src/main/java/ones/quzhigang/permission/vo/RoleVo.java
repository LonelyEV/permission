/**
 * Copyright (C), 2018, 上海米袋融资有限公司
 * ProjectName: permission
 * FileName: RoleVo
 * Author:   屈志刚
 * Date:     2018/7/6 0006 9:41
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
public class RoleVo {

    private Long id;

    @NotBlank(message = "角色名称不能为空")
    @Length(min = 2, max = 20, message = "角色名称字符长度只能在2-20之间")
    private String name;

    @Min(value = 1, message = "角色类型不合法")
    @Max(value = 2, message = "角色类型不合法")
    private Integer type = 1;

    @NotNull(message = "角色状态不能为空")
    @Min(value = 0, message = "角色状态不合法")
    @Max(value = 1, message = "角色状态不合法")
    private Integer status;

    @Length(min = 0, max = 200, message = "角色描述字符长度只能在0-200之间")
    private String remark;
}
