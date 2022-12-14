package com.urise.webapp;

import com.urise.webapp.model.Resume;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class MainReflection {
    public static void main(String[] args) throws IllegalAccessException, InvocationTargetException, NoSuchMethodException {
        Resume r = new Resume();
        Field field = r.getClass().getDeclaredFields()[0];
        field.setAccessible(true);
        System.out.println(field.getName());
        System.out.println(field.get(r));
        field.get(r);
        field.set(r, "new_uuid");
        System.out.println(r);

        //HW4 toString method thrue Reflection
        field.set(r, "new_uuid123");
        Method toStringMethod = r.getClass().getMethod("toString");
        System.out.println(toStringMethod.invoke(r));
    }
}