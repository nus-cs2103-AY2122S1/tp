package seedu.edrecord.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.edrecord.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.edrecord.logic.commands.DeleteModuleCommand;
import seedu.edrecord.logic.parser.exceptions.ParseException;
import seedu.edrecord.model.module.Module;

public class DeleteModuleCommandParser implements Parser<DeleteModuleCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the {@code DeleteModuleCommand}
     * and returns a {@code DeleteModuleCommand} object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    public DeleteModuleCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args);

        Module mod;
        try {
            mod = ParserUtil.parseModule(argMultimap.getPreamble());
        } catch (ParseException pe) {
            throw new ParseException(String.format(
                    MESSAGE_INVALID_COMMAND_FORMAT, DeleteModuleCommand.MESSAGE_USAGE), pe);
        }

        return new DeleteModuleCommand(mod);
    }

}
