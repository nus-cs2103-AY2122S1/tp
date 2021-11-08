package safeforhall.logic.commands.sort;

import static java.util.Objects.requireNonNull;
import static safeforhall.logic.parser.CliSyntax.PREFIX_ORDER;
import static safeforhall.logic.parser.CliSyntax.PREFIX_SORT;

import java.util.Comparator;

import safeforhall.logic.commands.Command;
import safeforhall.logic.commands.CommandResult;
import safeforhall.logic.commands.exceptions.CommandException;
import safeforhall.model.Model;
import safeforhall.model.person.Email;
import safeforhall.model.person.Faculty;
import safeforhall.model.person.LastDate;
import safeforhall.model.person.Name;
import safeforhall.model.person.Person;
import safeforhall.model.person.Phone;
import safeforhall.model.person.Room;
import safeforhall.model.person.VaccStatus;


/**
 * Sorts list of persons in the application.
 */
public class SortPersonCommand extends Command {

    public static final String COMMAND_WORD = "sort";
    public static final String PARAMETERS = "by/FIELD o/ORDER";

    public static final String MESSAGE_SUCCESS = "Residents have been successfully sorted.";
    public static final String ALLOWED_FIELDS = "FIELD should be one of the following: "
            + Name.FIELD + ", "
            + Email.FIELD + ", "
            + Room.FIELD + ", "
            + Phone.FIELD + ", "
            + Faculty.FIELD + ", "
            + VaccStatus.FIELD + ", "
            + LastDate.FET_FIELD + ", "
            + LastDate.COLLECTION_FIELD;

    public static final String ASCENDING = "a";
    public static final String DESCENDING = "d";
    public static final String ALLOWED_ORDER = "ORDER should be one of the following: " + ASCENDING + ", " + DESCENDING;

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Sort residents by field in "
            + "ascending or descending order. \n"
            + "Parameters: " + PREFIX_SORT + "FIELD " + PREFIX_ORDER + "ORDER \n"
            + ALLOWED_FIELDS + "\n"
            + ALLOWED_ORDER + "\n"
            + "Example: " + COMMAND_WORD + " " + PREFIX_SORT + Name.FIELD + " " + PREFIX_ORDER + ASCENDING;

    private final String field;
    private final String order;


    /**
     * Creates a SortPersonCommand to sort the list of residents.
     *
     * @param field The field to sort by.
     * @param order Ascending or descending order to sort.
     */
    public SortPersonCommand(String field, String order) {
        requireNonNull(field);
        requireNonNull(order);
        this.field = field;
        this.order = order;
    }

    /**
     * Executes the command and returns the result message.
     *
     * @param model {@code Model} which the command should operate on.
     * @return feedback message of the operation result for display
     * @throws CommandException If an error occurs during command execution.
     */
    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        Comparator<Person> comparator = getComparator();
        model.updateSortedPersonList(comparator);
        return new CommandResult(MESSAGE_SUCCESS);
    }


    /**
     * Creates a Comparator given the field and order for sort.
     * @return Comparator that sorts by the field and order specified.
     * @throws CommandException If field or order provided is invalid.
     */
    public Comparator<Person> getComparator() throws CommandException {
        Comparator<Person> comparator;
        switch (field) {
        case Name.FIELD:
            comparator = Comparator.comparing(Person::getName);
            break;
        case Email.FIELD:
            comparator = Comparator.comparing(Person::getEmail);
            break;
        case Room.FIELD:
            comparator = Comparator.comparing(Person::getRoom);
            break;
        case Phone.FIELD:
            comparator = Comparator.comparing(Person::getPhone);
            break;
        case Faculty.FIELD:
            comparator = Comparator.comparing(Person::getFaculty);
            break;
        case VaccStatus.FIELD:
            comparator = Comparator.comparing(Person::getVaccStatus);
            break;
        case LastDate.FET_FIELD:
            comparator = Comparator.comparing(Person::getLastFetDate);
            break;
        case LastDate.COLLECTION_FIELD:
            comparator = Comparator.comparing(Person::getLastCollectionDate);
            break;
        default:
            throw new CommandException(ALLOWED_FIELDS);
        }

        switch (order) {
        case ASCENDING:
            return comparator;
        case DESCENDING:
            return comparator.reversed();
        default:
            throw new CommandException(ALLOWED_ORDER);
        }
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof SortPersonCommand // instanceof handles nulls
                && field.equals(((SortPersonCommand) other).field)
                && order.equals(((SortPersonCommand) other).order));
    }

}
