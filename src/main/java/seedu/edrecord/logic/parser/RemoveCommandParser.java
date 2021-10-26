package seedu.edrecord.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.edrecord.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.edrecord.logic.parser.CliSyntax.PREFIX_GROUP;
import static seedu.edrecord.logic.parser.CliSyntax.PREFIX_MODULE;

import seedu.edrecord.commons.core.index.Index;
import seedu.edrecord.logic.commands.EditCommand;
import seedu.edrecord.logic.commands.RemoveCommand;
import seedu.edrecord.logic.commands.RemoveCommand.RemovePersonDescriptor;
import seedu.edrecord.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new RemoveCommand object
 */
public class RemoveCommandParser implements Parser<RemoveCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the MoveCommand
     * and returns an MoveCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    public RemoveCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_MODULE, PREFIX_GROUP);

        Index index;

        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditCommand.MESSAGE_USAGE), pe);
        }

        RemovePersonDescriptor removePersonDescriptor = new RemovePersonDescriptor();
        if (argMultimap.getValue(PREFIX_MODULE).isPresent()) {
            removePersonDescriptor.setModule(ParserUtil.parseModule(argMultimap.getValue(PREFIX_MODULE).get()));
        }
        if (argMultimap.getValue(PREFIX_GROUP).isPresent()) {
            removePersonDescriptor.setGroup(ParserUtil.parseGroup(argMultimap.getValue(PREFIX_GROUP).get()));
        }

        if (!removePersonDescriptor.isAnyFieldEdited()) {
            throw new ParseException(EditCommand.MESSAGE_NOT_EDITED);
        }

        return new RemoveCommand(index, removePersonDescriptor);
    }

}
