package seedu.address.logic.parser;

import static seedu.address.logic.parser.CliSyntax.PREFIX_PASS;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.LoginCommand;
import seedu.address.logic.parser.exceptions.ParseException;

public class LoginCommandParserTest {

    private LoginCommandParser parser = new LoginCommandParser();

    @Test
    public void parse_validArgs_returnsListCommand() throws ParseException {
        assertParseSuccess(parser, " " + PREFIX_PASS + "123456",
                new LoginCommand(ParserUtil.parsePassword("123456")));
    }
}
