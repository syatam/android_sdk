package base;

import com.adjust.sdk.*;

import java.lang.reflect.*;

/**
 * Created by abdullah on 7/28/16.
 */
public class TestableClass {

    public <T> T getPrivateField(Object object, String fieldName) {
        Field field = null;
        try {
            field = ActivityHandler.class.getDeclaredField(fieldName);
            field.setAccessible(true);
            return (T) field.get(object);
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        return null;
    }
}
