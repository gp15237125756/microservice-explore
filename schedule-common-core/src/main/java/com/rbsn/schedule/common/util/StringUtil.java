package com.rbsn.schedule.common.util;


import org.apache.commons.lang.StringUtils;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * String 工具类
 */
public class StringUtil {

    private static Pattern linePattern = Pattern.compile("_(\\w)");
    private static Pattern humpPattern = Pattern.compile("[A-Z]");

    /**
     * 下划线转驼峰
     *
     * @param str
     * @return
     */
    public static String lineToHump(String str) {
        if (null == str || "".equals(str)) {
            return str;
        }
        str = str.toLowerCase();
        Matcher matcher = linePattern.matcher(str);
        StringBuffer sb = new StringBuffer();
        while (matcher.find()) {
            matcher.appendReplacement(sb, matcher.group(1).toUpperCase());
        }
        matcher.appendTail(sb);

        str = sb.toString();
        str = str.substring(0, 1).toUpperCase() + str.substring(1);

        return str;
    }

    /**
     * 驼峰转下划线(简单写法，效率低于{@link #humpToLine2(String)})
     *
     * @param str
     * @return
     */
    public static String humpToLine(String str) {
        return str.replaceAll("[A-Z]", "_$0").toLowerCase();
    }

    /**
     * 驼峰转下划线,效率比上面高
     *
     * @param str
     * @return
     */
    public static String humpToLine2(String str) {
        Matcher matcher = humpPattern.matcher(str);
        StringBuffer sb = new StringBuffer();
        while (matcher.find()) {
            matcher.appendReplacement(sb, "_" + matcher.group(0).toLowerCase());
        }
        matcher.appendTail(sb);
        return sb.toString();
    }

    /**
     * 首字母转小写
     *
     * @param s
     * @return
     */
    public static String toLowerCaseFirstOne(String s) {
        if (StringUtils.isBlank(s)) {
            return s;
        }
        if (Character.isLowerCase(s.charAt(0))) {
            return s;
        } else {
            return (new StringBuilder()).append(Character.toLowerCase(s.charAt(0))).append(s.substring(1)).toString();
        }
    }

    /**
     * 首字母转大写
     *
     * @param s
     * @return
     */
    public static String toUpperCaseFirstOne(String s) {
        if (StringUtils.isBlank(s)) {
            return s;
        }
        if (Character.isUpperCase(s.charAt(0))) {
            return s;
        } else {
            return (new StringBuffer()).append(Character.toUpperCase(s.charAt(0))).append(s.substring(1)).toString();
        }
    }

    /**
     * object转String
     *
     * @param object
     * @return
     */
    public static String getString(Object object) {
        return getString(object, "");
    }

    public static String getString(Object object, String defaultValue) {
        if (null == object) {
            return defaultValue;
        }
        try {
            return object.toString();
        } catch (Exception e) {
            return defaultValue;
        }
    }

    /**
     * object转Integer
     *
     * @param object
     * @return
     */
    public static int getInt(Object object) {
        return getInt(object, -1);
    }

    public static int getInt(Object object, Integer defaultValue) {
        if (null == object) {
            return defaultValue;
        }
        try {
            return Integer.parseInt(object.toString());
        } catch (Exception e) {
            return defaultValue;
        }
    }

    /**
     * object转Boolean
     *
     * @param object
     * @return
     */
    public static boolean getBoolean(Object object) {
        return getBoolean(object, false);
    }

    public static boolean getBoolean(Object object, Boolean defaultValue) {
        if (null == object) {
            return defaultValue;
        }
        try {
            return Boolean.parseBoolean(object.toString());
        } catch (Exception e) {
            return defaultValue;
        }
    }

    /**
     * 判断字符串是否为空
     *
     * @param cs
     * @return
     */
    public static boolean isBlank(CharSequence cs) {
        int strLen;
        if (cs != null && (strLen = cs.length()) != 0) {
            for (int i = 0; i < strLen; ++i) {
                if (!Character.isWhitespace(cs.charAt(i))) {
                    return false;
                }
            }

            return true;
        } else {
            return true;
        }
    }

    /**
     * isEmpty:判断字符串是否为空. <br/>
     *
     * @param str
     * @return
     * @author qiyongkang
     * @since JDK 1.6
     */
    public static boolean isEmpty(String str) {
        if (str == null || str.length() == 0) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * subBySplit:根据分隔符截取字符串. <br/>
     *
     * @param split
     * @return
     * @author qiyongkang
     * @since JDK 1.6
     */
    public static String subBySplit(String str, String split) {
        String sub = null;
        if (!isEmpty(str) && str.lastIndexOf(split) > -1) {
            sub = str.substring(str.lastIndexOf(split) + 1);
        }

        return sub;
    }

    /**
     * upperFirst: 将字符串的首字母转换为大写. <br/>
     *
     * @param str 要转换的字符串
     * @return 转换之后的字符串
     * @author qiyongkang
     * @since JDK 1.6
     */
    public static String upperFirst(String str) {
        if (isEmpty(str)) {
            return null;
        }
        if (1 == str.length()) {
            return str.toUpperCase();
        }
        return str.substring(0, 1).toUpperCase() + str.substring(1);
    }

    /**
     * 将字符串拆分为list
     *
     * @param str
     * @param regex
     * @return
     */
    public static List<String> splitStr2List(String str, String regex) {
        List<String> list = new ArrayList<String>();
        String[] strs = str.split(regex);
        for (String s : strs) {
            list.add(s.trim());
        }
        return list;
    }

    /**
     * 将数据库字段名，转为属性名
     *
     * @param fieldName
     * @return
     */
    public static String convertFieldName2PropName(String fieldName) {
        String propName = "";
        String[] strs = fieldName.split("_");
        for (int i = 0; i < strs.length; i++) {
            if (i == 0) {
                propName = strs[0];
            } else {
                propName += strs[i].substring(0, 1).toUpperCase() + strs[i].substring(1);
            }
        }
        return propName;
    }

    /**
     * 生成随机序列值
     *
     * @return
     */
    public static String generate16LongNum() {
        Random ran = new Random();
        int a = ran.nextInt(99999999);
        int b = ran.nextInt(99999999);
        long l = a * 100000000L + b;
        String num = String.valueOf(l) + "L";
        return num;
    }

    /**
     * 字符串按照ASC码排序
     *
     * @param str
     * @return
     */
    public static String sortByAsc(String str) {
        char[] charArray = str.toCharArray();
        Arrays.sort(charArray);
        return new String(charArray);
    }

    /**
     * 生成8位随机字符，大小写英文
     */
    public static String randomString() {
        String[] chars = new String[]{
                "a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m",
                "n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z",
//                "0", "1", "2", "3", "4", "5", "6", "7", "8", "9",
                "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M",
                "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z"};
        StringBuilder stringBuilder = new StringBuilder();
        String uuid = UUID.randomUUID().toString().replace("-", "");
        for (int i = 0; i < 8; i++) {
            String str = uuid.substring(i * 4, i * 4 + 4);
            int x = Integer.parseInt(str, 16);
            stringBuilder.append(chars[x % chars.length]);
        }
        return stringBuilder.toString();
    }
}
