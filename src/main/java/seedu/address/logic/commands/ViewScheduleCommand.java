package seedu.address.logic.commands;

import seedu.address.model.Model;
import seedu.address.model.person.Person;

/**
 * Class representing the view schedule command
 * which views the schedule by Person.
 */
public class ViewScheduleCommand extends Command {

    //is there a better command word for this?
    public static final String DEFAULT_MESSAGE = "Schedule viewed of staff: %1$s";
    public static final String COMMAND_WORD = "viewSchedule";

    private final Person staff;

    /**
     * Constructor for the view schedule command.
     *
     * @param staff The staff to view the schedule of.
     */
    public ViewScheduleCommand(Person staff) {
        this.staff = staff;
    }



    @Override
    public CommandResult execute(Model model) {
        model.updateScheduleDisplay(staff -> staff.equals(this.staff));
        return new CommandResult(String.format(DEFAULT_MESSAGE, this.staff));
    }

}
