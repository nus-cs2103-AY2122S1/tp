package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.PasswordCommand.MESSAGE_INVALID_PASSWORD;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.PasswordCommand;
import seedu.address.testutil.PasswordUtil;

public class PasswordCommandParserTest {
    private PasswordCommandParser parser = new PasswordCommandParser();
    private String validPassword = "Password1234!";
    private String inValidPassword = "";

    @Test
    public void parse_repeatedFlag_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, PasswordCommand.MESSAGE_USAGE);
        assertParseFailure(parser, PasswordUtil.getRepeatedFlag(validPassword, validPassword), expectedMessage);
    }

    @Test
    public void parse_invalidPassword_failure() {
        String expectedMessage = MESSAGE_INVALID_PASSWORD
                + System.lineSeparator()
                + "NEW "
                + PasswordCommand.CORRECT_PASSWORD_FORMAT;
        assertParseFailure(parser, PasswordUtil.getPasswordCommand(validPassword, inValidPassword), expectedMessage);
    }

    @Test
    public void parse_emptyPassword_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, PasswordCommand.MESSAGE_USAGE);

        assertParseFailure(parser, PasswordUtil.getMissingFlag(validPassword),
                expectedMessage);
    }
}
