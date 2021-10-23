package seedu.address.logic.commands;

import seedu.address.model.Model;

/**
 * Format full help instructions for every command for display.
 */
public class DownloadCommand extends Command {

    public static final String COMMAND_WORD = "download";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Downloads elderly data.\n"
            + "Example: " + COMMAND_WORD;

    public static final String SHOWING_DOWNLOAD_MESSAGE = "Opened download window.";

    @Override
    public CommandResult execute(Model model) {
        return new CommandResult(SHOWING_DOWNLOAD_MESSAGE, false, false, true, false);
    }
}
