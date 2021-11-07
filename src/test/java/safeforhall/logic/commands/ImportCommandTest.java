package safeforhall.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static safeforhall.logic.commands.CommandTestUtil.assertCommandSuccess;
import static safeforhall.testutil.Assert.assertThrows;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;

import org.junit.jupiter.api.Test;

import safeforhall.logic.commands.exceptions.CommandException;
import safeforhall.model.AddressBook;
import safeforhall.model.Model;
import safeforhall.model.ModelManager;
import safeforhall.model.UserPrefs;
import safeforhall.model.event.Capacity;
import safeforhall.model.event.Event;
import safeforhall.model.event.EventDate;
import safeforhall.model.event.EventName;
import safeforhall.model.event.EventTime;
import safeforhall.model.event.ResidentList;
import safeforhall.model.event.Venue;
import safeforhall.model.person.LastDate;
import safeforhall.model.person.VaccStatus;
import safeforhall.testutil.TypicalPersons;

/**
 * Contains integration tests (interaction with the Model) for {@code ImportCommand}.
 */
public class ImportCommandTest {

    private static final Path TEST_DATA_FOLDER = Paths.get("src", "test", "data", "ImportTest");

    private Model model = new ModelManager(TypicalPersons.getTypicalAddressBook(), new UserPrefs());
    private Model expectedModel = new ModelManager(TypicalPersons.getTypicalImportedAddressBook(), new UserPrefs());

    @Test
    public void equals() {
        ImportCommand imp = new ImportCommand("safeforhall");
        ImportCommand imp2 = new ImportCommand("residents");

        // same object -> returns true
        assertTrue(imp.equals(imp));

        // same values -> returns true
        ImportCommand impCopy = new ImportCommand("safeforhall");
        assertTrue(imp.equals(impCopy));

        // different types -> returns false
        assertFalse(imp.equals(1));

        // null -> returns false
        assertFalse(imp.equals(null));

        // different person -> returns false
        assertFalse(imp.equals(imp2));
    }

    private Path getTestDataFilePath(String csvFileInTestDataFolder) {
        return csvFileInTestDataFolder != null
                ? TEST_DATA_FOLDER.resolve(csvFileInTestDataFolder)
                : null;
    }

