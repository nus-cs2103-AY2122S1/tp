package seedu.unify.logic;

import java.io.IOException;
import java.nio.file.Path;
import java.util.logging.Logger;

import javafx.collections.ObservableList;
import seedu.unify.commons.core.GuiSettings;
import seedu.unify.commons.core.LogsCenter;
import seedu.unify.logic.commands.Command;
import seedu.unify.logic.commands.CommandResult;
import seedu.unify.logic.commands.exceptions.CommandException;
import seedu.unify.logic.parser.AddressBookParser;
import seedu.unify.logic.parser.exceptions.ParseException;
import seedu.unify.model.Model;
import seedu.unify.model.ReadOnlyUniFy;
import seedu.unify.model.task.Task;
import seedu.unify.storage.Storage;

/**
 * The main LogicManager of the app.
 */
public class LogicManager implements Logic {
    public static final String FILE_OPS_ERROR_MESSAGE = "Could not save data to file: ";
    private final Logger logger = LogsCenter.getLogger(LogicManager.class);

    private final Model model;
    private final Storage storage;
    private final AddressBookParser addressBookParser;

    /**
     * Constructs a {@code LogicManager} with the given {@code Model} and {@code Storage}.
     */
    public LogicManager(Model model, Storage storage) {
        this.model = model;
        this.storage = storage;
        addressBookParser = new AddressBookParser();
    }

    @Override
    public CommandResult execute(String commandText) throws CommandException, ParseException {
        logger.info("----------------[USER COMMAND][" + commandText + "]");

        CommandResult commandResult;
        Command command = addressBookParser.parseCommand(commandText);
        commandResult = command.execute(model);

        try {
            storage.saveUniFy(model.getUniFy());
        } catch (IOException ioe) {
            throw new CommandException(FILE_OPS_ERROR_MESSAGE + ioe, ioe);
        }

        return commandResult;
    }

    @Override
    public ReadOnlyUniFy getUniFy() {
        return model.getUniFy();
    }

    @Override
    public ObservableList<Task> getFilteredTaskList() {
        return model.getFilteredTaskList();
    }

    @Override
    public ObservableList<Task> getWeeklyTaskList() {
        return model.getWeeklyTaskList();
    }

    @Override
    public Path getAddressBookFilePath() {
        return model.getUniFyFilePath();
    }

    @Override
    public GuiSettings getGuiSettings() {
        return model.getGuiSettings();
    }

    @Override
    public void setGuiSettings(GuiSettings guiSettings) {
        model.setGuiSettings(guiSettings);
    }
}
