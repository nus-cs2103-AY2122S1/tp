package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.ID_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_TRANSACTION_BILLING_DESC;
import static seedu.address.logic.commands.CommandTestUtil.PREAMBLE_NON_EMPTY;
import static seedu.address.logic.commands.CommandTestUtil.PREAMBLE_WHITESPACE;
import static seedu.address.logic.commands.CommandTestUtil.TRANSACTION_BILLING_DESC_200;
import static seedu.address.logic.commands.CommandTestUtil.TRANSACTION_BILLING_DESC_300;
import static seedu.address.logic.commands.CommandTestUtil.TRANSACTION_DESC;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ID_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TRANSACTION_BILLING_200;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TRANSACTION_ID_200_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TRANSACTION_TIMESTAMP_200;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.commons.status.ExecutionStatus;
import seedu.address.logic.commands.AddTransactionCommand;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.member.Id;
import seedu.address.model.transaction.Billing;
import seedu.address.model.transaction.Transaction;
import seedu.address.testutil.TransactionBuilder;

/**
 * Contains integration tests (interaction with the Model) and unit tests for
 * {@code AddTransactionCommandParser}.
 */
class AddTransactionCommandParserTest {

    private Model model = new ModelManager();
    private AddTransactionCommandParser parser = new AddTransactionCommandParser(model, ExecutionStatus.TEST);

    @Test
    public void parse_allFieldsPresent_success() {
        Transaction expectedTransaction = new TransactionBuilder().withId(VALID_TRANSACTION_ID_200_AMY)
                .withTimestamp(VALID_TRANSACTION_TIMESTAMP_200).withBilling(VALID_TRANSACTION_BILLING_200).build();
        Id expectedId = new Id(VALID_ID_AMY);

        // whitespace only preamble
        assertParseSuccess(parser, PREAMBLE_WHITESPACE + TRANSACTION_DESC + TRANSACTION_BILLING_DESC_200
                + ID_DESC_AMY, new AddTransactionCommand(expectedTransaction, expectedId));

        // multiple billings - last transaction accepted
        assertParseSuccess(parser, TRANSACTION_DESC + TRANSACTION_BILLING_DESC_300
                + TRANSACTION_BILLING_DESC_200 + ID_DESC_AMY,
                new AddTransactionCommand(expectedTransaction, expectedId));
    }

    @Test
    public void parse_compulsoryFieldMissing_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddTransactionCommand.MESSAGE_USAGE);

        // missing id prefix
        assertParseFailure(parser, TRANSACTION_DESC + TRANSACTION_BILLING_DESC_200, expectedMessage);
    }

    @Test
    public void parse_invalidValue_failure() {
        // invalid billing
        assertParseFailure(parser, TRANSACTION_DESC + INVALID_TRANSACTION_BILLING_DESC + ID_DESC_AMY,
                Billing.MESSAGE_CONSTRAINTS);

        // non-empty preamble
        assertParseFailure(parser, PREAMBLE_NON_EMPTY + TRANSACTION_BILLING_DESC_200 + ID_DESC_AMY,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddTransactionCommand.MESSAGE_USAGE));
    }
}
