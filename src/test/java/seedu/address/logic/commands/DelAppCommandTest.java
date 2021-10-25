package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.*;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalAppointment.getTypicalSchedule;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.logic.Logic;
import seedu.address.logic.LogicManager;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.Person;
import seedu.address.model.schedule.Appointment;
import seedu.address.storage.Storage;
import seedu.address.storage.StorageManager;
import seedu.address.testutil.UiStub;
import seedu.address.ui.Ui;
import seedu.address.ui.UiManager;

class DelAppCommandTest {
    private Model model;
    private Model expectedModel;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalAddressBook(), new UserPrefs(), getTypicalSchedule());
        expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs(), model.getSchedule());
        Ui uiStub = new UiStub();
    }

    @Test
    public void execute_validIndexUnfilteredList_success() {
        Appointment appToDelete = model.getFilteredAppointmentList().get(INDEX_FIRST_PERSON.getZeroBased());
        DelAppCommand delAppCommand = new DelAppCommand(INDEX_FIRST_PERSON);

        String expectedMessage = String.format(DelAppCommand.MESSAGE_SUCCESS, appToDelete);

        ModelManager expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs(), model.getSchedule());
        expectedModel.deleteAppointment(appToDelete);

        assertCommandSuccess(delAppCommand, model, expectedMessage, expectedModel);
    }
}