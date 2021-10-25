package seedu.address.model;

import java.util.ArrayList;
import java.util.List;

public class CommandHistory {

    private static final List<String> commandHistory = new ArrayList<>();
    private static final int DEFAULT_INDEX = -1;
    private static final String EMPTY_COMMAND = "";
    private static int index = DEFAULT_INDEX;

    private CommandHistory() {}

    /**
     * Adds the last {@code Command} executed into the command history.
     * @param command Last command executed
     */
    public static void addCommand(String command) {
        commandHistory.add(0, command);
        resetIndex();
    }

    /**
     * Returns the previous {@code Command} executed.
     * @return Previous command executed
     */
    public static String getPreviousCommand() {
        if (hasNoHistory()) {
            return EMPTY_COMMAND;
        }
        if (hasPreviousCommand()) {
            index++;
        }
        return commandHistory.get(index);
    }

    /**
     * Returns the next {@code Command} executed.
     * @return Next command executed
     */
    public static String getNextCommand() {
        if (hasNoHistory()) {
            return EMPTY_COMMAND;
        }
        if (!hasNextCommand()) {
            resetIndex();
            return EMPTY_COMMAND;
        }
        index--;
        return commandHistory.get(index);

    }

    private static void resetIndex() {
        index = DEFAULT_INDEX;
    }

    private static boolean hasPreviousCommand() {
        return index + 1 < commandHistory.size();

    }

    private static boolean hasNextCommand() {
        return index - 1 > DEFAULT_INDEX;

    }

    private static boolean hasNoHistory() {
        return commandHistory.isEmpty();
    }

}
