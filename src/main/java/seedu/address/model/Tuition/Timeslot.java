package seedu.address.model.Tuition;

import static java.util.Objects.requireNonNull;

public class Timeslot {
    public final String time;

    public Timeslot(String time) {
        requireNonNull(time);
        this.time = time;
    }

    @Override
    public String toString() {
        return time;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ClassLimit // instanceof handles nulls
                && time == ((Timeslot) other).time); // state check
    }
}
