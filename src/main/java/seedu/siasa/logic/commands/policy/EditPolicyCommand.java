package seedu.siasa.logic.commands.policy;

import static java.util.Objects.requireNonNull;
import static seedu.siasa.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.siasa.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.siasa.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.siasa.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.siasa.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.siasa.model.Model.PREDICATE_SHOW_ALL_POLICIES;

import java.util.List;
import java.util.Optional;

import seedu.siasa.commons.core.Messages;
import seedu.siasa.commons.core.index.Index;
import seedu.siasa.commons.util.CollectionUtil;
import seedu.siasa.logic.commands.Command;
import seedu.siasa.logic.commands.CommandResult;
import seedu.siasa.logic.commands.exceptions.CommandException;
import seedu.siasa.model.Model;
import seedu.siasa.model.person.Person;
import seedu.siasa.model.policy.Commission;
import seedu.siasa.model.policy.ExpiryDate;
import seedu.siasa.model.policy.Policy;
import seedu.siasa.model.policy.Price;
import seedu.siasa.model.policy.Title;

/**
 * Edits the details of an existing person in the address book.
 */
public class EditPolicyCommand extends Command {

    public static final String COMMAND_WORD = "editpolicy";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Edits the details of the policy identified "
            + "by the index number used in the displayed policy list. "
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

    public static final String MESSAGE_EDIT_POLICY_SUCCESS = "Edited Policy: %1$s";
    public static final String MESSAGE_NOT_EDITED = "At least one field to edit must be provided.";
    public static final String MESSAGE_DUPLICATE_POLICY = "This policy already exists in the address book.";

    private final Index index;
    private final EditPolicyDescriptor editPolicyDescriptor;

    /**
     * @param index of the person in the filtered person list to edit
     * @param editPolicyDescriptor details to edit the person with
     */
    public EditPolicyCommand(Index index, EditPolicyDescriptor editPolicyDescriptor) {
        requireNonNull(index);
        requireNonNull(editPolicyDescriptor);

        this.index = index;
        this.editPolicyDescriptor = new EditPolicyDescriptor(editPolicyDescriptor);
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Policy> lastShownList = model.getFilteredPolicyList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        Policy policyToEdit = lastShownList.get(index.getZeroBased());
        Policy editedPolicy = createEditedPolicy(policyToEdit, editPolicyDescriptor, model);

        if (!policyToEdit.isSamePolicy(editedPolicy) && model.hasPolicy(editedPolicy)) {
            throw new CommandException(MESSAGE_DUPLICATE_POLICY);
        }

        model.setPolicy(policyToEdit, editedPolicy);
        model.updateFilteredPolicyList(PREDICATE_SHOW_ALL_POLICIES);
        return new CommandResult(String.format(MESSAGE_EDIT_POLICY_SUCCESS, editedPolicy));
    }

    /**
     * Creates and returns a {@code Policy} with the details of {@code policyToEdit}
     * edited with {@code editPolicyDescriptor}.
     */
    private static Policy createEditedPolicy(
            Policy policyToEdit,
            EditPolicyDescriptor editPolicyDescriptor,
            Model model
    ) throws CommandException {
        assert policyToEdit != null;

        Title updatedTitle = editPolicyDescriptor.getTitle().orElse(policyToEdit.getTitle());
        Price updatedPrice = editPolicyDescriptor.getPrice().orElse(policyToEdit.getPrice());
        ExpiryDate updatedExpiryDate = editPolicyDescriptor.getExpiryDate().orElse(policyToEdit.getExpiryDate());
        Commission updatedCommission = editPolicyDescriptor.getCommission().orElse(policyToEdit.getCommission());

        Person updatedOwner;
        if (editPolicyDescriptor.getOwnerIndex().isEmpty()) {
            updatedOwner = policyToEdit.getOwner();
        } else {
            List<Person> lastShownList = model.getFilteredPersonList();
            if (editPolicyDescriptor.getOwnerIndex().get().getZeroBased() >= lastShownList.size()) {
                throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
            }
            updatedOwner = lastShownList.get(editPolicyDescriptor.getOwnerIndex().get().getZeroBased());
        }

        return new Policy(updatedTitle, updatedPrice, updatedExpiryDate, updatedCommission, updatedOwner);
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof EditPolicyCommand)) {
            return false;
        }

        // state check
        EditPolicyCommand e = (EditPolicyCommand) other;
        return index.equals(e.index)
                && editPolicyDescriptor.equals(e.editPolicyDescriptor);
    }

    /**
     * Stores the details to edit the person with. Each non-empty field value will replace the
     * corresponding field value of the person.
     */
    public static class EditPolicyDescriptor {
        private Title title;
        private Price price;
        private ExpiryDate expiryDate;
        private Commission commission;
        private Index ownerIndex;

        public EditPolicyDescriptor() {}

        /**
         * Copy constructor.
         * A defensive copy of {@code tags} is used internally.
         */
        public EditPolicyDescriptor(EditPolicyDescriptor toCopy) {
            setTitle(toCopy.title);
            setExpiryDate(toCopy.expiryDate);
            setPrice(toCopy.price);
            setCommission(toCopy.commission);
            setOwnerIndex(toCopy.ownerIndex);
        }

        /**
         * Returns true if at least one field is edited.
         */
        public boolean isAnyFieldEdited() {
            return CollectionUtil.isAnyNonNull(title, expiryDate, price, commission, ownerIndex);
        }

        public void setTitle(Title title) {
            this.title = title;
        }

        public Optional<Title> getTitle() {
            return Optional.ofNullable(title);
        }

        public void setPrice(Price price) {
            this.price = price;
        }

        public Optional<Price> getPrice() {
            return Optional.ofNullable(price);
        }

        public void setExpiryDate(ExpiryDate expiryDate) {
            this.expiryDate = expiryDate;
        }

        public Optional<ExpiryDate> getExpiryDate() {
            return Optional.ofNullable(expiryDate);
        }

        public void setCommission(Commission commission) {
            this.commission = commission;
        }

        public Optional<Commission> getCommission() {
            return Optional.ofNullable(commission);
        }

        public void setOwnerIndex(Index ownerIndex) {
            this.ownerIndex = ownerIndex;
        }

        public Optional<Index> getOwnerIndex() {
            return Optional.ofNullable(ownerIndex);
        }

        @Override
        public boolean equals(Object other) {
            // short circuit if same object
            if (other == this) {
                return true;
            }

            // instanceof handles nulls
            if (!(other instanceof EditPolicyDescriptor)) {
                return false;
            }

            // state check
            EditPolicyDescriptor e = (EditPolicyDescriptor) other;

            return getTitle().equals(e.getTitle())
                    && getExpiryDate().equals(e.getExpiryDate())
                    && getPrice().equals(e.getPrice())
                    && getCommission().equals(e.getCommission())
                    && getOwnerIndex().equals(e.getOwnerIndex());
        }
    }
}
