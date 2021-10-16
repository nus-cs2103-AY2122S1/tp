package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_DAY;

import java.util.Arrays;
import java.util.List;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.PersonAvailableOnDayPredicate;

/**
 * Splits members available on particular day to different Facilities.
 */
public class SplitCommand extends Command {
    public static final String COMMAND_WORD = "split";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": splits members into locations based on availability.\n"
            + "Parameters: " + "DAY\n"
            + "DAY must be an integer from 1 to 7\n"
            + "where 1 represents Monday, 2 represents Tuesday ... and 7 represents Sunday\n"
            + "Example: " + COMMAND_WORD + " 1";
    public static final String MESSAGE_SUCCESS = "Members have been split for %1$s";

    private final int dayNumber;

    /**
     * Creates a SplitCommand object to split the members.
     *
     * @param dayNumber Day to split members for.
     */
    public SplitCommand(int dayNumber) {
        requireNonNull(dayNumber);
        this.dayNumber = dayNumber;
    }


    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        PersonAvailableOnDayPredicate predicate = new PersonAvailableOnDayPredicate(dayNumber);
        model.split(predicate);
        return new CommandResult(String.format(MESSAGE_SUCCESS, dayNumber));
    }

    @Override
    public boolean equals(Object obj) {
        return (obj == this)
                || (obj instanceof SplitCommand
                && dayNumber == ((SplitCommand) obj).dayNumber);
    }
}
