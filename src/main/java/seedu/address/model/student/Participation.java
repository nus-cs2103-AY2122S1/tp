package seedu.address.model.student;

import java.util.ArrayList;

public class Participation {

    public final ArrayList<Integer> value;

    /**
     * Constructs a {@code Participation}.
     *
     */
    public Participation() {
        value = new ArrayList<>();
        for (int i = 0; i < 13; i++) {
            value.add(0);
        }
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Participation // instanceof handles nulls
                && value.equals(((Participation) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }
}
