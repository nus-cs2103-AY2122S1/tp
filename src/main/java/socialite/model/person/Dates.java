package socialite.model.person;

import java.util.HashMap;

public class Dates {
    private final HashMap<String, Date> value;

    public Dates() {
        value = new HashMap<>();
    }

    /**
     * Creates a new Dates object by copying the given object.
     * @param originalDates The {@code Dates} object to copy dates from.
     */
    public Dates(Dates originalDates) {
        if (originalDates == null) {
            value = new HashMap<>();
        } else {
            value = new HashMap<>(originalDates.value);
        }
    }

    public void addDate(Date date) {
        value.put(date.getName(), date);
    }

    public HashMap<String, Date> get() {
        return this.value;
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
