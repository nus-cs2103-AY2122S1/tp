package seedu.address.ui;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import seedu.address.commons.core.LogsCenter;

public class CommandHistory {
    static final String DEFAULT_COMMAND = "";
    private static final int INITIAL_COUNTER_VALUE = -1;

    private final Logger logger = LogsCenter.getLogger(getClass());
    private List<String> commandHistory;
    private int counter;

    /**
     * Constructor to initialize a new {@code CommandHistory} object.
     */
    public CommandHistory() {
        commandHistory = new ArrayList<>();
        counter = INITIAL_COUNTER_VALUE;
    }

    /**
     * Adds the {@code command} to the {@code commandHistory}.
     * {@code counter} points to the most recently added command in {@code commandHistory}.
     * @param command The string to be added to the command history.
     */
    public void add(String command) {
        addCommandToHistory(command);
    }

    /**
     * Returns the next previous command according to the {@code counter} pointer.
     * Returns the {@code DEFAULT_COMMAND} if the {@code commandHistory} is empty.
     * Returns the oldest command if the {@code counter} is already pointer at the first command.
     * @return The string of the next previous command.
     */
    public String getPrevCommand() {
        if (isCommandHistoryEmpty()) {
            logger.info("There is no command history.");
            return DEFAULT_COMMAND;
        }
        String result = commandHistory.get(counter);
        if (!isFirstCommand()) {
            counter--;
        }
        logger.info("Previous Command: " + result);
        return result;
    }

    /**
     * Returns the next command according to the {@code counter} pointer.
     * Returns the {@code DEFAULT_COMMAND} if the {@code commandHistory} is empty.
     * Returns the latest command if the {@code counter} is already pointer at the last command.
     * @return The string of the next command.
     */
    public String getNextCommand() {
        if (isCommandHistoryEmpty()) {
            logger.info("There is no command history.");
            return DEFAULT_COMMAND;
        }
        String result = commandHistory.get(counter);
        if (!isLatestCommand()) {
            counter++;
        }
        logger.info("Next Command: " + result);
        return result;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if (!(other instanceof CommandHistory)) {
            return false;
        }
        CommandHistory e = (CommandHistory) other;
        return commandHistory.equals(e.commandHistory) && counter == e.counter;
    }

    private boolean isCommandHistoryEmpty() {
        return commandHistory.size() == 0;
    }

    private void setLatestCounter() {
        counter = commandHistory.size() - 1;
    }

    private void addCommandToHistory(String command) {
        commandHistory.add(command);
        setLatestCounter();
    }

    private boolean isLatestCommand() {
        return counter == commandHistory.size() - 1;
    }

    private boolean isFirstCommand() {
        return counter == 0;
    }
}
