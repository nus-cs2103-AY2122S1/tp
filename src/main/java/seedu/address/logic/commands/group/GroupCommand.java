package seedu.address.logic.commands.group;

import seedu.address.logic.commands.Command;
import seedu.address.model.group.Group;

public abstract class GroupCommand extends Command {
    private Group target;
}
