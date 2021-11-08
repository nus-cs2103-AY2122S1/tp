package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ID;
import static seedu.address.logic.parser.CliSyntax.PREFIX_INDEX;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MEMBER;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_MEMBERS;

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
import seedu.address.model.Timestamp;
import seedu.address.model.member.Address;
import seedu.address.model.member.Credit;
import seedu.address.model.member.Email;
import seedu.address.model.member.Id;
import seedu.address.model.member.Member;
import seedu.address.model.member.Name;
import seedu.address.model.member.Phone;
import seedu.address.model.member.Point;
import seedu.address.model.reservation.Reservation;
import seedu.address.model.tag.Tag;
import seedu.address.model.transaction.Transaction;

/**
 * Edits the details of an existing member in the ezFoodie.
 */
public class EditMemberCommand extends EditCommand {

    /**
     * Stands for edit command.
     */
    public static final String COMMAND_WORD = "edit";

    /**
     * Stands for the message of edit command.
     */
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Edits the details of the member identified "
            + "by the index number used in the displayed member list or the member ID. "
            + "Existing values will be overwritten by the input values.\n"
            + "Parameters:\n"
            + "Edit by index number: " + PREFIX_MEMBER + " " + PREFIX_INDEX + "INDEX "
            + "(INDEX must be a positive integer) "
            + "[" + PREFIX_NAME + "NAME] "
            + "[" + PREFIX_PHONE + "PHONE] "
            + "[" + PREFIX_EMAIL + "EMAIL] "
            + "[" + PREFIX_ADDRESS + "ADDRESS]\n"
            + "Edit by member ID: " + PREFIX_MEMBER + " " + PREFIX_ID + "ID "
            + "[" + PREFIX_NAME + "NAME] "
            + "[" + PREFIX_PHONE + "PHONE] "
            + "[" + PREFIX_EMAIL + "EMAIL] "
            + "[" + PREFIX_ADDRESS + "ADDRESS]\n"
            + "Example:\n"
            + "Edit by index number: " + COMMAND_WORD + " " + PREFIX_MEMBER + " " + PREFIX_INDEX + "1 "
            + PREFIX_PHONE + "91234567 "
            + PREFIX_EMAIL + "johndoe@example.com\n"
            + "Edit by member ID: " + COMMAND_WORD + " " + PREFIX_MEMBER + " " + PREFIX_ID + "00001 "
            + PREFIX_PHONE + "91234567 "
            + PREFIX_EMAIL + "johndoe@example.com";

    /**
     * Stands for succeed message of edit member.
     */
    public static final String MESSAGE_SUCCESS = "Edited Member: %1$s";

    /**
     * Stands for message of not edited which need fields provided.
     */
    public static final String MESSAGE_NOT_EDITED = "At least one field to edit must be provided.";

    /**
     * Stands for message of duplicate message.
     */
    public static final String MESSAGE_DUPLICATE_MEMBER =
            "This member (phone or email) already exists in the ezFoodie.";

    private final Index index;
    private final Id id;
    private final EditMemberDescriptor editMemberDescriptor;

    /**
     * Constructs EditMemberCommand to edit member by index.
     *
     * @param index of the member in the updated member list to edit.
     * @param editMemberDescriptor details to edit the member with.
     */
    public EditMemberCommand(Index index, EditMemberDescriptor editMemberDescriptor) {
        requireAllNonNull(index, editMemberDescriptor);

        this.index = index;
        id = null;
        this.editMemberDescriptor = new EditMemberDescriptor(editMemberDescriptor);
    }

    /**
     * Constructs EditMemberCommand to edit member by member id.
     *
     * @param id of the member in the updated member list to edit.
     * @param editMemberDescriptor details to edit the member with.
     */
    public EditMemberCommand(Id id, EditMemberDescriptor editMemberDescriptor) {
        requireAllNonNull(id, editMemberDescriptor);

        this.id = id;
        index = null;
        this.editMemberDescriptor = new EditMemberDescriptor(editMemberDescriptor);
    }

    /**
     * Overrides and executes model.
     *
     * @param model {@code Model} which the command should operate on.
     * @return CommandResult related edit member command.
     * @throws CommandException if the user input does not conform the expected format.
     */
    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Member> lastShownList = model.getUpdatedMemberList();

