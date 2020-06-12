package main.algorithm;

/**
 * @description:
 * @author: zhangbing
 * @create: 2020-06-12 09:53
 **/
public class TestAlgorithm {

    public static void main(String[] args) {
        int [] nums = {1,2,1,2,3,3,4,5,1,5,6,7,7,8,7};
        int res = 0;

        for (int value: nums) {
            res ^= value;
        }

        System.out.println(res);
    }
}
