package com.chen.myssm.util;

/**
 * ClassName: StringUtil
 * Package: com.chen.myssm.util
 * Description:
 *
 * @Author: Night
 * @Create: 2023/9/27 - 14:34
 * @Version: 1.0
 */
public class StringUtil {
    public static boolean isEmpty(String var1){
        return (var1==null) || ("".equals(var1));
    }

    public static boolean isNotEmpty(String var1){
        return !isEmpty(var1);
    }
}
