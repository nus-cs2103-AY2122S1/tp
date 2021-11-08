package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MEMBER;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_MEMBERS;

import seedu.address.model.Model;

/**
 * Lists all members in the ezFoodie to the user.
 */
public class ListCommand extends Command {

    /**
     * Stands for list command.
     */
    public static final String COMMAND_WORD = "list";

    /**
     * Stands for the message of list command.
     */
    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Lists out all members.\n"
            + "Parameters: " + PREFIX_MEMBER + "\n"
            + "Example: " + COMMAND_WORD + " " + PREFIX_MEMBER;

    /**
     * Stands for the message success listed.
     */
    public static final String MESSAGE_SUCCESS = "Listed all members.";

    /**
     * Overrides and Executes the model.
     *
     * @param model {@code Model} which the command should operate on.
     * @return CommandResult related to list command.
     */
    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredMemberList(PREDICATE_SHOW_ALL_MEMBERS);
        return new CommandResult(MESSAGE_SUCCESS);
    }

    /**
     * Overrides the equals method.
     */
    @Override
    public boolean equals(Object other) {
        return other instanceof ListCommand;
    }
}
