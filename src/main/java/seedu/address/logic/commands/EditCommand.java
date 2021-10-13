package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_CLIENTID;
import static seedu.address.logic.parser.CliSyntax.PREFIX_CURRENTPLAN;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_LASTMET;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PERSONS;

import java.util.Collections;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import seedu.address.commons.core.Messages;
import seedu.address.commons.util.CollectionUtil;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Address;
import seedu.address.model.person.ClientId;
import seedu.address.model.person.CurrentPlan;
import seedu.address.model.person.DisposableIncome;
import seedu.address.model.person.Email;
import seedu.address.model.person.LastMet;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.person.RiskAppetite;
import seedu.address.model.tag.Tag;

/**
 * Edits the details of an existing person in the address book.
 */
public class EditCommand extends Command {

    public static final String COMMAND_WORD = "edit";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Edits the details of the person identified "
            + "by the client's ID. "
            + "Existing values will be overwritten by the input values.\n"
            + "Parameters: " + " CLIENT ID (must be a positive integer) "
            + PREFIX_NAME + "NAME "
            + PREFIX_PHONE + "PHONE "
            + PREFIX_EMAIL + "EMAIL "
            + PREFIX_ADDRESS + "ADDRESS "
            + PREFIX_LASTMET + "LAST MET "
            + PREFIX_CURRENTPLAN + "CURRENTPLAN "
            + "[" + PREFIX_TAG + "TAG]...\n"
            + "Example: " + COMMAND_WORD + " " + " 1 "
            + PREFIX_PHONE + "91234567 "
            + PREFIX_EMAIL + "johndoe@example.com";

    public static final String MESSAGE_EDIT_PERSON_SUCCESS = "Edited Person: %1$s";
    public static final String MESSAGE_NOT_EDITED = "At least one field to edit must be provided.";
    public static final String MESSAGE_DUPLICATE_PERSON = "This person already exists in the address book.";
    public static final String MESSAGE_CHANGE_CLIENTID = "Client's ID cannot be changed.";

    private final ClientId clientId;
    private final EditPersonDescriptor editPersonDescriptor;

    /**
     * @param clientId of the person in the filtered person list to edit
     * @param editPersonDescriptor details to edit the person with
     */
    public EditCommand(ClientId clientId, EditPersonDescriptor editPersonDescriptor) {
        requireNonNull(clientId);
        requireNonNull(editPersonDescriptor);

        this.clientId = clientId;
        this.editPersonDescriptor = new EditPersonDescriptor(editPersonDescriptor);
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        if (!model.hasClientId(clientId)) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_CLIENTID);
        }

        Person personToEdit = model.getAddressBook().getPerson(clientId);
        Person editedPerson = createEditedPerson(personToEdit, editPersonDescriptor);

        if (!personToEdit.isSamePerson(editedPerson) && model.hasPerson(editedPerson)) {
            throw new CommandException(MESSAGE_DUPLICATE_PERSON);
        }

        model.setPerson(personToEdit, editedPerson);
        model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
        return new CommandResult(String.format(MESSAGE_EDIT_PERSON_SUCCESS, editedPerson));
    }

    /**
     * Creates and returns a {@code Person} with the details of {@code personToEdit}
     * edited with {@code editPersonDescriptor}.
     */
    private static Person createEditedPerson(Person personToEdit, EditPersonDescriptor editPersonDescriptor) {
        assert personToEdit != null;

        ClientId oldClientId = personToEdit.getClientId();
        Name updatedName = editPersonDescriptor.getName().orElse(personToEdit.getName());
        Email updatedEmail = editPersonDescriptor.getEmail().orElse(personToEdit.getEmail());
        Phone updatedPhone = editPersonDescriptor.getPhone().orElse(personToEdit.getPhone());
        Address updatedAddress = editPersonDescriptor.getAddress().orElse(personToEdit.getAddress());
        RiskAppetite updateRiskAppetite = editPersonDescriptor.getRiskAppetite()
                .orElse(personToEdit.getRiskAppetite());
        DisposableIncome updatedDisposableIncome = editPersonDescriptor.getDisposableIncome()
                .orElse(personToEdit.getDisposableIncome());
        CurrentPlan updatedCurrentPlan = editPersonDescriptor.getCurrentPlan().orElse(personToEdit.getCurrentPlan());
        LastMet updatedLastMet = editPersonDescriptor.getLastMet().orElse(personToEdit.getLastMet());
        Set<Tag> updatedTags = editPersonDescriptor.getTags().orElse(personToEdit.getTags());

        return new Person(oldClientId, updatedName, updatedPhone, updatedEmail, updatedAddress, updateRiskAppetite,
            updatedDisposableIncome, updatedCurrentPlan, updatedLastMet, updatedTags);
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
        return clientId.equals(e.clientId)
                && editPersonDescriptor.equals(e.editPersonDescriptor);
    }

    /**
     * Stores the details to edit the person with. Each non-empty field value will replace the
     * corresponding field value of the person.
     */
    public static class EditPersonDescriptor {
        private Name name;
        private Phone phone;
        private Email email;
        private Address address;
        private RiskAppetite riskAppetite;
        private DisposableIncome disposableIncome;
        private Set<Tag> tags;
        private LastMet lastMet;
        private CurrentPlan currentPlan;

        public EditPersonDescriptor() {}

        /**
         * Copy constructor.
         * A defensive copy of {@code tags} is used internally.
         */
        public EditPersonDescriptor(EditPersonDescriptor toCopy) {
            setName(toCopy.name);
            setPhone(toCopy.phone);
            setEmail(toCopy.email);
            setAddress(toCopy.address);
            setLastMet(toCopy.lastMet);
            setCurrentPlan(toCopy.currentPlan);
            setDisposableIncome(toCopy.disposableIncome);
            setRiskAppetite(toCopy.riskAppetite);
            setTags(toCopy.tags);
        }

        /**
         * Returns true if at least one field is edited.
         */
        public boolean isAnyFieldEdited() {
            return CollectionUtil.isAnyNonNull(name, phone, email, address, riskAppetite, disposableIncome,
                currentPlan, lastMet, tags);
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

        public void setLastMet(LastMet lastMet) {
            this.lastMet = lastMet;
        }

        public Optional<LastMet> getLastMet() {
            return Optional.ofNullable(lastMet);
        }

        public void setCurrentPlan(CurrentPlan currentPlan) {
            this.currentPlan = currentPlan;
        }

        public Optional<CurrentPlan> getCurrentPlan() {
            return Optional.ofNullable(currentPlan);
        }

        public void setAddress(Address address) {
            this.address = address;
        }

        public Optional<Address> getAddress() {
            return Optional.ofNullable(address);
        }

        public void setRiskAppetite(RiskAppetite riskAppetite) {
            this.riskAppetite = riskAppetite;
        }

        public Optional<RiskAppetite> getRiskAppetite() {
            return Optional.ofNullable(riskAppetite);
        }

        public void setDisposableIncome(DisposableIncome disposableIncome) {
            this.disposableIncome = disposableIncome;
        }

        public Optional<DisposableIncome> getDisposableIncome() {
            return Optional.ofNullable(disposableIncome);
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
                    && getAddress().equals(e.getAddress())
                    && getLastMet().equals(e.getLastMet())
                    && getCurrentPlan().equals(e.getCurrentPlan())
                    && getDisposableIncome().equals(e.getDisposableIncome())
                    && getRiskAppetite().equals(e.getRiskAppetite())
                    && getTags().equals(e.getTags());
        }
    }
}
