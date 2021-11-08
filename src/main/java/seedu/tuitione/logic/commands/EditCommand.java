package seedu.tuitione.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.tuitione.commons.core.Messages.HEADER_ALERT;
import static seedu.tuitione.commons.core.Messages.HEADER_SUCCESS;
import static seedu.tuitione.commons.core.Messages.MESSAGE_DUPLICATE_STUDENT_FOUND;
import static seedu.tuitione.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.tuitione.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.tuitione.logic.parser.CliSyntax.PREFIX_DELETE_REMARK;
import static seedu.tuitione.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.tuitione.logic.parser.CliSyntax.PREFIX_GRADE;
import static seedu.tuitione.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.tuitione.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.tuitione.logic.parser.CliSyntax.PREFIX_REMARK;
import static seedu.tuitione.model.student.Student.MAX_REMARK_SIZE;
import static seedu.tuitione.model.student.Student.REMARK_COUNT_CONSTRAINT;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import seedu.tuitione.commons.core.Messages;
import seedu.tuitione.commons.core.index.Index;
import seedu.tuitione.commons.util.CollectionUtil;
import seedu.tuitione.logic.commands.exceptions.CommandException;
import seedu.tuitione.model.Model;
import seedu.tuitione.model.lesson.Lesson;
import seedu.tuitione.model.remark.Remark;
import seedu.tuitione.model.student.Address;
import seedu.tuitione.model.student.Email;
import seedu.tuitione.model.student.Grade;
import seedu.tuitione.model.student.Name;
import seedu.tuitione.model.student.ParentContact;
import seedu.tuitione.model.student.Student;

/**
 * Edits the details of an existing student in the tuitione book.
 */
public class EditCommand extends Command {

    public static final String COMMAND_WORD = "edit";

    public static final String MESSAGE_USAGE = "Command: "
            + COMMAND_WORD + "\nEdits the details of the student identified "
            + "by the index number used in the displayed student list. "
            + "Existing values will be overwritten by the input values.\n\n"
            + "Parameters: INDEX (must be a positive integer) "
            + "[" + PREFIX_NAME + "NAME] "
            + "[" + PREFIX_PHONE + "PHONE] "
            + "[" + PREFIX_EMAIL + "EMAIL] "
            + "[" + PREFIX_ADDRESS + "ADDRESS] "
            + "[" + PREFIX_GRADE + "GRADE] "
            + "[" + PREFIX_REMARK + "REMARK]... "
            + "[" + PREFIX_DELETE_REMARK + "REMARK_TO_DELETE]...\n"
            + "Example: " + COMMAND_WORD + " 1 "
            + PREFIX_PHONE + "91234567 "
            + PREFIX_EMAIL + "johndoe@example.com";

    public static final String MESSAGE_EDIT_STUDENT_SUCCESS = HEADER_SUCCESS + "Edited Student: %1$s";
    public static final String MESSAGE_NOT_EDITED = HEADER_ALERT + "At least one field to edit must be provided.";
    public static final String MESSAGE_DUPLICATE_STUDENT = HEADER_ALERT + MESSAGE_DUPLICATE_STUDENT_FOUND;
    public static final String MESSAGE_TOO_MANY_REMARKS = HEADER_ALERT + REMARK_COUNT_CONSTRAINT;
    public static final String MESSAGE_NO_SUCH_REMARK_FOUND = HEADER_ALERT + "The remark(s) you wish to "
            + "remove does not exist.";

    private final Index index;
    private final EditStudentDescriptor editStudentDescriptor;

