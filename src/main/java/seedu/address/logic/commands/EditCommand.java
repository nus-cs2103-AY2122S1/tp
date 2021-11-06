package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DASH_INDEX;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DASH_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ROLE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SALARY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_STATUS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PERSONS;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.CollectionUtil;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Email;
import seedu.address.model.person.Name;
import seedu.address.model.person.Period;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.person.Role;
import seedu.address.model.person.Salary;
import seedu.address.model.person.Schedule;
import seedu.address.model.person.Status;
import seedu.address.model.tag.Tag;


/**
 * Edits the details of an existing person in the address book.
 */
public class EditCommand extends Command {

    private enum Identifier {
        INDEX, NAME
    }

    public static final String COMMAND_WORD = "edit";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Edits the details of the person identified "
            + "by the index number used in the displayed person list or by the name identifier.\n"
            + "Existing values will be overwritten by the input values.\n\n"
            + "Parameters:\n"
            + PREFIX_DASH_INDEX + " INDEX or "
            + PREFIX_DASH_NAME + " NAME "
            + "[" + PREFIX_PHONE + "PHONE] "
            + "[" + PREFIX_EMAIL + "EMAIL] "
            + "[" + PREFIX_SALARY + "SALARY] "
            + "[" + PREFIX_STATUS + "STATUS] "
            + "[" + PREFIX_ROLE + "ROLE]... "
            + "[" + PREFIX_TAG + "TAG]...\n\n"
            + "Examples:\n" + COMMAND_WORD + " "
            + PREFIX_DASH_INDEX + " 1 "
            + PREFIX_PHONE + "91234567 "
            + PREFIX_EMAIL + "johndoe@example.com\n"
            + COMMAND_WORD + " "
            + PREFIX_DASH_NAME + " john "
            + PREFIX_PHONE + "91234567 "
            + PREFIX_EMAIL + "johndoe@example.com\n";

    public static final String MESSAGE_EDIT_PERSON_SUCCESS = "Edited Person: %1$s";
    public static final String MESSAGE_NOT_EDITED = "At least one field to edit must be provided.";
    public static final String MESSAGE_DUPLICATE_PERSON = "This person already exists in the address book.";

    private Index index;
    private Name name;
    private final Identifier identifier;
    private final EditPersonDescriptor editStaffDescriptor;

    /**
     * @param index of the person in the filtered person list to edit
     * @param editStaffDescriptor details to edit the person with
     */
    public EditCommand(Index index, EditPersonDescriptor editStaffDescriptor) {
        requireNonNull(index);
        requireNonNull(editStaffDescriptor);

        this.index = index;
        this.editStaffDescriptor = new EditPersonDescriptor(editStaffDescriptor);
        this.identifier = Identifier.INDEX;
    }

    /**
     * @param name                  of the person in the staffd database to edit.
     * @param editStaffDescriptor   details to edit the person with
     */
    public EditCommand(Name name, EditPersonDescriptor editStaffDescriptor) {
        requireNonNull(name);
        requireNonNull(editStaffDescriptor);

        this.editStaffDescriptor = editStaffDescriptor;
        this.name = name;
        this.identifier = Identifier.NAME;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        switch(this.identifier) {
        case INDEX:
            return editBasedOnIndex(model);
        case NAME:
            return editBasedOnName(model);
        default:
            throw new CommandException(Messages.MESSAGE_INVALID_COMMAND_FORMAT);

        }

    }

