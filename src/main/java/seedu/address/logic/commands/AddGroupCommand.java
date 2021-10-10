package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_GROUP;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ID;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.student.Group;

/**
 * Adds a group to the Source Control application.
 */
public class AddGroupCommand extends Command {

    public static final String COMMAND_WORD = "add group";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Creates a new group with the specified students (if any). "
            + "Parameters: "
            + PREFIX_GROUP + "<group_name> "
            + "[(" + PREFIX_NAME + "<student_name>" + " | "
            + PREFIX_ID + "<student_id>"  + ")]...\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_GROUP + "T01A"
            + PREFIX_NAME + "John Doe "
            + PREFIX_ID + "E0543948";

    public static final String MESSAGE_SUCCESS = "New added added: %1$s";
    public static final String MESSAGE_DUPLICATE_GROUP = "This group already exists in the address book";

    private final Group toAdd;

    /**
     * Creates an AddGroupCommand to add the specified {@code Group}
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
