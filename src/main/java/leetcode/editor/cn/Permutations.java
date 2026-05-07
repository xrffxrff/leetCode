
package leetcode.editor.cn;

import java.util.*;
import leetcode.editor.common.*;

public class Permutations {

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {

        // 主方法：输入int数组，返回所有全排列
        public List<List<Integer>> permute(int[] nums) {
            // 存储最终所有排列结果
            List<List<Integer>> result = new ArrayList<>();
            // 边界判断：数组为空直接返回
            if (nums == null || nums.length == 0) {
                return result;
            }
            // 调用回溯方法
            backtrack(nums, 0, result);
            return result;
        }

        /**
         * 回溯核心方法
         * @param nums 原始数组
         * @param start 当前固定的位置索引
         * @param result 存储所有排列结果
         */
        private void backtrack(int[] nums, int start, List<List<Integer>> result) {
            // 递归终止条件：start到达数组末尾，说明已经生成一个完整排列
            if (start == nums.length - 1) {
                // 将当前数组转为List，加入结果集
                List<Integer> permutation = new ArrayList<>();
                for (int num : nums) {
                    permutation.add(num);
                }
                result.add(permutation);
                return;
            }

            // 遍历从start开始的所有元素，依次与start位置交换
            for (int i = start; i < nums.length; i++) {
                // 1. 交换：固定第start位的元素为nums[i]
                swap(nums, start, i);
                // 2. 递归：处理下一个位置
                backtrack(nums, start + 1, result);
                // 3. 回溯：恢复交换前的状态，继续生成其他排列
                swap(nums, start, i);
            }
        }

        // 交换数组中两个位置的元素
        private void swap(int[] nums, int i, int j) {
            int temp = nums[i];
            nums[i] = nums[j];
            nums[j] = temp;
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)


    // 测试代码
    public static void main(String[] args) {
        Solution solution = new Permutations().new Solution();

        // 测试用例1：数组 [1,2,3]
        int[] nums1 = {1, 2, 3};
        System.out.println("数组 [1,2,3] 的全排列：");
        List<List<Integer>> res1 = solution.permute(nums1);
        for (List<Integer> list : res1) {
            System.out.println(list);
        }

        System.out.println("------------------------");

        // 测试用例2：数组 [5,6]
        int[] nums2 = {5, 6};
        System.out.println("数组 [5,6] 的全排列：");
        List<List<Integer>> res2 = solution.permute(nums2);
        for (List<Integer> list : res2) {
            System.out.println(list);
        }
    }
}