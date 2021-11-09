package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MODULE_NAME;

import java.util.stream.Stream;

import seedu.address.logic.commands.AddModuleCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.module.Module;
import seedu.address.model.module.ModuleName;

/**
 * Parses input arguments and creates a new AddModuleCommand object.
 */
public class AddModuleCommandParser implements Parser<AddModuleCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the AddCommand
     * and returns an AddCommand object for execution.
     *
     * @param args Args for adding a module.
     * @return AddModuleCommand object created from the user input.
     * @throws ParseException if the user input does not conform the expected format.
     */
    @Override
    public AddModuleCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_MODULE_NAME);
        if (!arePrefixesPresent(argMultimap, PREFIX_MODULE_NAME)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddModuleCommand.MESSAGE_USAGE));
        }

        ModuleName moduleName = ParserUtil.parseModuleName(argMultimap.getValue(PREFIX_MODULE_NAME).get());
        Module module = new Module(moduleName);

        return new AddModuleCommand(module);
    }

    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }
}
