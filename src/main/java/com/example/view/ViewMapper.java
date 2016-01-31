package com.example.view;

import com.example.view.annotation.Entity;
import com.example.view.annotation.View;
import org.springframework.util.ClassUtils;
import org.springframework.util.CollectionUtils;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Created by tom on 31/01/2016.
 */
public class ViewMapper<T> {

    public T map(T source, String viewName) {
        Class clazz = source.getClass();

        // Not an entity, return 'as is'
        if (!isEntity(clazz)) return source;

        T target = null;
        try {
            target = (T) clazz.newInstance();
            for (Field field : clazz.getDeclaredFields()) {
                field.setAccessible(true);
                View[] views = field.getDeclaredAnnotationsByType(View.class);
                if (views != null) {
                    for (View view : views) {
                        if (viewName.equals(view.value())) {
                            if (field.getType().isAssignableFrom(List.class)) {
                                List<?> sourceList = (List) field.get(source);
                                if (!CollectionUtils.isEmpty(sourceList)) {
                                    field.set(target, sourceList
                                            .stream()
                                            .map(i -> getFieldValue(i, viewName))
                                            .collect(Collectors.toList())
                                    );

                                }
                                // TODO
                                System.out.println(field.getGenericType().getTypeName());
                            } else {
                                field.set(target, getFieldValue(field, source, viewName));
                            }
                        }
                    }
                }
            }
        } catch (InstantiationException | IllegalAccessException e) {
            // TODO log it?
        } finally {
            return target;
        }
    }

    private Object getFieldValue(Field field, Object source, String viewName) {
        Object fieldValue = null;
        try {
            fieldValue = field.get(source);
        } catch (IllegalAccessException e) {
            return null;
        }
        return getFieldValue(fieldValue, viewName);
    }

    private Object getFieldValue(Object source, String viewName) {
        if (source == null) return null;
        Class<?> type = source.getClass();
        Object result = null;
        if (isEntity(type)) {
            result = new ViewMapper<>().map(source, viewName);
        } else if (ClassUtils.isPrimitiveOrWrapper(type) || type.equals(String.class)) {
            result = source;
        }
        return result;
    }

    private boolean isEntity(Class<?> type) {
        return type.getDeclaredAnnotation(Entity.class) != null;
    }
}
