package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_TAG_DESC;
import static seedu.address.logic.commands.CommandTestUtil.TAG_DESC_FRIEND;
import static seedu.address.logic.commands.CommandTestUtil.TAG_DESC_HUSBAND;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_FRIEND;
import static seedu.address.logic.commands.ReservationCommandTestUtil.INVALID_REMARK_DESC;
import static seedu.address.logic.commands.ReservationCommandTestUtil.REMARK_DESC;
import static seedu.address.logic.commands.ReservationCommandTestUtil.VALID_REMARK;
import static seedu.address.logic.parser.CliSyntax.PREFIX_REMARK;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import java.util.Set;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.EditReservationCommand;
import seedu.address.logic.commands.EditReservationCommand.EditReservationDescriptor;
import seedu.address.model.reservation.Remark;
import seedu.address.model.tag.Tag;

class EditReservationCommandParserTest {
    private static final String TAG_EMPTY = " " + PREFIX_TAG;
    private static final String REMARK_EMPTY = " " + PREFIX_REMARK;

    private static final String MESSAGE_INVALID_FORMAT =
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditReservationCommand.MESSAGE_USAGE);

    private EditReservationCommandParser parser = new EditReservationCommandParser();

    @Test
    public void parse_missingParts_failure() {
        // no index specified
        assertParseFailure(parser, REMARK_DESC, MESSAGE_INVALID_FORMAT);

        // no field specified
        assertParseFailure(parser, "1", EditReservationCommand.MESSAGE_NOT_EDITED);

        // no index and no field specified
        assertParseFailure(parser, "", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidPreamble_failure() {
        // negative index
        assertParseFailure(parser, "-5" + REMARK_DESC, MESSAGE_INVALID_FORMAT);

        // zero index
        assertParseFailure(parser, "0" + REMARK_DESC, MESSAGE_INVALID_FORMAT);

        // invalid arguments being parsed as preamble
        assertParseFailure(parser, "1 some random string", MESSAGE_INVALID_FORMAT);

        // invalid prefix being parsed as preamble
        assertParseFailure(parser, "1 i/ string", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidValue_failure() {
        // invalid remark
        assertParseFailure(parser, "1" + INVALID_REMARK_DESC, Remark.MESSAGE_CONSTRAINTS);
        // invalid tags
        assertParseFailure(parser, "1" + INVALID_TAG_DESC, Tag.MESSAGE_CONSTRAINTS);

        // while parsing {@code PREFIX_TAG} alone will reset the tags of the {@code Reservation} being edited,
        // parsing it together with a valid tag results in error
        assertParseFailure(parser, "1" + TAG_DESC_FRIEND + TAG_DESC_HUSBAND + TAG_EMPTY, Tag.MESSAGE_CONSTRAINTS);
        assertParseFailure(parser, "1" + TAG_DESC_FRIEND + TAG_EMPTY + TAG_DESC_HUSBAND, Tag.MESSAGE_CONSTRAINTS);
        assertParseFailure(parser, "1" + TAG_EMPTY + TAG_DESC_FRIEND + TAG_DESC_HUSBAND, Tag.MESSAGE_CONSTRAINTS);

        // multiple invalid values, but only the first invalid value is captured
        assertParseFailure(parser, "1" + INVALID_REMARK_DESC + INVALID_TAG_DESC, Remark.MESSAGE_CONSTRAINTS);
    }

    @Test
    public void parse_allFieldsSpecified_success() {
        Index targetIndex = Index.fromOneBased(1);
        EditReservationDescriptor descriptor = new EditReservationDescriptor();
        descriptor.setRemark(new Remark(VALID_REMARK));
        descriptor.setTags(Set.of(new Tag(VALID_TAG_FRIEND)));

        EditReservationCommand expectedCommand = new EditReservationCommand(targetIndex, descriptor);
        assertParseSuccess(parser, targetIndex.getOneBased() + REMARK_DESC + TAG_DESC_FRIEND, expectedCommand);
    }

    @Test
    public void parse_oneFieldsSpecified_success() {
        Index targetIndex = Index.fromOneBased(1);

        // only remark
        EditReservationDescriptor onlyRemarkDescriptor = new EditReservationDescriptor();
        onlyRemarkDescriptor.setRemark(new Remark(VALID_REMARK));
        EditReservationCommand onlyRemarkExpectedCommand =
                new EditReservationCommand(targetIndex, onlyRemarkDescriptor);
        assertParseSuccess(parser, targetIndex.getOneBased() + REMARK_DESC, onlyRemarkExpectedCommand);

        // only tag
        EditReservationDescriptor onlyTagDescriptor = new EditReservationDescriptor();
        onlyTagDescriptor.setTags(Set.of(new Tag(VALID_TAG_FRIEND)));
        EditReservationCommand onlyTagExpectedCommand =
                new EditReservationCommand(targetIndex, onlyTagDescriptor);
        assertParseSuccess(parser, targetIndex.getOneBased() + TAG_DESC_FRIEND, onlyTagExpectedCommand);
    }

    @Test
    public void parse_multipleRepeatedFields_acceptsLast() {
        Index targetIndex = Index.fromOneBased(1);
        EditReservationDescriptor descriptor = new EditReservationDescriptor();
        descriptor.setRemark(new Remark(VALID_REMARK));
        descriptor.setTags(Set.of(new Tag(VALID_TAG_FRIEND)));

        EditReservationCommand expectedCommand = new EditReservationCommand(targetIndex, descriptor);
        String userInput = targetIndex.getOneBased() + " " + PREFIX_REMARK + "abc" + TAG_DESC_FRIEND + REMARK_DESC;

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_invalidValueFollowedByValidValue_success() {
        Index targetIndex = Index.fromOneBased(1);
        EditReservationDescriptor descriptor = new EditReservationDescriptor();
        descriptor.setRemark(new Remark(VALID_REMARK));
        descriptor.setTags(Set.of(new Tag(VALID_TAG_FRIEND)));

        EditReservationCommand expectedCommand = new EditReservationCommand(targetIndex, descriptor);
        String userInput = targetIndex.getOneBased() + INVALID_REMARK_DESC + TAG_DESC_FRIEND + REMARK_DESC;

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_resetRemark_success() {
        Index targetIndex = Index.fromOneBased(1);
        EditReservationDescriptor descriptor = new EditReservationDescriptor();
        descriptor.setRemark(new Remark(""));

        EditReservationCommand expectedCommand = new EditReservationCommand(targetIndex, descriptor);

        assertParseSuccess(parser, targetIndex.getOneBased() + REMARK_EMPTY, expectedCommand);
    }

    @Test
    public void parse_resetTags_success() {
        Index targetIndex = Index.fromOneBased(1);
        EditReservationDescriptor descriptor = new EditReservationDescriptor();
        descriptor.setTags(Set.of());

        EditReservationCommand expectedCommand = new EditReservationCommand(targetIndex, descriptor);

        assertParseSuccess(parser, targetIndex.getOneBased() + TAG_EMPTY, expectedCommand);
    }
}
