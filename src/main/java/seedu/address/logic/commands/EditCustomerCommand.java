package seedu.address.logic.commands;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.CollectionUtil;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
//import seedu.address.model.person.customer.AllergyList;
import seedu.address.model.person.Address;
import seedu.address.model.person.Email;
import seedu.address.model.person.Name;
import seedu.address.model.person.Phone;
import seedu.address.model.person.customer.Customer;
import seedu.address.model.person.customer.LoyaltyPoints;
//import seedu.address.model.person.customer.SrList;
import seedu.address.model.tag.Tag;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ALLERGIES;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_LP;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SPECIALREQUESTS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_CUSTOMERS;

public class EditCustomerCommand extends Command {
    public static final String COMMAND_WORD = "edit customer";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Edits the details of the person identified "
            + "by the index number used in the displayed person list. "
            + "Existing values will be overwritten by the input values.\n"
            + "Parameters: INDEX (must be a positive integer) "
            + "[" + PREFIX_NAME + "NAME] "
            + "[" + PREFIX_PHONE + "PHONE] "
            + "[" + PREFIX_EMAIL + "EMAIL] "
            + "[" + PREFIX_ADDRESS + "ADDRESS] "
            + "[" + PREFIX_LP + "LOYALTY POINTS] "
            + "[" + PREFIX_ALLERGIES + "ALLERGIES] "
            + "[" + PREFIX_SPECIALREQUESTS + "SPECIAL REQUESTS] "
            + "[" + PREFIX_TAG + "TAG]...\n"
            + "Example: " + COMMAND_WORD + " 1 "
            + PREFIX_LP + "4000 "
            + PREFIX_ALLERGIES + "Peanuts"
            + PREFIX_PHONE + "91234567 "
            + PREFIX_EMAIL + "johndoe@example.com";

    public static final String MESSAGE_EDIT_CUSTOMER_SUCCESS = "Edited Customer: %1$s";
    public static final String MESSAGE_NOT_EDITED = "At least one field to edit must be provided.";
    public static final String MESSAGE_DUPLICATE_CUSTOMER = "This customer already exists in the address " + "book.";

    private final Index index;
    private final EditCustomerCommand.EditCustomerDescriptor editCustomerDescriptor;

    /**
     * @param index of the customer in the filtered customer list to edit
     * @param editCustomerDescriptor details to edit the customer with
     */
    public EditCustomerCommand(Index index,
                               EditCustomerCommand.EditCustomerDescriptor editCustomerDescriptor) {
        requireNonNull(index);
        requireNonNull(editCustomerDescriptor);

        this.index = index;
        this.editCustomerDescriptor = new EditCustomerCommand.EditCustomerDescriptor(editCustomerDescriptor);
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Customer> lastShownCustomerList = model.getFilteredCustomerList();

        if (index.getZeroBased() >= lastShownCustomerList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_CUSTOMER_DISPLAYED_INDEX);
        }

        Customer customerToEdit = lastShownCustomerList.get(index.getZeroBased());
        Customer editedCustomer = createEditedCustomer(customerToEdit, editCustomerDescriptor);

        if (!customerToEdit.isSamePerson(editedCustomer) && model.hasPerson(editedCustomer)) {
            throw new CommandException(MESSAGE_DUPLICATE_CUSTOMER);
        }

        model.setPerson(customerToEdit, editedCustomer);
        model.updateFilteredCustomerList(PREDICATE_SHOW_ALL_CUSTOMERS);
        return new CommandResult(String.format(MESSAGE_EDIT_CUSTOMER_SUCCESS, editedCustomer));
    }

    /**
     * Creates and returns a {@code Customer} with the details of {@code customerToEdit}
     * edited with {@code editCustomerDescriptor}.
     */
    private static Customer createEditedCustomer(Customer customerToEdit,
                                             EditCustomerCommand.EditCustomerDescriptor editCustomerDescriptor) {
        assert customerToEdit != null;

        Name updatedName = editCustomerDescriptor.getName().orElse(customerToEdit.getName());
        Phone updatedPhone = editCustomerDescriptor.getPhone().orElse(customerToEdit.getPhone());
        Email updatedEmail = editCustomerDescriptor.getEmail().orElse(customerToEdit.getEmail());
        Address updatedAddress = editCustomerDescriptor.getAddress().orElse(customerToEdit.getAddress());
        LoyaltyPoints updatedLoyaltyPoints =
                editCustomerDescriptor.getLoyaltyPoints().orElse(customerToEdit.getLoyaltyPoints());
        /** AllergyList updatedAllergies =
                editCustomerDescriptor.getAllergies().orElse(customerToEdit.getAllergies());
        SrList updatedSpecialRequests =
                editCustomerDescriptor.getSpecialRequests().orElse(customerToEdit.getSpecialRequests());*/

        Set<Tag> updatedTags = editCustomerDescriptor.getTags().orElse(customerToEdit.getTags());

        return new Customer(updatedName, updatedPhone, updatedEmail, updatedAddress, updatedLoyaltyPoints,
                updatedTags);
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof EditCustomerCommand)) {
            return false;
        }

        // state check
        EditCustomerCommand e = (EditCustomerCommand) other;
        return index.equals(e.index)
                && editCustomerDescriptor.equals(e.editCustomerDescriptor);
    }

    /**
     * Stores the details to edit the customer with. Each non-empty field value will replace the
     * corresponding field value of the customer.
     */
    public static class EditCustomerDescriptor {
        private Name name;
        private Phone phone;
        private Email email;
        private Address address;
        private LoyaltyPoints loyaltyPoints;
        /**private AllergyList allergies;
        private SrList specialRequests;*/
        private Set<Tag> tags;

        public EditCustomerDescriptor() {}

        /**
         * Copy constructor.
         * A defensive copy of {@code tags} is used internally.
         */
        public EditCustomerDescriptor(EditCustomerCommand.EditCustomerDescriptor toCopy) {
            setName(toCopy.name);
            setPhone(toCopy.phone);
            setEmail(toCopy.email);
            setAddress(toCopy.address);
            setLoyaltyPoints(toCopy.loyaltyPoints);
            /**setAllergies(toCopy.allergies);
            setSpecialRequests(toCopy.specialRequests);*/
            setTags(toCopy.tags);
        }

        /**
         * Returns true if at least one field is edited.
         */
        public boolean isAnyFieldEdited() {
            return CollectionUtil.isAnyNonNull(name, phone, email, address, loyaltyPoints, tags);
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

        public void setLoyaltyPoints(LoyaltyPoints loyaltyPoints) {
            this.loyaltyPoints = loyaltyPoints;
        }

        public Optional<LoyaltyPoints> getLoyaltyPoints() {
            return Optional.ofNullable(loyaltyPoints);
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
            if (!(other instanceof EditCustomerCommand.EditCustomerDescriptor)) {
                return false;
            }

            // state check
            EditCustomerCommand.EditCustomerDescriptor e = (EditCustomerCommand.EditCustomerDescriptor) other;

            return getName().equals(e.getName())
                    && getPhone().equals(e.getPhone())
                    && getEmail().equals(e.getEmail())
                    && getAddress().equals(e.getAddress())
                    && getLoyaltyPoints().equals(e.getLoyaltyPoints())
                    && getTags().equals(e.getTags());
        }
    }
}

