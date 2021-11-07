package seedu.address.logic.parser.guest;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.guest.TypicalPassportNumbers.PASSPORT_NUMBER_FIRST_PERSON;
import static seedu.address.testutil.guest.TypicalPassportNumbers.PASSPORT_NUMBER_SECOND_PERSON;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.guest.DeleteGuestCommand;

/**
 * As we are only doing white-box testing, our test cases do not cover path variations
 * outside of the DeleteCommand code. For example, inputs "1" and "1 abc" take the
 * same path through the DeleteCommand, and therefore we test only one of them.
 * The path variation for those two cases occur inside the ParserUtil, and
 * therefore should be covered by the ParserUtilTest.
 */
public class DeleteGuestCommandParserTest {
    // @@author NicolasCwy
    private DeleteGuestCommandParser parser = new DeleteGuestCommandParser();

    @Test
    public void parse_validPassportNumberArgs_returnsDeleteCommand() {
        assertParseSuccess(parser, "deleteguest pn/" + PASSPORT_NUMBER_FIRST_PERSON,
                new DeleteGuestCommand(PASSPORT_NUMBER_FIRST_PERSON));

        assertParseSuccess(parser, "deleteguest pn/" + PASSPORT_NUMBER_SECOND_PERSON,
                new DeleteGuestCommand(PASSPORT_NUMBER_SECOND_PERSON));
    }

    @Test
    public void parse_invalidDeleteCommand_throwsParseException() {
        assertParseFailure(parser, "",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteGuestCommand.MESSAGE_USAGE));
    }
}
