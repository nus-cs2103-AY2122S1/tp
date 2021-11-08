package seedu.siasa.logic.parser.policy;

import static seedu.siasa.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.siasa.logic.commands.CommandTestUtil.TITLE_DESC;
import static seedu.siasa.logic.parser.CommandParserTestUtil.assertParseFailure;

import org.junit.jupiter.api.Test;

import seedu.siasa.logic.commands.policy.AddPolicyCommand;

public class AddPolicyCommandParserTest {
    private AddPolicyCommandParser parser = new AddPolicyCommandParser();

    @Test
    public void parse_compulsoryFieldMissing_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddPolicyCommand.MESSAGE_USAGE);

        assertParseFailure(parser, TITLE_DESC, expectedMessage);
    }
}
