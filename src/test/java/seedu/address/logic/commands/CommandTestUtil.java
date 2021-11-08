package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_BILLING;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DATE_TIME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ID;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MEMBER;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_REMARK;
import static seedu.address.logic.parser.CliSyntax.PREFIX_RESERVATION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TRANSACTION;
import static seedu.address.testutil.Assert.assertThrows;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.EzFoodie;
import seedu.address.model.Model;
import seedu.address.model.member.Member;
import seedu.address.model.member.NameContainsKeywordsPredicate;
import seedu.address.testutil.EditMemberDescriptorBuilder;

/**
 * Contains helper methods for testing commands.
 */
public class CommandTestUtil {

    public static final String VALID_ID_AMY = "00001";
    public static final String VALID_ID_BOB = "00002";
    public static final String VALID_NAME_AMY = "Amy Bee";
    public static final String VALID_NAME_BOB = "Bob Choo";
    public static final String VALID_PHONE_AMY = "91111111";
    public static final String VALID_PHONE_BOB = "82222222";
    public static final String VALID_EMAIL_AMY = "amy@example.com";
    public static final String VALID_EMAIL_BOB = "bob@example.com";
    public static final String VALID_ADDRESS_AMY = "Block 312, Amy Street 1";
    public static final String VALID_ADDRESS_BOB = "Block 123, Bobby Street 3";
    public static final String VALID_RESERVATION_ID_200_AMY = "000001";
    public static final String VALID_RESERVATION_ID_200_BOB = "000001";
    public static final String VALID_RESERVATION_ID_300_BOB = "000002";
    public static final String VALID_REGISTRATION_TIMESTAMP_AMY = "1609459200000";
    public static final String VALID_REGISTRATION_TIMESTAMP_BOB = "1609549200000";
    public static final String VALID_RESERVATION_DATE_TIME_200 = "2021-01-01 00:00";
    public static final String VALID_RESERVATION_DATE_TIME_300 = "2021-01-02 00:00";
    public static final String VALID_RESERVATION_REMARK_200 = "2 people";
    public static final String VALID_RESERVATION_REMARK_300 = "3 people";
    public static final String VALID_CREDIT_AMY = "200";
    public static final String VALID_CREDIT_BOB = "500";
    public static final String VALID_POINT_AMY = "200";
    public static final String VALID_POINT_BOB = "500";
    public static final String VALID_TAG_HUSBAND = "husband";
    public static final String VALID_TAG_FRIEND = "friend";
    public static final String VALID_TRANSACTION_ID_200_AMY = "000001";
    public static final String VALID_TRANSACTION_ID_200_BOB = "000001";
    public static final String VALID_TRANSACTION_ID_300_BOB = "000002";
    public static final String VALID_TRANSACTION_TIMESTAMP_200 = "1609459200000";
    public static final String VALID_TRANSACTION_TIMESTAMP_300 = "1609542000000";
    public static final String VALID_TRANSACTION_BILLING_200 = "200.00";
    public static final String VALID_TRANSACTION_BILLING_300 = "300.00";

    public static final String MEMBER_DESC = " " + PREFIX_MEMBER;
    public static final String ID_DESC_AMY = " " + PREFIX_ID + VALID_ID_AMY;
    public static final String NAME_DESC_AMY = " " + PREFIX_NAME + VALID_NAME_AMY;
    public static final String NAME_DESC_BOB = " " + PREFIX_NAME + VALID_NAME_BOB;
    public static final String PHONE_DESC_AMY = " " + PREFIX_PHONE + VALID_PHONE_AMY;
    public static final String PHONE_DESC_BOB = " " + PREFIX_PHONE + VALID_PHONE_BOB;
    public static final String EMAIL_DESC_AMY = " " + PREFIX_EMAIL + VALID_EMAIL_AMY;
    public static final String EMAIL_DESC_BOB = " " + PREFIX_EMAIL + VALID_EMAIL_BOB;
    public static final String ADDRESS_DESC_AMY = " " + PREFIX_ADDRESS + VALID_ADDRESS_AMY;
    public static final String ADDRESS_DESC_BOB = " " + PREFIX_ADDRESS + VALID_ADDRESS_BOB;
    public static final String TAG_DESC_FRIEND = " " + PREFIX_TAG + VALID_TAG_FRIEND;
    public static final String TAG_DESC_HUSBAND = " " + PREFIX_TAG + VALID_TAG_HUSBAND;
    public static final String TRANSACTION_DESC = " " + PREFIX_TRANSACTION;
    public static final String TRANSACTION_BILLING_DESC_200 = " " + PREFIX_BILLING + VALID_TRANSACTION_BILLING_200;
    public static final String TRANSACTION_BILLING_DESC_300 = " " + PREFIX_BILLING + VALID_TRANSACTION_BILLING_300;
    public static final String RESERVATION_DESC = " " + PREFIX_RESERVATION;
    public static final String RESERVATION_DATE_TIME_DESC_200 =
            " " + PREFIX_DATE_TIME + VALID_RESERVATION_DATE_TIME_200;
    public static final String RESERVATION_DATE_TIME_DESC_300 =
            " " + PREFIX_DATE_TIME + VALID_RESERVATION_DATE_TIME_300;
    public static final String RESERVATION_REMARK_DESC_200 = " " + PREFIX_REMARK + VALID_RESERVATION_REMARK_200;
    public static final String RESERVATION_REMARK_DESC_300 = " " + PREFIX_REMARK + VALID_RESERVATION_REMARK_300;

