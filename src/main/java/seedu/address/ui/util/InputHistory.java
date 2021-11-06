package seedu.address.ui.util;

import java.util.ArrayList;

/**
 * Class that is responsible for containing and changing input history.
 */
public class InputHistory {
    private static InputHistory inputHistory;

    private ArrayList<String> history;
    private int currentIndex;

    /**
     * Private constructor for InputHistory.
     */
    private InputHistory() {
        this.history = new ArrayList<>();
        this.currentIndex = 0;
    }

    /**
     * Method to access InputHistory Object.
     */
    public static InputHistory getInstance() {
        if (inputHistory == null) {
            inputHistory = new InputHistory();
        }
        return inputHistory;
    }

    /**
     * Adds a given string into the back of the list and resets the index determining current position in history.
     * @param inputCommand the String to be saved in the list.
     */
    public void addToHistory(String inputCommand) {
        String command = inputCommand.trim();
        boolean isNotJustSpaces = !command.equals("");
        if (isNotJustSpaces) {
            history.add(inputCommand);
        }
        resetRecentIndex();
    }

    /**
     * Resets the position of currentIndex to be the size of history.
     */
    private void resetRecentIndex() {
        currentIndex = history.size();
    }

    /**
     * Gets the previous input based on the currentIndex.
     * @return String of the previous input.
     */
    public String getPreviousInput() {
        if (isHistoryEmpty()) {
            return "";
        }
        if (isNotLeastRecentInput()) {
            currentIndex -= 1;
        }
        return history.get(currentIndex);
    }

    /**
     * Gets the next input based on the currentIndex
     * @return String of the next input
     */
    public String getNextInput() {
        if (isHistoryEmpty() || isLast() || !isNotMostRecentInput()) {
            return "";
        }
        currentIndex += 1;
        if (isLast()) {
            return "";
        } else {
            return history.get(currentIndex);
        }

    }

    /**
     * Used to determine if currentIndex is not pointing to the bottom of history.
     * @return a Boolean on whether the current index is not equal to 0.
     */
    private boolean isNotLeastRecentInput() {
        assert currentIndex >= 0;
        return currentIndex != 0;
    }

    /**
     * Used to determine if currentIndex is not pointing to the top of history.
     * @return a Boolean on whether the current index is less than size of history - 1.
     */
    private boolean isNotMostRecentInput() {
        assert currentIndex <= history.size();
        return currentIndex <= history.size() - 1;
    }

    /**
     * Used to determine if history is empty
     * @return a Boolean on whether history is empty.
     */
    private boolean isHistoryEmpty() {
        return history.isEmpty();
    }

    /**
     * Used to determine if currentIndex is pointing to the largest index + 1
     * @return a Boolean on whether the currentIndex is equal to the size of the history.
     */
    private boolean isLast() {
        return currentIndex == history.size();
    }

    /**
     * Used to reset the singleton object to its original state.
     */
    public static void resetSingleton() {
        inputHistory = null;
    }

    @Override
    public String toString() {
        String result = "";
        for (int i = 0; i < history.size(); i++) {
            result += history.get(i) + ";";
        }
        result += currentIndex;
        return result;
    }
}
