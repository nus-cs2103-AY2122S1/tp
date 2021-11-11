package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_STUDENT_DISPLAYED_INDEX;
import static seedu.address.logic.parser.CliSyntax.PREFIX_CLASSCODE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_GROUPNUMBER;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TYPE;

import java.util.stream.Stream;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.logic.commands.AddGroupCommand;
import seedu.address.logic.commands.AddStudentToGroupCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.student.ClassCode;
import seedu.address.model.tutorialgroup.GroupNumber;
import seedu.address.model.tutorialgroup.GroupType;
import seedu.address.model.tutorialgroup.TutorialGroup;


public class AddStudentToGroupCommandParser implements Parser<AddGroupCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the AddStudentToGroupCommand
     * and returns an AddStudentToGroupCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format.
     */
    public AddStudentToGroupCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_GROUPNUMBER, PREFIX_CLASSCODE, PREFIX_TYPE);

        Index index;
        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (IllegalValueException ive) {
            throw new ParseException(String.format(
                    MESSAGE_INVALID_STUDENT_DISPLAYED_INDEX + MESSAGE_INVALID_COMMAND_FORMAT,
                    AddStudentToGroupCommand.MESSAGE_USAGE), ive);
        }

        if (!arePrefixesPresent(argMultimap, PREFIX_GROUPNUMBER, PREFIX_CLASSCODE, PREFIX_TYPE)) {
            throw new ParseException(String.format(
                    MESSAGE_INVALID_COMMAND_FORMAT, AddStudentToGroupCommand.MESSAGE_USAGE));
        }

        GroupNumber groupNumber = ParserUtil.parseGroupNumber(argMultimap.getValue(PREFIX_GROUPNUMBER).get());
        ClassCode classCode = ParserUtil.parseClassCode(argMultimap.getValue(PREFIX_CLASSCODE).get());
        GroupType groupType = ParserUtil.parseGroupType(argMultimap.getValue(PREFIX_TYPE).get());

        TutorialGroup tutorialGroup = new TutorialGroup(groupNumber, classCode, groupType);

        return new AddStudentToGroupCommand(index, tutorialGroup);

    }

    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }
}
