package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import seedu.address.logic.commands.FindCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.NameContainsKeywordsPredicate;
import seedu.address.model.person.PersonMatchesKeywordsPredicate;

/**
 * Parses input arguments and creates a new FindCommand object
 */
public class FindCommandParser implements Parser<FindCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the FindCommand
     * and returns a FindCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public FindCommand parse(String args) throws ParseException {
        Prefix[] prefixes = new Prefix[]{PREFIX_NAME, PREFIX_ADDRESS};
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, prefixes);
        
        if (!areAnyPrefixesPresent(argMultimap,prefixes) || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
        }

        PersonMatchesKeywordsPredicate predicate = new PersonMatchesKeywordsPredicate(); 
        
        if (argMultimap.getValue(PREFIX_NAME).isPresent()) {
            List<String> nameKeywords = ParserUtil.parseKeywords(argMultimap.getValue(PREFIX_NAME).get());
            predicate.setNameKeywords(nameKeywords);
        }
        if (argMultimap.getValue(PREFIX_ADDRESS).isPresent()) {
            List<String> addressKeywords = ParserUtil.parseKeywords(argMultimap.getValue(PREFIX_ADDRESS).get());
            predicate.setAddressKeywords(addressKeywords);
        }

        return new FindCommand(predicate);
    }

    /**
     * Returns true if at least one of the prefixes does not contain empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean areAnyPrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).anyMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }

}
