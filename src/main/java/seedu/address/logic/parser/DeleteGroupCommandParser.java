package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_CLASSCODE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_GROUPNUMBER;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TYPE;

import java.util.stream.Stream;

import seedu.address.logic.commands.DeleteGroupCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.student.ClassCode;
import seedu.address.model.tutorialgroup.GroupNumber;
import seedu.address.model.tutorialgroup.GroupType;
import seedu.address.model.tutorialgroup.TutorialGroup;

/**
 * Parses input arguments and creates a new DeleteGroupCommand object
 */
public class DeleteGroupCommandParser implements Parser<DeleteGroupCommand> {
    /**
     * Parses the given {@code String} of arguments in the context of the DeleteGroupCommand
     * and returns an DeleteGroupCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format.
     */
    public DeleteGroupCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_GROUPNUMBER, PREFIX_CLASSCODE, PREFIX_TYPE);

        if (!arePrefixesPresent(argMultimap, PREFIX_GROUPNUMBER, PREFIX_CLASSCODE, PREFIX_TYPE)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteGroupCommand.MESSAGE_USAGE));
        }

        GroupNumber groupNumber = ParserUtil.parseGroupNumber(argMultimap.getValue(PREFIX_GROUPNUMBER).get());
        ClassCode classCode = ParserUtil.parseClassCode(argMultimap.getValue(PREFIX_CLASSCODE).get());
        GroupType groupType = ParserUtil.parseGroupType(argMultimap.getValue(PREFIX_TYPE).get());

        TutorialGroup tutorialGroup = new TutorialGroup(groupNumber, classCode, groupType);

        return new DeleteGroupCommand(tutorialGroup);

    }

    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }
}
