package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.parser.CliSyntax.*;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.NameContainsKeywordsPredicate;
import seedu.address.model.person.Person;
import seedu.address.testutil.EditPersonDescriptorBuilder;
import seedu.address.testutil.PersonBuilder;


/**
 * Contains helper methods for testing commands.
 */
public class CommandTestUtil {

    public static final String VALID_NAME_AMY = "Amy Bee";
    public static final String VALID_NAME_BOB = "Bob Choo";
    public static final String VALID_NAME_CARL = "Carl Kurz";

    public static final String VALID_EMAIL_AMY = "amy@example.com";
    public static final String VALID_EMAIL_BOB = "bob@example.com";
    public static final String VALID_EMAIL_CARL = "carl@example.com";

    public static final String VALID_MODULE_AMY = "CS2106";
    public static final String VALID_MODULE_BOB = "CS1231";
    public static final String VALID_MODULE_CARL = "CS3230";

    public static final String VALID_TAG_FRIEND = "friend";
    public static final String VALID_TAG_HUSBAND = "husband";
    public static final String VALID_TAG_CLASSMATE = "classmate";

    public static final String VALID_DESCRIPTION_CS2106_PROJECT = "CS2106 Final Network Project";
    public static final String VALID_DESCRIPTION_CS1231S_TUTORIAL = "CS1231S Tutorial 10";
    public static final String VALID_DESCRIPTION_CS3230_LAB = "CS3230 Lab Week 4";
    public static final String VALID_DESCRIPTION_CS2100_LAB = "CS2100 Lab Week 7";
    public static final String VALID_DESCRIPTION_GEQ1000_QUIZ = "GEQ1000 Economics Quiz";

    public static final String VALID_DATE_CS1101S_MISSION = "30/09/2021";
    public static final String VALID_DATE_CS1231S_TUTORIAL = "01/02/2021";
    public static final String VALID_DATE_CS2103_QUIZ = "30/11/2022";
    public static final String VALID_DATE_CS2106_PROJECT = "1/1/2020";
    public static final String VALID_DATE_CS3230_LAB = "11/01/2021";

    public static final String VALID_FRIENDLY_DATE_CS2100_LAB = "tmr";
    public static final String VALID_FRIENDLY_DATE_GEQ1000_QUIZ = "today";

    public static final String VALID_TIME_CS2106_PROJECT = "0001";
    public static final String VALID_TIME_CS1231S_TUTORIAL = "1800";
    public static final String VALID_TIME_CS3230_LAB = "0800";
    public static final String VALID_TIME_CS1101S_MISSION = "1800";
    public static final String VALID_TIME_CS2100_TUTORIAL = "2200";
    public static final String VALID_TIME_CS2100_LAB = "1300";
    public static final String VALID_TIME_GEQ1000_QUIZ = "2359";

    public static final String VALID_DATE_TIME_CS2106_PROJECT =
            VALID_DATE_CS2106_PROJECT + "," + VALID_TIME_CS2106_PROJECT;
    public static final String VALID_DATE_TIME_CS1231S_TUTORIAL =
            VALID_DATE_CS1231S_TUTORIAL + "," + VALID_TIME_CS1231S_TUTORIAL;
    public static final String VALID_DATE_TIME_CS3230_LAB =
            VALID_DATE_CS3230_LAB + "," + VALID_TIME_CS3230_LAB;

    public static final String NAME_DESC_AMY = " " + PREFIX_NAME + VALID_NAME_AMY;
    public static final String NAME_DESC_BOB = " " + PREFIX_NAME + VALID_NAME_BOB;
    public static final String NAME_DESC_CARL = " " + PREFIX_NAME + VALID_NAME_CARL;

    public static final String EMAIL_DESC_AMY = " " + PREFIX_EMAIL + VALID_EMAIL_AMY;
    public static final String EMAIL_DESC_BOB = " " + PREFIX_EMAIL + VALID_EMAIL_BOB;
    public static final String EMAIL_DESC_CARL = " " + PREFIX_EMAIL + VALID_EMAIL_CARL;

    public static final String MODULE_DESC_AMY = " " + PREFIX_MODULE + VALID_MODULE_AMY;
    public static final String MODULE_DESC_BOB = " " + PREFIX_MODULE + VALID_MODULE_BOB;
    public static final String MODULE_DESC_CARL = " " + PREFIX_MODULE + VALID_MODULE_CARL;

    public static final String ASSIGNMENT_DESC_AMY = " " + PREFIX_DESCRIPTION + VALID_DESCRIPTION_CS2106_PROJECT;
    public static final String ASSIGNMENT_DESC_BOB = " " + PREFIX_DESCRIPTION + VALID_DESCRIPTION_CS1231S_TUTORIAL;
    public static final String ASSIGNMENT_DESC_CARL = " " + PREFIX_DESCRIPTION + VALID_DESCRIPTION_CS3230_LAB;

