package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.List;
import java.util.Objects;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.event.Event;
import seedu.address.model.person.Person;

/**
 * Clears the address book with the given range.
 */
public class ClearCommand extends Command {

    public static final String COMMAND_WORD = "clear";
    public static final String MESSAGE_CLEAR_PERSON_SUCCESS =
            "The member list has been cleared with the given range!";
    public static final String MESSAGE_CLEAR_EVENT_SUCCESS =
            "The event list has been cleared with the given range!";
    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Clears the person or event list with the given index range used in the displayed list \n"
            + "Parameters: " + "[flag] (can be [-p] for member list and [-e] for event)"
            + "RANGE (must either be all or a valid range like 1-3) \n"
            + "Example - clearing all entries in member list: " + COMMAND_WORD + "-p all \n"
            + "Example - clearing entries from index 1 to 3 (inclusive) in event list: "
            + COMMAND_WORD + "-e 1-3 \n";

    public static final int PERSON_FLAG = 0;
    public static final int EVENT_FLAG = 1;

    public static final int MODE_ALL = 0;
    public static final int MODE_RANGE = 1;

    private final int flag;
    private final int mode;
    private final Index begin;
    private final Index end;

    /**
     * Constructor for delete command for person.
     * @param flag which list to operate on, 0 for Person and 1 for Event
     * @param mode which mode to operate, 0 for clearing all and 1 for clearing in a specified range
     * @param begin The beginning index (inclusive) for clearing in a specified range
     * @param end The ending index (inclusive) for clearing in a specified range
     */
    public ClearCommand(int flag, int mode, Index begin, Index end) {
        this.flag = flag;
        this.mode = mode;
        this.begin = begin;
        this.end = end;
    }

    private CommandResult executePersonClear(Model model) throws CommandException {
        int beginIndex;
        int endIndex;
        List<Person> lastShownList = model.getFilteredPersonList();
        if (mode == MODE_ALL) {
            beginIndex = 0;
            endIndex = lastShownList.size() - 1;
        } else {
            if (end.getZeroBased() >= lastShownList.size()) {
                throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
            }
            if (begin.getZeroBased() > end.getZeroBased()) {
                throw new CommandException(Messages.MESSAGE_INVALID_RANGE);
            }
            beginIndex = begin.getZeroBased();
            endIndex = end.getZeroBased();
        }

        for (int i = endIndex; i >= beginIndex; i--) {
            Person personToDelete = lastShownList.get(i);
            model.deletePerson(personToDelete);
        }
        return new CommandResult(MESSAGE_CLEAR_PERSON_SUCCESS);
    }

    private CommandResult executeEventClear(Model model) throws CommandException {
        int beginIndex;
        int endIndex;
        List<Event> lastShownList = model.getFilteredEventList();
        if (mode == MODE_ALL) {
            beginIndex = 0;
            endIndex = lastShownList.size() - 1;
        } else {
            if (end.getZeroBased() >= lastShownList.size()) {
                throw new CommandException(Messages.MESSAGE_INVALID_EVENT_DISPLAYED_INDEX);
            }
            if (begin.getZeroBased() > end.getZeroBased()) {
                throw new CommandException(Messages.MESSAGE_INVALID_RANGE);
            }
            beginIndex = begin.getZeroBased();
            endIndex = end.getZeroBased();
        }

        for (int i = endIndex; i >= beginIndex; i--) {
            Event eventToDelete = lastShownList.get(i);
            model.deleteEvent(eventToDelete);
        }
        return new CommandResult(MESSAGE_CLEAR_EVENT_SUCCESS);
    }

    /**
     * Get the beginning index of the specified range.
     *
     * @return The beginning index
     */
    public Index getBegin() {
        return begin;
    }

    /**
     * Get the ending index of the specified range.
     *
     * @return The ending index
     */
    public Index getEnd() {
        return end;
    }

    /**
     * Get the command's flag.
     *
     * @return The command's flag
     */
    public int getFlag() {
        return flag;
    }

    /**
     * Get the command's operation mode.
     *
     * @return The command's operation mode
     */
    public int getMode() {
        return mode;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        if (flag == PERSON_FLAG) {
            return executePersonClear(model);
        } else {
            return executeEventClear(model);
        }
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        } else if (other instanceof ClearCommand) {
            ClearCommand c = (ClearCommand) other;
            boolean isFlagEqual = (flag == c.getFlag());
            boolean isModeEqual = (mode == c.getMode());
            return isFlagEqual && isModeEqual
                    && Objects.equals(begin, c.getBegin())
                    && Objects.equals(end, c.getEnd());
        } else {
            return false;
        }
    }
}
