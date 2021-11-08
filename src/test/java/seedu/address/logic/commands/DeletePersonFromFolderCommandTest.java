package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.parser.ParserUtil;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.folder.Folder;
import seedu.address.model.folder.FolderName;
import seedu.address.model.person.Address;
import seedu.address.model.person.Email;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.util.SampleDataUtil;
import seedu.address.testutil.TypicalPersons;

public class DeletePersonFromFolderCommandTest {

    private static String stringIndex = "1";
    private static String stringIndexLarge = "100";
    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void constructor_nullFolder_throwsNullPointerException() throws ParseException {
        Index validIndex = ParserUtil.parseIndex(stringIndex, new ParseException(DeleteCommand.MESSAGE_USAGE));
        assertThrows(NullPointerException.class, () -> new DeletePersonFromFolderCommand(validIndex, null));
    }

    @Test
    public void constructor_nullIndex_throwsNullPointerException() {
        Folder validFolder = new Folder(new FolderName("folder 1"));
        assertThrows(NullPointerException.class, () -> new DeletePersonFromFolderCommand(null, validFolder));
    }

    @Test
    public void execute_removeContactFromNonExistingFolder_failure() throws ParseException {
        Folder validFolder = new Folder(new FolderName("folder 1"));
        Index validIndex = ParserUtil.parseIndex(stringIndex, new ParseException(DeleteCommand.MESSAGE_USAGE));
        DeletePersonFromFolderCommand deletePersonFromFolderCommand = new DeletePersonFromFolderCommand(
                validIndex, validFolder);
        assertThrows(CommandException.class, () -> deletePersonFromFolderCommand.execute(model));
    }


    @Test
    public void execute_removeNonExistingContactExistingFolder_failure() throws ParseException {
        Folder validFolder = new Folder(new FolderName("folder 1"));
        model.addFolder(validFolder);
        Person validPerson = new Person(
                new Name("Charlotte Oliveiro"),
                new Phone("93210283"),
                new Email("charlotte@example.com"),
                new Address("Blk 11 Ang Mo Kio Street 74, #11-04"),
                SampleDataUtil.getTagSet("neighbours"));
        model.addPerson(validPerson);
        Index validIndex = ParserUtil.parseIndex(stringIndex, new ParseException(DeleteCommand.MESSAGE_USAGE));
        DeletePersonFromFolderCommand deletePersonFromFolderCommand = new DeletePersonFromFolderCommand(
                validIndex, validFolder);
        assertThrows(CommandException.class, () -> deletePersonFromFolderCommand.execute(model));
    }

    @Test
    public void execute_removeIndexOutOfBoundsFromExistingFolder_failure() throws ParseException {
        Folder validFolder = new Folder(new FolderName("folder 1"));
        Person validPerson = new Person(
                new Name("Charlotte Oliveiro"),
                new Phone("93210283"),
                new Email("charlotte@example.com"),
                new Address("Blk 11 Ang Mo Kio Street 74, #11-04"),
                SampleDataUtil.getTagSet("neighbours"));
        validFolder.addContacts(validPerson);
        model.addFolder(validFolder);
        Index validIndex = ParserUtil.parseIndex(stringIndexLarge, new ParseException(DeleteCommand.MESSAGE_USAGE));
        DeletePersonFromFolderCommand deletePersonFromFolderCommand = new DeletePersonFromFolderCommand(
                validIndex, validFolder);
        assertThrows(CommandException.class, () -> deletePersonFromFolderCommand.execute(model));
    }

    @Test
    public void execute_removeContactFromExistingFolder_success() throws ParseException {
        Folder validFolder = new Folder(new FolderName("folder 1"));
        Folder validFolder2 = new Folder(new FolderName("folder 1"));

        validFolder.addContacts(TypicalPersons.ALICE);
        model.addFolder(validFolder);

        Index validIndex = ParserUtil.parseIndex(stringIndex, new ParseException(DeleteCommand.MESSAGE_USAGE));
        DeletePersonFromFolderCommand deletePersonFromFolderCommand = new DeletePersonFromFolderCommand(
                validIndex, validFolder2);


        Model expectedModel = new ModelManager(getTypicalAddressBook(), new UserPrefs());
        expectedModel.addFolder(validFolder2);
        String expectedMessage = String.format(deletePersonFromFolderCommand
                .MESSAGE_DELETE_PERSON_FROM_FOLDER_SUCCESS, validFolder);

        assertCommandSuccess(
                deletePersonFromFolderCommand,
                model,
                expectedMessage,
                expectedModel);
    }

}
