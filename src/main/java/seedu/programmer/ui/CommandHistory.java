package seedu.programmer.ui;

import java.util.ArrayList;
import java.util.List;

public class CommandHistory {
    private static final List<String> commandHistory = new ArrayList<>();
    private static final String DEFAULT_COMMAND = "";
    private static int size = 0;
    private static int counter = -1;

    /**
     * Adds the {@code command} to the {@code commandHistory}.
     * {@code counter} resets itself to point to the most recently added command to {@code commandHistory}.
     * @param command The string to be added to the history of commands.
     */
    public static void add(String command) {
        commandHistory.add(command);
        size++;
        counter = size - 1;
    }

    /**
     * Returns the next most recent entered command according to the {@code counter} pointer.
     * Returns the {@code DEFAULT_COMMAND} if the {@code commandHistory} is empty.
     * Returns the least recent command if the {@code counter} is already pointer at the oldest command.
     * @return The string of the next most recent entered command.
     */
    public static String getPrevCommand() {
        if (counter == -1) {
            return DEFAULT_COMMAND;
        }
        if (counter == 0) {
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
    public static String getNextCommand() {
        if (counter == -1) {
            return DEFAULT_COMMAND;
            
        }
        if (counter == size - 1) {
            return commandHistory.get(counter);
        }
        return commandHistory.get(counter++);
    }
}
