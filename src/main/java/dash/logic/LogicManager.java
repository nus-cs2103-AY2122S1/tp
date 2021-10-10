package dash.logic;

import java.io.IOException;
import java.nio.file.Path;
import java.util.logging.Logger;

import dash.commons.core.GuiSettings;
import dash.commons.core.LogsCenter;
import dash.logic.commands.Command;
import dash.logic.commands.CommandResult;
import dash.logic.commands.exceptions.CommandException;
import dash.logic.parser.ContactsTabParser;
import dash.logic.parser.HelpTabParser;
import dash.logic.parser.TaskTabParser;
import dash.logic.parser.exceptions.ParseException;
import dash.model.Model;
import dash.model.ReadOnlyAddressBook;
import dash.model.person.Person;
import dash.model.task.Task;
import dash.storage.Storage;
import javafx.collections.ObservableList;

/**
 * The main LogicManager of the app.
 */
public class LogicManager implements Logic {
    public static final String FILE_OPS_ERROR_MESSAGE = "Could not save data to file: ";
    private final Logger logger = LogsCenter.getLogger(LogicManager.class);

    private final Model model;
    private final Storage storage;
    private final ContactsTabParser contactsTabParser;
    private final TaskTabParser taskTabParser;
    private final HelpTabParser helpTabParser;

    private int tabNumber = 0;

    /**
     * Constructs a {@code LogicManager} with the given {@code Model} and {@code Storage}.
     */
    public LogicManager(Model model, Storage storage) {
        this.model = model;
        this.storage = storage;
        contactsTabParser = new ContactsTabParser();
        taskTabParser = new TaskTabParser();
        helpTabParser = new HelpTabParser();
    }

    @Override
    public CommandResult execute(String commandText) throws CommandException, ParseException {
        logger.info("----------------[USER COMMAND][" + commandText + "]");

        CommandResult commandResult;
        Command command;
        switch (tabNumber) {
        case (0):
            command = contactsTabParser.parseCommand(commandText);
            break;
        case (1):
            command = taskTabParser.parseCommand(commandText);
            break;
        case (2):
            command = helpTabParser.parseCommand(commandText);
            break;
        default:
            command = contactsTabParser.parseCommand(commandText);
        }

        commandResult = command.execute(model);

        try {
            storage.saveAddressBook(model.getAddressBook());
            storage.saveTaskList(model.getTaskList());
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
    public ObservableList<Task> getFilteredTaskList() {
        return model.getFilteredTaskList();
    }

    @Override
    public Path getAddressBookFilePath() {
        return model.getAddressBookFilePath();
    }

    @Override
    public Path getTaskListFilePath() {
        return model.getTaskListFilePath();
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
    public void setTabNumber(int i) {
        tabNumber = i;
    }
}
