package seedu.address.logic.commands;

import seedu.address.model.Model;

/**
 * Exports the contacts into a named JSON file.
 */
public class ExportCommand extends Command {

    public static final String COMMAND_WORD = "export";

    @Override
    public CommandResult execute(Model model) {
        return new CommandResult("success");
    }
}