        Member memberToEdit = null;
        if (index != null) {
            if (index.getZeroBased() < lastShownList.size()) {
                memberToEdit = lastShownList.get(index.getZeroBased());
            } else {
                throw new CommandException(Messages.MESSAGE_INVALID_MEMBER_DISPLAYED_INDEX);
            }
        }
        if (id != null) {
            memberToEdit = lastShownList.stream()
                    .filter(member -> id.equals(member.getId())).findAny().orElse(null);
        }
        if (memberToEdit != null) {
            Member editedMember = createEditedMember(memberToEdit, editMemberDescriptor);
            if (model.hasMember(editedMember, member -> member.getId() != editedMember.getId())) {
                throw new CommandException(MESSAGE_DUPLICATE_MEMBER);
            }
            model.setMember(memberToEdit, editedMember);
            model.updateFilteredMemberList(PREDICATE_SHOW_ALL_MEMBERS);
            return new CommandResult(String.format(MESSAGE_SUCCESS, editedMember));
        } else {
            throw new CommandException(Messages.MESSAGE_INVALID_MEMBER_DISPLAYED_ID);
        }
    }

    /**
     * Creates and returns a {@code Member} with the details of {@code memberToEdit}
     * edited with {@code editMemberDescriptor}.
     * @return Member with edited member.
     */
    private static Member createEditedMember(Member memberToEdit, EditMemberDescriptor editMemberDescriptor) {
        assert memberToEdit != null;

        Id id = memberToEdit.getId();
        Name updatedName = editMemberDescriptor.getName().orElse(memberToEdit.getName());
        Phone updatedPhone = editMemberDescriptor.getPhone().orElse(memberToEdit.getPhone());
        Email updatedEmail = editMemberDescriptor.getEmail().orElse(memberToEdit.getEmail());
        Address updatedAddress = editMemberDescriptor.getAddress().orElse(memberToEdit.getAddress());
        Timestamp timestamp = memberToEdit.getTimestamp();
        Credit credit = memberToEdit.getCredit();
        Point point = memberToEdit.getPoint();
        List<Transaction> transactions = memberToEdit.getTransactions();
        List<Reservation> reservations = memberToEdit.getReservations();
        Set<Tag> updatedTags = editMemberDescriptor.getTags().orElse(memberToEdit.getTags());

        return new Member(id, updatedName, updatedPhone, updatedEmail, updatedAddress, timestamp, credit, point,
                transactions, reservations, updatedTags);
    }

    /**
     * Overrides the equals method.
     */
    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof EditMemberCommand // instanceof handles nulls
                && (index == null || index.equals(((EditMemberCommand) other).index))
                && (id == null || id.equals(((EditMemberCommand) other).id)))
                && editMemberDescriptor.equals(((EditMemberCommand) other).editMemberDescriptor); // state check
    }

    /**
     * Stores the details to edit the member with. Each non-empty field value will replace the
     * corresponding field value of the member.
     */
    public static class EditMemberDescriptor {
        private Name name;
        private Phone phone;
        private Email email;
        private Address address;
        private Set<Tag> tags;

        public EditMemberDescriptor() {}

        /**
         * Copy constructor.
         * A defensive copy of {@code toCopy} is used internally.
         */
        public EditMemberDescriptor(EditMemberDescriptor toCopy) {
            setName(toCopy.name);
            setPhone(toCopy.phone);
            setEmail(toCopy.email);
            setAddress(toCopy.address);
            setTags(toCopy.tags);
        }

        /**
         * Returns true if at least one field is edited.
         */
        public boolean isAnyFieldEdited() {
            return CollectionUtil.isAnyNonNull(name, phone, email, address, tags);
        }

        /**
         * Sets name.
         */
        public void setName(Name name) {
            this.name = name;
        }

        /**
         * Gets name.
         */
        public Optional<Name> getName() {
            return Optional.ofNullable(name);
        }

        /**
         * Sets phone.
         */
        public void setPhone(Phone phone) {
            this.phone = phone;
        }

        /**
         * Gets phone.
         */
        public Optional<Phone> getPhone() {
            return Optional.ofNullable(phone);
        }

        /**
         * Sets email.
         */
        public void setEmail(Email email) {
            this.email = email;
        }

        /**
         * Gets email.
         */
        public Optional<Email> getEmail() {
            return Optional.ofNullable(email);
        }

        /**
         * Sets address.
         */
        public void setAddress(Address address) {
            this.address = address;
        }

        /**
         * Gets address.
         */
        public Optional<Address> getAddress() {
            return Optional.ofNullable(address);
        }

        /**
         * Sets {@code tags} to this object's {@code tags}.
         * A defensive copy of {@code tags} is used internally.
         *
         * @param tags a list of tags.
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

        /**
         * Overrides the equals method.
         */
        @Override
        public boolean equals(Object other) {
            // short circuit if same object
            if (other == this) {
                return true;
            }

            // instanceof handles nulls
            if (!(other instanceof EditMemberDescriptor)) {
                return false;
            }

            // state check
            EditMemberDescriptor e = (EditMemberDescriptor) other;

            return getName().equals(e.getName())
                    && getPhone().equals(e.getPhone())
                    && getEmail().equals(e.getEmail())
                    && getAddress().equals(e.getAddress())
                    && getTags().equals(e.getTags());
        }
    }
}
