package seedu.track2gather.logic;

import java.io.IOException;
import java.nio.file.Path;
import java.util.logging.Logger;

import javafx.collections.ObservableList;
import seedu.track2gather.commons.core.GuiSettings;
import seedu.track2gather.commons.core.LogsCenter;
import seedu.track2gather.logic.commands.Command;
import seedu.track2gather.logic.commands.CommandResult;
import seedu.track2gather.logic.commands.exceptions.CommandException;
import seedu.track2gather.logic.parser.Track2GatherParser;
import seedu.track2gather.logic.parser.exceptions.ParseException;
import seedu.track2gather.model.Model;
import seedu.track2gather.model.ReadOnlyTrack2Gather;
import seedu.track2gather.model.person.Person;
import seedu.track2gather.storage.Storage;

/**
 * The main LogicManager of the app.
 */
public class LogicManager implements Logic {
    public static final String FILE_OPS_ERROR_MESSAGE = "Could not save data to file: ";
    private final Logger logger = LogsCenter.getLogger(LogicManager.class);

    private final Model model;
    private final Storage storage;
    private final Track2GatherParser track2GatherParser;

    /**
     * Constructs a {@code LogicManager} with the given {@code Model} and {@code Storage}.
     */
    public LogicManager(Model model, Storage storage) {
        this.model = model;
        this.storage = storage;
        track2GatherParser = new Track2GatherParser();
    }

    @Override
    public CommandResult execute(String commandText) throws CommandException, ParseException {
        logger.info("----------------[USER COMMAND][" + commandText + "]");

        CommandResult commandResult;
        Command command = track2GatherParser.parseCommand(commandText);
        commandResult = command.execute(model);

        try {
            storage.saveTrack2Gather(model.getTrack2Gather());
        } catch (IOException ioe) {
            throw new CommandException(FILE_OPS_ERROR_MESSAGE + ioe, ioe);
        }

        return commandResult;
    }

    @Override
    public ReadOnlyTrack2Gather getTrack2Gather() {
        return model.getTrack2Gather();
    }

    @Override
    public ObservableList<Person> getFilteredPersonList() {
        return model.getFilteredPersonList();
    }

    @Override
    public int getNumPersonsTotal() {
        return model.getTrack2Gather().getPersonList().size();
    }

    @Override
    public Path getTrack2GatherFilePath() {
        return model.getTrack2GatherFilePath();
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
