package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PERSONS;

import java.util.function.Predicate;

import seedu.address.model.Model;
import seedu.address.model.person.Person;

/**
 * Lists all persons in the address book to the user.
 */
public class ListCommand extends Command {

    public static final String COMMAND_WORD = "list";

    public static final String MESSAGE_SUCCESS_ALL = "Listed all persons";
    public static final String MESSAGE_SUCCESS_INCOMING = "Listed all persons with existing visits in next %1$s.";

    public static final Predicate<Person> PREDICATE_HAS_VISIT_THIS_MONTH = (Person::hasVisitThisMonth);
    public static final Predicate<Person> PREDICATE_HAS_VISIT_THIS_WEEK = (Person::hasVisitThisWeek);

    // TODO
    public static final String MESSAGE_USAGE = "All or incoming";

    private final boolean isIncoming;
    private final boolean isNextMonth;

    /**
     * @param isIncoming whether to display all or only those with incoming visits
     * @param isNextMonth whether the incoming duration is next month or next week, given {@Code isIncoming} is true.
     */
    public ListCommand(boolean isIncoming, boolean isNextMonth) {
        this.isIncoming = isIncoming;
        this.isNextMonth = isNextMonth;
    }

    /**
     * Overloaded constructor for default case.
     */
    public ListCommand() {
        this(false, false);
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        if (!isIncoming) {
            model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
            return new CommandResult(MESSAGE_SUCCESS_ALL);
        } else if (isNextMonth) {
            model.updateFilteredPersonList(Person::hasVisitThisMonth);
            return new CommandResult(String.format(MESSAGE_SUCCESS_INCOMING, "month"));
        } else {
            model.updateFilteredPersonList(Person::hasVisitThisWeek);
            return new CommandResult(String.format(MESSAGE_SUCCESS_INCOMING, "week"));
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ListCommand that = (ListCommand) o;

        if (isIncoming != that.isIncoming) return false;
        return isNextMonth == that.isNextMonth;
    }

}
