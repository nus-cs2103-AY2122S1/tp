package seedu.address.logic.parser;


import org.junit.jupiter.api.Test;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.PaidCommand;
import seedu.address.model.lesson.Money;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.PAYMENT_DESC;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PAYMENT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_LESSON;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_PERSON;

class PaidCommandParserTest {
    private static final String MESSAGE_INVALID_FORMAT =
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, PaidCommand.MESSAGE_USAGE);
    private final PaidCommandParser parser = new PaidCommandParser();

    @Test
    public void parse_invalidPreamble_failure() {
        // negative student index
        assertParseFailure(parser, "-5 1" + PAYMENT_DESC, MESSAGE_INVALID_FORMAT);

        // zero student index
        assertParseFailure(parser, "0 1" + PAYMENT_DESC, MESSAGE_INVALID_FORMAT);

        // negative lesson index
        assertParseFailure(parser, "1 -7" + PAYMENT_DESC, MESSAGE_INVALID_FORMAT);

        // zero lesson index
        assertParseFailure(parser, "1 0" + PAYMENT_DESC, MESSAGE_INVALID_FORMAT);

        // invalid arguments being parsed as preamble
        assertParseFailure(parser, "1 some random string", MESSAGE_INVALID_FORMAT);

        // invalid prefix being parsed as preamble
        assertParseFailure(parser, "1 i/ string", MESSAGE_INVALID_FORMAT);
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
}