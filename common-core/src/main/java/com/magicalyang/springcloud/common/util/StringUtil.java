package com.magicalyang.springcloud.common.util;

import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * The type String util.
 *
 * @author Constanline
 */
public class StringUtil {
    /**
     * The constant UNDERLINE.
     */
    public static final char UNDERLINE = '_';
    /**
     * The Random char pool.
     */
    final static char[] RANDOM_CHAR_POOL = "abcdefghijklmnopqrstuvwxyz1234567890ABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray();
    /**
     * The Random num pool.
     */
    final static char[] RANDOM_NUM_POOL = "123456789".toCharArray();
    /**
     * The constant RANDOM.
     */
    final static Random RANDOM = new Random();

    /**
     * Gets random num.
     *
     * @param length the length
     * @return the random num
     */
    public static String getRandomNum(int length) {
        if (length < 1) {
            return null;
        }
        int poolLen = RANDOM_NUM_POOL.length;
        StringBuilder sb = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            sb.append(RANDOM_NUM_POOL[RANDOM.nextInt(poolLen)]);
        }
        return sb.toString();
    }

    /**
     * Gets random str.
     *
     * @param length the length
     * @return the random str
     */
    public static String getRandomStr(int length) {
        if (length < 1) {
            length = 6;
        }
        int poolLen = RANDOM_CHAR_POOL.length;
        StringBuilder sb = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            sb.append(RANDOM_CHAR_POOL[RANDOM.nextInt(poolLen)]);
        }
        return sb.toString();
    }

    /**
     * 将驼峰转换成"_"(userId:user_id)
     *
     * @param param       要转化的参数
     * @param toUpperCase the to upper case
     * @return 转换结果 string
     */
    public static String camelToUnderline(String param, boolean toUpperCase){
        if (param == null || "".equals(param.trim())){
            return "";
        }

        int len = param.length();
        StringBuilder sb = new StringBuilder(len);
        for (int i = 0; i < len; i++) {
            char c = param.charAt(i);
            if (Character.isUpperCase(c)){
                sb.append(UNDERLINE);
            }
            sb.append(toUpperCase ? Character.toUpperCase(c) : Character.toLowerCase(c));
        }
        return sb.toString();
    }

    /**
     * 将驼峰转换成"_"(userId:user_id)
     *
     * @param param 要转化的参数
     * @return 转换结果 string
     */
    public static String camelToUnderline(String param){
        return camelToUnderline(param, false);
    }

    /**
     * 将"_"转成驼峰(user_id:userId)
     *
     * @param param 要转化的参数
     * @return 转换结果 string
     */
    public static String underlineToCamel(String param){
        if (param == null || "".equals(param.trim())){
            return "";
        }

        StringBuilder sb = new StringBuilder(param);
        Matcher mc = Pattern.compile(UNDERLINE + "").matcher(param);
        int i = 0;
        while (mc.find()){
            int position = mc.end() - ++i;
            sb.replace(position - 1,position + 1, sb.substring(position, position + 1).toUpperCase());
        }
        return sb.toString();

    }
}
