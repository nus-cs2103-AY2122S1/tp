package seedu.anilist.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.anilist.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.anilist.logic.parser.CliSyntax.PREFIX_NAME;

import seedu.anilist.commons.core.index.Index;
import seedu.anilist.logic.commands.RenameCommand;
import seedu.anilist.logic.parser.exceptions.ParseException;

public class RenameCommandParser implements Parser<RenameCommand> {
    /**
     * Parses the given {@code String} of arguments in the context of the EditCommand
     * and returns an EditCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public RenameCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_NAME);

        Index index;

        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                RenameCommand.MESSAGE_USAGE), pe);
        }

        RenameCommand.NameDescriptor nameDescriptor = new RenameCommand.NameDescriptor();
        if (argMultimap.getValue(PREFIX_NAME).isPresent()) {
            nameDescriptor.setName(ParserUtil.parseName(argMultimap.getValue(PREFIX_NAME).get()));
        }

        if (!nameDescriptor.isNameUpdated()) {
            throw new ParseException(RenameCommand.MESSAGE_NOT_RENAMED);
        }

        return new RenameCommand(index, nameDescriptor);
    }
}
