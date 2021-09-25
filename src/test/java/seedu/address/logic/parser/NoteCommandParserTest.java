package seedu.address.logic.parser;

import static seedu.address.logic.parser.CliSyntax.PREFIX_NOTE;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.NoteCommand;
import seedu.address.model.person.Note;

public class NoteCommandParserTest {
    private NoteCommandParser parser = new NoteCommandParser();
    private final String nonEmptyNote = "Test note.";

    @Test
    public void parse_indexSpecified_success() {
        // have remark
        Index targetIndex = INDEX_FIRST_PERSON;
        String userInput = targetIndex.getOneBased() + " " + PREFIX_NOTE + nonEmptyNote;
        NoteCommand expectedCommand = new NoteCommand(INDEX_FIRST_PERSON, new Note(nonEmptyNote));
        assertParseSuccess(parser, userInput, expectedCommand);

        // no remark
        userInput = targetIndex.getOneBased() + " " + PREFIX_NOTE;
        expectedCommand = new NoteCommand(INDEX_FIRST_PERSON, new Note(""));
        assertParseSuccess(parser, userInput, expectedCommand);
    }
}
