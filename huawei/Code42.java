package huawei;

public class Code42 {
    public static int trap(int[] height){
        // 每个位置接水的多少取决于左右两边最短的那个长度减去自己当前的高度
        int left = 0, right = height.length - 1;
        int water = 0;
        int maxLeft = 0, maxright = 0;
        while(left < right){
            maxLeft = Math.max(height[left], maxLeft);
            maxright = Math.max(height[right], maxright);
            if(maxright < maxLeft ){
                water += maxright - height[right];
                right--;
            }else{
                water += maxLeft - height[left];
                left++;
            }
        }
        return water;
    }
}
