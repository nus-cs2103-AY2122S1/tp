package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.ALIAS_DESC_D1;
import static seedu.address.logic.commands.CommandTestUtil.ALIAS_DESC_L;
import static seedu.address.logic.commands.CommandTestUtil.COMMAND_DESC_D1;
import static seedu.address.logic.commands.CommandTestUtil.COMMAND_DESC_L;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_ALIAS_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_COMMAND_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_EMPTY_ALIAS_DESC;
import static seedu.address.logic.commands.CommandTestUtil.PREAMBLE_NON_EMPTY;
import static seedu.address.logic.commands.CommandTestUtil.PREAMBLE_WHITESPACE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ALIAS_D1;
import static seedu.address.logic.commands.CommandTestUtil.VALID_COMMAND_D1;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.AliasCommand;

public class AliasCommandParserTest {
    private AliasCommandParser parser = new AliasCommandParser();

    @Test
    public void parse_allFieldsPresent_success() {
        // whitespace only preamble
        assertParseSuccess(parser, PREAMBLE_WHITESPACE + ALIAS_DESC_D1 + COMMAND_DESC_D1,
                new AliasCommand(VALID_ALIAS_D1, VALID_COMMAND_D1));

        // multiple alias - last alias accepted
        assertParseSuccess(parser, ALIAS_DESC_L + ALIAS_DESC_D1 + COMMAND_DESC_D1,
                new AliasCommand(VALID_ALIAS_D1, VALID_COMMAND_D1));

        // multiple commands - last command accepted
        assertParseSuccess(parser, COMMAND_DESC_L + ALIAS_DESC_D1 + COMMAND_DESC_D1,
                new AliasCommand(VALID_ALIAS_D1, VALID_COMMAND_D1));
    }

    @Test
    public void parse_compulsoryFieldMissing_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, AliasCommand.MESSAGE_USAGE);

        // missing alias prefix
        assertParseFailure(parser, VALID_ALIAS_D1 + COMMAND_DESC_D1,
                expectedMessage);

        // missing command prefix
        assertParseFailure(parser, ALIAS_DESC_D1 + VALID_COMMAND_D1,
                expectedMessage);

        // all prefixes missing
        assertParseFailure(parser, VALID_ALIAS_D1 + VALID_COMMAND_D1, expectedMessage);
    }

    @Test
    public void parse_invalidValue_failure() {
        // invalid alias
        assertParseFailure(parser, INVALID_ALIAS_DESC + COMMAND_DESC_D1,
                AliasCommandParser.MESSAGE_ALIAS_CONSTRAINTS);

        // empty alias
        assertParseFailure(parser, INVALID_EMPTY_ALIAS_DESC + COMMAND_DESC_D1,
                AliasCommandParser.MESSAGE_ALIAS_CONSTRAINTS);

        // invalid command
        assertParseFailure(parser, ALIAS_DESC_D1 + INVALID_COMMAND_DESC,
                AliasCommandParser.MESSAGE_COMMAND_CONSTRAINTS);

        // non-empty preamble
        assertParseFailure(parser, PREAMBLE_NON_EMPTY + ALIAS_DESC_D1 + COMMAND_DESC_D1,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, AliasCommand.MESSAGE_USAGE));
    }
}
