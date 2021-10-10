package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;

import javafx.collections.transformation.FilteredList;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Person;
import seedu.address.model.person.PersonContainsFieldsPredicate;

/**
 * Class representing the view schedule command
 * which views the schedule by Person.
 */
public class ViewScheduleCommand extends Command {
    public static final String DEFAULT_MESSAGE = "Schedule viewed of staff: %1$s\n";
    public static final String COMMAND_WORD = "viewSchedule";
    public static final String HELP_MESSAGE = ": view the schedule of a staff\n"
            + "Method to use:\n"
            + "viewSchedule [" + PREFIX_NAME + "NAME]";

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
