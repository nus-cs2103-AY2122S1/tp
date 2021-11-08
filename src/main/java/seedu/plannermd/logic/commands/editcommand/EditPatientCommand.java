package seedu.plannermd.logic.commands.editcommand;

import static java.util.Objects.requireNonNull;
import static seedu.plannermd.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.plannermd.logic.parser.CliSyntax.PREFIX_BIRTH_DATE;
import static seedu.plannermd.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.plannermd.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.plannermd.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.plannermd.logic.parser.CliSyntax.PREFIX_RISK;
import static seedu.plannermd.logic.parser.CliSyntax.PREFIX_TAG;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import seedu.plannermd.commons.core.Messages;
import seedu.plannermd.commons.core.index.Index;
import seedu.plannermd.commons.util.CollectionUtil;
import seedu.plannermd.logic.commands.CommandResult;
import seedu.plannermd.logic.commands.exceptions.CommandException;
import seedu.plannermd.model.Model;
import seedu.plannermd.model.patient.Patient;
import seedu.plannermd.model.patient.Risk;
import seedu.plannermd.model.person.Address;
import seedu.plannermd.model.person.BirthDate;
import seedu.plannermd.model.person.Email;
import seedu.plannermd.model.person.Name;
import seedu.plannermd.model.person.Phone;
import seedu.plannermd.model.person.Remark;
import seedu.plannermd.model.tag.Tag;

/**
 * Edits the details of an existing patient in the plannermd.
 */
public class EditPatientCommand extends EditCommand {

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Edits the details of the patient identified "
            + "by the index number used in the displayed patient list. "
            + "Existing values will be overwritten by the input values.\n"
            + "Parameters: INDEX (must be a positive integer) " + "[" + PREFIX_NAME + "NAME] " + "[" + PREFIX_PHONE
            + "PHONE] " + "[" + PREFIX_EMAIL + "EMAIL] " + "[" + PREFIX_ADDRESS + "ADDRESS] " + "[" + PREFIX_BIRTH_DATE
            + "BIRTH_DATE] " + "[" + PREFIX_TAG + "TAG]..." + "[" + PREFIX_RISK + "RISK]\n" + "Example: "
            + COMMAND_WORD + " 1 " + PREFIX_PHONE + "91234567 " + PREFIX_EMAIL + "johndoe@example.com";

    public static final String MESSAGE_EDIT_PATIENT_SUCCESS = "Edited Patient: %1$s";
    public static final String MESSAGE_NOT_EDITED = "At least one field to edit must be provided.";
    public static final String MESSAGE_DUPLICATE_PATIENT = "This patient already exists in the plannermd.";

    private final Index index;
    private final EditPatientDescriptor editPatientDescriptor;

