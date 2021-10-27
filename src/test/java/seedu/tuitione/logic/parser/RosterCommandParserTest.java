package seedu.tuitione.logic.parser;

import static seedu.tuitione.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.tuitione.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.tuitione.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.tuitione.testutil.TypicalIndexes.INDEX_SECOND_LESSON;

import org.junit.jupiter.api.Test;

import seedu.tuitione.logic.commands.FindCommand;
import seedu.tuitione.logic.commands.RosterCommand;

public class RosterCommandParserTest {

    private RosterCommandParser parser = new RosterCommandParser();

    @Test
    public void parse_emptyArg_throwsParseException() {
        assertParseFailure(parser, "     ", String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
    }

}
