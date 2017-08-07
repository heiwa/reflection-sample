package util;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * beanを扱うutilクラス
 */
public class BeanUtil {

    /**
     * 引数のbeanのプロパティを走査し、
     * プロパティ名をキー、プロパティ値をバリューとしてMapを生成する。
     * 値はgetter経由で取得する。
     *
     * @param bean Mapを生成するbean
     * @return beanから生成したMap
     */
    public static Map<String, Object> beanToMap(Object bean) {
        Map<String, Object> map = new HashMap<>();
        for (Field field : bean.getClass().getDeclaredFields()) {
            PropertyDescriptor pd;
            try {
                pd = new PropertyDescriptor(field.getName(), bean.getClass());
            } catch (IntrospectionException exception) {
                exception.printStackTrace();
                throw new IllegalArgumentException("bean must have getter and setter.");
            }
            Method getter = pd.getReadMethod();
            try {
                map.put(field.getName(), getter.invoke(bean));
            } catch (IllegalAccessException | InvocationTargetException exception) {
                exception.printStackTrace();
                throw new IllegalArgumentException("getter can not be invoked. property : " + field.getName());
            }
        }
        return map;
    }

}
