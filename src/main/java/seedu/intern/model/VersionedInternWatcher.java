package seedu.intern.model;

import static seedu.intern.logic.commands.RedoCommand.MESSAGE_NO_REDO;
import static seedu.intern.logic.commands.UndoCommand.MESSAGE_NO_UNDO;

import java.util.ArrayList;
import java.util.List;

import seedu.intern.logic.commands.exceptions.CommandException;

//Solution adapted from
//https://github.com/AY2021S1-CS2103T-W11-4/tp/blob/master/src/main/java/seedu/address/model/VersionedCliniCal.java
//Reused code by @eugene3231 with minor modifications.
/**
 * {@code InternWatcher} that keeps track of a list of its previous states.
 *
 */
public class VersionedInternWatcher extends InternWatcher {
    private List<ReadOnlyInternWatcher> watcherStateList;
    private List<String> commandHistory;
    private int currStatePointer;

    /**
     * Initialises a VersionedInternWatcher with the initial state.
     * @param initialState
     */
    public VersionedInternWatcher(ReadOnlyInternWatcher initialState) {
        super(initialState);

        this.currStatePointer = 0;
        this.watcherStateList = new ArrayList<>();
        this.commandHistory = new ArrayList<>();
        this.watcherStateList.add(new InternWatcher(initialState));
        this.commandHistory.add("Initial State");
    }

    /**
     * Saves a copy of the current InternWatcher state to the watcherStateList.
     */
    public void commitState(String commitMessage) {
        int listSize = watcherStateList.size();
        watcherStateList.subList(currStatePointer + 1, listSize).clear();
        commandHistory.subList(currStatePointer + 1, listSize).clear();
        watcherStateList.add(new InternWatcher(this));
        commandHistory.add(commitMessage);
        currStatePointer++;
    }

    /**
     * Restores the InternWatcher to a previous state in the watcherStateList.
     */
    public String undo() throws CommandException {
        if (!canUndo()) {
            throw new CommandException(MESSAGE_NO_UNDO);
        }
        currStatePointer--;
        resetData(watcherStateList.get(currStatePointer));
        return commandHistory.get(currStatePointer + 1);
    }

    /**
     * Restores the InternWatcher to a previously undone state in the watcherStateList.
     */
    public String redo() throws CommandException {
        if (!canRedo()) {
            throw new CommandException(MESSAGE_NO_REDO);
        }
        currStatePointer++;
        resetData(watcherStateList.get(currStatePointer));
        return commandHistory.get(currStatePointer);
    }

    /**
     * Return true if there is a previous state to restore in the watcherStateList.
     * @return if undo is possible.
     */
    public boolean canUndo() {
        return currStatePointer > 0;
    }

    /**
     * Return true if there is a previously undone state to restore in the watcherStateList.
     * @return if redo is possible.
     */
    public boolean canRedo() {
        return currStatePointer < watcherStateList.size() - 1;
    }
}
