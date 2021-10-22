package seedu.academydirectory.logic;

import java.io.IOException;
import java.nio.file.Path;
import java.util.logging.Level;
import java.util.logging.Logger;

import javafx.collections.ObservableList;
import seedu.academydirectory.commons.core.GuiSettings;
import seedu.academydirectory.commons.core.LogsCenter;
import seedu.academydirectory.logic.commands.Command;
import seedu.academydirectory.logic.commands.CommandResult;
import seedu.academydirectory.logic.commands.exceptions.CommandException;
import seedu.academydirectory.logic.parser.AcademyDirectoryParser;
import seedu.academydirectory.logic.parser.exceptions.ParseException;
import seedu.academydirectory.model.Model;
import seedu.academydirectory.model.ReadOnlyAcademyDirectory;
import seedu.academydirectory.model.student.Student;
import seedu.academydirectory.storage.Storage;
import seedu.academydirectory.versioncontrol.OptionalVersion;
import seedu.academydirectory.versioncontrol.Version;
import seedu.academydirectory.versioncontrol.logic.commands.VcCommand;
import seedu.academydirectory.versioncontrol.logic.parsers.AcademyDirectoryVcCommandParser;

/**
 * The main LogicManager of the app.
 */
public class LogicManager implements Logic {
    public static final String FILE_OPS_ERROR_MESSAGE = "Could not save data to file: ";
    private final Logger logger = LogsCenter.getLogger(LogicManager.class);

    private final Model model;
    private final Storage storage;
    private final OptionalVersion<Version> version;
    private final AcademyDirectoryParser academyDirectoryParser;
    private final AcademyDirectoryVcCommandParser versionControlParser;

    /**
     * Constructs a {@code LogicManager} with the given {@code Model} and {@code StorageManager}.
     */
    public LogicManager(Model model, Storage storage, OptionalVersion<Version> version) {
        this.model = model;
        this.storage = storage;
        this.version = version;
        academyDirectoryParser = new AcademyDirectoryParser();
        versionControlParser = new AcademyDirectoryVcCommandParser();
    }

    public LogicManager(Model model, Storage storage) {
        this(model, storage, OptionalVersion.ofNullable(null));
    }

    @Override
    public CommandResult execute(String commandText) throws CommandException, ParseException {
        logger.info("----------------[USER COMMAND][" + commandText + "]");

        CommandResult commandResult;
        try {
            VcCommand command = versionControlParser.parseCommand(commandText);
            commandResult = command.execute(version);
        } catch (IOException | java.text.ParseException ioe) {
            throw new CommandException(FILE_OPS_ERROR_MESSAGE + ioe, ioe);
        } catch (ParseException e) {
            Command command = academyDirectoryParser.parseCommand(commandText);
            commandResult = command.execute(model);

            try {
                storage.saveAcademyDirectory(model.getAcademyDirectory());
                logger.log(Level.INFO, "Commit successful? " + (version.commit(commandText) ? "Yes" : "No"));
            } catch (IOException ioe) {
                throw new CommandException(FILE_OPS_ERROR_MESSAGE + ioe, ioe);
            }
        }
        return commandResult;
    }

    @Override
    public ReadOnlyAcademyDirectory getAcademyDirectory() {
        return model.getAcademyDirectory();
    }

    @Override
    public ObservableList<Student> getFilteredStudentList() {
        return model.getFilteredStudentList();
    }

    @Override
    public Path getAcademyDirectoryFilePath() {
        return model.getAcademyDirectoryFilePath();
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
