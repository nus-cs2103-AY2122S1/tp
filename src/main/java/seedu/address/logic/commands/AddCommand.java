package seedu.address.logic.commands;

import static seedu.address.logic.parser.CliSyntax.PREFIX_MEMBER;
import static seedu.address.logic.parser.CliSyntax.PREFIX_RESERVATION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TRANSACTION;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;

/**
 * Represents a add command to add member or transaction to ezFoodie.
 */
public abstract class AddCommand extends Command {

    /**
     * Stands for COMMAND WORD for add command.
     */
    public static final String COMMAND_WORD = "add";

    /**
     * Stands for output message.
     */
    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Adds a member or a transaction or a reservation to the ezFoodie.\n"
            + "With "
            + PREFIX_MEMBER + " (member details) or "
            + PREFIX_TRANSACTION + " (transaction details) or "
            + PREFIX_RESERVATION + " (reservation details)";

    /**
     * Executes method for addCommand class to execute model.
     *
     * @param model {@code Model} which the command should operate on.
     * @return CommandResult for execute addCommand.
     * @throws CommandException if the user input does not conform the expected format.
     */
    @Override
    public abstract CommandResult execute(Model model) throws CommandException;

}
