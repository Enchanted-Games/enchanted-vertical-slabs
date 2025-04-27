package games.enchanted.verticalslabs.util;

import java.util.HashSet;
import java.util.List;

public class ListUtil {
    public static <T> boolean listsEqualOrderIndependent(List<T> list1, List<T> list2) {
        return new HashSet<>(list1).equals(new HashSet<>(list2));
    }
}
