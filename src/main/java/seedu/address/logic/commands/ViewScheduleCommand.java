package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DASH_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DASH_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DASH_INDEX;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DASH_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DASH_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DASH_ROLE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DASH_SALARY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DASH_STATUS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DASH_TAG;

import javafx.collections.transformation.FilteredList;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Person;
import seedu.address.model.person.predicates.PersonContainsFieldsPredicate;

/**
 * Class representing the view schedule command
 * which views the schedule by Person.
 */
public class ViewScheduleCommand extends Command {

    public static final String DEFAULT_MESSAGE = "Schedule viewed of staff: %1$s\n";
    public static final String COMMAND_WORD = "viewSchedule";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Displays the schedules of the staff that have the"
            + "input parameters.\n\n"
            + "Parameters:\n"
            + "[" + PREFIX_DASH_NAME + "NAME] "
            + "[" + PREFIX_DASH_INDEX + "INDEX] "
            + "[" + PREFIX_DASH_PHONE + "PHONE] "
            + "[" + PREFIX_DASH_EMAIL + "EMAIL] "
            + "[" + PREFIX_DASH_ADDRESS + "ADDRESS] "
            + "[" + PREFIX_DASH_SALARY + "SALARY] "
            + "[" + PREFIX_DASH_STATUS + "STATUS] "
            + "[" + PREFIX_DASH_ROLE + "ROLE]... "
            + "[" + PREFIX_DASH_TAG + "TAG]...\n\n"
            + "Example:\n" + COMMAND_WORD + " "
            + PREFIX_DASH_PHONE + "91234567 "
            + PREFIX_DASH_EMAIL + "johndoe@example.com";
    private static final String NAME_NOT_IN_LIST_ERROR = "Name used not in dataset.";

    private final PersonContainsFieldsPredicate predicate;

    public ViewScheduleCommand(PersonContainsFieldsPredicate predicate) {
        this.predicate = predicate;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        //todo: change this filtered list to the overall model
        FilteredList<Person> staffs = model.getFilteredPersonList()
                .filtered(this.predicate);
        if (staffs.size() == 0) {
            throw new CommandException(NAME_NOT_IN_LIST_ERROR);
        }

        String result = "";
        for (int i = 0; i < staffs.size(); i++) {
            result += String.format(DEFAULT_MESSAGE, staffs.get(i).getName());
            result += staffs.get(i).getSchedule().toViewScheduleString();
        }
        return new CommandResult(result);
    }

}
