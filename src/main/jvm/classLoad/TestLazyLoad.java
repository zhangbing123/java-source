package main.jvm.classLoad;

/**
 * @description: 测试类加载-懒加载
 * @author: zhangbing
 * @create: 2020-06-11 11:25
 **/
public class TestLazyLoad {

    static {
        System.out.println("*******************load TestLazyLoad*****************");
    }

    //启动main方法  必须要加载TestLazyLoad类
    public static void main(String[] args) {
        new A();//new一个A的实例 用到了A,所以A会被加载
        System.out.println("*******************load test*****************");
        B b = null;//定义了B，但是没有用到B，所以B不会被加载  所以，类的加载是一个懒加载，只有在被用到时才会加载，有些不被用到的类  可能一直不会被加载到
//        B b = new B();//此时B会被加载
    }
}
