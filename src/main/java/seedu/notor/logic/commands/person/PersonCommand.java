package seedu.notor.logic.commands.person;

import seedu.notor.commons.core.index.Index;
import seedu.notor.logic.commands.TargetedCommand;

public abstract class PersonCommand extends TargetedCommand {
    public static final String COMMAND_WORD = "person";
    protected final Index index;

    public PersonCommand(Index index) {
        this.index = index;
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof PersonCommand)) {
            return false;
        }

        // state check
        PersonCommand e = (PersonCommand) other;
        return index.equals(e.index);
    }
}
