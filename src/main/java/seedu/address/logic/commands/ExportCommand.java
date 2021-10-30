package seedu.address.logic.commands;

/**
 * Exports selected contacts into a .txt file.
 */
public abstract class ExportCommand extends Command {

    public static final String COMMAND_WORD = "export";

    public static final String MESSAGE_USAGE = COMMAND_WORD
        + ": Exports all contacts / a single contact into a text file.\n"
        + "Parameters: [INDEX] \n"
        + "Example: " + COMMAND_WORD + " 3";

    public static final String MESSAGE_EXPORT_SUCCESS = "Exported your contacts to export.txt!";

    @Override
    public boolean equals(Object other) {
        return other == this; // short circuit if same object
    }
}
