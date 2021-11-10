package seedu.address.logic.parser;

import static seedu.address.logic.commands.CountdownCommand.MESSAGE_DATE_USAGE;

import java.time.LocalDate;

import seedu.address.commons.util.StringUtil;
import seedu.address.logic.commands.CountdownCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new DeleteCommand object
 */
public class CountdownCommandParser implements Parser<CountdownCommand> {

    @Override
    public CountdownCommand parse(String arg) throws ParseException {
        String trimmedArg = arg.trim();
        if (trimmedArg.isEmpty()) {
            return new CountdownCommand();
        } else {
            boolean isNotValidDate = !StringUtil.isValidDate(trimmedArg);
            if (isNotValidDate) {
                throw new ParseException(MESSAGE_DATE_USAGE);
            }
            LocalDate dateSetByUser = LocalDate.parse(trimmedArg);
            return new CountdownCommand(dateSetByUser);
        }
    }
}
