package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.model.Model;
import seedu.address.model.TeachingAssistantBuddy;

/**
 * Clears TAB.
 */
public class ClearCommand extends Command {

    public static final String COMMAND_WORD = "clear";

    public static final String MESSAGE_SUCCESS = "TAB has been cleared!";


    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.setBuddy(new TeachingAssistantBuddy());
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
