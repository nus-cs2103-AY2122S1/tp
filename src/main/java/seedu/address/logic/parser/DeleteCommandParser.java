package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DASH_INDEX;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DASH_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DASH_ROLE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DASH_STATUS;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.DeleteCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.Name;
import seedu.address.model.person.Role;
import seedu.address.model.person.Status;

/**
 * Parses input arguments and creates a new DeleteCommand object
 */
public class DeleteCommandParser implements Parser<DeleteCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the DeleteCommand
     * and returns a DeleteCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    public DeleteCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_DASH_INDEX, PREFIX_DASH_NAME, PREFIX_DASH_ROLE,
                        PREFIX_DASH_STATUS);

        if (!exactlyOneAcceptedPrefix(argMultimap)) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteCommand.MESSAGE_USAGE));
        }

        if (argMultimap.getValue(PREFIX_DASH_INDEX).isPresent()) {
            if (argMultimap.getValue(PREFIX_DASH_INDEX).get() == "") {
                throw new ParseException(
                        String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteCommand.MESSAGE_USAGE));
            }

            Index index = ParserUtil.parseIndex(argMultimap.getValue(PREFIX_DASH_INDEX).get());
            return new DeleteCommand(index);
        }

        try {
            if (argMultimap.getValue(PREFIX_DASH_NAME).isPresent()) {
                Name name = ParserUtil.parseName(argMultimap.getValue(PREFIX_DASH_NAME).get());
                return new DeleteCommand(name);
            } else if (argMultimap.getValue(PREFIX_DASH_ROLE).isPresent()) {
                Role role = ParserUtil.parseRole(argMultimap.getValue(PREFIX_DASH_ROLE).get());
                return new DeleteCommand(role);
            } else if (argMultimap.getValue(PREFIX_DASH_STATUS).isPresent()) {
                Status status = ParserUtil.parseStatus(argMultimap.getValue(PREFIX_DASH_STATUS).get());
                return new DeleteCommand(status);
            } else {
                throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteCommand.MESSAGE_USAGE));
            }
        } catch (ParseException pe) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteCommand.MESSAGE_USAGE), pe);
        }
    }

    private boolean exactlyOneAcceptedPrefix(ArgumentMultimap argMultimap) {
        Prefix[] prefixes = {PREFIX_DASH_INDEX, PREFIX_DASH_NAME, PREFIX_DASH_ROLE, PREFIX_DASH_STATUS};
        boolean isTrueOnlyOnce = false;
        for (Prefix prefix : prefixes) {
            if (argMultimap.getValue(prefix).isPresent()) {
                if (isTrueOnlyOnce) {
                    return false;
                }
                isTrueOnlyOnce = true;
            }
        }
        return isTrueOnlyOnce;
    }
}
