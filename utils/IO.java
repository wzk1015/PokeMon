package utils;

import java.util.Scanner;

//输入输出管理类

public class IO {
    public static final Scanner sn = new Scanner(System.in);

    public static void debug(Object s) {
        //println(s);
    }

    public static void println(Object... s) {
        print(s);
        System.out.println();
    }

    public static void print(Object... s) {
        for (int i = 0; i < s.length; i++) {
            if (i > 0)
                System.out.print(" ");
            System.out.print(s[i].toString());
        }
    }
}
