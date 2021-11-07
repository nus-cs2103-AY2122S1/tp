package seedu.track2gather.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.track2gather.commons.core.Messages.MESSAGE_PREDICATE_SHOW_ALL_PERSONS;
import static seedu.track2gather.logic.parser.CliSyntax.PREFIX_CASE_NUMBER;
import static seedu.track2gather.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.track2gather.logic.parser.CliSyntax.PREFIX_HOME_ADDRESS;
import static seedu.track2gather.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.track2gather.logic.parser.CliSyntax.PREFIX_NEXT_OF_KIN_ADDRESS;
import static seedu.track2gather.logic.parser.CliSyntax.PREFIX_NEXT_OF_KIN_NAME;
import static seedu.track2gather.logic.parser.CliSyntax.PREFIX_NEXT_OF_KIN_PHONE;
import static seedu.track2gather.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.track2gather.logic.parser.CliSyntax.PREFIX_QUARANTINE_ADDRESS;
import static seedu.track2gather.logic.parser.CliSyntax.PREFIX_SHN_PERIOD;
import static seedu.track2gather.logic.parser.CliSyntax.PREFIX_WORK_ADDRESS;
import static seedu.track2gather.model.Model.PREDICATE_SHOW_ALL_PERSONS;

import java.util.List;
import java.util.Optional;

import seedu.track2gather.commons.core.Messages;
import seedu.track2gather.commons.core.index.Index;
import seedu.track2gather.commons.util.CollectionUtil;
import seedu.track2gather.logic.commands.exceptions.CommandException;
import seedu.track2gather.model.Model;
import seedu.track2gather.model.person.Person;
import seedu.track2gather.model.person.attributes.Address;
import seedu.track2gather.model.person.attributes.CallStatus;
import seedu.track2gather.model.person.attributes.CaseNumber;
import seedu.track2gather.model.person.attributes.Email;
import seedu.track2gather.model.person.attributes.Name;
import seedu.track2gather.model.person.attributes.NextOfKinAddress;
import seedu.track2gather.model.person.attributes.NextOfKinName;
import seedu.track2gather.model.person.attributes.NextOfKinPhone;
import seedu.track2gather.model.person.attributes.Phone;
import seedu.track2gather.model.person.attributes.QuarantineAddress;
import seedu.track2gather.model.person.attributes.ShnPeriod;
import seedu.track2gather.model.person.attributes.WorkAddress;

/**
 * Edits the details of an existing person in the contacts list.
 */
public class EditCommand extends Command {

    public static final String COMMAND_WORD = "edit";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Edits the details of the person identified "
            + "by the index number used in the displayed person list. "
            + "Existing values will be overwritten by the input values.\n"
            + "Parameters: INDEX (must be a positive integer) "
            + "[" + PREFIX_NAME + "NAME] "
            + "[" + PREFIX_PHONE + "PHONE] "
            + "[" + PREFIX_EMAIL + "EMAIL] "
            + "[" + PREFIX_CASE_NUMBER + "CASE_NUMBER] "
            + "[" + PREFIX_HOME_ADDRESS + "HOME_ADDRESS] "
            + "[" + PREFIX_WORK_ADDRESS + "WORK_ADDRESS] "
            + "[" + PREFIX_QUARANTINE_ADDRESS + "QUARANTINE_ADDRESS] "
            + "[" + PREFIX_SHN_PERIOD + "SHN_PERIOD] "
            + "[" + PREFIX_NEXT_OF_KIN_NAME + "NEXT_OF_KIN_NAME] "
            + "[" + PREFIX_NEXT_OF_KIN_PHONE + "NEXT_OF_KIN_PHONE] "
            + "[" + PREFIX_NEXT_OF_KIN_ADDRESS + "NEXT_OF_KIN_ADDRESS] \n"
            + "Example: " + COMMAND_WORD + " 1 "
            + PREFIX_PHONE + "91234567 "
            + PREFIX_EMAIL + "johndoe@example.com";

