package seedu.notor.logic.parser.person;

import static seedu.notor.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.notor.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.notor.logic.parser.CliSyntax.PREFIX_TAG;

import java.util.Optional;
import java.util.Set;

import seedu.notor.logic.commands.person.PersonCommand;
import seedu.notor.logic.commands.person.PersonFindCommand;
import seedu.notor.logic.parser.ArgumentMultimap;
import seedu.notor.logic.parser.ArgumentTokenizer;
import seedu.notor.logic.parser.ParserUtil;
import seedu.notor.logic.parser.exceptions.ParseException;
import seedu.notor.model.person.PersonContainsPredicate;
import seedu.notor.model.tag.Tag;

/**
 * Parses input arguments and creates a new FindCommand object
 */
public class PersonFindCommandParser extends PersonCommandParser {
    private Optional<String> nameQuery;
    private Optional<Set<Tag>> tagQuery;
    public PersonFindCommandParser(String arguments) throws ParseException {
        super(null, arguments);
    }

    /**
     * Parses the given {@code String} of arguments in the context of the PersonFindCommand
     * and returns a PersonFindCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    public PersonFindCommand parse(String args) throws ParseException {
        if (args.trim().isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, PersonFindCommand.MESSAGE_USAGE));
        }

        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(arguments, PREFIX_NAME, PREFIX_TAG);
        nameQuery = argMultimap.getValue(PREFIX_NAME).map(String::trim).filter(s -> !s.isEmpty());
        tagQuery = argMultimap.getValue(PREFIX_TAG).map(String::trim).orElse("").equals("")
                    ? Optional.empty()
                    : Optional.ofNullable(ParserUtil.parseTags(argMultimap.getValue(PREFIX_TAG).get()));
        if (nameQuery.isEmpty() && tagQuery.isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, PersonFindCommand.MESSAGE_USAGE));
        }
        return new PersonFindCommand(new PersonContainsPredicate(nameQuery, tagQuery));
    }

    @Override
    public PersonCommand parse() throws ParseException {
        return parse(arguments);
    }
}
