package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TITLE;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.logic.position.CommandTestUtil.INVALID_POSITION_STATUS;
import static seedu.address.logic.position.CommandTestUtil.INVALID_POSITION_TITLE;
import static seedu.address.logic.position.CommandTestUtil.POSITION_ADMIN_ASSISTANT;
import static seedu.address.logic.position.CommandTestUtil.POSITION_BOOKKEEPER;
import static seedu.address.logic.position.CommandTestUtil.POSITION_HR_MANAGER;
import static seedu.address.logic.position.CommandTestUtil.STATUS_OPEN;
import static seedu.address.logic.position.CommandTestUtil.VALID_STATUS_OPEN;
import static seedu.address.logic.position.CommandTestUtil.VALID_TITLE_ADMIN_ASSISTANT;
import static seedu.address.logic.position.CommandTestUtil.VALID_TITLE_BOOKKEEPER;
import static seedu.address.logic.position.CommandTestUtil.VALID_TITLE_HR_MANAGER;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_POSITION;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_POSITION;
import static seedu.address.testutil.TypicalIndexes.INDEX_THIRD_POSITION;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.position.EditPositionCommand;
import seedu.address.model.position.Position.PositionStatus;
import seedu.address.model.position.Title;
import seedu.address.testutil.EditPositionDescriptorBuilder;

public class EditPositionCommandParserTest {

    private static final String MESSAGE_INVALID_FORMAT =
            String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    ParserUtil.MESSAGE_INVALID_INDEX) + EditPositionCommand.MESSAGE_USAGE;

    private static final String MESSAGE_INVALID_FORMAT_EMPTY_INDEX =
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditPositionCommand.MESSAGE_USAGE);

    private EditPositionCommandParser parser = new EditPositionCommandParser();

    @Test
    public void parse_missingParts_failure() {
        // no index specified, followed by field name=value
        assertParseFailure(parser,
                " " + PREFIX_TITLE + VALID_TITLE_ADMIN_ASSISTANT, MESSAGE_INVALID_FORMAT_EMPTY_INDEX);

        // no index specified, immediately followed by field value
        assertParseFailure(parser, VALID_TITLE_ADMIN_ASSISTANT, MESSAGE_INVALID_FORMAT);

        // no field specified
        assertParseFailure(parser, "1", EditPositionCommand.MESSAGE_NOT_EDITED);

        // no index and no field specified
        assertParseFailure(parser, "", MESSAGE_INVALID_FORMAT_EMPTY_INDEX);
    }

    @Test
    public void parse_invalidPreamble_failure() {
        // negative index
        assertParseFailure(parser, "-5" + POSITION_ADMIN_ASSISTANT, MESSAGE_INVALID_FORMAT);

        // zero index
        assertParseFailure(parser, "0" + POSITION_ADMIN_ASSISTANT, MESSAGE_INVALID_FORMAT);

        // invalid arguments being parsed as preamble
        assertParseFailure(parser, "1 some random string", MESSAGE_INVALID_FORMAT);

        // invalid prefix being parsed as preamble
        assertParseFailure(parser, "1 i/ string", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidValue_failure() {
        assertParseFailure(parser, "1" + INVALID_POSITION_TITLE, Title.MESSAGE_CONSTRAINTS); // invalid title

        // invalid position title followed by valid status
        assertParseFailure(parser, "1" + INVALID_POSITION_TITLE + STATUS_OPEN, Title.MESSAGE_CONSTRAINTS);

        // valid position title followed by invalid status.
        assertParseFailure(parser,
                "1" + POSITION_BOOKKEEPER + INVALID_POSITION_STATUS, PositionStatus.MESSAGE_CONSTRAINTS);

        // multiple invalid values, but only the first invalid value is captured
        assertParseFailure(parser, "1" + INVALID_POSITION_TITLE + INVALID_POSITION_STATUS,
                Title.MESSAGE_CONSTRAINTS);
    }

    @Test
    public void parse_allFieldsSpecified_success() {
        Index targetIndex = INDEX_SECOND_POSITION;
        String userInput = targetIndex.getOneBased() + POSITION_BOOKKEEPER + STATUS_OPEN;

        EditPositionCommand.EditPositionDescriptor descriptor =
                new EditPositionDescriptorBuilder().withTitle(VALID_TITLE_BOOKKEEPER)
                        .withPositionStatus(VALID_STATUS_OPEN).build();
        EditPositionCommand expectedCommand = new EditPositionCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_someFieldsSpecified_success() {
        Index targetIndex = INDEX_FIRST_POSITION;
        String userInput = targetIndex.getOneBased() + POSITION_BOOKKEEPER;

        EditPositionCommand.EditPositionDescriptor descriptor =
                new EditPositionDescriptorBuilder().withTitle(VALID_TITLE_BOOKKEEPER).build();
        EditPositionCommand expectedCommand = new EditPositionCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_oneFieldSpecified_success() {
        // title
        Index targetIndex = INDEX_THIRD_POSITION;
        String userInput = targetIndex.getOneBased() + POSITION_ADMIN_ASSISTANT;
        EditPositionCommand.EditPositionDescriptor descriptor =
                new EditPositionDescriptorBuilder().withTitle(VALID_TITLE_ADMIN_ASSISTANT).build();
        EditPositionCommand expectedCommand = new EditPositionCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // status
        userInput = targetIndex.getOneBased() + STATUS_OPEN;
        descriptor = new EditPositionDescriptorBuilder().withPositionStatus(VALID_STATUS_OPEN).build();
        expectedCommand = new EditPositionCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_multipleRepeatedFields_acceptsLast() {
        Index targetIndex = INDEX_FIRST_POSITION;
        String userInput = targetIndex.getOneBased() + POSITION_ADMIN_ASSISTANT + STATUS_OPEN
                + POSITION_ADMIN_ASSISTANT + STATUS_OPEN;

        EditPositionCommand.EditPositionDescriptor descriptor =
                new EditPositionDescriptorBuilder().withTitle(VALID_TITLE_ADMIN_ASSISTANT)
                        .withPositionStatus(VALID_STATUS_OPEN).build();
        EditPositionCommand expectedCommand = new EditPositionCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_invalidValueFollowedByValidValue_success() {
        // no other valid values specified
        Index targetIndex = INDEX_FIRST_POSITION;
        String userInput = targetIndex.getOneBased() + INVALID_POSITION_TITLE + POSITION_HR_MANAGER;
        EditPositionCommand.EditPositionDescriptor descriptor =
                new EditPositionDescriptorBuilder().withTitle(VALID_TITLE_HR_MANAGER).build();
        EditPositionCommand expectedCommand = new EditPositionCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // other valid values specified
        userInput = targetIndex.getOneBased() + POSITION_BOOKKEEPER + INVALID_POSITION_STATUS + STATUS_OPEN;
        descriptor = new EditPositionDescriptorBuilder()
                .withTitle(VALID_TITLE_BOOKKEEPER).withPositionStatus(VALID_STATUS_OPEN).build();
        expectedCommand = new EditPositionCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);
    }
}
