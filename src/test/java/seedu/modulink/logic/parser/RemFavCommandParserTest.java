package seedu.modulink.logic.parser;

import static seedu.modulink.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.modulink.commons.core.Messages.MESSAGE_UNEXPECTED_INPUT_FORMAT;
import static seedu.modulink.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.modulink.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.modulink.logic.commands.RemFavCommand;
import seedu.modulink.model.person.Person;
import seedu.modulink.testutil.PersonBuilder;

/**
 * Contains integration tests (interaction with the Model) and unit tests for AddFavCommandParser.
 */
public class RemFavCommandParserTest {
    private RemFavCommandParser parser = new RemFavCommandParser();


    @Test
    public void parse_emptyArgs_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, RemFavCommand.MESSAGE_USAGE);

        // missing name prefix
        assertParseFailure(parser, "", expectedMessage);
    }

    @Test
    public void parse_twoArgs_failure() {
        String expectedMessage = RemFavCommand.MULTIPLE_ID_ERROR;

        // missing name prefix
        assertParseFailure(parser, "A123333X A232113Y", expectedMessage);
    }

    @Test
    public void parse_arg_success() {
        Person expectedPerson = new PersonBuilder().withName("Daniel Meier").withPhone("87652533")
                .withStudentId("A1234567D").withEmail("cornelia@example.com")
                .withFavourite(false).build();
        String id = expectedPerson.getStudentId().value;
        assertParseSuccess(parser, id, new RemFavCommand(id));
    }

    @Test
    public void parse_nonStudentId_failure() {
        String expectedMessage = String.format(MESSAGE_UNEXPECTED_INPUT_FORMAT, "random", RemFavCommand.MESSAGE_USAGE);

        // missing name prefix
        assertParseFailure(parser, "random", expectedMessage);
    }

}
