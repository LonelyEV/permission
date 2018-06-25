/**
 * Copyright (C), 2018, 上海米袋融资有限公司
 * ProjectName: permission
 * FileName: BeanValidator
 * Author:   屈志刚
 * Date:     2018/6/21 0021 17:14
 * Description: 统一参数效验工具
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package ones.quzhigang.permission.common;


import com.google.common.base.Preconditions;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import ones.quzhigang.permission.exception.ParamException;
import org.apache.commons.collections.MapUtils;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.ValidatorFactory;
import javax.validation.Validator;
import java.util.*;

public class BeanValidator {

    private static ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();

    public static <T> Map<String, String> validate(T t, Class... group){

        Validator validator = validatorFactory.getValidator();

        Set validateResult = validator.validate(t, group);

        if(validateResult.isEmpty()){
            return Collections.emptyMap();
        }else{
            LinkedHashMap errors = Maps.newLinkedHashMap();
            Iterator iterator = validateResult.iterator();

            while (iterator.hasNext()){
                ConstraintViolation violation = (ConstraintViolation) iterator.next();
                errors.put(violation.getPropertyPath().toString(), violation.getMessage());

            }
            return errors;
        }
    }

    public static Map<String, String> validateList(Collection<?> collection){

        Preconditions.checkNotNull(collection);
        Iterator iterator = collection.iterator();

        Map<String, String> errors;

        do{
            if(!iterator.hasNext()){
                return Collections.emptyMap();
            }
            Object object = iterator.next();
            errors = validate(object, new Class[0]);

        }while(errors.isEmpty());

        return errors;

    }

    public static Map<String, String> validateObject(Object first, Object... objects){
        if(objects != null && objects.length > 0){
            return validateList(Lists.asList(first, objects));
        }else{
            return validate(first, new Class[0]);
        }
    }

    public static void check(Object param){
        Map<String, String> result = validateObject(param);
        if(MapUtils.isNotEmpty(result)){
            throw new ParamException(result.toString());
        }

    }
}
