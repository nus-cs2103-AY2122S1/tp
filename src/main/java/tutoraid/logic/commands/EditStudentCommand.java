package tutoraid.logic.commands;

import static java.util.Objects.requireNonNull;
import static tutoraid.logic.parser.CliSyntax.PREFIX_PARENT_NAME;
import static tutoraid.logic.parser.CliSyntax.PREFIX_PARENT_PHONE;
import static tutoraid.logic.parser.CliSyntax.PREFIX_STUDENT_NAME;
import static tutoraid.logic.parser.CliSyntax.PREFIX_STUDENT_PHONE;
import static tutoraid.model.Model.PREDICATE_SHOW_ALL_STUDENTS;

import java.util.List;
import java.util.Optional;

import tutoraid.commons.core.Messages;
import tutoraid.commons.core.index.Index;
import tutoraid.commons.util.CollectionUtil;
import tutoraid.logic.commands.exceptions.CommandException;
import tutoraid.model.Model;
import tutoraid.model.student.ParentName;
import tutoraid.model.student.PaymentStatus;
import tutoraid.model.student.Phone;
import tutoraid.model.student.Progress;
import tutoraid.model.student.Student;
import tutoraid.model.student.StudentName;

/**
 * Edits the details of an existing person in the address book.
 */
public class EditStudentCommand extends Command {

    public static final String COMMAND_WORD = "edit";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Edits the details of the student identified "
        + "by the index number used in the displayed student list. "
        + "Existing values will be overwritten by the input values.\n"
        + "Parameters: INDEX (must be a positive integer) "
        + PREFIX_STUDENT_NAME + "STUDENT NAME "
        + PREFIX_STUDENT_PHONE + "STUDENT PHONE "
        + PREFIX_PARENT_NAME + "PARENT NAME "
        + PREFIX_PARENT_PHONE + "PARENT PHONE "
        + "Example: " + COMMAND_WORD + " "
        + PREFIX_STUDENT_NAME + "John Doe "
        + PREFIX_STUDENT_PHONE + "81234567 "
        + PREFIX_PARENT_NAME + "Mrs Doe "
        + PREFIX_PARENT_PHONE + "91234567 ";

    public static final String MESSAGE_EDIT_STUDENT_SUCCESS = "Edited Person: %1$s";
    public static final String MESSAGE_NOT_EDITED = "At least one field to edit must be provided.";
    public static final String MESSAGE_DUPLICATE_STUDENT = "This student already exists in TutorAid";

    private final Index targetIndex;
    private final EditStudentDescriptor editStudentDescriptor;

    /**
     * @param targetIndex           of the person in the filtered person list to edit
     * @param editStudentDescriptor details to edit the person with
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
        List<Student> lastShownList = model.getFilteredStudentList();

        if (targetIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_STUDENT_DISPLAYED_INDEX);
        }

        Student studentToEdit = lastShownList.get(targetIndex.getZeroBased());
        Student editedStudent = createEditedPerson(studentToEdit, editStudentDescriptor);

        if (!studentToEdit.isSameStudent(editedStudent) && model.hasStudent(editedStudent)) {
            throw new CommandException(MESSAGE_DUPLICATE_STUDENT);
        }

        model.setStudent(studentToEdit, editedStudent);
        model.updateFilteredStudentList(PREDICATE_SHOW_ALL_STUDENTS);
        return new CommandResult(String.format(MESSAGE_EDIT_STUDENT_SUCCESS, editedStudent));
    }

    /**
     * Creates and returns a {@code Person} with the details of {@code studentToEdit}
     * edited with {@code editStudentDescriptor}.
     */
    private static Student createEditedPerson(Student studentToEdit, EditStudentDescriptor editStudentDescriptor) {
        assert studentToEdit != null;

        StudentName updatedStudentName = editStudentDescriptor.getStudentName().orElse(studentToEdit.getStudentName());
        Phone updatedStudentPhone = editStudentDescriptor.getStudentPhone().orElse(studentToEdit.getStudentPhone());
        ParentName updatedParentName = editStudentDescriptor.getParentName().orElse(studentToEdit.getParentName());
        Phone updatedParentPhone = editStudentDescriptor.getParentPhone().orElse(studentToEdit.getParentPhone());

        // not updated here
        Progress studentProgress = studentToEdit.getProgress();
        PaymentStatus paymentStatus = studentToEdit.getPaymentStatus();

        return new Student(updatedStudentName, updatedStudentPhone, updatedParentName, updatedParentPhone,
            studentProgress, paymentStatus);
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
     * Stores the details to edit the person with. Each non-empty field value will replace the
     * corresponding field value of the person.
     */
    public static class EditStudentDescriptor {
        private StudentName studentName;
        private Phone studentPhone;
        private ParentName parentName;
        private Phone parentPhone;

        // Data fields
        private Progress progress;
        private PaymentStatus paymentStatus;

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

            EditStudentDescriptor e = (EditStudentDescriptor) other;
            EditStudentDescriptor otherDescriptor = (EditStudentDescriptor) other;
            return otherDescriptor.getStudentName().equals(getStudentName())
                && otherDescriptor.getStudentPhone().equals(getStudentPhone())
                && otherDescriptor.getParentName().equals(getParentName())
                && otherDescriptor.getParentPhone().equals(getParentPhone());
        }
    }
}
