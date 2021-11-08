package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_GENDER;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_QUALIFICATION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_REMARK;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.CollectionUtil;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.parser.PersonType;
import seedu.address.model.Model;
import seedu.address.model.person.Gender;
import seedu.address.model.person.Name;
import seedu.address.model.person.Phone;
import seedu.address.model.person.Qualification;
import seedu.address.model.person.Remark;
import seedu.address.model.person.Student;
import seedu.address.model.person.TagsContainTagPredicate;
import seedu.address.model.person.Tutor;
import seedu.address.model.tag.Tag;

/**
 * Edits the details of an existing person in the address book.
 */
public class EditCommand extends Command {
    public static final String COMMAND_WORD = "edit";
    public static final String COMMAND_ALIAS = "e";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Edits the details of the person identified "
            + "by the index number used in the displayed person list. "
            + "Existing values will be overwritten by the input values.\n"
            + "Parameters: "
            + "<t "
            + "INDEX (must be a positive integer) "
            + "[" + PREFIX_NAME + "NAME] "
            + "[" + PREFIX_PHONE + "PHONE] "
            + "[" + PREFIX_GENDER + "GENDER] "
            + "[" + PREFIX_QUALIFICATION + "QUALIFICATION] "
            + "[" + PREFIX_REMARK + "REMARK] "
            + "[" + PREFIX_TAG + "TAG...]>\n"
            + "or <s "
            + "INDEX (must be a positive integer) "
            + "[" + PREFIX_NAME + "NAME] "
            + "[" + PREFIX_PHONE + "PHONE] "
            + "[" + PREFIX_GENDER + "GENDER] "
            + "[" + PREFIX_REMARK + "REMARK] "
            + "[" + PREFIX_TAG + "TAG...]>\n"
            + "Example: " + COMMAND_WORD + " t 1 "
            + PREFIX_PHONE + "91234567 ";

    public static final String MESSAGE_EDIT_TUTOR_SUCCESS = "Edited Tutor:\n%1$s";
    public static final String MESSAGE_EDIT_STUDENT_SUCCESS = "Edited Student:\n%1$s";
    public static final String MESSAGE_NOT_EDITED = "At least one field to edit must be provided";
    public static final String MESSAGE_UNCHANGED_TUTOR = "This tutor is unchanged";
    public static final String MESSAGE_UNCHANGED_STUDENT = "This student is unchanged";

    private final Index index;
    private final EditPersonDescriptor editPersonDescriptor;
    private final PersonType personType;

    /**
     * @param index of the person in the filtered person list to edit
     * @param editPersonDescriptor details to edit the person with
     */
    public EditCommand(Index index, EditPersonDescriptor editPersonDescriptor, PersonType personType) {
        requireNonNull(index);
        requireNonNull(editPersonDescriptor);
        requireNonNull(personType);

        this.index = index;
        this.editPersonDescriptor = editPersonDescriptor;
        this.personType = personType;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        switch(personType) {
        case TUTOR:
            return executeEditTutor(model);
            // No break necessary due to return statement
        case STUDENT:
            return executeEditStudent(model);
            // No break necessary due to return statement
        default:
            // Any invalid input would be handled by the EditCommandParser and will not reach here
            throw new CommandException(MESSAGE_USAGE);
        }
    }

