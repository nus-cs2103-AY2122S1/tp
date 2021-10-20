package socialite.model.person;

import java.util.Date;
import java.util.HashMap;

public class Dates {
    public final HashMap<String, Date> value;

    public Dates() {
        value = new HashMap<>();
    }

    public Dates(HashMap<String, Date> dates) {
        value = new HashMap<>(dates);
    }

    public Dates(Dates originalDates) {
        value = new HashMap<>(originalDates.value);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Dates // instanceof handles nulls
                && value.equals(((Dates) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }
}
