package seedu.address.logic.commands;

import javafx.collections.transformation.FilteredList;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Period;
import seedu.address.model.person.Person;
import seedu.address.model.person.PersonContainsFieldsPredicate;

/**
 * Class representing the command for marking a person as absent.
 */
public class MarkCommand extends Command {
    private static final String COMMAND_WORD = "mark";
    private static final String MESSAGE_USAGE = COMMAND_WORD + ": ";
    private static final String DEFAULT_EXECUTION = "$1%s number of staff have been marked for the period $2%s\n"
            + "$3%s";

    private enum List {
        OBSERVED, INTERNAL
    }

    private final List toModify;
    private Period period;
    private PersonContainsFieldsPredicate predicate;
    private Index index;

    /**
     * Constructs an {@code MarkCommand} to indicate that a person who satisfies
     * the {@code PersonContainsFieldsPredicate} has been marked as not working
     * in {@code} period.
     */
    public MarkCommand(PersonContainsFieldsPredicate predicate, Period period) {
        this.period = period;
        this.predicate = predicate;
        this.toModify = List.INTERNAL;
    }

    /**
     * Constructs an {@code MarkCommand} to indicate that a person who is
     * at {@code index} during the execution of the command is not working during
     * {@code period}.
     */
    public MarkCommand(Index index, Period period) {
        this.period = period;
        this.toModify = List.OBSERVED;

    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        FilteredList<Person> toModify;
        switch (this.toModify) {
        case OBSERVED:
            Person modifiedStaff = model.getFilteredPersonList().get(index.getZeroBased());
            toModify = model.getFilteredPersonList().filtered(p -> p.equals(modifiedStaff));
            break;
        case INTERNAL:
            toModify = model.getFilteredPersonList().filtered(predicate);
            break;
        default:
            throw new CommandException(MESSAGE_USAGE);
        }
        long total = toModify.stream()
                .map(staff -> staff.mark(this.period))
                .count();
        return new CommandResult(String.format(DEFAULT_EXECUTION, total, period, toModify));



    }
}
