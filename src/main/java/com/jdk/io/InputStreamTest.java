package com.jdk.io;

import java.io.*;

/**

* @Description:    测试mac环境下一行一行读取文本文件内容

* @Author:         zhangbing

* @CreateDate:     2020/6/21 4:36 PM

*/
public class InputStreamTest {

    public static void main(String[] args) throws FileNotFoundException {

        String fileName = "/Users/zhangbing/worker/test.txt";
        FileReader fileReader = new FileReader(fileName);

        BufferedReader reader = null;
        try {
            reader = new BufferedReader(fileReader);
            String tempStr;
            while ((tempStr = reader.readLine()) != null) {
                System.out.println(tempStr);
            }
            reader.close();

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        }
    }
}
