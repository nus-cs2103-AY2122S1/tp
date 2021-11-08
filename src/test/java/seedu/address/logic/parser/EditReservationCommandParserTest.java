package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_RESERVATION_DATE_TIME_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_RESERVATION_REMARK_DESC;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_AMY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ID;
import static seedu.address.logic.parser.CliSyntax.PREFIX_RESERVATION;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.EditReservationCommand;
import seedu.address.model.reservation.DateTime;
import seedu.address.model.reservation.Remark;

public class EditReservationCommandParserTest {

    private static final String MESSAGE_INVALID_FORMAT =
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditReservationCommand.MESSAGE_USAGE);

    private EditReservationCommandParser parser = new EditReservationCommandParser();

    @Test
    public void parse_missingParts_failure() {
        // no index specified
        assertParseFailure(parser, VALID_NAME_AMY, MESSAGE_INVALID_FORMAT);

        // no field specified
        assertParseFailure(parser, " " + PREFIX_RESERVATION + " " + PREFIX_ID + " 00001000001",
                EditReservationCommand.MESSAGE_NOT_EDITED);

        // no index and no field specified
        assertParseFailure(parser, "", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidPreamble_failure() {
        // invalid arguments being parsed as preamble
        assertParseFailure(parser, "1 some random string", MESSAGE_INVALID_FORMAT);

        // invalid prefix being parsed as preamble
        assertParseFailure(parser, "1 i/ string", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidValue_failure() {
        assertParseFailure(parser, " " + PREFIX_RESERVATION + " " + PREFIX_ID + " 00001000001"
                + INVALID_RESERVATION_DATE_TIME_DESC, DateTime.MESSAGE_CONSTRAINTS); // invalid date time

        assertParseFailure(parser, " " + PREFIX_RESERVATION + " " + PREFIX_ID + " 00001000001"
                + INVALID_RESERVATION_REMARK_DESC, Remark.MESSAGE_CONSTRAINTS); // invalid date time
    }
}
