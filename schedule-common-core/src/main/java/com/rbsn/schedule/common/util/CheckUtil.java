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
     * 正则表达式：验证用户名
     * 只包含英文字母、数字及下划线，来头必须为字母
     * 长度最小6最大20
     */
    public static final String REGEX_USERNAME = "^[a-zA-Z]\\w{5,19}$";

    /**
     * 正则表达式：验证真实姓名
     * 只包含中英文字母、数字及下划线，来头必须为字母
     * 长度最大20
     */
    public static final String REGEX_REALNAME = "^[a-zA-Z0-9\u4e00-\u9fa5\\s+]{1,10}$";


    /**
     * 正则表达式：验证密码
     * 只包含英文字母、数字及以下特殊符号~!@#$%^&*()_+|<>,.?/:;'[]{}"）
     * 长度最小8最大20
     */
    public static final String REGEX_PASSWORD = "^[a-zA-Z0-9~!@#$%^&*()_+|<>,.?/:;'\\[\\]{}\"]{8,20}$";

    /**
     * 正则表达式：验证手机号
     */
    public static final String REGEX_MOBILE = "^\\d{1,20}$";

    /**
     * 正则表达式：验证手机后6位作为密码格式
     */
    public static final String REGEX_PHONE_SUFFIX_PASSWORD = "^\\d{6}$";

    /**
     * 正则表达式：验证邮箱
     */
    public static final String REGEX_EMAIL = "^([a-z0-9A-Z]+[-|_|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";

    /**
     * 正则表达式：验证汉字
     */
    public static final String REGEX_CHINESE = "^[\u4e00-\u9fa5],{0,}$";

    /**
     * 正则表达式：验证身份证
     */
    public static final String REGEX_ID_CARD = "^\\d{17}([\\d]|[xX])$";

    /**
     * 正则表达式：验证IP地址
     */
    public static final String REGEX_IP_ADDR = "(25[0-5]|2[0-4]\\d|[0-1]\\d{2}|[1-9]?\\d)";

    /**
     * 正则表达式：验证持卡人
     * 只包含中英文、数字、空格
     * 长度最大10
     */
    public static final String REGEX_USER_BANK_CARD_NAME = "^[a-zA-Z0-9\u4e00-\u9fa5\\s+]{1,10}$";

    /**
     * 正则表达式：银行卡号
     * 只包含数字
     * 长度最小13 最大28
     */
    public static final String REGEX_USER_BANK_CARD_NUMBER = "^[\\d]{13,28}$";
    /**
     * 正则表达式：提现密码
     * 8位数字，英文，不支持特殊字符
     */
    public static final String REGEX_WITHDRAWL_PASSWORD = "^[a-zA-Z\\d]{8}$";
    /**
     * 正则表达式：价格
     * 整数最长8位数字，小数最长2位
     */
    public static final String REGEX_GOODS_PRICE = "^0[\\.][1-9]|0[\\.][1-9][0-9]|0[\\.][0][1-9]|([1-9][\\d]{0,7})([\\.][0-9]{1,2})?$";

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

    /**
     * 校验用户名，并抛出对应异常
     *
     * @param username
     * @return 校验通过返回true，否则返回false
     */
    public static void checkUsername(String username, String exceptionStr) {
        if (!Pattern.matches(REGEX_USERNAME, username)) {
            throw new CheckException(exceptionStr);
        }
    }

    /**
     * 校验真实姓名，并抛出对应异常
     *
     * @param realName
     * @return 校验通过返回true，否则返回false
     */
    public static void checkRealname(String realName, String exceptionStr) {
        if (!Pattern.matches(REGEX_REALNAME, realName)) {
            throw new CheckException(exceptionStr);
        }
    }

    /**
     * 校验密码，并抛出对应异常
     * 只包含英文字母、数字及以下特殊符号~!@#$%^&*()_+|<>,.?/:;'[]{}"
     *
     * @param password
     * @return 校验通过返回true，否则返回false
     */
    public static void checkPassword(String password, String exceptionStr) {
        if (!Pattern.matches(REGEX_PASSWORD, password)) {
            throw new CheckException(exceptionStr);
        }
    }

    /**
     * 校验密码组合，并抛出对应异常
     * 至少包含英文字母、数字及特殊符号中的两种
     *
     * @param password
     * @return 校验通过返回true，否则返回false
     */
    public static void checkPasswordGroup(String password, String exceptionStr) {
        //是否含有字母
        boolean letterFlag = Pattern.matches(".*[a-zA-Z]+.*", password);
        //是否含有数字
        boolean numberFlag = Pattern.matches(".*\\d+.*", password);
        //是否含有特殊字符
        boolean symbolFlag = Pattern.matches(".*[~!@#$%^&*()_+|<>,.?/:;'\\[\\]{}\"]+.*", password);

        //判断密码中是否只有一种类型的字符
        if ((letterFlag && !numberFlag && !symbolFlag)
                || (!letterFlag && numberFlag && !symbolFlag)
                || (!letterFlag && !numberFlag && symbolFlag)) {
            throw new CheckException(exceptionStr);
        }
    }

    /**
     * 校验手机号，并抛出对应异常
     *
     * @param phone
     * @return 校验通过返回true，否则返回false
     */
    public static void checkPhone(String phone, String exceptionStr) {
        if (!Pattern.matches(REGEX_MOBILE, phone)) {
            throw new CheckException(exceptionStr);
        }
    }

    /**
     * 校验邮箱，并抛出对应异常
     *
     * @param email
     * @return 校验通过返回true，否则返回false
     */
    public static void checkEmail(String email, String exceptionStr) {
        if (!Pattern.matches(REGEX_EMAIL, email)) {
            throw new CheckException(exceptionStr);
        }
    }

    /**
     * 校验汉字，并抛出对应异常
     *
     * @param chinese
     * @return 校验通过返回true，否则返回false
     */
    public static void checkChinese(String chinese, String exceptionStr) {
        if (!Pattern.matches(REGEX_CHINESE, chinese)) {
            throw new CheckException(exceptionStr);
        }
    }

    /**
     * 校验身份证，并抛出对应异常
     *
     * @param idCard
     * @return 校验通过返回true，否则返回false
     */
    public static void checkIDCard(String idCard, String exceptionStr) {
        if (!Pattern.matches(REGEX_ID_CARD, idCard)) {
            throw new CheckException(exceptionStr);
        }
    }

    /**
     * 校验IP地址，并抛出对应异常
     *
     * @param ip
     * @return
     */
    public static void checkIP(String ip, String exceptionStr) {
        if (!Pattern.matches(REGEX_IP_ADDR, ip)) {
            throw new CheckException(exceptionStr);
        }
    }

    /**
     * 校验银行卡持卡人，并抛出对应异常
     *
     * @param name
     * @return 校验通过返回true，否则返回false
     */
    public static void checkUserBankCardName(String name, String exceptionStr) {
        if (!Pattern.matches(REGEX_USER_BANK_CARD_NAME, name)) {
            throw new CheckException(exceptionStr);
        }
    }

    /**
     * 校验银行卡号，并抛出对应异常
     *
     * @param cardNumber
     * @return 校验通过返回true，否则返回false
     */
    public static void checkUserBankCardNumber(String cardNumber, String exceptionStr) {
        if (!Pattern.matches(REGEX_USER_BANK_CARD_NUMBER, cardNumber)) {
            throw new CheckException(exceptionStr);
        }
    }

    /**
     * 校验提现密码，并抛出对应异常
     *
     * @param withdrawlPassword
     * @return 校验通过返回true，否则返回false
     */
    public static void checkWithdrawlPassword(String withdrawlPassword, String exceptionStr) {
        if (!Pattern.matches(REGEX_WITHDRAWL_PASSWORD, withdrawlPassword)) {
            throw new CheckException(exceptionStr);
        }
    }

    /**
     * 校验手机号后6位初始密码，并抛出对应异常
     *
     * @param password
     * @return 校验通过返回true，否则返回false
     */
    public static void checkPhoneSuffixPassword(String password, String exceptionStr) {
        if (!Pattern.matches(REGEX_PHONE_SUFFIX_PASSWORD, password)) {
            throw new CheckException(exceptionStr);
        }
    }

    /**
     * 验证中文数字及空格
     *
     * @param value
     * @param exceptionStr
     */
    public static void checkChineseNumberSpace(String value, String exceptionStr) {
        String regex = "^[\\u4e00-\\u9fa5\\d\\s]+$";
        if (!Pattern.matches(regex, value)) {
            throw new CheckException(exceptionStr);
        }
    }

    /**
     * 验证非特殊字符
     *
     * @param value
     * @param exceptionStr
     */
    public static void checkNotSpecial(String value, String exceptionStr) {
        String regex = "^[\\u4e00-\\u9fa5\\w\\s]+$";
        if (!Pattern.matches(regex, value)) {
            throw new CheckException(exceptionStr);
        }
    }

    /**
     * 校验商品价格
     *
     * @param value
     * @return 校验通过返回true，否则返回false
     */
    public static void checkGoodsPrice(String value, String exceptionStr) {
        if (!Pattern.matches(REGEX_GOODS_PRICE, value)) {
            throw new CheckException(exceptionStr);
        }
    }

}
