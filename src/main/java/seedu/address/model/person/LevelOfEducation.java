package seedu.address.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

import java.util.ArrayList;

/**
 * Represents an Applicant's level of education in RecruitIn.
 */
public class LevelOfEducation {

    /**
     * Enumeration of the possible levels of education supported by RecruitIn.
     */
    public enum Education {
        ELEMENTARY("Elementary"),
        MIDDLE_SCHOOL("Middle School"),
        HIGH_SCHOOL("High School"),
        UNIVERSITY("University"),
        BACHELORS("Bachelors"),
        MASTERS("Masters"),
        PHD("PhD");

        /** The level of education of a Person. */
        private final String educationLevel;

        /**
         * Constructs a {@code Education}.
         *
         * @param educationLevel The level of education of a Person.
         */
        Education(String educationLevel) {
            this.educationLevel = educationLevel;
        }

        /**
         * Gets all the types of educationlevel.
         *
         * @return An array containing all the types of educationlevel.
         */
        public static ArrayList<String> getEducationLevels() {
            ArrayList<String> educationLevels = new ArrayList<>();

            for (Education education: Education.values()) {
                educationLevels.add(education.educationLevel);
            }

            return educationLevels;
        }

        /**
         * Gets the regex to compare LevelOfEducation inputs to.
         *
         * @return The string to compare LevelOfEducation inputs to.
         */
        public static String getRegex() {
            StringBuilder regex = new StringBuilder("(?i)\\b(?:");
            for (Education education: Education.values()) {
                regex.append(education.educationLevel);
                regex.append("|");
            }
            regex.append("\\w+)\\b");
            return regex.toString();
        }
    }

    public static final String MESSAGE_CONSTRAINTS =
            "Level of Education should only be one of the following: Elementary, Middle School,"
                    + " High School, University, Bachelors, Masters or PhD";

    public static final String FIND_MESSAGE_CONSTRAINTS = "You can only search for keywords that one or more of "
            + "the following levels of education start with: Elementary, Middle School, High School, University,"
            + " Bachelors, Masters or PhD";

    public final String levelOfEducation;

    /**
     * Constructs a {@code LevelOfEducation}.
     *
     * @param levelOfEducation A valid level of education.
     */
    public LevelOfEducation(String levelOfEducation) {
        requireNonNull(levelOfEducation);
        checkArgument(isValidLevelOfEducation(levelOfEducation), MESSAGE_CONSTRAINTS);
        this.levelOfEducation = getCorrectCapitalization(levelOfEducation);
    }

    /**
     * Returns true if a given string is a valid level of education.
     *
     * @param test String to be tested.
     * @return Boolean indicating if given string is a valid level of education.
     */
    public static boolean isValidLevelOfEducation(String test) {
        return test.equalsIgnoreCase(Education.ELEMENTARY.educationLevel)
                || test.equalsIgnoreCase(Education.MIDDLE_SCHOOL.educationLevel)
                || test.equalsIgnoreCase(Education.HIGH_SCHOOL.educationLevel)
                || test.equalsIgnoreCase(Education.UNIVERSITY.educationLevel)
                || test.equalsIgnoreCase(Education.BACHELORS.educationLevel)
                || test.equalsIgnoreCase(Education.MASTERS.educationLevel)
                || test.equalsIgnoreCase(Education.PHD.educationLevel);
    }

    @Override
    public String toString() {
        return levelOfEducation;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof LevelOfEducation // instanceof handles nulls
                && levelOfEducation.equals(((LevelOfEducation) other).levelOfEducation)); // state check
    }

    @Override
    public int hashCode() {
        return levelOfEducation.hashCode();
    }

    /**
     * Converts the given input to match the same capitalisation as the {@code Education} enums.
     *
     * @param levelOfEducation Given input to initialise a LevelOfEducation.
     * @return The same input string except it is capitalised as in the Education enums.
     */
    private String getCorrectCapitalization(String levelOfEducation) {
        ArrayList<String> levelsOfEducation = LevelOfEducation.Education.getEducationLevels();
        for (String l: levelsOfEducation) {
            if (l.equalsIgnoreCase(levelOfEducation)) {
                return l;
            }
        }
        return null;
    }
}
