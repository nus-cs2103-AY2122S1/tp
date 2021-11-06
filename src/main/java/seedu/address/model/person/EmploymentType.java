package seedu.address.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

import java.util.ArrayList;

public class EmploymentType {

    public enum Type {
        FULL_TIME("Full time"),
        PART_TIME("Part time"),
        TEMPORARY("Temporary"),
        INTERNSHIP("Internship");

        private final String term;
        Type(String term) {
            this.term = term;
        }

        public static ArrayList<String> getTerms() {
            ArrayList<String> terms = new ArrayList<>();

            for (Type type: Type.values()) {
                terms.add(type.term);
            }

            return terms;
        }

        public static String getRegex() {
            StringBuilder regex = new StringBuilder("(?i)\\b(?:");
            for (Type type: Type.values()) {
                regex.append(type.term);
                regex.append("|");
            }
            regex.append("\\w+)\\b");
            return regex.toString();
        }
    }

    public static final String MESSAGE_CONSTRAINTS = "Employment type can only be one of the following: "
            + "Full time, Part time, Temporary or Internship";

    public static final String FIND_MESSAGE_CONSTRAINTS = "You can only search for keywords that one or more of "
            + "the following employment types start with: Full time, Part time, Temporary or Internship";

    public final String employmentType;

    /**
     * Constructs a {@code EmploymentType}.
     *
     * @param employmentType A valid employment type.
     */
    public EmploymentType(String employmentType) {
        requireNonNull(employmentType);
        checkArgument(isValidEmploymentType(employmentType), MESSAGE_CONSTRAINTS);
        this.employmentType = getCorrectCapitalization(employmentType);
    }

    /**
     * Returns true if a given string is a valid employment type.
     *
     * @param test String to be tested.
     * @return Boolean indicating if given string is a valid employment type.
     */
    public static boolean isValidEmploymentType(String test) {
        return test.equalsIgnoreCase(Type.FULL_TIME.term) || test.equalsIgnoreCase(Type.PART_TIME.term)
                || test.equalsIgnoreCase(Type.TEMPORARY.term) || test.equalsIgnoreCase(Type.INTERNSHIP.term);
    }

    @Override
    public String toString() {
        return employmentType;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof EmploymentType // instanceof handles nulls
                && employmentType.equals(((EmploymentType) other).employmentType)); // state check
    }

    @Override
    public int hashCode() {
        return employmentType.hashCode();
    }

    private String getCorrectCapitalization(String employmentType) {
        ArrayList<String> employmentTypes = EmploymentType.Type.getTerms();
        for (String et: employmentTypes) {
            if (et.equalsIgnoreCase(employmentType)) {
                return et;
            }
        }
        return null;
    }

}
