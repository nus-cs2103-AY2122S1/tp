package seedu.address.logic.commands;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.CollectionUtil;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.position.Description;
import seedu.address.model.position.Position;
import seedu.address.model.position.Title;


import java.util.*;

import static java.util.Objects.requireNonNull;

import static seedu.address.model.Model.PREDICATE_SHOW_ALL_POSITIONS;

public class EditPositionCommand extends Command {

    public static final String COMMAND_WORD = "edit-position";

    public static final String MESSAGE_USAGE = COMMAND_WORD  + ": Edits the details of the position identified ";

    public static final String MESSAGE_EDIT_POSITION_SUCCESS = "Edited Position: %1$s";

    public static final String MESSAGE_NOT_EDITED = "At least one field to edit must be provided.";

    public static final String MESSAGE_DUPLICATE_POSITION = "This position already exists in the position book.";

    private final Index index;
    private final EditPositionDescriptor editPositionDescriptor;


    /**
     * @param index of the position in the filtered position list to edit
     * @param editPositionDescriptor details to edit the position with
     */
    public EditPositionCommand(Index index, EditPositionDescriptor editPositionDescriptor) {
        requireNonNull(index);
        requireNonNull(editPositionDescriptor);

        this.index = index;
        this.editPositionDescriptor = new EditPositionDescriptor(editPositionDescriptor);
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Position> lastShownList = model.getFilteredPositionList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_POSITION_DISPLAYED_INDEX);
        }

        Position positionToEdit = lastShownList.get(index.getZeroBased());
        Position editedPosition = createEditedPosition(positionToEdit, editPositionDescriptor);

        if (!positionToEdit.isSamePosition(editedPosition) && model.hasPosition(editedPosition)) {
            throw new CommandException(MESSAGE_DUPLICATE_POSITION);
        }

        model.setPosition(positionToEdit, editedPosition);
        model.updateApplicantsWithPosition(positionToEdit, editedPosition);
        model.updateFilteredPositionList(PREDICATE_SHOW_ALL_POSITIONS);
        return new CommandResult(String.format(MESSAGE_EDIT_POSITION_SUCCESS, editedPosition));
    }

    /**
     * Creates and returns a {@code Position} with the details of {@code positionToEdit}
     * edited with {@code editPositionDescriptor}.
     */
    private static Position createEditedPosition(Position positionToEdit, EditPositionDescriptor editPositionDescriptor) {
        assert positionToEdit != null;

        Title updatedTitle = editPositionDescriptor.getTitle().orElse(positionToEdit.getTitle());
        Description updatedDescription = editPositionDescriptor.getDescription().orElse(positionToEdit.getDescription());
        return new Position(updatedTitle, updatedDescription);
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
        private Description description;

        public EditPositionDescriptor() {}

        /**
         * Copy constructor.
         * A defensive copy of {@code tags} is used internally.
         */
        public EditPositionDescriptor(EditPositionDescriptor toCopy) {
            setTitle(toCopy.title);
            setDescription(toCopy.description);
        }

        /**
         * Returns true if at least one field is edited.
         */
        public boolean isAnyFieldEdited() {
            return CollectionUtil.isAnyNonNull(title, description);
        }

        public void setTitle(Title title) {
            this.title = title;
        }

        public Optional<Title> getTitle() {
            return Optional.ofNullable(title);
        }

        public void setDescription(Description description) {
            this.description = description;
        }

        public Optional<Description> getDescription() {
            return Optional.ofNullable(description);
        }

        @Override
        public boolean equals(Object other) {
            // short circuit if same object
            if (other == this) {
                return true;
            }

            // instanceof handles nulls
            if (!(other instanceof EditPositionDescriptor)) {
                return false;
            }

            // state check
            EditPositionDescriptor e = (EditPositionDescriptor) other;

            return getTitle().equals(e.getTitle())
                    && getDescription().equals(e.getDescription());
        }

    }


}
