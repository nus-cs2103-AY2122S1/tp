package seedu.address.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;

import seedu.address.MainApp;

/**
 * Represents a Person's nationality in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidNationality(String)}
 */
public class Nationality {

    public static final List<String> VALID_NATIONALITIES = readValidNationalities();
    public static final String MESSAGE_CONSTRAINTS = "Nationality can be any valid nationality spelled in full.\n"
            + "Leaving it blank will remove the Nationality field.";
    public static final String MESSAGE_NOT_FOUND = "The nationality entered is not valid. Please try again";

    /*
     * The first character of the nationality must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid input.
     */
    public static final String VALIDATION_REGEX = "[^\\s].*";

    private static final HashSet<String> NATIONALITIES_SET = new HashSet<>(VALID_NATIONALITIES);

    public final String value;

    /**
     * Constructs an {@code Nationality}.
     *
     * @param nationality A valid nationality.
     */
    public Nationality(String nationality) {
        requireNonNull(nationality);
        checkArgument(isValidNationality(nationality), MESSAGE_CONSTRAINTS);

        value = nationality.equals("")
                ? nationality
                : nationality.substring(0, 1).toUpperCase() + nationality.substring(1).toLowerCase();
    }

    /**
     * Returns true if a given string is inside the {@code validNationalities} list if {@code validNationalities} is
     * non-empty. Otherwise, it can take any values.
     */
    public static boolean isValidNationality(String test) {
        if (test.isEmpty()) {
            return true;
        }

        return NATIONALITIES_SET.contains(test.trim().toLowerCase());

        // Previous validating mechanism
        /*
        return VALID_NATIONALITIES.size() > 0
                ? VALID_NATIONALITIES.contains(test.trim().toLowerCase())
                : test.matches(VALIDATION_REGEX);
        */
    }

    // This method is for debugging
    private static boolean isNationalitySetEmpty() {
        return NATIONALITIES_SET.isEmpty();
    }

    /**
     * Reads a txt file located in data/nationalities.txt containing a list of nationalities and
     * puts it into {@code validNationalities}
     */
    public static List<String> readValidNationalities() {
        List<String> tmp = new ArrayList<>();

        // WARNING: MainApp.class.getResourceAsStream could return null
        Scanner sc = new Scanner(MainApp.class.getResourceAsStream("/data/nationalities.txt"));

        while (sc.hasNext()) {
            tmp.add(sc.nextLine().toLowerCase());
        }
        sc.close();

        return tmp;
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Nationality // instanceof handles nulls
                && value.equals(((Nationality) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.toLowerCase().hashCode();
    }

}
