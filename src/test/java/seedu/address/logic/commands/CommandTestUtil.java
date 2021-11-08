package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_FREQUENCY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_HEALTH_CONDITION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_LANGUAGE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_LAST_VISIT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_OCCURRENCE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_VISIT;
import static seedu.address.testutil.Assert.assertThrows;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.person.NameContainsKeywordsPredicate;
import seedu.address.model.person.Person;
import seedu.address.testutil.DateTimeUtil;
import seedu.address.testutil.EditPersonDescriptorBuilder;

/**
 * Contains helper methods for testing commands.
 */
public class CommandTestUtil {

    public static final String VALID_NAME_AMY = "Amy Bee";
    public static final String VALID_NAME_BOB = "Bob Choo";
    public static final String VALID_PHONE_AMY = "11111111";
    public static final String VALID_PHONE_BOB = "22222222";
    public static final String VALID_LANGUAGE_AMY = "English";
    public static final String VALID_LANGUAGE_BOB = "Chinese";
    public static final String VALID_ADDRESS_AMY = "Block 312, Amy Street 1";
    public static final String VALID_ADDRESS_BOB = "Block 123, Bobby Street 3";
    public static final String VALID_LAST_VISIT_AMY = "2021-01-01 12:00";
    public static final String VALID_LAST_VISIT_BOB = "2021-01-02 13:00";
    public static final String VALID_VISIT_AMY = DateTimeUtil.getInputStringSevenDaysFromNow();
    public static final String VALID_VISIT_BOB = DateTimeUtil.getInputStringEightDaysFromNow();
    public static final String VALID_FREQUENCY_AMY = "Weekly";
    public static final String VALID_FREQUENCY_BOB = "Monthly";
    public static final String VALID_OCCURRENCE_AMY = "2";
    public static final String VALID_OCCURRENCE_BOB = "4";
    public static final String VALID_HEALTH_CONDITION_DIABETES = "diabetes";
    public static final String VALID_HEALTH_CONDITION_DEMENTIA = "dementia";

    public static final String NAME_DESC_AMY = " " + PREFIX_NAME + VALID_NAME_AMY;
    public static final String NAME_DESC_BOB = " " + PREFIX_NAME + VALID_NAME_BOB;
    public static final String PHONE_DESC_AMY = " " + PREFIX_PHONE + VALID_PHONE_AMY;
    public static final String PHONE_DESC_BOB = " " + PREFIX_PHONE + VALID_PHONE_BOB;
    public static final String LANGUAGE_DESC_AMY = " " + PREFIX_LANGUAGE + VALID_LANGUAGE_AMY;
    public static final String LANGUAGE_DESC_BOB = " " + PREFIX_LANGUAGE + VALID_LANGUAGE_BOB;
    public static final String ADDRESS_DESC_AMY = " " + PREFIX_ADDRESS + VALID_ADDRESS_AMY;
    public static final String ADDRESS_DESC_BOB = " " + PREFIX_ADDRESS + VALID_ADDRESS_BOB;
    public static final String VISIT_DESC_AMY = " " + PREFIX_VISIT + VALID_VISIT_AMY;
    public static final String VISIT_DESC_BOB = " " + PREFIX_VISIT + VALID_VISIT_BOB;
    public static final String LAST_VISIT_DESC_AMY = " " + PREFIX_LAST_VISIT + VALID_LAST_VISIT_AMY;
    public static final String LAST_VISIT_DESC_BOB = " " + PREFIX_LAST_VISIT + VALID_LAST_VISIT_BOB;
    public static final String FREQUENCY_DESC_AMY = " " + PREFIX_FREQUENCY + VALID_FREQUENCY_AMY;
    public static final String FREQUENCY_DESC_BOB = " " + PREFIX_FREQUENCY + VALID_FREQUENCY_BOB;
    public static final String OCCURRENCE_DESC_AMY = " " + PREFIX_OCCURRENCE + VALID_OCCURRENCE_AMY;
    public static final String OCCURRENCE_DESC_BOB = " " + PREFIX_OCCURRENCE + VALID_OCCURRENCE_BOB;
    public static final String HEALTH_CONDITION_DESC_DIABETES = " " + PREFIX_HEALTH_CONDITION
            + VALID_HEALTH_CONDITION_DIABETES;
    public static final String HEALTH_CONDITION_DESC_DEMENTIA = " " + PREFIX_HEALTH_CONDITION
            + VALID_HEALTH_CONDITION_DEMENTIA;

    public static final String INVALID_NAME_DESC = " " + PREFIX_NAME + "James&"; // '&' not allowed in names
    public static final String INVALID_PHONE_DESC = " " + PREFIX_PHONE + "911a"; // 'a' not allowed in phones
    public static final String INVALID_LANGUAGE_DESC = " " + PREFIX_LANGUAGE; // empty string not allowed for languages
    public static final String INVALID_ADDRESS_DESC = " " + PREFIX_ADDRESS; // empty string not allowed for addresses
    // '*' not allowed in healthConditions
    public static final String INVALID_HEALTH_CONDITION_DESC = " " + PREFIX_HEALTH_CONDITION + "heartCondition*";
    public static final String INVALID_LAST_VISIT_DESC = " " + PREFIX_LAST_VISIT + "2000"; //wrong datetime format

    public static final String PREAMBLE_WHITESPACE = "\t  \r  \n";
    public static final String PREAMBLE_NON_EMPTY = "NonEmptyPreamble";

    public static final EditCommand.EditPersonDescriptor DESC_AMY;
    public static final EditCommand.EditPersonDescriptor DESC_BOB;

    static {
        DESC_AMY = new EditPersonDescriptorBuilder().withName(VALID_NAME_AMY)
                .withPhone(VALID_PHONE_AMY).withLanguage(VALID_LANGUAGE_AMY).withAddress(VALID_ADDRESS_AMY)
                .withHealthConditions(VALID_HEALTH_CONDITION_DIABETES).withLastVisit(VALID_LAST_VISIT_AMY).build();
        DESC_BOB = new EditPersonDescriptorBuilder().withName(VALID_NAME_BOB)
                .withPhone(VALID_PHONE_BOB).withLanguage(VALID_LANGUAGE_BOB).withAddress(VALID_ADDRESS_BOB)
                .withHealthConditions(VALID_HEALTH_CONDITION_DEMENTIA, VALID_HEALTH_CONDITION_DIABETES).build();
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
