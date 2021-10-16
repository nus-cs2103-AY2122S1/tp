package seedu.address.ui;

import seedu.address.commons.util.history.History;
import seedu.address.commons.util.history.StringHistory;

/**
 * CommandInput is responsible for providing methods for the user interface to save and interact with saved
 * command inputs.
 */
public class CommandInput {
    private final History<String> history = new StringHistory();
    private String[] editedHistoricalSnapshots;
    private String currentInput;
    private Cursor cursor;

    /**
     * Creates a new instance of a CommandInput.
     */
    public CommandInput() {
        reset();
    }

    /**
     * Gets the command value of the CommandInput.
     *
     * @return The command value of the CommandInput.
     */
    public String value() {
        if (cursor.isCurrentCommand()) {
            return currentInput;
        }
        return getEditedHistoricalCommand(cursor.value());
    }

    /**
     * Sets the command input that the cursor is pointing to to the provided String.
     *
     * @param string The provided String.
     */
    public void set(String string) {
        if (cursor.isCurrentCommand()) {
            currentInput = string;
        } else if (cursor.isHistoricalCommand()) {
            editedHistoricalSnapshots[cursor.value()] = string;
        }
    }

    /**
     * Returns the next command in the history.
     *
     * @return The next command in the history.
     */
    public String next() {
        cursor.next();
        return value();
    }

    /**
     * Returns the previous command in the history.
     *
     * @return The previous command in the history.
     */
    public String previous() {
        cursor.previous();
        return value();
    }

    /**
     * Saves the current command into the command history and resets.
     */
    public void save() {
        history.add(value());
        reset();
    }

    /**
     * Returns the resulting command after factoring in the temporary edits to a command that is in the
     * history.
     *
     * @param index The index of the command in the history.
     * @return The resulting command after factoring in the temporary edits.
     */
    private String getEditedHistoricalCommand(int index) {
        if (editedHistoricalSnapshots[index] != null) {
            return editedHistoricalSnapshots[index];
        }
        return history.get(index);
    }

    /**
     * Resets the temporary edits to all historical commands, the current input and the cursor.
     */
    private void reset() {
        currentInput = "";
        editedHistoricalSnapshots = new String[history.size()];
        cursor = new Cursor();
    }

    /**
     * A Cursor points to the Command currently selected.
     * {@code cursor.value() >= 0} refers to the command in {@code history} at that index.
     * {@code cursor.value() === -1} refers to the command in {@code currentInput}.
     */
    private class Cursor {
        private int value = -1;

        /**
         * Gets the current value of the Cursor.
         *
         * @return The value of the Cursor.
         */
        private int value() {
            return value;
        }

        /**
         * Returns a boolean representing whether the cursor is pointing to the current command input.
         *
         * @return The boolean representing whether the cursor is pointing to the current command input.
         */
        private boolean isCurrentCommand() {
            return value() == -1;
        }

        /**
         * Returns a boolean representing whether the cursor is pointing to a saved command input.
         *
         * @return The boolean representing whether the cursor is pointing to a saved command input.
         */
        private boolean isHistoricalCommand() {
            return value() > -1;
        }

        /**
         * Shifts the Cursor to point to the next command.
         * Calling {@code next()} when the cursor is pointing to the last command will have no effect.
         */
        private void next() {
            if (value() < history.size() - 1) {
                value++;
            }
        }

        /**
         * Shifts the Cursor to point to the previous command.
         * Calling {@code back()} when the cursor is pointing to the current command will have no effect
         */
        private void previous() {
            if (value() > -1) {
                value--;
            }
        }
    }
}
