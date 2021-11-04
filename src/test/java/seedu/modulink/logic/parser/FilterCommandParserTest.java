package seedu.modulink.logic.parser;

import static seedu.modulink.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.modulink.commons.core.Messages.MESSAGE_UNKNOWN_PREFIX_FORMAT;
import static seedu.modulink.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.modulink.logic.parser.CommandParserTestUtil.assertParseSuccess;

import java.util.Set;

import org.junit.jupiter.api.Test;

import seedu.modulink.logic.commands.FilterCommand;
import seedu.modulink.logic.parser.exceptions.ParseException;
import seedu.modulink.model.person.ModuleContainsKeywordsPredicate;
import seedu.modulink.model.tag.Mod;
import seedu.modulink.model.tag.Status;

public class FilterCommandParserTest {

    private FilterCommandParser parser = new FilterCommandParser();

    @Test
    public void parse_emptyArg_throwsParseException() {
        assertParseFailure(parser, "     ",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, FilterCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_validArgs_returnsFilterCommand() throws ParseException {
        FilterCommand expectedFilterCommand =
                new FilterCommand(new ModuleContainsKeywordsPredicate(Set.of(new Mod("CS2100 need group"))));
        assertParseSuccess(parser, " mod/CS2100 need group", expectedFilterCommand);

        // multiple whitespaces between keywords
        assertParseSuccess(parser, " \n mod/CS2100 \n \t need group  \t", expectedFilterCommand);
    }

    @Test
    public void parse_multipleArgs_failure() {
        // multiple modules - not accepted
        assertParseFailure(parser, " mod/CS2100 need group mod/CS1231S",
                FilterCommandParser.MESSAGE_MORE_THAN_ONE_PARAMETER_FORMAT);
    }

    @Test
    public void parse_nonEmptyPreamble_failure() {
        // random prefixes - not accepted
        assertParseFailure(parser, " status/CS2100 mod/CS2100 need group mod/CS1231S",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, FilterCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_unknownPrefixes_failure() {
        // random prefixes - not accepted
        assertParseFailure(parser, " mod/CS2100 status/CS2100 need group",
                String.format(MESSAGE_UNKNOWN_PREFIX_FORMAT, FilterCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_invalidStatusDescription_failure() {
        // random group status - not accepted
        assertParseFailure(parser, " mod/CS2100 need something",
                Status.MESSAGE_CONSTRAINTS);
    }

}
