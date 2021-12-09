package seedu.times.logic.parser.classcommandparsers;

import static java.util.Objects.requireNonNull;
import static seedu.times.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.times.logic.parser.CliSyntax.PREFIX_CLASSTIMING;
import static seedu.times.logic.parser.CliSyntax.PREFIX_CLASS_NAME;
import static seedu.times.logic.parser.CliSyntax.PREFIX_LOCATION;
import static seedu.times.logic.parser.CliSyntax.PREFIX_RATE;

import seedu.times.commons.core.index.Index;
import seedu.times.logic.commands.classcommands.EditClassCommand;
import seedu.times.logic.commands.classcommands.EditClassCommand.EditClassDescriptor;
import seedu.times.logic.parser.ArgumentMultimap;
import seedu.times.logic.parser.ArgumentTokenizer;
import seedu.times.logic.parser.Parser;
import seedu.times.logic.parser.ParserUtil;
import seedu.times.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new EditClassCommand object
 */
public class EditClassCommandParser implements Parser<EditClassCommand> {
    @Override
    public EditClassCommand parse(String args) throws ParseException {
        requireNonNull(args);

        ArgumentMultimap argMultimap =
                ArgumentTokenizer
                        .tokenize(args, PREFIX_CLASS_NAME, PREFIX_CLASSTIMING, PREFIX_RATE, PREFIX_LOCATION);

        Index index;

        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    EditClassCommand.MESSAGE_USAGE));
        }

        EditClassDescriptor editClassDescriptor = new EditClassDescriptor();

        if (argMultimap.getValue(PREFIX_CLASS_NAME).isPresent()) {
            editClassDescriptor.setClassName(ParserUtil.parseClassName(argMultimap.getValue(PREFIX_CLASS_NAME).get()));
        }
        if (argMultimap.getValue(PREFIX_CLASSTIMING).isPresent()) {
            editClassDescriptor.setClassTiming(ParserUtil
                    .parseClassTiming(argMultimap.getValue(PREFIX_CLASSTIMING).get()));
        }
        if (argMultimap.getValue(PREFIX_RATE).isPresent()) {
            editClassDescriptor.setRate(ParserUtil.parseRate(argMultimap.getValue(PREFIX_RATE).get()));
        }
        if (argMultimap.getValue(PREFIX_LOCATION).isPresent()) {
            editClassDescriptor.setLocation(ParserUtil.parseLocation(argMultimap.getValue(PREFIX_LOCATION).get()));
        }

        if (!editClassDescriptor.isAnyFieldEdited()) {
            throw new ParseException(EditClassCommand.MESSAGE_NO_FIELD_PROVIDED);
        }

        return new EditClassCommand(index, editClassDescriptor);
    }
}
