package seedu.address.commons.core.index;

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
     *
     * @param zeroBasedIndex Initial sequence of interger starting with zero.
     */
    private Index(int zeroBasedIndex) {
        if (zeroBasedIndex < 0) {
            throw new IndexOutOfBoundsException();
        }

        this.zeroBasedIndex = zeroBasedIndex;
    }

    /**
     * This method returns a zero based index.
     *
     * @return int representation of the zeroBasedIndex.
     */
    public int getZeroBased() {
        return zeroBasedIndex;
    }

    /**
     * This method returns a one based index.
     *
     * @return int representation of the oneBasedIndex.
     */
    public int getOneBased() {
        return zeroBasedIndex + 1;
    }

    /**
     * Creates a new {@code Index} using a zero-based index.
     *
     * @param zeroBasedIndex Initial sequence of interger starting with zero.
     * @return Index containing the zeroBasedIndex.
     */
    public static Index fromZeroBased(int zeroBasedIndex) {
        return new Index(zeroBasedIndex);
    }

    /**
     * Creates a new {@code Index} using a one-based index.
     *
     * @param oneBasedIndex Initial sequence of interger starting with one.
     * @return Index containing the oneBasedIndex.
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
}
