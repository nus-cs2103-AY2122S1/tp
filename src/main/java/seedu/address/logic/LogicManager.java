package seedu.address.logic;

import java.io.IOException;
import java.nio.file.Path;
import java.util.function.Predicate;
import java.util.logging.Logger;

import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.commons.core.LogsCenter;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.parser.AddressBookParser;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.Model;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.person.Person;
import seedu.address.storage.JsonAdaptedPerson;
import seedu.address.storage.JsonSerializableUserProfile;
import seedu.address.storage.Storage;
import seedu.address.ui.PersonListPanel;

/**
 * The main LogicManager of the app.
 */
public class LogicManager implements Logic {
    public static final String FILE_OPS_ERROR_MESSAGE = "Could not save data to file: ";
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

    /**
     * This method attempts to execute an input command.
     *
     * @param commandText {@code commandText} input command by user.
     * @return CommandResult which holds the outcome of this method.
     * @throws CommandException if there are any command errors during execution.
     * @throws ParseException if there are any parsing errors during execution.
     */
    @Override
    public CommandResult execute(String commandText) throws CommandException, ParseException {
        logger.info("----------------[USER COMMAND][" + commandText + "]");

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
    public Person getUserProfile() {
        return model.getUserProfile();
    }

    @Override
    public boolean isProfilePresent() {
        return model.isProfilePresent();
    }

    @Override
    public void setUserProfile(Person p) throws IOException {
        storage.saveUserProfile(new JsonSerializableUserProfile(new JsonAdaptedPerson(p)));
        model.setUserProfile(p);
    }
    @Override
    public ReadOnlyAddressBook getAddressBook() {
        return model.getAddressBook();
    }

    @Override
    public void saveAllData() throws IOException {
        storage.saveAddressBook(model.getAddressBook());
        storage.saveUserProfile(new JsonSerializableUserProfile(new JsonAdaptedPerson(model.getUserProfile())));
        storage.saveUserPrefs(model.getUserPrefs());
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

    @Override
    public int getSelectedIndex() {
        return model.getSelectedIndex();
    }

    @Override
    public void setSelectedIndex(int index) {
        model.setSelectedIndex(index);
    }

    @Override
    public void setPersonList(PersonListPanel personListPanel) {
        model.setPersonListControl(personListPanel);
    }

    @Override
    public PersonListPanel getPersonList() {
        return model.getPersonListControl();
    }

    @Override
    public void updateFilteredPersonList(Predicate<Person> predicate) {
        model.updateFilteredPersonList(predicate);
    }

    @Override
    public ObservableList<Person> getModifiableList() {
        return model.getAddressBook().getModifiableList();
    }

    @Override
    public void sort() {
        model.getAddressBook().sort();
    }
}
