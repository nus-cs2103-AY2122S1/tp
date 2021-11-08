package seedu.address.logic.commands.student;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_COPY_FIELD;

import java.util.List;
import java.util.logging.Logger;

import seedu.address.commons.core.LogsCenter;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.CopyCommand;
import seedu.address.logic.commands.descriptors.CopyCommandDescriptor;
import seedu.address.model.Model;
import seedu.address.model.person.student.Student;

/**
 * Copies the specified field of all students in the currently displayed student list.
 */
public class CopyStudentCommand extends CopyCommand {

    public static final String COMMAND_WORD = "copyStudent";
    public static final String MESSAGE_USAGE = "Copies the specified field of all students in "
            + "the currently displayed student list.\n"
            + "Parameters: " + PREFIX_COPY_FIELD + "FIELD_TO_COPY\n"
            + "where FIELD_TO_COPY can be 'phone', 'email', or 'name'. \n"
            + "Example usage: " + COMMAND_WORD + " " + PREFIX_COPY_FIELD + "name";

    private final Logger logger = LogsCenter.getLogger(CopyStudentCommand.class);

    /**
     * Constructor for {@code CopyStudentCommand}
     *
     * @param copyCommandDescriptor the {@code copyCommandDescriptor to specify which field to copy}
     */

    public CopyStudentCommand(CopyCommandDescriptor copyCommandDescriptor) {
        super(copyCommandDescriptor);
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        List<Student> lastShownList = model.getFilteredStudentList();
        if (lastShownList.size() == 0) {
            return new CommandResult(CopyCommand.EMPTY_LIST);
        }
        copyToClipBoard(lastShownList);
        logger.info("Contents copied to clipboard!");
        return new CommandResult(MESSAGE_SUCCESS);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof CopyStudentCommand // instanceof handles nulls
                && super.equals(other));
    }

}
