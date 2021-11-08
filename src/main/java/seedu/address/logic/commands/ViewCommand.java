package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ID;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MEMBER;

import seedu.address.commons.core.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.member.IdContainsKeywordsPredicate;

/**
 * Views specific member details in eZFoodie, accessed by member ID.
 */
public class ViewCommand extends Command {

    /**
     * Stands for view command.
     */
    public static final String COMMAND_WORD = "show";

    /**
     * Stands for the message of open view window successfully.
     */
    public static final String SHOWING_VIEW_MESSAGE = "Opened view window.";

    /**
     * Stands for the message of show and view command.
     */
    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": View a specific member's details, "
            + "accessed by member ID.\n"
            + "Parameters:\n"
            + PREFIX_MEMBER + " " + PREFIX_ID + "ID\n"
            + "Example:\n"
            + COMMAND_WORD + " " + PREFIX_MEMBER + " " + PREFIX_ID + "00001";

    private final IdContainsKeywordsPredicate predicate;

    /**
     * Constructs the view command based on member ID predicate.
     *
     * @param predicate of member ID.
     */
    public ViewCommand(IdContainsKeywordsPredicate predicate) {
        this.predicate = predicate;
    }

    /**
     * Overrides and executes the model.
     *
     * @param model {@code Model} which the command should operate on.
     * @return CommandResult related to View command.
     * @throws CommandException if the user input does not conform the expected format.
     */
    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        model.updateFilteredMemberListForView(predicate);
        if (model.getUpdatedMemberListForView().size() > 0) {
            return new CommandResult(SHOWING_VIEW_MESSAGE, false, false, true, false);
        } else {
            throw new CommandException(Messages.MESSAGE_INVALID_MEMBER_DISPLAYED_ID);
        }
    }

    /**
     * Override the equals method.
     */
    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ViewCommand // instanceof handles nulls
                && predicate.equals(((ViewCommand) other).predicate)); // state check
    }
}
