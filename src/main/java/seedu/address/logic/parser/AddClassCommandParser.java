package seedu.address.logic.parser;

import seedu.address.logic.commands.AddClassCommand;
import seedu.address.logic.commands.AddStudentCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.tag.Tag;
import seedu.address.model.tutorialclass.Schedule;
import seedu.address.model.tutorialclass.TutorialClass;

import java.util.Set;
import java.util.stream.Stream;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.*;

public class AddClassCommandParser implements Parser<AddClassCommand> {

    public AddClassCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_CLASSCODE, PREFIX_SCHEDULE, PREFIX_TAG);

        if (!arePrefixesPresent(argMultimap, PREFIX_CLASSCODE, PREFIX_SCHEDULE, PREFIX_TAG)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddClassCommand.MESSAGE_USAGE));
        }

        String classCode = ParserUtil.parseClassCode(argMultimap.getValue(PREFIX_CLASSCODE).get());
        Schedule schedule = ParserUtil.parseSchedule(argMultimap.getValue(PREFIX_SCHEDULE).get());
        Set<Tag> tagList = ParserUtil.parseTags(argMultimap.getAllValues(PREFIX_TAG));

        TutorialClass tutorialClass = new TutorialClass(classCode, schedule, tagList);

        return new AddClassCommand(tutorialClass);

    }

    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }
}
