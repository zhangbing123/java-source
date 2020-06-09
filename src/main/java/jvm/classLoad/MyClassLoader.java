package main.java.jvm.classLoad;

/**
 * @description: 自定义类加载器
 * @author: zhangbing
 * @create: 2020-06-09 13:59
 **/
public class MyClassLoader extends ClassLoader {

    @Override
    protected Class<?> loadClass(String name, boolean resolve) throws ClassNotFoundException {
        return super.loadClass(name, resolve);
    }

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        return super.findClass(name);
    }
}
