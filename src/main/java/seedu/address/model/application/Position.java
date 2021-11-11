package seedu.address.model.application;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

import java.util.Comparator;

/**
 * Represents an Application's position in InternSHIP.
 * Guarantees: immutable; is valid as declared in {@link #isValidPosition(String)}
 */
public class Position {

    public static final String MESSAGE_CONSTRAINTS =
            "Position should only contain alphanumeric characters and space. "
                    + "It should not be blank or longer than 40 characters.";

    /*
     * The first character of the position must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid input.
     */
    public static final String VALIDATION_REGEX = "[\\p{Alnum}][\\p{Alnum} ]*";

    public final String value;

    /**
     * Constructs a {@code Position}.
     *
     * @param position A valid position.
     */
    public Position(String position) {
        requireNonNull(position);
        checkArgument(isValidPosition(position), MESSAGE_CONSTRAINTS);
        value = position;
    }

    /**
     * Returns true if a given string is a valid position.
     */
    public static boolean isValidPosition(String test) {
        return test.matches(VALIDATION_REGEX) && test.length() <= 40;
    }

    /**
     * Returns a Comparator object that compares two applications by their internship positions.
     * The application with an internship position that comes first alphabetically is taken to be greater.
     * Capitalisation of letters are ignored in the comparison.
     *
     * @return Comparator object that compares applications by their internship positions.
     */
    public static Comparator<Application> getComparator() {
        //Solution below adapted from AY2122S1-CS2103T-T15-3
        return (application, otherApplication) -> {
            String position = application.getPosition().value.toUpperCase();
            String otherPosition = otherApplication.getPosition().value.toUpperCase();
            return position.compareTo(otherPosition);
        };
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Position // instanceof handles nulls
                && value.equals(((Position) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

}
