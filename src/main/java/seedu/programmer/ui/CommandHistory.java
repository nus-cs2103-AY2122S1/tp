package seedu.programmer.ui;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import seedu.programmer.commons.core.LogsCenter;
import seedu.programmer.ui.exceptions.CommandHistoryException;

public class CommandHistory {
    private final Logger logger = LogsCenter.getLogger(getClass());
    private List<String> commandHistory;
    private int counter;

    /**
     * Constructor to initialize a new {@code CommandHistory} object.
     */
    public CommandHistory() {
        commandHistory = new ArrayList<>();
        counter = 0;
    }

    /**
     * Adds the {@code command} to the {@code commandHistory}.
     * @param command The string to be added to the history of commands.
     */
    public void add(String command) {
        addCommandToHistory(command);
    }

    /**
     * Returns the next most recently entered command according to the {@code counter} pointer.
     * Returns the least recent command if the {@code counter} is already pointer at the oldest command.
     * @return The string of the next most recent entered command.
     * @throws CommandHistoryException is thrown when the command history is empty.
     */
    public String getPrevCommand() throws CommandHistoryException {
        if (!isCounterAtFirst()) {
            counter--;
        }

        String result = commandHistory.get(counter);
        logger.info("Previous Command retrieved: " + result);
        return result;
    }

    /**
     * Returns the next least recent entered command according to the {@code counter} pointer.
     * Returns the most recent command if the {@code counter} is already pointer at the latest command.
     * @return The string of the next least recent entered command.
     * @throws CommandHistoryException is thrown when the command history is empty or when {@code getPrevCommand}
     * method has never been called yet.
     */
    public String getNextCommand() throws CommandHistoryException {
        if (!isCounterAtLast()) {
            counter++;
        }

        String result = commandHistory.get(counter);

        logger.info("Next Command retrieved: " + result);
        return result;
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof CommandHistory)) {
            return false;
        }

        // state check
        CommandHistory e = (CommandHistory) other;
        return commandHistory.equals(e.commandHistory)
                && counter == e.counter;
    }

    public boolean isCounterAtDefault() {
        return counter == commandHistory.size();
    }
    public boolean isCommandHistoryEmpty() {
        return commandHistory.size() == 0;
    }

    private void resetCounterToDefault() {
        counter = commandHistory.size();
    }

    private void addCommandToHistory(String command) {
        commandHistory.add(command);
        resetCounterToDefault();
    }

    public boolean isCounterAtLast() {
        return counter == commandHistory.size() - 1;
    }

    public boolean isCounterAtFirst() {
        return counter == 0;
    }
}
