package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.UnselectCommand;

public class UnselectCommandParserTest {

    private UnselectCommandParser parser = new UnselectCommandParser();

    @Test
    public void parse_emptyArg_throwsParseException() {
        assertParseFailure(parser, "     ",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, UnselectCommand.MESSAGE_USAGE));
    }

    /**
     * Equality in predicates are hardly testable.
     */
    @Test
    public void parse_validArgs_returnsFindCommand() {
        List<Index> indexes = new ArrayList<>();
        indexes.add(Index.fromOneBased(1));
        indexes.add(Index.fromOneBased(2));

        // no leading and trailing whitespaces
        UnselectCommand expectedUnselectCommand = new UnselectCommand(indexes, true);
        assertParseSuccess(parser, " -i 1 2", expectedUnselectCommand);

        // multiple whitespaces between keywords
        assertParseSuccess(parser, " -i \n 1 \n \t 2  \t", expectedUnselectCommand);
    }

}
