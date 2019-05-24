package leetcode;

import com.google.common.collect.Sets;

import java.util.*;

/**
 * @Author:XiongbinHuang
 * @Description
 * @Date:Created in 14:33 2019/5/20
 * @Modified By:
 */
class Solution {
    public static void main(String[] args) {
        Integer[] nums = {0, 0, 1, 1, 1, 2, 2, 3, 3, 4};
        int i = removeDuplicates(nums);
        System.out.println(i);
    }


    public static int removeDuplicates(Integer[] nums) {
        int number = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != nums[number]) {
                number++;
                nums[number] = nums[i];
            }
        }
        return number + 1;
    }
}
