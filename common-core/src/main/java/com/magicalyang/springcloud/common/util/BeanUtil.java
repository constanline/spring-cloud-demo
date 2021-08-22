package com.magicalyang.springcloud.common.util;

import org.springframework.beans.BeanUtils;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Supplier;

/**
 * @author Constanline
 */
public class BeanUtil {

    @FunctionalInterface
    public interface BeanCopyUtilCallBack <S, T> {

        /**
         * 定义默认回调方法
         * @param t 源
         * @param s 目标
         */
        void callBack(S t, T s);
    }

    /**
     * 数据的拷贝
     * @param sources: 数据源类
     * @param target: 目标类::new(eg: UserVO::new)
     * @return 拷贝结果
     */
    public static <S, T> T copyProperties(S sources, Supplier<T> target) {
        T t = target.get();
        if (sources instanceof HashMap) {
            t = copyProperties((HashMap<?, ?>) sources, target);
        } else {
            BeanUtils.copyProperties(sources, t);
        }
        return t;
    }

    public static <S, T> void copyProperties(S sources, T target) {
        if (sources instanceof HashMap) {
            copyProperties((HashMap<?, ?>) sources, target);
        } else {
            BeanUtils.copyProperties(sources, target);
        }
    }

    /**
     * 数据的拷贝
     * @param sources: 数据源类
     * @param target: 目标类::new(eg: UserVO::new)
     * @return 拷贝结果
     */
    public static <S extends HashMap<?, ?>, T> T copyProperties(S sources, Supplier<T> target) {
        T t = target.get();
        copyProperties(sources, t);
        return t;
    }

    public static <S extends HashMap<?, ?>, T> void copyProperties(S sources, T target) {
        Class<?> cls = target.getClass();
        try {
            for (Map.Entry<?, ?> entry : sources.entrySet()) {
                Field field = cls.getDeclaredField(entry.getKey().toString());
                field.setAccessible(true);
                field.set(target, entry.getValue());
            }
        } catch (Throwable ignored) {

        }
    }

    /**
     * 集合数据的拷贝
     * @param sources: 数据源类
     * @param target: 目标类::new(eg: UserVO::new)
     * @return 拷贝结果
     */
    public static <S, T> List<T> copyProperties(List<S> sources, Supplier<T> target) {
        return copyProperties(sources, target, null);
    }


    /**
     * 带回调函数的集合数据的拷贝（可自定义字段拷贝规则）
     * @param sources: 数据源类
     * @param target: 目标类::new(eg: UserVO::new)
     * @param callBack: 回调函数
     * @return 拷贝结果
     */
    public static <S, T> List<T> copyProperties(List<S> sources, Supplier<T> target, BeanCopyUtilCallBack<S, T> callBack) {
        List<T> list = new ArrayList<>(sources.size());
        for (S source : sources) {
            T t = target.get();
            BeanUtils.copyProperties(source, t);
            list.add(t);
            if (callBack != null) {
                // 回调
                callBack.callBack(source, t);
            }
        }
        return list;
    }
}
