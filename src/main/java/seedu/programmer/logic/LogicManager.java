package seedu.programmer.logic;

import java.io.IOException;
import java.nio.file.Path;
import java.util.logging.Logger;

import javafx.collections.ObservableList;
import seedu.programmer.commons.core.GuiSettings;
import seedu.programmer.commons.core.LogsCenter;
import seedu.programmer.logic.commands.Command;
import seedu.programmer.logic.commands.CommandResult;
import seedu.programmer.logic.commands.exceptions.CommandException;
import seedu.programmer.logic.parser.ProgrammerErrorParser;
import seedu.programmer.logic.parser.exceptions.ParseException;
import seedu.programmer.model.Model;
import seedu.programmer.model.ReadOnlyProgrammerError;
import seedu.programmer.model.student.LabResult;
import seedu.programmer.model.student.Student;
import seedu.programmer.storage.Storage;


/**
 * The main LogicManager of the app.
 */
public class LogicManager implements Logic {
    public static final String FILE_OPS_ERROR_MESSAGE = "Could not save data to file: ";
    private final Logger logger = LogsCenter.getLogger(LogicManager.class);

    private final Model model;
    private final Storage storage;
    private final ProgrammerErrorParser programmerErrorParser;

    /**
     * Constructs a {@code LogicManager} with the given {@code Model} and {@code Storage}.
     */
    public LogicManager(Model model, Storage storage) {
        this.model = model;
        this.storage = storage;
        programmerErrorParser = new ProgrammerErrorParser();
    }

    @Override
    public CommandResult execute(String commandText) throws CommandException, ParseException {
        logger.info("----------------[USER COMMAND][" + commandText + "]");

        CommandResult commandResult;
        Command command = programmerErrorParser.parseCommand(commandText);
        commandResult = command.execute(model);

        try {
            storage.saveProgrammerError(model.getProgrammerError());
        } catch (IOException ioe) {
            throw new CommandException(FILE_OPS_ERROR_MESSAGE + ioe, ioe);
        }

        return commandResult;
    }

    @Override
    public ReadOnlyProgrammerError getProgrammerError() {
        return model.getProgrammerError();
    }

    @Override
    public ObservableList<Student> getFilteredStudentList() {
        return model.getFilteredStudentList();
    }

    @Override
    public ObservableList<LabResult> getLabResultList(Student target) {
        return model.showLabResultList(target);
    }

    @Override
    public Path getProgrammerErrorFilePath() {
        return model.getProgrammerErrorFilePath();
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
