package seedu.address.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * The ID field of a person. Used to distinguish between different Persons,
 * especially if they have the same name.
 */
public class Nric {

    public static final String MESSAGE_CONSTRAINTS =
            "IDs must have a length of less than 50";

    /*
     * The first character of the address must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid input.
     */
    public static final String VALIDATION_REGEX = "[^\\s].*";
    public static final int MAX_LENGTH = 50;

    public final String value;

    /**
     * Constructor for Nric object.
     */
    public Nric(String ic) {
        this.value = ic;
    }

    /**
     * Constructs an Id object using given Integer Id.
     *
     * @param ic the Nric of the guest, stored as a String.
     * @return a unique Nric object.
     */
    public static Nric of(String ic) {
        requireNonNull(ic);
        checkArgument(isValidNric(ic), MESSAGE_CONSTRAINTS);
        return new Nric(ic);
    }

    /**
     * Evaluates if an Nric provided is valid to be a Person's Nric.
     * In other words, it checks for null or whether the string is empty.
     *
     * @param test the String of a supposed Nric.
     * @return boolean to indicate if string is non-empty.
     */
    public static boolean isValidNric(String test) {
        return test.matches(VALIDATION_REGEX) && test.length() <= MAX_LENGTH;
    }

    /**
     * Returns the Nric object's value.
     * @return the String that the Nric object contains.
     */
    public String getValue() {
        return this.value;
    }

    @Override
    public String toString() {
        return this.value;
    }

    @Override
    public boolean equals(Object other) {
        return other == this
                || (other instanceof Nric
                && ((Nric) other).value.toLowerCase().equals(this.value.toLowerCase()));
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

}
