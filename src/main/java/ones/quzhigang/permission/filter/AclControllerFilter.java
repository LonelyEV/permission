/**
 * Copyright (C), 2018, 上海米袋融资有限公司
 * ProjectName: permission
 * FileName: AclControllerFilter
 * Author:   屈志刚
 * Date:     2018/7/9 0009 9:16
 * Description: 权限拦截器
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package ones.quzhigang.permission.filter;

import com.google.common.base.Splitter;
import com.google.common.collect.Sets;
import lombok.extern.slf4j.Slf4j;
import ones.quzhigang.permission.common.ApplicationContextHelper;
import ones.quzhigang.permission.common.JsonData;
import ones.quzhigang.permission.common.JsonMapper;
import ones.quzhigang.permission.common.RequestHolder;
import ones.quzhigang.permission.model.SysUserModel;
import ones.quzhigang.permission.service.SysCoreService;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Set;

@Slf4j
public class AclControllerFilter implements Filter{

    private static Set<String> exclusionUrlsSet = Sets.newHashSet();

    private static String noAuthUrl = "/sys/user/noAuth.page";

    /**
     * @see javax.servlet.Filter#init(javax.servlet.FilterConfig)
     */
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        String exclusionUrls = filterConfig.getInitParameter("exclusionUrls");
        List<String> exclusionUrlsList = Splitter.on(",").omitEmptyStrings().splitToList(exclusionUrls);
        exclusionUrlsSet = Sets.newConcurrentHashSet(exclusionUrlsList);
        exclusionUrlsSet.add(noAuthUrl);
    }

    /**
     * @see javax.servlet.Filter#doFilter(javax.servlet.ServletRequest, javax.servlet.ServletResponse, javax.servlet.FilterChain)
     */
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        String servletPath = request.getServletPath();

        if(exclusionUrlsSet.contains(servletPath)){
            filterChain.doFilter(servletRequest, servletResponse);
            return;
        }

        SysUserModel sysUserModel = RequestHolder.getCurrentUser();

        if(sysUserModel == null){
            log.info("url : {} ,  : {} ",servletPath, JsonMapper.obj2String(request.getParameterMap()));
            noAuth(request, response);
            return;
        }

        SysCoreService sysCoreService = ApplicationContextHelper.popBean(SysCoreService.class);

        if(!sysCoreService.hasUrlAcl(servletPath)){
            log.info("sysUser : {} , url : {} ,  : {} ",JsonMapper.obj2String(sysUserModel), servletPath, JsonMapper.obj2String(request.getParameterMap()));
            noAuth(request, response);
            return;
        }

        filterChain.doFilter(servletRequest, servletResponse);
        return;


    }

    private void noAuth(HttpServletRequest request, HttpServletResponse response) throws IOException{

        String servletPath = request.getServletPath();

        if(servletPath.endsWith(".json")){
            JsonData jsonData = JsonData.fail("无权限访问，请联系管理员");
            response.setHeader("Content-Type", "application/json");
            response.getWriter().print(JsonMapper.obj2String(jsonData));
        }else{
            clientRedirect(noAuthUrl,response);
            return;
        }


    }

    private void clientRedirect(String url, HttpServletResponse response) throws IOException{
        response.setHeader("Content-Type", "text/html");
        response.getWriter().print("<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Strict//EN\" \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd\">\n"
                + "<html xmlns=\"http://www.w3.org/1999/xhtml\">\n" + "<head>\n" + "<meta http-equiv=\"content-type\" content=\"text/html; charset=UTF-8\"/>\n"
                + "<title>跳转中...</title>\n" + "</head>\n" + "<body>\n" + "跳转中，请稍候...\n" + "<script type=\"text/javascript\">//<![CDATA[\n"
                + "window.location.href='" + url + "?ret='+encodeURIComponent(window.location.href);\n" + "//]]></script>\n" + "</body>\n" + "</html>\n");
    }

    /**
     * @see javax.servlet.Filter#destroy()
     */
    @Override
    public void destroy() {

    }
}
