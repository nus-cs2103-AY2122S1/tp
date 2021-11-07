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

    private NoteCommandParser parser = new NoteCommandParser();

    @Test
    public void parse_validArgs_returnsNoteCommand() {
        assertParseSuccess(parser, " " + PREFIX_NAME + ALICE.getName().toString() + " "
                + PREFIX_NOTE + ALICE.getNote().toString(), new NoteCommand(ALICE.getName(), ALICE.getNote()));
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
