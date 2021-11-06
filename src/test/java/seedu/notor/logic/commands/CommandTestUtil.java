package seedu.notor.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.notor.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.notor.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.notor.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.notor.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.notor.testutil.Assert.assertThrows;

import java.util.ArrayList;
import java.util.List;

import seedu.notor.commons.core.index.Index;
import seedu.notor.logic.commands.exceptions.CommandException;
import seedu.notor.logic.executors.exceptions.ExecuteException;
import seedu.notor.logic.executors.person.PersonEditExecutor;
import seedu.notor.model.Model;
import seedu.notor.model.Notor;
import seedu.notor.model.group.Group;
import seedu.notor.model.person.NameContainsKeywordsPredicate;
import seedu.notor.model.person.Person;
import seedu.notor.testutil.PersonEditDescriptorBuilder;

/**
 * Contains helper methods for testing commands.
 */
public class CommandTestUtil {
    public static final String VALID_NAME_AMY = "Amy Toh";
    public static final String VALID_NAME_BOB = "Bob Kebab";
    public static final String INVALID_NAME_JAMES = "James&";
    public static final String VALID_PHONE_AMY = "11111111";
    public static final String VALID_PHONE_BOB = "22222222";
    public static final String VALID_EMAIL_AMY = "amy@example.com";
    public static final String VALID_EMAIL_BOB = "bob@example.com";
    public static final String VALID_NOTE_AMY = "Fri, Oct 22 2021 20:58\n\n\nLikes to ski. Wish that I could go to "
            + "sleep now";
    public static final String VALID_NOTE_BOB = "Favourite pastime: Eating";
    public static final String VALID_NOTE_DATE = "Last Modified: (Fri, Oct 22 2021 20:59)";
    public static final String VALID_TAG_HUSBAND = "husband";
    public static final String VALID_TAG_FRIEND = "friend";


    public static final String NAME_DESC_AMY = " " + PREFIX_NAME + VALID_NAME_AMY;
    public static final String NAME_DESC_BOB = " " + PREFIX_NAME + VALID_NAME_BOB;
    public static final String PHONE_DESC_AMY = " " + PREFIX_PHONE + VALID_PHONE_AMY;
    public static final String PHONE_DESC_BOB = " " + PREFIX_PHONE + VALID_PHONE_BOB;
    public static final String EMAIL_DESC_AMY = " " + PREFIX_EMAIL + VALID_EMAIL_AMY;
    public static final String EMAIL_DESC_BOB = " " + PREFIX_EMAIL + VALID_EMAIL_BOB;
    public static final String TAG_DESC_FRIEND = " " + PREFIX_TAG + VALID_TAG_FRIEND;
    public static final String TAG_DESC_HUSBAND = " " + PREFIX_TAG + VALID_TAG_HUSBAND;
    public static final String TAG_MULTIPLE_TAGS = " " + PREFIX_TAG + VALID_TAG_HUSBAND + ", " + VALID_TAG_FRIEND;

    public static final String INVALID_NAME_DESC = " " + PREFIX_NAME + "James&"; // '&' not allowed in names
    public static final String INVALID_PHONE_DESC = " " + PREFIX_PHONE + "911a"; // 'a' not allowed in phones
    public static final String INVALID_EMAIL_DESC = " " + PREFIX_EMAIL + "bob!yahoo"; // missing '@' symbol
    public static final String INVALID_TAG_DESC = " " + PREFIX_TAG + "hubby*"; // '*' not allowed in tags

    public static final String PREAMBLE_WHITESPACE = "\t  \r  \n";
    public static final String PREAMBLE_NON_EMPTY = "NonEmptyPreamble";

    public static final PersonEditExecutor.PersonEditDescriptor DESC_AMY;
    public static final PersonEditExecutor.PersonEditDescriptor DESC_BOB;

    static {
        DESC_AMY = new PersonEditDescriptorBuilder().withName(VALID_NAME_AMY)
                .withPhone(VALID_PHONE_AMY).withEmail(VALID_EMAIL_AMY)
                .build();
        DESC_BOB = new PersonEditDescriptorBuilder().withName(VALID_NAME_BOB)
                .withPhone(VALID_PHONE_BOB).withEmail(VALID_EMAIL_BOB)
                .build();
    }

    /**
     * Executes the given {@code command}, confirms that <br>
     * - the returned {@link CommandResult} matches {@code expectedCommandResult} <br>
     * - the {@code actualModel} matches {@code expectedModel}
     */
    public static void assertExecuteSuccess(Command command, Model actualModel, CommandResult expectedCommandResult,
            Model expectedModel) {
        try {
            CommandResult result = command.execute(actualModel);
            assertEquals(expectedCommandResult, result);
            assertEquals(expectedModel, actualModel);
        } catch (CommandException | ExecuteException ce) {
            throw new AssertionError("Execution of command should not fail.", ce);
        }
    }

    /**
     * Convenience wrapper to {@link #assertExecuteSuccess(Command, Model, CommandResult, Model)}
     * that takes a string {@code expectedMessage}.
     */
    public static void assertExecuteSuccess(Command command, Model actualModel, String expectedMessage,
            Model expectedModel) {
        CommandResult expectedCommandResult = new CommandResult(expectedMessage);
        assertExecuteSuccess(command, actualModel, expectedCommandResult, expectedModel);
    }

    /**
     * Executes the given {@code command}, confirms that <br>
     * - a {@code CommandException} is thrown <br>
     * - the ExecuteException message matches {@code expectedMessage} <br>
     * - the Notor, filtered person list and selected person in {@code actualModel} remain unchanged
     */
    public static void assertExecuteFailure(Command command, Model actualModel, String expectedMessage) {
        // we are unable to defensively copy the model for comparison later, so we can
        // only do so by copying its components.
        Notor expectedNotor = new Notor(actualModel.getNotor());
        List<Person> expectedFilteredList = new ArrayList<>(actualModel.getFilteredPersonList());

        assertThrows(ExecuteException.class, expectedMessage, () -> command.execute(actualModel));
        assertEquals(expectedNotor, actualModel.getNotor());
        assertEquals(expectedFilteredList, actualModel.getFilteredPersonList());
    }

    /**
     * Updates {@code model}'s filtered list to show only the person at the given {@code targetIndex} in the
     * {@code model}'s Notor.
     */
    public static void showPersonAtIndex(Model model, Index targetIndex) {
        assertTrue(targetIndex.getZeroBased() < model.getFilteredPersonList().size());

        Person person = model.getFilteredPersonList().get(targetIndex.getZeroBased());
        final String[] splitName = person.getName().fullName.split("\\s+");
        model.updateFilteredPersonList(new NameContainsKeywordsPredicate(List.of(splitName[0])));

        assertEquals(1, model.getFilteredPersonList().size());
    }

    /**
     * Updates {@code model}'s filtered list to show only the group at the given {@code targetIndex} in the
     * {@code model}'s Notor.
     */
    public static void showGroupAtIndex(Model model, Index targetIndex) {
        assertTrue(targetIndex.getZeroBased() < model.getFilteredGroupList().size());

        Group group = model.getFilteredGroupList().get(targetIndex.getZeroBased());
        model.updateFilteredGroupList(x -> x.getName().equals(group.getName()));

        assertEquals(1, model.getFilteredGroupList().size());
    }

}
