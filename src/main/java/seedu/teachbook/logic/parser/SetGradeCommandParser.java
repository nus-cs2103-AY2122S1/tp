package seedu.teachbook.logic.parser;

import static seedu.teachbook.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import java.util.List;

import seedu.teachbook.logic.commands.SetGradeCommand;
import seedu.teachbook.logic.parser.exceptions.ParseException;
import seedu.teachbook.model.gradeobject.Grade;

public class SetGradeCommandParser implements Parser<SetGradeCommand> {

    /**
     * Parses the user input according to the right context for SetGradeCommand
     *
     * @param args user input to be parsed.
     * @return {@code SetGradeCommand} result of parsing the input.
     * @throws ParseException if the user input does not conform the expected format.
     */
    public SetGradeCommand parse(String args) throws ParseException {
        String trimmedArgs = args.trim();

        if (trimmedArgs.isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, SetGradeCommand.MESSAGE_USAGE));
        }

        List<Grade> gradeList = ParserUtil.parseGrades(trimmedArgs);

        return new SetGradeCommand(gradeList);
    }

}
