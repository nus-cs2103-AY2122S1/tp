package seedu.notor.logic.parser.person;

import static java.util.Objects.requireNonNull;
import static seedu.notor.logic.parser.CliSyntax.PREFIX_TAG;

import seedu.notor.logic.commands.person.PersonCommand;
import seedu.notor.logic.commands.person.PersonTagCommand;
import seedu.notor.logic.parser.ArgumentMultimap;
import seedu.notor.logic.parser.ArgumentTokenizer;
import seedu.notor.logic.parser.ParserUtil;
import seedu.notor.logic.parser.exceptions.ParseException;

/**
 * Checks the parsing for the tag command, and returns a Person Command when parse is called.
 */
public class PersonTagCommandParser extends PersonCommandParser {
    public static final String COMMAND_WORD = "tag";

    public PersonTagCommandParser(String unparsedIndex, String arguments) throws ParseException {
        super(unparsedIndex, arguments);
    }

    /**
     * Parses the given {@code String} of arguments in the context of the TagCommand
     * and returns an PersonTagCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    public PersonTagCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_TAG);

        if (!argMultimap.getValue(PREFIX_TAG).isPresent()) {
            throw new ParseException(PersonTagCommand.MESSAGE_NO_TAGS);
        }
        String tags = argMultimap.getValue(PREFIX_TAG).get();

        assert tags != null;
        if (tags.isEmpty()) {
            throw new ParseException(PersonTagCommand.MESSAGE_NO_TAGS);
        }

        return new PersonTagCommand(index, ParserUtil.parseTags(tags));
    }

    @Override public PersonCommand parse() throws ParseException {
        return parse(arguments);
    }
}
