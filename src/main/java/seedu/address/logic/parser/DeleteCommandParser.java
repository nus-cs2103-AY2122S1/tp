package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_INDEX;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ROLE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_STATUS;

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
                ArgumentTokenizer.tokenize(args, PREFIX_INDEX, PREFIX_NAME, PREFIX_ROLE, PREFIX_STATUS);

        if (!exactlyOneAcceptedPrefix(argMultimap)) {

            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteCommand.MESSAGE_USAGE));

        } else if (argMultimap.getValue(PREFIX_INDEX).isPresent()) {

            try {
                Index index = ParserUtil.parseIndex(argMultimap.getValue(PREFIX_INDEX).get());
                return new DeleteCommand(index);
            } catch (ParseException pe) {
                throw new ParseException(
                        String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteCommand.MESSAGE_USAGE), pe);
            }

        } else if (argMultimap.getValue(PREFIX_NAME).isPresent()) {

            Name name = ParserUtil.parseName(argMultimap.getValue(PREFIX_NAME).get());
            return new DeleteCommand(name);

        } else if (argMultimap.getValue(PREFIX_ROLE).isPresent()) {

            try {
                Role role = Role.translateStringToRole(argMultimap.getValue(PREFIX_ROLE).get());
                return new DeleteCommand(role);
            } catch (IllegalArgumentException e) {
                throw new ParseException(e.getMessage());
            }

        } else if (argMultimap.getValue(PREFIX_STATUS).isPresent()) {

            try {
                Status status = Status.translateStringToStatus(argMultimap.getValue(PREFIX_STATUS).get());
                return new DeleteCommand(status);
            } catch (IllegalArgumentException e) {
                throw new ParseException(e.getMessage());
            }

        } else {

            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteCommand.MESSAGE_USAGE));

        }
    }

    private boolean exactlyOneAcceptedPrefix(ArgumentMultimap argMultimap) {
        Prefix[] prefixes = {PREFIX_INDEX, PREFIX_NAME, PREFIX_ROLE, PREFIX_STATUS};
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
