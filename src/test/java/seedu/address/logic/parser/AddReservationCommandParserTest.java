package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.ID_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_RESERVATION_DATE_TIME_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_RESERVATION_REMARK_DESC;
import static seedu.address.logic.commands.CommandTestUtil.PREAMBLE_NON_EMPTY;
import static seedu.address.logic.commands.CommandTestUtil.PREAMBLE_WHITESPACE;
import static seedu.address.logic.commands.CommandTestUtil.RESERVATION_DATE_TIME_DESC_200;
import static seedu.address.logic.commands.CommandTestUtil.RESERVATION_DATE_TIME_DESC_300;
import static seedu.address.logic.commands.CommandTestUtil.RESERVATION_DESC;
import static seedu.address.logic.commands.CommandTestUtil.RESERVATION_REMARK_DESC_200;
import static seedu.address.logic.commands.CommandTestUtil.RESERVATION_REMARK_DESC_300;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ID_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_RESERVATION_DATE_TIME_200;
import static seedu.address.logic.commands.CommandTestUtil.VALID_RESERVATION_ID_200_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_RESERVATION_REMARK_200;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.commons.status.ExecutionStatus;
import seedu.address.logic.commands.AddReservationCommand;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.member.Id;
import seedu.address.model.reservation.DateTime;
import seedu.address.model.reservation.Remark;
import seedu.address.model.reservation.Reservation;
import seedu.address.testutil.ReservationBuilder;

/**
 * Contains integration tests (interaction with the Model) and unit tests for
 * {@code AddReservationCommandParser}.
 */
class AddReservationCommandParserTest {

    private Model model = new ModelManager();
    private AddReservationCommandParser parser = new AddReservationCommandParser(model, ExecutionStatus.TEST);

    @Test
    public void parse_allFieldsPresent_success() {
        Reservation expectedReservation = new ReservationBuilder().withId(VALID_RESERVATION_ID_200_AMY)
                .withDateTime(VALID_RESERVATION_DATE_TIME_200)
                .withRemark(VALID_RESERVATION_REMARK_200).build();
        Id expectedId = new Id(VALID_ID_AMY);

        // whitespace only preamble
        assertParseSuccess(parser, PREAMBLE_WHITESPACE + RESERVATION_DESC + RESERVATION_DATE_TIME_DESC_200
                + RESERVATION_REMARK_DESC_200 + ID_DESC_AMY,
                new AddReservationCommand(expectedReservation, expectedId));

        // multiple dateTimes - last dateTime accepted
        assertParseSuccess(parser, RESERVATION_DESC + RESERVATION_DATE_TIME_DESC_300
                + RESERVATION_DATE_TIME_DESC_200 + RESERVATION_REMARK_DESC_200 + ID_DESC_AMY,
                new AddReservationCommand(expectedReservation, expectedId));

        // multiple remarks - last remark accepted
        assertParseSuccess(parser, RESERVATION_DESC + RESERVATION_DATE_TIME_DESC_200
                + RESERVATION_REMARK_DESC_300 + RESERVATION_REMARK_DESC_200 + ID_DESC_AMY,
                new AddReservationCommand(expectedReservation, expectedId));
    }

    @Test
    public void parse_compulsoryFieldMissing_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddReservationCommand.MESSAGE_USAGE);

        // missing id prefix
        assertParseFailure(parser, RESERVATION_DESC + RESERVATION_DATE_TIME_DESC_200
                + RESERVATION_REMARK_DESC_200, expectedMessage);
    }

    @Test
    public void parse_invalidValue_failure() {
        // invalid dateTime
        assertParseFailure(parser, RESERVATION_DESC + INVALID_RESERVATION_DATE_TIME_DESC
                + RESERVATION_REMARK_DESC_200 + ID_DESC_AMY,
                DateTime.MESSAGE_CONSTRAINTS);

        // invalid remark
        assertParseFailure(parser, RESERVATION_DESC + RESERVATION_DATE_TIME_DESC_200
                + INVALID_RESERVATION_REMARK_DESC + ID_DESC_AMY,
                Remark.MESSAGE_CONSTRAINTS);

        // non-empty preamble
        assertParseFailure(parser, PREAMBLE_NON_EMPTY + RESERVATION_DATE_TIME_DESC_200
                + RESERVATION_REMARK_DESC_200 + ID_DESC_AMY,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddReservationCommand.MESSAGE_USAGE));
    }
}
