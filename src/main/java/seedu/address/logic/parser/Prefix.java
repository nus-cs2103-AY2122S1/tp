package seedu.address.logic.parser;

/**
 * A prefix that marks the beginning of an argument in an arguments string.
 * E.g. '-t' in 'add James -t friend'.
 */
public class Prefix {
    private final String prefix;

    /**
     * Constructs a {@code Prefix} with the given {@code String}.
     */
    public Prefix(String prefix) {
        this.prefix = prefix;
    }

    /**
     * Gets prefix of command.
     */
    public String getPrefix() {
        return prefix;
    }

    public String toString() {
        return getPrefix();
    }

    /**
     * Overrides the hashCode method.
     */
    @Override
    public int hashCode() {
        return prefix == null ? 0 : prefix.hashCode();
    }

    /**
     * Overrides equals method.
     */
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
}