    /**
     * @param index of the student in the filtered student list to edit
     * @param editStudentDescriptor details to edit the student with
     */
    public EditCommand(Index index, EditStudentDescriptor editStudentDescriptor) {
        requireAllNonNull(index, editStudentDescriptor);

        this.index = index;
        this.editStudentDescriptor = new EditStudentDescriptor(editStudentDescriptor);
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        // grab student from list
        List<Student> lastShownList = model.getFilteredStudentList();
        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_STUDENT_DISPLAYED_INDEX);
        }
        Student studentToEdit = lastShownList.get(index.getZeroBased());

        // check if it conflicts with another unique student
        List<Lesson> studentLessons = studentToEdit.getLessons();
        Student editedStudent = createEditedStudent(studentToEdit, editStudentDescriptor);
        if (!studentToEdit.isSameStudent(editedStudent) && model.hasStudent(editedStudent)) {
            throw new CommandException(MESSAGE_DUPLICATE_STUDENT);
        }

        // update lessons associated with student to edit
        while (!studentLessons.isEmpty()) {
            Lesson lesson = studentLessons.get(0);
            if (!editedStudent.getGrade().equals(studentToEdit.getGrade())) {
                lesson.unenrollStudent(studentToEdit);
            } else {
                // grade is not modified, hence must be update lessons about student change
                lesson.updateStudent(studentToEdit, editedStudent);
            }
            model.setLesson(lesson, lesson);
        }

        model.setStudent(studentToEdit, editedStudent);
        return new CommandResult(String.format(MESSAGE_EDIT_STUDENT_SUCCESS, editedStudent));
    }

    /**
     * Creates and returns a {@code Student} with the details of {@code studentToEdit}
     * edited with {@code editStudentDescriptor}.
     */
    private static Student createEditedStudent(Student studentToEdit, EditStudentDescriptor editStudentDescriptor)
            throws CommandException {
        assert studentToEdit != null;

        Name updatedName = editStudentDescriptor.getName().orElse(studentToEdit.getName());
        ParentContact updatedParentContact = editStudentDescriptor.getPhone().orElse(studentToEdit.getParentContact());
        Email updatedEmail = editStudentDescriptor.getEmail().orElse(studentToEdit.getEmail());
        Address updatedAddress = editStudentDescriptor.getAddress().orElse(studentToEdit.getAddress());
        Grade updatedGrade = editStudentDescriptor.getGrade().orElse(studentToEdit.getGrade());

        Set<Remark> remarksToAdd = editStudentDescriptor.getRemarks().orElse(Collections.emptySet());
        Set<Remark> remarksToDelete = editStudentDescriptor.getRemarksToDelete().orElse(Collections.emptySet());

        Set<Remark> updatedRemarks = new HashSet<>(studentToEdit.getRemarks());

        for (Remark remark : remarksToDelete) {
            if (!updatedRemarks.contains(remark)) {
                throw new CommandException(MESSAGE_NO_SUCH_REMARK_FOUND);
            }
            updatedRemarks.remove(remark);
        }

        for (Remark remark : remarksToAdd) {
            updatedRemarks.add(remark);
            if (updatedRemarks.size() > MAX_REMARK_SIZE) {
                throw new CommandException(MESSAGE_TOO_MANY_REMARKS);
            }
        }

        return new Student(updatedName, updatedParentContact, updatedEmail, updatedAddress,
                updatedGrade, updatedRemarks);
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
        private ParentContact parentContact;
        private Email email;
        private Address address;
        private Grade grade;
        private Set<Remark> remarks;
        private Set<Remark> remarksToDelete;

        public EditStudentDescriptor() {}

        /**
         * Copy constructor.
         * A defensive copy of {@code remarks} is used internally.
         */
        public EditStudentDescriptor(EditStudentDescriptor toCopy) {
            setName(toCopy.name);
            setPhone(toCopy.parentContact);
            setEmail(toCopy.email);
            setAddress(toCopy.address);
            setGrade(toCopy.grade);
            setRemarks(toCopy.remarks);
            setRemarksToDelete(toCopy.remarksToDelete);
        }

        /**
         * Returns true if at least one field is edited.
         */
        public boolean isAnyFieldEdited() {
            return CollectionUtil.isAnyNonNull(name, parentContact, email, address, grade, remarks, remarksToDelete);
        }

        public void setName(Name name) {
            this.name = name;
        }

        public Optional<Name> getName() {
            return Optional.ofNullable(name);
        }

        public void setPhone(ParentContact parentContact) {
            this.parentContact = parentContact;
        }

        public Optional<ParentContact> getPhone() {
            return Optional.ofNullable(parentContact);
        }

        public void setEmail(Email email) {
            this.email = email;
        }

        public Optional<Email> getEmail() {
            return Optional.ofNullable(email);
        }

        public void setAddress(Address address) {
            this.address = address;
        }

        public Optional<Address> getAddress() {
            return Optional.ofNullable(address);
        }

        public void setGrade(Grade grade) {
            this.grade = grade;
        }

        public Optional<Grade> getGrade() {
            return Optional.ofNullable(grade);
        }

        /**
         * Sets {@code remarks} to this object's {@code remarks}.
         * A defensive copy of {@code remarks} is used internally.
         */
        public void setRemarks(Set<Remark> remarks) {
            this.remarks = (remarks != null) ? new HashSet<>(remarks) : Collections.emptySet();
        }

        /**
         * Sets {@code remarksToDelete} to this object's {@code remarksToDelete}.
         * A defensive copy of {@code remarksToDelete} is used internally.
         */
        public void setRemarksToDelete(Set<Remark> remarksToDelete) {
            this.remarksToDelete = (remarksToDelete != null) ? new HashSet<>(remarksToDelete) : Collections.emptySet();
        }

        /**
         * Returns an unmodifiable remark set, which throws {@code UnsupportedOperationException}
         * if modification is attempted.
         * Returns {@code Optional#empty()} if {@code remarks} is null.
         */
        public Optional<Set<Remark>> getRemarks() {
            return (remarks != null) ? Optional.of(Collections.unmodifiableSet(remarks)) : Optional.empty();
        }

        /**
         * Returns an unmodifiable remark set, which throws {@code UnsupportedOperationException}
         * if modification is attempted.
         * Returns {@code Optional#empty()} if {@code remarks} is null.
         */
        public Optional<Set<Remark>> getRemarksToDelete() {
            return (remarksToDelete != null)
                    ? Optional.of(Collections.unmodifiableSet(remarksToDelete)) : Optional.empty();
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
                    && getPhone().equals(e.getPhone())
                    && getEmail().equals(e.getEmail())
                    && getAddress().equals(e.getAddress())
                    && getGrade().equals(e.getGrade())
                    && getRemarks().equals(e.getRemarks());
        }

    }
}
