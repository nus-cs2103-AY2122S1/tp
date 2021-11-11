package seedu.address.logic.commands;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.state.ApplicationState;
import seedu.address.model.Model;

/**
 * Undoes the previous undoable command that modified the records.
 */
public class UndoCommand implements Command {

    public static final String COMMAND_WORD = "undo";

    public static final String MESSAGE_NOTHING_UNDONE = "Nothing remaining can be undone.";

    private final ApplicationState currentApplicationState;

    /**
     * Creates a UndoCommand to undo a previous action.
     *
     * @param currentApplicationState
     */
    public UndoCommand(ApplicationState currentApplicationState) {
        this.currentApplicationState = currentApplicationState;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        // This message will not appear if something was undone and the CommandResult from its undo replaces this.
        // This means this message will only appear if nothing was undone.
        return new CommandResult.Builder(MESSAGE_NOTHING_UNDONE)
                .goCauseUndo()
                .setNextApplicationState(currentApplicationState)
                .build();
    }

    @Override
    public boolean equals(Object other) {
        if (!(other instanceof UndoCommand)) {
            return false;
        }
        return other == this;
    }
}
