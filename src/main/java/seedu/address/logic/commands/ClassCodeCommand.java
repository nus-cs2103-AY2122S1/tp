package seedu.address.logic.commands;

import java.util.List;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.student.ClassCode;
import seedu.address.model.student.Student;


public class ClassCodeCommand extends Command {

    public static final String COMMAND_WORD = "classcode";

    public static final String MESSAGE_ADD_REMARK_SUCCESS = "Added classCode to Person: %1$s";
    public static final String MESSAGE_DELETE_REMARK_SUCCESS = "Removed classCode from Person: %1$s";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Edits the classcode of the person identified "
            + "by the index number used in the last person listing. "
            + "Existing classcode will be overwritten by the input.\n"
            + "Parameters: INDEX (must be a positive integer) "
            + "c/ [CLASSCODE]\n"
            + "Example: " + COMMAND_WORD + " 1 "
            + "c/ G06.";

    public static final String MESSAGE_NOT_IMPLEMENTED_YET =
            "Remark command not implemented yet";

    public static final String MESSAGE_ARGUMENTS = "Index: %1$d, ClassCode: %2$s";

    private final Index index;
    private final ClassCode classCode;

    /**
     * Adds a ClassCode to a specific {@code Student}
     * @param index Index of the student in the list
     * @param classCode ClassCode of the {@code Student} to be added
     */
    public ClassCodeCommand(Index index, ClassCode classCode) {
        requireAllNonNull(index, classCode);

        this.index = index;
        this.classCode = classCode;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        List<Student> lastShownList = model.getFilteredStudentList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_STUDENT_DISPLAYED_INDEX);
        }

        Student personToEdit = lastShownList.get(index.getZeroBased());
        Student editedPerson = new Student(
                personToEdit.getName(), personToEdit.getPhone(), personToEdit.getEmail(),
                personToEdit.getAddress(), classCode, personToEdit.getTags());

        model.setStudent(personToEdit, editedPerson);
        model.updateFilteredStudentList(Model.PREDICATE_SHOW_ALL_STUDENTS);

        return new CommandResult(generateSuccessMessage(editedPerson));
    }

    private String generateSuccessMessage(Student studentToEdit) {
        String message = !classCode.value.isEmpty() ? MESSAGE_ADD_REMARK_SUCCESS : MESSAGE_DELETE_REMARK_SUCCESS;
        return String.format(message, studentToEdit);
    }


    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof ClassCodeCommand)) {
            return false;
        }

        // state check
        ClassCodeCommand e = (ClassCodeCommand) other;
        return index.equals(e.index)
                && classCode.equals(e.classCode);
    }
}
