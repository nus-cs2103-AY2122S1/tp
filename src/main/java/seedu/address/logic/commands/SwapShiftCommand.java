package seedu.address.logic.commands;

import static seedu.address.logic.parser.CliSyntax.PREFIX_DASH_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DAY_SHIFT;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;

/**
 * Swaps shifts between 2 staffs.
 */
public class SwapShiftCommand extends Command {
    public static final String COMMAND_WORD = "swapShift";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": swaps shifts between 2 staffs identified "
            + "using their names. You have to input exactly 2 names and 2 shifts.\n\n"
            + "NOTE: The staff identified using the first name is associated with the first shift and the staff "
            + "identified using the second name is associated with the second shift. Do take note of the order!\n\n"
            + "Parameters: (2 of each)\n"
            + PREFIX_DASH_NAME + " NAME\n"
            + PREFIX_DAY_SHIFT + "monday-1\n\n"
            + "Examples:\n"
            + COMMAND_WORD + " -n Alex Yeoh d/monday-1 -n David Li d/friday-0\n\n"
            + COMMAND_WORD + " -n Alex Yeoh -n David Li d/tuesday-0 d/wednesday-1";

    @Override
    public CommandResult execute(Model model) throws CommandException {
        return null;
    }
}
