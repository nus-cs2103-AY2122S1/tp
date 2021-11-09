package seedu.modulink.logic;

import java.io.IOException;
import java.nio.file.Path;
import java.util.logging.Logger;

import javafx.collections.ObservableList;
import seedu.modulink.commons.core.GuiSettings;
import seedu.modulink.commons.core.LogsCenter;
import seedu.modulink.logic.commands.Command;
import seedu.modulink.logic.commands.CommandResult;
import seedu.modulink.logic.commands.CreateCommand;
import seedu.modulink.logic.commands.EditCommand;
import seedu.modulink.logic.commands.exceptions.CommandException;
import seedu.modulink.logic.parser.AddressBookParser;
import seedu.modulink.logic.parser.exceptions.ParseException;
import seedu.modulink.model.Model;
import seedu.modulink.model.ReadOnlyAddressBook;
import seedu.modulink.model.person.Person;
import seedu.modulink.storage.Storage;


/**
 * The main LogicManager of the app.
 */
public class LogicManager implements Logic {
    public static final String FILE_OPS_ERROR_MESSAGE = "Could not save data to file: ";
    public static final String PROFILE_NOT_CREATED_ERROR_MESSAGE =
            "Please create your profile using the create command first!";
    public static final String PROFILE_CREATED_ERROR_MESSAGE =
            "Profile already created! Use the edit command to edit it!";
    private final Logger logger = LogsCenter.getLogger(LogicManager.class);

    private final Model model;
    private final Storage storage;
    private final AddressBookParser addressBookParser;

    /**
     * Constructs a {@code LogicManager} with the given {@code Model} and {@code Storage}.
     */
    public LogicManager(Model model, Storage storage) {
        this.model = model;
        this.storage = storage;
        addressBookParser = new AddressBookParser();
    }

    @Override
    public CommandResult execute(String commandText) throws CommandException, ParseException {
        logger.info("----------------[USER COMMAND][" + commandText + "]");

        if (!hasCreatedProfile() && !commandText.toLowerCase().startsWith("create") && !commandText.equals("list")) {
            throw new CommandException(PROFILE_NOT_CREATED_ERROR_MESSAGE + "\n" + CreateCommand.MESSAGE_USAGE);
        } else if (hasCreatedProfile() && commandText.toLowerCase().startsWith("create")) {
            throw new CommandException(PROFILE_CREATED_ERROR_MESSAGE + "\n" + EditCommand.MESSAGE_USAGE);
        }

        CommandResult commandResult;
        Command command = addressBookParser.parseCommand(commandText);

        commandResult = command.execute(model);

        try {
            storage.saveAddressBook(model.getAddressBook());
        } catch (IOException ioe) {
            throw new CommandException(FILE_OPS_ERROR_MESSAGE + ioe, ioe);
        }

        return commandResult;
    }

    @Override
    public ReadOnlyAddressBook getAddressBook() {
        return model.getAddressBook();
    }

    @Override
    public ObservableList<Person> getFilteredPersonList() {
        return model.getFilteredPersonList();
    }

    @Override
    public Path getAddressBookFilePath() {
        return model.getAddressBookFilePath();
    }

    @Override
    public GuiSettings getGuiSettings() {
        return model.getGuiSettings();
    }

    @Override
    public void setGuiSettings(GuiSettings guiSettings) {
        model.setGuiSettings(guiSettings);
    }

    public boolean hasCreatedProfile() {
        return !model.hasPerson(Person.getPlaceholder());
    }
}
