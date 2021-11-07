package tutoraid.logic.commands;

import static java.util.Objects.requireNonNull;
import static tutoraid.logic.parser.CliSyntax.PREFIX_PARENT_NAME;
import static tutoraid.logic.parser.CliSyntax.PREFIX_PARENT_PHONE;
import static tutoraid.logic.parser.CliSyntax.PREFIX_STUDENT_NAME;
import static tutoraid.logic.parser.CliSyntax.PREFIX_STUDENT_PHONE;

import java.util.List;
import java.util.Optional;

import tutoraid.commons.core.Messages;
import tutoraid.commons.core.index.Index;
import tutoraid.commons.util.CollectionUtil;
import tutoraid.logic.commands.exceptions.CommandException;
import tutoraid.model.Model;
import tutoraid.model.student.Lessons;
import tutoraid.model.student.ParentName;
import tutoraid.model.student.Phone;
import tutoraid.model.student.ProgressList;
import tutoraid.model.student.Student;
import tutoraid.model.student.StudentName;

/**
 * Edits the details of an existing student in the address book.
 */
public class EditStudentCommand extends EditCommand {

    public static final String COMMAND_FLAG = "-s";

    public static final String MESSAGE_USAGE = String.format("%1$s %2$s: Edits the details of the student."
                    + "Existing values will be overwritten by the input values."
                    + "\nParameters:"
                    + "\nINDEX (must be a positive integer)"
                    + "  %3$s[STUDENT NAME]"
                    + "  [%4$sSTUDENT PHONE]"
                    + "  [%5$sPARENT NAME]"
                    + "  [%6$sPARENT PHONE]"
                    + "\nExample:"
                    + "\n%1$s %2$s 1 %3$sJon Poh %4$s87654321 %5$sMr Po %6$s98765432",
            COMMAND_WORD, COMMAND_FLAG, PREFIX_STUDENT_NAME, PREFIX_STUDENT_PHONE, PREFIX_PARENT_NAME,
            PREFIX_PARENT_PHONE);
    public static final String MESSAGE_EDIT_STUDENT_SUCCESS = "Edit successful. Showing %s and his/her lessons.";
    public static final String MESSAGE_NOT_CHANGED = "Warning: Attempted to edit %s but the provided field(s) did not "
            + "contain any changes.";
    public static final String MESSAGE_NOT_EDITED = "At least one field to edit must be provided.";
    public static final String MESSAGE_DUPLICATE_STUDENT = "This student already exists in TutorAid.";

    private final Index targetIndex;
    private final EditStudentDescriptor editStudentDescriptor;

    /**
     * @param targetIndex           of the student in the filtered student list to edit
     * @param editStudentDescriptor details to edit the student with
     */
    public EditStudentCommand(Index targetIndex, EditStudentDescriptor editStudentDescriptor) {
        requireNonNull(targetIndex);
        requireNonNull(editStudentDescriptor);

        this.targetIndex = targetIndex;
        this.editStudentDescriptor = new EditStudentDescriptor(editStudentDescriptor);
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Student> lastShownStudentList = model.getFilteredStudentList();

        if (targetIndex.getZeroBased() >= lastShownStudentList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_STUDENT_DISPLAYED_INDEX);
        }

        Student studentToEdit = lastShownStudentList.get(targetIndex.getZeroBased());
        Student editedStudent = createEditedStudent(studentToEdit, editStudentDescriptor);

        if (!studentToEdit.isSameStudent(editedStudent) && model.hasStudent(editedStudent)) {
            throw new CommandException(MESSAGE_DUPLICATE_STUDENT);
        }

        if (studentToEdit.equals(editedStudent)) {
            throw new CommandException(String.format(MESSAGE_NOT_CHANGED, editedStudent.toNameString()));
        }

        studentToEdit.replace(editedStudent);
        model.viewStudent(studentToEdit);
        model.updateFilteredLessonList(studentToEdit::hasLesson);
        return new CommandResult(String.format(MESSAGE_EDIT_STUDENT_SUCCESS, studentToEdit.toNameString()));
    }

    /**
     * Creates and returns a {@code Student} with the details of {@code studentToEdit}
     * edited with {@code editStudentDescriptor}.
     */
    private static Student createEditedStudent(Student studentToEdit, EditStudentDescriptor editStudentDescriptor) {
        assert studentToEdit != null;

        StudentName updatedStudentName = editStudentDescriptor.getStudentName().orElse(studentToEdit.getStudentName());
        Phone updatedStudentPhone = editStudentDescriptor.getStudentPhone().orElse(studentToEdit.getStudentPhone());
        ParentName updatedParentName = editStudentDescriptor.getParentName().orElse(studentToEdit.getParentName());
        Phone updatedParentPhone = editStudentDescriptor.getParentPhone().orElse(studentToEdit.getParentPhone());

        ProgressList studentProgress = studentToEdit.getProgressList();
        Lessons lessons = studentToEdit.getLessons();

        return new Student(updatedStudentName, updatedStudentPhone, updatedParentName, updatedParentPhone,
                studentProgress, lessons);
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof EditStudentCommand)) {
            return false;
        }

        // state check
        EditStudentCommand e = (EditStudentCommand) other;
        return targetIndex.equals(e.targetIndex)
                && editStudentDescriptor.equals(e.editStudentDescriptor);
    }

    /**
     * Stores the details to edit the student with. Each non-empty field value will replace the
     * corresponding field value of the student.
     */
    public static class EditStudentDescriptor {
        private StudentName studentName;
        private Phone studentPhone;
        private ParentName parentName;
        private Phone parentPhone;

        public EditStudentDescriptor() {
        }

        /**
         * Copy constructor.
         */
        public EditStudentDescriptor(EditStudentDescriptor toCopy) {
            setStudentName(toCopy.studentName);
            setStudentPhone(toCopy.studentPhone);
            setParentName(toCopy.parentName);
            setParentPhone(toCopy.parentPhone);
        }

        /**
         * Returns true if at least one field is edited.
         */
        public boolean isAnyFieldEdited() {
            return CollectionUtil.isAnyNonNull(studentName, studentPhone, parentName, parentPhone);
        }

        public void setStudentName(StudentName studentName) {
            this.studentName = studentName;
        }

        public Optional<StudentName> getStudentName() {
            return Optional.ofNullable(studentName);
        }

        public void setStudentPhone(Phone studentPhone) {
            this.studentPhone = studentPhone;
        }

        public Optional<Phone> getStudentPhone() {
            return Optional.ofNullable(studentPhone);
        }

        public void setParentName(ParentName parentName) {
            this.parentName = parentName;
        }

        public Optional<ParentName> getParentName() {
            return Optional.ofNullable(parentName);
        }

        public void setParentPhone(Phone parentPhone) {
            this.parentPhone = parentPhone;
        }

        public Optional<Phone> getParentPhone() {
            return Optional.ofNullable(parentPhone);
        }

        /**
         * Returns true if both descriptors represent students that have the same identity and data fields.
         * This defines a stronger notion of equality between two students.
         */
        @Override
        public boolean equals(Object other) {
            if (other == this) {
                return true;
            }

            if (!(other instanceof EditStudentDescriptor)) {
                return false;
            }

            EditStudentDescriptor otherDescriptor = (EditStudentDescriptor) other;
            return otherDescriptor.getStudentName().equals(getStudentName())
                    && otherDescriptor.getStudentPhone().equals(getStudentPhone())
                    && otherDescriptor.getParentName().equals(getParentName())
                    && otherDescriptor.getParentPhone().equals(getParentPhone());
        }
    }
}
