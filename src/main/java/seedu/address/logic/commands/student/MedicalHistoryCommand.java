package seedu.address.logic.commands.student;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MEDICAL_HISTORY;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_STUDENTS;

import java.util.List;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.student.MedicalHistory;
import seedu.address.model.person.student.Student;

/**
 * Changes the medical history of an existing student inside NewAddressBook.
 */
public class MedicalHistoryCommand extends Command {
    public static final String COMMAND_WORD = "medical";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Edits the medical history of the student identified "
            + "by the index number used in the currently displayed student list. "
            + "Existing medical history will be overwritten by the input, unless the input is identical.\n"
            + "Parameters: INDEX (must be a positive integer) "
            + PREFIX_MEDICAL_HISTORY + "MEDICAL_HISTORY\n"
            + "Example: " + COMMAND_WORD + " 1 " + PREFIX_MEDICAL_HISTORY + "ADHD";
    public static final String MESSAGE_ADD_MEDICAL_HISTORY_SUCCESS = "Set medical history of Student: %1$s";
    public static final String MESSAGE_DELETE_MEDICAL_HISTORY_SUCCESS = "Removed medical history from Student: %1$s";
    public static final String MESSAGE_DUPLICATE = "The student already has this medical history recorded.";

    private final Index index;
    private final MedicalHistory medicalHistory;

    /**
     * @param index of the student in the filtered student list to edit the {@code MedicalHistory}
     * @param medicalHistory of the student to be updated to
     */
    public MedicalHistoryCommand(Index index, MedicalHistory medicalHistory) {
        requireAllNonNull(index, medicalHistory);
        this.index = index;
        this.medicalHistory = medicalHistory;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Student> lastShownList = model.getFilteredStudentList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_STUDENT_DISPLAYED_INDEX);
        }

        Student studentToEdit = lastShownList.get(index.getZeroBased());

        if (studentToEdit.getMedicalHistory().equals(medicalHistory)) {
            throw new CommandException(MESSAGE_DUPLICATE);
        }

        Student editedStudent = new Student(studentToEdit, studentToEdit.getEmergencyContact(),
            studentToEdit.getFormClass(), studentToEdit.getAddress(), medicalHistory);

        model.setStudent(studentToEdit, editedStudent);
        model.updateFilteredStudentList(PREDICATE_SHOW_ALL_STUDENTS);
        return new CommandResult(generateSuccessMessage(editedStudent));
    }

    /**
     * Generates a command execution success message based on whether
     * the medical history is added to or removed from
     * {@code studentToEdit}.
     */
    private String generateSuccessMessage(Student studentToEdit) {
        String message = !medicalHistory.value.isEmpty() ? MESSAGE_ADD_MEDICAL_HISTORY_SUCCESS
                : MESSAGE_DELETE_MEDICAL_HISTORY_SUCCESS;
        return String.format(message, studentToEdit);
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof MedicalHistoryCommand)) {
            return false;
        }

        // state check
        MedicalHistoryCommand e = (MedicalHistoryCommand) other;
        return index.equals(e.index)
                && medicalHistory.equals(e.medicalHistory);
    }
}
