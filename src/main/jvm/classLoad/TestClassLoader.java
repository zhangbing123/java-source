package main.jvm.classLoad;

import com.sun.crypto.provider.DESKeyFactory;
import sun.misc.Launcher;

import java.net.URL;


/**
 * @description: 验证加载器，加载的类
 * @author: zhangbing
 * @create: 2020-06-11 11:35
 **/
public class TestClassLoader {

    public static void main(String[] args) {

        /**
         * 测试类有哪些加载器加载
         */
        System.out.println("=======================测试类有哪些加载器加载====================");
        //rt.jar包的核心类库中的类
        System.out.println(String.class.getClassLoader());
        //ext扩展目录下的类
        System.out.println(DESKeyFactory.class.getClassLoader());
        //自定义类
        System.out.println(TestClassLoader.class.getClassLoader());

        //应用类加载器 默认获取到的是应用类加载器
        ClassLoader appClassLoader = ClassLoader.getSystemClassLoader();
        //ext扩展类加载器
        ClassLoader extClassLoader = appClassLoader.getParent();
        //引导类加载器
        ClassLoader bootStrapClassLoader = extClassLoader.getParent();
        System.out.println("the appClassLoader:"+appClassLoader);
        System.out.println("the extClassLoader:"+extClassLoader);
        System.out.println("the bootStrapClassLoader:"+bootStrapClassLoader);


        /**
         * 测试各加载器加载哪些目录包下的类
         */
        System.out.println("=====================测试各加载器加载哪些目录包下的类======================");

        System.out.println("bootStrapClassLoader加载以下文件：");
        URL[] urLs = Launcher.getBootstrapClassPath().getURLs();
        for (URL urL : urLs) {
            System.out.println(urL);
        }

        System.out.println("extClassLoader加载以下文件：");
        System.out.println(System.getProperty("java.ext.dirs"));

        System.out.println("appClassLoader加载以下文件：");
        System.out.println(System.getProperty("java.class.path"));


    }
}
