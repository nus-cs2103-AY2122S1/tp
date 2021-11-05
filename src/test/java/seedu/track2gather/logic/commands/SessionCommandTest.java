package seedu.track2gather.logic.commands;

import static seedu.track2gather.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.track2gather.logic.commands.CommandTestUtil.showPersonAtIndex;
import static seedu.track2gather.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.track2gather.testutil.TypicalPersons.getTypicalTrack2Gather;

import org.junit.jupiter.api.Test;

import seedu.track2gather.model.Model;
import seedu.track2gather.model.ModelManager;
import seedu.track2gather.model.UserPrefs;
import seedu.track2gather.model.person.Person;
import seedu.track2gather.testutil.TestUtil;

public class SessionCommandTest {
    private Model expectedModel = new ModelManager(getTypicalTrack2Gather(), new UserPrefs());
    private Model model = new ModelManager(getTypicalTrack2Gather(), new UserPrefs());

    @Test
    public void execute_emptyTrack2Gather_success() {
        SessionCommand command = new SessionCommand();
        String expectedMessage = String.format(SessionCommand.MESSAGE_SUCCESS);
        Model model = new ModelManager();
        Model expectedModel = new ModelManager();

        assertCommandSuccess(command, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_sessionUnfilteredList_showsSameSession() {
        expectedModel.resetAllCallStatuses();
        assertCommandSuccess(new SessionCommand(), model, SessionCommand.MESSAGE_SUCCESS, expectedModel);
    }

    @Test
    public void execute_sessionFilteredList_showsEverything() {
        showPersonAtIndex(model, INDEX_FIRST_PERSON);
        expectedModel.resetAllCallStatuses();
        assertCommandSuccess(new SessionCommand(), model, SessionCommand.MESSAGE_SUCCESS, expectedModel);
    }

    @Test
    public void execute_differentCallStatusUnfilteredList_showsSameSession() {
        // Contains one called person
        Person personToCall = TestUtil.getPerson(model, INDEX_FIRST_PERSON);
        Person calledPerson = new Person(personToCall, personToCall.getCallStatus().call());
        model.setPerson(personToCall, calledPerson);

        // All persons non-called
        expectedModel.resetAllCallStatuses();

        assertCommandSuccess(new SessionCommand(), model, SessionCommand.MESSAGE_SUCCESS, expectedModel);
    }

    @Test
    public void execute_differentCallStatusFilteredList_showsSameSession() {
        showPersonAtIndex(model, INDEX_FIRST_PERSON);

        // Contains one called person
        Person personToCall = TestUtil.getPerson(model, INDEX_FIRST_PERSON);
        Person calledPerson = new Person(personToCall, personToCall.getCallStatus().call());
        model.setPerson(personToCall, calledPerson);

        // All persons non-called
        expectedModel.resetAllCallStatuses();

        assertCommandSuccess(new SessionCommand(), model, SessionCommand.MESSAGE_SUCCESS, expectedModel);
    }
}
