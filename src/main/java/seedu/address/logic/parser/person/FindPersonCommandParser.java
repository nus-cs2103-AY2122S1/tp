package seedu.address.logic.parser.person;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MODULE_CODE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import seedu.address.logic.commands.person.FindPersonCommand;
import seedu.address.logic.parser.ArgumentMultimap;
import seedu.address.logic.parser.ArgumentTokenizer;
import seedu.address.logic.parser.Parser;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.ModuleCodesContainsKeywordsPredicate;
import seedu.address.model.person.NameContainsKeywordsPredicate;

/**
 * Parses input arguments and creates a new FindPersonCommand object
 */
public class FindPersonCommandParser implements Parser<FindPersonCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the FindCommand
     * and returns a FindCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public FindPersonCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap = ArgumentTokenizer
                .tokenize(args, PREFIX_NAME, PREFIX_MODULE_CODE);
        boolean isNamePrefixPresent = argMultimap.getValue(PREFIX_NAME).isPresent();
        boolean isModulePrefixPresent = argMultimap.getValue(PREFIX_MODULE_CODE).isPresent();

        long numberOfValidPrefixes = countValidPrefixes(isNamePrefixPresent, isModulePrefixPresent);

        if (numberOfValidPrefixes == 0) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindPersonCommand.MESSAGE_USAGE));
        }

        if (numberOfValidPrefixes > 1) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindPersonCommand.MESSAGE_SINGLE_PREFIX_SEARCH)
            );
        }

        assert(numberOfValidPrefixes == 1) : "There should only be one prefix used for the command!";

        if (isNamePrefixPresent) {
            return getFindNameCommand(argMultimap);
        } else if (isModulePrefixPresent) {
            return getFindModuleCommand(argMultimap);
        }
        throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindPersonCommand.MESSAGE_USAGE));
    }

    private long countValidPrefixes (Boolean... prefixPresent) {
        long count = Arrays.stream(prefixPresent).filter(x -> x).count();
        return count;
    }

    private FindPersonCommand getFindNameCommand(ArgumentMultimap argMultimap) throws ParseException {
        Optional<String> searchInput = argMultimap.getValue(PREFIX_NAME);
        String names = searchInput.get().trim();
        if (names.isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindPersonCommand.MESSAGE_USAGE));
        }

        String[] nameKeywords = names.split("\\s+");
        return new FindPersonCommand(new NameContainsKeywordsPredicate(Arrays.asList(nameKeywords)));

    }

    private FindPersonCommand getFindModuleCommand (ArgumentMultimap argMultimap) throws ParseException {
        Optional<String> searchInput = argMultimap.getValue(PREFIX_MODULE_CODE);
        String modules = searchInput.get().trim();
        if (modules.isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindPersonCommand.MESSAGE_USAGE));
        }

        List<String> moduleKeywordsList = Arrays.stream(modules.split("\\s+"))
                .collect(Collectors.toList());

        return new FindPersonCommand(new ModuleCodesContainsKeywordsPredicate(moduleKeywordsList));
    }
}
