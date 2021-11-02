package seedu.address.logic.parser;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.commons.core.Messages.MESSAGE_TOO_MANY_FLAGS;
import static seedu.address.logic.commands.PasswordCommand.MESSAGE_INVALID_PASSWORD;
import static seedu.address.logic.commands.PasswordCommand.MESSAGE_WRONG_PASSWORD;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.PasswordCommand;
import seedu.address.testutil.PasswordUtil;

public class PasswordCommandParserTest {
    private PasswordCommandParser parser = new PasswordCommandParser();
    private String validPassword = "Password1234!";
    private String inValidPassword = "";

    @Test
    public void incorrect_formatPasswordReturns_false() {
        // empty password
        assertFalse(PasswordCommandParser.passwordValidation(""));

        // password too short
        assertFalse(PasswordCommandParser.passwordValidation("1232a!"));

        // contain illegal characters
        assertFalse(PasswordCommandParser.passwordValidation("`````sdfdf1212121"));

        // does not contain all three types of characters
        assertFalse(PasswordCommandParser.passwordValidation("gsdjfkhk123123"));
        assertFalse(PasswordCommandParser.passwordValidation("@#%$^@&*dfghj"));
        assertFalse(PasswordCommandParser.passwordValidation("121212121!!!!"));
        assertFalse(PasswordCommandParser.passwordValidation("2132354241412"));
        assertFalse(PasswordCommandParser.passwordValidation("sdfgjbsjkdfsdfkhsdf"));
        assertFalse(PasswordCommandParser.passwordValidation("!@#$%^&*(*&^%$#$%^&"));

        // contains /
        assertFalse(PasswordCommandParser.passwordValidation("password1234/"));

        // valid cases
        assertTrue(PasswordCommandParser.passwordValidation("p1!@#$%&*()_+=|<>?{}~-[]"));

        // valid plus additional characters
        assertTrue(PasswordCommandParser.passwordValidation("password1!@''`"));
    }

    @Test
    public void parse_repeatedFlag_failure() {
        String expectedMessage = MESSAGE_TOO_MANY_FLAGS
                + System.lineSeparator()
                + PasswordCommand.MESSAGE_USAGE;

        assertParseFailure(parser, PasswordUtil.getRepeatedFlag(validPassword, validPassword),
                expectedMessage);
    }

    @Test
    public void parse_wrongPassword_failure() {
        String expectedMessage = MESSAGE_WRONG_PASSWORD;

        assertParseFailure(parser, PasswordUtil.getPasswordCommand(inValidPassword, validPassword),
                expectedMessage);
    }

    @Test
    public void parse_invalidPassword_failure() {
        String expectedMessage = MESSAGE_INVALID_PASSWORD
                + System.lineSeparator()
                + PasswordCommand.CORRECT_PASSWORD_FORMAT;

        assertParseFailure(parser, PasswordUtil.getPasswordCommand(validPassword, inValidPassword),
                expectedMessage);
    }

    @Test
    public void parse_emptyPassword_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, PasswordCommand.MESSAGE_USAGE);

        assertParseFailure(parser, PasswordUtil.getMissingFlag(validPassword),
                expectedMessage);
    }
}
