package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.model.Model;

/**
 * Exports the contacts into a named JSON file.
 */
public class ExportCommand extends Command {

    private final String fileName;

    public ExportCommand(String fileName) {
        requireNonNull(fileName);
        this.fileName = fileName;
    }

    public static final String COMMAND_WORD = "export";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + "Exports the current list of contacts to a specified filename.\n"
            + "Parameters: FILENAME\n"
            + "Example: " + COMMAND_WORD + " friends";

    @Override
    public CommandResult execute(Model model) {
        return new CommandResult(fileName);
    }
}
