package seedu.programmer.ui;

import java.util.ArrayList;
import java.util.List;

public class CommandHistory {
    private static final List<String> commandHistory = new ArrayList<>();
    private static final String DEFAULT_COMMAND = "";
    private static int size = 0;
    private static int counter = -1;

    public static void add(String command) {
        commandHistory.add(command);
        size++;
        counter = size - 1;
    }
    
    public static String getPrevCommand() {
        if (counter == -1) {
            return DEFAULT_COMMAND;
        }
        if (counter == 0) {
            return commandHistory.get(counter);
        }
        return commandHistory.get(counter--);
    }

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
