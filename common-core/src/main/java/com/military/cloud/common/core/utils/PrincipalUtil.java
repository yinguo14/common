package com.military.cloud.common.core.utils;

import cn.hutool.core.util.StrUtil;

import java.util.regex.Pattern;

/**
 * @author guo.yin
 * @date 2024/03/08 16:03
 **/

public class PrincipalUtil {
    public static final String MOBILE_REGEXP = "1[0-9]{10}";
    public static final String USER_NAME_REGEXP = "(?!\\d+$)([a-zA-Z0-9_]{4,32})";
    public static final String FIELD_REGEXP = "([a-zA-Z0-9_]+)";
    public static final String SIMPLE_CHAR_REGEXP = "([a-z0-9]+)";
    public static final String EMAIL_REGEXP = "[\\w!#$%&'*+/=?^_`{|}~-]+(?:\\.[\\w!#$%&'*+/=?^_`{|}~-]+)*@(?:[\\w](?:[\\w-]*[\\w])?\\.)+[\\w](?:[\\w-]*[\\w])?";
    public static final String HTTP_PROTOCOL_REGEXP = "^((http[s]{0,1})://)";
    public static final String UP_TO_TWO_DECIMAL = "^(-)?[1-9]\\d*$|^(-)?[1-9]\\d*\\.\\d\\d?$|^(-)?0\\.\\d\\d?$";
    public static final String WITHOUT_CHINESE = "^[A-Za-z0-9]+$";

    public PrincipalUtil() {
    }

    public static boolean isMobile(String value) {
        return isMatching("1[0-9]{10}", value);
    }

    public static boolean isUserName(String value) {
        return isMatching("(?!\\d+$)([a-zA-Z0-9_]{4,32})", value);
    }

    public static boolean isField(String value) {
        return !isMatching("([a-zA-Z0-9_]+)", value);
    }

    public static boolean isEmail(String value) {
        return isMatching("[\\w!#$%&'*+/=?^_`{|}~-]+(?:\\.[\\w!#$%&'*+/=?^_`{|}~-]+)*@(?:[\\w](?:[\\w-]*[\\w])?\\.)+[\\w](?:[\\w-]*[\\w])?", value);
    }

    public static boolean isSimpleChar(String value) {
        return isMatching("([a-z0-9]+)", value);
    }

    public static boolean isMaximumOfTwoDecimal(String value) {
        return isMatching("^(-)?[1-9]\\d*$|^(-)?[1-9]\\d*\\.\\d\\d?$|^(-)?0\\.\\d\\d?$", value);
    }

    public static boolean isHttpProtocol(String value) {
        return isFind("^((http[s]{0,1})://)", value);
    }

    public static boolean isMatching(String regexp, String value) {
        return StrUtil.isBlank(value) ? false : Pattern.matches(regexp, value);
    }

    public static boolean isFind(String regexp, String value) {
        if (StrUtil.isBlank(value)) {
            return false;
        } else {
            Pattern pattern = Pattern.compile(regexp);
            return pattern.matcher(value).find();
        }
    }
}