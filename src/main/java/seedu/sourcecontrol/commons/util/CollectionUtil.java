package seedu.sourcecontrol.commons.util;

import static java.util.Objects.requireNonNull;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;

/**
 * Utility methods related to Collections
 */
public class CollectionUtil {

    /** @see #requireAllNonNull(Collection) */
    public static void requireAllNonNull(Object... items) {
        requireNonNull(items);
        Stream.of(items).forEach(Objects::requireNonNull);
    }

    /**
     * Throws NullPointerException if {@code items} or any element of {@code items} is null.
     */
    public static void requireAllNonNull(Collection<?> items) {
        requireNonNull(items);
        items.forEach(Objects::requireNonNull);
    }

    /**
     * Returns true if {@code items} contain any elements that are non-null.
     */
    public static boolean isAnyNonNull(Object... items) {
        return items != null && Arrays.stream(items).anyMatch(Objects::nonNull);
    }

    /**
     * Returns true if two lists are equal, ignoring the order of elements within the list.
     */
    public static <T> boolean equalsIgnoreOrder(List<T> l1, List<T> l2) {
        if (l1 == null || l2 == null) {
            return l1 == l2;
        }
        return l1.size() == l2.size()
                && l1.stream().allMatch(l2::contains);
    }
}
