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
}
