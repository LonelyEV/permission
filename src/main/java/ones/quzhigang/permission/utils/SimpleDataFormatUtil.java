/**
 * Copyright (C), 2018, 上海米袋融资有限公司
 * ProjectName: permission
 * FileName: SimpleDataFormatUtil
 * Author:   屈志刚
 * Date:     2018/6/22 0022 10:12
 * Description: 日期格式化工具类
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package ones.quzhigang.permission.utils;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Slf4j
public class SimpleDataFormatUtil {

    public static final String DATE_SDF = "yyyy-MM-dd";

    public static final String yyyyMMdd = "yyyyMMdd";

    public static final String DATE_SDF_WZ = "yyyy年MM月dd日";

    public static final String TIME_SDF = "yyyy-MM-dd HH:mm";

    public static final String YYYYMMDDHHMMSS = "yyyyMMddHHmmss";

    public static final String SHORT_TIME_SDF = "HH:mm";

    public static final String DATE_TIME_FORMAT = "yyyy-MM-dd HH:mm:ss";

    public static final String DEFAULT_PATTERN = "default";




    /**
     * 功能描述: <br>
     * 〈锁对象〉
     *
     * @param null
     * @return:
     * @@throws:
     * @Version: 1.0.0
     * @Author: 屈志刚
     * @Date: 2018/6/22 0022 10:13
     */
    private static final Object lock =new Object();

    /**
     * 功能描述: <br>
     * 〈存放不同日期格式模板 MAP〉
     *
     * @param null
     * @return:
     * @@throws:
     * @Version: 1.0.0
     * @Author: 屈志刚
     * @Date: 2018/6/22 0022 10:14
     */
    private static Map<String, ThreadLocal<SimpleDateFormat>> stringThreadLocalMap = new HashMap<String, ThreadLocal<SimpleDateFormat>>();


    private static SimpleDateFormat getSimpleDateFormat(final String pattern){

        ThreadLocal<SimpleDateFormat> t1 = stringThreadLocalMap.get(pattern);

        if(t1 == null){
            synchronized(lock){
                t1 = stringThreadLocalMap.get(pattern);

                if(t1 == null){

                    log.info(" put new simpledateformat :{} to map", pattern);

                    t1 = new ThreadLocal<SimpleDateFormat>(){
                        @Override
                        protected SimpleDateFormat initialValue() {
                            return new SimpleDateFormat(pattern);
                        }
                    };
                    stringThreadLocalMap.put(pattern, t1);
                }

            }
        }

        return t1.get();

    }


    /**
     * 功能描述: <br>
     * 〈使用 Threadlocal<SimpleDateFormat> 来获取 SimpleDateFormat,保证每个线程只能有一个 SimpleDateFormat </>〉
     *
     * @param date
     * @param pattern
     * @return: java.lang.String
     * @@throws:
     * @Version: 1.0.0
     * @Author: 屈志刚
     * @Date: 2018/6/22 0022 10:25
     */
    public static String format(Date date, String pattern){


        if(StringUtils.isBlank(pattern) || DEFAULT_PATTERN.equals(pattern)){
            return getSimpleDateFormat(DATE_TIME_FORMAT).format(date);
        }

        return getSimpleDateFormat(pattern).format(date);
    }

    /**
     * 功能描述: <br>
     * 〈使用 Threadlocal<SimpleDateFormat> 来获取 SimpleDateFormat,保证每个线程只能有一个 SimpleDateFormat </>〉
     *
     * @param dateStr
     * @param pattern
     * @return: java.util.Date
     * @@throws:
     * @Version: 1.0.0
     * @Author: 屈志刚
     * @Date: 2018/6/22 0022 10:27
     */
    public static Date parse(String dateStr, String pattern) throws ParseException{

        if(StringUtils.isBlank(pattern) || DEFAULT_PATTERN.equals(pattern)){
            return getSimpleDateFormat(DATE_TIME_FORMAT).parse(dateStr);
        }

        return getSimpleDateFormat(pattern).parse(dateStr);

    }


}
