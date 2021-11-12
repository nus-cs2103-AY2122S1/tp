package seedu.notor.logic.parser.group;

import static seedu.notor.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.notor.logic.parser.CliSyntax.PREFIX_NAME;

import java.util.Optional;

import seedu.notor.logic.commands.group.GroupCommand;
import seedu.notor.logic.commands.group.GroupFindCommand;
import seedu.notor.logic.parser.ArgumentMultimap;
import seedu.notor.logic.parser.ArgumentTokenizer;
import seedu.notor.logic.parser.exceptions.ParseException;
import seedu.notor.model.group.GroupContainsPredicate;

/**
 * Parses input arguments and creates a new GroupFindCommand object
 */
public class GroupFindCommandParser extends GroupCommandParser {
    private Optional<String> nameQuery;

    public GroupFindCommandParser(String arguments) throws ParseException {
        super(null, arguments);
    }

    /**
     * Parses the given {@code String} of arguments in the context of the GroupFindCommand
     * and returns a GroupFindCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    public GroupFindCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(arguments, PREFIX_NAME);
        if (args.trim().isEmpty() || argMultimap.getValue(PREFIX_NAME).isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, GroupFindCommand.MESSAGE_USAGE));
        }
        nameQuery = argMultimap.getValue(PREFIX_NAME).map(String::trim);

        return new GroupFindCommand(new GroupContainsPredicate(nameQuery));
    }

    @Override
    public GroupCommand parse() throws ParseException {
        return parse(arguments);
    }
}
