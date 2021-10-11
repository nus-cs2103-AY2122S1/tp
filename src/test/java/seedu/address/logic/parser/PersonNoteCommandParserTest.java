package seedu.address.logic.parser;

import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.person.PersonNoteCommand;
import seedu.address.logic.parser.person.PersonNoteCommandParser;

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
