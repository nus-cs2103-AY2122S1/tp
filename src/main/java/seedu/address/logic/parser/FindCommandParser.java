package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MODULE_CODE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import seedu.address.logic.commands.FindCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.ModuleCodesContainsKeywordsPredicate;
import seedu.address.model.person.NameContainsKeywordsPredicate;
import seedu.address.model.person.TagsContainsKeywordsPredicate;

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
        ArgumentMultimap argMultimap = ArgumentTokenizer
                .tokenize(args, PREFIX_NAME, PREFIX_MODULE_CODE, PREFIX_TAG);
        boolean isNamePrefixPresent = argMultimap.getValue(PREFIX_NAME).isPresent();
        boolean isModulePrefixPresent = argMultimap.getValue(PREFIX_MODULE_CODE).isPresent();
        boolean isTagPrefixPresent = argMultimap.getValue(PREFIX_TAG).isPresent();

        // boolean condition to check that only one of the three prefixes are present
        if (checkMoreThanOnePrefixPresent(isNamePrefixPresent, isModulePrefixPresent, isTagPrefixPresent)) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_SINGLE_PREFIX_SEARCH)
            );
        }

        if (isNamePrefixPresent) {
            return getFindNameCommand(argMultimap);
        }

        if (isModulePrefixPresent) {
            return getFindModuleOrTagCommand(argMultimap, PREFIX_MODULE_CODE);
        }

        if (isTagPrefixPresent) {
            return getFindModuleOrTagCommand(argMultimap, PREFIX_TAG);
        }

        // all three prefixes not present
        throw new ParseException(
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE)
        );
    }

    private boolean checkMoreThanOnePrefixPresent (boolean firstPrefix, boolean secondPrefix, boolean thirdPrefix) {
        return firstPrefix ? (secondPrefix || thirdPrefix) : (secondPrefix && thirdPrefix);
    }

    private FindCommand getFindNameCommand(ArgumentMultimap argMultimap) throws ParseException {
        Optional<String> searchInput = argMultimap.getValue(PREFIX_NAME);
        String names = searchInput.get().trim();
        if (names.isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
        }

        String[] nameKeywords = names.split("\\s+");
        return new FindCommand(new NameContainsKeywordsPredicate(Arrays.asList(nameKeywords)));

    }

    private FindCommand getFindModuleOrTagCommand(ArgumentMultimap argMultimap, Prefix prefix) throws ParseException {
        Optional<String> searchInput = argMultimap.getValue(prefix);
        String tagsOrModules = searchInput.get().trim();
        if (tagsOrModules.isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
        }

        List<String> moduleKeywordsList = Arrays.stream(tagsOrModules.split("\\s+"))
                .map(tagOrModule -> '[' + tagOrModule + ']')
                .collect(Collectors.toList());

        if (prefix.equals(PREFIX_MODULE_CODE)) {
            return new FindCommand(new ModuleCodesContainsKeywordsPredicate(moduleKeywordsList));
        } else {
            return new FindCommand(new TagsContainsKeywordsPredicate(moduleKeywordsList));
        }
    }
}
