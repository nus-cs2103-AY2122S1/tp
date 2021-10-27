package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_REMARK;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_RESERVATIONS;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.CollectionUtil;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.commands.util.CommandUtil;
import seedu.address.model.Model;
import seedu.address.model.reservation.Remark;
import seedu.address.model.reservation.Reservation;
import seedu.address.model.tag.Tag;

/**
 * Edits the details of an existing reservation in the address book.
 */
public class EditReservationCommand extends Command {
    public static final String COMMAND_WORD = "editr";
    public static final String MESSAGE_USAGE = CommandUtil.formatCommandWord(COMMAND_WORD)
            + ": Edits the details of the reservation identified "
            + "by the index number used in the displayed reservation list. "
            + "Existing values will be overwritten by the input values.\n"
            + "Parameters: INDEX (must be a positive integer) "
            + "[" + PREFIX_REMARK + "REMARK] "
            + "[" + PREFIX_TAG + "TAG]...\n"
            + "Example: " + CommandUtil.formatCommandWord(COMMAND_WORD) + " 1 "
            + PREFIX_REMARK + "surprise birthday party"
            + PREFIX_TAG + "10PercentOff";
    public static final String MESSAGE_EDIT_RESERVATION_SUCCESS = "Edited reservation: %1$s";
    public static final String MESSAGE_NOT_EDITED = "At least one field to edit must be provided.";

    private final Index index;
    private final EditReservationDescriptor editReservationDescriptor;

    /**
     * Creates a command object to edit a reservation
     * @param index of the reservation in the reservation customer list to edit
     * @param editReservationDescriptor details to edit the reservation with
     */
    public EditReservationCommand(Index index, EditReservationDescriptor editReservationDescriptor) {
        requireAllNonNull(index, editReservationDescriptor);
        this.index = index;
        this.editReservationDescriptor = editReservationDescriptor;
    }

    /**
     * Executes the command and returns the result message.
     *
     * @param model {@code Model} which the command should operate on.
     * @return feedback message of the operation result for display
     * @throws CommandException If an error occurs during command execution.
     */
    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Reservation> lastShownList = model.getFilteredReservationList();
        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_RESERVATION_DISPLAYED_INDEX);
        }

        Reservation reservationToEdit = lastShownList.get(index.getZeroBased());
        Reservation editedReservation = createEditedReservation(reservationToEdit, editReservationDescriptor);

        model.setReservation(reservationToEdit, editedReservation);
        model.updateFilteredReservationList(PREDICATE_SHOW_ALL_RESERVATIONS);
        return new CommandResult(
                String.format(MESSAGE_EDIT_RESERVATION_SUCCESS, editedReservation),
                false, false, false, false, false, true
        );
    }

    /**
     * Creates and returns a {@code Reservation} with the details of {@code reservationToEdit}
     * edited with {@code editReservationDescriptor}.
     */
    private static Reservation createEditedReservation(Reservation reservationToEdit,
                                           EditReservationDescriptor editReservationDescriptor) {
        assert reservationToEdit != null;

        Remark updatedRemark = editReservationDescriptor.getRemark().orElse(reservationToEdit.getRemark());
        Set<Tag> updatedTags = editReservationDescriptor.getTags().orElse(reservationToEdit.getTags());

        return new Reservation(
                reservationToEdit.getPhone(), reservationToEdit.getNumberOfPeople(),
                reservationToEdit.getDateTime(), reservationToEdit.getTable(),
                updatedRemark, updatedTags
        );
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof EditReservationCommand)) {
            return false;
        }

        // state check
        EditReservationCommand e = (EditReservationCommand) other;
        return index.equals(e.index)
                && editReservationDescriptor.equals(e.editReservationDescriptor);
    }

    /**
     * Stores the details to edit the reservation with. Each non-empty field value will replace the
     * corresponding field value of the reservation.
     */
    public static class EditReservationDescriptor {
        private Remark remark;
        private Set<Tag> tags;

        public EditReservationDescriptor() {}

        /**
         * Copy constructor.
         * A defensive copy of {@code tags} is used internally.
         */
        public EditReservationDescriptor(EditReservationDescriptor toCopy) {
            setRemark(toCopy.remark);
            setTags(toCopy.tags);
        }

        /**
         * Returns true if at least one field is edited.
         */
        public boolean isAnyFieldEdited() {
            return CollectionUtil.isAnyNonNull(remark, tags);
        }

        public void setRemark(Remark remark) {
            this.remark = remark;
        }

        public Optional<Remark> getRemark() {
            return Optional.ofNullable(remark);
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
            if (!(other instanceof EditReservationCommand.EditReservationDescriptor)) {
                return false;
            }

            // state check
            EditReservationCommand.EditReservationDescriptor e =
                    (EditReservationCommand.EditReservationDescriptor) other;

            return getRemark().equals(e.getRemark())
                    && getTags().equals(e.getTags());
        }
    }
}
