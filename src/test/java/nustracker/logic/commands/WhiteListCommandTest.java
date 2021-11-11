package nustracker.logic.commands;

import static java.util.Objects.requireNonNull;
import static nustracker.commons.core.Messages.MESSAGE_INVALID_EVENT_NAME;
import static nustracker.commons.util.CollectionUtil.requireAllNonNull;
import static nustracker.testutil.Assert.assertThrows;
import static nustracker.testutil.TypicalAddressBook.getTypicalAddressBook;
import static nustracker.testutil.TypicalEvents.EVENTNAME_ONE;
import static nustracker.testutil.TypicalEvents.ORIENTATION;
import static nustracker.testutil.TypicalStudents.STUDENTID_ONE;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import nustracker.logic.commands.exceptions.CommandException;
import nustracker.model.AddressBook;
import nustracker.model.Model;
import nustracker.model.ModelManager;
import nustracker.model.ReadOnlyAddressBook;
import nustracker.model.UserPrefs;
import nustracker.model.event.Event;
import nustracker.model.event.EventName;
import nustracker.model.event.Participant;
import nustracker.model.student.StudentId;
import nustracker.testutil.EventBuilder;
import nustracker.testutil.ModelStub;
import nustracker.ui.MainWindow.CurrentlyShownList;

public class WhiteListCommandTest {

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void constructor_nullParameters_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new WhiteListCommand(null, null));
        assertThrows(NullPointerException.class, () -> new WhiteListCommand(
                new StudentId("e1234567"), null));
        assertThrows(NullPointerException.class, () -> new WhiteListCommand(
                null, new EventName("Valid event name")));
    }

    @Test
    public void execute_whitelistAccepted_removeSuccessful() throws Exception {

        Participant blacklisted = (Participant) ORIENTATION.getBlacklist().toArray()[0];
        CommandResult commandResult = new WhiteListCommand(blacklisted.getStudentId(),
                ORIENTATION.getName()).execute(model, CurrentlyShownList.STUDENTS_LIST);

        Assertions.assertEquals(String.format(WhiteListCommand.MESSAGE_WHITELIST_SUCCESS,
                        blacklisted.getStudentId(), EVENTNAME_ONE),
                commandResult.getFeedbackToUser());

        // Change blacklist of event in modelStub
        ModelStubSetEventBlacklist modelStub = new ModelStubSetEventBlacklist();
        ArrayList<String> newBlacklist =
                new ArrayList<>(ORIENTATION
                        .getBlacklist().stream().map(Participant::toString).collect(Collectors.toList()));
        newBlacklist.remove(blacklisted.getStudentId().toString());
        modelStub.setBlacklist(ORIENTATION, newBlacklist);

        Assertions.assertEquals(modelStub.addressBook, model.getAddressBook());
    }

    @Test
    public void execute_whitelistRejected_studentIdNotBlacklisted() throws Exception {
        StudentId nonExistentStudentId = new StudentId("e0000000");

        assertThrows(CommandException.class,
                String.format(WhiteListCommand.MESSAGE_STUDENTID_NOT_BLACKLISTED,
                        nonExistentStudentId, ORIENTATION.getName()), () ->
                        new WhiteListCommand(nonExistentStudentId, ORIENTATION.getName()).execute(model,
                                CurrentlyShownList.STUDENTS_LIST));
    }

    @Test
    public void execute_whitelistRejected_eventDoesNotExist() throws Exception {
        EventName nonExistentEventName = new EventName("This event does not exist");

        assertThrows(CommandException.class,
            String.format(MESSAGE_INVALID_EVENT_NAME, nonExistentEventName.toString()), () ->
                        new WhiteListCommand(STUDENTID_ONE, nonExistentEventName).execute(model,
                                CurrentlyShownList.STUDENTS_LIST)
        );
    }

    /**
     * A Model stub that always sets the blacklist of the event to be edited.
     */
    private class ModelStubSetEventBlacklist extends ModelStub {
        final AddressBook addressBook = getTypicalAddressBook();

        @Override
        public void setEvent(Event target, Event editedEvent) {
            requireAllNonNull(target, editedEvent);

            addressBook.setEvent(target, editedEvent);
        }

        public void setBlacklist(Event target, List<String> blacklist) {
            Event withNewBlacklist = new EventBuilder(target)
                    .withBlacklist(blacklist.toArray(new String[0]))
                    .build();
            setEvent(target, withNewBlacklist);
        }

        @Override
        public Event getEvent(EventName name) {
            requireNonNull(name);

            return addressBook.getEvent(name);
        }

        @Override
        public ReadOnlyAddressBook getAddressBook() {
            return new AddressBook();
        }
    }

    @Test
    void equals() {
        WhiteListCommand whiteListCommand = new WhiteListCommand(
                new StudentId("e1234567"), new EventName("test event"));

        assertTrue(whiteListCommand.equals(whiteListCommand));
        assertTrue(whiteListCommand.equals(new WhiteListCommand(
                new StudentId("e1234567"), new EventName("test event"))));

        assertFalse(whiteListCommand.equals(null));
        assertFalse(whiteListCommand.equals(new WhiteListCommand(
                new StudentId("e7654321"), new EventName("event test"))));
        assertFalse(whiteListCommand.equals(new BlackListCommand(
                new StudentId("e1234567"), new EventName("test event"))));
    }
}
