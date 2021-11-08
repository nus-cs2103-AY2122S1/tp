package seedu.address.logic.position;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_POSITION_STATUS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TITLE;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.CollectionUtil;
import seedu.address.logic.Command;
import seedu.address.logic.CommandResult;
import seedu.address.logic.candidate.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.interview.Interview;
import seedu.address.model.person.Person;
import seedu.address.model.position.Position;
import seedu.address.model.position.Position.PositionStatus;
import seedu.address.model.position.Title;

public class EditPositionCommand extends Command {
    public static final String COMMAND_WORD = "edit_p";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Edits the details of the position identified "
            + "by the index number used in the displayed position list. "
            + "Existing values will be overwritten by the input values.\n"
            + "NOTE: only one field can be edited at one time.\n"
            + "Valid status values: open, closed.\n"
            + "Parameters: INDEX (must be a positive integer) "
            + "[" + PREFIX_TITLE + "TITLE] "
            + "[" + PREFIX_POSITION_STATUS + "POSITION STATUS]\n"
            + "Example: " + COMMAND_WORD + " 1 "
            + PREFIX_POSITION_STATUS + "closed\n";

    public static final String MESSAGE_EDIT_POSITION_SUCCESS = "Edited Position: %1$s";
    public static final String MESSAGE_NOT_EDITED = "One field to edit must be provided.";
    public static final String MESSAGE_DUPLICATE_POSITION = "This position already exists in the position list.";
    public static final String MESSAGE_BOTH_FIELDS_EDITED = "Only one field can be edited at one time.";

    private final Index index;
    private final EditPositionCommand.EditPositionDescriptor editPositionDescriptor;

    /**
     * @param index of the position in the filtered position list to edit
     * @param editPositionDescriptor details to edit the position with
     */
    public EditPositionCommand(Index index, EditPositionCommand.EditPositionDescriptor editPositionDescriptor) {
        requireNonNull(index);
        requireNonNull(editPositionDescriptor);

        this.index = index;
        this.editPositionDescriptor = new EditPositionCommand.EditPositionDescriptor(editPositionDescriptor);
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Position> lastShownPositionList = model.getFilteredPositionList();
        List<Person> lastShownPersonList = model.getFilteredPersonList();
        List<Interview> lastShownInterviewList = model.getFilteredInterviewList();

        // Save updated position in the positions.json file.
        if (index.getZeroBased() >= lastShownPositionList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_POSITION_DISPLAYED_INDEX);
        }

        Position positionToEdit = lastShownPositionList.get(index.getZeroBased());
        Position editedPosition = createEditedPosition(positionToEdit, editPositionDescriptor);

        if (editPositionDescriptor.isBothFieldsEdited()) {
            throw new CommandException(MESSAGE_BOTH_FIELDS_EDITED);
        }

        if (!positionToEdit.isSamePosition(editedPosition) && model.hasPosition(editedPosition)) {
            throw new CommandException(MESSAGE_DUPLICATE_POSITION);
        }

        model.setPosition(positionToEdit, editedPosition);

        for (Person person : lastShownPersonList) {
            Set<Position> positions = person.getPositions();
            if (positions.contains(positionToEdit)) {
                person.deletePosition(positionToEdit);

                // if closing position, deletes position from candidate and
                // does not add edited position back to candidate's positions
                if (!editPositionDescriptor.getTitle().equals(Optional.empty())
                        && !editPositionDescriptor.getPositionStatus().equals(Optional.of(PositionStatus.CLOSED))) {
                    person.addPosition(editedPosition);
                }
            }
        }

        for (Interview interview : lastShownInterviewList) {
            Position interviewPosition = interview.getPosition();
            if (interviewPosition.isSamePosition(positionToEdit)) {
                interview.setPosition(editedPosition);
            }
        }

        return new CommandResult(String.format(MESSAGE_EDIT_POSITION_SUCCESS, editedPosition),
                CommandResult.CommandType.POSITION);
    }

    /**
     * Creates and returns a {@code Position} with the details of {@code positionToEdit}
     * edited with {@code editPositionDescriptor}.
     */
    private static Position createEditedPosition(
            Position positionToEdit, EditPositionCommand.EditPositionDescriptor editPositionDescriptor) {
        assert positionToEdit != null;

        Title updatedTitle = editPositionDescriptor.getTitle().orElse(positionToEdit.getTitle());
        PositionStatus updatedStatus = editPositionDescriptor.getPositionStatus().orElse(positionToEdit.getStatus());

        return new Position(updatedTitle, updatedStatus);
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof EditPositionCommand)) {
            return false;
        }

        // state check
        EditPositionCommand e = (EditPositionCommand) other;
        return index.equals(e.index)
                && editPositionDescriptor.equals(e.editPositionDescriptor);
    }

    /**
     * Stores the details to edit the position with. Each non-empty field value will replace the
     * corresponding field value of the position.
     */
    public static class EditPositionDescriptor {
        private Title title;
        private PositionStatus status;

        public EditPositionDescriptor() {}

        /**
         * Copy constructor.
         * A defensive copy of {@code tags} is used internally.
         */
        public EditPositionDescriptor(EditPositionCommand.EditPositionDescriptor toCopy) {
            setTitle(toCopy.title);
            setPositionStatus(toCopy.status);
        }

        /**
         * Returns true if at least one field is edited.
         */
        public boolean isAnyFieldEdited() {
            return CollectionUtil.isAnyNonNull(title, status);
        }

        /**
         * Returns true if both fields are edited.
         */
        public boolean isBothFieldsEdited() {
            return (CollectionUtil.isAnyNonNull(title) && CollectionUtil.isAnyNonNull(status));
        }

        public void setTitle(Title title) {
            this.title = title;
        }

        public Optional<Title> getTitle() {
            return Optional.ofNullable(title);
        }

        public void setPositionStatus(PositionStatus status) {
            this.status = status;
        }

        public Optional<PositionStatus> getPositionStatus() {
            return Optional.ofNullable(status);
        }

        @Override
        public boolean equals(Object other) {
            // short circuit if same object
            if (other == this) {
                return true;
            }

            // instanceof handles nulls
            if (!(other instanceof EditPositionCommand.EditPositionDescriptor)) {
                return false;
            }

            // state check
            EditPositionCommand.EditPositionDescriptor e = (EditPositionCommand.EditPositionDescriptor) other;

            return getTitle().equals(e.getTitle())
                    && getPositionStatus().equals(e.getPositionStatus());
        }
    }
}
