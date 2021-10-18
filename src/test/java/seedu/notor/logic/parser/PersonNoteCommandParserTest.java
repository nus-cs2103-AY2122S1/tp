package seedu.notor.logic.parser;

import static seedu.notor.logic.commands.CommandTestUtil.INVALID_PHONE_DESC;
import static seedu.notor.logic.commands.CommandTestUtil.PHONE_DESC_BOB;
import static seedu.notor.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.notor.testutil.TypicalIndexes.INDEX_FIRST_PERSON;

import org.junit.jupiter.api.Test;

import seedu.notor.commons.core.index.Index;
import seedu.notor.logic.commands.person.PersonNoteCommand;
import seedu.notor.logic.parser.exceptions.ParseException;

public class PersonNoteCommandParserTest {
    private final NotorParser notorParser = new NotorParser();

    @Test
    public void parse_indexSpecified_success() throws ParseException {
        Index targetIndex = INDEX_FIRST_PERSON;
        PersonNoteCommand expectedCommand = new PersonNoteCommand(targetIndex);
        String validNoteCommand = String.format("person 1 /note%s%s", INVALID_PHONE_DESC, PHONE_DESC_BOB);
        assertParseSuccess(notorParser.parseCommand(validNoteCommand), expectedCommand);
    }
}
