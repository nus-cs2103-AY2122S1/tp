package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;

import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.Person;
import seedu.address.testutil.PersonBuilder;

public class ImportCommandTest {

    private Path filePathIncorrectFormat = Paths.get("src/test/data/ImportTest/incorrectFormat.json");
    private Path filePathNoDuplicates = Paths.get("src/test/data/ImportTest/noDuplicates.json");
    private Path filePathWithDuplicates = Paths.get("src/test/data/ImportTest/withDuplicates.json");
    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    private Person alex = new PersonBuilder()
            .withName("Alex Marcus")
            .withPhone("91234567")
            .withEmail("e0000007@u.nus.edu")
            .withAddress("123, Jurong West Ave 6, #08-111")
            .withTags("friends")
            .withGitHubId("alex-marcus")
            .withNusNetworkId("e0000007")
            .withType("student")
            .withStudentId("A0000010X")
            .withTutorialId("00")
            .build();

    private Person carol = new PersonBuilder()
            .withName("Carol Heinz")
            .withPhone("97897897")
            .withEmail("e0000009@u.nus.edu")
            .withAddress("wall street")
            .withGitHubId("carol-heinz")
            .withNusNetworkId("e0000009")
            .withType("student")
            .withStudentId("A0001000X")
            .withTutorialId("02")
            .build();

    @Test
    public void constructor_nullFile_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new ImportCommand(null));
    }

    @Test
    public void execute_jsonFileFormatIncorrect_throwsDataConversionException() {
        assertThrows(DataConversionException.class, () -> new ImportCommand(filePathIncorrectFormat).execute(model));
    }

    @Test
    public void execute_noDuplicates_importSuccessful() {
        ImportCommand importCommand = new ImportCommand(filePathNoDuplicates);
        String expectedMessage = String.format(ImportCommand.MESSAGE_SUCCESS, filePathNoDuplicates.toString());
        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.addPerson(alex);
        expectedModel.addPerson(carol);
        assertCommandSuccess(importCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_withDuplicates_importSuccessful() {
        ImportCommand importCommand = new ImportCommand(filePathWithDuplicates);
        String expectedMessage = String.format(ImportCommand.MESSAGE_SUCCESS, filePathWithDuplicates.toString());
        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.addPerson(alex);
        assertCommandSuccess(importCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void equals() {
        final ImportCommand standardCommand = new ImportCommand(filePathNoDuplicates);

        // same values -> returns true
        assertTrue(standardCommand.equals(new ImportCommand(filePathNoDuplicates)));

        // same object -> returns true
        assertTrue(standardCommand.equals(standardCommand));

        // null -> returns false
        assertFalse(standardCommand.equals(null));

        // different index -> returns false
        assertFalse(standardCommand.equals(new ImportCommand(filePathWithDuplicates)));
    }
}
