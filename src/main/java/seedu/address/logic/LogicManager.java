package seedu.address.logic;

import java.io.IOException;
import java.nio.file.Path;
import java.util.logging.Logger;

import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.commons.core.LogsCenter;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.parser.AddressBookParser;
import seedu.address.logic.parser.MeetingParser;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.Model;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.meeting.Meeting;
import seedu.address.model.person.student.Student;
import seedu.address.model.person.teacher.Teacher;
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
    private final MeetingParser meetingParser;

    /**
     * Constructs a {@code LogicManager} with the given {@code Model} and {@code Storage}.
     */
    public LogicManager(Model model, Storage storage) {
        this.model = model;
        this.storage = storage;
        addressBookParser = new AddressBookParser();
        meetingParser = new MeetingParser();
    }

    @Override
    public CommandResult execute(String commandText, Window window) throws CommandException, ParseException {
        /* {@code window} tells the logic manager which window the user is currently typing in the commands, and so {
         * @code LogicManager} will select the correct {@code Parser} to parse the command. This is to ensure commands
         * that are meant for the main window do not get parsed as commands in the meeting window.
         */

        logger.info("----------------[USER COMMAND][" + commandText + "]");
        logger.info("----------------[WINDOW][" + window + "]");

        CommandResult commandResult;
        Command command;
        if (window.equals(Window.MAIN)) {
            command = addressBookParser.parseCommand(commandText);
        } else {
            command = meetingParser.parseCommand(commandText);
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
    public ObservableList<Student> getFilteredStudentList() {
        return model.getFilteredStudentList();
    }

    @Override
    public ObservableList<Teacher> getFilteredTeacherList() {
        return model.getFilteredTeacherList();
    }

    @Override
    public ObservableList<Meeting> getMeetingList() {
        return model.getMeetingList();
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
}