    /**
     * Executes an edit tutor command.
     *
     * @param model Model to edit tutor in.
     * @return A successful CommandResult with the edited tutor.
     * @throws CommandException An exception that occurs when editing tutors.
     */
    private CommandResult executeEditTutor(Model model) throws CommandException {
        List<Tutor> lastShownList = model.getFilteredTutorList();

        if (lastShownList.isEmpty()) {
            throw new CommandException(String.format(Messages.MESSAGE_EMPTY_LIST, PersonType.TUTOR));
        }

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_TUTOR_DISPLAYED_INDEX);
        }

        if (!editPersonDescriptor.isAnyFieldEdited()) {
            throw new CommandException(MESSAGE_NOT_EDITED);
        }

        Tutor tutorToEdit = lastShownList.get(index.getZeroBased());
        Tutor editedTutor = createEditedTutor(tutorToEdit, (EditTutorDescriptor) editPersonDescriptor);

        if (tutorToEdit.equals(editedTutor)) {
            throw new CommandException(MESSAGE_UNCHANGED_TUTOR);
        }

        if (!tutorToEdit.getPhone().equals(editedTutor.getPhone())
                && model.hasPersonWithSamePhone(editedTutor.getPhone())) {
            throw new CommandException(Phone.MESSAGE_REPEATED_PHONE);
        }

        model.setTutor(tutorToEdit, editedTutor);

        return new CommandResult(String.format(MESSAGE_EDIT_TUTOR_SUCCESS, editedTutor));
    }

    /**
     * Executes an edit student command.
     *
     * @param model Model to edit student in.
     * @return A successful CommandResult with the edited student.
     * @throws CommandException An exception that occurs when editing students.
     */
    private CommandResult executeEditStudent(Model model) throws CommandException {
        List<Student> lastShownList = model.getFilteredStudentList();

        if (lastShownList.isEmpty()) {
            throw new CommandException(String.format(Messages.MESSAGE_EMPTY_LIST, PersonType.STUDENT));
        }

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_STUDENT_DISPLAYED_INDEX);
        }

        if (!editPersonDescriptor.isAnyFieldEdited()) {
            throw new CommandException(MESSAGE_NOT_EDITED);
        }

        Student studentToEdit = lastShownList.get(index.getZeroBased());
        Student editedStudent = createEditedStudent(studentToEdit, (EditStudentDescriptor) editPersonDescriptor);

        if (studentToEdit.equals(editedStudent)) {
            throw new CommandException(MESSAGE_UNCHANGED_STUDENT);
        }

        if (!studentToEdit.getPhone().equals(editedStudent.getPhone())
                && model.hasPersonWithSamePhone(editedStudent.getPhone())) {
            throw new CommandException(Phone.MESSAGE_REPEATED_PHONE);
        }

        model.setStudent(studentToEdit, editedStudent);
        handleMatchList(model, studentToEdit, editedStudent);
        return new CommandResult(String.format(MESSAGE_EDIT_STUDENT_SUCCESS, editedStudent));
    }

    private void handleMatchList(Model model, Student student, Student editedStudent) {
        Student studentMatched = model.getMatchedStudent();
        if (studentMatched != null && studentMatched.isSamePerson(student)) {
            Set<Tag> studentTag = editedStudent.getTags();
            ArrayList<Tag> ls = new ArrayList<>(studentTag);
            model.updateMatchedTutor(new TagsContainTagPredicate(ls), ls, editedStudent);
        }
    }

    /**
     * Creates and returns a {@code Tutor} with the details of {@code tutorToEdit}
     * edited with {@code editTutorDescriptor}.
     */
    private static Tutor createEditedTutor(Tutor tutorToEdit, EditTutorDescriptor editTutorDescriptor) {
        assert tutorToEdit != null;

        Name updatedName = editTutorDescriptor.getName().orElse(tutorToEdit.getName());
        Phone updatedPhone = editTutorDescriptor.getPhone().orElse(tutorToEdit.getPhone());
        Gender updatedGender = editTutorDescriptor.getGender().orElse(tutorToEdit.getGender());
        Qualification updatedQualification = editTutorDescriptor.getQualification()
            .orElse(tutorToEdit.getQualification());
        Remark updatedRemark = editTutorDescriptor.getRemark().orElse(tutorToEdit.getRemark());

        Set<Tag> updatedTags = editTutorDescriptor.getTags().orElse(tutorToEdit.getTags());

        return new Tutor(updatedName, updatedPhone, updatedGender, updatedQualification, updatedRemark, updatedTags);
    }

    /**
     * Creates and returns a {@code Student} with the details of {@code studentToEdit}
     * edited with {@code editStudentDescriptor}.
     */
    private static Student createEditedStudent(Student studentToEdit, EditStudentDescriptor editStudentDescriptor) {
        assert studentToEdit != null;

        Name updatedName = editStudentDescriptor.getName().orElse(studentToEdit.getName());
        Phone updatedPhone = editStudentDescriptor.getPhone().orElse(studentToEdit.getPhone());
        Gender updatedGender = editStudentDescriptor.getGender().orElse(studentToEdit.getGender());
        Remark updatedRemark = editStudentDescriptor.getRemark().orElse(studentToEdit.getRemark());
        Set<Tag> updatedTag = editStudentDescriptor.getTags().orElse(studentToEdit.getTags());

        return new Student(updatedName, updatedPhone, updatedGender, updatedRemark, updatedTag);
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
                && editPersonDescriptor.equals(e.editPersonDescriptor);
    }

    /**
     * Stores the details to edit the person with. Each non-empty field value will replace the
     * corresponding field value of the person.
     */
    public static class EditPersonDescriptor {
        private Name name;
        private Phone phone;
        private Gender gender;
        private Remark remark;
        private Set<Tag> tags;

        public EditPersonDescriptor() {}

        /**
         * Copy constructor.
         * A defensive copy of {@code tags} is used internally.
         */
        public EditPersonDescriptor(EditPersonDescriptor toCopy) {
            setName(toCopy.name);
            setPhone(toCopy.phone);
            setGender(toCopy.gender);
            setRemark(toCopy.remark);
            setTags(toCopy.tags);
        }

        /**
         * Returns true if at least one field is edited.
         */
        public boolean isAnyFieldEdited() {
            return CollectionUtil.isAnyNonNull(name, phone, gender, remark, tags);
        }

        public void setName(Name name) {
            this.name = name;
        }

        public Optional<Name> getName() {
            return Optional.ofNullable(name);
        }

        public void setPhone(Phone phone) {
            this.phone = phone;
        }

        public Optional<Phone> getPhone() {
            return Optional.ofNullable(phone);
        }

        public void setGender(Gender gender) {
            this.gender = gender;
        }

        public Optional<Gender> getGender() {
            return Optional.ofNullable(gender);
        }

        public void setRemark(Remark remark) {
            this.remark = remark;
        }

        public Optional<Remark> getRemark() {
            return Optional.ofNullable(remark);
        }

        /**
         * Sets {@code tags} to this object's {@code tags}.
         * A defensive copy of {@code tags} is used internally.
         */
        public void setTags(Set<Tag> tags) {
            this.tags = (tags != null) ? new HashSet<>(tags) : null;
        }

        /**
         * Returns an unmodifiable tag set, which throws {@code UnsupportedOperationException}
         * if modification is attempted.
         * Returns {@code Optional#empty()} if {@code tags} is null.
         */
        public Optional<Set<Tag>> getTags() {
            return (tags != null) ? Optional.of(Collections.unmodifiableSet(tags)) : Optional.empty();
        }

        @Override
        public boolean equals(Object other) {
            // short circuit if same object
            if (other == this) {
                return true;
            }

            // instanceof handles nulls
            if (!(other instanceof EditPersonDescriptor)) {
                return false;
            }

            // state check
            EditPersonDescriptor e = (EditPersonDescriptor) other;

            return getName().equals(e.getName())
                    && getPhone().equals(e.getPhone())
                    && getGender().equals(e.getGender())
                    && getRemark().equals(e.getRemark())
                    && getTags().equals(e.getTags());
        }
    }

    /**
     * Stores the details to edit the tutor with. Each non-empty field value will replace the
     * corresponding field value of the tutor.
     */
    public static class EditTutorDescriptor extends EditPersonDescriptor {
        private Qualification qualification;

        public EditTutorDescriptor() {
            super();
        }

        /**
         * Copy constructor.
         * A defensive copy of {@code tags} is used internally.
         */
        public EditTutorDescriptor(EditTutorDescriptor toCopy) {
            super(toCopy);
            setQualification(toCopy.qualification);
        }

        @Override
        public boolean isAnyFieldEdited() {
            return super.isAnyFieldEdited() || CollectionUtil.isAnyNonNull(qualification);
        }

        public void setQualification(Qualification qualification) {
            this.qualification = qualification;
        }

        public Optional<Qualification> getQualification() {
            return Optional.ofNullable(qualification);
        }

        @Override
        public boolean equals(Object other) {
            // short circuit if same object
            if (other == this) {
                return true;
            }

            // instanceof handles nulls
            if (!(other instanceof EditTutorDescriptor)) {
                return false;
            }

            // state check
            EditTutorDescriptor e = (EditTutorDescriptor) other;

            return getName().equals(e.getName())
                    && getPhone().equals(e.getPhone())
                    && getGender().equals(e.getGender())
                    && getQualification().equals(e.getQualification())
                    && getRemark().equals(e.getRemark())
                    && getTags().equals(e.getTags());
        }
    }

    /**
     * Stores the details to edit the student with. Each non-empty field value will replace the
     * corresponding field value of the student.
     */
    public static class EditStudentDescriptor extends EditPersonDescriptor {
        public EditStudentDescriptor() {
            super();
        }

        /**
         * Copy constructor.
         * A defensive copy of {@code tags} is used internally.
         */
        public EditStudentDescriptor(EditStudentDescriptor toCopy) {
            super(toCopy);
        }
    }
}
