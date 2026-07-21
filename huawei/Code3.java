package huawei;

import java.util.HashMap;
import java.util.Map;

public class Code3 {
    public static int lengthOfLongestSubString(String s){
        // 我们维护一个HashMap，我们从始至终保证其中元素不重复
        // 因此他每次遍历的长度就是我们所有子串的长度，然后一个个比较。

        Map<Character, Integer> map = new HashMap<>();

        // left只是一个随着重复元素位置改变的索引

        int left = 0, maxLen = 0;
        for (int right = 0; right < s.length(); right++) {
            // right的遍历每次必然判断我们左边界是否重复，然后每次依然把遍历的元素存进去
            // 这样每次新增元素都保证不重复，同时保证所有子串都照顾到了
            char c = s.charAt(right);
            if(map.containsKey(c) && map.get(c) >= left){
                left = map.get(c) + 1;
            }
            map.put(c, right);
            maxLen = Math.max(maxLen, right - left + 1);
        }
        return maxLen;
    }
}
