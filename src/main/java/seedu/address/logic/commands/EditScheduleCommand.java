package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DESCRIPTION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_FROM;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TO;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_SCHEDULE;

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
import seedu.address.model.event.Schedule;
import seedu.address.model.tag.Tag;

public class EditScheduleCommand extends Command {
    public static final String COMMAND_WORD = "edit";
    // todo change message usage after completion of contact relationship
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Edits the details of a schedule identified "
            + "by the index number used in the displayed schedule list. "
            + "Existing values will be overwritten by the input values.\n"
            + "Parameters: INDEX (must be a positive integer) " + "[" + PREFIX_DESCRIPTION + "DESCRIPTION] " + "["
            + PREFIX_DATE + " DATE] " + "[" + PREFIX_FROM + " FROM] " + "[" + PREFIX_TO + " TO]\n " + "Example: "
            + COMMAND_WORD + " 1 " + PREFIX_DESCRIPTION + " lecture " + PREFIX_DATE + " 12/01/2021 " + PREFIX_TAG
            + " chill";

    public static final String MESSAGE_EDIT_SCHEDULE_SUCCESS = "Edited Schedule: %1$s";
    public static final String MESSAGE_NOT_EDITED = "At least one field to edit must be provided.";
    public static final String MESSAGE_DUPLICATE_SCHEDULE = "This person already exists in the address book.";

    private final Index index;
    private final EditScheduleDescriptor editScheduleDescriptor;

    /**
     * @param index                  of the schedule in the filtered schedule list
     *                               to edit
     * @param editScheduleDescriptor details to edit the schedule with
     */
    public EditScheduleCommand(Index index, EditScheduleDescriptor editScheduleDescriptor) {
        requireNonNull(index);
        requireNonNull(editScheduleDescriptor);

        this.index = index;
        this.editScheduleDescriptor = new EditScheduleDescriptor(editScheduleDescriptor);
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Schedule> lastShownList = model.getFilteredScheduleList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_SCHEDULE_DISPLAYED_INDEX);
        }

        Schedule scheduleToEdit = lastShownList.get(index.getZeroBased());
        Schedule editedSchedule = createEditedSchedule(scheduleToEdit, editScheduleDescriptor);

        if (!scheduleToEdit.isSameSchedule(editedSchedule) && model.hasSchedule(editedSchedule)) {
            throw new CommandException(MESSAGE_DUPLICATE_SCHEDULE);
        }

        model.setSchedule(scheduleToEdit, editedSchedule);
        model.updateFilteredScheduleList(PREDICATE_SHOW_ALL_SCHEDULE);
        return new CommandResult(String.format(MESSAGE_EDIT_SCHEDULE_SUCCESS, editedSchedule));
    }

    /**
     * Creates and returns a {@code Person} with the details of {@code personToEdit}
     * edited with {@code editPersonDescriptor}.
     */
    private static Schedule createEditedSchedule(Schedule scheduleToEdit,
            EditScheduleDescriptor editScheduleDescriptor) {
        assert scheduleToEdit != null;

        String updatedDescription = editScheduleDescriptor.getDescription().orElse(scheduleToEdit.getDescription());
        String updatedDate = editScheduleDescriptor.getDate().orElse(scheduleToEdit.getDate());
        String updatedTimeFrom = editScheduleDescriptor.getTimeFrom()
                .orElse(String.valueOf(scheduleToEdit.getTimeFrom()));
        String updatedTimeTo = editScheduleDescriptor.getTimeTo().orElse(String.valueOf(scheduleToEdit.getTimeTo()));
        Set<Tag> updatedTags = editScheduleDescriptor.getTags().orElse(scheduleToEdit.getTags());

        return new Schedule(updatedDescription, updatedDate, updatedTimeFrom, updatedTimeTo, false, updatedTags,
                scheduleToEdit.getRecurrType(), scheduleToEdit.getRecurrDate());
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof EditCommand)) {
            return false;
        }

        // state check
        EditScheduleCommand e = (EditScheduleCommand) other;
        return index.equals(e.index) && editScheduleDescriptor.equals(e.editScheduleDescriptor);
    }

    /**
     * Stores the details to edit the person with. Each non-empty field value will
     * replace the corresponding field value of the person.
     */
    public static class EditScheduleDescriptor {
        private String description;
        private String date;
        private String timeFrom;
        private String timeTo;
        private Set<Tag> tags;

        public EditScheduleDescriptor() {
        }

        /**
         * Copy constructor. A defensive copy of {@code tags} is used internally.
         */
        public EditScheduleDescriptor(EditScheduleDescriptor toCopy) {
            setDescription(toCopy.description);
            setDate(toCopy.date);
            setTimeFrom(toCopy.timeFrom);
            setTimeTo(toCopy.timeTo);
            setTags(toCopy.tags);
        }

        /**
         * Returns true if at least one field is edited.
         */
        public boolean isAnyFieldEdited() {
            return CollectionUtil.isAnyNonNull(description, date, timeFrom, timeTo, tags);
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public Optional<String> getDescription() {
            return Optional.ofNullable(description);
        }

        public void setDate(String date) {
            this.date = date;
        }

        public Optional<String> getDate() {
            return Optional.ofNullable(date);
        }

        public void setTimeFrom(String timeFrom) {
            this.timeFrom = timeFrom;
        }

        public Optional<String> getTimeFrom() {
            return Optional.ofNullable(timeFrom);
        }

        public void setTimeTo(String timeTo) {
            this.timeTo = timeTo;
        }

        public Optional<String> getTimeTo() {
            return Optional.ofNullable(timeTo);
        }

        /**
         * Sets {@code tags} to this object's {@code tags}. A defensive copy of
         * {@code tags} is used internally.
         */
        public void setTags(Set<Tag> tags) {
            this.tags = (tags != null) ? new HashSet<>(tags) : null;
        }

        /**
         * Returns an unmodifiable tag set, which throws
         * {@code UnsupportedOperationException} if modification is attempted. Returns
         * {@code Optional#empty()} if {@code tags} is null.
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
            if (!(other instanceof EditScheduleDescriptor)) {
                return false;
            }

            // state check
            EditScheduleDescriptor e = (EditScheduleDescriptor) other;

            return getDescription().equals(e.getDescription()) && getDate().equals(e.getDate())
                    && getTimeFrom().equals(e.getTimeFrom()) && getTimeTo().equals(e.getTimeTo());
        }
    }
}
