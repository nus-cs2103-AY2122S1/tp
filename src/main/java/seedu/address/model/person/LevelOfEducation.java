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
         * Constructor for the Education Enumeration.
         *
         * @param educationLevel The level of education of a Person.
         */
        Education(String educationLevel) {
            this.educationLevel = educationLevel;
        }

        public static ArrayList<String> getEducationLevels() {
            ArrayList<String> educationLevels = new ArrayList<>();

            for (Education education: Education.values()) {
                educationLevels.add(education.educationLevel);
            }

            return educationLevels;
        }

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
