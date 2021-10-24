package seedu.plannermd.logic.commands.apptcommand;

import static seedu.plannermd.logic.parser.CliSyntax.FLAG_DELETE;

import seedu.plannermd.logic.commands.Command;

public abstract class AppointmentCommand extends Command {

    public static final String COMMAND_WORD = "appt";
    public static final String MESSAGE_USAGE =
            COMMAND_WORD + "{FLAG} {ARGUMENTS}: Executes appointment command given by flag tag\n"
            + "Commands: \n" +
            FLAG_DELETE + "INDEX_OF_PATIENT\n";
            //EDIT
            //DELETE
            //FILTER
            //FILTER UPCOMING
            //LIST
            //TODO: Append Commands Usages

}
