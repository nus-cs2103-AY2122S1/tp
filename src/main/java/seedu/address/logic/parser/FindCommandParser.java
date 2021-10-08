package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_FIND_CONDITION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;

import java.util.List;

import seedu.address.logic.commands.FindCommand;
import seedu.address.logic.commands.FindCommand.FindCondition;
import seedu.address.logic.parser.exceptions.ParseException;
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

        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_NAME, PREFIX_ADDRESS,
            PREFIX_FIND_CONDITION);

        if (!argMultimap.getPreamble().isEmpty()) {
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

        if (!predicate.isAnyFieldSearched()) {
            throw new ParseException(FindCommand.MESSAGE_FIELD_REQUIRED);
        }

        // Find condition is optional and defaults to match all fields
        if (argMultimap.getValue(PREFIX_FIND_CONDITION).isPresent()) {
            FindCondition condition = ParserUtil.parseFindCondition(argMultimap.getValue(PREFIX_FIND_CONDITION).get());
            predicate.setCondition(condition);
        }

        return new FindCommand(predicate);
    }
}
