package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.commons.core.Messages;
import seedu.address.model.Model;

/**
 * Undo the previous modification to MTR.
 */
public class UndoCommand extends Command {
    public static final String COMMAND_WORD = "undo";

    public static final String MESSAGE_SUCCESS = "Undo the previous modification : \n";

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        String previousMessages = model.recoverHistory();
        return new CommandResult(MESSAGE_SUCCESS + previousMessages);
    }

}
