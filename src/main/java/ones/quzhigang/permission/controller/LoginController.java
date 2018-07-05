/**
 * Copyright (C), 2018, 上海米袋融资有限公司
 * ProjectName: permission
 * FileName: LoginController
 * Author:   屈志刚
 * Date:     2018/7/4 0004 9:21
 * Description: 用户登录处理器
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package ones.quzhigang.permission.controller;


import lombok.extern.slf4j.Slf4j;
import ones.quzhigang.permission.mapper.SysUserMapper;
import ones.quzhigang.permission.model.SysUserModel;
import ones.quzhigang.permission.utils.MD5Util;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
@Slf4j
public class LoginController {

    @Autowired
    private SysUserMapper SysUserMapper;


    @RequestMapping("login.page")
    public void login(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        String username = request.getParameter("username");
        String password = request.getParameter("password");

        SysUserModel SysUserModel = SysUserMapper.findByKeyWords(username);

        String errorMsg = "";
        String ret = request.getParameter("ret");

        if(StringUtils.isBlank(username)){
            errorMsg = "用户名不能为空";
        }else if(SysUserModel == null){
            errorMsg = "查询不到指定用户";
        } else if(!SysUserModel.getPassword().equals(MD5Util.encrypt(password))){
            errorMsg = "用户名或密码错误";
        }else if(SysUserModel.getStatus() != 1){
            errorMsg = "用户已被冻结，请联系管理员";
        }else{
            request.getSession().setAttribute("user", SysUserModel);
            if(StringUtils.isNotBlank(ret)){
                response.sendRedirect(ret);
            }else{
                response.sendRedirect("/index.page");
            }
        }

        request.setAttribute("errorMsg", errorMsg);
        request.setAttribute("userName", username);
        if(StringUtils.isNotBlank(ret)){
            request.setAttribute("'ret", ret);
        }

        String path = "/signin.jsp";
        request.getRequestDispatcher(path).forward(request, response);

    }

    @RequestMapping("/index.page")
    public ModelAndView index() {
        return new ModelAndView("admin");
    }

    @RequestMapping("/logout.page")
    public void logout(HttpServletRequest request, HttpServletResponse response) throws IOException{
        request.getSession().invalidate();
        String path = "signin.jsp";
        response.sendRedirect(path);

    }
}