    private CommandResult editBasedOnName(Model model) throws CommandException {
        requireNonNull(model);
        List<Person> underlyingList = model.getUnFilteredPersonList();
        Optional<Person> person = underlyingList
                .stream()
                .filter(staff -> staff.getName().equals(this.name))
                .findFirst();
        if (!person.isPresent()) {
            //if the person is not in the list
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_SEARCHED);
        }
        Person staffToEdit = person.get();
        return editStaffOnModel(model, staffToEdit);


    }

    private CommandResult editStaffOnModel(Model model, Person staffToEdit) throws CommandException {
        Person editedStaff = createEditedPerson(staffToEdit, editStaffDescriptor);
        if (!staffToEdit.isSamePerson(editedStaff) && model.hasPerson(editedStaff)) {
            throw new CommandException(MESSAGE_DUPLICATE_PERSON);
        }

        model.setPerson(staffToEdit, editedStaff);
        model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
        return new CommandResult(String.format(MESSAGE_EDIT_PERSON_SUCCESS, editedStaff));
    }

    private CommandResult editBasedOnIndex(Model model) throws CommandException {
        requireNonNull(model);
        List<Person> lastShownList = model.getFilteredPersonList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        Person staffToEdit = lastShownList.get(index.getZeroBased());
        return editStaffOnModel(model, staffToEdit);
    }

    /**
     * Creates and returns a {@code Person} with the details of {@code personToEdit}
     * edited with {@code editPersonDescriptor}.
     */
    private static Person createEditedPerson(Person staffToEdit, EditPersonDescriptor editPersonDescriptor) {
        assert staffToEdit != null;

        Name updatedName = editPersonDescriptor.getName().orElse(staffToEdit.getName());
        Phone updatedPhone = editPersonDescriptor.getPhone().orElse(staffToEdit.getPhone());
        Email updatedEmail = editPersonDescriptor.getEmail().orElse(staffToEdit.getEmail());
        Set<Role> updatedRoles = editPersonDescriptor.getRoles().orElse(staffToEdit.getRoles());
        Salary updatedSalary = editPersonDescriptor.getSalary().orElse(staffToEdit.getSalary());
        Status updatedStatus = editPersonDescriptor.getStatus().orElse(staffToEdit.getStatus());
        Set<Tag> updatedTags = editPersonDescriptor.getTags().orElse(staffToEdit.getTags());
        //currently do not allow modifications to period via edit person descriptor
        //exception would be during tests.
        Set<Period> updatedPeriod = editPersonDescriptor.getPeriod().orElse(staffToEdit.getAbsentDates());
        Schedule sameSchedule = staffToEdit.getSchedule();


        Person updatedPerson = new Person(updatedName, updatedPhone, updatedEmail, updatedRoles,
                updatedSalary, updatedStatus, updatedTags, updatedPeriod);
        updatedPerson.setSchedule(sameSchedule);
        return updatedPerson;
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
                && editStaffDescriptor.equals(e.editStaffDescriptor);
    }

    /**
     * Stores the details to edit the person with. Each non-empty field value will replace the
     * corresponding field value of the person.
     */
    public static class EditPersonDescriptor {
        private Name name;
        private Phone phone;
        private Email email;
        private Set<Role> roles;
        private Salary salary;
        private Status status;
        private Set<Tag> tags;
        private Set<Period> absentPeriods;
        private Schedule schedule;

        public EditPersonDescriptor() {
        }

        /**
         * Copy constructor.
         * A defensive copy of {@code tags} is used internally.
         */
        public EditPersonDescriptor(EditPersonDescriptor toCopy) {
            setName(toCopy.name);
            setPhone(toCopy.phone);
            setEmail(toCopy.email);
            setRoles(toCopy.roles);
            setSalary(toCopy.salary);
            setStatus(toCopy.status);
            setTags(toCopy.tags);
            setPeriod(toCopy.absentPeriods);
            setSchedule(toCopy.schedule);
        }

        /**
         * Returns true if at least one field is edited.
         */
        public boolean isAnyFieldEdited() {
            return CollectionUtil.isAnyNonNull(name, phone, email, roles, salary, status, tags, absentPeriods);
        }

        public void setName(Name name) {
            this.name = name;
        }

        public Optional<Name> getName() {
            return Optional.ofNullable(name);
        }

        public void setSchedule(Schedule schedule) {
            this.schedule = schedule;
        }

        public Optional<Schedule> getSchedule() {
            return Optional.ofNullable(schedule);
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

        /**
         * Sets {@code roles} to this object's {@code roles}.
         * A defensive copy of {@code roles} is used internally.
         */
        public void setRoles(Set<Role> roles) {
            this.roles = (roles != null) ? new HashSet<>(roles) : null;
        }

        /**
         * Returns an unmodifiable role set, which throws {@code UnsupportedOperationException}
         * if modification is attempted.
         * Returns {@code Optional#empty()} if {@code roles} is null.
         */
        public Optional<Set<Role>> getRoles() {
            return (roles != null) ? Optional.of(Collections.unmodifiableSet(roles)) : Optional.empty();
        }

        public void setSalary(Salary salary) {
            this.salary = salary;
        }

        public Optional<Salary> getSalary() {
            return Optional.ofNullable(salary);
        }

        public void setStatus(Status status) {
            this.status = status;
        }

        public Optional<Status> getStatus() {
            return Optional.ofNullable(status);
        }

        /**
         * Sets {@code periods} to this object's {@code periods}.
         * A defensive copy of {@code periods is used internally.}
         * @param periods
         */
        public void setPeriod(Set<Period> periods) {
            this.absentPeriods = (periods != null) ? new HashSet<>(periods) : null;
        }

        /**
         * Returns an unmodifiable period set, which throws {@code UnsupportedOperationException}
         * if modification is attempted.
         * Returns {@code Optional#empty()} if {@code period} is null.
         */
        public Optional<Set<Period>> getPeriod() {
            return absentPeriods != null
                    ? Optional.of(Collections.unmodifiableSet(absentPeriods))
                    : Optional.empty();
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
                    && getEmail().equals(e.getEmail())
                    && getRoles().equals(e.getRoles())
                    && getSalary().equals(e.getSalary())
                    && getStatus().equals(e.getStatus())
                    && getTags().equals(e.getTags())
                    && getSchedule().equals(e.getSchedule());
        }
    }
}
