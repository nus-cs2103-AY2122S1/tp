package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.CATEGORY_DESC_ATT;
import static seedu.address.logic.commands.CommandTestUtil.CATEGORY_DESC_FNB;
import static seedu.address.logic.commands.CommandTestUtil.CATEGORY_EMPTY;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_CATEGORY_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_RATING_DESC;
import static seedu.address.logic.commands.CommandTestUtil.PREAMBLE_WHITESPACE;
import static seedu.address.logic.commands.CommandTestUtil.RATING_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.RATING_DESC_BOB;
import static seedu.address.logic.parser.CliSyntax.PREFIX_CATEGORY_CODE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_RATING;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.FilterCommand;
import seedu.address.model.contact.CategoryCode;
import seedu.address.model.contact.Rating;

class FilterCommandParserTest {

    private FilterCommandParser parser = new FilterCommandParser();

    @Test
    public void parse_invalidFormat_throwsParseException() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, FilterCommand.MESSAGE_USAGE);

        // empty args
        assertParseFailure(parser, "  ", expectedMessage);

        // missing prefix
        assertParseFailure(parser, "att", expectedMessage);

        // missing prefix
        assertParseFailure(parser, "3", expectedMessage);

        // missing prefix
        assertParseFailure(parser, "tag", expectedMessage);

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
                new FilterCommand(Collections.singleton(new CategoryCode("att")), new Rating(),
                        Collections.emptySet());
        assertParseSuccess(parser, CATEGORY_DESC_ATT, expectedFilterCommand);

        // multiple whitespaces between keywords
        assertParseSuccess(parser, PREAMBLE_WHITESPACE + CATEGORY_DESC_ATT, expectedFilterCommand);
    }

    @Test
    public void parse_multipleCategoryCode_returnsFilterCommand() {
        // no leading and trailing whitespaces
        FilterCommand expectedFilterCommand =
                new FilterCommand(new HashSet<>(Arrays.asList(new CategoryCode("att"), new CategoryCode("fnb"))),
                    new Rating(), Collections.emptySet());
        assertParseSuccess(parser, CATEGORY_DESC_FNB + CATEGORY_DESC_ATT, expectedFilterCommand);

        // multiple whitespaces between keywords
        assertParseSuccess(parser, PREAMBLE_WHITESPACE + CATEGORY_DESC_ATT + "      " + CATEGORY_DESC_FNB,
                expectedFilterCommand);
    }

    @Test
    public void parse_noCategoryCode_returnsFilterCommand() {
        // no leading and trailing whitespaces
        FilterCommand expectedFilterCommand =
            new FilterCommand(Collections.emptySet(), new Rating("3"), Collections.emptySet());
        assertParseSuccess(parser, CATEGORY_EMPTY + RATING_DESC_BOB, expectedFilterCommand);

        // multiple whitespaces between keywords
        assertParseSuccess(parser, PREAMBLE_WHITESPACE + CATEGORY_EMPTY + "        " + RATING_DESC_BOB,
            expectedFilterCommand);
    }

    @Test
    public void parse_emptyRating_failure() {
        String expectedMessage = Rating.MESSAGE_CONSTRAINTS;

        // empty category code
        assertParseFailure(parser, " " + PREFIX_RATING, expectedMessage);

        // invalid category code
        assertParseFailure(parser, INVALID_RATING_DESC, expectedMessage);
    }

    @Test
    public void parse_validRating_returnsFilterCommand() {
        // no leading and trailing whitespaces
        FilterCommand expectedFilterCommand =
            new FilterCommand(Collections.emptySet(), new Rating("5"),
                Collections.emptySet());
        assertParseSuccess(parser, RATING_DESC_AMY, expectedFilterCommand);

        // multiple whitespaces between keywords
        assertParseSuccess(parser, PREAMBLE_WHITESPACE + RATING_DESC_AMY, expectedFilterCommand);
    }

    // TODO [LETHICIA]: add test cases for tags filtering

    @Test
    public void parse_invalidTag_failure() {

    }

    @Test
    public void parse_oneTag_returnsFilterCommand() {

    }

    @Test
    public void parse_multipleTag_returnsFilterCommand() {

    }

    @Test
    public void parse_noTag_returnsFilterCommand() {

    }

}
