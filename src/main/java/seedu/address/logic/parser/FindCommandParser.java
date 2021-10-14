package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MODULE_CODE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;

import java.util.List;
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
        List<String> names = argMultimap.getAllValues(PREFIX_NAME);
        List<String> moduleCodes = argMultimap.getAllValues(PREFIX_MODULE_CODE);

        if (!names.isEmpty()) {
            Set<Name> nameSet;
            try {
                nameSet = ParserUtil.parseNames(names);
            } catch (ParseException e) {
                throw new ParseException(
                        String.format(MESSAGE_INVALID_COMMAND_FORMAT, e.getMessage())
                );
            }

            List<String> stringListOfNames = nameSet.stream()
                    .map(Name::toString)
                    .collect(Collectors.toList());

            return new FindCommand(
                    new NameContainsKeywordsPredicate(stringListOfNames)
            );
        }

        if (!moduleCodes.isEmpty()) {
            Set<ModuleCode> moduleCodeSet;
            try {
                moduleCodeSet = ParserUtil.parseModuleCodes(moduleCodes);
            } catch (ParseException e) {
                throw new ParseException(
                        String.format(MESSAGE_INVALID_COMMAND_FORMAT, e.getMessage())
                );
            }

            List<String> stringListOfModuleCodes = moduleCodeSet.stream()
                    .map(ModuleCode::toString)
                    .collect(Collectors.toList());

            return new FindCommand(
                    new ModuleCodesContainsKeywordsPredicate(stringListOfModuleCodes)
            );
        }

        throw new ParseException(
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE)
        );
    }
}
