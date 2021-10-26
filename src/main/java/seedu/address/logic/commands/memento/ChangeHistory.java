package seedu.address.logic.commands.memento;

import seedu.address.logic.commands.Command;

import java.util.Stack;

/**
 * Records the command history.
 * Refers to https://stackoverflow.com/questions/11530276/how-do-i-implement-a-simple-undo-redo-for-actions-in-java
 */
public class ChangeHistory {
    private Stack<Command> commandStack = new Stack<>();



}
