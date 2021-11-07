package seedu.address.logic.commands.guest;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PASSPORT_NUMBER;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ROOM_NUMBER;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_GUESTS;

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
import seedu.address.model.chargeable.Chargeable;
import seedu.address.model.commonattributes.Email;
import seedu.address.model.commonattributes.Name;
import seedu.address.model.guest.Guest;
import seedu.address.model.guest.PassportNumber;
import seedu.address.model.guest.RoomNumber;
import seedu.address.model.tag.Tag;

/**
 * Edits the details of an existing person in the address book.
 */
public class EditGuestCommand extends Command {

    public static final String COMMAND_WORD = "editguest";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Edits the details of the guest identified "
            + "by the passport number of the guest. "
            + "Existing values will be overwritten by the input values.\n"
            + "Parameters:  "
            + PREFIX_PASSPORT_NUMBER + "PASSPORT_NUMBER "
            + "[" + PREFIX_NAME + "NAME] "
            + "[" + PREFIX_EMAIL + "EMAIL] "
            + "[" + PREFIX_ROOM_NUMBER + "ROOM_NUMBER] "
            + "[" + PREFIX_TAG + "TAG]...\n"
            + "Example: " + COMMAND_WORD + " pn/A12345678 "
            + PREFIX_EMAIL + "johndoe@example.com "
            + PREFIX_ROOM_NUMBER + "1233 "
            + PREFIX_TAG + "VIP ";

    public static final String MESSAGE_EDIT_GUEST_SUCCESS = "Edited Guest: %1$s";
    public static final String MESSAGE_NOT_EDITED = "At least one field to edit must be provided.";
    public static final String MESSAGE_DUPLICATE_GUEST = "This person already exists in the address book.";
    public static final String MESSAGE_DUPLICATE_ROOM = "This room number is already in use.";

    private final PassportNumber passportNumber;
    private final EditGuestDescriptor editGuestDescriptor;

    /**
     * @param passportNumber      of the person in the filtered guest list to edit
     * @param editGuestDescriptor details to edit the person with
     */
    public EditGuestCommand(PassportNumber passportNumber, EditGuestDescriptor editGuestDescriptor) {
        requireAllNonNull(passportNumber, editGuestDescriptor);

        this.passportNumber = passportNumber;
        this.editGuestDescriptor = editGuestDescriptor;
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
        List<Guest> lastShownList = model.getFilteredGuestList();

        Guest guestToEdit = lastShownList
                .stream()
                .filter(p -> p.getPassportNumber().equals(passportNumber))
                .findAny()
                .orElse(null);

        if (guestToEdit == null) {
            Optional<Guest> guestToLocate = model.getArchivedGuest(passportNumber);
            if (guestToLocate.isPresent()) {
                throw new CommandException(Messages.MESSAGE_GUEST_IS_IN_ARCHIVE);
            } else {
                throw new CommandException(Messages.MESSAGE_GUEST_TO_EDIT_DOES_NOT_EXIST);
            }
        }

        Guest editedGuest = createEditedGuest(guestToEdit, editGuestDescriptor, model);

        if (!guestToEdit.isSame(editedGuest) && model.hasGuest(editedGuest)) {
            throw new CommandException(MESSAGE_DUPLICATE_GUEST);
        }

        model.setGuest(guestToEdit, editedGuest);
        model.updateFilteredGuestList(PREDICATE_SHOW_ALL_GUESTS);
        return new CommandResult(String.format(MESSAGE_EDIT_GUEST_SUCCESS, editedGuest));
    }

    /**
     * Creates and returns a {@code Guest} with the details of {@code guestToEdit}
     * edited with {@code editGuestDescriptor}.
     */
    private static Guest createEditedGuest(Guest guestToEdit, EditGuestDescriptor editGuestDescriptor,
                                           Model model) throws CommandException {
        assert guestToEdit != null;

        Name updatedName = editGuestDescriptor.getName().orElse(guestToEdit.getName());
        Email updatedEmail = editGuestDescriptor.getEmail().orElse(guestToEdit.getEmail());
        Set<Tag> updatedTags = editGuestDescriptor.getTags().orElse(guestToEdit.getTags());
        RoomNumber updatedRoomNumber = editGuestDescriptor.getRoomNumber().orElse(guestToEdit.getRoomNumber());
        PassportNumber passportNumber =
                editGuestDescriptor.getPassportNumber().orElse(guestToEdit.getPassportNumber());
        List<Chargeable> chargeablesUsed = guestToEdit.getChargeableUsed();
        // checks that newly provided room number is not already in use by another guest
        if (model.getFilteredGuestList()
                .stream()
                .filter(v -> !v.getPassportNumber().equals(passportNumber) && v.getRoomNumber().equals(
                        updatedRoomNumber))
                .findAny()
                .orElse(null) != null) {
            throw new CommandException(MESSAGE_DUPLICATE_ROOM);
        }


        return new Guest(updatedName, updatedEmail, updatedTags, updatedRoomNumber, passportNumber, chargeablesUsed);
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof EditGuestCommand)) {
            return false;
        }

        // state check
        EditGuestCommand e = (EditGuestCommand) other;
        return passportNumber.equals(e.passportNumber)
                && editGuestDescriptor.equals(e.editGuestDescriptor);
    }

    /**
     * Stores the details to edit the guest with. Each non-empty field value will replace the
     * corresponding field value of the guest.
     */
    public static class EditGuestDescriptor {
        private Name name;
        private Email email;
        private Set<Tag> tags;
        private PassportNumber passportNumber;
        private RoomNumber roomNumber;

        public EditGuestDescriptor() {
        }

        /**
         * Creates a edit guest descriptor instance.
         *
         * @param toCopy
         */
        public EditGuestDescriptor(EditGuestDescriptor toCopy) {
            setName(toCopy.name);
            setEmail(toCopy.email);
            setTags(toCopy.tags);
            setPassportNumber(toCopy.passportNumber);
            setRoomNumber(toCopy.roomNumber);
        }

        /**
         * Returns true if at least one field is edited. Passport number has been left out as its purpose is to
         * identify the guest. If included in the implementation, a command such as:
         * <p>
         * editguest pn/A1234567 would edit the guest successfully, even though it should be throwing an exception
         * stating that at least one field should be specified for editing. See (@code EditGuestCommandParser::parse}
         * for more details.
         */
        public boolean isAnyFieldEdited() {
            return CollectionUtil.isAnyNonNull(name, email, tags, roomNumber);
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

        public void setPassportNumber(PassportNumber passportNumber) {
            this.passportNumber = passportNumber;
        }

        public Optional<PassportNumber> getPassportNumber() {
            return Optional.ofNullable(passportNumber);
        }

        public void setRoomNumber(RoomNumber roomNumber) {
            this.roomNumber = roomNumber;
        }

        public Optional<RoomNumber> getRoomNumber() {
            return Optional.ofNullable(roomNumber);
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
            if (!(other instanceof EditGuestDescriptor)) {
                return false;
            }

            // state check
            EditGuestDescriptor e = (EditGuestDescriptor) other;

            return getName().equals(e.getName())
                    && getEmail().equals(e.getEmail())
                    && getTags().equals(e.getTags())
                    && getPassportNumber().equals(e.getPassportNumber())
                    && getRoomNumber().equals(e.getRoomNumber());
        }
    }

}
