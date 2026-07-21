package huawei;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Code15 {
    public static List<List<Integer>> threeSum(int[] nums){
        List<List<Integer>> res = new ArrayList<>();
        // 首先这个排序很重要，这个直接影响了后面我们的循环判断
        Arrays.sort(nums);
        int n =nums.length;
        for (int i = 0; i < n-2; i++) {
            // 这个是跳过0索引之后的重复，跳过的是我们固定的和。
            if(i > 0 && nums[i] == nums[i-1]) continue;

            int target = -nums[i];
            int left = i + 1, right = n - 1;
            // 这个是内层循环，我们固定总和，然后去找与和数互为相反数的总和
            while(left < right){
                int sum = nums[left] + nums[right];
                if(sum == target){
                    res.add(Arrays.asList(nums[i], nums[left], nums[right]));
                    // 这个一定要是while，因为用if只会跳过一个重复的，但如果三个连续重复则会少跳过一个
                   while(left < right && nums[left] == nums[left + 1]){
                        left++;
                    }
                    while(left < right && nums[right] == nums[right - 1]){
                        right--;
                    }
                    left++;
                    right--;
                } else if (sum < target) {
                    left++;
                }else {
                    right--;
                }
            }
        }
        return res;
    }
}
