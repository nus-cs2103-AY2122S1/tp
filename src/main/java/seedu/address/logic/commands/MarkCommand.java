package seedu.address.logic.commands;

import static seedu.address.logic.parser.CliSyntax.PREFIX_DAY_SHIFT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_INDEX;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;

import java.util.List;
import java.util.stream.Collectors;

import javafx.collections.transformation.FilteredList;
import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Name;
import seedu.address.model.person.Period;
import seedu.address.model.person.Person;
import seedu.address.model.person.PersonContainsFieldsPredicate;

/**
 * Class representing the command for marking a person as absent.
 */
public class MarkCommand extends Command {
    public static final String COMMAND_WORD = "mark";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Used to mark someone as absent!\n"
            + "e.g. " + COMMAND_WORD + " " + PREFIX_INDEX + "INDEX"
            + " " + PREFIX_DAY_SHIFT + "DATE" + ",\n"
            + COMMAND_WORD + " " + PREFIX_NAME + "NAME"
            + PREFIX_DAY_SHIFT + "DATE" + " " + PREFIX_DAY_SHIFT + "DATE";

    public static final String DEFAULT_EXECUTION = "%1$d number of staff have been marked for the period %2$s\n"
            + "%3$s";

    private final Period period;
    private final PersonContainsFieldsPredicate predicate;
    private final int index;

    /**
     * Constructs an {@code MarkCommand} to indicate that a person who satisfies
     * the {@code PersonContainsFieldsPredicate} has been marked as not working
     * in {@code period}.
     */
    public MarkCommand(PersonContainsFieldsPredicate predicate, Period period) {
        this.period = period;
        this.predicate = predicate;
        this.index = -1;
    }

    /**
     * Constructs an {@code MarkCommand} to indicate that a person who is
     * at {@code index} during the execution of the command is not working during
     * {@code period}.
     */
    public MarkCommand(Index index, Period period, PersonContainsFieldsPredicate predicate) {
        this.period = period;
        this.predicate = predicate;
        this.index = index.getZeroBased();

    }

    @Override
    public CommandResult execute(Model model) throws CommandException {

        if (index != -1) {
            return executeIndex(model);

        }
        FilteredList<Person> toModify = model.getFilteredPersonList().filtered(predicate);

        int total = toModify.size();

        for (Person p : toModify) {
            model.setPerson(p, p.mark(period));
        }
        List<Name> names = toModify.stream()
                .map(staff -> staff.getName())
                .collect(Collectors.toList());
        return new CommandResult(String.format(DEFAULT_EXECUTION, total, period, names));



    }

    private CommandResult executeIndex(Model model) throws CommandException {
        if (index >= model.getFilteredPersonList().size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }
        Person staffToModify = model.getFilteredPersonList().get(index);
        Person changedStaff = staffToModify.mark(period);
        model.setPerson(staffToModify, changedStaff);
        return new CommandResult(String.format(DEFAULT_EXECUTION, 1, period, changedStaff));

    }
}
