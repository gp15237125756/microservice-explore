package com.rbsn.schedule.common.util;

import com.rbsn.schedule.common.exception.CheckException;
import org.apache.commons.lang.StringUtils;

import java.util.regex.Pattern;

/**
 * 校验工具类
 *
 * @author Administrator
 * @date 2017/9/7
 */
public class CheckUtil {

    /**
     * 校验字符串是否为空，并抛出对应异常
     *
     * @param value
     * @param exceptionStr
     */
    public static void checkBlank(String value, String exceptionStr) {
        if (StringUtils.isBlank(value)) {
            throw new CheckException(exceptionStr);
        }
    }

    /**
     * 校验对象是否为空，并抛出对应异常
     *
     * @param value
     * @param exceptionStr
     */
    public static void checkNull(Object value, String exceptionStr) {
        if (value == null) {
            throw new CheckException(exceptionStr);
        }
    }


}
