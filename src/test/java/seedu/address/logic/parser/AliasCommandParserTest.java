package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;
import static seedu.address.logic.commands.CommandTestUtil.ADD_COMMAND;
import static seedu.address.logic.commands.CommandTestUtil.ALIAS;
import static seedu.address.logic.commands.CommandTestUtil.PREAMBLE_WHITESPACE;
import static seedu.address.logic.commands.CommandTestUtil.STATISTICS_COMMAND;
import static seedu.address.logic.commands.CommandTestUtil.UNKNOWN_COMMAND;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ALIAS;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_FRIEND;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TUTORIAL_GROUP_AMY;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalPersons.BOB;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.AddCommand;
import seedu.address.logic.commands.AliasCommand;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.StatisticsCommand;
import seedu.address.model.person.Person;
import seedu.address.model.person.TutorialGroup;
import seedu.address.testutil.PersonBuilder;

public class AliasCommandParserTest {

    private AliasCommandParser parser = new AliasCommandParser();

    @Test
    public void parse_allFieldsPresent_success() {
        Person expectedPerson = new PersonBuilder(BOB).withTags(VALID_TAG_FRIEND).build();
        Command expectedCommand = new AddCommand(expectedPerson);
        String userInput = PREAMBLE_WHITESPACE + ALIAS + ADD_COMMAND;

        // AddCommand
        assertParseSuccess(parser, userInput, new AliasCommand(VALID_ALIAS, expectedCommand, ADD_COMMAND));

        // StatisticsCommand
        expectedCommand = new StatisticsCommand(new TutorialGroup(VALID_TUTORIAL_GROUP_AMY));
        userInput = PREAMBLE_WHITESPACE + ALIAS + STATISTICS_COMMAND;
        assertParseSuccess(parser, userInput, new AliasCommand(VALID_ALIAS, expectedCommand, STATISTICS_COMMAND));

        // More tests to be added
    }


    @Test
    public void parse_compulsoryFieldMissing_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, AliasCommand.MESSAGE_USAGE);

        // missing alias prefix
        assertParseFailure(parser, PREAMBLE_WHITESPACE + ADD_COMMAND, expectedMessage);

        // missing command prefix
        assertParseFailure(parser, PREAMBLE_WHITESPACE + ALIAS, expectedMessage);

        // all prefixes missing
        assertParseFailure(parser, PREAMBLE_WHITESPACE, expectedMessage);
    }

    @Test
    public void parse_invalidValue_failure() {
        // unknown command
        assertParseFailure(parser, PREAMBLE_WHITESPACE + ALIAS + UNKNOWN_COMMAND, MESSAGE_UNKNOWN_COMMAND);
    }
}
