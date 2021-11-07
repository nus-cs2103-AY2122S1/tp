package seedu.address.logic.commands;

import static seedu.address.logic.parser.CliSyntax.PREFIX_DASH_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DASH_INDEX;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DASH_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DASH_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DASH_ROLE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DASH_SALARY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DASH_STATUS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DASH_TAG;

import java.util.List;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Period;
import seedu.address.model.person.Person;
import seedu.address.model.person.predicates.PersonContainsFieldsPredicate;

/**
 * A class representing the command to obtain the working
 * hours and overall salary for each staff.
 */
public class StaffIndividualStatisticsCommand extends Command {

    public static final String COMMAND_WORD = "istaff";
    public static final String DEFAULT_EXECUTION = "Staff to show for the period of %2$s:\n%1$s";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ":"
            + "Command to obtain salary statistics of staff\n"
            + "Used by looking up the staff to display by field.\n"
            + "input parameters.\n\n"
            + "Parameters:\n"
            + "[" + PREFIX_DASH_NAME + "NAME] "
            + "[" + PREFIX_DASH_INDEX + "INDEX] "
            + "[" + PREFIX_DASH_PHONE + "PHONE] "
            + "[" + PREFIX_DASH_EMAIL + "EMAIL] "
            + "[" + PREFIX_DASH_SALARY + "SALARY] "
            + "[" + PREFIX_DASH_STATUS + "STATUS] "
            + "[" + PREFIX_DASH_ROLE + "ROLE]... "
            + "[" + PREFIX_DASH_TAG + "TAG]...\n\n"
            + "Example:\n" + COMMAND_WORD + " "
            + PREFIX_DASH_PHONE + "91234567 "
            + PREFIX_DASH_EMAIL + "johndoe@example.com";
    public static final String INDIVIDUAL_STAFF_PRINT = "Stats for %1$s:\n"
            + "Total work hours: %2$s\n"
            + "Total salary: %3$s";

    public static final String NO_STAFF_SATISFIES_QUERY = "No one satisfies the conditions specified";

    private final PersonContainsFieldsPredicate predicate;
    private final int index;
    private final Period period;

    /**
     * Constructor for the lookup command to display staff statistics.
     * Staff statistics displayed are total work hours for the month.
     *
     * @param predicate The {@code PersonContainsFieldsPredicate} that decides
     *                  which staff to display the statistics of.
     */
    public StaffIndividualStatisticsCommand(PersonContainsFieldsPredicate predicate, Period period) {
        this.predicate = predicate;
        this.index = -1;
        this.period = period;
    }

    /**
     * Constructor for the lookup command to display staff statistics by index.
     *
     * @param predicate The predicate to test the staff with.
     * @param index The index of the staff to get.
     */
    public StaffIndividualStatisticsCommand(PersonContainsFieldsPredicate predicate, Index index, Period period) {
        this.predicate = predicate;
        this.index = index.getZeroBased();
        this.period = period;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        if (index != -1) {
            return executeIndex(model);
        }
        List<Person> staffs = model.getUnFilteredPersonList().filtered(predicate);
        if (staffs.size() == 0) {
            throw new CommandException(NO_STAFF_SATISFIES_QUERY);
        }
        return new CommandResult(String.format(DEFAULT_EXECUTION, result(staffs), period));

    }

    private CommandResult executeIndex(Model model) throws CommandException {
        if (index >= model.getFilteredPersonList().size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        Person staff = model.getFilteredPersonListByIndex(index);
        if (!predicate.test(staff)) {
            throw new CommandException(NO_STAFF_SATISFIES_QUERY);
        }
        return new CommandResult(String.format(DEFAULT_EXECUTION, result(List.of(staff)), period));
    }


    private String result(List<Person> staffs) {
        StringBuilder sb = new StringBuilder();
        sb.append("\n");
        for (Person staff : staffs) {
            sb.append(staffSummary(staff));
            sb.append("\n");
        }
        return sb.toString();

    }

    private String staffSummary(Person staff) {
        long workHours = staff
                .getTotalWorkingHour(period);
        double totalSalary = staff.getSalaryToBePaid(this.period);
        return String.format(INDIVIDUAL_STAFF_PRINT, staff.getName(), workHours, totalSalary);
    }

    @Override
    public boolean equals(Object obj) {
        return obj != null
                && obj instanceof StaffIndividualStatisticsCommand
                && ((StaffIndividualStatisticsCommand) obj).index == index
                && ((StaffIndividualStatisticsCommand) obj).predicate.equals(predicate)
                && ((StaffIndividualStatisticsCommand) obj).period.equals(period);
    }
}
