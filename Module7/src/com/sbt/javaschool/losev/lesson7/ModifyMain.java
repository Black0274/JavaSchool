package com.sbt.javaschool.losev.lesson7;

import javax.tools.*;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.Arrays;

/**
 * Сделать свой класслоадер который из имеющегося списка путей на диске C:\ (2шт) ищет  класс SayHello
 * с методом sayHello().
 * К примеру в одном каталоге есть SayHello#say() в другом каталоге класс SayHello#sayHello()
 * Фактически необходима обертка в виде своего класслоадера над кодом в этом примере.
 * Свой класслоадер, перебрав имеющиеся классы, выбирает правильный и выдает его.
 *
 */
public class ModifyMain {

    private static final String DIR_NAME_OLD = System.getProperty("java.io.tmpdir") + "old";
    private static final String DIR_NAME_NEW = System.getProperty("java.io.tmpdir") + "new";
    private static final String CLASS_NAME = "SayHello";
    public static final String METHOD_NAME_INCORRECT = "say";
    public static final String METHOD_NAME_CORRECT = "sayHello";

    public static void main(String[] args) throws MalformedURLException, IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException, ClassNotFoundException
    {
        ModifyMain main = new ModifyMain();
        main.checkDirectory(DIR_NAME_OLD);
        main.checkDirectory(DIR_NAME_NEW);

        try {
            Class<?> aClass = Class.forName(CLASS_NAME);
            System.out.println(aClass.getName());
        } catch (ClassNotFoundException e) {
            System.out.println("Класс " + CLASS_NAME + " отсутсвует в ClassPath\n");
        }

        main.generateClass(DIR_NAME_OLD, CLASS_NAME, METHOD_NAME_INCORRECT);
        File dir = new File(DIR_NAME_OLD);
        MyClassLoader classLoader = new MyClassLoader(dir);
        Class<?> clazz = classLoader.loadClass(CLASS_NAME, METHOD_NAME_CORRECT);

        if (clazz == null){
            System.out.println("Класс " + Arrays.asList(classLoader.getURLs()).get(0).getPath()
                    + CLASS_NAME + ".class не содержит метода " + METHOD_NAME_CORRECT + "\n");
        }
        else{
            System.out.println("Класс " + Arrays.asList(classLoader.getURLs()).get(0).getPath()
                    + CLASS_NAME + ".class содержит метод " + METHOD_NAME_CORRECT + "\nВызов метода:");
            Method method = clazz.getDeclaredMethod(METHOD_NAME_CORRECT);
            method.invoke(clazz.newInstance());
            System.out.println();
        }


        main.generateClass(DIR_NAME_NEW, CLASS_NAME, METHOD_NAME_CORRECT);
        dir = new File(DIR_NAME_NEW);
        classLoader = new MyClassLoader(dir);
        clazz = classLoader.loadClass(CLASS_NAME, METHOD_NAME_CORRECT);

        if (clazz == null){
            System.out.println("Класс " + Arrays.asList(classLoader.getURLs()).get(0).getPath()
                    + CLASS_NAME + ".class не содержит метода " + METHOD_NAME_CORRECT + "\n");
        }
        else{
            System.out.println("Класс " + Arrays.asList(classLoader.getURLs()).get(0).getPath()
                    + CLASS_NAME + ".class содержит метод " + METHOD_NAME_CORRECT + "\nВызов метода:");
            Method method = clazz.getDeclaredMethod(METHOD_NAME_CORRECT);
            method.invoke(clazz.newInstance());
            System.out.println();
        }
    }

    private static void checkDirectory(String dirName) {
        File directory = new File(dirName);
        if (! directory.exists()){
            directory.mkdir();
        }
    }

    private void generateClass(String dirName, String clazzName, String methodName){
        try {
            File tempFile = new File (dirName, clazzName +".java");
            tempFile.deleteOnExit();
            String className = tempFile.getName().split("\\.")[0];
            String sourceCode = "public class " + className + " { public void "+ methodName + "() { System.out.println(\"Hello everybody !\"); } }";
            FileWriter fileWriter = new FileWriter(tempFile);
            fileWriter.write(sourceCode);
            fileWriter.close();

            JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
            StandardJavaFileManager manager = compiler.getStandardFileManager(null, null, null);

            File parentDirectory = tempFile.getParentFile();
            manager.setLocation(StandardLocation.CLASS_OUTPUT, Arrays.asList(parentDirectory));
            Iterable<? extends JavaFileObject> compilationUnits = manager.getJavaFileObjectsFromFiles(Arrays.asList(tempFile));
            compiler.getTask(null, manager, null, null, null, compilationUnits).call();
            manager.close();
            System.out.println("Сгенерирован класс " + tempFile.getAbsolutePath());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

