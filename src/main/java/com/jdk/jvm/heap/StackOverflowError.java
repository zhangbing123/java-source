package com.jdk.jvm.heap;

/**
 * 栈内存测试
 */
public class StackOverflowError {

    static int count=0;

    //JVM设置‐Xss160k(默认1M) 栈内存配置为128k
    public static void redo(){
        count++;


        /**
         * 每调用一次方法都需要在线程栈内存空间中开辟一块内存空间给方法使用，
         * -Xss配置的越小，线程栈内存越小，分配给方法的内存快越小  则递归调用的次数越小
         *
         * 默认线程栈配置为1M  可以调用2万多次
         *
         * 配置160k  只可以调用800多次
         */
        redo();
    }

    public static void main(String[] args) {
        try {
            redo();
        }catch (Throwable e){
            e.printStackTrace();
            System.out.println(count);
        }
    }
}
