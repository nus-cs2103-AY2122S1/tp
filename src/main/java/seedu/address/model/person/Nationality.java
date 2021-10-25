package seedu.address.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;

/**
 * Represents a Person's nationality in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidNationality(String)}
 */
public class Nationality {

    public static final List<String> validNationalities = new ArrayList<>();
    public static final String MESSAGE_CONSTRAINTS = "Nationality can take any values, and it should not be blank";

    /*
     * The first character of the nationality must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid input.
     */
    public static final String VALIDATION_REGEX = "[^\\s].*";

    public final String value;

    /**
     * Constructs an {@code Nationality}.
     *
     * @param nationality A valid nationality.
     */
    public Nationality(String nationality) {
        requireNonNull(nationality);
        checkArgument(isValidNationality(nationality), MESSAGE_CONSTRAINTS);

        value = nationality;
    }

    /**
     * Returns true if a given string is inside the {@code validNationalities} list if {@code validNationalities} is
     * non-empty. Otherwise, it can take any values.
     */
    public static boolean isValidNationality(String test) {
        if (test.isEmpty()) {
            return true;
        }

        if (validNationalities.size() == 0) {
            readValidNationalities();
        }

        return validNationalities.size() > 0
                ? validNationalities.contains(test.trim().toLowerCase())
                : test.matches(VALIDATION_REGEX);
    }

    /**
     * Reads a txt file located in data/nationalities.txt containing a list of nationalities and
     * puts it into {@code validNationalities}
     */
    public static void readValidNationalities() {
        try {
            Scanner sc = new Scanner(new File("data/nationalities.txt"));

            while (sc.hasNext()) {
                validNationalities.add(sc.nextLine().toLowerCase());
            }

            sc.close();
        } catch (FileNotFoundException e) {
            System.out.println(e);
        }
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
        return value.hashCode();
    }

}
