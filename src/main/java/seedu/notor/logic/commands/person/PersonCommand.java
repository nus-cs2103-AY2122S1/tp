package seedu.notor.logic.commands.person;

import java.util.Arrays;
import java.util.List;

import seedu.notor.commons.core.index.Index;
import seedu.notor.logic.commands.HelpCommand;
import seedu.notor.logic.commands.TargetedCommand;

public abstract class PersonCommand extends TargetedCommand {
    public static final String COMMAND_WORD = "person";
    public static final List<String> COMMAND_WORDS = Arrays.asList("person", "p");

    public static final String MESSAGE_USAGE = "Person commands target a group by INDEX - person (INDEX) /COMMANDWORD "
            + "[params]\n"
            + "except the create command, with this format - person (NAME) /create [params]\n"
            + "or the find/list command - person /COMMANDWORD [params].\n"
            + HelpCommand.MESSAGE_USAGE;

    protected final Index index;

    protected PersonCommand(Index index) {
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
