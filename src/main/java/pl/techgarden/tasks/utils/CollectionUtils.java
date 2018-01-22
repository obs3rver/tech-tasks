package pl.techgarden.tasks.utils;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class CollectionUtils {

    public static <T> Set<T> mutableHashSetOf(T... elems) {
        return new HashSet<>(Arrays.asList(elems));
    }

    public static <T> Set<T> emptyMutableHashSet() {
        return new HashSet<>();
    }
}
