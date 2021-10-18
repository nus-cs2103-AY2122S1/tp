package seedu.plannermd.logic.commands.apptcommand;

import seedu.plannermd.logic.commands.Command;

public abstract class AppointmentCommand extends Command {

    public static final String COMMAND_WORD = "appt";
    public static final String MESSAGE_USAGE =
            COMMAND_WORD + "{FLAG} {ARGUMENTS}: Executes appointment command given by flag tag\n"
            + "Commands: \n";
            //ADD
            //EDIT
            //DELETE
            //FILTER
            //FILTER UPCOMING
            //LIST
            //TODO: Append Commands Usages

}
