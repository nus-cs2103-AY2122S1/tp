package seedu.address.logic.parser;

import static seedu.address.logic.parser.CliSyntax.PREFIX_NOTE;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.person.PersonNoteCommand;
import seedu.address.logic.parser.person.PersonNoteCommandParser;
import seedu.address.model.person.Note;

public class PersonNoteCommandParserTest {
    private final PersonNoteCommandParser parser = new PersonNoteCommandParser();
    private final String nonEmptyNote = "Test note.";

    @Test
    public void parse_indexSpecified_success() {
        Index targetIndex = INDEX_FIRST_PERSON;

        // have remark
        String userInput = targetIndex.getOneBased() + " " + PREFIX_NOTE + nonEmptyNote;
        PersonNoteCommand expectedCommand = new PersonNoteCommand(targetIndex, new Note(nonEmptyNote));
        assertParseSuccess(parser, userInput, expectedCommand);

        // no remark
        userInput = targetIndex.getOneBased() + " " + PREFIX_NOTE;
        expectedCommand = new PersonNoteCommand(targetIndex, new Note(""));
        assertParseSuccess(parser, userInput, expectedCommand);
    }
}
