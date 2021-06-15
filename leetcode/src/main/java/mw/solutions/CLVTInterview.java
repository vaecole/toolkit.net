package mw.solutions;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 *
 */

public class CLVTInterview extends SolutionBase {

    /**
     * Clari(vate) Interview
     * Complete the 'getMaxColors' function below.
     *
     * The function is expected to return an INTEGER.
     * The function accepts following parameters:
     *  1. INTEGER_ARRAY prices
     *  2. INTEGER money
     */

    public int getMaxColors(List<Integer> prices, int money) {
        // Write your code here
        int max = 0;
        int sum = 0;
        for (int i = 0, j = 0; i <= j && j < prices.size(); ) {
            while (sum < money && j < prices.size()) {
                sum += prices.get(j);
                j++;
            }

            int cmax = (j - i - 1) + (sum <= money ? 1 : 0);
            if (cmax > max) {
                max = cmax;
            }
            while (sum >= money) {
                sum -= prices.get(i);
                i++;
            }
        }
        return max;
    }

    @Override
    public void solve() {
        int[] inputs = scanInts();
        int priceCount = inputs[0];
        List<Integer> prices = Arrays.stream(inputs).skip(1).limit(priceCount).boxed().collect(Collectors.toList());
        int money = inputs[inputs.length - 1];
        print(getMaxColors(prices, money));
    }
}
