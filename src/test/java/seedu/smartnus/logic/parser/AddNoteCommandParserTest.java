package seedu.smartnus.logic.parser;

import static seedu.smartnus.logic.commands.CommandTestUtil.INVALID_NOTE_DESC;
import static seedu.smartnus.logic.commands.CommandTestUtil.NOTE_DESC_1;
import static seedu.smartnus.logic.commands.CommandTestUtil.VALID_NOTE_1;
import static seedu.smartnus.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.smartnus.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.smartnus.logic.commands.AddNoteCommand;
import seedu.smartnus.model.note.Note;
import seedu.smartnus.model.note.NoteName;

public class AddNoteCommandParserTest {
    private final AddNoteCommandParser parser = new AddNoteCommandParser();

    @Test
    void parse_allFieldsValid_success() {

        Note expectedNote = new Note(VALID_NOTE_1);

        AddNoteCommand expectedCommand = new AddNoteCommand(expectedNote);

        // normal command with preamble whitespace
        assertParseSuccess(parser, NOTE_DESC_1, expectedCommand);
    }

    @Test
    void parse_fieldsOrPrefixMissing_failure() {
        String expectedMessage = NoteName.MESSAGE_CONSTRAINTS;
        // note prefix missing
        assertParseFailure(parser, INVALID_NOTE_DESC , expectedMessage);
    }
}
