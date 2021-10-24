package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;

import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.ViewTaskListCommand;

public class ViewTaskListCommandParserTest {

    private final ViewTaskListCommandParser viewTaskListCommandParser = new ViewTaskListCommandParser();

    @Test
    public void parse_validArgs_returnsDeleteCommand() {
        assertParseSuccess(viewTaskListCommandParser, "1", new ViewTaskListCommand(INDEX_FIRST_PERSON));
        assertParseSuccess(viewTaskListCommandParser, "1 -f work",
                new ViewTaskListCommand(INDEX_FIRST_PERSON, List.of("work")));
    }

    @Test
    public void parse_invalidArgs_throwsParseException() {
        assertParseFailure(viewTaskListCommandParser, "a", String.format(
                MESSAGE_INVALID_COMMAND_FORMAT, ViewTaskListCommand.MESSAGE_USAGE));
    }
}
