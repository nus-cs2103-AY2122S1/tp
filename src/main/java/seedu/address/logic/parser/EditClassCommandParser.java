package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_LIMIT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TIMESLOT;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.EditClassCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.tuition.ClassName;

public class EditClassCommandParser implements Parser<EditClassCommand> {
    /**
     * Parses {@code userInput} into a command and returns it.
     *
     * @param args
     * @throws ParseException if {@code userInput} does not conform the expected format
     */
    @Override
    public EditClassCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_NAME, PREFIX_LIMIT, PREFIX_TIMESLOT);
        Index index;

        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditClassCommand.MESSAGE_USAGE), pe);
        }
        EditClassCommand.EditClassDescriptor editClassDescriptor = new EditClassCommand.EditClassDescriptor();
        if (argMultimap.getValue(PREFIX_NAME).isPresent()) {
            ClassName name = new ClassName(ParserUtil.parseName(argMultimap.getValue(PREFIX_NAME).get()).fullName);
            editClassDescriptor.setClassName(name);
        }
        if (argMultimap.getValue(PREFIX_LIMIT).isPresent()) {
            editClassDescriptor.setLimit(ParserUtil.parseLimit(argMultimap.getValue(PREFIX_LIMIT).get()));
        }
        if (argMultimap.getValue(PREFIX_TIMESLOT).isPresent()) {
            editClassDescriptor.setTimeslot(ParserUtil.parseTimeslot(argMultimap.getValue(PREFIX_TIMESLOT).get()));
        }

        if (!editClassDescriptor.isAnyFieldEdited()) {
            throw new ParseException(Messages.MESSAGE_NOT_EDITED);
        }
        return new EditClassCommand(index, editClassDescriptor);
    }
}

