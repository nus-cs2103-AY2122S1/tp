package seedu.address.logic;

import java.io.IOException;
import java.nio.file.Path;
import java.util.logging.Logger;

import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.commons.core.LogsCenter;
import seedu.address.logic.candidate.exceptions.CommandException;
import seedu.address.logic.parser.HrManagerParser;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.Model;
import seedu.address.model.ReadOnlyHrManager;
import seedu.address.model.interview.Interview;
import seedu.address.model.person.Person;
import seedu.address.model.position.Position;
import seedu.address.storage.Storage;

/**
 * The main LogicManager of the app.
 */
public class LogicManager implements Logic {
    public static final String FILE_OPS_ERROR_MESSAGE = "Could not save data to file: ";
    private final Logger logger = LogsCenter.getLogger(LogicManager.class);

    private final Model model;
    private final Storage storage;
    private final HrManagerParser hrManagerParser;

    /**
     * Constructs a {@code LogicManager} with the given {@code Model} and {@code Storage}.
     */
    public LogicManager(Model model, Storage storage) {
        this.model = model;
        this.storage = storage;
        hrManagerParser = new HrManagerParser();
    }

    @Override
    public CommandResult execute(String commandText) throws CommandException, ParseException {
        logger.info("----------------[USER COMMAND][" + commandText + "]");

        CommandResult commandResult;
        Command command = hrManagerParser.parseCommand(commandText);
        commandResult = command.execute(model);

        try {
            storage.saveHrManager(model.getHrManager());
        } catch (IOException ioe) {
            throw new CommandException(FILE_OPS_ERROR_MESSAGE + ioe, ioe);
        }

        return commandResult;
    }

    @Override
    public ReadOnlyHrManager getHrManager() {
        return model.getHrManager();
    }

    @Override
    public ObservableList<Person> getFilteredPersonList() {
        return model.getFilteredPersonList();
    }

    @Override
    public ObservableList<Position> getFilteredPositionList() {
        return model.getFilteredPositionList();
    }

    @Override
    public ObservableList<Interview> getFilteredInterviewList() {
        return model.getFilteredInterviewList();
    }

    @Override
    public Path getHrManagerCandidatesFilePath() {
        return model.getHrManagerCandidatesFilePath();
    }

    @Override
    public Path getHrManagerPositionsFilePath() {
        return model.getHrManagerPositionsFilePath();
    }

    @Override
    public Path getHrManagerInterviewsFilePath() {
        return model.getHrManagerInterviewsFilePath();
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
