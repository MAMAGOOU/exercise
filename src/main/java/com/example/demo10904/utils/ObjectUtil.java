package com.example.demo10904.utils;

import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.InvalidPropertiesFormatException;

/**
 * @author 19750
 * @version 1.0
 */
public class ObjectUtil {
    //            1. 当properties参数为空数组时，throw java.security.InvalidParameterException 异常。
//            2. source和target Object 可能是不同类型的class，但他们存在同名同类型的属性，就能进行拷贝。
//            3. 当properties参数中的属性，在source和target中同名但类型不一致时，throw RuntimeException 异常。
//            4. source Object的属性，如果是数组、对象等，需要进行深拷贝，即修改target的值不会同步修改source的值。
//            5. 只能使用 java.lang.reflect包下面的类或函数实现，不能使用第三方的库和函数，包括spring boot的。
//            6. 请补充各种异常情况和正常情况的测试代码，能处理循环引用等复杂情况者更佳。
    public static void copyProperties(Object source, Object target, String[] properties) throws IllegalAccessException {
        if (properties == null || properties.length == 0) {
            throw new InvalidParameterException("参数数组不能够为空");
        }
        for (String property : properties) {
            Field sourceField = getField(source.getClass(), property);
            Field targetField = getField(source.getClass(), property);

            if (sourceField == null || targetField == null) {
                throw new IllegalArgumentException("参数：" + property + "在source或者target中未找到");
            }
            if (!sourceField.getType().equals(targetField.getType())) {
                throw new RuntimeException("参数类型不匹配：" + sourceField.getType() + " vs " + targetField.getType());
            }
            sourceField.setAccessible(true);
            targetField.setAccessible(true);

            Object sourceValue = sourceField.get(source);
            if (sourceValue != null){
                sourceValue = deepCopy(sourceValue);
            }
            targetField.set(target,sourceValue);
        }
    }

    @SuppressWarnings("unchecked")
    private static <T> T deepCopy(T object)  throws IllegalAccessException{
        if (object instanceof Collection) {
            Collection<?> collection = (Collection<?>) object;
            Collection<Object> copiedCollection = new ArrayList<>(collection.size());
            for (Object item : collection) {
                copiedCollection.add(deepCopy(item));
            }
            return (T) copiedCollection;
        } else if (object.getClass().isArray()) {
            int length = Array.getLength(object);
            Object newArray = Array.newInstance(object.getClass().getComponentType(), length);
            for (int i = 0; i < length; i++) {
                Array.set(newArray, i, deepCopy(Array.get(object, i)));
            }
            return (T) newArray;
        } else if (object instanceof Object) {
            try {
                T newInstance = (T) object.getClass().newInstance();
                Field[] fields = object.getClass().getDeclaredFields();
                for (Field field : fields) {
                    field.setAccessible(true);
                    Object fieldValue = field.get(object);
                    if (fieldValue != null) {
                        field.set(newInstance, deepCopy(fieldValue));
                    }
                }
                return newInstance;
            } catch (InstantiationException e) {
                throw new RuntimeException("实例化类失败: " + object.getClass().getName(), e);
            }
        } else {
            return object;
        }
    }

    private static Field getField(Class<?> clazz,String fieldName){
        try {
            return clazz.getDeclaredField(fieldName);
        } catch (NoSuchFieldException e) {
            Class<?> superClass = clazz.getSuperclass();
            if (superClass != null) {
                return getField(superClass, fieldName);
            }
            return null;
        }
    }
}
