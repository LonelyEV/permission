/**
 * Copyright (C), 2018, 上海米袋融资有限公司
 * ProjectName: permission
 * FileName: GlobalExceptionResolver
 * Author:   屈志刚
 * Date:     2018/6/21 0021 16:21
 * Description: 全局异常处理
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package ones.quzhigang.permission.common;

import lombok.extern.slf4j.Slf4j;
import ones.quzhigang.permission.exception.ParamException;
import ones.quzhigang.permission.exception.PermissionException;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Slf4j
public class GlobalExceptionResolver implements HandlerExceptionResolver {
    @Override
    public ModelAndView resolveException(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) {

        String requestUrl = httpServletRequest.getRequestURI().toString();

        ModelAndView modelAndView;
        JsonData result;
        String defaultMessage = "系统异常！请联系管理员！";

        // json数据请求，以 .json结尾
        if(requestUrl.endsWith(".json")){
            if(e instanceof PermissionException || e instanceof ParamException){
                result = JsonData.fail(e.getMessage());
                modelAndView = new ModelAndView("jsonView", result.toMap());
            }else{
                log.error(" 未知【json请求】异常，URL:"+requestUrl, e.getMessage());
                result = JsonData.fail(defaultMessage);
                modelAndView = new ModelAndView("jsonView", result.toMap());
            }

            // page请求， 以 .page结尾
        }else if(requestUrl.endsWith(".page")){
            log.error(" 未知【page请求】异常，URL:"+requestUrl, e.getMessage());
            result = JsonData.fail(defaultMessage);
            modelAndView = new ModelAndView("exception", result.toMap());
        }else{
            log.error(" 未知异常，URL:"+requestUrl, e.getMessage());
            result = JsonData.fail(defaultMessage);
            modelAndView = new ModelAndView("jsonView", result.toMap());
        }


        return modelAndView;
    }
}
