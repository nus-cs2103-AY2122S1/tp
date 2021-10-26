package seedu.address.logic.commands.memento;

import seedu.address.logic.commands.Command;
import seedu.address.model.Model;

import java.util.Stack;

/**
 * Records the command history.
 * Refers to https://stackoverflow.com/questions/11530276/how-do-i-implement-a-simple-undo-redo-for-actions-in-java
 */
public class History {
    private Stack<Command> commandStack = new Stack<>();


    public void add(Command command) {
        commandStack.push(command);
    }

    public boolean hasHistory() {
        return commandStack.size() > 0;
    }

    public Memento recoverHistory() {
        assert hasHistory() : "There is no history to recover." ;
        Command previousCommand = commandStack.pop();
        return previousCommand.getMemento();
    }


}
