package seedu.notor.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.notor.logic.parser.CliSyntax.PREFIX_GROUP;

import seedu.notor.logic.commands.exceptions.CommandException;
import seedu.notor.model.Model;
import seedu.notor.model.group.SuperGroup;

public class GroupCreateCommand implements Command {

    public static final String COMMAND_WORD = "group_create";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Creates a group "
        + "Parameters: "
        + COMMAND_WORD
        + PREFIX_GROUP + "GROUP\n"
        + "Example: " + COMMAND_WORD
        + PREFIX_GROUP + "Orbital";

    public static final String MESSAGE_SUCCESS = "Group %s created";
    public static final String MESSAGE_DUPLICATE_GROUP = "Group already exists";

    public final SuperGroup toAdd;
    /**
     * Creates a new GroupCommand that add the specified group.
     * @param toAdd the SuperGroup that will be created and added to the list.
     */
    public GroupCreateCommand(SuperGroup toAdd) {
        this.toAdd = toAdd;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (model.hasSuperGroup(toAdd)) {
            throw new CommandException(MESSAGE_DUPLICATE_GROUP);
        }

        model.addSuperGroup(toAdd);
        return new CommandResult(String.format(MESSAGE_SUCCESS, toAdd));
    }
}
