package tutoraid.logic;

import java.io.IOException;
import java.nio.file.Path;
import java.util.logging.Logger;

import javafx.collections.ObservableList;
import tutoraid.commons.core.GuiSettings;
import tutoraid.commons.core.LogsCenter;
import tutoraid.logic.commands.Command;
import tutoraid.logic.commands.CommandResult;
import tutoraid.logic.commands.exceptions.CommandException;
import tutoraid.logic.parser.TutorAidParser;
import tutoraid.logic.parser.exceptions.ParseException;
import tutoraid.model.Model;
import tutoraid.model.ReadOnlyStudentBook;
import tutoraid.model.student.Student;
import tutoraid.storage.Storage;

/**
 * The main LogicManager of the app.
 */
public class LogicManager implements Logic {
    public static final String FILE_OPS_ERROR_MESSAGE = "Could not save data to file: ";
    private final Logger logger = LogsCenter.getLogger(LogicManager.class);

    private final Model model;
    private final Storage storage;
    private final TutorAidParser tutorAidParser;

    /**
     * Constructs a {@code LogicManager} with the given {@code Model} and {@code Storage}.
     */
    public LogicManager(Model model, Storage storage) {
        this.model = model;
        this.storage = storage;
        tutorAidParser = new TutorAidParser();
    }

    @Override
    public CommandResult execute(String commandText) throws CommandException, ParseException {
        logger.info("----------------[USER COMMAND][" + commandText + "]");

        CommandResult commandResult;
        Command command = tutorAidParser.parseCommand(commandText);
        commandResult = command.execute(model);

        try {
            storage.saveStudentBook(model.getStudentBook());
        } catch (IOException ioe) {
            throw new CommandException(FILE_OPS_ERROR_MESSAGE + ioe, ioe);
        }

        return commandResult;
    }

    @Override
    public ReadOnlyStudentBook getStudentBook() {
        return model.getStudentBook();
    }

    @Override
    public ObservableList<Student> getFilteredStudentList() {
        return model.getFilteredStudentList();
    }

    @Override
    public Path getStudentBookFilePath() {
        return model.getStudentBookFilePath();
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
