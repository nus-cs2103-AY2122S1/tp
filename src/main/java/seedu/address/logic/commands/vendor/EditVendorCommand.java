package seedu.address.logic.commands.vendor;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_COST;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_OPERATING_HOURS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SERVICE_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.logic.parser.CliSyntax.PREFIX_VENDOR_ID;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_VENDORS;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import seedu.address.commons.core.Messages;
import seedu.address.commons.util.CollectionUtil;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.commonattributes.Email;
import seedu.address.model.commonattributes.Name;
import seedu.address.model.tag.Tag;
import seedu.address.model.vendor.Address;
import seedu.address.model.vendor.Cost;
import seedu.address.model.vendor.OperatingHours;
import seedu.address.model.vendor.Phone;
import seedu.address.model.vendor.ServiceName;
import seedu.address.model.vendor.Vendor;
import seedu.address.model.vendor.VendorId;

public class EditVendorCommand extends Command {

    public static final String COMMAND_WORD = "editvendor";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Edits the details of the vendor identified "
            + "by the vendor id of the vendor. "
            + "Existing values will be overwritten by the input values.\n"
            + "Parameters: "
            + PREFIX_VENDOR_ID + "VENDOR_ID "
            + "[" + PREFIX_NAME + "NAME] "
            + "[" + PREFIX_PHONE + "PHONE] "
            + "[" + PREFIX_EMAIL + "EMAIL] "
            + "[" + PREFIX_ADDRESS + "ADDRESS] "
            + "[" + PREFIX_COST + "COST] "
            + "[" + PREFIX_SERVICE_NAME + "SERVICE_NAME] "
            + "[" + PREFIX_OPERATING_HOURS + "OPERATING_HOURS] "
            + "[" + PREFIX_TAG + "TAG]...\n"
            + "Example: " + COMMAND_WORD + " vid/123 "
            + PREFIX_EMAIL + "johndoe@example.com "
            + PREFIX_SERVICE_NAME + "Food "
            + PREFIX_TAG + "VIP ";

    public static final String MESSAGE_EDIT_VENDOR_SUCCESS = "Edited Vendor: %1$s";
    public static final String MESSAGE_NOT_EDITED = "At least one field to edit must be provided.";
    public static final String MESSAGE_DUPLICATE_VENDOR = "This person already exists in the address book.";

    private final VendorId vendorId;
    private final EditVendorDescriptor editVendorDescriptor;

    /**
     * @param vendorId             of the vendor in the filtered vendor list to edit
     * @param editVendorDescriptor details to edit the vendor with
     */
    public EditVendorCommand(VendorId vendorId, EditVendorDescriptor editVendorDescriptor) {
        requireAllNonNull(vendorId, editVendorDescriptor);

        this.vendorId = vendorId;
        this.editVendorDescriptor = editVendorDescriptor;
    }

    /**
     * Execute the edit command.
     *
     * @param model {@code Model} which the command should operate on.
     * @return CommandResult
     * @throws CommandException
     */
    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Vendor> lastShownList = model.getFilteredVendorList();

        Vendor vendorToEdit = lastShownList
                .stream()
                .filter(p -> p.getVendorId().equals(vendorId))
                .findAny()
                .orElse(null);

        if (vendorToEdit == null) {
            throw new CommandException(Messages.MESSAGE_VENDOR_TO_EDIT_DOES_NOT_EXIST);
        }

        Vendor editedVendor = createdEditedVendor(vendorToEdit, editVendorDescriptor);

        if (!vendorToEdit.isSame(editedVendor) && model.hasVendor(editedVendor)) {
            throw new CommandException(MESSAGE_DUPLICATE_VENDOR);
        }

