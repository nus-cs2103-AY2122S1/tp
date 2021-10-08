package seedu.address.logic.parser;

import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.NoteCommand;

public class NoteCommandParserTest {
    private final NoteCommandParser parser = new NoteCommandParser();
    private final String nonEmptyNote = "Test note.";

    @Test
    public void parse_indexSpecified_success() {
        NoteCommand expectedCommand = new NoteCommand(INDEX_FIRST_PERSON);
        assertParseSuccess(parser, "1", expectedCommand);
    }
}
