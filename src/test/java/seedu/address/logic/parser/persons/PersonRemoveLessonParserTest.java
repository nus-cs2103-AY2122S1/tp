package seedu.address.logic.parser.persons;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.INVALID_COMMAND_INVALID_INDEX;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_LESSON;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.persons.EditPersonCommand;

class PersonRemoveLessonParserTest {

    private static final String INVALID_COMMAND = String.format(MESSAGE_INVALID_COMMAND_FORMAT,
            PersonRemoveLessonParser.MESSAGE_USAGE);

    private PersonRemoveLessonParser parser = new PersonRemoveLessonParser();

    @Test
    void parse_nullInput_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> parser.parse(null));
    }

    @Test
    public void parse_validArgs_returnsCorrectCommand() {
        EditPersonCommand.EditPersonDescriptor editPersonDesc = new EditPersonCommand.EditPersonDescriptor();
        editPersonDesc.removeLesson(INDEX_FIRST_LESSON);
        assertParseSuccess(parser, "1 1", new EditPersonCommand(INDEX_FIRST_PERSON, editPersonDesc,
                PersonRemoveLessonParser.MESSAGE_SUCCESS));
    }

    @Test
    public void parse_invalidArgs_throwsParseException() {
        // empty
        assertParseFailure(parser, " ", INVALID_COMMAND);

        // all wrong
        assertParseFailure(parser, "abcdefgh", INVALID_COMMAND);

        // half right :)
        assertParseFailure(parser, "1 abc", INVALID_COMMAND_INVALID_INDEX);

        assertParseFailure(parser, "abc 1", INVALID_COMMAND_INVALID_INDEX);
    }
}
