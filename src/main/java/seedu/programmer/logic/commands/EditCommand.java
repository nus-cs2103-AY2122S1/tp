package seedu.programmer.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.programmer.logic.parser.CliSyntax.PREFIX_CLASS_ID;
import static seedu.programmer.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.programmer.logic.parser.CliSyntax.PREFIX_LAB_NUM;
import static seedu.programmer.logic.parser.CliSyntax.PREFIX_LAB_RESULT;
import static seedu.programmer.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.programmer.logic.parser.CliSyntax.PREFIX_STUDENT_ID;

import java.util.List;
import java.util.Optional;

import javafx.collections.ObservableList;
import seedu.programmer.commons.core.Messages;
import seedu.programmer.commons.core.index.Index;
import seedu.programmer.commons.util.CollectionUtil;
import seedu.programmer.logic.commands.exceptions.CommandException;
import seedu.programmer.model.Model;
import seedu.programmer.model.student.ClassId;
import seedu.programmer.model.student.Email;
import seedu.programmer.model.student.Lab;
import seedu.programmer.model.student.LabNum;
import seedu.programmer.model.student.LabResult;
import seedu.programmer.model.student.LabTotal;
import seedu.programmer.model.student.Name;
import seedu.programmer.model.student.Student;
import seedu.programmer.model.student.StudentId;

/**
 * Edits the details of an existing student in ProgrammerError.
 */
public class EditCommand extends Command {

    public static final String COMMAND_WORD = "edit";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Edits the details of the student identified "
            + "by the index number used in the displayed student list. "
            + "Existing values will be overwritten by the input values.\n"
            + "Parameters: INDEX (must be a positive integer) "
            + "[" + PREFIX_NAME + "<NAME>] "
            + "[" + PREFIX_STUDENT_ID + "<STUDENT_ID>] "
            + "[" + PREFIX_CLASS_ID + "<CLASS_ID>] "
            + "[" + PREFIX_EMAIL + "<EMAIL>] "
            + "[" + PREFIX_LAB_NUM + "<LAB_NUMBER> " + PREFIX_LAB_RESULT + "<LAB_SCORE>]\n"
            + "Example: " + COMMAND_WORD + " 1 "
            + PREFIX_STUDENT_ID + "A0121234H "
            + PREFIX_CLASS_ID + "B01";

    public static final String MESSAGE_EDIT_STUDENT_SUCCESS = "Edited Student: %1$s";
    public static final String MESSAGE_NOT_EDITED = "At least one field to edit must be provided.";
    public static final String MESSAGE_NO_NEW_FIELDS = "There is no fields to be edited. Student's information are "
            + "already the same as what you have asked to be edited to.";
    public static final String MESSAGE_DUPLICATE_STUDENT_ID = "This student with the same Student ID "
            + "already exists in the ProgrammerError";
    public static final String MESSAGE_DUPLICATE_STUDENT_EMAIL = "This student with the same Email "
            + "already exists in the ProgrammerError";
    public static final String MESSAGE_EDIT_LAB_SUCCESS = "Lab %1$s score has been updated!";
    public static final String MESSAGE_NO_LAB_EDITED = "No lab has been updated.";

    private static LabNum labNum2;
    private final Index index;
    private final EditStudentDescriptor editStudentDescriptor;

