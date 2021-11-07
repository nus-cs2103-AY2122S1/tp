package seedu.address.logic.commands;

import static seedu.address.logic.commands.RemoveMarkCommand.NO_STAFF_SATISFIES_QUERY;
import static seedu.address.logic.commands.RemoveMarkCommand.listToString;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DASH_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DASH_INDEX;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DASH_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DASH_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DASH_ROLE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DASH_SALARY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DASH_STATUS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DATE;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javafx.collections.transformation.FilteredList;
import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Period;
import seedu.address.model.person.Person;
import seedu.address.model.person.predicates.PersonContainsFieldsPredicate;

/**
 * Class representing the command for marking a person as absent.
 */
public class MarkCommand extends Command {

    public static final String COMMAND_WORD = "mark";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Used to mark someone as absent "
            + "for the input duration."
            + Messages.SHIFT_PERIOD_PARSING_DEFAULT + "\n\n"
            + "Parameters:\n"
            + "[" + PREFIX_DASH_INDEX + " INDEX] "
            + "[" + PREFIX_DASH_NAME + " NAME] "
            + "[" + PREFIX_DASH_PHONE + " PHONE] "
            + "[" + PREFIX_DASH_EMAIL + " EMAIL] "
            + "[" + PREFIX_DASH_SALARY + " SALARY] "
            + "[" + PREFIX_DASH_STATUS + " STATUS] "
            + "[" + PREFIX_DASH_ROLE + " ROLE]... "
            + Messages.DATE_RANGE_INPUT + "\n\n"
            + "Examples:\n"
            + COMMAND_WORD + " " + PREFIX_DASH_INDEX + "1"
            + " " + PREFIX_DATE + "2021-11-18\n"
            + COMMAND_WORD + " " + PREFIX_DASH_NAME + "Jace "
            + PREFIX_DATE + "2021-11-11" + " " + PREFIX_DATE + "2021-11-13";

    public static final String DEFAULT_EXECUTION = "For the period: \n%2$s\n\n%1$d staff have been marked:\n"
            + "%3$s";

    public static final String NOTHING_CHANGED = "For the input duration: "
            + "\n%1$s\n\nThe staff \"%2$s\" has already been marked.";
    public static final String NO_ONE_SATISFIES_QUERY = "The field(s) indicated is/are not "
            + "satisfied by any staff in Staff'd";

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
        if (total == 0) {
            throw new CommandException(NO_STAFF_SATISFIES_QUERY);
        }
        List<String> conflicts = new ArrayList<>();
        for (Person p : toModify) {
            if (p.mark(period).equals(p)) {
                conflicts.add(p.getName().toString());
            }
        }
        if (conflicts.size() != 0) {
            throw new CommandException(String.format(NOTHING_CHANGED, period, listToString(conflicts)));
        }
        for (Person p : toModify) {
            model.setPerson(p, p.mark(period));
        }
        List<String> names = toModify.stream()
                .map(staff -> staff.getName().toString())
                .collect(Collectors.toList());
        return new CommandResult(String.format(DEFAULT_EXECUTION, total, period, listToString(names)));
    }

    private CommandResult executeIndex(Model model) throws CommandException {
        if (index >= model.getFilteredPersonList().size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }
        Person staffToModify = model.getFilteredPersonList().get(index);
        if (!predicate.test(staffToModify)) {
            throw new CommandException(NO_ONE_SATISFIES_QUERY);
        }
        Person changedStaff = staffToModify.mark(period);
        if (staffToModify.equals(changedStaff)) {
            throw new CommandException(String.format(NOTHING_CHANGED, period, staffToModify.getName()));
        }
        model.setPerson(staffToModify, changedStaff);
        return new CommandResult(String.format(DEFAULT_EXECUTION, 1, period, changedStaff.getName()));

    }

    @Override
    public boolean equals(Object other) {
        return other != null
                && other instanceof MarkCommand
                && ((MarkCommand) other).period.equals(period)
                && ((MarkCommand) other).index == index
                && ((MarkCommand) other).predicate.equals(predicate);
    }
}
