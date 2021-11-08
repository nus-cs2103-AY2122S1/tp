package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.AddNoteCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new AddNoteCommand object
 */
public class AddNoteCommandParser implements Parser<AddNoteCommand> {

    /**
     * Used for separation of index and note.
     */
    private static final Pattern ADD_NOTE_ARGS_FORMAT = Pattern
            .compile("(?<index>\\d+) (?<note>.*)");

    /**
     * Parses the given {@code String} of arguments in the context of the AddNoteCommand
     * and returns an AddNoteCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public AddNoteCommand parse(String args) throws ParseException {
        requireNonNull(args);
        final Matcher matcher = ADD_NOTE_ARGS_FORMAT.matcher(args.trim());
        if (!matcher.matches()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddNoteCommand.MESSAGE_USAGE));
        }

        final String indexArg = matcher.group("index");
        final String note = matcher.group("note");

        try {
            Index index = ParserUtil.parseIndex(indexArg);
            return new AddNoteCommand(index, note);
        } catch (IndexOutOfBoundsException e) {
            throw new ParseException(MESSAGE_INVALID_PERSON_DISPLAYED_INDEX, e);
        }
    }
}
