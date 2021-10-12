package dash.logic.parser.taskcommand;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

import dash.commons.core.Messages;
import dash.logic.commands.taskcommand.FindTaskCommand;
import dash.logic.commands.taskcommand.FindTaskCommand.FindTaskDescriptor;
import dash.logic.parser.CommandParserTestUtil;

public class FindTaskCommandParserTest {

    private FindTaskCommandParser parser = new FindTaskCommandParser();

    @Test
    public void parse_emptyArg_throwsParseException() {
        CommandParserTestUtil.assertParseFailure(parser, "     ",
                String.format(Messages.MESSAGE_INVALID_COMMAND_FORMAT,
                        FindTaskCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_validArgs_returnsFindCommand() {
        // no leading and trailing whitespaces
        FindTaskDescriptor findTaskDescriptor = new FindTaskDescriptor();
        findTaskDescriptor.setDesc(Arrays.asList("Desc1", "Desc2"));
        FindTaskCommand expectedFindTaskCommand =
                new FindTaskCommand(findTaskDescriptor);
        CommandParserTestUtil.assertParseSuccess(parser, "Desc1 Desc2", expectedFindTaskCommand);

        // multiple whitespaces between keywords
        CommandParserTestUtil.assertParseSuccess(parser, " \n Desc1 \n \t Desc2  \t", expectedFindTaskCommand);
    }

}
