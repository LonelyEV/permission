/**
 * Copyright (C), 2018, 上海米袋融资有限公司
 * ProjectName: permission
 * FileName: ApplicationContextHelper
 * Author:   屈志刚
 * Date:     2018/6/21 0021 23:15
 * Description: spring上下文工具类
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package ones.quzhigang.permission.common;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

@Component("applicationContextHelper")
public class ApplicationContextHelper implements ApplicationContextAware {

    private static ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext context) throws BeansException {
        applicationContext = context;
    }

    public static <T> T popBean(Class<T> clazz){
        if(applicationContext == null){
            return null;
        }

        return applicationContext.getBean(clazz);

    }

    public static <T> T popBean(String name, Class<T> clazz){

        if(applicationContext == null){
            return null;
        }

        return applicationContext.getBean(name, clazz);

    }
}
