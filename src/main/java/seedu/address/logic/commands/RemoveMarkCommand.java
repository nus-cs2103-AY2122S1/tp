package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DAY_SHIFT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_INDEX;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;

import javafx.collections.transformation.FilteredList;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Period;
import seedu.address.model.person.Person;
import seedu.address.model.person.PersonContainsFieldsPredicate;

/**
 * Class representing the command to remove a mark.
 */
public class RemoveMarkCommand extends Command {

    public static final String COMMAND_WORD = "unmark";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Used to remove the marking of an absentee!\n"
            + "e.g. " + COMMAND_WORD + " " + PREFIX_INDEX + "INDEX"
            + " " + PREFIX_DAY_SHIFT + "DATE" + ",\n"
            + COMMAND_WORD + " " + PREFIX_NAME + "NAME"
            + PREFIX_DAY_SHIFT + "DATE" + " " + PREFIX_DAY_SHIFT + "DATE";
    public static final String NO_STAFF_SATISFIES_QUERY = "No one satisfies the conditions specified";
    public static final String STAFF_UNMARKED = "Staff unmarked: %1$s";

    private final PersonContainsFieldsPredicate predicate;
    private final int index;
    private final Period period;

    /**
     * Constructs an {@code RemoveMarkCommand} to indicate that a person who satisfies
     * the {@code PersonContainsFieldsPredicate} has been marked as working
     * in {@code period}.
     */
    public RemoveMarkCommand(PersonContainsFieldsPredicate predicate, Period period) {
        index = -1;
        this.predicate = predicate;
        this.period = period;
    }

    /**
     * Constructs an {@code RemoveMarkCommand} to indicate that a person who satisfies the
     * {@code PersonContainsFieldsPredicate} has been marked as working in
     * {@code period}.
     */
    public RemoveMarkCommand(PersonContainsFieldsPredicate predicate, Index index, Period period) {
        this.index = index.getZeroBased();
        this.predicate = predicate;
        this.period = period;
    }


    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        if (index != -1) {
            return executeIndex(model);
        }
        FilteredList<Person> toEdit = model.getUnFilteredPersonList()
                .filtered(this.predicate);
        for (Person p : toEdit) {
            checkPerson(p);
        }
        return new CommandResult(String.format(STAFF_UNMARKED, toEdit));
    }

    private CommandResult executeIndex(Model model) throws CommandException {
        requireNonNull(model);
        Person toTest = model.getFilteredPersonList().get(index);
        requireNonNull(toTest);
        checkPerson(toTest);
        return new CommandResult(String.format(STAFF_UNMARKED, toTest));
    }

    private void checkPerson(Person toTest) throws CommandException {
        if (!this.predicate.test(toTest) || !toTest.unMark(period)) { //use short circuit to control
            throw new CommandException(NO_STAFF_SATISFIES_QUERY);
        }
    }
}
