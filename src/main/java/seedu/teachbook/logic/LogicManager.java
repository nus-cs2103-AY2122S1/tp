package seedu.teachbook.logic;

import java.io.IOException;
import java.nio.file.Path;
import java.util.logging.Logger;

import javafx.collections.ObservableList;
import seedu.teachbook.commons.core.GuiSettings;
import seedu.teachbook.commons.core.LogsCenter;
import seedu.teachbook.commons.core.index.GeneralIndex;
import seedu.teachbook.logic.commands.Command;
import seedu.teachbook.logic.commands.CommandResult;
import seedu.teachbook.logic.commands.exceptions.CommandException;
import seedu.teachbook.logic.parser.TeachBookParser;
import seedu.teachbook.logic.parser.exceptions.ParseException;
import seedu.teachbook.model.Model;
import seedu.teachbook.model.ReadOnlyTeachBook;
import seedu.teachbook.model.classobject.Class;
import seedu.teachbook.model.student.Student;
import seedu.teachbook.storage.Storage;

/**
 * The main LogicManager of the app.
 */
public class LogicManager implements Logic {
    public static final String FILE_OPS_ERROR_MESSAGE = "Could not save data to file: ";
    private final Logger logger = LogsCenter.getLogger(LogicManager.class);

    private final Model model;
    private final Storage storage;
    private final TeachBookParser teachBookParser;

    /**
     * Constructs a {@code LogicManager} with the given {@code Model} and {@code Storage}.
     */
    public LogicManager(Model model, Storage storage) {
        this.model = model;
        this.storage = storage;
        teachBookParser = new TeachBookParser();
    }

    @Override
    public CommandResult execute(String commandText) throws CommandException, ParseException {
        logger.info("----------------[USER COMMAND][" + commandText + "]");


        CommandResult commandResult;
        Command command = teachBookParser.parseCommand(commandText);
        commandResult = command.execute(model);
        try {
            storage.saveTeachBook(model.getTeachBook());
        } catch (IOException ioe) {
            throw new CommandException(FILE_OPS_ERROR_MESSAGE + ioe, ioe);
        }


        return commandResult;
    }

    @Override
    public ReadOnlyTeachBook getTeachBook() {
        return model.getTeachBook();
    }

    @Override
    public ObservableList<Student> getFilteredStudentList() {
        return model.getFilteredStudentList();
    }

    @Override
    public ObservableList<Class> getUniqueClassList() {
        return model.getUniqueClassList();
    }

    @Override
    public Path getTeachBookFilePath() {
        return model.getTeachBookFilePath();
    }

    @Override
    public GuiSettings getGuiSettings() {
        return model.getGuiSettings();
    }

    @Override
    public void setGuiSettings(GuiSettings guiSettings) {
        model.setGuiSettings(guiSettings);
    }

    @Override
    public GeneralIndex getCurrentlySelectedClassIndex() {
        return model.getCurrentlySelectedClassIndex();
    }
}
