package seedu.times.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.times.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.times.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.times.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.times.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.times.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.times.model.Model.PREDICATE_SHOW_ALL_PERSONS;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import seedu.times.commons.core.Messages;
import seedu.times.commons.core.index.Index;
import seedu.times.commons.util.CollectionUtil;
import seedu.times.logic.commands.exceptions.CommandException;
import seedu.times.model.Model;
import seedu.times.model.person.Address;
import seedu.times.model.person.Email;
import seedu.times.model.person.Name;
import seedu.times.model.person.Nok;
import seedu.times.model.person.Phone;
import seedu.times.model.person.Student;
import seedu.times.model.tag.Tag;
import seedu.times.ui.TabName;

/**
 * Edits the details of an existing person in the Timestable.
 */
public class EditCommand extends Command {

    public static final String COMMAND_WORD = "edit";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Edits the details of the student identified "
            + "by the index number used in the displayed student list. "
            + "Existing values will be overwritten by the input values.\n"
            + "Parameters: INDEX (must be a positive integer) "
            + "[" + PREFIX_NAME + "NAME] "
            + "[" + PREFIX_PHONE + "PHONE] "
            + "[" + PREFIX_EMAIL + "EMAIL] "
            + "[" + PREFIX_ADDRESS + "ADDRESS] "
            + "[" + PREFIX_TAG + "TAG]...\n"
            + "Example: " + COMMAND_WORD + " 1 "
            + PREFIX_PHONE + "91234567 "
            + PREFIX_EMAIL + "johndoe@example.com";

    public static final String MESSAGE_EDIT_STUDENT_SUCCESS = "Edited Student: %1$s";
    public static final String MESSAGE_NOT_EDITED = "At least one field to edit must be provided.";
    public static final String MESSAGE_DUPLICATE_STUDENT = "This student already exists in the address book.";

    private final Index index;
    private final EditStudentDescriptor editStudentDescriptor;

    /**
     * @param index of the person in the filtered person list to edit
     * @param editStudentDescriptor details to edit the person with
     */
    public EditCommand(Index index, EditStudentDescriptor editStudentDescriptor) {
        requireNonNull(index);
        requireNonNull(editStudentDescriptor);

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

        //execute update scade, change name in student name list in tuitionclass
        editStudentDescriptor.getName().ifPresent(name -> {
            model.updateClassStudentLists(name, studentToEdit.getName());
        });

        Student editedStudent = createEditedStudent(studentToEdit, editStudentDescriptor);


        if (!studentToEdit.isSamePerson(editedStudent) && model.hasStudent(editedStudent)) {
            throw new CommandException(MESSAGE_DUPLICATE_STUDENT);
        }

        model.setStudent(studentToEdit, editedStudent);
        model.updateFilteredStudentList(PREDICATE_SHOW_ALL_PERSONS);
        updateView(TabName.STUDENTS);

        return new CommandResult(String.format(MESSAGE_EDIT_STUDENT_SUCCESS, editedStudent));
    }

    /**
     * Creates and returns a {@code Person} with the details of {@code personToEdit}
     * edited with {@code editPersonDescriptor}.
     */
    private static Student createEditedStudent(Student studentToEdit, EditStudentDescriptor editStudentDescriptor)
            throws CommandException {
        assert studentToEdit != null;

        Name updatedName = editStudentDescriptor.getName().orElse(studentToEdit.getName());
        Phone updatedPhone = editStudentDescriptor.getPhone().orElse(studentToEdit.getPhone());
        Email updatedEmail = editStudentDescriptor.getEmail().orElse(studentToEdit.getEmail());
        Address updatedAddress = editStudentDescriptor.getAddress().orElse(studentToEdit.getAddress());
        Set<Tag> updatedTags = editStudentDescriptor.getTags().orElse(studentToEdit.getTags());

        // Nok
        Name nokName = editStudentDescriptor.getNokName().orElse(studentToEdit.getNok().getName());
        Phone nokPhone = editStudentDescriptor.getNokPhone().orElse(studentToEdit.getNok().getPhone());
        Email nokEmail = editStudentDescriptor.getNokEmail().orElse(studentToEdit.getNok().getEmail());
        Address nokAddress = editStudentDescriptor.getNokAddress().orElse(studentToEdit.getNok().getAddress());
        Nok nok = new Nok(nokName, nokPhone, nokEmail, nokAddress);

        return new Student(updatedName, updatedPhone, updatedEmail, updatedAddress, nok, updatedTags);
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
        private Phone phone;
        private Email email;
        private Address address;
        private Set<Tag> tags;

        private Name nokName;
        private Phone nokPhone;
        private Email nokEmail;
        private Address nokAddress;

        public EditStudentDescriptor() {}

        /**
         * Copy constructor.
         * A defensive copy of {@code tags} is used internally.
         */
        public EditStudentDescriptor(EditStudentDescriptor toCopy) {
            setName(toCopy.name);
            setPhone(toCopy.phone);
            setEmail(toCopy.email);
            setAddress(toCopy.address);

            setNokName(toCopy.nokName);
            setNokPhone(toCopy.nokPhone);
            setNokEmail(toCopy.nokEmail);
            setNokAddress(toCopy.nokAddress);

            setTags(toCopy.tags);
        }

        /**
         * Returns true if at least one field is edited.
         */
        public boolean isAnyFieldEdited() {
            return CollectionUtil.isAnyNonNull(name, phone, email, address, tags, nokAddress, nokEmail, nokName,
                    nokPhone);
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

        //// nok

        public void setNokName(Name nokName) {
            this.nokName = nokName;
        }

        public Optional<Name> getNokName() {
            return Optional.ofNullable(nokName);
        }
        public void setNokPhone(Phone nokPhone) {
            this.nokPhone = nokPhone;
        }

        public Optional<Phone> getNokPhone() {
            return Optional.ofNullable(nokPhone);
        }

        public void setNokEmail(Email nokEmail) {
            this.nokEmail = nokEmail;
        }

        public Optional<Email> getNokEmail() {
            return Optional.ofNullable(nokEmail);
        }

        public void setNokAddress(Address nokAddress) {
            this.nokAddress = nokAddress;
        }

        public Optional<Address> getNokAddress() {
            return Optional.ofNullable(nokAddress);
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
            if (!(other instanceof EditStudentDescriptor)) {
                return false;
            }

            // state check
            EditStudentDescriptor e = (EditStudentDescriptor) other;

            return getName().equals(e.getName())
                    && getPhone().equals(e.getPhone())
                    && getEmail().equals(e.getEmail())
                    && getAddress().equals(e.getAddress())
                    && getTags().equals(e.getTags())
                    && getNokAddress().equals(e.getNokAddress())
                    && getNokEmail().equals(e.getNokEmail())
                    && getNokName().equals(e.getNokName())
                    && getNokPhone().equals(e.getNokPhone());

        }
    }
}
