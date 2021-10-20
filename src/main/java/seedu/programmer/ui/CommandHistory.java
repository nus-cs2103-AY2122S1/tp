package seedu.programmer.ui;

import seedu.programmer.logic.commands.AddCommand;
import seedu.programmer.logic.commands.EditCommand;

import java.util.ArrayList;
import java.util.List;

public class CommandHistory {
    private static final String DEFAULT_COMMAND = "";
    private static final int INITIAL_LIST_SIZE = 0;
    private static final int INITIAL_COUNTER_VALUE = -1;

    private List<String> commandHistory;
    private int size;
    private int counter;

    public CommandHistory() {
        commandHistory = new ArrayList<>();
        size = INITIAL_LIST_SIZE;
        counter = INITIAL_COUNTER_VALUE;
    }

    /**
     * Adds the {@code command} to the {@code commandHistory}.
     * {@code counter} resets itself to point to the most recently added command to {@code commandHistory}.
     * @param command The string to be added to the history of commands.
     */
    public void add(String command) {
        addCommandToHistory(command);
    }

    /**
     * Returns the next most recent entered command according to the {@code counter} pointer.
     * Returns the {@code DEFAULT_COMMAND} if the {@code commandHistory} is empty.
     * Returns the least recent command if the {@code counter} is already pointer at the oldest command.
     * @return The string of the next most recent entered command.
     */
    public String getPrevCommand() {
        if (isCommandHistoryEmpty()) {
            return DEFAULT_COMMAND;
        }
        if (isCounterAtFirst()) {
            return commandHistory.get(counter);
        }
        return commandHistory.get(counter--);
    }

    /**
     * Returns the next least recent entered command according to the {@code counter} pointer.
     * Returns the {@code DEFAULT_COMMAND} if the {@code commandHistory} is empty.
     * Returns the most recent command if the {@code counter} is already pointer at the latest command.
     * @return The string of the next least recent entered command.
     */
    public String getNextCommand() {
        if (isCommandHistoryEmpty()) {
            return DEFAULT_COMMAND;
        }
        if (isCounterAtLast()) {
            return commandHistory.get(counter);
        }
        return commandHistory.get(counter++);
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
        CommandHistory e = (CommandHistory) other;
        return commandHistory.equals(e.commandHistory)
                && size == e.size
                && counter == e.counter;
    }

    private boolean isCommandHistoryEmpty() {
        return size == INITIAL_LIST_SIZE;
    }

    private void increaseSizeByOne() {
        size++;
    }

    private void resetCounterToLast() {
        counter = size - 1;
    }

    private void addCommandToHistory(String command) {
        commandHistory.add(command);
        increaseSizeByOne();
        resetCounterToLast();
    }

    private boolean isCounterAtLast() {
        return counter == size - 1;
    }

    private boolean isCounterAtFirst() {
        return counter == 0;
    }
}
