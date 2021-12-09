package seedu.times.logic.parser.classcommandparsers;

import static seedu.times.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.times.logic.parser.CliSyntax.PREFIX_CLASSTIMING;
import static seedu.times.logic.parser.CliSyntax.PREFIX_CLASS_NAME;
import static seedu.times.logic.parser.CliSyntax.PREFIX_LOCATION;
import static seedu.times.logic.parser.CliSyntax.PREFIX_RATE;
import static seedu.times.logic.parser.ParserUtil.arePrefixesPresent;

import seedu.times.logic.commands.classcommands.AddClassCommand;
import seedu.times.logic.parser.ArgumentMultimap;
import seedu.times.logic.parser.ArgumentTokenizer;
import seedu.times.logic.parser.Parser;
import seedu.times.logic.parser.ParserUtil;
import seedu.times.logic.parser.exceptions.ParseException;
import seedu.times.model.tuitionclass.ClassName;
import seedu.times.model.tuitionclass.ClassTiming;
import seedu.times.model.tuitionclass.Location;
import seedu.times.model.tuitionclass.Rate;
import seedu.times.model.tuitionclass.StudentNameList;
import seedu.times.model.tuitionclass.TuitionClass;

/**
 * Parses input arguments and creates a new AddClassCommand object
 */
public class AddClassCommandParser implements Parser<AddClassCommand> {
    @Override
    public AddClassCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer
                        .tokenize(args, PREFIX_CLASS_NAME, PREFIX_RATE, PREFIX_CLASSTIMING, PREFIX_LOCATION);

        if (!arePrefixesPresent(argMultimap, PREFIX_CLASS_NAME, PREFIX_RATE, PREFIX_CLASSTIMING, PREFIX_LOCATION)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddClassCommand.MESSAGE_USAGE));
        }

        ClassName className = ParserUtil.parseClassName(argMultimap.getValue(PREFIX_CLASS_NAME).get());
        Rate rate = ParserUtil.parseRate(argMultimap.getValue(PREFIX_RATE).get());
        ClassTiming classTiming = ParserUtil.parseClassTiming(argMultimap.getValue(PREFIX_CLASSTIMING).get());
        Location location = ParserUtil.parseLocation(argMultimap.getValue(PREFIX_LOCATION).get());

        return new AddClassCommand(new TuitionClass(className, classTiming, location, rate, new StudentNameList()));
    }
}
