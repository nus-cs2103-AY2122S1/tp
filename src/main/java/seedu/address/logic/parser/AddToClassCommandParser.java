package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_STUDENT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_STUDENT_INDEX;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TUITION_CLASS;

import java.util.List;
import java.util.stream.Stream;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.AddToClassCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.tuition.StudentList;

/**
 * Parses input arguments and creates a new AddToClassCommand object
 */
public class AddToClassCommandParser implements Parser<AddToClassCommand> {

    @Override
    public AddToClassCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
            ArgumentTokenizer.tokenize(args, PREFIX_STUDENT_INDEX, PREFIX_TUITION_CLASS);
        ArgumentMultimap argMultimapName =
                ArgumentTokenizer.tokenize(args, PREFIX_STUDENT, PREFIX_TUITION_CLASS);
        if (!arePrefixesPresent(argMultimap, PREFIX_STUDENT_INDEX, PREFIX_TUITION_CLASS)) {
            if (!arePrefixesPresent(argMultimapName, PREFIX_STUDENT, PREFIX_TUITION_CLASS)
                    || !argMultimapName.getPreamble().isEmpty()) {
                throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                        AddToClassCommand.MESSAGE_USAGE));
            }
            StudentList student = ParserUtil.parseStudent(argMultimapName.getAllValues(PREFIX_STUDENT));
            Index classIndex = ParserUtil.parseIndex(argMultimapName.getValue(PREFIX_TUITION_CLASS).get());
            return new AddToClassCommand(student, classIndex);
        }
        if (!argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    AddToClassCommand.MESSAGE_USAGE));
        }
        List<Index> studentIndex = ParserUtil.parseIndices(argMultimap.getAllValues(PREFIX_STUDENT_INDEX));
        Index classIndex = ParserUtil.parseIndex(argMultimap.getValue(PREFIX_TUITION_CLASS).get());
        return new AddToClassCommand(studentIndex, classIndex);
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }
}
