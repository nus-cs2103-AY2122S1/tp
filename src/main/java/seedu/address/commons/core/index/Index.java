package seedu.address.commons.core.index;

import java.util.Comparator;
import java.util.Objects;

/**
 * Represents a zero-based or one-based index.
 *
 * {@code Index} should be used right from the start (when parsing in a new user input), so that if the current
 * component wants to communicate with another component, it can send an {@code Index} to avoid having to know what
 * base the other component is using for its index. However, after receiving the {@code Index}, that component can
 * convert it back to an int if the index will not be passed to a different component again.
 */
public class Index {
    private int zeroBasedIndex;

    /**
     * Index can only be created by calling {@link Index#fromZeroBased(int)} or
     * {@link Index#fromOneBased(int)}.
     */
    private Index(int zeroBasedIndex) {
        if (zeroBasedIndex < 0) {
            throw new IndexOutOfBoundsException();
        }

        this.zeroBasedIndex = zeroBasedIndex;
    }

    public int getZeroBased() {
        return zeroBasedIndex;
    }

    public int getOneBased() {
        return zeroBasedIndex + 1;
    }

    /**
     * Checks if current index is greater than the given index
     * @param other to compare with
     * @return true if this is greater than the other index
     */
    public boolean isGreaterThan(Index other) {
        return zeroBasedIndex > other.getZeroBased();
    }

    /**
     * Checks if current index is lesser than the given index
     * @param other to compare with
     * @return true if this is lesser than the other index
     */
    public boolean isLesserThan(Index other) {
        return zeroBasedIndex < other.getZeroBased();
    }

    /**
     * Creates a new {@code Index} using a zero-based index.
     */
    public static Index fromZeroBased(int zeroBasedIndex) {
        return new Index(zeroBasedIndex);
    }

    /**
     * Creates a new {@code Index} using a one-based index.
     */
    public static Index fromOneBased(int oneBasedIndex) {
        return new Index(oneBasedIndex - 1);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Index // instanceof handles nulls
                && zeroBasedIndex == ((Index) other).zeroBasedIndex); // state check
    }

    @Override
    public int hashCode() {
        return Objects.hash(zeroBasedIndex);
    }

    /**
     * Comparator class for sorting index in descending order
     */
    public static class SortDescending implements Comparator<Index> {

        @Override
        public int compare(Index first, Index second) {
            if (first.isGreaterThan(second)) {
                return -1;
            } else if (first.isLesserThan(second)) {
                return 1;
            } else {
                return 0;
            }
        }
    }
}
