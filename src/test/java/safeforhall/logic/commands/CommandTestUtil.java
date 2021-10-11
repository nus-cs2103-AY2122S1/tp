package safeforhall.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static safeforhall.logic.parser.CliSyntax.PREFIX_DATE1;
import static safeforhall.logic.parser.CliSyntax.PREFIX_DATE2;
import static safeforhall.logic.parser.CliSyntax.PREFIX_EMAIL;
import static safeforhall.logic.parser.CliSyntax.PREFIX_FACULTY;
import static safeforhall.logic.parser.CliSyntax.PREFIX_KEYWORD;
import static safeforhall.logic.parser.CliSyntax.PREFIX_NAME;
import static safeforhall.logic.parser.CliSyntax.PREFIX_PHONE;
import static safeforhall.logic.parser.CliSyntax.PREFIX_ROOM;
import static safeforhall.logic.parser.CliSyntax.PREFIX_VACCSTATUS;
import static safeforhall.testutil.Assert.assertThrows;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import safeforhall.commons.core.index.Index;
import safeforhall.logic.commands.exceptions.CommandException;
import safeforhall.model.AddressBook;
import safeforhall.model.Model;
import safeforhall.model.person.NameContainsKeywordsPredicate;
import safeforhall.model.person.Person;
import safeforhall.testutil.EditPersonDescriptorBuilder;

/**
 * Contains helper methods for testing commands.
 */
public class CommandTestUtil {

    public static final String VALID_NAME_AMY = "Amy Bee";
    public static final String VALID_NAME_BOB = "Bob Choo";
    public static final String VALID_PHONE_AMY = "11111111";
    public static final String VALID_PHONE_BOB = "22222222";
    public static final String VALID_EMAIL_AMY = "amy@example.com";
    public static final String VALID_EMAIL_BOB = "bob@example.com";
    public static final String VALID_ROOM_AMY = "A100";
    public static final String VALID_ROOM_BOB = "E420";
    public static final String VALID_FACULTY_AMY = "SoC";
    public static final String VALID_FACULTY_BOB = "FASS";
    public static final String VALID_VACCSTATUS_AMY = "T";
    public static final String VALID_VACCSTATUS_BOB = "F";
    public static final String VALID_LAST_DATE1_OCT = "10-10-2021";
    public static final String VALID_LAST_DATE2_OCT = "15-10-2021";
    public static final String VALID_KEYWORD_F = "f";
    public static final String VALID_KEYWORD_C = "c";

    public static final String NAME_DESC_AMY = " " + PREFIX_NAME + VALID_NAME_AMY;
    public static final String NAME_DESC_BOB = " " + PREFIX_NAME + VALID_NAME_BOB;
    public static final String PHONE_DESC_AMY = " " + PREFIX_PHONE + VALID_PHONE_AMY;
    public static final String PHONE_DESC_BOB = " " + PREFIX_PHONE + VALID_PHONE_BOB;
    public static final String EMAIL_DESC_AMY = " " + PREFIX_EMAIL + VALID_EMAIL_AMY;
    public static final String EMAIL_DESC_BOB = " " + PREFIX_EMAIL + VALID_EMAIL_BOB;
    public static final String ROOM_DESC_AMY = " " + PREFIX_ROOM + VALID_ROOM_AMY;
    public static final String ROOM_DESC_BOB = " " + PREFIX_ROOM + VALID_ROOM_BOB;
    public static final String FACULTY_DESC_AMY = " " + PREFIX_FACULTY + VALID_FACULTY_AMY;
    public static final String FACULTY_DESC_BOB = " " + PREFIX_FACULTY + VALID_FACULTY_BOB;
    public static final String VACCSTATUS_DESC_AMY = " " + PREFIX_VACCSTATUS + VALID_VACCSTATUS_AMY;
    public static final String VACCSTATUS_DESC_BOB = " " + PREFIX_VACCSTATUS + VALID_VACCSTATUS_BOB;
    public static final String LAST_DATE1_DESC_OCT = " " + PREFIX_DATE1 + VALID_LAST_DATE1_OCT;
    public static final String LAST_DATE2_DESC_OCT = " " + PREFIX_DATE2 + VALID_LAST_DATE2_OCT;
    public static final String KEYWORD_DESC_F = " " + PREFIX_KEYWORD + VALID_KEYWORD_F;
    public static final String KEYWORD_DESC_C = " " + PREFIX_KEYWORD + VALID_KEYWORD_C;


