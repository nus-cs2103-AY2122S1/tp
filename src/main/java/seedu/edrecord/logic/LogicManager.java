package seedu.edrecord.logic;

import java.io.IOException;
import java.nio.file.Path;
import java.util.logging.Logger;

import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import seedu.edrecord.commons.core.GuiSettings;
import seedu.edrecord.commons.core.LogsCenter;
import seedu.edrecord.logic.commands.Command;
import seedu.edrecord.logic.commands.CommandResult;
import seedu.edrecord.logic.commands.exceptions.CommandException;
import seedu.edrecord.logic.parser.EdRecordParser;
import seedu.edrecord.logic.parser.exceptions.ParseException;
import seedu.edrecord.model.Model;
import seedu.edrecord.model.ReadOnlyEdRecord;
import seedu.edrecord.model.module.Module;
import seedu.edrecord.model.person.Person;
import seedu.edrecord.storage.Storage;
import seedu.edrecord.ui.PersonListPanel;

/**
 * The main LogicManager of the app.
 */
public class LogicManager implements Logic {
    public static final String FILE_OPS_ERROR_MESSAGE = "Could not save data to file: ";
    private final Logger logger = LogsCenter.getLogger(LogicManager.class);

    private final Model model;
    private final Storage storage;
    private final EdRecordParser edRecordParser;

    /**
     * Constructs a {@code LogicManager} with the given {@code Model} and {@code Storage}.
     */
    public LogicManager(Model model, Storage storage) {
        this.model = model;
        this.storage = storage;
        edRecordParser = new EdRecordParser();
    }

    @Override
    public CommandResult execute(String commandText) throws CommandException, ParseException {
        logger.info("----------------[USER COMMAND][" + commandText + "]");

        CommandResult commandResult;
        Command command = edRecordParser.parseCommand(commandText);
        commandResult = command.execute(model);

        try {
            storage.saveEdRecord(model.getEdRecord());
            storage.saveModuleSystem(model.getModuleSystem());
        } catch (IOException ioe) {
            logger.warning("Error in writing to file: " + ioe.getMessage());
            throw new CommandException(FILE_OPS_ERROR_MESSAGE + ioe, ioe);
        }

        return commandResult;
    }

    @Override
    public ReadOnlyEdRecord getEdRecord() {
        return model.getEdRecord();
    }

    @Override
    public ObservableList<Person> getFilteredPersonList() {
        return model.getFilteredPersonList();
    }

    @Override
    public ObservableValue<PersonListPanel.View> getSelectedView() {
        return model.getSelectedView();
    }

    @Override
    public Path getEdRecordFilePath() {
        return model.getEdRecordFilePath();
    }

    @Override
    public ObservableValue<Module> getSelectedModule() {
        return model.getSelectedModule();
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
