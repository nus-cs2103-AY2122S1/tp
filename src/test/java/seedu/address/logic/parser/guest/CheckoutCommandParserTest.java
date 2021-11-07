package seedu.address.logic.parser.guest;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.guest.TypicalPassportNumbers.PASSPORT_NUMBER_FIRST_PERSON;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.guest.CheckOutCommand;
import seedu.address.model.guest.PassportNumber;

public class CheckoutCommandParserTest {

    private CheckOutCommandParser parser = new CheckOutCommandParser();

    @Test
    public void parse_validPassportNumberArgs_returnsCheckoutCommand() {
        assertParseSuccess(parser, "checkout pn/E0123122G", new CheckOutCommand(PASSPORT_NUMBER_FIRST_PERSON));
    }

    @Test
    public void parse_invalidPassportNumberArgs_throwsParseException() {
        assertParseFailure(parser, "checkout pn/",
                PassportNumber.MESSAGE_CONSTRAINTS);
    }

    @Test
    public void parse_invalidCheckoutCommand_throwsParseException() {
        assertParseFailure(parser, "checkout",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, CheckOutCommand.MESSAGE_USAGE));
    }

}
