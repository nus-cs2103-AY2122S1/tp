package seedu.tracker.logic;

import java.io.IOException;
import java.nio.file.Path;
import java.util.logging.Logger;

import javafx.collections.ObservableList;
import seedu.tracker.commons.core.GuiSettings;
import seedu.tracker.commons.core.LogsCenter;
import seedu.tracker.logic.commands.Command;
import seedu.tracker.logic.commands.CommandResult;
import seedu.tracker.logic.commands.exceptions.CommandException;
import seedu.tracker.logic.parser.ModuleTrackerParser;
import seedu.tracker.logic.parser.exceptions.ParseException;
import seedu.tracker.model.Model;
import seedu.tracker.model.ReadOnlyModuleTracker;
import seedu.tracker.model.module.Module;
import seedu.tracker.storage.Storage;

/**
 * The main LogicManager of the app.
 */
public class LogicManager implements Logic {
    public static final String FILE_OPS_ERROR_MESSAGE = "Could not save data to file: ";
    private final Logger logger = LogsCenter.getLogger(LogicManager.class);

    private final Model model;
    private final Storage storage;
    private final ModuleTrackerParser moduleTrackerParser;

    /**
     * Constructs a {@code LogicManager} with the given {@code Model} and {@code Storage}.
     */
    public LogicManager(Model model, Storage storage) {
        this.model = model;
        this.storage = storage;
        moduleTrackerParser = new ModuleTrackerParser();
    }

    @Override
    public CommandResult execute(String commandText) throws CommandException, ParseException {
        logger.info("----------------[USER COMMAND][" + commandText + "]");

        CommandResult commandResult;
        Command command = moduleTrackerParser.parseCommand(commandText);
        commandResult = command.execute(model);

        try {
            storage.saveModuleTracker(model.getModuleTracker());
        } catch (IOException ioe) {
            throw new CommandException(FILE_OPS_ERROR_MESSAGE + ioe, ioe);
        }

        return commandResult;
    }

    @Override
    public ReadOnlyModuleTracker getModuleTracker() {
        return model.getModuleTracker();
    }

    @Override
    public ObservableList<Module> getFilteredModuleList() {
        return model.getFilteredModuleList();
    }

    @Override
    public Path getModuleTrackerFilePath() {
        return model.getModuleTrackerFilePath();
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
