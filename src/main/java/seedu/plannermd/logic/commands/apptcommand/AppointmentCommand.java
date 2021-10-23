package seedu.plannermd.logic.commands.apptcommand;

import static seedu.plannermd.logic.parser.CliSyntax.FLAG_EDIT;
import static seedu.plannermd.logic.parser.CliSyntax.PREFIX_DOCTOR;
import static seedu.plannermd.logic.parser.CliSyntax.PREFIX_DURATION;
import static seedu.plannermd.logic.parser.CliSyntax.PREFIX_PATIENT;
import static seedu.plannermd.logic.parser.CliSyntax.PREFIX_REMARK;
import static seedu.plannermd.logic.parser.CliSyntax.PREFIX_START;

import seedu.plannermd.logic.commands.Command;

public abstract class AppointmentCommand extends Command {

    public static final String COMMAND_WORD = "appt";
    public static final String MESSAGE_USAGE =
            COMMAND_WORD + "{FLAG} {ARGUMENTS}: Executes appointment command given by flag tag\n"
            + "Commands: \n"
            //ADD
            //EDIT
            + COMMAND_WORD + " " + FLAG_EDIT
            + " : Edits the details of the appointment identified "
            + "by the index number used in the displayed appointment list. "
            + "Existing values will be overwritten by the input values.\n"
            + "Parameters: INDEX (must be a positive integer) " + "[" + PREFIX_PATIENT + "PATIENT_INDEX] "
            + "[" + PREFIX_DOCTOR + "DOCTOR_INDEX] " + "[" + PREFIX_START + "DATE TIME] "
            + "[" + PREFIX_DURATION + "DURATION] " + "[" + PREFIX_REMARK + "REMARK]\n";
            //DELETE
            //FILTER
            //FILTER UPCOMING
            //LIST
            //TODO: Append Commands Usages

}
