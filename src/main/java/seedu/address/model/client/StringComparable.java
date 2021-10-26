package seedu.address.model.client;

import org.jetbrains.annotations.NotNull;

/**
 * An abstract class to allow the class extending it to be comparable by their String representation.
 */
public abstract class StringComparable<T> implements Comparable<T> {
    @Override
     public int compareTo(@NotNull T other) {
        String a = this.toString();
        String b = other.toString();
        if (a.isEmpty()) {
            return 1;
        }

        if (b.isEmpty()) {
            return 0;
        }

        return a.compareToIgnoreCase(b);
    }
}
