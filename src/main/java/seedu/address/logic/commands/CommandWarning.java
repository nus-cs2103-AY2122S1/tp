package seedu.address.logic.commands;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_LAST_VISIT_DATE;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_VISIT_DATE;

public class CommandWarning {

    public static final CommandWarning FUTURE_LAST_VISIT_WARNING = new CommandWarning(MESSAGE_INVALID_LAST_VISIT_DATE);
    public static final CommandWarning PAST_NEXT_VISIT_WARNING = new CommandWarning(MESSAGE_INVALID_VISIT_DATE);
    public static final CommandWarning BOTH_VISIT_FIELDS_WARNING = new CommandWarning(
            MESSAGE_INVALID_LAST_VISIT_DATE + " " + MESSAGE_INVALID_VISIT_DATE);

    private final String value;

    public CommandWarning(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        CommandWarning that = (CommandWarning) o;

        return value != null ? value.equals(that.value) : that.value == null;
    }

    @Override
    public int hashCode() {
        return value != null ? value.hashCode() : 0;
    }
}
