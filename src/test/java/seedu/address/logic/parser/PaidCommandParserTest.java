package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_LESSON_DISPLAYED_INDEX;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_STUDENT_DISPLAYED_INDEX;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_PAYMENT_DESC;
import static seedu.address.logic.commands.CommandTestUtil.PAYMENT_DESC;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PAYMENT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PAID_AMOUNT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_LESSON;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_PERSON;
import static seedu.address.testutil.TypicalIndexes.INDEX_THIRD_LESSON;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.PaidCommand;
import seedu.address.model.lesson.Money;

class PaidCommandParserTest {
    private static final String MESSAGE_INVALID_FORMAT =
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, PaidCommand.MESSAGE_USAGE);

    private static final String VALID_INDICES =
            INDEX_FIRST_PERSON.getOneBased() + " " + INDEX_THIRD_LESSON.getOneBased();

    private static final String INVALID_INDICES = INDEX_FIRST_PERSON.getOneBased() + " " + 0;

    private final PaidCommandParser parser = new PaidCommandParser();

    @Test
    public void parse_invalidPreamble_failure() {
        // negative student index
        assertParseFailure(parser, "-5 1" + PAYMENT_DESC, MESSAGE_INVALID_STUDENT_DISPLAYED_INDEX);

        // zero student index
        assertParseFailure(parser, "0 1" + PAYMENT_DESC, MESSAGE_INVALID_STUDENT_DISPLAYED_INDEX);

        // negative lesson index
        assertParseFailure(parser, "1 -7" + PAYMENT_DESC, MESSAGE_INVALID_LESSON_DISPLAYED_INDEX);

        // zero lesson index
        assertParseFailure(parser, "1 0" + PAYMENT_DESC, MESSAGE_INVALID_LESSON_DISPLAYED_INDEX);

        // invalid student and lesson index
        assertParseFailure(parser, "-1 0" + PAYMENT_DESC, MESSAGE_INVALID_STUDENT_DISPLAYED_INDEX);

        // invalid arguments being parsed as preamble
        assertParseFailure(parser, "1 some random string", MESSAGE_INVALID_FORMAT);

        // invalid prefix being parsed as preamble
        assertParseFailure(parser, "1 i/ string", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_missingParts_failure() {
        // no index specified
        assertParseFailure(parser, PAYMENT_DESC, MESSAGE_INVALID_FORMAT);

        // no field specified
        assertParseFailure(parser, "1 1", MESSAGE_INVALID_FORMAT);

        // no index and no field specified
        assertParseFailure(parser, "", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_amountFieldSpecified_success() {
        Index targetIndex = INDEX_SECOND_PERSON;
        Index lessonTargetIndex = INDEX_FIRST_LESSON;
        String userInput = targetIndex.getOneBased() + " " + lessonTargetIndex.getOneBased() + PAYMENT_DESC;

        Money payment = new Money(VALID_PAYMENT);

        PaidCommand expectedCommand = new PaidCommand(targetIndex, lessonTargetIndex, payment);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_invalidAmount_failure() {
        assertParseFailure(parser, "1 1" + INVALID_PAYMENT_DESC, Money.MESSAGE_FORMAT_CONSTRAINTS);
    }

    @Test
    public void parse_multipleRepeatedAmount_acceptsLast() {
        Index targetIndex = INDEX_FIRST_PERSON;
        Index lessonTargetIndex = INDEX_THIRD_LESSON;
        String testPayment = " " + PREFIX_PAID_AMOUNT + "10.00 ";

        String userInput = targetIndex.getOneBased() + " " + lessonTargetIndex.getOneBased()
                + testPayment + PAYMENT_DESC;

        PaidCommand expectedCommand = new PaidCommand(targetIndex, lessonTargetIndex, new Money(VALID_PAYMENT));
        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_multipleInvalidValues_failure() {
        // missing arguments have priority over invalid argument format
        assertParseFailure(parser, VALID_INDICES, MESSAGE_INVALID_FORMAT);

        // invalid fields have priority over invalid index
        assertParseFailure(parser, INVALID_INDICES + INVALID_PAYMENT_DESC, Money.MESSAGE_FORMAT_CONSTRAINTS);
    }
}
