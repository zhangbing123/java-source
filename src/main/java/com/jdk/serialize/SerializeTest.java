package com.jdk.serialize;

import com.jdk.entity.Student;

import java.io.*;

/**
 * @description: 测试序列化和反序列化
 * @author: zhangbing
 * @create: 2020-08-28 17:03
 **/
public class SerializeTest {

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        //序列化User对象
        serialize();

        //反序列化
//        User user = deserialize();
//        System.out.println(user);//User{name='张三', age=20, sex=1, code='null'}  code=null 是没有被序列化的  所以也不会被反序列化

        /**
         * 那静态变量sex是否会被序列化呢？
         *
         * 第一步：先把对象(User{name='张三', age=20, sex=1, code='123'})序列化user.txt文件中
         * 第二步：修改对象的静态属性sex=2;
         * 第三步：将user.txt文件反序列化成user对象
         *
         * 结果：User{name='张三', age=20, sex=2, code='null'}  set等于最新值2  说明sex不会被序列化
         *
         */


        /**
         * 探究serialVersionUID这个字段的意义？
         *
         * 实验 1  不手动设置serialVersionUID字段的值：
         * 第一步：先把对象(User{name='张三', age=20, sex=1, code='123'})序列化user.txt文件中
         * 第二步：新增字段department
         * 第三步：将user.txt文件反序列化成user对象
         *
         * 结果：抛出异常：java.io.InvalidClassException: com.jdk.serialize.User
         * local class incompatible: stream classdesc serialVersionUID = -1057053695467362358, local class serialVersionUID = 8191924715493570484
         *
         *
         * 实验 1  手动设置serialVersionUID字段的值，例如设置为1L：
         * 重复实现1的步骤
         *
         * 结果：反序列化成功
         * User{name='张三', age=20, sex=1, department='人事', code='null'}
         *
         * 总结：针对这个字段serialVersionUID  如果我们不手动设置值，则jvm会根据对象属性自动生成一个值。 如果手动设置了  则会一直使用这个值
         * 对于实验1：序列化时，没有属性 department时  jvm自动生成一个值，
         * 当你序列化完成后，修改对象的字段了，那么serialVersionUID就会重新生成一个值，反序列化时用的还是老的值，所以会抛异常
         * 如果手动设置了，序列化前后  不管对象的属性怎么变化 都不会影响这个值，所以每次反序列化都会成功
         *
         * 所以一般情况下对象实现Serializable接口时  对于serialVersionUID都手动指定一个值，防止反序列化时报错
         *
         */


        /**
         * 探究对象中属性引用的对象不可序列化时，序列化是否能够成功？
         *
         * 在对user序列化时  抛出异常：Exception in thread "main" java.io.NotSerializableException: com.jdk.entity.Student
         *
         * 所以当可以序列化的对象中 有属性引用了一个不可序列化的对象  那么在对对象序列化时 会报错
         *
         */
    }

    private static void serialize() throws IOException {

        /**
         * sex字段是一个静态字段
         * code字段是被transient修饰的
         * student属性对应的Student对象不可序列化
         */
        User user = new User();
        user.setName("张三");
        user.setAge(20);
        user.setCode("123");//被transient修饰的
        user.setDepartment("人事");
        user.setStudent(new Student("学生", 22));
        System.out.println("序列化前对象：" + user);
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(new File("D://user.txt")));
        objectOutputStream.writeObject(user);
        System.out.println("序列化成功");
        objectOutputStream.close();
    }

    private static User deserialize() throws IOException, ClassNotFoundException {
        ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(new File("D://user.txt")));
        User user = (User) objectInputStream.readObject();
        System.out.println("反序列化成功");
        return user;
    }
}
