package seedu.address.model.application;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

import java.util.Comparator;

/**
 * Represents an Application's priority in InternSHIP.
 * Guarantees: immutable; is valid as declared in {@link #isValidPriority(String)}
 */
public class Priority {

    public static final String MESSAGE_CONSTRAINTS = "Priority can only be 'Low' or 'Medium' or 'High'.";

    public final String value;

    /**
     * Constructs a {@code Priority}. Regardless of the capitalisation of the input String, it is reformatted to having
     * only the first letter capitalised before being initialised as the value of the Priority object.
     * (e.g. low is reformatted to Low, mEdIuM is reformatted to Medium)
     *
     */
    public Priority(String priority) {
        requireNonNull(priority);
        checkArgument(isValidPriority(priority), MESSAGE_CONSTRAINTS);
        value = priority.substring(0, 1).toUpperCase() + priority.substring(1).toLowerCase();
    }

    /**
     * Returns true if a given string is a valid priority. Capitalisation of characters is ignored.
     */
    public static boolean isValidPriority(String test) {
        return test.equalsIgnoreCase("low")
                || test.equalsIgnoreCase("medium")
                || test.equalsIgnoreCase("high");
    }

    /**
     * Returns a Comparator object that compares two applications by their priority.
     * The application with a higher priority is taken to be greater.
     *
     * @return Comparator object that compares applications by their priority.
     */
    public static Comparator<Application> getComparator() {
        return new Comparator<>() {
            //Solution below adapted from AY2122S1-CS2103T-T15-3
            @Override
            public int compare(Application application, Application otherApplication) {
                String priority = application.getPriority().value;
                String otherPriority = otherApplication.getPriority().value;

                String numericPriority = getNumericPriority(priority);
                String otherNumericPriority = getNumericPriority(otherPriority);

                return numericPriority.compareTo(otherNumericPriority);
            }

            private String getNumericPriority(String priority) {
                switch (priority) {
                case "High":
                    return "1";
                case "Medium":
                    return "2";
                case "Low":
                    return "3";
                default:
                    // Should not reach this line.
                }
                return "0"; // Should not reach this line.
            }
        };
    }

    @Override
    public String toString() {
        return value;
    }

    /**
     * Converts the text into emoji for GUI display
     * @return the corresponding emoji for each level of priority
     */
    public String toDisplayString() {
        if (value.equals("High")) {
            return "❗❗❗";
        } else if (value.equals("Medium")) {
            return "❗❗";
        } else {
            assert(value.equals("Low"));
            return "❗";
        }
    }

    @Override
    public boolean equals(Object other) {
        return other == this
                || (other instanceof Priority
                && value.equals(((Priority) other).value));
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }
}
