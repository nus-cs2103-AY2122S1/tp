package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ID;
import static seedu.address.logic.parser.CliSyntax.PREFIX_RESERVATION;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.DeleteReservationCommand;

/**
 * As we are only doing white-box testing, our test cases do not cover path variations
 * outside of the DeleteReservationCommand code. For example, inputs "1" and "1 abc" take the
 * same path through the DeleteReservationCommand, and therefore we test only one of them.
 * The path variation for those two cases occur inside the ParserUtil, and
 * therefore should be covered by the ParserUtilTest.
 */
public class DeleteReservationCommandParserTest {

    private DeleteReservationCommandParser parser = new DeleteReservationCommandParser();

    @Test
    public void parse_validArgs_returnsDeleteReservationCommand() {
        seedu.address.model.member.Id memberId = new seedu.address.model.member.Id("00001");
        seedu.address.model.reservation.Id reservationId = new seedu.address.model.reservation.Id("000001");
        assertParseSuccess(parser, " " + PREFIX_RESERVATION + " " + PREFIX_ID + " "
                + memberId.value + reservationId.value, new DeleteReservationCommand(memberId, reservationId));
    }

    @Test
    public void parse_invalidArgs_throwsParseException() {
        assertParseFailure(parser, "a",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteReservationCommand.MESSAGE_USAGE));
    }
}
