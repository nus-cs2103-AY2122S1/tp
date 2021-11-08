package seedu.notor.logic.commands.group;

import java.util.Arrays;
import java.util.List;

import seedu.notor.commons.core.index.Index;
import seedu.notor.logic.commands.HelpCommand;
import seedu.notor.logic.commands.TargetedCommand;

public abstract class GroupCommand extends TargetedCommand {
    public static final String MESSAGE_USAGE = "Group commands target a group by INDEX - group (INDEX) /COMMANDWORD "
            + "[parameters]\n"
            + "except the create command, with this format - group (NAME) /create [g:supergroupIndex]\n"
            + "or the find/list command - group /COMMANDWORD (params).\n"
            + HelpCommand.MESSAGE_USAGE;
    public static final String COMMAND_WORD = "group";
    public static final List<String> COMMAND_WORDS = Arrays.asList("group", "g");
    protected final Index index;

    public GroupCommand(Index index) {
        this.index = index;
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof GroupCommand)) {
            return false;
        }

        // state check
        GroupCommand e = (GroupCommand) other;
        return e.index == index || index.equals(e.index);
    }
}