    /**
     * Creates an EditPatientCommand.

     * @param index of the person in the filtered person list to edit
     * @param editPatientDescriptor details to edit the person with
     */
    public EditPatientCommand(Index index, EditPatientDescriptor editPatientDescriptor) {
        requireNonNull(index);
        requireNonNull(editPatientDescriptor);

        this.index = index;
        this.editPatientDescriptor = new EditPatientDescriptor(editPatientDescriptor);
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Patient> lastShownList = model.getFilteredPatientList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PATIENT_DISPLAYED_INDEX);
        }

        Patient patientToEdit = lastShownList.get(index.getZeroBased());
        Patient editedPerson = createEditedPatient(patientToEdit, editPatientDescriptor);

        if (!patientToEdit.isSamePerson(editedPerson) && model.hasPatient(editedPerson)) {
            throw new CommandException(MESSAGE_DUPLICATE_PATIENT);
        }

        model.setPatient(patientToEdit, editedPerson);
        model.editAppointmentsWithPerson(patientToEdit, editedPerson);

        return new CommandResult(String.format(MESSAGE_EDIT_PATIENT_SUCCESS, editedPerson));
    }

    /**
     * Creates and returns a {@code Patient} with the details of
     * {@code patientToEdit} edited with {@code editPatientDescriptor}.
     */
    private static Patient createEditedPatient(Patient patientToEdit, EditPatientDescriptor editPatientDescriptor) {
        assert patientToEdit != null;

        Name updatedName = editPatientDescriptor.getName().orElse(patientToEdit.getName());
        Phone updatedPhone = editPatientDescriptor.getPhone().orElse(patientToEdit.getPhone());
        Email updatedEmail = editPatientDescriptor.getEmail().orElse(patientToEdit.getEmail());
        Address updatedAddress = editPatientDescriptor.getAddress().orElse(patientToEdit.getAddress());
        BirthDate updatedBirthDate = editPatientDescriptor.getBirthDate().orElse(patientToEdit.getBirthDate());
        Set<Tag> updatedTags = editPatientDescriptor.getTags().orElse(patientToEdit.getTags());
        Risk updatedRisk = editPatientDescriptor.getRisk().orElse(patientToEdit.getRisk());
        Remark updatedRemark = patientToEdit.getRemark();

        return new Patient(updatedName, updatedPhone, updatedEmail, updatedAddress, updatedBirthDate, updatedRemark,
                updatedTags, updatedRisk);
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof EditPatientCommand)) {
            return false;
        }

        // state check
        EditPatientCommand e = (EditPatientCommand) other;
        return index.equals(e.index) && editPatientDescriptor.equals(e.editPatientDescriptor);
    }

    /**
     * Stores the details to edit the patient with. Each non-empty field value will
     * replace the corresponding field value of the patient.
     */
    public static class EditPatientDescriptor {
        private Name name;
        private Phone phone;
        private Email email;
        private Address address;
        private BirthDate birthDate;
        private Remark remark;
        private Set<Tag> tags;
        private Risk risk;

        public EditPatientDescriptor() {
        }

        /**
         * Copy constructor. A defensive copy of {@code tags} is used internally.
         */
        public EditPatientDescriptor(EditPatientDescriptor toCopy) {
            setName(toCopy.name);
            setPhone(toCopy.phone);
            setEmail(toCopy.email);
            setAddress(toCopy.address);
            setRemark(toCopy.remark);
            setTags(toCopy.tags);
            setBirthDate(toCopy.birthDate);
            setRisk(toCopy.risk);
        }

        /**
         * Returns true if at least one field is edited.
         */
        public boolean isAnyFieldEdited() {
            return CollectionUtil.isAnyNonNull(name, phone, email, address, birthDate, tags, risk);
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

        public void setBirthDate(BirthDate birthDate) {
            this.birthDate = birthDate;
        }

        public Optional<BirthDate> getBirthDate() {
            return Optional.ofNullable(birthDate);
        }

        public void setRemark(Remark remark) {
            this.remark = remark;
        }

        public Optional<Remark> getRemark() {
            return Optional.ofNullable(remark);
        }

        /**
         * Sets {@code tags} to this object's {@code tags}. A defensive copy of
         * {@code tags} is used internally.
         */
        public void setTags(Set<Tag> tags) {
            this.tags = (tags != null) ? new HashSet<>(tags) : null;
        }

        /**
         * Returns an unmodifiable tag set, which throws
         * {@code UnsupportedOperationException} if modification is attempted. Returns
         * {@code Optional#empty()} if {@code tags} is null.
         */
        public Optional<Set<Tag>> getTags() {
            return (tags != null) ? Optional.of(Collections.unmodifiableSet(tags)) : Optional.empty();
        }

        public void setRisk(Risk risk) {
            this.risk = risk;
        }

        public Optional<Risk> getRisk() {
            return Optional.ofNullable(risk);
        }

        @Override
        public boolean equals(Object other) {
            // short circuit if same object
            if (other == this) {
                return true;
            }

            // instanceof handles nulls
            if (!(other instanceof EditPatientDescriptor)) {
                return false;
            }

            // state check
            EditPatientDescriptor e = (EditPatientDescriptor) other;

            return getName().equals(e.getName()) && getPhone().equals(e.getPhone()) && getEmail().equals(e.getEmail())
                    && getAddress().equals(e.getAddress()) && getBirthDate().equals(e.getBirthDate())
                    && getRemark().equals(e.getRemark()) && getTags().equals(e.getTags())
                    && getRisk().equals(e.getRisk());
        }
    }
}
