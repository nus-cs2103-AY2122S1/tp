package seedu.address.logic.commands;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;

import javafx.collections.transformation.FilteredList;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;

/**
 * Class representing the view schedule command
 * which views the schedule by Person.
 */
public class ViewScheduleCommand extends Command {

    //is there a better command word for this?
    public static final String DEFAULT_MESSAGE = "Schedule viewed of staff: %1$s\n";
    public static final String COMMAND_WORD = "viewSchedule";
    public static final String HELP_MESSAGE = ": view the schedule of a staff\n"
            + "Method to use:\n"
            + "viewSchedule [" + PREFIX_NAME + "NAME]";

    private static final String NAME_NOT_IN_LIST_ERROR = "Name used not in dataset.";

    private final Name name;

    /**
     * Constructor for the view schedule class with
     * a name of a person.
     *
     * @param name
     */
    public ViewScheduleCommand(Name name) {
        this.name = name;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireAllNonNull(model);
        FilteredList<Person> staffs = model.getFilteredPersonList()
                .filtered(per -> per.getName().equals(this.name));
        if (staffs.size() == 0) {
            throw new CommandException(NAME_NOT_IN_LIST_ERROR);
        }
        model.updateScheduleDisplay(person -> person.getName().equals(this.name));

        String result = "";
        for (int i = 0; i < staffs.size(); i++) {
            result += staffs.get(i).getSchedule();
        }
        return new CommandResult(result);
    }

}
