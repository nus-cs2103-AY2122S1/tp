package seedu.edrecord.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.edrecord.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.edrecord.logic.parser.AddCommandParser.arePrefixesPresent;
import static seedu.edrecord.logic.parser.CliSyntax.PREFIX_GROUP;
import static seedu.edrecord.logic.parser.CliSyntax.PREFIX_MODULE;

import java.util.ArrayList;
import java.util.List;

import seedu.edrecord.commons.core.index.Index;
import seedu.edrecord.logic.commands.MoveCommand;
import seedu.edrecord.logic.parser.exceptions.ParseException;
import seedu.edrecord.model.group.Group;
import seedu.edrecord.model.module.Module;

/**
 * Parses input arguments and creates a new MoveCommand object
 */
public class MoveCommandParser implements Parser<MoveCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the MoveCommand
     * and returns an MoveCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    public MoveCommand parse(String args) throws ParseException {
        requireNonNull(args);

        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_MODULE, PREFIX_GROUP);

        if (!arePrefixesPresent(argMultimap, PREFIX_MODULE, PREFIX_GROUP) || argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, MoveCommand.MESSAGE_USAGE));
        }

        Module module = ParserUtil.parseModule(argMultimap.getValue(PREFIX_MODULE).get());
        Group group = ParserUtil.parseGroup(argMultimap.getValue(PREFIX_GROUP).get());
        List<Index> indexes = new ArrayList<>();

        try {
            String[] preambles = argMultimap.getPreamble().split(" ");
            for (String i : preambles) {
                indexes.add(ParserUtil.parseIndex(i));
            }
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, MoveCommand.MESSAGE_USAGE), pe);
        }

        return new MoveCommand(indexes, module, group);
    }

}
