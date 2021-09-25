package seedu.address.model.Tuition;

import seedu.address.model.person.Classes;

import static java.util.Objects.requireNonNull;

public class ClassName {
    public final String name;

    public ClassName(String name) {
        requireNonNull(name);
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ClassLimit // instanceof handles nulls
                && name == ((ClassName) other).name); // state check
    }
}
