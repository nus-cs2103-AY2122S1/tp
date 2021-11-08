package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.address.logic.commands.LoginCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.account.Password;

/**
 * Parses input arguments and creates a new LoginCommand object.
 */
public class LoginCommandParser implements Parser<LoginCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the LoginCommand
     * and returns a LoginCommand object for execution.
     *
     * @param args the input arguments related login command to be parsed.
     * @return LoginCommand the class for process login command string.
     * @throws ParseException if the user input does not conform the expected format.
     */
    public LoginCommand parse(String args) throws ParseException {
        try {
            Password password = ParserUtil.parsePassword(args);
            return new LoginCommand(password);
        } catch (ParseException pe) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, LoginCommand.MESSAGE_USAGE), pe);
        }
    }

}
