package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MODULE_CODE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import seedu.address.logic.commands.person.FindCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.ModuleCodesContainsKeywordsPredicate;
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
        ArgumentMultimap argMultimap = ArgumentTokenizer
                .tokenize(args, PREFIX_NAME, PREFIX_MODULE_CODE);
        boolean isNamePrefixPresent = argMultimap.getValue(PREFIX_NAME).isPresent();
        boolean isModulePrefixPresent = argMultimap.getValue(PREFIX_MODULE_CODE).isPresent();

        long numberOfValidPrefixes = countValidPrefixes(isNamePrefixPresent, isModulePrefixPresent);

        if (numberOfValidPrefixes == 0) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
        }

        if (numberOfValidPrefixes > 1) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_SINGLE_PREFIX_SEARCH)
            );
        }

        assert(numberOfValidPrefixes == 1) : "There should only be one prefix used for the command!";

        if (isNamePrefixPresent) {
            return getFindNameCommand(argMultimap);
        } else if (isModulePrefixPresent) {
            return getFindModuleCommand(argMultimap);
        }
        throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
    }

    private long countValidPrefixes (Boolean... prefixPresent) {
        long count = Arrays.stream(prefixPresent).filter(x -> x).count();
        return count;
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

    private FindCommand getFindModuleCommand (ArgumentMultimap argMultimap) throws ParseException {
        Optional<String> searchInput = argMultimap.getValue(PREFIX_MODULE_CODE);
        String modules = searchInput.get().trim();
        if (modules.isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
        }

        List<String> moduleKeywordsList = Arrays.stream(modules.split("\\s+"))
                .map(module -> '[' + module + ']')
                .collect(Collectors.toList());

        return new FindCommand(new ModuleCodesContainsKeywordsPredicate(moduleKeywordsList));
    }
}
