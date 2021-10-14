package seedu.notor.logic.parser;

import static seedu.notor.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.notor.testutil.TypicalIndexes.INDEX_FIRST_PERSON;

import org.junit.jupiter.api.Test;

import seedu.notor.commons.core.index.Index;
import seedu.notor.logic.commands.person.PersonNoteCommand;
import seedu.notor.logic.parser.person.PersonNoteCommandParser;

public class PersonNoteCommandParserTest {
    private final PersonNoteCommandParser parser = new PersonNoteCommandParser();

    @Test
    public void parse_indexSpecified_success() {
        Index targetIndex = INDEX_FIRST_PERSON;

        PersonNoteCommand expectedCommand = new PersonNoteCommand(targetIndex);
        expectedCommand = new PersonNoteCommand(targetIndex);

        assertParseSuccess(parser, "1", expectedCommand);
    }
}