    public static final String DATE_DESC_AMY =
            " " + PREFIX_DUEDATE + VALID_DATE_TIME_CS2106_PROJECT;
    public static final String DATE_DESC_BOB =
            " " + PREFIX_DUEDATE + VALID_DATE_TIME_CS1231S_TUTORIAL;
    public static final String DATE_DESC_CARL =
            " " + PREFIX_DUEDATE + VALID_DATE_TIME_CS3230_LAB;

    public static final String TAG_DESC_FRIEND = " " + PREFIX_TAG + VALID_TAG_FRIEND;
    public static final String TAG_DESC_HUSBAND = " " + PREFIX_TAG + VALID_TAG_HUSBAND;
    public static final String TAG_DESC_CLASSMATE = " " + PREFIX_TAG + VALID_TAG_CLASSMATE;

    public static final String INVALID_DESCRIPTION_DESC =
            " " + PREFIX_DESCRIPTION + "assignment&"; // '&' not allowed in assignment names
    public static final String INVALID_DUEDATE_DESC =
            " " + PREFIX_DUEDATE + "1/1/2021 1450"; // ' ' not allowed in due date
    public static final String INVALID_NAME_DESC = " " + PREFIX_NAME + "James&"; // '&' not allowed in names
    public static final String INVALID_EMAIL_DESC = " " + PREFIX_EMAIL + "bob!yahoo"; // missing '@' symbol
    public static final String INVALID_MODULE_DESC = " " + PREFIX_MODULE; // empty string not allowed for addresses
    public static final String INVALID_TAG_DESC = " " + PREFIX_TAG + "hubby*"; // '*' not allowed in tags

    public static final String INVALID_CS1231S_TUTORIAL_DESCRIPTION =
            " " + PREFIX_DESCRIPTION + "CS1231S @ Tutorial 10";
    public static final String INVALID_DESCRIPTION_CS3230_LAB = "CS3230: Lab Week 4";
    public static final String INVALID_DATE_DESCRIPTION = " " + PREFIX_DUEDATE + "11/13/2021";

    public static final String ZERO_INDEX = "0";
    public static final String NEGATIVE_INDEX = "-1";
    public static final String POSITIVE_INDEX = "1";

    public static final String PREAMBLE_WHITESPACE = "\t  \r  \n";
    public static final String PREAMBLE_NON_EMPTY = "NonEmptyPreamble";

    public static final EditPersonCommand.EditPersonDescriptor DESC_AMY;
    public static final EditPersonCommand.EditPersonDescriptor DESC_BOB;
    public static final EditPersonCommand.EditPersonDescriptor DESC_CARL;

    static {
        DESC_AMY = new EditPersonDescriptorBuilder().withName(VALID_NAME_AMY)
                .withEmail(VALID_EMAIL_AMY).withModule(VALID_MODULE_AMY)
                .withTags(VALID_TAG_FRIEND).build();
        DESC_BOB = new EditPersonDescriptorBuilder().withName(VALID_NAME_BOB)
                .withEmail(VALID_EMAIL_BOB).withModule(VALID_MODULE_BOB)
                .withTags(VALID_TAG_HUSBAND, VALID_TAG_FRIEND).build();
        DESC_CARL = new EditPersonDescriptorBuilder().withName(VALID_NAME_CARL)
                .withEmail(VALID_EMAIL_CARL).withModule(VALID_MODULE_CARL)
                .withTags(VALID_TAG_CLASSMATE).build();
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
            //assertEquals(expectedModel, actualModel);
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

    /**
     * Instantiates another person object with the same fields.
     * @param person the Person object
     * @return another instance of the same Person object
     */
    public static Person clonePerson(Person person) {
        return new PersonBuilder(person).build();
    }

    /**
     * Replaces the person object within the current model with a clone of that person.
     *
     * @param inputModel the given model
     * @param selectedPerson the person to be replaced
     * @param clonedPerson the clone of the person that will replace the original person
     * @return the Model with the selected person replaced by a clone
     */
    public static Model clonePersonInModel(Model inputModel, Person selectedPerson, Person clonedPerson) {
        inputModel.setPerson(selectedPerson, clonedPerson);
        return inputModel;
    }

    /**
     * Creates a new Model with the selected person replaced with a clone.
     * @param selectedPerson the person to be replaced
     * @param clonedPerson the clone of the person that will replace the original person
     * @return a new Model with the selected person replaced by a clone
     */
    public static Model setUpNewModelWithClonedPerson(Person selectedPerson, Person clonedPerson) {
        Model freshModel = new ModelManager(getTypicalAddressBook(), new UserPrefs());
        return clonePersonInModel(freshModel, selectedPerson, clonedPerson);
    }
}
