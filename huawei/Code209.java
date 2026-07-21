package huawei;

public class Code209 {
    public int minSubArrayLen(int[] nums, int target){
        // 依旧左边索引只用来求长度
        int left = 0, sum = 0;
        // 我们需要设置一个极大的数字，不然可能后面取最小值一直是我们设置的初始值
        int minLen = Integer.MAX_VALUE;
        for (int right = 0; right < nums.length; right++) {
            sum += nums[right];
            // 一直累加，然后我们在while循环中减去左边的值。以寻求这个情况下的最小长度，因此while循环中需要对left++
            // 寻找完这轮之后继续寻找下一组
            while(sum >= target){
                minLen = Math.min(minLen, right - left + 1);
                sum -= nums[left];
                left++;
            }
        }
        return minLen == Integer.MAX_VALUE ? 0 : minLen;
    }
}
