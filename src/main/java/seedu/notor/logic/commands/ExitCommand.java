package seedu.notor.logic.commands;

import java.util.Arrays;
import java.util.List;

import seedu.notor.model.Model;

/**
 * Terminates the program.
 */
public class ExitCommand implements Command {
    public static final String COMMAND_WORD = "exit";
    public static final List<String> COMMAND_WORDS = Arrays.asList("exit", "e");

    public static final String MESSAGE_EXIT_ACKNOWLEDGEMENT = "Exiting Notor as requested ...";

    @Override
    public CommandResult execute(Model model) {
        return new CommandResult(MESSAGE_EXIT_ACKNOWLEDGEMENT, false, false, true);
    }
}
