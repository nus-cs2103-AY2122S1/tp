package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.EVENT_NAME_DESC_PARTY;
import static seedu.address.logic.commands.CommandTestUtil.NAME_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EVENT_NAME_PARTY;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalPersons.BOB;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.RemovePersonFromEventCommand;
import seedu.address.model.event.Event;
import seedu.address.model.event.EventNameEqualKeywordPredicate;
import seedu.address.model.person.NameEqualKeywordPredicate;
import seedu.address.model.person.Person;
import seedu.address.testutil.EventBuilder;

public class RemovePersonFromEventCommandParserTest {

    private RemovePersonFromEventCommandParser parser = new RemovePersonFromEventCommandParser();

    @Test
    public void parse_personMissing_throwsCommandException() {
        Person person = BOB;
        Event event = new EventBuilder().withEventName(VALID_EVENT_NAME_PARTY).withPerson(person).build();

        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                RemovePersonFromEventCommand.MESSAGE_USAGE);
        assertParseFailure(parser, " " + EVENT_NAME_DESC_PARTY, expectedMessage);
    }

    @Test
    public void parse_eventMissing_throwsCommandException() {
        Person person = BOB;
        Event event = new EventBuilder().withEventName(VALID_EVENT_NAME_PARTY).withPerson(person).build();

        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                RemovePersonFromEventCommand.MESSAGE_USAGE);
        assertParseFailure(parser, " " + NAME_DESC_BOB, expectedMessage);
    }

    @Test
    public void parse_allSpecificationsMissing_throwsCommandException() {
        Person person = BOB;
        Event event = new EventBuilder().withEventName(VALID_EVENT_NAME_PARTY).withPerson(person).build();

        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                RemovePersonFromEventCommand.MESSAGE_USAGE);
        assertParseFailure(parser, " ", expectedMessage);
    }

    @Test
    public void parse_allSpecificationsValid_success() {
        Person person = BOB;
        Event event = new EventBuilder().withEventName(VALID_EVENT_NAME_PARTY).withPerson(person).build();

        RemovePersonFromEventCommand expectedCommand = new RemovePersonFromEventCommand(
                new NameEqualKeywordPredicate(person.getName()),
                new EventNameEqualKeywordPredicate(event.getEventName()));
        assertParseSuccess(parser, " " + NAME_DESC_BOB + " " + EVENT_NAME_DESC_PARTY,
                expectedCommand);
    }
}
