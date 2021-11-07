package seedu.address.logic.commands.event;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EVENT_INDEX;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MEMBER_INDEX;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_EVENTS;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.CollectionUtil;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.module.Name;
import seedu.address.model.module.event.Event;
import seedu.address.model.module.event.EventDate;
import seedu.address.model.module.member.Member;

/**
 * Edits the details of an existing event in Ailurus.
 */
public class EeditCommand extends Command {

    public static final String COMMAND_WORD = "eedit";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Edits the details of the event identified "
            + "by the corresponding index number. "
            + "Existing values will be overwritten by the input values.\n"
            + "Parameters: "
            + PREFIX_EVENT_INDEX + "EVENT_INDEX (must be a positive integer) "
            + "[" + PREFIX_NAME + "NAME] "
            + "[" + PREFIX_DATE + "DATE] "
            + "[" + PREFIX_MEMBER_INDEX + "MEMBER_INDEX]...\n"
            + "Example: " + COMMAND_WORD + " " + PREFIX_EVENT_INDEX + " 1 "
            + PREFIX_NAME + "Freshman Orientation Week "
            + PREFIX_DATE + "11/11/2021 "
            + PREFIX_MEMBER_INDEX + "1 " + PREFIX_MEMBER_INDEX + "2";

    public static final String MESSAGE_EDIT_EVENT_SUCCESS = "Edited Event: %1$s";
    public static final String MESSAGE_NOT_EDITED = "At least one field to edit must be provided.";
    public static final String MESSAGE_DUPLICATE_EVENT = "This event already exists in Ailurus";

    private final Index index;
    private final EditEventDescriptor editEventDescriptor;
    private final Set<Index> indexSet;

