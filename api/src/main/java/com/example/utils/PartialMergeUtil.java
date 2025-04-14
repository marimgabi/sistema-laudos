package com.example.utils;

import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.stereotype.Component;

import java.beans.PropertyDescriptor;
import java.util.Map;

public class PartialMergeUtil {


    public static <T> void merge(T source, T target){
        merge(source, target, 2);
    }


    public static <T> void merge(T source, T target, int depth){
        if(source == null || target == null || depth < 0) return;


        BeanWrapper sourceWrapper = new BeanWrapperImpl(source);
        BeanWrapper targetWrapper = new BeanWrapperImpl(target);


        for(PropertyDescriptor pd : sourceWrapper.getPropertyDescriptors()){
            String propertyName = pd.getName();


            if("class".equals(propertyName)) continue;


            Object sourceValue = sourceWrapper.getPropertyValue(propertyName);
            Object targetValue = targetWrapper.getPropertyValue(propertyName);


            if(targetValue == null && sourceValue != null){
                targetWrapper.setPropertyValue(propertyName, sourceValue);
            } else if(sourceValue != null && targetValue != null && isEntity(sourceValue.getClass())){
                merge(sourceValue, targetValue, depth - 1);
            }
        }
    }


    private static boolean isEntity(Class<?> clazz){
        return !clazz.isPrimitive()
                && !clazz.isEnum()
                && !clazz.getName().startsWith("java.")
                && !clazz.getName().startsWith("javax.")
                && !Iterable.class.isAssignableFrom(clazz)
                && !Map.class.isAssignableFrom(clazz);
    }
}

