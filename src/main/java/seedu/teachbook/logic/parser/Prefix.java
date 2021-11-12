package seedu.teachbook.logic.parser;

/**
 * A prefix that marks the beginning of an argument in an arguments string.
 * E.g. 't/' in 'add James t/ friend'.
 */
public class Prefix {
    private final String prefix;

    /**
     * Constructs a {@code Prefix} object.
     *
     * @param prefix Prefix string.
     */
    public Prefix(String prefix) {
        this.prefix = prefix;
    }

    /**
     * Returns the prefix.
     *
     * @return prefix.
     */
    public String getPrefix() {
        return prefix;
    }

    @Override
    public String toString() {
        return getPrefix();
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Prefix)) {
            return false;
        }
        if (obj == this) {
            return true;
        }

        Prefix otherPrefix = (Prefix) obj;
        return otherPrefix.getPrefix().equals(getPrefix());
    }

    @Override
    public int hashCode() {
        return prefix == null ? 0 : prefix.hashCode();
    }
}
