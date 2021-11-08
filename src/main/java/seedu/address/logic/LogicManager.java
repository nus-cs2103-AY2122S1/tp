package seedu.address.logic;

import java.io.IOException;
import java.nio.file.Path;
import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.scene.chart.PieChart;
import seedu.address.commons.core.GuiSettings;
import seedu.address.commons.core.LogsCenter;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.parser.AddressBookInternalParser;
import seedu.address.logic.parser.AddressBookParser;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.Model;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.person.Person;
import seedu.address.model.task.Task;
import seedu.address.model.task.TaskListManager;
import seedu.address.storage.Storage;

/**
 * The main LogicManager of the app.
 */
public class LogicManager implements Logic {
    public static final String FILE_OPS_ERROR_MESSAGE = "Could not save data to file: ";
    private final Logger logger = LogsCenter.getLogger(LogicManager.class);

    private final Model model;
    private final Storage storage;
    private final AddressBookParser addressBookParser;
    private final AddressBookInternalParser addressBookInternalParser;

    /**
     * Constructs a {@code LogicManager} with the given {@code Model} and {@code Storage}.
     */
    public LogicManager(Model model, Storage storage) {
        this.model = model;
        this.storage = storage;
        addressBookParser = new AddressBookParser();
        addressBookInternalParser = new AddressBookInternalParser();
    }

    @Override
    public CommandResult execute(String commandText, boolean isInternal) throws CommandException, ParseException {
        CommandResult commandResult;
        Command command;
        if (isInternal) {
            logger.info("----------------[INTERNAL COMMAND][" + commandText + "]");
            command = addressBookInternalParser.parseCommand(commandText);
        } else {
            logger.info("----------------[USER COMMAND][" + commandText + "]");
            model.addCommand(commandText);
            command = addressBookParser.parseCommand(commandText);
        }
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

    @Override
    public ObservableList<Task> getDisplayTaskList() {
        return model.getDisplayTaskList();
    }

    /** Gets important statistics information relating to tasks. */
    @Override
    public ObservableList<PieChart.Data> getStatistics() {
        return model.getStatistics();
    }

    @Override
    public TaskListManager getTaskListManager() {
        return model.getTaskListManager();
    }

    @Override
    public ObservableList<Person> getObservablePersonList() {
        return model.getViewAllTaskListPersons();
    }
}
