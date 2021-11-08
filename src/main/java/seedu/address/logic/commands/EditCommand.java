package seedu.address.logic.commands;

import static seedu.address.logic.parser.CliSyntax.PREFIX_MEMBER;
import static seedu.address.logic.parser.CliSyntax.PREFIX_RESERVATION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TRANSACTION;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;

/**
 * Represents a edit command to add member or transaction to ezFoodie.
 */
public abstract class EditCommand extends Command {

    /**
     * Stands for edit command.
     */
    public static final String COMMAND_WORD = "edit";

    /**
     * Stands for the message of edit command.
     */
    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Edits a member or a transaction or a reservation in the ezFoodie.\n"
            + "With "
            + PREFIX_MEMBER + " (member details) or "
            + PREFIX_TRANSACTION + " (transaction details) or "
            + PREFIX_RESERVATION + " (reservation details)";

    /**
     * Overrides the executes command.
     *
     * @param model {@code Model} which the command should operate on.
     * @throws CommandException if the user input does not conform the expected format.
     */
    @Override
    public abstract CommandResult execute(Model model) throws CommandException;

}
