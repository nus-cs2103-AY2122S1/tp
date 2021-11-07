package dash.logic.parser.personcommand;

import static dash.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static dash.logic.parser.CliSyntax.PREFIX_EMAIL;
import static dash.logic.parser.CliSyntax.PREFIX_NAME;
import static dash.logic.parser.CliSyntax.PREFIX_PHONE;
import static dash.logic.parser.CliSyntax.PREFIX_TAG;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

import dash.commons.core.Messages;
import dash.logic.commands.CommandTestUtil;
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
        // no leading and trailing whitespaces for name
        FindPersonDescriptor findPersonDescriptor = new FindPersonDescriptor();
        findPersonDescriptor.setName(Arrays.asList("Alice", "Bob"));
        FindPersonCommand expectedFindPersonCommand =
                new FindPersonCommand(findPersonDescriptor);
        CommandParserTestUtil.assertParseSuccess(parser, "Alice Bob", expectedFindPersonCommand);

        // multiple whitespaces between keywords for name
        CommandParserTestUtil.assertParseSuccess(parser, " \n Alice \n \t Bob  \t", expectedFindPersonCommand);

        //name with prefix
        FindPersonDescriptor findPersonDescriptorA = new FindPersonDescriptor();
        findPersonDescriptorA.setName(Arrays.asList("Alice", "Yeoh"));
        FindPersonCommand expectedFindPersonCommandA =
                new FindPersonCommand(findPersonDescriptorA);
        String userInput = " " + PREFIX_NAME + "Alice Yeoh";
        CommandParserTestUtil.assertParseSuccess(parser, userInput, expectedFindPersonCommandA);

        //phone number
        FindPersonDescriptor findPersonDescriptorB = new FindPersonDescriptor();
        findPersonDescriptorB.setPhone(Arrays.asList(CommandTestUtil.VALID_PHONE_AMY));
        FindPersonCommand expectedFindPersonCommandB =
                new FindPersonCommand(findPersonDescriptorB);
        userInput = " " + PREFIX_PHONE + CommandTestUtil.VALID_PHONE_AMY;
        CommandParserTestUtil.assertParseSuccess(parser, userInput, expectedFindPersonCommandB);

        //email
        FindPersonDescriptor findPersonDescriptorC = new FindPersonDescriptor();
        findPersonDescriptorC.setEmail(Arrays.asList(CommandTestUtil.VALID_EMAIL_AMY));
        FindPersonCommand expectedFindPersonCommandC =
                new FindPersonCommand(findPersonDescriptorC);
        userInput = " " + PREFIX_EMAIL + CommandTestUtil.VALID_EMAIL_AMY;
        CommandParserTestUtil.assertParseSuccess(parser, userInput, expectedFindPersonCommandC);

        //address
        FindPersonDescriptor findPersonDescriptorD = new FindPersonDescriptor();
        findPersonDescriptorD.setAddress(Arrays.asList("Test", "Address"));
        FindPersonCommand expectedFindPersonCommandD =
                new FindPersonCommand(findPersonDescriptorD);
        userInput = " " + PREFIX_ADDRESS + "Test Address";
        CommandParserTestUtil.assertParseSuccess(parser, userInput, expectedFindPersonCommandD);

        //tags
        FindPersonDescriptor findPersonDescriptorE = new FindPersonDescriptor();
        findPersonDescriptorE.setTags(Arrays.asList("Friends", "Neighbours"));
        FindPersonCommand expectedFindPersonCommandE =
                new FindPersonCommand(findPersonDescriptorE);
        userInput = " " + PREFIX_TAG + "Friends " + PREFIX_TAG + "Neighbours ";
        CommandParserTestUtil.assertParseSuccess(parser, userInput, expectedFindPersonCommandE);
    }

}
