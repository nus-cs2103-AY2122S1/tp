package nustracker.logic.commands;

import static nustracker.logic.parser.CliSyntax.PREFIX_EVENT;
import static nustracker.logic.parser.CliSyntax.PREFIX_MAJOR;
import static nustracker.logic.parser.CliSyntax.PREFIX_NAME;
import static nustracker.logic.parser.CliSyntax.PREFIX_STUDENTID;
import static nustracker.logic.parser.CliSyntax.PREFIX_YEAR;

import nustracker.logic.commands.exceptions.CommandException;
import nustracker.model.Model;
import nustracker.ui.MainWindow.CurrentlyShownList;

/**
 * Filters and lists all students whose data contain a specific field.
 */
public abstract class FilterCommand extends Command {

    public static final String COMMAND_WORD = "filter";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Filters all students by name, student ID, major, year, or Events "
            + "by the specified keywords.\n"
            + "Parameters: " + PREFIX_NAME + "STUDENT_NAME [MORE_STUDENT_NAMES]... \n"
            + "Example: " + COMMAND_WORD + " " + PREFIX_NAME + "alice bob charlie\n"
            + "Parameters: " + PREFIX_STUDENTID + "STUDENT_ID [MORE_STUDENT_IDS]...\n"
            + "Example: " + COMMAND_WORD + " " + PREFIX_STUDENTID + "e1234567 e234567 e3456789\n"
            + "Parameters: " + PREFIX_EVENT + "EVENT_NAME\n"
            + "Example: " + COMMAND_WORD + " " + PREFIX_EVENT + "Orientation\n"
            + "Parameters: " + PREFIX_MAJOR + "MAJOR [MORE_MAJORS]...\n"
            + "Example: " + COMMAND_WORD + " " + PREFIX_MAJOR + "CS IS\n"
            + "Parameters: " + PREFIX_YEAR + "YEAR [MORE_YEARS]...\n"
            + "Example: " + COMMAND_WORD + " " + PREFIX_YEAR + "2\n";


    @Override
    public abstract CommandResult execute(Model model,
                                          CurrentlyShownList currentlyShownList) throws CommandException;
}
