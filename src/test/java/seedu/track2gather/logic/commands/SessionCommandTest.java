package seedu.track2gather.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.track2gather.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.track2gather.logic.commands.CommandTestUtil.showPersonAtIndex;
import static seedu.track2gather.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.track2gather.testutil.TypicalPersons.BOB;
import static seedu.track2gather.testutil.TypicalPersons.getTypicalTrack2Gather;

import org.junit.jupiter.api.Test;

import seedu.track2gather.model.Model;
import seedu.track2gather.model.ModelManager;
import seedu.track2gather.model.UserPrefs;
import seedu.track2gather.model.person.Person;
import seedu.track2gather.testutil.TestUtil;

public class SessionCommandTest {

    @Test
    public void execute_emptyTrack2Gather_success() {
        SessionCommand command = new SessionCommand();
        String expectedMessage = String.format(SessionCommand.MESSAGE_SUCCESS);
        Model model = new ModelManager();
        Model expectedModel = new ModelManager();

        assertCommandSuccess(command, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_unfilteredList_success() {
        Model model = new ModelManager(getTypicalTrack2Gather(), new UserPrefs());
        Model expectedModel = new ModelManager(model.getTrack2Gather(), new UserPrefs());
        expectedModel.resetAllCallStatuses();

        assertCommandSuccess(new SessionCommand(), model, SessionCommand.MESSAGE_SUCCESS, expectedModel);
    }

    @Test
    public void execute_filteredList_success() {
        Model model = new ModelManager(getTypicalTrack2Gather(), new UserPrefs());
        Model expectedModel = new ModelManager(model.getTrack2Gather(), new UserPrefs());
        expectedModel.resetAllCallStatuses();
        showPersonAtIndex(model, INDEX_FIRST_PERSON);

        assertCommandSuccess(new SessionCommand(), model, SessionCommand.MESSAGE_SUCCESS, expectedModel);
    }

    @Test
    public void execute_differentCallStatusUnfilteredList_success() {
        Model model = new ModelManager(getTypicalTrack2Gather(), new UserPrefs());
        Model expectedModel = new ModelManager(model.getTrack2Gather(), new UserPrefs());

        // Contains one called person
        Person personToCall = TestUtil.getPerson(model, INDEX_FIRST_PERSON);
        Person calledPerson = new Person(personToCall, personToCall.getCallStatus().call());
        model.setPerson(personToCall, calledPerson);

        // All persons non-called
        expectedModel.resetAllCallStatuses();

        assertCommandSuccess(new SessionCommand(), model, SessionCommand.MESSAGE_SUCCESS, expectedModel);
    }

    @Test
    public void execute_differentCallStatusFilteredList_success() {
        Model model = new ModelManager(getTypicalTrack2Gather(), new UserPrefs());
        Model expectedModel = new ModelManager(model.getTrack2Gather(), new UserPrefs());
        showPersonAtIndex(model, INDEX_FIRST_PERSON);

        // Contains one called person
        Person personToCall = TestUtil.getPerson(model, INDEX_FIRST_PERSON);
        Person calledPerson = new Person(personToCall, personToCall.getCallStatus().call());
        model.setPerson(personToCall, calledPerson);

        // All persons non-called
        expectedModel.resetAllCallStatuses();

        assertCommandSuccess(new SessionCommand(), model, SessionCommand.MESSAGE_SUCCESS, expectedModel);
    }

    @Test
    public void execute_resetAllCallStatusAndShowAllPersons() {
        Model model = new ModelManager(getTypicalTrack2Gather(), new UserPrefs());
        Model expectedModel = new ModelManager(model.getTrack2Gather(), new UserPrefs());

        // One person called
        model.addPerson(new Person(BOB, BOB.getCallStatus().call()));
        expectedModel.addPerson(new Person(BOB, BOB.getCallStatus().call()));

        SessionCommand command = new SessionCommand();
        command.execute(model);

        expectedModel.resetAllCallStatuses();
        expectedModel.updateFilteredPersonList(Model.PREDICATE_SHOW_NON_CALLED);

        assertEquals(model.getFilteredPersonList(), expectedModel.getFilteredPersonList());
    }
}
