package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.ViewTaskListCommand;

public class ViewTaskListCommandParserTest {

    private final ViewTaskListCommandParser parser = new ViewTaskListCommandParser();

    @Test
    public void parse_validArgs_returnsDeleteCommand() {
        assertParseSuccess(parser, "1", new ViewTaskListCommand(INDEX_FIRST_PERSON));
        assertParseSuccess(parser, "1 -f work",
                new ViewTaskListCommand(INDEX_FIRST_PERSON, List.of("work")));
    }

    @Test
    public void parse_invalidArgs_throwsParseException() {
        assertParseFailure(parser, "a", String.format(
                MESSAGE_INVALID_COMMAND_FORMAT, ViewTaskListCommand.MESSAGE_USAGE)
        );
    }

    @Test
    public void parse_validArgsWithoutFlag_success() {
        assertParseSuccess(parser, "-A", new ViewTaskListCommand());
    }

    @Test
    public void parse_validArgsWithFlag_success() {
        assertParseSuccess(parser, "-A -f work sleep", new ViewTaskListCommand(Arrays.asList("work", "sleep")));
    }
}
