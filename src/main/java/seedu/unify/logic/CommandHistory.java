package seedu.unify.logic;

import java.util.ArrayList;

import seedu.unify.commons.core.LogsCenter;
import seedu.unify.logic.commands.exceptions.CommandException;

//@@author naranghardik16-reused
//Reused from rajobasu, a previous student
//with minor modiciations

public class CommandHistory {

    public static final String MESSAGE_NO_MORE_HISTORY = "The command history ends here.";
    private static CommandHistory chInstance;
    private final ArrayList<String> previousCommands;
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
     *
     * @param command
     */
    public void addCommandToHistory(String command) {
        previousCommands.add(command);
        LogsCenter.getLogger(CommandHistory.class).info("Added command : " + command);
        resetCounter();
    }

    /**
     * update counter to retrieve next command
     * @return counter
     * @throws CommandException
     */
    public String retrieveNextCommand() throws CommandException {
        return retrievePreviousCommand(--counter);
    }

    /**
     * update counter to retrieve previous command
     *
     * @return counter
     * @throws CommandException
     */
    public String retrievePreviousCommand() throws CommandException {
        return retrievePreviousCommand(counter++);
    }


    /**
     * Retrieve previous command using the given index
     *
     * @param previousIndex
     * @return previous command
     * @throws CommandException
     */
    public String retrievePreviousCommand(int previousIndex) throws CommandException {
        if (previousIndex >= previousCommands.size()) {
            counter--;
            throw new CommandException(MESSAGE_NO_MORE_HISTORY);
        }
        if (previousIndex < 0) {
            counter++;
            throw new CommandException(MESSAGE_NO_MORE_HISTORY);
        }

        return previousCommands.get(previousCommands.size() - previousIndex - 1);
    }

    public void resetCounter() {
        counter = 0;
    }

}
//@@author
