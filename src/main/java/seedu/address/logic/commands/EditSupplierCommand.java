package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DELIVERY_DETAILS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SUPPLY_TYPE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_SUPPLIERS;

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
import seedu.address.model.person.Address;
import seedu.address.model.person.Email;
import seedu.address.model.person.Name;
import seedu.address.model.person.Phone;
import seedu.address.model.person.supplier.DeliveryDetails;
import seedu.address.model.person.supplier.Supplier;
import seedu.address.model.person.supplier.SupplyType;
import seedu.address.model.tag.Tag;

public class EditSupplierCommand extends Command {

    public static final String COMMAND_WORD = "editSupplier";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Edits the details of the supplier identified "
            + "by the index number used in the displayed supplier list. "
            + "Existing values will be overwritten by the input values.\n"
            + "Parameters: INDEX (must be a positive integer) "
            + "[" + PREFIX_NAME + "NAME] "
            + "[" + PREFIX_PHONE + "PHONE] "
            + "[" + PREFIX_EMAIL + "EMAIL] "
            + "[" + PREFIX_ADDRESS + "ADDRESS] "
            + "[" + PREFIX_TAG + "TAG] "
            + "[" + PREFIX_SUPPLY_TYPE + "SUPPLY TYPE] "
            + "[" + PREFIX_DELIVERY_DETAILS + "DELIVERY DETAILS]...\n"
            + "Example: " + COMMAND_WORD + " 1 "
            + PREFIX_PHONE + "91234567 "
            + PREFIX_EMAIL + "johndoe@example.com";

    public static final String MESSAGE_EDIT_SUPPLIER_SUCCESS = "Edited Supplier: %1$s";
    public static final String MESSAGE_NOT_EDITED = "At least one field to edit must be provided.";
    public static final String MESSAGE_DUPLICATE_SUPPLIER = "This supplier already exists in the address book.";

    private final Index index;
    private final EditSupplierCommand.EditSupplierDescriptor editSupplierDescriptor;

    /**
     * @param index of the person in the filtered person list to edit
     * @param editSupplierDescriptor details to edit the person with
     */
    public EditSupplierCommand(Index index, EditSupplierCommand.EditSupplierDescriptor editSupplierDescriptor) {
        requireNonNull(index);
        requireNonNull(editSupplierDescriptor);

        this.index = index;
        this.editSupplierDescriptor = new EditSupplierCommand.EditSupplierDescriptor(editSupplierDescriptor);
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Supplier> lastShownList = model.getFilteredSupplierList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_SUPPLIER_DISPLAYED_INDEX);
        }

        Supplier supplierToEdit = lastShownList.get(index.getZeroBased());
        Supplier editedSupplier = createEditedSupplier(supplierToEdit, editSupplierDescriptor);

        if (!supplierToEdit.isSameSupplier(editedSupplier) && model.hasSupplier(editedSupplier)) {
            throw new CommandException(MESSAGE_DUPLICATE_SUPPLIER);
        }

        model.setSupplier(supplierToEdit, editedSupplier);
        model.updateFilteredSupplierList(PREDICATE_SHOW_ALL_SUPPLIERS);
        return new CommandResult(String.format(MESSAGE_EDIT_SUPPLIER_SUCCESS, editedSupplier),
                false, false, false, true);
    }

    /**
     * Creates and returns a {@code Person} with the details of {@code personToEdit}
     * edited with {@code editPersonDescriptor}.
     */
    private static Supplier createEditedSupplier(Supplier supplierToEdit,
                                               EditSupplierCommand.EditSupplierDescriptor editSupplierDescriptor) {
        assert supplierToEdit != null;

        Name updatedName = editSupplierDescriptor.getName().orElse(supplierToEdit.getName());
        Phone updatedPhone = editSupplierDescriptor.getPhone().orElse(supplierToEdit.getPhone());
        Email updatedEmail = editSupplierDescriptor.getEmail().orElse(supplierToEdit.getEmail());
        Address updatedAddress = editSupplierDescriptor.getAddress().orElse(supplierToEdit.getAddress());
        Set<Tag> updatedTags = editSupplierDescriptor.getTags().orElse(supplierToEdit.getTags());
        SupplyType updatedSupplyType = editSupplierDescriptor.getSupplyType().orElse(supplierToEdit.getSupplyType());
        DeliveryDetails updatedDeliveryDetails =
                editSupplierDescriptor.getDeliveryDetails().orElse(supplierToEdit.getDeliveryDetails());

        return new Supplier(updatedName, updatedPhone, updatedEmail, updatedAddress, updatedTags,
                updatedSupplyType, updatedDeliveryDetails);
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof EditSupplierCommand)) {
            return false;
        }

        // state check
        EditSupplierCommand e = (EditSupplierCommand) other;
        return index.equals(e.index)
                && editSupplierDescriptor.equals(e.editSupplierDescriptor);
    }

    /**
     * Stores the details to edit the person with. Each non-empty field value will replace the
     * corresponding field value of the person.
     */
    public static class EditSupplierDescriptor {
        private Name name;
        private Phone phone;
        private Email email;
        private Address address;
        private Set<Tag> tags;
        private SupplyType supplyType;
        private DeliveryDetails deliveryDetails;

        public EditSupplierDescriptor() {}

        /**
         * Copy constructor.
         * A defensive copy of {@code tags} is used internally.
         */
        public EditSupplierDescriptor(EditSupplierCommand.EditSupplierDescriptor toCopy) {
            setName(toCopy.name);
            setPhone(toCopy.phone);
            setEmail(toCopy.email);
            setAddress(toCopy.address);
            setTags(toCopy.tags);
            setSupplyType(toCopy.supplyType);
            setDeliveryDetails(toCopy.deliveryDetails);
        }

        /**
         * Returns true if at least one field is edited.
         */
        public boolean isAnyFieldEdited() {
            return CollectionUtil.isAnyNonNull(name, phone, email, address, tags, supplyType, deliveryDetails);
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

        public void setSupplyType(SupplyType supplyType) {
            this.supplyType = supplyType;
        }

        public Optional<SupplyType> getSupplyType() {
            return Optional.ofNullable(supplyType);
        }

        public void setDeliveryDetails(DeliveryDetails deliveryDetails) {
            this.deliveryDetails = deliveryDetails;
        }

        public Optional<DeliveryDetails> getDeliveryDetails() {
            return Optional.ofNullable(deliveryDetails);
        }

        @Override
        public boolean equals(Object other) {
            // short circuit if same object
            if (other == this) {
                return true;
            }

            // instanceof handles nulls
            if (!(other instanceof EditSupplierCommand.EditSupplierDescriptor)) {
                return false;
            }

            // state check
            EditSupplierCommand.EditSupplierDescriptor e = (EditSupplierCommand.EditSupplierDescriptor) other;

            return getName().equals(e.getName())
                    && getPhone().equals(e.getPhone())
                    && getEmail().equals(e.getEmail())
                    && getAddress().equals(e.getAddress())
                    && getTags().equals(e.getTags())
                    && getSupplyType().equals(e.getSupplyType())
                    && getDeliveryDetails().equals(e.getDeliveryDetails());
        }
    }
}
