package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;


import seedu.address.logic.commands.AddFavCommand;
import seedu.address.model.person.Person;

import seedu.address.testutil.PersonBuilder;

/**
 * Contains integration tests (interaction with the Model) and unit tests for EditCommand.
 */
public class AddFavCommandParserTest {
    private AddFavCommandParser parser = new AddFavCommandParser();


    @Test
    public void parse_emptyArgs_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddFavCommand.MESSAGE_USAGE);

        // missing name prefix
        assertParseFailure(parser, ""
                , expectedMessage);
    }

    @Test
    public void parse_twoArgs_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddFavCommand.MESSAGE_USAGE);

        // missing name prefix
        assertParseFailure(parser, "A123333X A232113Y"
                , expectedMessage);
    }

    @Test
    public void parse_arg_success() {
        Person expectedPerson = new PersonBuilder().withName("Daniel Meier").withPhone("87652533")
                .withStudentId("A1234567D").withEmail("cornelia@example.com")
                .withFavourite(false).build();
        String id = expectedPerson.getStudentId().value;
        assertParseSuccess(parser, id
                , new AddFavCommand(id));
    }


}
