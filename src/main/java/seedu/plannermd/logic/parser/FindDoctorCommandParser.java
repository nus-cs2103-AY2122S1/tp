package seedu.plannermd.logic.parser;

import static seedu.plannermd.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import java.util.Arrays;

import seedu.plannermd.logic.commands.findcommand.FindDoctorCommand;
import seedu.plannermd.logic.parser.exceptions.ParseException;
import seedu.plannermd.model.person.NameContainsKeywordsPredicate;

public class FindDoctorCommandParser implements Parser<FindDoctorCommand> {
    /**
     * Parses the given {@code String} of arguments in the context of the FindDoctorCommand
     * and returns a FindPatientCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public FindDoctorCommand parse(String args) throws ParseException {
        String trimmedArgs = args.trim();
        if (trimmedArgs.isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindDoctorCommand.MESSAGE_USAGE));
        }

        String[] nameKeywords = trimmedArgs.split("\\s+");

        return new FindDoctorCommand(new NameContainsKeywordsPredicate(Arrays.asList(nameKeywords)));
    }
}
