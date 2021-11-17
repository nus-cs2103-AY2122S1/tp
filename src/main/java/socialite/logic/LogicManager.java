package socialite.logic;

import java.io.IOException;
import java.nio.file.Path;
import java.util.logging.Logger;

import javafx.collections.ObservableList;
import socialite.commons.core.GuiSettings;
import socialite.commons.core.LogsCenter;
import socialite.logic.commands.Command;
import socialite.logic.commands.CommandResult;
import socialite.logic.commands.exceptions.CommandException;
import socialite.logic.parser.SocialiteParser;
import socialite.logic.parser.exceptions.ParseException;
import socialite.model.Model;
import socialite.model.ReadOnlyCommandHistory;
import socialite.model.ReadOnlyContactList;
import socialite.model.person.Person;
import socialite.storage.Storage;

/**
 * The main LogicManager of the app.
 */
public class LogicManager implements Logic {
    public static final String FILE_OPS_ERROR_MESSAGE = "Could not save data to file: ";
    private final Logger logger = LogsCenter.getLogger(LogicManager.class);
    private final Model model;
    private final Storage storage;
    private final SocialiteParser socialiteParser;

    /**
     * Constructs a {@code LogicManager} with the given {@code Model} and {@code Storage}.
     */
    public LogicManager(Model model, Storage storage) {
        this.model = model;
        this.storage = storage;
        socialiteParser = new SocialiteParser();
    }

    @Override
    public CommandResult execute(String commandText) throws CommandException, ParseException {
        logger.info("----------------[USER COMMAND][" + commandText + "]");

        model.addCommandToHistory(commandText);

        CommandResult commandResult;
        Command command = socialiteParser.parseCommand(commandText);
        commandResult = command.execute(model);

        try {
            storage.saveCommandHistory(model.getCommandHistory());
            storage.saveContactList(model.getContactList());
            storage.syncProfilePictures(model.getProfilePictureEditDescriptor());
            model.clearProfilePictureModel();
        } catch (IOException ioe) {
            throw new CommandException(FILE_OPS_ERROR_MESSAGE + ioe, ioe);
        }

        return commandResult;
    }

    @Override
    public ReadOnlyContactList getContactList() {
        return model.getContactList();
    }

    @Override
    public ObservableList<Person> getFullPersonList() {
        model.updateFilteredPersonList(Model.PREDICATE_SHOW_ALL_PERSONS);
        return getFilteredPersonList();
    }

    @Override
    public ObservableList<Person> getFilteredPersonList() {
        return model.getFilteredPersonList();
    }

    @Override
    public Path getContactListFilePath() {
        return model.getContactListFilePath();
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
    public ReadOnlyCommandHistory getCommandHistory() {
        return model.getCommandHistory();
    }
}
