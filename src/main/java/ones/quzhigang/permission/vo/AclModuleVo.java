/**
 * Copyright (C), 2018, 上海米袋融资有限公司
 * ProjectName: permission
 * FileName: AclModuleVo
 * Author:   屈志刚
 * Date:     2018/7/5 0005 10:15
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
public class AclModuleVo {

    private Long id;

    @NotBlank(message = "权限模块名称不能为空")
    @Length(min = 2, max = 20, message = "权限模块名称长度只能在2至20以内")
    private String name;

    private Integer parentId = 0;

    @NotNull(message = "权限模块顺序不能为空")
    private Integer seq;

    @NotNull(message = "权限模块状态不能为空")
    @Min(value = 0,  message = "状态值不合法")
    @Max(value = 1,  message = "状态值不合法")
    private Integer status;

    @Length(max = 200, message = "权限模块描述长度只能在200以内")
    private String remark;
}
