package seedu.teachbook.model.classobject;

import static java.util.Objects.requireNonNull;
import static seedu.teachbook.commons.util.AppUtil.checkArgument;

/**
 * Represents a Class's name in the TeachBook.
 * Guarantees: immutable; is valid as declared in {@link #isValidClassName(String)}.
 */
public class ClassName {

    public static final String MESSAGE_CONSTRAINTS = "Class names should only "
            + "contain alphanumeric characters and spaces, have less than 20 characters (including spaces), "
            + "and it should not be blank";

    public static final String VALIDATION_REGEX = "[\\p{Alnum}][\\p{Alnum} ]*";

    public final String nameOfClass;

    /**
     * Constructs a {@code ClassName}.
     *
     * @param className a valid class name.
     */
    public ClassName(String className) {
        requireNonNull(className);
        checkArgument(isValidClassName(className), MESSAGE_CONSTRAINTS);
        nameOfClass = className;
    }

    /**
     * Checks if the given string is a valid class name.
     *
     * @param test a string to be tested.
     * @return {@code true} if the given string is a valid class name.
     */
    public static boolean isValidClassName(String test) {
        return test.matches(VALIDATION_REGEX) && test.length() < 20;
    }


    @Override
    public String toString() {
        return nameOfClass;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        } else if (other instanceof ClassName) {
            return nameOfClass.equals(((ClassName) other).nameOfClass);
        } else if (other instanceof ClassNameDescriptor) {
            return nameOfClass.equals(((ClassNameDescriptor) other).nameOfClass);
        } else {
            return false;
        }
    }

    @Override
    public int hashCode() {
        return nameOfClass.hashCode();
    }

}
