package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MODULE_CODE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import seedu.address.logic.commands.FindCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.ModuleCode;
import seedu.address.model.person.ModuleCodesContainsKeywordsPredicate;
import seedu.address.model.person.Name;
import seedu.address.model.person.NameContainsKeywordsPredicate;

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
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_NAME, PREFIX_MODULE_CODE);
        boolean isNamePrefixPresent = argMultimap.getValue(PREFIX_NAME).isPresent();
        boolean isModulePrefixPresent = argMultimap.getValue(PREFIX_MODULE_CODE).isPresent();

        if (isNamePrefixPresent && isModulePrefixPresent) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_SINGLE_PREFIX_SEARCH)
            );
        }

        if (isNamePrefixPresent) {
            Optional<String> searchInput = argMultimap.getValue(PREFIX_NAME);
            String names = searchInput.get().trim();
            if (names.isEmpty()) {
                throw new ParseException(
                        String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
            }

            String[] nameKeywords = names.split("\\s+");
            return new FindCommand(new NameContainsKeywordsPredicate(Arrays.asList(nameKeywords)));
        }

        if (isModulePrefixPresent) {
            Optional<String> searchInput = argMultimap.getValue(PREFIX_MODULE_CODE);
            String moduleCodes = searchInput.get().trim();
            if (moduleCodes.isEmpty()) {
                throw new ParseException(
                        String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
            }

            List<String> moduleKeywordsList = Arrays.stream(moduleCodes.split("\\s+"))
                    .map(moduleName -> '[' + moduleName + ']')
                    .collect(Collectors.toList());

            return new FindCommand(new ModuleCodesContainsKeywordsPredicate(moduleKeywordsList));
        }

        throw new ParseException(
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE)
        );
    }
}
