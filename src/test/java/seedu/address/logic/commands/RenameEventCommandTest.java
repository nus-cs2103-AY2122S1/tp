package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EVENT_NAME_DINNER;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EVENT_NAME_PARTY;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_EVENT;
import static seedu.address.testutil.TypicalPersons.BOB;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.event.Event;
import seedu.address.testutil.Assert;
import seedu.address.testutil.EventBuilder;
import seedu.address.testutil.RenameEventDescriptorBuilder;

/**
 * Contains integration tests (interaction with the Model) and unit tests for RenameEventCommand.
 */
public class RenameEventCommandTest {

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void execute_newNameSpecified_success() throws CommandException {
        Event originalEvent = new EventBuilder().withEventName(VALID_EVENT_NAME_PARTY).withPerson(BOB).build();
        Event renamedEvent = new EventBuilder().withEventName(VALID_EVENT_NAME_DINNER).withPerson(BOB).build();
        model.addEvent(originalEvent);
        RenameEventCommand.RenameEventDescriptor descriptor = new RenameEventDescriptorBuilder(renamedEvent).build();
        RenameEventCommand renameEventCommand = new RenameEventCommand(INDEX_FIRST_EVENT, descriptor);

        String expectedMessage = String.format(
                RenameEventCommand.MESSAGE_RENAME_EVENT_SUCCESS,
                originalEvent.getEventName(),
                renamedEvent.getEventName());

        CommandResult commandResult = renameEventCommand.execute(model);

        assertEquals(expectedMessage, commandResult.getFeedbackToUser());
    }

    @Test
    public void execute_duplicateEvent_throwsCommandException() {
        Event originalEvent = new EventBuilder().withEventName("Original").withPerson(BOB).build();
        Event renamedEvent = new EventBuilder().withEventName("Renamed").withPerson(BOB).build();
        model.addEvent(originalEvent);
        model.addEvent(renamedEvent);
        RenameEventCommand.RenameEventDescriptor descriptor = new RenameEventDescriptorBuilder(renamedEvent).build();
        RenameEventCommand renameEventCommand = new RenameEventCommand(INDEX_FIRST_EVENT, descriptor);

        Assert.assertThrows(CommandException.class,
                RenameEventCommand.MESSAGE_DUPLICATE_EVENT, () -> renameEventCommand.execute(model));
    }

}