    public static final String MESSAGE_EDIT_PERSON_SUCCESS = "Edited the following fields: \n"
            + "%1$s\n"
            + MESSAGE_PREDICATE_SHOW_ALL_PERSONS;
    public static final String MESSAGE_NOT_EDITED = "At least one field to edit must be provided.";
    public static final String MESSAGE_DUPLICATE_PERSON = "This case number already exists in the contacts list.";

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
        return new CommandResult(String.format(MESSAGE_EDIT_PERSON_SUCCESS, editPersonDescriptor));
    }

    /**
     * Creates and returns a {@code Person} with the details of {@code personToEdit}
     * edited with {@code editPersonDescriptor}.
     */
    public static Person createEditedPerson(Person personToEdit, EditPersonDescriptor editPersonDescriptor) {
        assert personToEdit != null;

        Name updatedName = editPersonDescriptor.getName().orElse(personToEdit.getName());
        Phone updatedPhone = editPersonDescriptor.getPhone().orElse(personToEdit.getPhone());
        Email updatedEmail = editPersonDescriptor.getEmail().orElse(personToEdit.getEmail());
        CaseNumber updatedCaseNumber = editPersonDescriptor.getCaseNumber().orElse(personToEdit.getCaseNumber());
        Address updatedHomeAddress = editPersonDescriptor.getHomeAddress().orElse(personToEdit.getHomeAddress());
        WorkAddress updatedWorkAddress = editPersonDescriptor.getWorkAddress()
                .orElse(personToEdit.getWorkAddress());
        QuarantineAddress updatedQuarantineAddress = editPersonDescriptor.getQuarantineAddress()
                .orElse(personToEdit.getQuarantineAddress());
        ShnPeriod updatedShnPeriod = editPersonDescriptor.getShnPeriod()
                .orElse(personToEdit.getShnPeriod());
        NextOfKinName updatedNextOfKinName = editPersonDescriptor.getNextOfKinName()
                .orElse(personToEdit.getNextOfKinName());
        NextOfKinPhone updatedNextOfKinPhone = editPersonDescriptor.getNextOfKinPhone()
                .orElse(personToEdit.getNextOfKinPhone());
        NextOfKinAddress updatedNextOfKinAddress = editPersonDescriptor.getNextOfKinAddress()
                .orElse(personToEdit.getNextOfKinAddress());
        CallStatus updatedCallStatus = editPersonDescriptor.getCallStatus().orElse(personToEdit.getCallStatus());
        return new Person(updatedName, updatedPhone, updatedEmail, updatedCaseNumber, updatedHomeAddress,
                updatedWorkAddress, updatedQuarantineAddress, updatedShnPeriod, updatedNextOfKinName,
                updatedNextOfKinPhone, updatedNextOfKinAddress, updatedCallStatus);
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
        private Email email;
        private CaseNumber caseNumber;
        private Address homeAddress;
        private WorkAddress workAddress;
        private QuarantineAddress quarantineAddress;
        private ShnPeriod shnPeriod;
        private NextOfKinName nextOfKinName;
        private NextOfKinPhone nextOfKinPhone;
        private NextOfKinAddress nextOfKinAddress;
        private CallStatus callStatus;

        public EditPersonDescriptor() {
        }

        /**
         * Copy constructor.
         */
        public EditPersonDescriptor(EditPersonDescriptor toCopy) {
            setName(toCopy.name);
            setPhone(toCopy.phone);
            setEmail(toCopy.email);
            setCaseNumber(toCopy.caseNumber);
            setHomeAddress(toCopy.homeAddress);
            setWorkAddress(toCopy.workAddress);
            setQuarantineAddress(toCopy.quarantineAddress);
            setShnPeriod(toCopy.shnPeriod);
            setNextOfKinName(toCopy.nextOfKinName);
            setNextOfKinPhone(toCopy.nextOfKinPhone);
            setNextOfKinAddress(toCopy.nextOfKinAddress);
            setCallStatus(toCopy.callStatus);
        }

        /**
         * Returns true if at least one field is edited.
         */
        public boolean isAnyFieldEdited() {
            return CollectionUtil.isAnyNonNull(name, phone, email, caseNumber, homeAddress,
                    workAddress, quarantineAddress, shnPeriod, nextOfKinName, nextOfKinPhone, nextOfKinAddress);
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

        public void setCaseNumber(CaseNumber caseNumber) {
            this.caseNumber = caseNumber;
        }

        public Optional<CaseNumber> getCaseNumber() {
            return Optional.ofNullable(caseNumber);
        }

        public void setHomeAddress(Address homeAddress) {
            this.homeAddress = homeAddress;
        }

        public Optional<Address> getHomeAddress() {
            return Optional.ofNullable(homeAddress);
        }

        public void setWorkAddress(WorkAddress workAddress) {
            this.workAddress = workAddress;
        }

        public Optional<WorkAddress> getWorkAddress() {
            return Optional.ofNullable(workAddress);
        }

        public void setQuarantineAddress(QuarantineAddress quarantineAddress) {
            this.quarantineAddress = quarantineAddress;
        }

        public Optional<QuarantineAddress> getQuarantineAddress() {
            return Optional.ofNullable(quarantineAddress);
        }

        public void setShnPeriod(ShnPeriod shnPeriod) {
            this.shnPeriod = shnPeriod;
        }

        public Optional<ShnPeriod> getShnPeriod() {
            return Optional.ofNullable(shnPeriod);
        }

        public void setNextOfKinName(NextOfKinName nextOfKinName) {
            this.nextOfKinName = nextOfKinName;
        }

        public Optional<NextOfKinName> getNextOfKinName() {
            return Optional.ofNullable(nextOfKinName);
        }

        public void setNextOfKinPhone(NextOfKinPhone nextOfKinPhone) {
            this.nextOfKinPhone = nextOfKinPhone;
        }

        public Optional<NextOfKinPhone> getNextOfKinPhone() {
            return Optional.ofNullable(nextOfKinPhone);
        }

        public void setNextOfKinAddress(NextOfKinAddress nextOfKinAddress) {
            this.nextOfKinAddress = nextOfKinAddress;
        }

        public Optional<NextOfKinAddress> getNextOfKinAddress() {
            return Optional.ofNullable(nextOfKinAddress);
        }

        public void setCallStatus(CallStatus callStatus) {
            this.callStatus = callStatus;
        }

        public Optional<CallStatus> getCallStatus() {
            return Optional.ofNullable(callStatus);
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
                    && getCaseNumber().equals(e.getCaseNumber())
                    && getHomeAddress().equals(e.getHomeAddress())
                    && getWorkAddress().equals(e.getWorkAddress())
                    && getQuarantineAddress().equals(e.getQuarantineAddress())
                    && getShnPeriod().equals(e.getShnPeriod())
                    && getNextOfKinName().equals(e.getNextOfKinName())
                    && getNextOfKinPhone().equals(e.getNextOfKinPhone())
                    && getNextOfKinAddress().equals(e.getNextOfKinAddress())
                    && getCallStatus().equals(e.getCallStatus());
        }

        @Override
        public String toString() {
            final StringBuilder builder = new StringBuilder();
            getName().ifPresent(n -> builder.append("; Name: ").append(n));
            getPhone().ifPresent(p -> builder.append("; Phone: ").append(p));
            getEmail().ifPresent(e -> builder.append("; Email: ").append(e));
            getCaseNumber().ifPresent(cn -> builder.append("; Case Number: ").append(cn));
            getHomeAddress().ifPresent(ha -> builder.append("; Home Address: ").append(ha));
            getWorkAddress().flatMap(wa -> wa.value)
                    .ifPresent(wa -> builder.append("; Work Address: ").append(wa));
            getQuarantineAddress().flatMap(qa -> qa.value)
                    .ifPresent(qa -> builder.append("; Quarantine Address: ").append(qa));
            getShnPeriod().flatMap(sh -> sh.value)
                    .ifPresent(sh -> builder.append("; SHN Period: ").append(sh));
            getNextOfKinName().flatMap(kn -> kn.value)
                    .ifPresent(kn -> builder.append("; Next-of-Kin's Name: ").append(kn));
            getNextOfKinPhone().flatMap(kp -> kp.value)
                    .ifPresent(kp -> builder.append("; Next-of-Kin's Phone: ").append(kp));
            getNextOfKinAddress().flatMap(ka -> ka.value)
                    .ifPresent(ka -> builder.append("; Next-of-Kin's Address: ").append(ka));

            return builder.toString();
        }
    }
}
