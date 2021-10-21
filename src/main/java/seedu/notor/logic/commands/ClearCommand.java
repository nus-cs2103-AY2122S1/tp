package seedu.notor.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.Arrays;
import java.util.List;

import seedu.notor.model.Model;
import seedu.notor.model.Notor;
import seedu.notor.ui.WarningWindow;

/**
 * Clears Notor of all entries
 */
public class ClearCommand implements Command {
    public static final String COMMAND_WORD = "clear";
    public static final List<String> COMMAND_WORDS = Arrays.asList("clear", "c");

    public static final String MESSAGE_SUCCESS = "Notor has been cleared!";
    public static final String CONFIRMATION_MESSAGE = "Do you want to proceed with clearing Notor?";
    public static final String MESSAGE_CANCEL = "Clearing of Notor has been cancelled.";

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        WarningWindow warningWindow = new WarningWindow(CONFIRMATION_MESSAGE);
        warningWindow.show();
        if (warningWindow.canContinue()) {
            model.setNotor(new Notor());
            return new CommandResult(MESSAGE_SUCCESS);
        } else {
            return new CommandResult(MESSAGE_CANCEL);
        }
    }
}
