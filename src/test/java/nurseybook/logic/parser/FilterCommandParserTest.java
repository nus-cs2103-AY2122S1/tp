package nurseybook.logic.parser;

import static nurseybook.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static nurseybook.logic.commands.CommandTestUtil.INVALID_TAG_DESC;
import static nurseybook.logic.commands.CommandTestUtil.NAME_DESC_AMY;
import static nurseybook.logic.commands.CommandTestUtil.SET_TWO_TAGS;
import static nurseybook.logic.commands.CommandTestUtil.TAG_DESC_DIABETES;
import static nurseybook.logic.commands.CommandTestUtil.TAG_DESC_FRIEND;
import static nurseybook.logic.commands.CommandTestUtil.TAG_DESC_HUSBAND;
import static nurseybook.logic.commands.CommandTestUtil.TAG_EMPTY;
import static nurseybook.logic.commands.FilterCommand.MESSAGE_USAGE;
import static nurseybook.logic.parser.CommandParserTestUtil.assertParseFailure;
import static nurseybook.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static nurseybook.model.tag.Tag.MESSAGE_CONSTRAINTS;

import org.junit.jupiter.api.Test;

import nurseybook.logic.commands.FilterCommand;
import nurseybook.model.tag.ElderlyHasTagPredicate;

public class FilterCommandParserTest {

    private FilterCommandParser parser = new FilterCommandParser();

    @Test
    public void parse_validTags_returnsFilterCommand() {
        // no leading and trailing whitespaces
        FilterCommand expectedFilterCommand = new FilterCommand(new ElderlyHasTagPredicate(SET_TWO_TAGS));
        assertParseSuccess(parser, TAG_DESC_DIABETES + TAG_DESC_FRIEND, expectedFilterCommand);

        // multiple whitespaces between keywords
        assertParseSuccess(parser, " \n " + TAG_DESC_DIABETES + " \n \t " + TAG_DESC_FRIEND + "\t",
                expectedFilterCommand);
    }

    @Test
    public void parse_invalidTag_failure() {
        assertParseFailure(parser, INVALID_TAG_DESC, MESSAGE_CONSTRAINTS); // invalid tag
        assertParseFailure(parser, TAG_EMPTY, MESSAGE_CONSTRAINTS); // invalid empty tag

        // parsing it together with a valid tag results in error
        assertParseFailure(parser, TAG_DESC_FRIEND + TAG_DESC_HUSBAND + TAG_EMPTY, MESSAGE_CONSTRAINTS);
        assertParseFailure(parser, TAG_DESC_FRIEND + TAG_EMPTY + TAG_DESC_HUSBAND, MESSAGE_CONSTRAINTS);
        assertParseFailure(parser, TAG_EMPTY + TAG_DESC_FRIEND + TAG_DESC_HUSBAND, MESSAGE_CONSTRAINTS);
    }

    @Test
    public void parse_invalidPreamble_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, MESSAGE_USAGE);

        // no Tags
        assertParseFailure(parser, "     ", expectedMessage);

        // invalid prefix being parsed as preamble
        assertParseFailure(parser, "i/ string", expectedMessage);
    }

    @Test
    public void parse_unexpectedFieldPresent_failure() {
        assertParseFailure(parser, TAG_DESC_FRIEND + NAME_DESC_AMY,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, FilterCommand.MESSAGE_USAGE));
    }
}
