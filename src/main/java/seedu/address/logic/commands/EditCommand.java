package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_HEALTH_CONDITION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_LANGUAGE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_LAST_VISIT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
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
import seedu.address.model.healthcondition.HealthCondition;
import seedu.address.model.person.Address;
import seedu.address.model.person.Frequency;
import seedu.address.model.person.Language;
import seedu.address.model.person.LastVisit;
import seedu.address.model.person.Name;
import seedu.address.model.person.Occurrence;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.person.Visit;

/**
 * Edits the details of an existing person in the address book.
 */
public class EditCommand extends Command {

    public static final String COMMAND_WORD = "edit";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Edits the details of the elderly identified "
            + "by the index number used in the displayed elderly list. "
            + "Existing values will be overwritten by the input values.\n"
            + "Parameters: INDEX (must be a positive integer) "
            + "[" + PREFIX_NAME + "NAME] "
            + "[" + PREFIX_PHONE + "PHONE] "
            + "[" + PREFIX_LANGUAGE + "LANGUAGE] "
            + "[" + PREFIX_ADDRESS + "ADDRESS] "
            + "[" + PREFIX_LAST_VISIT + "LAST_VISIT] "
            + "[" + PREFIX_HEALTH_CONDITION + "HEALTH_CONDITION]...\n"
            + "Example: " + COMMAND_WORD + " 1 "
            + PREFIX_PHONE + "91234567 "
            + PREFIX_LANGUAGE + "English";

    public static final String MESSAGE_EDIT_PERSON_SUCCESS = "Edited Elderly: %1$s";
    public static final String MESSAGE_NOT_EDITED = "At least one field to edit must be provided.";
    public static final String MESSAGE_DUPLICATE_PERSON = "This elderly already exists in the address book.";

    private final Index index;
    private final EditPersonDescriptor editPersonDescriptor;

    /**
     * @param index of the person in the filtered person list to edit
     * @param editPersonDescriptor details to edit the person with
     */
    public EditCommand(Index index, EditPersonDescriptor editPersonDescriptor) {
        requireNonNull(index);
        requireNonNull(editPersonDescriptor);

        this.index = index;
        this.editPersonDescriptor = new EditPersonDescriptor(editPersonDescriptor);
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Person> lastShownList = model.getFilteredPersonList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        Person personToEdit = lastShownList.get(index.getZeroBased());
        Person editedPerson = createEditedPerson(personToEdit, editPersonDescriptor);

        if (!personToEdit.isSamePerson(editedPerson) && model.hasPerson(editedPerson)) {
            throw new CommandException(MESSAGE_DUPLICATE_PERSON);
        }

        model.setPerson(personToEdit, editedPerson);
        model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);

        // check for warnings
        boolean isInvalidVisit = editedPerson.hasVisit() && editedPerson.getVisit().get().isOverdue();
        boolean isInvalidLastVisit = editedPerson.hasLastVisit() && editedPerson.getLastVisit().get().isFuture();

        if (isInvalidVisit && isInvalidLastVisit) {
            return new CommandResult(
                    String.format(MESSAGE_EDIT_PERSON_SUCCESS, editedPerson), CommandWarning.BOTH_VISIT_FIELDS_WARNING);
        } else if (isInvalidLastVisit) {
            return new CommandResult(
                    String.format(MESSAGE_EDIT_PERSON_SUCCESS, editedPerson), CommandWarning.FUTURE_LAST_VISIT_WARNING);
        } else if (isInvalidVisit) {
            return new CommandResult(
                    String.format(MESSAGE_EDIT_PERSON_SUCCESS, editedPerson), CommandWarning.PAST_NEXT_VISIT_WARNING);
        } else {
            return new CommandResult(String.format(MESSAGE_EDIT_PERSON_SUCCESS, editedPerson));
        }
    }

    /**
     * Creates and returns a {@code Person} with the details of {@code personToEdit}
     * edited with {@code editPersonDescriptor}.
     */
    private static Person createEditedPerson(Person personToEdit, EditPersonDescriptor editPersonDescriptor) {
        assert personToEdit != null;

        Name updatedName = editPersonDescriptor.getName().orElse(personToEdit.getName());
        Phone updatedPhone = editPersonDescriptor.getPhone().orElse(personToEdit.getPhone());
        Language updatedLanguage = editPersonDescriptor.getLanguage().orElse(personToEdit.getLanguage());
        Address updatedAddress = editPersonDescriptor.getAddress().orElse(personToEdit.getAddress());
        LastVisit lastVisit = editPersonDescriptor.getLastVisit().orElse(personToEdit.getLastVisit().get());
        Optional<LastVisit> updatedLastVisit = Optional.ofNullable(lastVisit);
        // edit command does not allow editing visits
        Optional<Visit> updatedVisit = personToEdit.getVisit();
        // edit command does not allow editing frequency
        Optional<Frequency> updatedFrequency = personToEdit.getFrequency();
        // edit command does not allow editing occurrence
        Optional<Occurrence> updatedOccurrence = personToEdit.getOccurrence();
        Set<HealthCondition> updatedHealthConditions = editPersonDescriptor.getHealthConditions()
                .orElse(personToEdit.getHealthConditions());

        return new Person(updatedName, updatedPhone, updatedLanguage, updatedAddress,
                updatedLastVisit, updatedVisit, updatedFrequency, updatedOccurrence, updatedHealthConditions);
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
        private Language language;
        private Address address;
        private Set<HealthCondition> healthConditions;
        private LastVisit lastVisit;

        public EditPersonDescriptor() {}

        /**
         * Copy constructor.
         * A defensive copy of {@code healthConditions} is used internally.
         */
        public EditPersonDescriptor(EditPersonDescriptor toCopy) {
            setName(toCopy.name);
            setPhone(toCopy.phone);
            setLanguage(toCopy.language);
            setAddress(toCopy.address);
            setHealthConditions(toCopy.healthConditions);
            setLastVisit(toCopy.lastVisit);
        }

        /**
         * Returns true if at least one field is edited.
         */
        public boolean isAnyFieldEdited() {
            return CollectionUtil.isAnyNonNull(name, phone, language, address, healthConditions, lastVisit);
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

        public void setLanguage(Language language) {
            this.language = language;
        }

        public Optional<Language> getLanguage() {
            return Optional.ofNullable(language);
        }

        public void setAddress(Address address) {
            this.address = address;
        }

        public Optional<Address> getAddress() {
            return Optional.ofNullable(address);
        }

        public void setLastVisit(LastVisit lastVisit) {
            this.lastVisit = lastVisit;
        }

        public Optional<LastVisit> getLastVisit() {
            return Optional.ofNullable(lastVisit);
        }

        /**
         * Sets {@code healthConditions} to this object's {@code healthConditions}.
         * A defensive copy of {@code healthConditions} is used internally.
         */
        public void setHealthConditions(Set<HealthCondition> healthConditions) {
            this.healthConditions = (healthConditions != null) ? new HashSet<>(healthConditions) : null;
        }

        /**
         * Returns an unmodifiable healthCondition set, which throws {@code UnsupportedOperationException}
         * if modification is attempted.
         * Returns {@code Optional#empty()} if {@code healthConditions} is null.
         */
        public Optional<Set<HealthCondition>> getHealthConditions() {
            return (healthConditions != null)
                    ? Optional.of(Collections.unmodifiableSet(healthConditions)) : Optional.empty();
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
                    && getLanguage().equals(e.getLanguage())
                    && getAddress().equals(e.getAddress())
                    && getHealthConditions().equals(e.getHealthConditions())
                    && getLastVisit().equals(e.getLastVisit());
        }
    }
}
