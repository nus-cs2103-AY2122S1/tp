package seedu.teachbook.logic.parser;

import static seedu.teachbook.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.teachbook.logic.parser.CliSyntax.PREFIX_GRADE;
import static seedu.teachbook.logic.parser.ParserUtil.arePrefixesPresent;

import java.util.ArrayList;
import java.util.List;

import seedu.teachbook.commons.core.index.Index;
import seedu.teachbook.logic.commands.GradeCommand;
import seedu.teachbook.logic.parser.exceptions.ParseException;
import seedu.teachbook.model.gradeobject.Grade;

public class GradeCommandParser implements Parser<GradeCommand> {

    @Override
    public GradeCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_GRADE);
        List<Index> indices = new ArrayList<>();
        boolean isAll;

        String preamble = argMultimap.getPreamble();

        isAll = ParserUtil.parseAll(preamble);
        if (!isAll) {
            try {
                indices = ParserUtil.parseIndices(preamble);
            } catch (ParseException pe) {
                throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, GradeCommand.MESSAGE_USAGE), pe);
            }
        }

        if (!arePrefixesPresent(argMultimap, PREFIX_GRADE)) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, GradeCommand.MESSAGE_USAGE));
        }

        Grade grade = ParserUtil.parseGrade(argMultimap.getValue(PREFIX_GRADE).get());

        if (isAll) {
            return new GradeCommand(grade);
        } else {
            return new GradeCommand(indices, grade);
        }
    }

}

