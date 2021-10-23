package seedu.address.model.lesson;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;
import static seedu.address.model.util.LessonUtil.formattedValue;

/**
 * Represents the amount per lesson payable in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidLessonRates(String)}
 */
public class LessonRates {
    public static final String MESSAGE_CONSTRAINTS =
            "Lesson Rates should be formatted with a decimal point '.' as a separator between the dollars and cents, "
                    + "and adhere to the following constraints:\n"
                    + "1. Lesson Rates should only contain numbers and at most one decimal point.\n"
                    + "2. Lesson Rates should not start or end with a decimal point"
                    + " and should have at most two decimal places.";

    public static final String VALIDATION_REGEX = "^[0-9]+(\\.[0-9]{1,2})?$";
    public final String value;

    /**
     * Constructs a {@code LessonRates}.
     *
     * @param lessonRates A valid lesson rate.
     */
    public LessonRates(String lessonRates) {
        requireNonNull(lessonRates);
        checkArgument(isValidLessonRates(lessonRates));
        value = formattedValue(lessonRates);
    }

    public static boolean isValidLessonRates(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    /**
     * Get Lesson Rates in float type for total cost per lesson calculation.
     */
    public float getLessonRatesFloat() {
        return Float.parseFloat(value);
    }



    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        return other == this //short circuit if same object
                || (other instanceof LessonRates //instanceof handles nulls
                && value.equals(((LessonRates) other).value)); //state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }
}
