package com.jdk.jvm.classLoad;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Method;

/**
 * @description: 自定义类加载器  父加载器默认AppClassLoader 应用类加载器
 * @author: zhangbing
 * @create: 2020-06-09 13:59
 **/
public class MyClassLoader extends ClassLoader {

    private String classPath;

    public MyClassLoader(String classPath) {
        this.classPath = classPath;
    }


    /**
     * 重写loadClass 打破双亲委派机制
     *
     * @param name
     * @param resolve
     * @return
     * @throws ClassNotFoundException
     */
    @Override
    protected Class<?> loadClass(String name, boolean resolve) throws ClassNotFoundException {
        synchronized (getClassLoadingLock(name)) {
            // 首先，查找当前加载器是否已经加载过，已经加载过 则直接返回
            Class<?> c = findLoadedClass(name);
            if (c == null) {
                //当前加载器没有加载过
                long t0 = System.nanoTime();
                if (!name.startsWith("main.jvm.classLoad")) {
                    //jdk的类 不允许打破双亲委派机制
                    c = getParent().loadClass(name);
                } else {
                    //自定义的类 使用自定义加载器加载
                    c = findClass(name);
                }
                // If still not found, then invoke findClass in order
                // to find the class.
                long t1 = System.nanoTime();

                // this is the defining class loader; record the stats
                sun.misc.PerfCounter.getParentDelegationTime().addTime(t1 - t0);
                sun.misc.PerfCounter.getFindClassTime().addElapsedTimeFrom(t1);
                sun.misc.PerfCounter.getFindClasses().increment();
            }
            if (resolve) {
                resolveClass(c);
            }
            return c;
        }
    }

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        byte[] bytes = loadByte(name);
        return defineClass(name, bytes, 0, bytes.length);

    }

    private byte[] loadByte(String fullPathname) {
        String path = fullPathname.replaceAll("\\.", "/");
        byte[] bytes = null;
        try {
            FileInputStream fileInputStream = new FileInputStream(classPath + "/" + path + ".class");
            int available = fileInputStream.available();
            bytes = new byte[available];
            fileInputStream.read(bytes);
            fileInputStream.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();

        } catch (IOException e) {
            e.printStackTrace();
        }

        return bytes;
    }


    public static void main(String[] args) {
        //创建一个自定义加载器 传入自定义类路径
        MyClassLoader myClassLoader = new MyClassLoader("D:/workspace/test/");
        try {
            Class<?> aClass = myClassLoader.loadClass("main.jvm.classLoad.User", false);
            System.out.println("使用的类加载器：" + aClass.getClassLoader());
            Object o = aClass.newInstance();
            Method testMethod = aClass.getDeclaredMethod("testMethod", null);
            testMethod.invoke(o, args);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
