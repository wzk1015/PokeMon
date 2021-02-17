package utils;

import java.util.*;

//工具类：数据结构的常用方法，断言、异常、退出，随机数相关

public class Utils {
    private static final Random random = new Random();

    public static <T> boolean contains(Collection<T> lst, T item) {
        for (T obj : lst) {
            if (obj == item) {
                return true;
            }
        }
        return false;
    }

    public static void assertion(boolean statement, String msg) {
        if (!statement) {
            raise("Assertion failed" + msg);
        }
    }

    public static void raise(String msg) {
        IO.println(msg);
        exit(-1);
    }

    public static void exit(int code) {
        IO.println("System exit with code", code);
        System.exit(code);
    }

    public static void exit() {
        exit(0);
    }

    public static double random() {
        return Math.random();
    }

    public static int randint(int min, int max) {
        return random.nextInt(max - min + 1) + min;
    }

    public static double randdouble(double min, double max) {
        return min + random() * (max - min);
    }

    public static <E> E choice(ArrayList<E> choices) {
        Random random = new Random();
        return choices.get(randint(0, choices.size() - 1));
    }
}