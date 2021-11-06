package seedu.edrecord.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.edrecord.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.edrecord.logic.commands.MakeGroupCommand;
import seedu.edrecord.logic.commands.MakeModuleCommand;
import seedu.edrecord.logic.parser.exceptions.ParseException;
import seedu.edrecord.model.module.Module;

public class MakeModuleCommandParser implements Parser<MakeModuleCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the {@code MakeModuleCommand}
     * and returns a {@code MakeModuleCommand} object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    public MakeModuleCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args);

        if (argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, MakeGroupCommand.MESSAGE_USAGE));
        }

        Module mod;
        mod = ParserUtil.parseModule(argMultimap.getPreamble());

        return new MakeModuleCommand(mod);
    }

}