    public static final String INVALID_NAME_DESC = " " + PREFIX_NAME + "James&"; // '&' not allowed in names
    public static final String INVALID_PHONE_DESC = " " + PREFIX_PHONE + "911a"; // 'a' not allowed in phones
    public static final String INVALID_EMAIL_DESC = " " + PREFIX_EMAIL + "bob!yahoo"; // missing '@' symbol
    public static final String INVALID_ROOM_DESC = " " + PREFIX_ROOM; // empty string not allowed for room
    public static final String INVALID_FACULTY_DESC = " " + PREFIX_FACULTY; // empty string not allowed for faculty
    public static final String INVALID_VACCSTATUS_DESC = " " + PREFIX_VACCSTATUS + "fake"; //only T or F allowed

    public static final String PREAMBLE_WHITESPACE = "\t  \r  \n";
    public static final String PREAMBLE_NON_EMPTY = "NonEmptyPreamble";

    public static final EditCommand.EditPersonDescriptor DESC_AMY;
    public static final EditCommand.EditPersonDescriptor DESC_BOB;

    static {
        DESC_AMY = new EditPersonDescriptorBuilder().withName(VALID_NAME_AMY)
                .withPhone(VALID_PHONE_AMY).withEmail(VALID_EMAIL_AMY)
                .build();
        DESC_BOB = new EditPersonDescriptorBuilder().withName(VALID_NAME_BOB)
                .withPhone(VALID_PHONE_BOB).withEmail(VALID_EMAIL_BOB)
                .build();
    }

    /**
     * Executes the given {@code command}, confirms that <br>
     * - the returned {@link CommandResult} matches {@code expectedCommandResult} <br>
     * - the {@code actualModel} matches {@code expectedModel}
     */
    public static void assertCommandSuccess(Command command, Model actualModel, CommandResult expectedCommandResult,
                                            Model expectedModel) {
        try {
            CommandResult result = command.execute(actualModel);
            assertEquals(expectedCommandResult, result);
            assertEquals(expectedModel, actualModel);
        } catch (CommandException ce) {
            throw new AssertionError("Execution of command should not fail.", ce);
        }
    }

    /**
     * Convenience wrapper to {@link #assertCommandSuccess(Command, Model, CommandResult, Model)}
     * that takes a string {@code expectedMessage}.
     */
    public static void assertCommandSuccess(Command command, Model actualModel, String expectedMessage,
            Model expectedModel) {
        CommandResult expectedCommandResult = new CommandResult(expectedMessage);
        assertCommandSuccess(command, actualModel, expectedCommandResult, expectedModel);
    }

    /**
     * Executes the given {@code command}, confirms that <br>
     * - a {@code CommandException} is thrown <br>
     * - the CommandException message matches {@code expectedMessage} <br>
     * - the address book, filtered person list and selected person in {@code actualModel} remain unchanged
     */
    public static void assertCommandFailure(Command command, Model actualModel, String expectedMessage) {
        // we are unable to defensively copy the model for comparison later, so we can
        // only do so by copying its components.
        AddressBook expectedAddressBook = new AddressBook(actualModel.getAddressBook());
        List<Person> expectedFilteredList = new ArrayList<>(actualModel.getFilteredPersonList());

        assertThrows(CommandException.class, expectedMessage, () -> command.execute(actualModel));
        assertEquals(expectedAddressBook, actualModel.getAddressBook());
        assertEquals(expectedFilteredList, actualModel.getFilteredPersonList());
    }
    /**
     * Updates {@code model}'s filtered list to show only the person at the given {@code targetIndex} in the
     * {@code model}'s address book.
     */
    public static void showPersonAtIndex(Model model, Index targetIndex) {
        assertTrue(targetIndex.getZeroBased() < model.getFilteredPersonList().size());

        Person person = model.getFilteredPersonList().get(targetIndex.getZeroBased());
        final String[] splitName = person.getName().fullName.split("\\s+");
        model.updateFilteredPersonList(new NameContainsKeywordsPredicate(Arrays.asList(splitName[0])));

        assertEquals(1, model.getFilteredPersonList().size());
    }

}
