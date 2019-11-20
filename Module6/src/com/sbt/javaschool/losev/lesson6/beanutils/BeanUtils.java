package com.sbt.javaschool.losev.lesson6.beanutils;

import com.sbt.javaschool.losev.lesson6.presentation.Main;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BeanUtils {
    /**
     * Scans object "from" for all getters. If object "to"
     * contains correspondent setter, it will invoke it
     * to set property value for "to" which equals to the property
     * of "from".
     * <p/>
     * The type in setter should be compatible to the value returned
     * by getter (if not, no invocation performed).
     * Compatible means that parameter type in setter should
     * be the same or be superclass of the return type of the getter.
     * <p/>
     * The method takes care only about public methods.
     *
     * @param to   Object which properties will be set.
     * @param from Object which properties will be used to get values.
     */
    public static void assign(Object to, Object from) throws InvocationTargetException, IllegalAccessException {
        List<Method> getters = new ArrayList<>(Arrays.asList(Main.getAllGetters(from.getClass())));
        List<Method> setters = new ArrayList<>(Arrays.asList(Main.getAllSetters(to.getClass())));

        for (Method getter : getters) {
            String setterName = "s" + getter.getName().substring(1);
            for (Method setter: setters){
                if (setter.getName().equals(setterName)){
                    if (getter.getReturnType().equals(setter.getParameters()[0].getType())) {
                        setter.invoke(to, getter.invoke(from));
                    } else if (getter.getReturnType().getSuperclass() != null){
                        if (getter.getReturnType().getSuperclass().equals(setter.getParameters()[0].getType())){
                            setter.invoke(to, getter.invoke(from));
                        }
                    }
                }
            }
        }
        System.out.println();
    }
}
