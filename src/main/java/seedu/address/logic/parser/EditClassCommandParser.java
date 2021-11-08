package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_LIMIT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TIMESLOT;

import java.util.logging.Logger;
import java.util.stream.Stream;

import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.EditClassCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.tuition.ClassLimit;
import seedu.address.model.tuition.ClassName;
import seedu.address.model.tuition.Timeslot;


/**
 * Parses input arguments and creates a new EditClassCommand object.
 */
public class EditClassCommandParser implements Parser<EditClassCommand> {

    private static final Logger logger = LogsCenter.getLogger(EditClassCommandParser.class);

    /**
     * Parses {@code userInput} into a command and returns it.
     *
     * @param userInput The user input to be parsed
     * @throws ParseException if {@code userInput} does not conform the expected format
     */
    @Override
    public EditClassCommand parse(String userInput) throws ParseException {
        requireNonNull(userInput);
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(userInput, PREFIX_NAME, PREFIX_LIMIT, PREFIX_TIMESLOT);
        if (noPrefixesPresent(argMultimap, PREFIX_NAME, PREFIX_LIMIT, PREFIX_TIMESLOT)) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditClassCommand.MESSAGE_USAGE));
        }
        Index index;
        EditClassCommand.EditClassDescriptor editClassDescriptor = new EditClassCommand.EditClassDescriptor();

        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (ParseException pe) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditClassCommand.MESSAGE_USAGE), pe);
        }
        parseClassDetails(editClassDescriptor, argMultimap);
        return new EditClassCommand(index, editClassDescriptor);
    }

    private void parseClassDetails(EditClassCommand.EditClassDescriptor editClassDescriptor,
                                   ArgumentMultimap argMultimap) throws ParseException {
        if (argMultimap.getValue(PREFIX_NAME).isPresent()) {
            ClassName className = new ClassName(ParserUtil.parseName(argMultimap.getValue(PREFIX_NAME).get()).fullName);
            editClassDescriptor.setClassName(className);
        }
        if (argMultimap.getValue(PREFIX_LIMIT).isPresent()) {
            ClassLimit limit = ParserUtil.parseLimit(argMultimap.getValue(PREFIX_LIMIT).get());
            editClassDescriptor.setLimit(limit);
        }
        if (argMultimap.getValue(PREFIX_TIMESLOT).isPresent()) {
            Timeslot timeslot = ParserUtil.parseTimeslot(argMultimap.getValue(PREFIX_TIMESLOT).get());
            editClassDescriptor.setTimeslot(timeslot);
        }
        if (!editClassDescriptor.isAnyFieldEdited()) {
            throw new ParseException(Messages.MESSAGE_NOT_EDITED);
        }
    }

    /**
     * Returns true if none of the relevant prefixes contain some {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean noPrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isEmpty());
    }
}


