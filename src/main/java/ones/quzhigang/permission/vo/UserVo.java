/**
 * Copyright (C), 2018, 上海米袋融资有限公司
 * ProjectName: permission
 * FileName: UserVo
 * Author:   屈志刚
 * Date:     2018/7/3 0003 17:06
 * Description: 用户VO类
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package ones.quzhigang.permission.vo;


import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class UserVo {


    private Long id;

    @NotBlank(message = "用户名不能为空")
    @Length(min = 1, max = 20, message = "用户名长度在20个字符以内")
    private String username;

    @NotBlank(message = "电话号码不能为空")
    @Length(min = 1, max = 13, message = "电话号码长度在13个字符以内")
    private String telephone;

    @NotBlank(message = "邮箱地址不能为空")
    @Email(message = "格式必须为正确的邮箱格式 ： XXX.@XXX.com")
    private String mail;

    @NotNull(message = "部门ID不能为空")
    private Integer deptId;

    @NotNull(message = "必须指定用户状态")
    @Min(value = 0, message = "用户状态不合法")
    @Max(value = 2, message = "用户状态不合法")
    private Integer status;

    @Length(max = 200, message = "备注长度不能超过200个字符")
    private String remark = "";
}
