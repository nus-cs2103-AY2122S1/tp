package seedu.address.logic.parser;

import seedu.address.logic.commands.AddCommand;
import seedu.address.logic.commands.AddModuleCommand;
import seedu.address.logic.commands.DeleteModuleCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.module.ModuleName;

import java.util.regex.Pattern;
import java.util.stream.Stream;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.*;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;

public class DeleteModuleCommandParser implements Parser<DeleteModuleCommand> {


    /**
     * Parses {@code userInput} into a command and returns it.
     *
     * @param args
     * @throws ParseException if {@code userInput} does not conform the expected format
     */
    @Override
    public DeleteModuleCommand parse(String args) throws ParseException {
        //todo: get the name of the module to be deleted (modules are compared by names)
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_MODULE_NAME);

        if (!arePrefixesPresent(argMultimap, PREFIX_MODULE_NAME)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteModuleCommand.MESSAGE_USAGE));
        }
        ModuleName moduleName = ParserUtil.parseModuleName(argMultimap.getValue(PREFIX_MODULE_NAME).get());
        return new DeleteModuleCommand(moduleName);
    }


    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }
}
