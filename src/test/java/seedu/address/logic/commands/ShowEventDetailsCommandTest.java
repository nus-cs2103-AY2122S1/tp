package seedu.address.logic.commands;

import static seedu.address.commons.core.Messages.MESSAGE_EVENT_NOT_FOUND;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalParticipants.getTypicalAddressBook;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.event.EventNamePredicate;

public class ShowEventDetailsCommandTest {

    private final Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void constructor_nullPerson_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new ShowEventDetailsCommand(null));
    }

    @Test
    public void execute_eventNotInList_throwsCommandException() {
        EventNamePredicate predicate = preparePredicate(" ");
        ShowEventDetailsCommand command = new ShowEventDetailsCommand(predicate);
        assertThrows(CommandException.class, MESSAGE_EVENT_NOT_FOUND, () -> command.execute(model));
    }

    /* Prior to implementation of addParticipant functionality, no way of manually adding Event to Event list without
    direct access to AddressBook class, which causes unwanted dependency. Test to be implemented after addParticipant is
    implemented. */
    @Test
    public void execute_noUiChange_showDetailsSuccessful() throws CommandException {
    }

    /* Prior to implementation of addParticipant functionality, no way of manually adding Event to Event list without
    direct access to AddressBook class, which causes unwanted dependency. Test to be implemented after addParticipant is
    implemented. */
    @Test
    public void execute_eventInList_showDetailsSuccessful() throws CommandException {
    }

    /**
     * Parses {@code userInput} into a {@code EventNamePredicate}.
     */
    private EventNamePredicate preparePredicate(String userInput) {
        return new EventNamePredicate(userInput.trim().replaceAll("\\s+", " "));
    }
}
