package utils;

import java.util.Collection;

public class Utils {
    public static <T> boolean contains(Collection<T> lst, T item) {
        for (T obj: lst) {
            if (obj == item) {
                return true;
            }
        }
        return false;
    }

    public static void assertion(boolean statement, String msg) {
        if (!statement) {
            IO.println("Assertion failed: ", msg);
            exit(-1);
        }
    }

    public static void exit(int code) {
        IO.println("System exit with code", code);
        System.exit(code);
    }

    public static void exit() {
        exit(0);
    }
}
