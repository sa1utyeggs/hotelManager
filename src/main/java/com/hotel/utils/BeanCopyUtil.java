package com.hotel.utils;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

/**
 * 复制对象或集合属性
 *
 * @author ab875
 */
public class BeanCopyUtil {

    /**
     * 复制对象
     *
     * @param source 源
     * @param target 目标
     * @return {@link T}
     */
    public static <T> T copyObject(Object source, Class<T> target) {
        T temp = null;
        try {
            temp = target.newInstance();
            if (null != source) {
                org.springframework.beans.BeanUtils.copyProperties(source, temp);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return temp;
    }

    /**
     * 拷贝集合
     *
     * @param source 源
     * @param target 目标
     * @return {@link List<T>} 集合
     */
    public static <T, S> List<T> copyList(List<S> source, Class<T> target) {
        List<T> list = new ArrayList<>();
        if (null != source && source.size() > 0) {
            for (Object obj : source) {
                list.add(BeanCopyUtil.copyObject(obj, target));
            }
        }
        return list;
    }

    public static <T> T copyFromMap(Map<String, Object> map, Class<T> target) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        T instance = target.getConstructor().newInstance();
        org.apache.commons.beanutils.BeanUtils.populate(instance, map);
        return instance;
    }

    public static <C, O> Map<C, O> listToMapWithPrimaryKey(List<O> list, Function<O, C> column) {
        HashMap<C, O> map = new HashMap<>(list.size());
        list.forEach(r -> {
            map.put(column.apply(r), r);
        });
        return map;
    }


}
