package dash.logic.parser.personcommand;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

import dash.commons.core.Messages;
import dash.logic.commands.personcommand.FindPersonCommand;
import dash.logic.commands.personcommand.FindPersonCommand.FindPersonDescriptor;
import dash.logic.parser.CommandParserTestUtil;

public class FindPersonCommandParserTest {

    private FindPersonCommandParser parser = new FindPersonCommandParser();

    @Test
    public void parse_emptyArg_throwsParseException() {
        CommandParserTestUtil.assertParseFailure(parser, "     ",
                String.format(Messages.MESSAGE_INVALID_COMMAND_FORMAT,
                        FindPersonCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_validArgs_returnsFindCommand() {
        // no leading and trailing whitespaces
        FindPersonDescriptor findPersonDescriptor = new FindPersonDescriptor();
        findPersonDescriptor.setName(Arrays.asList("Alice", "Bob"));
        FindPersonCommand expectedFindPersonCommand =
                new FindPersonCommand(findPersonDescriptor);
        CommandParserTestUtil.assertParseSuccess(parser, "Alice Bob", expectedFindPersonCommand);

        // multiple whitespaces between keywords
        CommandParserTestUtil.assertParseSuccess(parser, " \n Alice \n \t Bob  \t", expectedFindPersonCommand);
    }

}
