package mw.solutions;

import java.util.ArrayList;
import java.util.List;

/**
 * 2. 有长文本字符串变量strA， String strA="LONG TEXT ......"，请用Java代码实现从该字符串中找出数字所有小于1000的数字。
 */

public class RealmeInterview extends SolutionBase {

    /**
     * RealmeInterview
     */

    @Override
    public void solve() {
        String strA = scanStrings()[0];
        if (strA == null || strA.length() == 0)
            return;
        List res = new ArrayList<Integer>();
        int index = 0;
        while (index < strA.length()) {
            char c = strA.charAt(index);
            StringBuilder sb = new StringBuilder();
            while (isDigit(c)) {
                sb.append(c);
                index++;
                // ...
            }

        }

    }

    private boolean isDigit(char c) {
        return c >= '0' && c <= '9';
    }
}