    /**
     * Class constructor for EditCommand.
     *
     * @param index of the student in the filtered student list to edit.
     * @param editStudentDescriptor details to edit the student with.
     */
    public EditCommand(Index index, EditStudentDescriptor editStudentDescriptor) {
        requireNonNull(index);
        requireNonNull(editStudentDescriptor);
        assert(index.getOneBased() >= 1);

        this.index = index;
        this.editStudentDescriptor = new EditStudentDescriptor(editStudentDescriptor);
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Student> lastShownList = model.getFilteredStudentList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_STUDENT_DISPLAYED_INDEX);
        }

        Student studentToEdit = lastShownList.get(index.getZeroBased());

        //Duplicate a copy of the student specified at the index to be edited.
        //This allows for the original student to remain unchanged, so that comparison of fields can be carried out.
        Student studentToEditCopy = studentToEdit.copy();
        Student editedStudent = createEditedStudent(studentToEditCopy, editStudentDescriptor);

        //Check if the user edited any fields.
        //Throws CommandException if no fields are changed.
        if (studentToEdit.isIdenticalStudent(editedStudent)) {
            throw new CommandException(MESSAGE_NO_NEW_FIELDS);
        }


        if (model.hasOtherStudent(studentToEdit, editedStudent)) {
            if (model.hasOtherSameStudentId(studentToEdit, editedStudent)) {
                throw new CommandException(MESSAGE_DUPLICATE_STUDENT_ID);
            } else if (model.hasOtherSameStudentEmail(studentToEdit, editedStudent)) {
                throw new CommandException(MESSAGE_DUPLICATE_STUDENT_EMAIL);
            }
        }

        model.setStudent(studentToEdit, editedStudent);
        model.setSelectedStudent(editedStudent);
        model.setSelectedLabs(editedStudent.getLabList());
        if (labNum2 != null) {
            return new CommandResult(String.format(MESSAGE_EDIT_STUDENT_SUCCESS, editedStudent)
                    + "\n" + String.format(MESSAGE_EDIT_LAB_SUCCESS, labNum2));
        }

        return new CommandResult(String.format(MESSAGE_EDIT_STUDENT_SUCCESS, editedStudent)
                + "\n" + MESSAGE_NO_LAB_EDITED);
    }

    /**
     * Creates and returns a {@code Student} with the details of {@code studentToEdit}
     * edited with {@code editStudentDescriptor}.
     *
     * @param studentToEdit Student object to be edited.
     * @param editStudentDescriptor Contains the corresponding fields of the student to be edited.
     * @return The {@code Student} with the edited attributes.
     */
    private static Student createEditedStudent(Student studentToEdit, EditStudentDescriptor editStudentDescriptor)
            throws CommandException {
        assert studentToEdit != null;
        labNum2 = null;

        Name updatedName = editStudentDescriptor.getName().orElse(studentToEdit.getName());
        StudentId updateStudentId = editStudentDescriptor.getStudentId().orElse(studentToEdit.getStudentId());
        ClassId updatedClassId = editStudentDescriptor.getClassId().orElse(studentToEdit.getClassId());
        Email updatedEmail = editStudentDescriptor.getEmail().orElse(studentToEdit.getEmail());
        Lab updatedLab = editStudentDescriptor.getLab().orElse(null);

        LabResult updatedResult = null;
        if (updatedLab != null) {
            updatedResult = editStudentDescriptor.getResult().orElse(null);
            LabNum labNum = updatedLab.getLabNum();
            LabTotal currTotalScore;
            try {
                currTotalScore = studentToEdit.getLab(labNum).getLabTotal();
            } catch (NullPointerException e) { //when getLab does not find anything
                throw new CommandException(String.format(Lab.MESSAGE_LAB_NOT_EXISTS, updatedLab));
            }
            labNum2 = labNum;
            updatedLab.updateTotal(currTotalScore);
        }

        if (updatedLab != null && updatedResult != null) {
            studentToEdit.editLabScore(updatedLab, updatedResult);
            ObservableList<Lab> updatedList = studentToEdit.getLabList();
            return new Student(updatedName, updateStudentId, updatedClassId, updatedEmail, updatedList);
        } else {
            return new Student(updatedName, updateStudentId, updatedClassId, updatedEmail,
                    studentToEdit.getLabList());
        }
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof EditCommand)) {
            return false;
        }

        // state check
        EditCommand e = (EditCommand) other;
        return index.equals(e.index)
                && editStudentDescriptor.equals(e.editStudentDescriptor);
    }

    /**
     * Stores the details to edit the student with. Each non-empty field value will replace the
     * corresponding field value of the student.
     */
    public static class EditStudentDescriptor {
        private Name name;
        private StudentId studentId;
        private ClassId classId;
        private Email email;
        private LabResult result;
        private Lab lab;
        private ObservableList<Lab> labList;

        /**
         * Class constructor.
         */
        public EditStudentDescriptor() {}

        /**
         * Copy constructor.
         * A defensive copy of {@code tags} is used internally.
         */
        public EditStudentDescriptor(EditStudentDescriptor toCopy) {
            setName(toCopy.name);
            setStudentId(toCopy.studentId);
            setClassId(toCopy.classId);
            setEmail(toCopy.email);
            setLab(toCopy.lab, toCopy.result);
        }

        /**
         * Returns true if at least one field is edited.
         */
        public boolean isAnyFieldEdited() {
            return CollectionUtil.isAnyNonNull(name, studentId, classId, email, lab);
        }

        public void setName(Name name) {
            this.name = name;
        }

        public Optional<Name> getName() {
            return Optional.ofNullable(name);
        }

        public void setStudentId(StudentId studentId) {
            this.studentId = studentId;
        }

        public Optional<StudentId> getStudentId() {
            return Optional.ofNullable(studentId);
        }

        public void setClassId(ClassId classId) {
            this.classId = classId;
        }

        public Optional<ClassId> getClassId() {
            return Optional.ofNullable(classId);
        }

        public void setEmail(Email email) {
            this.email = email;
        }

        public Optional<Email> getEmail() {
            return Optional.ofNullable(email);
        }

        public void setLab(Lab lab, LabResult result) {
            this.lab = lab;
            this.result = result;
        }

        public void setLabList(ObservableList<Lab> labList) {
            this.labList = labList;
        }

        public Optional<Lab> getLab() {
            return Optional.ofNullable(lab);
        }

        public Optional<LabResult> getResult() {
            return Optional.ofNullable(result);
        }

        @Override
        public boolean equals(Object other) {
            // short circuit if same object
            if (other == this) {
                return true;
            }

            // instanceof handles nulls
            if (!(other instanceof EditStudentDescriptor)) {
                return false;
            }

            // state check
            EditStudentDescriptor e = (EditStudentDescriptor) other;

            return getName().equals(e.getName())
                    && getStudentId().equals(e.getStudentId())
                    && getClassId().equals(e.getClassId())
                    && getEmail().equals(e.getEmail());
        }
    }
}
