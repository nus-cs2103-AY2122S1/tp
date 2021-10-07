package seedu.address.model.student;

import java.util.ArrayList;


/**
 * Represents a Student's attendance in tApp.
 * Guarantees: immutable;
 */
public class Attendance {


    public final ArrayList<Integer> value;


    /**
     * Constructs an {@code Attendance}.
     *
     */
    public Attendance() {
        value = new ArrayList<>();
        for (int i = 0; i < 13; i++) {
            value.add(0);
        }
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Attendance // instanceof handles nulls
                && value.equals(((Attendance) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

}
