package seedu.address.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Person's body measurements in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidMeasurement(String, GenderType)}
 */
public class Measurement {
    public static final String GENERAL_MESSAGE_CONSTRAINTS =
            "Body measurements should be ok the format height_waist_shoulder_[bust] in cm, and it should not be blank";
    public static final String MALE_MESSAGE_CONSTRAINTS =
            "Body measurements should be of the format height_waist_shoulder in cm, and it should not be blank";
    public static final String FEMALE_MESSAGE_CONSTRAINTS =
            "Body measurements should be of the format height_waist_shoulder_bust in cm, and it should not be blank";
    public static final String IS_NUMBER_REGEX = "\\d+";
    public final String value;

    /**
     * Constructs a {@code Measurement}.
     *
     * @param measurement A valid measurement.
     */
    public Measurement(String measurement) {
        requireNonNull(measurement);
        checkArgument(isValidMeasurement(measurement), GENERAL_MESSAGE_CONSTRAINTS);
        value = measurement;
    }

    /**
     * Returns true if a given string is a valid measurement.
     */
    public static boolean isValidMeasurement(String test, GenderType genderType) {
        String[] args = test.split("_");
        boolean isArgsNumber = isNumber(args);
        return isArgsNumber && (genderType == GenderType.MALE ? args.length == 3 : args.length == 4);
    }

    /**
     * Returns true if a given string is a valid measurement.
     */
    public static boolean isValidMeasurement(String test) {
        String[] args = test.split("_");
        boolean isArgsNumber = isNumber(args);
        return isArgsNumber && (args.length == 3 || args.length == 4);
    }

    public static String getMessageConstraints(GenderType genderType) {
        if (genderType == GenderType.MALE) {
            return MALE_MESSAGE_CONSTRAINTS;
        } else {
            return FEMALE_MESSAGE_CONSTRAINTS;
        }
    }

    private static boolean isNumber(String[] args) {
        for (String i: args) {
            if (!i.matches(IS_NUMBER_REGEX)) {
                return false;
            }
        }
        return true;
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Measurement // instanceof handles nulls
                && value.equals(((Measurement) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }
}
