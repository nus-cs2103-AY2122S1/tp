package seedu.address.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

import java.text.DecimalFormat;

/**
 * Represents a Person's body measurements in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidMeasurement(String, GenderType)}
 */
public class Measurement {
    public static final String GENERAL_MESSAGE_CONSTRAINTS =
            "Body measurements should be of the format height_waist_shoulder[_bust] in cm, and it should not be blank";
    public static final String MALE_MESSAGE_CONSTRAINTS =
            "A male's body measurements should be of the format height_waist_shoulder in cm,"
                    + " and it should not be blank";
    public static final String FEMALE_MESSAGE_CONSTRAINTS =
            "A female's body measurements should be of the format height_waist_shoulder_bust in cm,"
                    + " and it should not be blank";
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

    public String getValue() {
        String[] measurements = value.split("_");
        formatMeasurements(measurements);
        return String.join("_", measurements);
    }

    private void formatMeasurements(String[] args) {
        DecimalFormat df = new DecimalFormat("#.##");
        for (int i = 0; i < args.length; i++) {
            double value = Double.parseDouble(args[i]);
            args[i] = df.format(value);
        }
    }

    /**
     * Returns true if a given string is a valid measurement.
     */
    public static boolean isValidMeasurement(String test, GenderType genderType) {
        requireNonNull(genderType);
        String[] args = test.split("_");
        boolean isArgsNumber = isNumber(args);
        return isArgsNumber && (genderType == GenderType.MALE ? args.length == 3 : args.length == 4);
    }

    /**
     * Returns true if a given string is a valid measurement.
     */
    public static boolean isValidMeasurement(String test) {
        requireNonNull(test);
        String[] args = test.split("_");
        boolean isArgsNumber = isNumber(args);
        return isArgsNumber && (args.length == 3 || args.length == 4);
    }

    /**
     * Returns message constraints based on gender type.
     */
    public static String getMessageConstraints(GenderType genderType) {
        requireNonNull(genderType);
        if (genderType == GenderType.MALE) {
            return MALE_MESSAGE_CONSTRAINTS;
        } else {
            return FEMALE_MESSAGE_CONSTRAINTS;
        }
    }

    private static boolean isNumber(String[] args) {
        for (String i: args) {
            try {
                Double.parseDouble(i);
            } catch (NumberFormatException e) {
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
