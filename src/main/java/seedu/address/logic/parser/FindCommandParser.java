package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DASH_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DASH_INDEX;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DASH_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DASH_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DASH_ROLE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DASH_SALARY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DASH_STATUS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DASH_TAG;
import static seedu.address.logic.parser.ParserUtil.testByAllFieldsExceptName;

import java.util.Arrays;

import seedu.address.commons.core.Messages;
import seedu.address.logic.commands.FindCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.predicates.NameContainsKeywordsPredicate;
import seedu.address.model.person.predicates.PersonContainsFieldsPredicate;

/**
 * Parses input arguments and creates a new FindCommand object
 */
public class FindCommandParser implements Parser<FindCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the FindCommand
     * and returns a FindCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    public FindCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args,
                PREFIX_DASH_NAME, PREFIX_DASH_PHONE, PREFIX_DASH_INDEX,
                PREFIX_DASH_EMAIL, PREFIX_DASH_TAG, PREFIX_DASH_STATUS,
                PREFIX_DASH_ROLE, PREFIX_DASH_SALARY);

        PersonContainsFieldsPredicate predicate = testByAllFieldsExceptName(argMultimap);

        if (argMultimap.getValue(PREFIX_DASH_INDEX).isPresent()) {
            if (!ParserUtil.isValidInt(argMultimap.getValue(PREFIX_DASH_INDEX).get())) {
                throw new ParseException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
            }
            return new FindCommand(ParserUtil
                    .parseIndex(argMultimap.getValue(PREFIX_DASH_INDEX).get()).getZeroBased(), predicate);
        }
        if (argMultimap.getValue(PREFIX_DASH_NAME).isPresent()) {
            String[] nameKeywords = argMultimap.getValue(PREFIX_DASH_NAME).get()
                    .trim().split("\\s+");
            if (nameKeywords[0].equals("")) {
                throw new ParseException(
                        String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
            }
            return new FindCommand(new NameContainsKeywordsPredicate(Arrays.asList(nameKeywords)),
                    predicate);

        }
        if (!predicate.isEmpty()) {
            return new FindCommand(predicate);
        }
        throw new ParseException(
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
    }
}
