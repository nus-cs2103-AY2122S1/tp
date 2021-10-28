package seedu.edrecord.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.edrecord.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.edrecord.logic.parser.AddCommandParser.arePrefixesPresent;
import static seedu.edrecord.logic.parser.CliSyntax.PREFIX_GROUP;
import static seedu.edrecord.logic.parser.CliSyntax.PREFIX_MODULE;

import seedu.edrecord.logic.commands.DeleteGroupCommand;
import seedu.edrecord.logic.commands.MakeGroupCommand;
import seedu.edrecord.logic.parser.exceptions.ParseException;
import seedu.edrecord.model.group.Group;
import seedu.edrecord.model.module.Module;

public class DeleteGroupCommandParser implements Parser<DeleteGroupCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the {@code DeleteGroupCommand}
     * and returns a {@code DeleteGroupCommand} object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    public DeleteGroupCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_MODULE, PREFIX_GROUP);

        if (!arePrefixesPresent(argMultimap, PREFIX_MODULE, PREFIX_GROUP)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, MakeGroupCommand.MESSAGE_USAGE));
        }

        Module module = ParserUtil.parseModule(argMultimap.getValue(PREFIX_MODULE).get());
        Group group = ParserUtil.parseGroup(argMultimap.getValue(PREFIX_GROUP).get());

        return new DeleteGroupCommand(group, module);
    }

}
