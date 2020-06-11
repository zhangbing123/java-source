package main.jvm.classLoad;

/**
 * @description:
 * @author: zhangbing
 * @create: 2020-06-11 11:27
 **/
public class A {

    /**
     * 静态方法是在类加载的初始化节点被调用
     */
    static {
        System.out.println("*******************load A*****************");
    }

    /**
     * 构造方法是在类被加载完成后，被实例化的是才被调用  所以在静态方法后被执行
     */
    public A(){
        System.out.println("*******************init A*****************");
    }
}