        model.setVendor(vendorToEdit, editedVendor);
        model.updateFilteredVendorList(PREDICATE_SHOW_ALL_VENDORS);
        return new CommandResult(String.format(MESSAGE_EDIT_VENDOR_SUCCESS, editedVendor));
    }

    /**
     * Creates and returns a {@code Vendor} with the details of {@code vendorToEdit}
     * edited with {@code editVendorDescriptor}.
     */
    private static Vendor createdEditedVendor(Vendor vendorToEdit, EditVendorDescriptor editVendorDescriptor) {
        assert vendorToEdit != null;

        Name updatedName = editVendorDescriptor.getName().orElse(vendorToEdit.getName());
        Email updatedEmail = editVendorDescriptor.getEmail().orElse(vendorToEdit.getEmail());
        Set<Tag> updatedTags = editVendorDescriptor.getTags().orElse(vendorToEdit.getTags());
        Address updatedAddress = editVendorDescriptor.getAddress().orElse(vendorToEdit.getAddress());
        Phone updatedPhone = editVendorDescriptor.getPhone().orElse(vendorToEdit.getPhone());
        VendorId updatedVendorId =
                editVendorDescriptor.getVendorId().orElse(vendorToEdit.getVendorId());
        ServiceName updatedServiceName = editVendorDescriptor.getServiceName().orElse(vendorToEdit.getServiceName());
        Cost updatedCost = editVendorDescriptor.getCost().orElse(vendorToEdit.getCost());
        OperatingHours updatedOperatingHours =
                editVendorDescriptor.getOperatingHours().orElse(vendorToEdit.getOperatingHours());

        return new Vendor(updatedName, updatedEmail, updatedTags, updatedVendorId, updatedPhone, updatedServiceName,
                updatedAddress, updatedCost, updatedOperatingHours);
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof EditVendorCommand)) {
            return false;
        }

        // state check
        EditVendorCommand e = (EditVendorCommand) other;
        return vendorId.equals(e.vendorId)
                && editVendorDescriptor.equals(e.editVendorDescriptor);
    }

    /**
     * Stores the details to edit the vendor with. Each non-empty field value will replace the
     * corresponding field value of the vendor.
     */
    public static class EditVendorDescriptor {
        private Name name;
        private Email email;
        private Set<Tag> tags;
        private Phone phone;
        private Address address;
        private VendorId vendorId;
        private Cost cost;
        private ServiceName serviceName;
        private OperatingHours operatingHours;

        public EditVendorDescriptor() {
        }

        /**
         * Copy constructor.
         * A defensive copy of {@code tags} is used internally.
         */
        public EditVendorDescriptor(EditVendorDescriptor toCopy) {
            setName(toCopy.name);
            setEmail(toCopy.email);
            setTags(toCopy.tags);
            setPhone(toCopy.phone);
            setAddress(toCopy.address);
            setVendorId(toCopy.vendorId);
            setCost(toCopy.cost);
            setServiceName(toCopy.serviceName);
            setOperatingHours(toCopy.operatingHours);
        }

        /**
         * Returns true if at least one field is edited. Vendor ID has been left out. The explanation is similar to that
         * in EditGuestCommand.
         */
        public boolean isAnyFieldEdited() {
            return CollectionUtil.isAnyNonNull(name, phone, email, address, tags, cost, serviceName,
                    operatingHours);
        }

        public void setName(Name name) {
            this.name = name;
        }

        public Optional<Name> getName() {
            return Optional.ofNullable(name);
        }

        public void setEmail(Email email) {
            this.email = email;
        }

        public Optional<Email> getEmail() {
            return Optional.ofNullable(email);
        }

        public void setPhone(Phone phone) {
            this.phone = phone;
        }

        public Optional<Phone> getPhone() {
            return Optional.ofNullable(phone);
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

        public void setAddress(Address address) {
            this.address = address;
        }

        public Optional<Address> getAddress() {
            return Optional.ofNullable(address);
        }

        public void setVendorId(VendorId vendorId) {
            this.vendorId = vendorId;
        }

        public Optional<VendorId> getVendorId() {
            return Optional.ofNullable(vendorId);
        }

        public void setServiceName(ServiceName serviceName) {
            this.serviceName = serviceName;
        }

        public Optional<ServiceName> getServiceName() {
            return Optional.ofNullable(serviceName);
        }

        public void setCost(Cost cost) {
            this.cost = cost;
        }

        public Optional<Cost> getCost() {
            return Optional.ofNullable(cost);
        }

        public void setOperatingHours(OperatingHours operatingHours) {
            this.operatingHours = operatingHours;
        }

        public Optional<OperatingHours> getOperatingHours() {
            return Optional.ofNullable(operatingHours);
        }

        @Override
        public boolean equals(Object other) {
            // short circuit if same object
            if (other == this) {
                return true;
            }

            // instanceof handles nulls
            if (!(other instanceof EditVendorDescriptor)) {
                return false;
            }

            // state check
            EditVendorDescriptor e = (EditVendorDescriptor) other;

            return getAddress().equals(e.getAddress())
                    && getPhone().equals(e.getPhone())
                    && getVendorId().equals(e.getVendorId())
                    && getCost().equals(e.getCost())
                    && getServiceName().equals(e.getServiceName())
                    && getName().equals(e.getName())
                    && getEmail().equals(e.getEmail())
                    && getTags().equals(e.getTags())
                    && getOperatingHours().equals(e.getOperatingHours());
        }
    }

}
