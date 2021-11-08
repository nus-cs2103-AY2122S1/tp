package seedu.address.logic.position;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_POSITION_STATUS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TITLE;

import seedu.address.commons.core.Messages;
import seedu.address.logic.Command;
import seedu.address.logic.CommandResult;
import seedu.address.model.Model;
import seedu.address.model.position.FindPositionCommandPredicate;



/**
 * Finds and lists all persons in HR Manager whose name contains any of the argument keywords.
 * Keyword matching is case insensitive.
 */
public class FindPositionCommand extends Command {

    public static final String COMMAND_WORD = "find_p";

    public static final String MESSAGE_SUCCESS = "Listed all found positions";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Finds all positions that contain any of "
            + "the specified keywords (case-insensitive) and displays them as a list with index numbers.\n"
            + "Parameters: "
            + "[" + PREFIX_TITLE + "TITLE]... "
            + "[" + PREFIX_POSITION_STATUS + "POSITION STATUS]...\n"
            + "Example: " + COMMAND_WORD + " " + PREFIX_TITLE + "accountant";
    private final FindPositionCommandPredicate predicate;



    public FindPositionCommand(FindPositionCommandPredicate predicate) {
        this.predicate = predicate;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredPositionList(predicate);
        return new CommandResult(
                String.format(Messages.MESSAGE_POSITIONS_LISTED_OVERVIEW, model.getFilteredPositionList().size()),
                                CommandResult.CommandType.FIND_P);

    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof FindPositionCommand // instanceof handles nulls
                && predicate.equals(((FindPositionCommand) other).predicate)); // state check
    }

}
