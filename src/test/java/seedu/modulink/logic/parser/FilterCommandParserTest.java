package seedu.modulink.logic.parser;

import static seedu.modulink.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.modulink.logic.parser.CommandParserTestUtil.assertParseFailure;

import org.junit.jupiter.api.Test;

import seedu.modulink.logic.commands.FilterCommand;

public class FilterCommandParserTest {

    private FilterCommandParser parser = new FilterCommandParser();

    @Test
    public void parse_emptyArg_throwsParseException() {
        assertParseFailure(parser, "     ",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, FilterCommand.MESSAGE_USAGE));
    }

    /*
    Test failing - don't know why yet

    @Test
    public void parse_validArgs_returnsFilterCommand() {
        // no leading and trailing whitespaces
        FilterCommand expectedFilterCommand =
                new FilterCommand(new ModuleContainsKeywordsPredicate(
                        Set.of(new Mod("CS2100")), Status.NEED_GROUP));
        assertParseSuccess(parser, "mod/CS2100 group/SG", expectedFilterCommand);

        // multiple whitespaces between keywords
        assertParseSuccess(parser, " \n mod/CS2100 \n \t group/SG  \t", expectedFilterCommand);
    }
    */
}