    public static final String INVALID_NAME_DESC = " " + PREFIX_NAME + "James&"; // '&' not allowed in names
    public static final String INVALID_PHONE_DESC = " " + PREFIX_PHONE + "911a"; // 'a' not allowed in phones
    public static final String INVALID_EMAIL_DESC = " " + PREFIX_EMAIL + "bob!yahoo"; // missing '@' symbol
    public static final String INVALID_ADDRESS_DESC = " " + PREFIX_ADDRESS; // empty string not allowed for addresses
    public static final String INVALID_TAG_DESC = " " + PREFIX_TAG + "hubby*"; // '*' not allowed in tags
    public static final String INVALID_TRANSACTION_BILLING_DESC =
            " " + PREFIX_BILLING + "$123.12"; // '$' not allowed in billing
    public static final String INVALID_RESERVATION_DATE_TIME_DESC =
            " " + PREFIX_DATE_TIME + "2021-0*-01"; // '*' not allowed in dateTime
    public static final String INVALID_RESERVATION_REMARK_DESC = " " + PREFIX_REMARK + " "; // ' ' not allowed in remark

    public static final String PREAMBLE_WHITESPACE = "\t  \r  \n";
    public static final String PREAMBLE_NON_EMPTY = "NonEmptyPreamble";

    public static final EditMemberCommand.EditMemberDescriptor DESC_AMY;
    public static final EditMemberCommand.EditMemberDescriptor DESC_BOB;

    static {
        DESC_AMY = new EditMemberDescriptorBuilder().withName(VALID_NAME_AMY)
                .withPhone(VALID_PHONE_AMY).withEmail(VALID_EMAIL_AMY).withAddress(VALID_ADDRESS_AMY)
                .withTags(VALID_TAG_FRIEND).build();
        DESC_BOB = new EditMemberDescriptorBuilder().withName(VALID_NAME_BOB)
                .withPhone(VALID_PHONE_BOB).withEmail(VALID_EMAIL_BOB).withAddress(VALID_ADDRESS_BOB)
                .withTags(VALID_TAG_HUSBAND, VALID_TAG_FRIEND).build();
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
     * - the ezFoodie, updated member list and selected member in {@code actualModel} remain unchanged
     */
    public static void assertCommandFailure(Command command, Model actualModel, String expectedMessage) {
        // we are unable to defensively copy the model for comparison later, so we can
        // only do so by copying its components.
        EzFoodie expectedEzFoodie = new EzFoodie(actualModel.getEzFoodie());
        List<Member> expectedUpdatedList = new ArrayList<>(actualModel.getUpdatedMemberList());

        assertThrows(CommandException.class, expectedMessage, () -> command.execute(actualModel));
        assertEquals(expectedEzFoodie, actualModel.getEzFoodie());
        assertEquals(expectedUpdatedList, actualModel.getUpdatedMemberList());
    }

    /**
     * Updates {@code model}'s filtered list to show only the member at the given {@code targetIndex} in the
     * {@code model}'s ezFoodie.
     */
    public static void showMemberAtIndex(Model model, Index targetIndex) {
        assertTrue(targetIndex.getZeroBased() < model.getUpdatedMemberList().size());

        Member member = model.getUpdatedMemberList().get(targetIndex.getZeroBased());
        final String[] splitName = member.getName().fullName.split("\\s+");
        model.updateFilteredMemberList(new NameContainsKeywordsPredicate(Arrays.asList(splitName[0])));

        assertEquals(1, model.getUpdatedMemberList().size());
    }

}
