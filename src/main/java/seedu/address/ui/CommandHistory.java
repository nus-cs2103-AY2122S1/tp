package seedu.address.ui;

import java.util.ArrayList;
import java.util.List;

public class CommandHistory {

    private final List<String> history;
    private int historyIndex;

    public CommandHistory() {
        this.history = new ArrayList<>();
        this.historyIndex = 0;
    }

    public void addCommand(String commandString) {
        history.add(commandString);
        historyIndex = history.size();
    }

    /**
     * Gets the last user input in the history of this instance.
     * return the first input if there is no more last history.
     *
     * @return a string of user input history
     */
    public String getLastInput() {
        try {
            historyIndex = Math.max(historyIndex - 1, 0);
            return history.get(historyIndex);
        } catch (IndexOutOfBoundsException e) {
            return "";
        }
    }

    /**
     * Gets the next user input in the history of this instance.
     * return an empty string if there is no more next history.
     *
     * @return a string of user input history
     */
    public String getNextInput() {
        try {
            String nextInput = history.get(historyIndex + 1);
            historyIndex = Math.min(historyIndex + 1, history.size() - 1);
            return nextInput;
        } catch (IndexOutOfBoundsException e) {
            return "";
        }
    }
}
