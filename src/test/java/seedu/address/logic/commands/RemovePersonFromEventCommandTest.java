package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.commons.core.Messages.MESSAGE_EVENT_DETAILS_NOT_FOUND;
import static seedu.address.commons.core.Messages.MESSAGE_PERSON_DETAILS_NOT_FOUND;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EVENT_NAME_PARTY;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalPersons.BOB;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.event.Event;
import seedu.address.model.event.EventNameEqualKeywordPredicate;
import seedu.address.model.person.NameEqualKeywordPredicate;
import seedu.address.testutil.EventBuilder;

public class RemovePersonFromEventCommandTest {

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void execute_personNotFound_throwsCommandException() {
        Event event = new EventBuilder().withEventName(VALID_EVENT_NAME_PARTY).build();
        model.addEvent(event);

        NameEqualKeywordPredicate personPredicate = new NameEqualKeywordPredicate(BOB.getName());
        EventNameEqualKeywordPredicate eventPredicate = new EventNameEqualKeywordPredicate(event.getEventName());

        RemovePersonFromEventCommand command = new RemovePersonFromEventCommand(personPredicate, eventPredicate);

        assertThrows(CommandException.class,
                String.format(MESSAGE_PERSON_DETAILS_NOT_FOUND, personPredicate), () -> command.execute(model));
    }

    @Test
    public void execute_eventNotFound_throwsCommandException() {
        Event event = new EventBuilder().withEventName(VALID_EVENT_NAME_PARTY).build();

        NameEqualKeywordPredicate personPredicate = new NameEqualKeywordPredicate(BOB.getName());
        EventNameEqualKeywordPredicate eventPredicate = new EventNameEqualKeywordPredicate(event.getEventName());

        RemovePersonFromEventCommand command = new RemovePersonFromEventCommand(personPredicate, eventPredicate);

        assertThrows(CommandException.class,
                String.format(MESSAGE_EVENT_DETAILS_NOT_FOUND, eventPredicate), () -> command.execute(model));
    }

    @Test
    public void execute_predicatesValid_success() throws CommandException {
        Event event = new EventBuilder().withEventName(VALID_EVENT_NAME_PARTY)
                .withPerson(BOB).build();
        model.addEvent(event);

        NameEqualKeywordPredicate personPredicate = new NameEqualKeywordPredicate(BOB.getName());
        EventNameEqualKeywordPredicate eventPredicate = new EventNameEqualKeywordPredicate(event.getEventName());

        RemovePersonFromEventCommand command = new RemovePersonFromEventCommand(personPredicate, eventPredicate);

        CommandResult commandResult = command.execute(model);

        String expectedMessage = String.format(RemovePersonFromEventCommand.MESSAGE_SUCCESS,
                BOB.getName().fullName, event.getEventName());

        assertEquals(expectedMessage, commandResult.getFeedbackToUser());
    }
}
