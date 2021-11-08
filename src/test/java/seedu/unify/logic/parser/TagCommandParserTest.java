package seedu.unify.logic.parser;

import static seedu.unify.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.unify.logic.commands.CommandTestUtil.TAG_DESC_CCA;
import static seedu.unify.logic.commands.CommandTestUtil.VALID_TAG_CCA;
import static seedu.unify.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.unify.logic.parser.CommandParserTestUtil.assertParseFailure;

import org.junit.jupiter.api.Test;

import seedu.unify.logic.commands.TagCommand;

public class TagCommandParserTest {

    private static final String TAG_EMPTY = " " + PREFIX_TAG;

    private static final String MESSAGE_INVALID_FORMAT =
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, TagCommand.MESSAGE_USAGE);


    private TagCommandParser parser = new TagCommandParser();

    @Test
    public void parse_missingParts_failure() {
        // no index specified
        assertParseFailure(parser, VALID_TAG_CCA, MESSAGE_INVALID_FORMAT);

        // no tag field specified
        assertParseFailure(parser, "1", TagCommand.MESSAGE_NOT_TAGGED);

        // no index and no tag field specified
        assertParseFailure(parser, "", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidPreamble_failure() {
        // negative index
        assertParseFailure(parser, "-5" + TAG_DESC_CCA, MESSAGE_INVALID_FORMAT);

        // zero index
        assertParseFailure(parser, "0" + TAG_DESC_CCA, MESSAGE_INVALID_FORMAT);

        // invalid arguments being parsed as preamble
        assertParseFailure(parser, "1 some random tag", MESSAGE_INVALID_FORMAT);

        // invalid prefix being parse as preamble
        assertParseFailure(parser, "1 tag/ something", MESSAGE_INVALID_FORMAT);
    }
}