    /**
     * Constructor for EeditCommand
     *
     * @param index of the event in the filtered member list to edit
     * @param editEventDescriptor details to edit the event with
     */
    public EeditCommand(Index index, EditEventDescriptor editEventDescriptor, Set<Index> indices) {
        requireAllNonNull(index, editEventDescriptor, indices);

        this.index = index;
        this.editEventDescriptor = new EditEventDescriptor(editEventDescriptor);
        this.indexSet = indices;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Event> lastShownList = model.getFilteredEventList();
        List<Member> lastShownMemberList = model.getFilteredMemberList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_EVENT_DISPLAYED_INDEX);
        }

        Event eventToEdit = lastShownList.get(index.getZeroBased());

        Set<Member> memberSet = new HashSet<>();
        Map<Member, Boolean> attendance = new HashMap<>();
        // adds all members in indexSet to participant list in event
        for (Index targetIndex : indexSet) {
            if (targetIndex.getZeroBased() >= lastShownMemberList.size()) {
                throw new CommandException(Messages.MESSAGE_INVALID_MEMBER_DISPLAYED_INDEX);
            }
            Member memberAsParticipant = lastShownMemberList.get(targetIndex.getZeroBased());
            memberSet.add(memberAsParticipant);
            boolean isParticipant = eventToEdit.isParticipatingInEvent(memberAsParticipant);
            if (isParticipant) {
                attendance.put(memberAsParticipant, eventToEdit.hasAttended(memberAsParticipant));
            } else {
                attendance.put(memberAsParticipant, false);
            }
        }
        if (!memberSet.isEmpty()) {
            editEventDescriptor.setMemberSet(memberSet);
            editEventDescriptor.setAttendance(attendance);
        }

        Event editedEvent = createEditedEvent(eventToEdit, editEventDescriptor);

        if (!eventToEdit.isSameType(editedEvent) && model.hasEvent(editedEvent)) {
            throw new CommandException(MESSAGE_DUPLICATE_EVENT);
        }

        model.setEvent(eventToEdit, editedEvent);
        model.updateFilteredEventList(PREDICATE_SHOW_ALL_EVENTS);
        return new CommandResult(String.format(MESSAGE_EDIT_EVENT_SUCCESS, editedEvent));
    }

    /**
     * Creates and returns a {@code Event} with the details of {@code eventToEdit}
     * edited with {@code editEventDescriptor}.
     */
    private static Event createEditedEvent(Event eventToEdit, EditEventDescriptor editEventDescriptor) {
        assert eventToEdit != null;

        Name updatedName = editEventDescriptor.getName().orElse(eventToEdit.getName());
        EventDate updatedDate = editEventDescriptor.getDate().orElse(eventToEdit.getDate());
        Map<Member, Boolean> updatedAttendance = editEventDescriptor.getAttendance().orElse(eventToEdit.getMap());
        return new Event(updatedName, updatedDate, updatedAttendance);
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof EeditCommand)) {
            return false;
        }

        // state check
        EeditCommand e = (EeditCommand) other;
        return index.equals(e.index)
                && editEventDescriptor.equals(e.editEventDescriptor);
    }

    /**
     * Stores the details to edit the event with. Each non-empty field value will replace the
     * corresponding field value of the event.
     */
    public static class EditEventDescriptor {
        private Name name;
        private EventDate date;
        private Set<Member> memberSet;
        private Map<Member, Boolean> attendance;

        public EditEventDescriptor() {}

        /**
         * Copy constructor.
         * A defensive copy of {@code memberSet} is used internally.
         */
        public EditEventDescriptor(EditEventDescriptor toCopy) {
            setName(toCopy.name);
            setDate(toCopy.date);
            setMemberSet(toCopy.memberSet);
            setAttendance(toCopy.attendance);
        }

        /**
         * Returns true if at least one field is edited.
         */
        public boolean isAnyFieldEdited() {
            return CollectionUtil.isAnyNonNull(name, date, memberSet, attendance);
        }

        public void setName(Name name) {
            this.name = name;
        }

        public Optional<Name> getName() {
            return Optional.ofNullable(name);
        }

        public void setDate(EventDate date) {
            this.date = date;
        }

        public Optional<EventDate> getDate() {
            return Optional.ofNullable(date);
        }
        /**
         * Sets {@code members} to this object's {@code member}.
         * A defensive copy of {@code memberSet} is used internally.
         */
        public void setMemberSet(Set<Member> memberSet) {
            this.memberSet = (memberSet != null) ? new HashSet<>(memberSet) : null;
        }

        /**
         * Returns an unmodifiable member set, which throws {@code UnsupportedOperationException}
         * if modification is attempted.
         * Returns {@code Optional#empty()} if {@code memberSet} is null.
         */
        public Optional<Set<Member>> getMemberSet() {
            return (memberSet != null) ? Optional.of(Collections.unmodifiableSet(memberSet)) : Optional.empty();
        }

        /**
         * Sets {@code booleans} to this object's {@code attendance}.
         * A defensive copy of {@code attendance} is used internally.
         */
        public void setAttendance(Map<Member, Boolean> attendance) {
            this.attendance = (attendance != null) ? new HashMap<Member, Boolean>(attendance) : null;
        }

        /**
         * Returns an unmodifiable list of booleans, which throws {@code UnsupportedOperationException}
         * if modification is attempted.
         * Returns {@code Optional#empty()} if {@code attendance} is null.
         */
        public Optional<Map<Member, Boolean>> getAttendance() {
            return (attendance != null) ? Optional.of(Collections.unmodifiableMap(attendance))
                    : Optional.empty();
        }

        @Override
        public boolean equals(Object other) {
            // short circuit if same object
            if (other == this) {
                return true;
            }

            // instanceof handles nulls
            if (!(other instanceof EditEventDescriptor)) {
                return false;
            }

            // state check
            EditEventDescriptor e = (EditEventDescriptor) other;

            return getName().equals(e.getName())
                    && getDate().equals(e.getDate())
                    && getMemberSet().equals(e.getMemberSet());
        }
    }
}
