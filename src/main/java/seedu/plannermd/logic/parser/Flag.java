package seedu.plannermd.logic.parser;

import static java.util.Objects.requireNonNull;

/**
 * A flag that specifies the options for a command.
 * E.g. '-d' in 'tag -d id/1'.
 */
public class Flag {

    private final String flag;

    /**
     * Constructs a Flag.
     *
     * @param flag specifies the options for a command
     */
    public Flag(String flag) {
        requireNonNull(flag);

        this.flag = flag;
    }

    public String getFlag() {
        return flag;
    }

    @Override
    public String toString() {
        return getFlag();
    }

    @Override
    public int hashCode() {
        return flag.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Flag)) {
            return false;
        }
        if (obj == this) {
            return true;
        }

        Flag otherFlag = (Flag) obj;
        return otherFlag.getFlag().equals(getFlag());
    }
}
