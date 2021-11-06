package seedu.siasa.logic.commands.policy;

import static java.util.Objects.requireNonNull;
import static seedu.siasa.logic.parser.CliSyntax.PREFIX_COMMISSION;
import static seedu.siasa.logic.parser.CliSyntax.PREFIX_CONTACT_INDEX;
import static seedu.siasa.logic.parser.CliSyntax.PREFIX_EXPIRY;
import static seedu.siasa.logic.parser.CliSyntax.PREFIX_PAYMENT;
import static seedu.siasa.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.siasa.logic.parser.CliSyntax.PREFIX_TITLE;
import static seedu.siasa.model.Model.PREDICATE_SHOW_ALL_POLICIES;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import seedu.siasa.commons.core.Messages;
import seedu.siasa.commons.core.index.Index;
import seedu.siasa.commons.util.CollectionUtil;
import seedu.siasa.logic.commands.Command;
import seedu.siasa.logic.commands.CommandResult;
import seedu.siasa.logic.commands.Warning;
import seedu.siasa.logic.commands.exceptions.CommandException;
import seedu.siasa.model.Model;
import seedu.siasa.model.contact.Contact;
import seedu.siasa.model.policy.Commission;
import seedu.siasa.model.policy.CoverageExpiryDate;
import seedu.siasa.model.policy.PaymentStructure;
import seedu.siasa.model.policy.Policy;
import seedu.siasa.model.policy.Title;
import seedu.siasa.model.tag.Tag;

/**
 * Edits the details of an existing policy in the SIASA.
 */
public class EditPolicyCommand extends Command {

    public static final String COMMAND_WORD = "editpolicy";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Edits the details of the policy identified "
            + "by the index number used in the displayed policy list.\n"
            + "Existing values will be overwritten by the input values.\n"
            + "Parameters: INDEX (must be a positive integer) "
            + "[" + PREFIX_TITLE + "POLICY_NAME] "
            + "[" + PREFIX_PAYMENT + "PMT_AMOUNT_CENTS [PMTS_PER_YR] [NUM_OF_PMTS]] "
            + "[" + PREFIX_COMMISSION + "COMMISSION_% NUM_OF_COMM] "
            + "[" + PREFIX_CONTACT_INDEX + "CONTACT_INDEX] "
            + "[" + PREFIX_EXPIRY + "COVERAGE_EXPIRY_DATE] "
            + "[" + PREFIX_TAG + "TAG]...\n"
            + "Example: " + COMMAND_WORD + " 1 "
            + PREFIX_TITLE + "Full Life "
            + PREFIX_EXPIRY + "2021-06-13";

    public static final String MESSAGE_EDIT_POLICY_SUCCESS = "Edited Policy: %1$s";
    public static final String MESSAGE_NOT_EDITED = "At least one field to edit must be provided";
    public static final String MESSAGE_DUPLICATE_POLICY = "This policy already exists in the SIASA";
    public static final String MESSAGE_NOT_FUTURE_EXPIRY_DATE = "Expiry Date is not in the future";

    private final Index index;
    private final EditPolicyDescriptor editPolicyDescriptor;

    /**
     * @param index of the policy in the filtered policy list to edit
     * @param editPolicyDescriptor details to edit the policy with
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
            throw new CommandException(Messages.MESSAGE_INVALID_POLICY_DISPLAYED_INDEX);
        }


        Policy policyToEdit = lastShownList.get(index.getZeroBased());
        Policy editedPolicy = createEditedPolicy(policyToEdit, editPolicyDescriptor, model);

        if (editPolicyDescriptor.expiryDate != null
                && !CoverageExpiryDate.isFutureExpiryDate(editPolicyDescriptor.expiryDate.value)) {
            boolean response = Warning.isUserConfirmingCommand(MESSAGE_NOT_FUTURE_EXPIRY_DATE);
            if (!response) {
                return new CommandResult(Messages.MESSAGE_CANCELLED_COMMAND);
            }
        }

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
        PaymentStructure updatedPaymentStructure = editPolicyDescriptor.getPaymentStructure()
                .orElse(policyToEdit.getPaymentStructure());
        CoverageExpiryDate updatedCoverageExpiryDate = editPolicyDescriptor.getCoverageExpiryDate()
                .orElse(policyToEdit.getCoverageExpiryDate().orElse(null));
        Commission updatedCommission = editPolicyDescriptor.getCommission().orElse(policyToEdit.getCommission());
        Set<Tag> updatedTags = editPolicyDescriptor.getTags().orElse(policyToEdit.getTags());

        if (updatedCommission.numberOfPayments > updatedPaymentStructure.numberOfPayments) {
            throw new CommandException(Messages.MESSAGE_INVALID_COMMISSION_NUM_OF_PMT);
        }

        Contact updatedOwner;
        if (editPolicyDescriptor.getOwnerIndex().isEmpty()) {
            updatedOwner = policyToEdit.getOwner();
        } else {
            List<Contact> lastShownList = model.getFilteredContactList();
            if (editPolicyDescriptor.getOwnerIndex().get().getZeroBased() >= lastShownList.size()) {
                throw new CommandException(Messages.MESSAGE_INVALID_CONTACT_DISPLAYED_INDEX);
            }
            updatedOwner = lastShownList.get(editPolicyDescriptor.getOwnerIndex().get().getZeroBased());
        }

        return new Policy(updatedTitle, updatedPaymentStructure, updatedCoverageExpiryDate, updatedCommission,
                updatedOwner, updatedTags);
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
     * Stores the details to edit the policy with. Each non-empty field value will replace the
     * corresponding field value of the policy.
     */
    public static class EditPolicyDescriptor {
        private Title title;
        private PaymentStructure paymentStructure;
        private CoverageExpiryDate expiryDate;
        private Commission commission;
        private Index ownerIndex;
        private Set<Tag> tags;

        public EditPolicyDescriptor() {}

        /**
         * Copy constructor.
         * A defensive copy of {@code tags} is used internally.
         */
        public EditPolicyDescriptor(EditPolicyDescriptor toCopy) {
            setTitle(toCopy.title);
            setCoverageExpiryDate(toCopy.expiryDate);
            setPaymentStructure(toCopy.paymentStructure);
            setCommission(toCopy.commission);
            setOwnerIndex(toCopy.ownerIndex);
            setTags(toCopy.tags);
        }

        /**
         * Returns true if at least one field is edited.
         */
        public boolean isAnyFieldEdited() {
            return CollectionUtil.isAnyNonNull(title, expiryDate, paymentStructure, commission, ownerIndex, tags);
        }

        public void setTitle(Title title) {
            this.title = title;
        }

        public Optional<Title> getTitle() {
            return Optional.ofNullable(title);
        }

        public void setPaymentStructure(PaymentStructure paymentStructure) {
            this.paymentStructure = paymentStructure;
        }

        public Optional<PaymentStructure> getPaymentStructure() {
            return Optional.ofNullable(paymentStructure);
        }

        public void setCoverageExpiryDate(CoverageExpiryDate expiryDate) {
            this.expiryDate = expiryDate;
        }

        public Optional<CoverageExpiryDate> getCoverageExpiryDate() {
            return Optional.ofNullable(expiryDate);
        }

        public void setCommission(Commission commission) {
            this.commission = commission;
        }

        public Optional<Commission> getCommission() {
            return Optional.ofNullable(commission);
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
                    && getCoverageExpiryDate().equals(e.getCoverageExpiryDate())
                    && getPaymentStructure().equals(e.getPaymentStructure())
                    && getCommission().equals(e.getCommission())
                    && getOwnerIndex().equals(e.getOwnerIndex());
        }
    }
}
