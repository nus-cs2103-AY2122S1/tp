package safeforhall.logic.parser;

import static safeforhall.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static safeforhall.logic.parser.CommandParserTestUtil.assertParseFailure;

import org.junit.jupiter.api.Test;

import safeforhall.logic.commands.TraceCommand;

public class TraceCommandParserTest {

    private TraceCommandParser parser = new TraceCommandParser();

    @Test
    public void parse_emptyArg_throwsParseException() {
        assertParseFailure(parser, " ",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, TraceCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_validArgs_returnsImportCommand() {
        TraceCommand expectedTraceCommand = new TraceCommand("A106");
        CommandParserTestUtil.assertParseSuccess(parser,
                " r/A106", expectedTraceCommand);

        expectedTraceCommand = new TraceCommand("A106", 2);
        CommandParserTestUtil.assertParseSuccess(parser,
                " r/A106 d/2", expectedTraceCommand);

    }

    @Test
    public void parse_invalidArgs_fails() {
        CommandParserTestUtil.assertParseFailure(parser,
                " a/A106", String.format(MESSAGE_INVALID_COMMAND_FORMAT, TraceCommand.MESSAGE_USAGE));

        CommandParserTestUtil.assertParseFailure(parser,
                " r/A106 d/2.3",
                "Depth and duration must be integers\n" + TraceCommand.MESSAGE_USAGE);

        CommandParserTestUtil.assertParseFailure(parser,
                " r/A1D06 d/2.3",
                "Information is neither a room or name\n" + TraceCommand.MESSAGE_USAGE);

    }
}
