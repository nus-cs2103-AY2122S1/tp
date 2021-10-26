package seedu.address.model.client;

import org.jetbrains.annotations.NotNull;

/**
 * An abstract class to allow the class extending it to be comparable
 * by the parsed number of the string representation.
 */
public abstract class NumberComparable<T> implements Comparable<T> {
    @Override
    public int compareTo(@NotNull T other) {
        int a;
        int b;
        try {
            a = Integer.parseInt(this.toString());
        } catch (NumberFormatException | NullPointerException e) {
            return 1;
        }

        try {
            b = Integer.parseInt(other.toString());
        } catch (NumberFormatException | NullPointerException e) {
            return 0;
        }

        return Integer.compare(a, b);
    }
}
