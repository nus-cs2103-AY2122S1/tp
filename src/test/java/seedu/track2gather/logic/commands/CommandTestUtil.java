package seedu.track2gather.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.track2gather.logic.parser.CliSyntax.PREFIX_CASE_NUMBER;
import static seedu.track2gather.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.track2gather.logic.parser.CliSyntax.PREFIX_HOME_ADDRESS;
import static seedu.track2gather.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.track2gather.logic.parser.CliSyntax.PREFIX_NEXT_OF_KIN_ADDRESS;
import static seedu.track2gather.logic.parser.CliSyntax.PREFIX_NEXT_OF_KIN_NAME;
import static seedu.track2gather.logic.parser.CliSyntax.PREFIX_NEXT_OF_KIN_PHONE;
import static seedu.track2gather.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.track2gather.logic.parser.CliSyntax.PREFIX_QUARANTINE_ADDRESS;
import static seedu.track2gather.logic.parser.CliSyntax.PREFIX_SHN_PERIOD;
import static seedu.track2gather.logic.parser.CliSyntax.PREFIX_WORK_ADDRESS;
import static seedu.track2gather.testutil.Assert.assertThrows;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.track2gather.commons.core.index.Index;
import seedu.track2gather.logic.commands.exceptions.CommandException;
import seedu.track2gather.model.Model;
import seedu.track2gather.model.Track2Gather;
import seedu.track2gather.model.person.Person;
import seedu.track2gather.model.person.predicates.NameContainsKeywordsPredicate;
import seedu.track2gather.testutil.EditPersonDescriptorBuilder;

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
    public static final String VALID_CASE_NUMBER_AMY = "1";
    public static final String VALID_CASE_NUMBER_BOB = "2";
    public static final String VALID_HOME_ADDRESS_AMY = "Block 312, Amy Street 1";
    public static final String VALID_HOME_ADDRESS_BOB = "Block 123, Bobby Street 3";
    public static final String VALID_WORK_ADDRESS_AMY = "Block 312, Amy Work Street 1";
    public static final String VALID_WORK_ADDRESS_BOB = "Block 123, Bob Work Street 3";
    public static final String VALID_QUARANTINE_ADDRESS_AMY = "Block 312, Amy Quarantine Street 1";
    public static final String VALID_QUARANTINE_ADDRESS_BOB = "Block 123, Bob Quarantine Street 3";
    public static final String VALID_SHN_PERIOD_AMY = "2020-01-01 => 2020-02-02";
    public static final String VALID_SHN_PERIOD_BOB = "2021-01-01 => 2021-02-02";
    public static final String VALID_NEXT_OF_KIN_NAME_AMY = "Daniel Eng";
    public static final String VALID_NEXT_OF_KIN_NAME_BOB = "Frank Goh";
    public static final String VALID_NEXT_OF_KIN_PHONE_AMY = "88888888";
    public static final String VALID_NEXT_OF_KIN_PHONE_BOB = "99999999";
    public static final String VALID_NEXT_OF_KIN_ADDRESS_AMY = "Block 312, Amy NOK Street 1";
    public static final String VALID_NEXT_OF_KIN_ADDRESS_BOB = "Block 123, Bob NOK Street 3";
    public static final String VALID_CALL_STATUS_AMY = "0 false";
    public static final String VALID_CALL_STATUS_BOB = "0 false";

    //TODO: Amend shnPeriod so that output = input
    public static final String NAME_DESC_AMY = " " + PREFIX_NAME + VALID_NAME_AMY;
    public static final String NAME_DESC_BOB = " " + PREFIX_NAME + VALID_NAME_BOB;
    public static final String PHONE_DESC_AMY = " " + PREFIX_PHONE + VALID_PHONE_AMY;
    public static final String PHONE_DESC_BOB = " " + PREFIX_PHONE + VALID_PHONE_BOB;
    public static final String EMAIL_DESC_AMY = " " + PREFIX_EMAIL + VALID_EMAIL_AMY;
    public static final String EMAIL_DESC_BOB = " " + PREFIX_EMAIL + VALID_EMAIL_BOB;
    public static final String CASE_NUMBER_DESC_AMY = " " + PREFIX_CASE_NUMBER + VALID_CASE_NUMBER_AMY;
    public static final String CASE_NUMBER_DESC_BOB = " " + PREFIX_CASE_NUMBER + VALID_CASE_NUMBER_BOB;
    public static final String HOME_ADDRESS_DESC_AMY = " " + PREFIX_HOME_ADDRESS + VALID_HOME_ADDRESS_AMY;
    public static final String HOME_ADDRESS_DESC_BOB = " " + PREFIX_HOME_ADDRESS + VALID_HOME_ADDRESS_BOB;
    public static final String WORK_ADDRESS_DESC_AMY = " " + PREFIX_WORK_ADDRESS + VALID_WORK_ADDRESS_AMY;
    public static final String WORK_ADDRESS_DESC_BOB = " " + PREFIX_WORK_ADDRESS + VALID_WORK_ADDRESS_BOB;
    public static final String QUARANTINE_ADDRESS_DESC_AMY =
            " " + PREFIX_QUARANTINE_ADDRESS + VALID_QUARANTINE_ADDRESS_AMY;
    public static final String QUARANTINE_ADDRESS_DESC_BOB =
            " " + PREFIX_QUARANTINE_ADDRESS + VALID_QUARANTINE_ADDRESS_BOB;
    public static final String SHN_PERIOD_DESC_AMY = " " + PREFIX_SHN_PERIOD + "2020-01-01 2020-02-02";
    public static final String SHN_PERIOD_DESC_BOB = " " + PREFIX_SHN_PERIOD + "2021-01-01 2021-02-02";
    public static final String NEXT_OF_KIN_NAME_DESC_AMY = " " + PREFIX_NEXT_OF_KIN_NAME + VALID_NEXT_OF_KIN_NAME_AMY;
    public static final String NEXT_OF_KIN_NAME_DESC_BOB = " " + PREFIX_NEXT_OF_KIN_NAME + VALID_NEXT_OF_KIN_NAME_BOB;
    public static final String NEXT_OF_KIN_PHONE_DESC_AMY =
            " " + PREFIX_NEXT_OF_KIN_PHONE + VALID_NEXT_OF_KIN_PHONE_AMY;
    public static final String NEXT_OF_KIN_PHONE_DESC_BOB =
            " " + PREFIX_NEXT_OF_KIN_PHONE + VALID_NEXT_OF_KIN_PHONE_BOB;
    public static final String NEXT_OF_KIN_ADDRESS_DESC_AMY =
            " " + PREFIX_NEXT_OF_KIN_ADDRESS + VALID_NEXT_OF_KIN_ADDRESS_AMY;
    public static final String NEXT_OF_KIN_ADDRESS_DESC_BOB =
            " " + PREFIX_NEXT_OF_KIN_ADDRESS + VALID_NEXT_OF_KIN_ADDRESS_BOB;

    public static final String INVALID_NAME_DESC = " " + PREFIX_NAME + "James&"; // '&' not allowed in names
    public static final String INVALID_PHONE_DESC = " " + PREFIX_PHONE + "911a"; // 'a' not allowed in phones
    public static final String INVALID_EMAIL_DESC = " " + PREFIX_EMAIL + "bob!yahoo"; // missing '@' symbol
    public static final String INVALID_CASE_NUMBER_DESC = " " + PREFIX_CASE_NUMBER + "-1"; // negative not allowed
    // empty string not allowed for addresses
    public static final String INVALID_HOME_ADDRESS_DESC = " " + PREFIX_HOME_ADDRESS;
    public static final String INVALID_WORK_ADDRESS_DESC = " " + PREFIX_WORK_ADDRESS;
    public static final String INVALID_QUARANTINE_ADDRESS_DESC = " " + PREFIX_QUARANTINE_ADDRESS;
    // end date earlier than start date not allowed for shn periods
    public static final String INVALID_SHN_PERIOD_DESC = " " + PREFIX_SHN_PERIOD + "2020-02-02 2020-01-01";
    // '!' not allowed
    public static final String INVALID_NEXT_OF_KIN_NAME_DESC = " " + PREFIX_NEXT_OF_KIN_NAME + "Frank!";
    // inputs less than 3 digits not allowed for phones
    public static final String INVALID_NEXT_OF_KIN_PHONE_DESC = " " + PREFIX_NEXT_OF_KIN_PHONE + "23";
    // first character whitespace not allowed for addresses
    public static final String INVALID_NEXT_OF_KIN_ADDRESS_DESC = " " + PREFIX_NEXT_OF_KIN_ADDRESS + " ";

    public static final String PREAMBLE_WHITESPACE = "\t  \r  \n";
    public static final String PREAMBLE_NON_EMPTY = "NonEmptyPreamble";

    public static final EditCommand.EditPersonDescriptor DESC_AMY;
    public static final EditCommand.EditPersonDescriptor DESC_BOB;

    static {
        DESC_AMY = new EditPersonDescriptorBuilder().withName(VALID_NAME_AMY)
                .withPhone(VALID_PHONE_AMY).withEmail(VALID_EMAIL_AMY).withCaseNumber(VALID_CASE_NUMBER_AMY)
                .withHomeAddress(VALID_HOME_ADDRESS_AMY).build();
        DESC_BOB = new EditPersonDescriptorBuilder().withName(VALID_NAME_BOB)
                .withPhone(VALID_PHONE_BOB).withEmail(VALID_EMAIL_BOB).withCaseNumber(VALID_CASE_NUMBER_BOB)
                .withHomeAddress(VALID_HOME_ADDRESS_BOB).build();
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
     * - the contacts list, filtered person list and selected person in {@code actualModel} remain unchanged
     */
    public static void assertCommandFailure(Command command, Model actualModel, String expectedMessage) {
        // we are unable to defensively copy the model for comparison later, so we can
        // only do so by copying its components.
        Track2Gather expectedTrack2Gather = new Track2Gather(actualModel.getTrack2Gather());
        List<Person> expectedFilteredList = new ArrayList<>(actualModel.getFilteredPersonList());

        assertThrows(CommandException.class, expectedMessage, () -> command.execute(actualModel));
        assertEquals(expectedTrack2Gather, actualModel.getTrack2Gather());
        assertEquals(expectedFilteredList, actualModel.getFilteredPersonList());
    }
    /**
     * Updates {@code model}'s filtered list to show only the person at the given {@code targetIndex} in the
     * {@code model}'s contacts list.
     */
    public static void showPersonAtIndex(Model model, Index targetIndex) {
        assertTrue(targetIndex.getZeroBased() < model.getFilteredPersonList().size());

        Person person = model.getFilteredPersonList().get(targetIndex.getZeroBased());
        final String[] splitName = person.getName().value.split("\\s+");
        model.updateFilteredPersonList(new NameContainsKeywordsPredicate(Arrays.asList(splitName[0])));

        assertEquals(1, model.getFilteredPersonList().size());
    }

}
