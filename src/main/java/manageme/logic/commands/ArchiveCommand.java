package manageme.logic.commands;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import manageme.logic.commands.exceptions.CommandException;
import manageme.model.Model;
import manageme.storage.Storage;

/**
 * Clear all current data and saves it into a another file.
 */
public class ArchiveCommand extends Command {

    public static final String COMMAND_WORD = "archive";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Saves a copy of the current data into a new file in the same data folder";

    public static final String FILE_OPS_ERROR_MESSAGE = "Could not save data to file: ";

    public static final String MESSAGE_ARCHIVE_SUCCESS = "Data Archived into: %s";

    private static final DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyyMMdd'T'HHmmss ");

    private static Storage storage;

    public void setStorage(Storage storage) {
        this.storage = storage;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        LocalDateTime now = LocalDateTime.now();
        Path archiveFilePath = Paths.get("data" , dtf.format(now) + "archive.json");
        try {
            storage.saveManageMe(model.getManageMe(), archiveFilePath);

        } catch (IOException ioe) {
            throw new CommandException(FILE_OPS_ERROR_MESSAGE + ioe, ioe);
        }
        return new CommandResult(String.format(MESSAGE_ARCHIVE_SUCCESS, archiveFilePath));
    }
}
