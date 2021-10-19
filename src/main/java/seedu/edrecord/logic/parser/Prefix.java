package seedu.edrecord.logic.parser;

/**
 * A prefix that marks the beginning of an argument in an arguments string.
 * E.g. 't/' in 'add James t/ friend'.
 */
public class Prefix {
    private final String prefix;
    private final PrefixIsOptional isOptional;

    /**
     * Constructs an {@code Prefix} that is not optional by default.
     * @param prefix The prefix's value.
     */
    public Prefix(String prefix) {
        this.prefix = prefix;
        this.isOptional = PrefixIsOptional.NO;
    }
    /**
     * Constructs an {@code Prefix} that specifies if it's optional.
     * @param prefix The prefix's value.
     * @param isOptional An enum to indicate if prefix is optional.
     */
    public Prefix(String prefix, PrefixIsOptional isOptional) {
        this.prefix = prefix;
        this.isOptional = isOptional;
    }

    public PrefixIsOptional getPrefixIsOptional() {
        return this.isOptional;
    }

    public String getPrefix() {
        return prefix;
    }

    public String toString() {
        return getPrefix();
    }

    @Override
    public int hashCode() {
        return prefix == null ? 0 : prefix.hashCode();
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
}
