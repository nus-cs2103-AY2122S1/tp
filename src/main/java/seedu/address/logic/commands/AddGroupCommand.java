package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_GROUP;
import static seedu.address.logic.parser.CliSyntax.PREFIX_REPO;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.logic.parser.CliSyntax.PREFIX_YEAR;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.group.Group;

/**
 * Adds a group to the group list in tApp.
 */
public class AddGroupCommand extends Command {

    public static final String COMMAND_WORD = "addGroup";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a group to tApp group list. "
            + "Parameters: "
            + PREFIX_GROUP + "NAME "
            + "[" + PREFIX_REPO + "REPO_NAME] "
            + "[" + PREFIX_YEAR + "YEAR] "
            + "[" + PREFIX_TAG + "TAG]...\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_GROUP + "W14-4 "
            + PREFIX_YEAR + "AY2122S1 "
            + PREFIX_REPO + "tp "
            + PREFIX_TAG + "tApp";

    public static final String MESSAGE_SUCCESS = "New group added: %1$s";
    public static final String MESSAGE_DUPLICATE_GROUP = "This group already exists in the tApp group list.";

    public final Group toAdd;

    /**
     * Creates an AddCommand to add the specified {@code Student}
     */
    public AddGroupCommand(Group group) {
        requireNonNull(group);
        toAdd = group;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        if (model.hasGroup(toAdd)) {
            throw new CommandException(MESSAGE_DUPLICATE_GROUP);
        }

        model.addGroup(toAdd);
        return new CommandResult(String.format(MESSAGE_SUCCESS, toAdd));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AddGroupCommand // instanceof handles nulls
                && toAdd.equals(((AddGroupCommand) other).toAdd));
    }
}
