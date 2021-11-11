package seedu.intern.model.applicant;

import static java.util.Objects.requireNonNull;
import static seedu.intern.commons.util.AppUtil.checkArgument;

/**
 * Represents a Job in Intern Watcher.
 * Guarantees: immutable; name is valid as declared in {@link #isValidJobName(String)}
 */
public class Job {

    public static final String MESSAGE_CONSTRAINTS =
            "Job Names should only contain alphabet characters and spaces, and it should not be blank";
    public static final String VALIDATION_REGEX = "[a-zA-Z][a-zA-Z ]*";

    public final String jobName;

    /**
     * Constructs a {@code Job}.
     *
     * @param jobName A valid job name.
     */
    public Job(String jobName) {
        requireNonNull(jobName);
        checkArgument(isValidJobName(jobName), MESSAGE_CONSTRAINTS);
        this.jobName = jobName;
    }

    /**
     * Returns true if a given string is a valid job name.
     */
    public static boolean isValidJobName(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Job // instanceof handles nulls
                && jobName.equals(((Job) other).jobName)); // state check
    }

    @Override
    public int hashCode() {
        return jobName.hashCode();
    }

    /**
     * Format state as text for viewing.
     */
    public String toString() {
        return jobName;
    }

}
