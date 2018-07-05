/**
 * Copyright (C), 2018, 上海米袋融资有限公司
 * ProjectName: permission
 * FileName: LoginFilter
 * Author:   屈志刚
 * Date:     2018/7/5 0005 9:24
 * Description: 用户登录过滤器
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package ones.quzhigang.permission.filter;

import lombok.extern.slf4j.Slf4j;
import ones.quzhigang.permission.common.RequestHolder;
import ones.quzhigang.permission.model.SysUserModel;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
public class LoginFilter implements Filter{
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        SysUserModel SysUserModel = (SysUserModel)request.getSession().getAttribute("user");
        if(SysUserModel == null){
            String path = "/signin.jsp";
            response.sendRedirect(path);
            return;
        }

        RequestHolder.add(SysUserModel);
        RequestHolder.add(request);
        filterChain.doFilter(request, response);
        return;

    }

    @Override
    public void destroy() {

    }
}
