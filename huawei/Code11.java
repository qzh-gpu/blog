package huawei;
// 盛水最多的容器
public class Code11 {
    public static int maxArea(int[] height){
        // 思路： 我们从两端开始，保证我们的宽最长，然后后面每次移动宽肯定减小，所以
        // 想找大面积只能是某一条边变长，因此我们遍历每一条边，直至左右重合。
        int left = 0, right = height.length - 1;
        int area = 0;
        int maxArea = 0;
        while (left < right){
            int h = Math.min(height[left], height[right]);
            area = h * (right - left);
            maxArea = Math.max(area, maxArea);
            if(height[left] < height[right]){
                left++;
            }else {
                right--;
            }
        }
        return maxArea;
    }
}
