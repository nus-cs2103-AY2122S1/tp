package seedu.smartnus.logic.parser;

import static seedu.smartnus.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.smartnus.logic.parser.CliSyntax.PREFIX_NOTE;
import static seedu.smartnus.logic.parser.ParserUtil.arePrefixesPresent;

import seedu.smartnus.logic.commands.AddNoteCommand;
import seedu.smartnus.logic.parser.exceptions.ParseException;
import seedu.smartnus.model.note.Note;
import seedu.smartnus.model.note.NoteName;

public class AddNoteCommandParser implements Parser<AddNoteCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the AddNoteCommand
     * and returns an AddNoteCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public AddNoteCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_NOTE);

        if (!arePrefixesPresent(argMultimap, PREFIX_NOTE)) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddNoteCommand.MESSAGE_USAGE));
        }

        NoteName noteName = ParserUtil.parseNoteName(argMultimap.getValue(PREFIX_NOTE).get());

        // becoming null, why?
        Note toAdd = new Note(noteName.fullName);

        return new AddNoteCommand(toAdd);
    }
}
