package seedu.address.model.Tuition;

import static java.util.Objects.requireNonNull;

public class Counter {
    public final int counter;

    public Counter(int counter) {
        requireNonNull(counter);
        this.counter = counter;
    }

    @Override
    public String toString() {
        return counter + "";
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ClassLimit // instanceof handles nulls
                && counter == ((Counter) other).counter); // state check
    }
}
