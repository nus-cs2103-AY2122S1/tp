package seedu.address.logic.parser;

import seedu.address.MainApp;
import seedu.address.commons.core.LogsCenter;
import seedu.address.logic.commands.AddClassCommand;
import seedu.address.logic.commands.AddCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.Tuition.*;
import seedu.address.model.person.*;
import seedu.address.model.tag.Tag;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_LIMIT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_COUNTER;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TIMESLOT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_STUDENT;

import seedu.address.logic.commands.AddClassCommand;


import java.util.ArrayList;
import java.util.Set;
import java.util.logging.Logger;
import java.util.stream.Stream;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.*;

public class AddClassCommandParser implements Parser<AddClassCommand> {

    private static final Logger logger = LogsCenter.getLogger(AddClassCommand.class);
    /**
     * Parses the given {@code String} of arguments in the context of the AddClassCommand
     * and returns an AddCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public AddClassCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_NAME, PREFIX_LIMIT, PREFIX_COUNTER, PREFIX_TIMESLOT, PREFIX_STUDENT);

        if (!arePrefixesPresent(argMultimap, PREFIX_NAME, PREFIX_LIMIT, PREFIX_COUNTER, PREFIX_TIMESLOT, PREFIX_STUDENT)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddClassCommand.MESSAGE_USAGE));
        }

        ClassName name = ParserUtil.parseClassName(argMultimap.getValue(PREFIX_NAME).get());
        ClassLimit limit = ParserUtil.parseLimit(argMultimap.getValue(PREFIX_LIMIT).get());
        Counter counter = ParserUtil.parseCounter(argMultimap.getValue(PREFIX_COUNTER).get());
        Timeslot timeslot = ParserUtil.parseTimeslot(argMultimap.getValue(PREFIX_TIMESLOT).get());
//        Student student = ParserUtil.parseStudent(argMultimap.getAllValues(PREFIX_STUDENT));

        Student student = new Student(new ArrayList<>());//TODO parseStudent

        TuitionClass tuitionClass = new TuitionClass(name, limit, counter, timeslot, student);

        logger.info("AddClassCommandParser " + tuitionClass);

        return new AddClassCommand(tuitionClass);
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }

}
