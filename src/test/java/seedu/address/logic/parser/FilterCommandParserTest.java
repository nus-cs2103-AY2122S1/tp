package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.CATEGORY_DESC_ATT;
import static seedu.address.logic.commands.CommandTestUtil.CATEGORY_DESC_FNB;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_CATEGORY_DESC;
import static seedu.address.logic.commands.CommandTestUtil.PREAMBLE_WHITESPACE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_CATEGORY_CODE;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.FilterCommand;
import seedu.address.model.person.CategoryCode;

class FilterCommandParserTest {

    private FilterCommandParser parser = new FilterCommandParser();

    @Test
    public void parse_invalidFormat_throwsParseException() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, FilterCommand.MESSAGE_USAGE);

        // empty args
        assertParseFailure(parser, "  ", expectedMessage);

        // missing prefix
        assertParseFailure(parser, "att", expectedMessage);

        // invalid format
        assertParseFailure(parser, "abc", expectedMessage);
    }


    @Test
    public void parse_invalidCategoryCode_failure() {
        String expectedMessage = CategoryCode.MESSAGE_CONSTRAINTS;

        // empty category code
        assertParseFailure(parser, " " + PREFIX_CATEGORY_CODE, expectedMessage);

        // invalid category code
        assertParseFailure(parser, INVALID_CATEGORY_DESC, expectedMessage);
    }

    @Test
    public void parse_oneCategoryCode_returnsFilterCommand() {
        // no leading and trailing whitespaces
        FilterCommand expectedFilterCommand =
                new FilterCommand(Collections.singleton(new CategoryCode("att")));
        assertParseSuccess(parser,CATEGORY_DESC_ATT, expectedFilterCommand);

        // multiple whitespaces between keywords
        assertParseSuccess(parser,PREAMBLE_WHITESPACE + CATEGORY_DESC_ATT, expectedFilterCommand);
    }

    @Test
    public void parse_multipleCategoryCode_returnsFilterCommand() {
        // no leading and trailing whitespaces
        FilterCommand expectedFilterCommand =
                new FilterCommand(new HashSet<>(Arrays.asList(new CategoryCode("att"), new CategoryCode("fnb"))));
        assertParseSuccess(parser,CATEGORY_DESC_FNB + CATEGORY_DESC_ATT, expectedFilterCommand);

        // multiple whitespaces between keywords
        assertParseSuccess(parser,PREAMBLE_WHITESPACE + CATEGORY_DESC_ATT + "      " + CATEGORY_DESC_FNB, expectedFilterCommand);
    }
}
