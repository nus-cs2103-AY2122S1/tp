package seedu.address.logic.commands;

import org.junit.jupiter.api.Test;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.event.EventNamePredicate;

import static seedu.address.commons.core.Messages.MESSAGE_EVENT_NOT_FOUND;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

public class ShowDetailsCommandTest {

    private final Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void constructor_nullPerson_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new ShowDetailsCommand(null));
    }

    @Test
    public void execute_eventNotInList_throwsCommandException() {
        EventNamePredicate predicate = preparePredicate(" ");
        ShowDetailsCommand command = new ShowDetailsCommand(predicate);
        assertThrows(CommandException.class, MESSAGE_EVENT_NOT_FOUND, () -> command.execute(model));
    }

    /* Prior to implementation of addParticipant functionality, no way of manually adding Event to Event list without
    direct access to AddressBook class, which causes unwanted dependency. Test can be used after addParticipant is
    implemented. */
    @Test
    public void execute_noUiChange_showDetailsSuccessful() throws CommandException {
//        Event testEvent = new Event(new EventName("Amogus Meeting"), new EventDate("2021-07-27"), new EventTime("0727"));
//        EventNamePredicate predicate = preparePredicate(testEvent.getName().eventName);
//        ShowDetailsCommand command = new ShowDetailsCommand(predicate);
//        command.execute(model);
//        assertEquals(model.getEventList(), expectedModel.getEventList());
    }

    /* Prior to implementation of addParticipant functionality, no way of manually adding Event to Event list without
    direct access to AddressBook class, which causes unwanted dependency. Test can be used after addParticipant is
    implemented. */
    @Test
    public void execute_eventInList_showDetailsSuccessful() throws CommandException {
//        Event testEvent = model.getEventList().get(0);
//        String expectedMessage = String.format("Event Name: %s\nEvent Date: %s\nEvent Time: %s",
//                testEvent.getName(), testEvent.getDate(), testEvent.getTime());
//        EventNamePredicate predicate = preparePredicate(testEvent.getName().eventName);
//        ShowDetailsCommand command = new ShowDetailsCommand(predicate);
//        assertEquals(command.execute(model).getFeedbackToUser(), expectedMessage);
    }

    /**
     * Parses {@code userInput} into a {@code EventNamePredicate}.
     */
    private EventNamePredicate preparePredicate(String userInput) {
        return new EventNamePredicate(userInput.trim().replaceAll("\\s+", " "));
    }
}