    @Test
    public void execute_readCsv_success() {
        String expectedMessage = ImportCommand.MESSAGE_SUCCESS;
        ImportCommand command = new ImportCommand(getTestDataFilePath("safeforhall.csv"));
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(TypicalPersons.AARON, TypicalPersons.BRAD, TypicalPersons.CODY,
                TypicalPersons.DARREN, TypicalPersons.ELLIE),
                model.getFilteredPersonList());
    }

    @Test
    public void execute_readCsvEmptyLines_success() {
        String expectedMessage = ImportCommand.MESSAGE_SUCCESS;
        ImportCommand command = new ImportCommand(getTestDataFilePath("emptylines.csv"));
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(TypicalPersons.AARON, TypicalPersons.BRAD, TypicalPersons.CODY,
                TypicalPersons.DARREN, TypicalPersons.ELLIE),
                model.getFilteredPersonList());
    }

    @Test
    public void execute_readCsvMissingFet_success() {
        Model expectedModel = new ModelManager(TypicalPersons.getTypicalAddressBookWithSpecifiedPersons(
                Arrays.asList(TypicalPersons.AARON, TypicalPersons.BRAD, TypicalPersons.CODY_WO_FET,
                TypicalPersons.DARREN, TypicalPersons.ELLIE)),
                new UserPrefs());
        String expectedMessage = ImportCommand.MESSAGE_SUCCESS;
        ImportCommand command = new ImportCommand(getTestDataFilePath("missingfet.csv"));
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(TypicalPersons.AARON, TypicalPersons.BRAD, TypicalPersons.CODY_WO_FET,
                TypicalPersons.DARREN, TypicalPersons.ELLIE),
                model.getFilteredPersonList());
    }

    @Test
    public void execute_readCsvMissingCollection_success() {
        Model expectedModel = new ModelManager(TypicalPersons.getTypicalAddressBookWithSpecifiedPersons(
                Arrays.asList(TypicalPersons.AARON, TypicalPersons.BRAD, TypicalPersons.CODY,
                        TypicalPersons.DARREN, TypicalPersons.ELLIE_WO_COLLECTION)),
                new UserPrefs());
        String expectedMessage = ImportCommand.MESSAGE_SUCCESS;
        ImportCommand command = new ImportCommand(getTestDataFilePath("missingcollection.csv"));
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(TypicalPersons.AARON, TypicalPersons.BRAD, TypicalPersons.CODY,
                TypicalPersons.DARREN, TypicalPersons.ELLIE_WO_COLLECTION),
                model.getFilteredPersonList());
    }

    @Test
    public void execute_readCsvWithEvents_success() {
        AddressBook addressBook = TypicalPersons.getTypicalAddressBook();
        Event event = new Event(new EventName("Gymming"), new EventDate("09-09-2021"),
                new EventTime("0830"), new Venue("Gym"), new Capacity("10"),
                new ResidentList(TypicalPersons.AMY.getName().toString(), TypicalPersons.AMY.toString()));
        ArrayList<Event> events = new ArrayList<>();
        events.add(event);
        addressBook.setEvents(events);
        model = new ModelManager(addressBook, new UserPrefs());

        AddressBook expectedAddressBook = TypicalPersons.getTypicalImportedAddressBook();
        Event expectedEvent = new Event(new EventName("Gymming"), new EventDate("09-09-2021"),
                new EventTime("0830"), new Venue("Gym"), new Capacity("10"),
                new ResidentList(ResidentList.DEFAULT_LIST));
        ArrayList<Event> expectedEvents = new ArrayList<>();
        expectedEvents.add(expectedEvent);
        expectedAddressBook.setEvents(expectedEvents);
        expectedModel = new ModelManager(expectedAddressBook, new UserPrefs());

        String expectedMessage = ImportCommand.MESSAGE_SUCCESS;
        ImportCommand command = new ImportCommand(getTestDataFilePath("safeforhall.csv"));
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(TypicalPersons.AARON, TypicalPersons.BRAD, TypicalPersons.CODY,
                TypicalPersons.DARREN, TypicalPersons.ELLIE),
                model.getFilteredPersonList());
    }

    @Test
    public void execute_readCsvUnspecified_fail() {
        ImportCommand command = new ImportCommand(getTestDataFilePath("nonexistent.csv"));
        assertThrows(CommandException.class, ImportCommand.MESSAGE_FILE_NOT_FOUND, () -> command.execute(model));
    }

    @Test
    public void execute_readCsvIncorrectDate_fail() {
        ImportCommand command = new ImportCommand(getTestDataFilePath("incorrectdate.csv"));
        assertThrows(CommandException.class, String.format(ImportCommand.MESSAGE_ERROR_READING, 3)
                + LastDate.MESSAGE_CONSTRAINTS, () -> command.execute(model));
    }

    @Test
    public void execute_readCsvIncorrectVacc_fail() {
        ImportCommand command = new ImportCommand(getTestDataFilePath("incorrectvacc.csv"));
        assertThrows(CommandException.class, String.format(ImportCommand.MESSAGE_ERROR_READING, 4)
                + VaccStatus.MESSAGE_CONSTRAINTS, () -> command.execute(model));
    }

    @Test
    public void execute_readCsvMissingFields_fail() {
        ImportCommand command = new ImportCommand(getTestDataFilePath("missingfields.csv"));
        assertThrows(CommandException.class, String.format(ImportCommand.MESSAGE_ERROR_READING, 2)
                + ImportCommand.MESSAGE_INCORRECT_FIELDS, () -> command.execute(model));
    }
}
