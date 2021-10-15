package seedu.notor.logic.commands.group;

import seedu.notor.logic.commands.Command;
import seedu.notor.model.group.Group;

public abstract class GroupCommand implements Command {
    private Group target;
}
