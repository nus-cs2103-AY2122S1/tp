package nustracker.logic;

import java.io.IOException;
import java.nio.file.Path;
import java.util.logging.Level;
import java.util.logging.Logger;

import javafx.collections.ObservableList;
import nustracker.commons.core.GuiSettings;
import nustracker.commons.core.LogsCenter;
import nustracker.logic.commands.Command;
import nustracker.logic.commands.CommandResult;
import nustracker.logic.commands.exceptions.CommandException;
import nustracker.logic.parser.AddressBookParser;
import nustracker.logic.parser.exceptions.ParseException;
import nustracker.model.Model;
import nustracker.model.ReadOnlyAddressBook;
import nustracker.model.event.Event;
import nustracker.model.student.Student;
import nustracker.storage.Storage;
import nustracker.ui.MainWindow.CurrentlyShownList;

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

    @Override
    public CommandResult execute(String commandText, CurrentlyShownList currentlyShownList)
            throws CommandException, ParseException {
        logger.info("----------------[USER COMMAND][" + commandText + "]");

        CommandResult commandResult;
        Command command = addressBookParser.parseCommand(commandText);
        commandResult = command.execute(model, currentlyShownList);

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
    public ObservableList<Student> getFilteredStudentList() {
        return model.getFilteredStudentList();
    }

    @Override
    public ObservableList<Event> getFilteredEventList() {
        return model.getFilteredEventList();
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
    public void setGuiSettings(GuiSettings guiSettings) throws IllegalArgumentException {
        if (guiSettings == null) {
            logger.log(Level.INFO, "guiSettings cannot be null!");
            throw new IllegalArgumentException("guiSettings cannot be null!");
        }
        model.setGuiSettings(guiSettings);
    }
}
