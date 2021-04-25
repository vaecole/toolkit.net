package solutions;

import java.util.HashMap;
import java.util.Map;

/**
 * 众数
 */
public class S1 extends SolutionBase {
    @Override
    public void resolve() {
        int[] nums = scanInts();
        Map<Integer, Integer> counter = new HashMap<>();
        for (int num : nums) {
            if (counter.containsKey(num)) {
                counter.put(num, counter.get(num) + 1);
                continue;
            }
            counter.put(num, 1);
        }
        int maxKey = 0;
        int maxCount = 0;
        for (int key : counter.keySet()) {
            if (counter.get(key) > maxCount) {
                maxCount = counter.get(key);
                maxKey = key;
            }
        }
        print(maxKey);
        print(maxCount);
    }
}