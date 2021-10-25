package seedu.address.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.function.Predicate;

/**
 * Contains helper methods for testing predicates.
 */
public class PredicateTestUtil {

    /**
     * Asserts that the testing of {@code object} by {@code predicate} is successful and the outcome equals to
     * {@code expectedBoolean}.
     */
    public static <T extends Predicate<S>, S> void assertPredicateSuccess(Predicate<S> predicate, S object,
        boolean expectedBoolean) {
        try {
            assertEquals(predicate.test(object), expectedBoolean);
        } catch (IllegalArgumentException iae) {
            throw new IllegalArgumentException("Invalid userInput.", iae);
        }
    }

    /**
     * Asserts that the testing of {@code object} by {@code predicate} is unsuccessful and the error message
     * equals to {@code expectedMessage}.
     */
    public static <T extends Predicate<S>, S> void assertPredicateFailure(Predicate<S> predicate, S object,
        String expectedMessage) {
        try {
            predicate.test(object);
            throw new AssertionError("The expected IllegalArgumentException was not thrown.");
        } catch (IllegalArgumentException iae) {
            assertEquals(expectedMessage, iae.getMessage());
        }
    }
}
