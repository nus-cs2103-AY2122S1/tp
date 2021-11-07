package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NOTE;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalStudents.ALICE;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.NoteCommand;

public class NoteCommandParserTest {

    private static final String MESSAGE_INVALID_FORMAT =
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, NoteCommand.MESSAGE_USAGE);

    private NoteCommandParser parser = new NoteCommandParser();

    @Test
    public void parse_validArgs_returnsNoteCommand() {
        assertParseSuccess(parser, " " + PREFIX_NAME + ALICE.getName().toString() + " "
                + PREFIX_NOTE + ALICE.getNote().toString(), new NoteCommand(ALICE.getName(), ALICE.getNote()));
    }

    @Test
    public void parse_invalidPreamble_failure() {
        // invalid prefix being parsed as preamble
        assertParseFailure(parser, " i/invalid", MESSAGE_INVALID_FORMAT);

        // invalid arguments being parsed as preamble
        assertParseFailure(parser, "invalid arguments", MESSAGE_INVALID_FORMAT);

        // invalid preamble before correct arguments
        assertParseFailure(parser, " -5" + PREFIX_NAME + ALICE.getName().toString() + " "
                + PREFIX_NOTE + ALICE.getNote().toString(), MESSAGE_INVALID_FORMAT);

        // invalid preamble before incomplete arguments
        assertParseFailure(parser, " 0" + PREFIX_NAME + ALICE.getName().toString(), MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidArgs_throwsParseException() {
        assertParseFailure(parser,
                " Alice", String.format(MESSAGE_INVALID_COMMAND_FORMAT, NoteCommand.MESSAGE_USAGE));
        assertParseFailure(parser,
                " Alice Note", String.format(MESSAGE_INVALID_COMMAND_FORMAT, NoteCommand.MESSAGE_USAGE));
        assertParseFailure(parser,
                " n/Alice", String.format(MESSAGE_INVALID_COMMAND_FORMAT, NoteCommand.MESSAGE_USAGE));
        assertParseFailure(parser,
                " no/A sample note", String.format(MESSAGE_INVALID_COMMAND_FORMAT, NoteCommand.MESSAGE_USAGE));
    }
}
