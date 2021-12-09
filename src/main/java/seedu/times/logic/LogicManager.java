package seedu.times.logic;

import java.io.IOException;
import java.nio.file.Path;
import java.util.logging.Logger;

import javafx.collections.ObservableList;
import seedu.times.commons.core.GuiSettings;
import seedu.times.commons.core.LogsCenter;
import seedu.times.logic.commands.Command;
import seedu.times.logic.commands.CommandResult;
import seedu.times.logic.commands.exceptions.CommandException;
import seedu.times.logic.parser.TimesTableParser;
import seedu.times.logic.parser.exceptions.ParseException;
import seedu.times.model.Model;
import seedu.times.model.ReadOnlyTimesTable;
import seedu.times.model.person.Student;
import seedu.times.model.tuitionclass.TuitionClass;
import seedu.times.storage.Storage;

/**
 * The main LogicManager of the app.
 */
public class LogicManager implements Logic {
    public static final String FILE_OPS_ERROR_MESSAGE = "Could not save data to file: ";
    private final Logger logger = LogsCenter.getLogger(LogicManager.class);

    private final Model model;
    private final Storage storage;
    private final TimesTableParser timesTableParser;

    /**
     * Constructs a {@code LogicManager} with the given {@code Model} and {@code Storage}.
     */
    public LogicManager(Model model, Storage storage) {
        this.model = model;
        this.storage = storage;
        timesTableParser = new TimesTableParser();
    }

    @Override
    public CommandResult execute(String commandText) throws CommandException, ParseException {
        logger.info("----------------[USER COMMAND][" + commandText + "]");

        CommandResult commandResult;
        Command command = timesTableParser.parseCommand(commandText);
        commandResult = command.execute(model);

        try {
            storage.saveTimesTable(model.getTimesTable());
        } catch (IOException ioe) {
            throw new CommandException(FILE_OPS_ERROR_MESSAGE + ioe, ioe);
        }

        return commandResult;
    }

    @Override
    public void setCommandObserver(CommandObserver commandObserver) {
        Command.setCommandObserver(commandObserver);
    }

    @Override
    public ReadOnlyTimesTable getTimesTable() {
        return model.getTimesTable();
    }

    @Override
    public ObservableList<Student> getFilteredStudentList() {
        return model.getFilteredStudentList();
    }

    @Override
    public ObservableList<TuitionClass> getFilteredTuitionClassList() {
        return model.getFilteredTuitionClassList();
    }

    @Override
    public Path getTimesTableFilePath() {
        return model.getTimesTableFilePath();
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
