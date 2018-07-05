/**
 * Copyright (C), 2018, 上海米袋融资有限公司
 * ProjectName: permission
 * FileName: HttpInterceptor
 * Author:   屈志刚
 * Date:     2018/6/21 0021 23:25
 * Description: HTTP请求前后监听工具
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package ones.quzhigang.permission.interceptor;

import lombok.extern.slf4j.Slf4j;
import ones.quzhigang.permission.common.JsonMapper;
import ones.quzhigang.permission.common.RequestHolder;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

@Slf4j
public class HttpInterceptor extends HandlerInterceptorAdapter {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        String requestUrl = request.getRequestURI();
        Map parameterMap = request.getParameterMap();
        log.info("request start url:{}, params:{}", requestUrl, JsonMapper.obj2String(parameterMap));

        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        String requestUrl = request.getRequestURI();
        Map parameterMap = request.getParameterMap();
        log.info("request finished url:{}, params:{}", requestUrl, JsonMapper.obj2String(parameterMap));
        removeThreadlocalhandle();
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        String requestUrl = request.getRequestURI();
        Map parameterMap = request.getParameterMap();
        log.info("request complete url:{}, params:{}", requestUrl, JsonMapper.obj2String(parameterMap));
        removeThreadlocalhandle();

    }

    private void removeThreadlocalhandle(){
        RequestHolder.remove();
    }

}
