package com.wm.app;

import lombok.extern.slf4j.Slf4j;
import com.wm.app.solutions.leetcode438;
import com.wm.app.solutions.Leetcode312;
import com.wm.app.solutions.Leetcode78;
import com.wm.app.solutions.SolutionBase;
import com.wm.app.sourcecode.demo.LogBuilderDemo;
import com.wm.app.sourcecode.demo.NIODemo;
import com.wm.app.sourcecode.demo.ThreadLocalDemo;
import com.wm.app.sourcecode.demo.log.MessagePayload;
import org.apache.log4j.BasicConfigurator;

import java.util.Map;
import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        BasicConfigurator.configure();
        LogBuilderDemo.callServiceWithLogging(() -> {
            print("demo running");
            return new LogBuilderDemo.ApiCallResult();
        }, MessagePayload.Scope.CERT_DRAFT, MessagePayload.ActionType.GET, Map.of("test", "value1"));
        LogBuilderDemo.callServiceWithLogging(() -> {
            print("demo running");
            if (1 == 1)
                throw new RuntimeException("test ex");
            return new LogBuilderDemo.ApiCallResult();
        }, MessagePayload.Scope.CERT_ORDER, MessagePayload.ActionType.CREATE, Map.of("test", "value1"));

    }

    public static void print(Object str) {
        System.out.println(str);
    }

    public static int[] scanInts() {
        Scanner scanner = new Scanner(System.in);
        int len = scanner.nextInt();
        int[] nums = new int[len];
        int index = 0;
        while (len > 0) {
            nums[index++] = scanner.nextInt();
            len--;
        }
        return nums;
    }

    public static String[] scanStrings() {
        Scanner scanner = new Scanner(System.in);
        int len = scanner.nextInt();
        String[] strs = new String[len];
        int index = 0;
        while (len > 0) {
            strs[index++] = scanner.nextLine();
            len--;
        }
        return strs;
    }
}

