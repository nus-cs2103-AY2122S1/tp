package seedu.tuitione.logic;

import java.io.IOException;
import java.nio.file.Path;
import java.util.logging.Logger;

import javafx.collections.ObservableList;
import seedu.tuitione.commons.core.GuiSettings;
import seedu.tuitione.commons.core.LogsCenter;
import seedu.tuitione.logic.commands.Command;
import seedu.tuitione.logic.commands.CommandResult;
import seedu.tuitione.logic.commands.exceptions.CommandException;
import seedu.tuitione.logic.parser.TuitioneParser;
import seedu.tuitione.logic.parser.exceptions.ParseException;
import seedu.tuitione.model.Model;
import seedu.tuitione.model.ReadOnlyTuitione;
import seedu.tuitione.model.lesson.Lesson;
import seedu.tuitione.model.student.Student;
import seedu.tuitione.storage.Storage;

/**
 * The main LogicManager of the app.
 */
public class LogicManager implements Logic {
    public static final String FILE_OPS_ERROR_MESSAGE = "Could not save data to file: ";
    private final Logger logger = LogsCenter.getLogger(LogicManager.class);

    private final Model model;
    private final Storage storage;
    private final TuitioneParser tuitioneParser;

    /**
     * Constructs a {@code LogicManager} with the given {@code Model} and {@code Storage}.
     */
    public LogicManager(Model model, Storage storage) {
        this.model = model;
        this.storage = storage;
        tuitioneParser = new TuitioneParser();
    }

    @Override
    public CommandResult execute(String commandText) throws CommandException, ParseException {
        logger.info("----------------[USER COMMAND][" + commandText + "]");

        CommandResult commandResult;
        Command command = tuitioneParser.parseCommand(commandText);
        commandResult = command.execute(model);

        try {
            storage.saveTuitione(model.getTuitione());
        } catch (IOException ioe) {
            throw new CommandException(FILE_OPS_ERROR_MESSAGE + ioe, ioe);
        }

        return commandResult;
    }

    @Override
    public ReadOnlyTuitione getTuitione() {
        return model.getTuitione();
    }

    @Override
    public ObservableList<Student> getFilteredStudentList() {
        return model.getFilteredStudentList();
    }

    @Override
    public ObservableList<Lesson> getFilteredLessonList() {
        return model.getFilteredLessonList();
    }

    @Override
    public Path getTuitioneFilePath() {
        return model.getTuitioneFilePath();
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
