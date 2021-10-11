package seedu.address.logic.commands;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Rating;

/**
 * Changes the rating of an existing person in the address book.
 */
public class RateCommand extends Command {

    public static final String COMMAND_WORD = "rate";

    public static final String MESSAGE_USAGE = COMMAND_WORD
        + ": Edits the rating of the person identified "
        + "by the index number used in the last person listing. "
        + "Existing rating will be overwritten by the input.\n"
        + "Parameters: INDEX (must be a positive integer) \n"
        + "ra/ [RATING] (must be an integer between 1 to 5 inclusive) \n"
        + "Example: " + COMMAND_WORD + " 1 "
        + "ra/ 5";

    public static final String MESSAGE_ARGUMENTS = "Index: %1$d, Remark: %2$s";

    private final Index index;
    private final Rating rating;

    /**
     * @param index of the person in the filtered person list to edit the remark
     * @param rating of the person to be updated to
     */
    public RateCommand(Index index, Rating rating) {
        requireAllNonNull(index, rating);

        this.index = index;
        this.rating = rating;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        throw new CommandException(String.format(MESSAGE_ARGUMENTS, index.getOneBased(), rating));
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof RateCommand)) {
            return false;
        }

        // state check
        RateCommand e = (RateCommand) other;
        return index.equals(e.index)
            && rating.equals(e.rating);
    }
}
