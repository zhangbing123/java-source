package com.jdk.reflex;

import com.jdk.entity.Student;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @description: 测试反射
 * @author: zhangbing
 * @create: 2020-10-13 14:24
 **/
public class TestReflexMain {

    public static void main(String[] args) throws InvocationTargetException, IllegalAccessException, NoSuchMethodException, NoSuchFieldException {

        //测试通过反射调用对象的私有方法和属性
        testInvokePrivateMethodField();

    }

    private static void testInvokePrivateMethodField() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException, NoSuchFieldException {
        Student student = new Student("张三", 13);

        //say()方法是public方法
        Method say = student.getClass().getDeclaredMethod("say");
        say.invoke(student, null);

        //获取private的方法
        Method testPrivate = student.getClass().getDeclaredMethod("testPrivate");
        testPrivate.setAccessible(true);//必须设置访问权限
        testPrivate.invoke(student, null);//执行方法

        //获取私有属性
        Field name = student.getClass().getDeclaredField("name");
        name.setAccessible(true);//设置访问权限
        System.out.println("对象的private属性name: " + name.get(student));
        name.set(student, "重新设置一个name值");
        System.out.println("对象的private属性name: " + name.get(student));

        /**
         * 结果：
         * 张三 say hello
         * sleep 之后执行...
         * 对象的private方法被成功调用
         * 对象的private属性name: 张三
         * 对象的private属性name: 重新设置一个name值
         *
         * 所以对象的private修饰的方法或者属性 并不是绝对安全的 可以通过反射进行访问
         *
         * 而java的private关键字被设计的意义，并不是为了保证对象私有属性的绝对安全
         *
         * 而是
         * 1.java用户在正常调用时，private就是一个约束
         * 2.java有一个很重要的概念：封装，private的意义就是把一些属于对象的东西封装到对象中，这些东西是属于我的，并且我不想暴露给被人
         * 如果你非要通过setAccessible(true)这种方式用 我也不拦你。
         */

    }
}
