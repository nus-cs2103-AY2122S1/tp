package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MEMBER_INDEX;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MEMBER_INDEX_DEL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_POSITION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TASK_INDEX;
import static seedu.address.testutil.Assert.assertThrows;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.commands.member.MeditCommand;
import seedu.address.logic.parser.CliSyntax;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.module.NameContainsKeywordsPredicate;
import seedu.address.model.module.member.Member;
import seedu.address.testutil.EditMemberDescriptorBuilder;

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
    public static final String VALID_ADDRESS_AMY = "Block 312, Amy Street 1";
    public static final String VALID_ADDRESS_BOB = "Block 123, Bobby Street 3";
    public static final String VALID_POSITION_HUSBAND = "husband";
    public static final String VALID_POSITION_FRIEND = "friend";
    public static final String VALID_POEM_TASK_NAME = "write a poem";
    public static final String VALID_POEM_TASK_DEADLINE = "25/10/2021 18:00";
    public static final String VALID_EVENT_NAME_CHESS = "Chess Competition";
    public static final String VALID_EVENT_NAME_BADMINTON = "Recreational badminton";
    public static final String VALID_EVENT_DATE_CHESS = "10/10/2030";
    public static final String VALID_EVENT_DATE_BADMINTON = "20/09/2020";
    public static final int VALID_EVENT_INDEX = 1;
    public static final int VALID_TASK_INDEX = 1;
    public static final int VALID_MEMBER_INDEX = 1;
    public static final int VALID_MEMBER_INDEX_DEL = 1;

    public static final String NAME_DESC_AMY = " " + PREFIX_NAME + VALID_NAME_AMY;
    public static final String NAME_DESC_BOB = " " + PREFIX_NAME + VALID_NAME_BOB;
    public static final String PHONE_DESC_AMY = " " + PREFIX_PHONE + VALID_PHONE_AMY;
    public static final String PHONE_DESC_BOB = " " + PREFIX_PHONE + VALID_PHONE_BOB;
    public static final String EMAIL_DESC_AMY = " " + PREFIX_EMAIL + VALID_EMAIL_AMY;
    public static final String EMAIL_DESC_BOB = " " + PREFIX_EMAIL + VALID_EMAIL_BOB;
    public static final String ADDRESS_DESC_AMY = " " + PREFIX_ADDRESS + VALID_ADDRESS_AMY;
    public static final String ADDRESS_DESC_BOB = " " + PREFIX_ADDRESS + VALID_ADDRESS_BOB;
    public static final String POSITION_DESC_FRIEND = " " + PREFIX_POSITION + VALID_POSITION_FRIEND;
    public static final String POSITION_DESC_HUSBAND = " " + PREFIX_POSITION + VALID_POSITION_HUSBAND;
    public static final String TASK_NAME_DESC_POEM = " " + PREFIX_NAME + VALID_POEM_TASK_NAME;
    public static final String TASK_DEADLINE_DESC_POEM = " " + PREFIX_DATE + VALID_POEM_TASK_DEADLINE;
    public static final String TASK_INDEX_DESC_ONE = " " + PREFIX_TASK_INDEX + VALID_TASK_INDEX;
    public static final String MEMBER_INDEX_DESC_ONE = " " + PREFIX_MEMBER_INDEX + VALID_MEMBER_INDEX;
    public static final String MEMBER_INDEX_DEL_DESC_ONE = " " + PREFIX_MEMBER_INDEX_DEL + VALID_MEMBER_INDEX;
    public static final String EVENT_NAME_DESC_CHESS = " " + PREFIX_NAME + VALID_EVENT_NAME_CHESS;
    public static final String EVENT_NAME_DESC_BADMINTON = " " + PREFIX_NAME + VALID_EVENT_NAME_BADMINTON;
    public static final String EVENT_DATE_DESC_CHESS = " " + PREFIX_DATE + VALID_EVENT_DATE_CHESS;
    public static final String EVENT_DATE_DESC_BADMINTON = " " + PREFIX_DATE + VALID_EVENT_DATE_BADMINTON;

    public static final String INVALID_NAME_DESC = " " + PREFIX_NAME + "James&"; // '&' not allowed in names
    public static final String INVALID_PHONE_DESC = " " + PREFIX_PHONE + "911a"; // 'a' not allowed in phones
    public static final String INVALID_EMAIL_DESC = " " + PREFIX_EMAIL + "bob!yahoo"; // missing '@' symbol
    public static final String INVALID_ADDRESS_DESC = " " + PREFIX_ADDRESS; // empty string not allowed for addresses
    public static final String INVALID_POSITION_DESC = " " + PREFIX_POSITION + "hubby*"; // '*' not allowed in positions
    public static final String INVALID_TASK_NAME_DESC =
            " " + CliSyntax.PREFIX_NAME + ""; // an empty string is not allowed in names
    public static final String INVALID_TASK_INDEX_DESC =
            " " + PREFIX_TASK_INDEX + "abs"; // 'abs' not allowed in id
    public static final String INVALID_MEMBER_INDEX_DESC =
            " " + PREFIX_MEMBER_INDEX + "abs"; // 'abs' not allowed in id
    public static final String INVALID_MEMBER_INDEX_DEL_DESC =
            " " + PREFIX_MEMBER_INDEX_DEL + "abs"; // 'abs' not allowed in id
    public static final String INVALID_EVENT_NAME_DESC = " " + PREFIX_NAME + "B@DMINTON";
    public static final String INVALID_EVENT_DATE_DESC = " " + PREFIX_DATE + "20/13/2021"; // There is no 13th month.

    public static final String PREAMBLE_WHITESPACE = "\t  \r  \n";
    public static final String PREAMBLE_NON_EMPTY = "NonEmptyPreamble";

    public static final MeditCommand.EditMemberDescriptor DESC_AMY;
    public static final MeditCommand.EditMemberDescriptor DESC_BOB;

    static {
        DESC_AMY = new EditMemberDescriptorBuilder().withName(VALID_NAME_AMY)
                .withPhone(VALID_PHONE_AMY).withEmail(VALID_EMAIL_AMY).withAddress(VALID_ADDRESS_AMY)
                .withPositions(VALID_POSITION_FRIEND).build();
        DESC_BOB = new EditMemberDescriptorBuilder().withName(VALID_NAME_BOB)
                .withPhone(VALID_PHONE_BOB).withEmail(VALID_EMAIL_BOB).withAddress(VALID_ADDRESS_BOB)
                .withPositions(VALID_POSITION_HUSBAND, VALID_POSITION_FRIEND).build();
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
     * - the address book, filtered member list and selected member in {@code actualModel} remain unchanged
     */
    public static void assertCommandFailure(Command command, Model actualModel, String expectedMessage) {
        // we are unable to defensively copy the model for comparison later, so we can
        // only do so by copying its components.
        AddressBook expectedAddressBook = new AddressBook(actualModel.getAddressBook());
        List<Member> expectedFilteredList = new ArrayList<>(actualModel.getFilteredMemberList());

        assertThrows(CommandException.class, expectedMessage, () -> command.execute(actualModel));
        assertEquals(expectedAddressBook, actualModel.getAddressBook());
        assertEquals(expectedFilteredList, actualModel.getFilteredMemberList());
    }
    /**
     * Updates {@code model}'s filtered list to show only the member at the given {@code targetIndex} in the
     * {@code model}'s address book.
     */
    public static void showMemberAtIndex(Model model, Index targetIndex) {
        assertTrue(targetIndex.getZeroBased() < model.getFilteredMemberList().size());

        Member member = model.getFilteredMemberList().get(targetIndex.getZeroBased());
        final String[] splitName = member.getName().fullName.split("\\s+");
        model.updateFilteredMemberList(new NameContainsKeywordsPredicate<Member>(Arrays.asList(splitName[0])));

        assertEquals(1, model.getFilteredMemberList().size());
    }

}
