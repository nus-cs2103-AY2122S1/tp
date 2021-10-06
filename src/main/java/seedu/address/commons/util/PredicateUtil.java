package seedu.address.commons.util;

import java.util.function.Predicate;
import java.util.stream.Stream;

/**
 * Utility class for predicate related operations.
 *
 * @author zhenghanlee
 */
public class PredicateUtil {

    /**
     * Combines predicates using {@link Predicate#or(Predicate)}.
     *
     * @return false predicate if no arguments are present
     */
    @SafeVarargs
    public static <T> Predicate<T> union(Predicate<T>... predicates) {
        return Stream.of(predicates).reduce(x -> false, Predicate::or);
    }

    /**
     * Combines predicates using {@link Predicate#and(Predicate)}.
     *
     * @return true predicate if no arguments are present
     */
    @SafeVarargs
    public static <T> Predicate<T> intersection(Predicate<T>... predicates) {
        return Stream.of(predicates).reduce(x -> true, Predicate::and);
    }

}
