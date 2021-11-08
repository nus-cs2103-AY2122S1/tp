package seedu.address.commons.util;

import java.util.ArrayList;
import java.util.List;

public class CommandUtil {

    private static final int MAX_LENGTH = 30;
    private static final List<String> commands = new ArrayList<>();

    private static int pointer = 0;

    /**
     * Adds command to storage so that it can be retrieved.
     *
     * @param command to be added.
     */
    public static void addCommand(String command) {
        if (!commands.isEmpty() && commands.size() >= MAX_LENGTH) {
            commands.remove(0);
        }
        commands.add(command);
        pointer = commands.size();
    }

    /**
     * Gets previous command from storage.
     */
    public static String getPreCommand() {
        if (pointer > 0) {
            pointer--;
            return commands.get(pointer);
        } else {
            pointer = -1;
            return "";
        }
    }

    /**
     * Gets next command from storage.
     */
    public static String getNextCommand() {
        if (pointer < commands.size() - 1) {
            pointer++;
            return commands.get(pointer);
        } else {
            pointer = commands.size();
            return "";
        }
    }

    /**
     * Gets size of commands that stored in storage.
     */
    public static int getSize() {
        return commands.size();
    }
}
