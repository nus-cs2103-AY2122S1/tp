package seedu.track2gather.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static seedu.track2gather.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.track2gather.logic.commands.CommandTestUtil.showPersonAtIndex;
import static seedu.track2gather.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.track2gather.testutil.TypicalPersons.ALICE;
import static seedu.track2gather.testutil.TypicalPersons.BOB;
import static seedu.track2gather.testutil.TypicalPersons.getTypicalTrack2Gather;

import java.util.Collections;

import org.junit.jupiter.api.Test;

import seedu.track2gather.model.Model;
import seedu.track2gather.model.ModelManager;
import seedu.track2gather.model.UserPrefs;
import seedu.track2gather.model.person.Person;
import seedu.track2gather.testutil.TestUtil;

public class ScheduleCommandTest {
    private Model model = new ModelManager(getTypicalTrack2Gather(), new UserPrefs());
    private Model expectedModel = new ModelManager(model.getTrack2Gather(), new UserPrefs());

    @Test
    public void execute_emptyTrack2Gather_success() {
        ScheduleCommand command = new ScheduleCommand();
        String expectedMessage = String.format(ScheduleCommand.MESSAGE_SUCCESS);
        Model model = new ModelManager();
        Model expectedModel = new ModelManager();

        assertCommandSuccess(command, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_scheduleAllNonCalledUnfilteredList_showsSameSchedule() {
        model.resetAllCallStatuses();
        expectedModel.resetAllCallStatuses();
        assertCommandSuccess(new ScheduleCommand(), model, ScheduleCommand.MESSAGE_SUCCESS, expectedModel);
    }

    @Test
    public void execute_scheduleAllNonCalledFilteredList_showsAllUncalled() {
        model.resetAllCallStatuses();
        expectedModel.resetAllCallStatuses();
        showPersonAtIndex(model, INDEX_FIRST_PERSON);
        assertCommandSuccess(new ScheduleCommand(), model, ScheduleCommand.MESSAGE_SUCCESS, expectedModel);
    }

    @Test
    public void execute_differentCallStatusUnfilteredList_showsDifferentSchedule() {
        // Contains one called person
        Person personToCall = TestUtil.getPerson(model, INDEX_FIRST_PERSON);
        Person calledPerson = new Person(personToCall, personToCall.getCallStatus().call());
        model.setPerson(personToCall, calledPerson);

        // All persons non-called
        expectedModel.resetAllCallStatuses();

        ScheduleCommand command = new ScheduleCommand();
        command.execute(model);
        command.execute(expectedModel);

        assertFalse(model.getFilteredPersonList().equals(expectedModel.getFilteredPersonList()));
    }

    @Test
    public void execute_differentCallStatusFilteredList_showsDifferentSchedule() {
        showPersonAtIndex(model, INDEX_FIRST_PERSON);

        // Contains one called person
        Person personToCall = TestUtil.getPerson(model, INDEX_FIRST_PERSON);
        Person calledPerson = new Person(personToCall, personToCall.getCallStatus().call());
        model.setPerson(personToCall, calledPerson);

        // All persons non-called
        expectedModel.resetAllCallStatuses();

        ScheduleCommand command = new ScheduleCommand();
        command.execute(model);
        command.execute(expectedModel);

        assertFalse(model.getFilteredPersonList().equals(expectedModel.getFilteredPersonList()));
    }

    @Test
    public void execute_allCalledUnfilteredList_showEmptySchedule() {
        Model model = new ModelManager();
        Model expectedModel = new ModelManager();
        // Contains one called person only
        model.addPerson(new Person(BOB, BOB.getCallStatus().call()));

        ScheduleCommand command = new ScheduleCommand();
        command.execute(model);

        assertEquals(Collections.emptyList(), model.getFilteredPersonList());
        assertEquals(expectedModel.getFilteredPersonList(), model.getFilteredPersonList());
    }

    @Test
    public void execute_allCalledFilteredList_showEmptySchedule() {
        Model model = new ModelManager();
        Model expectedModel = new ModelManager();
        // Contains called persons only
        model.addPerson(new Person(BOB, BOB.getCallStatus().call()));
        model.addPerson(new Person(ALICE, ALICE.getCallStatus().call()));
        showPersonAtIndex(model, INDEX_FIRST_PERSON);

        ScheduleCommand command = new ScheduleCommand();
        command.execute(model);

        assertEquals(Collections.emptyList(), model.getFilteredPersonList());
        assertEquals(expectedModel.getFilteredPersonList(), model.getFilteredPersonList());
    }
}
