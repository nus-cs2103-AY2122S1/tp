package seedu.unify.logic;

import java.util.ArrayList;

import seedu.unify.commons.core.LogsCenter;
import seedu.unify.logic.commands.exceptions.CommandException;

public class CommandHistory {

    public static final String MESSAGE_NO_MORE_HISTORY = "";
    private static CommandHistory chInstance;
    private ArrayList<String> previousCommands;
    private int counter;

    private CommandHistory() {
        previousCommands = new ArrayList<>();
        counter = 0;
    }

    public static CommandHistory getInstance() {
        if (chInstance == null) {
            chInstance = new CommandHistory();
        }
        return chInstance;
    }

    /**
     * Add command to history
     * @param command
     */
    public void addCommandToHistory(String command) {
        previousCommands.add(command);
        LogsCenter.getLogger(CommandHistory.class).info("Added command : " + command);
        resetCounter();
    }

    /**
     * some function.
     *
     * @return some function
     * @throws CommandException
     */
    public String retrievePreviousCommand() throws CommandException {
        return retrievePreviousCommand(counter++);
    }

    /**
     * some function
     *
     * @param previousIndex
     * @return some function
     * @throws CommandException
     */
    public String retrievePreviousCommand(int previousIndex) throws CommandException {
        if (previousIndex >= previousCommands.size()) {
            throw new CommandException(MESSAGE_NO_MORE_HISTORY);
        }

        return previousCommands.get(previousCommands.size() - previousIndex - 1);
    }

    public void resetCounter() {
        counter = 0;
    }

}
