package seedu.address.model.person;

import static java.util.Objects.requireNonNull;

public class Remark {
    public final String value;

    public Remark(String remark) {
        requireNonNull(remark);
        value = remark;
    }

    @Override
    public boolean equals(Object other) {
        //short circuit if same object
        if (other == this) {
            return true;
        }

        //instanceof handles nulls
        if (!(other instanceof Remark)) {
            return false;
        }

        // state check
        Remark e = (Remark) other;
        return value.equals(e.value);
    }
}
