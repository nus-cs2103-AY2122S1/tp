package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Path;
import java.nio.file.Paths;

import seedu.address.commons.util.FileUtil;
import seedu.address.commons.util.JsonUtil;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.storage.JsonSerializableAddressBook;


/**
 * Imports a JSON file to the address book.
 */
public class ExportCommand extends Command {

    public static final String COMMAND_WORD = "export";

    public static final String MESSAGE_USAGE = "\n" + COMMAND_WORD
            + ": Exports the current contacts list into a file identified by the given filename.\n\n"
            + "Parameters: FILE_NAME\n\n"
            + "Example: " + COMMAND_WORD + " friends.json";

    public static final String MESSAGE_SUCCESS = "File successfully exported as JSON format to %s";
    public static final String MESSAGE_IO_ERROR =
            "Problem while writing to the file. Please try again";
    public static final String MESSAGE_WRONG_FORMAT = "File can only be exported to JSON format "
            + "and cannot contain any special characters";

    private final String outputFileName; // fileName.json

    /**
     * Creates an ExportCommand to export the current data to the specified {@code outputFileName}
     */
    public ExportCommand(String outputFileName) {
        requireNonNull(outputFileName);
        this.outputFileName = outputFileName;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        requireNonNull(model.getAddressBook());
        requireNonNull(outputFileName);
        Path outputFilePath;

        try {
            String os = System.getProperty("os.name").toLowerCase();

            if (os.contains("win")) {
                outputFilePath = Paths.get("data", outputFileName);
            } else {
                outputFilePath = Paths.get(FileUtil.getCurrentPath(), "..", "data", outputFileName).normalize();
            }


            FileUtil.createIfMissing(outputFilePath);
            JsonUtil.saveJsonFile(new JsonSerializableAddressBook(model.getAddressBook()), outputFilePath);
        } catch (IOException | URISyntaxException e) {
            throw new CommandException(MESSAGE_IO_ERROR);
        }

        return new CommandResult(String.format(MESSAGE_SUCCESS, outputFilePath));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ExportCommand // instanceof handles nulls
                && outputFileName.equals(((ExportCommand) other).outputFileName));
    }
}
