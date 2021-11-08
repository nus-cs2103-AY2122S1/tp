package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.Person;
import seedu.address.storage.JsonAddressBookStorage;

public class ExportCommandTest {

    private static final IOException DUMMY_IO_EXCEPTION = new IOException("dummy exception");

    private Path testOutput = Paths.get("src/test/data/ExportTest/testOutput.json");
    private Path expectedOutput = Paths.get("src/test/data/ExportTest/expectedOutput.json");
    private Model model;
    private Model expectedModel;


    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
        expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
    }

    @Test
    public void execute_exportSuccessful() throws IOException {

        String expectedMessage = String.format(ExportCommand.MESSAGE_SUCCESS + " to " + testOutput);
        assertCommandSuccess(new ExportCommand(testOutput), model, expectedMessage, expectedModel);

        List<String> file1 = Files.readAllLines(testOutput);
        List<String> file2 = Files.readAllLines(expectedOutput);

        assertEquals(file1.size(), file2.size());

        for (int i = 0; i < file2.size() - 1; i++) {
            System.out.println("Comparing line: " + i);
            assertEquals(file1.get(i), file2.get(i));
        }
    }


    @Test
    public void equals() {
        final ExportCommand standardCommand = new ExportCommand(testOutput);

        // same values -> returns true
        assertTrue(standardCommand.equals(new ExportCommand(testOutput)));

        // same object -> returns true
        assertTrue(standardCommand.equals(standardCommand));

        // null -> returns false
        assertFalse(standardCommand.equals(null));

        // different index -> returns false
        assertFalse(standardCommand.equals(new ExportCommand(expectedOutput)));
    }

    @Test
    public void execute_throwsIoException_throwsCommandException() {
        JsonAddressBookStorage addressBookStorage =
            new JsonAddressBookIoExceptionThrowingStub(expectedOutput);

        String expectedMessage = ExportCommand.FILE_OPS_ERROR_MESSAGE;
        assertCommandFailure(new ExportCommand(testOutput), model, expectedMessage, addressBookStorage);
    }

    /**
     * A stub class to throw an {@code IOException} when the save method is called.
     */
    private static class JsonAddressBookIoExceptionThrowingStub extends JsonAddressBookStorage {
        private JsonAddressBookIoExceptionThrowingStub(Path filePath) {
            super(filePath);
        }

        @Override
        public void saveAddressBook(ReadOnlyAddressBook addressBook) throws IOException {
            throw DUMMY_IO_EXCEPTION;
        }
    }

    private static void assertCommandFailure(ExportCommand command, Model actualModel,
            String expectedMessage, JsonAddressBookStorage storage) {
        AddressBook expectedAddressBook = new AddressBook(actualModel.getAddressBook());
        List<Person> expectedFilteredList = new ArrayList<>(actualModel.getFilteredPersonList());

        assertThrows(CommandException.class, expectedMessage, () -> command.execute(actualModel, storage));
        assertEquals(expectedAddressBook, actualModel.getAddressBook());
        assertEquals(expectedFilteredList, actualModel.getFilteredPersonList());
    }
}
