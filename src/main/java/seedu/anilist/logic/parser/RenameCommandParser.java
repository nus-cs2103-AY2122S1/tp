package seedu.anilist.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.anilist.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.anilist.commons.core.Messages.MESSAGE_OUT_OF_RANGE_INDEX;
import static seedu.anilist.logic.parser.CliSyntax.PREFIX_NAME;

import java.util.Optional;

import seedu.anilist.commons.core.index.Index;
import seedu.anilist.logic.commands.RenameCommand;
import seedu.anilist.logic.parser.exceptions.IntegerOutOfRangeException;
import seedu.anilist.logic.parser.exceptions.ParseException;
import seedu.anilist.model.anime.Name;



public class RenameCommandParser implements Parser<RenameCommand> {
    private static final String MESSAGE_INVALID_COMMAND_RENAME = String.format(MESSAGE_INVALID_COMMAND_FORMAT,
            RenameCommand.MESSAGE_USAGE);

    /**
     * Parses the given {@code String} of arguments in the context of the RenameCommand
     * and returns an RenameCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public RenameCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap;
        try {
            argMultimap = ParserUtil.tokenizeWithCheck(args, RenameCommand.REQUIRES_PREAMBLE,
                    RenameCommand.REQUIRED_PREFIXES, RenameCommand.OPTIONAL_PREFIXES);
        } catch (ParseException pe) {
            throw new ParseException(MESSAGE_INVALID_COMMAND_RENAME);
        }
        Index index;

        try {
            String preamble = argMultimap.getPreamble();
            index = ParserUtil.parseIndex(preamble);
        } catch (IntegerOutOfRangeException e) {
            throw new ParseException(MESSAGE_OUT_OF_RANGE_INDEX);
        } catch (ParseException pe) {
            throw new ParseException(MESSAGE_INVALID_COMMAND_RENAME, pe);
        }

        RenameCommand.NameDescriptor nameDescriptor = new RenameCommand.NameDescriptor();
        Optional<String> nameParam = argMultimap.getValue(PREFIX_NAME);
        Name name = ParserUtil.parseName(nameParam.get());
        nameDescriptor.setName(name);

        if (!nameDescriptor.isNameUpdated()) {
            throw new ParseException(RenameCommand.MESSAGE_NOT_RENAMED);
        }

        return new RenameCommand(index, nameDescriptor);
    }
}
