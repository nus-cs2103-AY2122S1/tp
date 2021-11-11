package seedu.intern.logic;

import java.io.IOException;
import java.nio.file.AccessDeniedException;
import java.nio.file.Path;
import java.util.logging.Logger;

import javafx.collections.ObservableList;
import seedu.intern.commons.core.GuiSettings;
import seedu.intern.commons.core.LogsCenter;
import seedu.intern.logic.commands.Command;
import seedu.intern.logic.commands.CommandResult;
import seedu.intern.logic.commands.exceptions.CommandException;
import seedu.intern.logic.parser.InternWatcherParser;
import seedu.intern.logic.parser.exceptions.ParseException;
import seedu.intern.model.Model;
import seedu.intern.model.ReadOnlyInternWatcher;
import seedu.intern.model.applicant.Applicant;
import seedu.intern.storage.Storage;

/**
 * The main LogicManager of the app.
 */
public class LogicManager implements Logic {
    public static final String FILE_OPS_ERROR_MESSAGE = "Could not save data to file: ";
    public static final String FILE_ACCESS_DENIED_ERROR_MESSAGE = "File access denied, ensure save "
            + "file is not set to read only.";
    private final Logger logger = LogsCenter.getLogger(LogicManager.class);

    private final Model model;
    private final Storage storage;
    private final InternWatcherParser internWatcherParser;

    /**
     * Constructs a {@code LogicManager} with the given {@code Model} and {@code Storage}.
     */
    public LogicManager(Model model, Storage storage) {
        this.model = model;
        this.storage = storage;
        internWatcherParser = new InternWatcherParser();
    }

    @Override
    public CommandResult execute(String commandText) throws CommandException, ParseException {
        logger.info("----------------[USER COMMAND][" + commandText + "]");

        CommandResult commandResult;
        Command command = internWatcherParser.parseCommand(commandText);
        commandResult = command.execute(model);

        try {
            storage.saveInternWatcher(model.getInternWatcher());
        } catch (AccessDeniedException ioe) {
            throw new CommandException(FILE_OPS_ERROR_MESSAGE + ioe.getFile() + ". "
                    + FILE_ACCESS_DENIED_ERROR_MESSAGE, ioe);
        } catch (IOException ioe) {
            throw new CommandException(FILE_OPS_ERROR_MESSAGE + ioe, ioe);
        }

        return commandResult;
    }

    @Override
    public ReadOnlyInternWatcher getInternWatcher() {
        return model.getInternWatcher();
    }

    @Override
    public ObservableList<Applicant> getFilteredApplicantList() {
        return model.getFilteredApplicantList();
    }

    @Override
    public Applicant getApplicant() {
        return model.getApplicant();
    }

    @Override
    public boolean getIsToggle() {
        return model.getIsToggle();
    }

    @Override
    public Path getInternWatcherFilePath() {
        return model.getInternWatcherFilePath();
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
