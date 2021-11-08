package seedu.plannermd.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.plannermd.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
import static seedu.plannermd.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static seedu.plannermd.model.Model.PREDICATE_SHOW_ALL_PERSONS;
import static seedu.plannermd.testutil.Assert.assertThrows;
import static seedu.plannermd.testutil.appointment.TypicalAppointments.ANOTHER_TWO_HOUR_APPOINTMENT;
import static seedu.plannermd.testutil.appointment.TypicalAppointments.TWO_HOUR_APPOINTMENT;
import static seedu.plannermd.testutil.doctor.TypicalDoctors.DR_ALICE;
import static seedu.plannermd.testutil.doctor.TypicalDoctors.DR_BENSON;
import static seedu.plannermd.testutil.patient.TypicalPatients.ALICE;
import static seedu.plannermd.testutil.patient.TypicalPatients.BENSON;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;

import org.junit.jupiter.api.Test;

import seedu.plannermd.commons.core.GuiSettings;
import seedu.plannermd.model.Model.State;
import seedu.plannermd.model.appointment.Appointment;
import seedu.plannermd.model.doctor.Doctor;
import seedu.plannermd.model.patient.Patient;
import seedu.plannermd.model.person.NameContainsKeywordsPredicate;
import seedu.plannermd.testutil.PlannerMdBuilder;
import seedu.plannermd.testutil.appointment.AppointmentBuilder;
import seedu.plannermd.testutil.doctor.DoctorBuilder;
import seedu.plannermd.testutil.patient.PatientBuilder;

public class ModelManagerTest {

    private ModelManager modelManager = new ModelManager();

    @Test
    public void constructor() {
        assertEquals(new UserPrefs(), modelManager.getUserPrefs());
        assertEquals(new GuiSettings(), modelManager.getGuiSettings());
        assertEquals(State.PATIENT, modelManager.getState());
        assertEquals(new PlannerMd(), new PlannerMd(modelManager.getPlannerMd()));
    }

    @Test
    public void setUserPrefs_nullUserPrefs_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.setUserPrefs(null));
    }

    @Test
    public void setUserPrefs_validUserPrefs_copiesUserPrefs() {
        UserPrefs userPrefs = new UserPrefs();
        userPrefs.setPlannerMdFilePath(Paths.get("address/book/file/path"));
        userPrefs.setGuiSettings(new GuiSettings(1, 2, 3, 4));
        modelManager.setUserPrefs(userPrefs);
        assertEquals(userPrefs, modelManager.getUserPrefs());

        // Modifying userPrefs should not modify modelManager's userPrefs
        UserPrefs oldUserPrefs = new UserPrefs(userPrefs);
        userPrefs.setPlannerMdFilePath(Paths.get("new/address/book/file/path"));
        assertEquals(oldUserPrefs, modelManager.getUserPrefs());
    }

    @Test
    public void setGuiSettings_nullGuiSettings_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.setGuiSettings(null));
    }

    @Test
    public void setGuiSettings_validGuiSettings_setsGuiSettings() {
        GuiSettings guiSettings = new GuiSettings(1, 2, 3, 4);
        modelManager.setGuiSettings(guiSettings);
        assertEquals(guiSettings, modelManager.getGuiSettings());
    }

    @Test
    public void setPlannerMdFilePath_nullPath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.setPlannerMdFilePath(null));
    }

    @Test
    public void setPlannerMdFilePath_validPath_setsPlannerMdFilePath() {
        Path path = Paths.get("address/book/file/path");
        modelManager.setPlannerMdFilePath(path);
        assertEquals(path, modelManager.getPlannerMdFilePath());
    }

    @Test
    public void hasPerson_nullPerson_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.hasPatient(null));
        assertThrows(NullPointerException.class, () -> modelManager.hasDoctor(null));
    }

    @Test
    public void hasPerson_personNotInPlannerMd_returnsFalse() {
        assertFalse(modelManager.hasPatient(ALICE));
        assertFalse(modelManager.hasDoctor(DR_ALICE));
    }

    @Test
    public void hasPerson_personInPlannerMd_returnsTrue() {
        modelManager.addPatient(ALICE);
        assertTrue(modelManager.hasPatient(ALICE));

        modelManager.addDoctor(DR_ALICE);
        assertTrue(modelManager.hasDoctor(DR_ALICE));
    }

    @Test
    public void deleteDoctor_doesNotDeletePatientWithSameName_returnsTrue() {
        modelManager.addPatient(ALICE);
        modelManager.addDoctor(DR_ALICE);
        modelManager.deleteDoctor(DR_ALICE);
        assertFalse(modelManager.hasDoctor(DR_ALICE));
        assertTrue(modelManager.hasPatient(ALICE));
    }

    @Test
    public void deletePatient_doesNotDeleteDoctorWithSameName_returnsTrue() {
        modelManager.addPatient(ALICE);
        modelManager.addDoctor(DR_ALICE);
        modelManager.deletePatient(ALICE);
        assertFalse(modelManager.hasPatient(ALICE));
        assertTrue(modelManager.hasDoctor(DR_ALICE));
    }

    @Test
    public void getFilteredPersonList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> modelManager.getFilteredPatientList().remove(0));
        assertThrows(UnsupportedOperationException.class, () -> modelManager.getFilteredDoctorList().remove(0));
    }

    @Test
    public void getFilteredAppointmentList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> modelManager.getFilteredAppointmentList().remove(0));
    }

    @Test
    public void deleteAppointmentsWithPerson_deletePerson_appointmentsDeleted() {
        modelManager.addAppointment(TWO_HOUR_APPOINTMENT);
        modelManager.addAppointment(ANOTHER_TWO_HOUR_APPOINTMENT);

        modelManager.deleteAppointmentsWithPerson(ALICE);
        assertFalse(modelManager.hasAppointment(TWO_HOUR_APPOINTMENT));

        modelManager.deleteAppointmentsWithPerson(DR_ALICE);
        assertFalse(modelManager.hasAppointment(ANOTHER_TWO_HOUR_APPOINTMENT));
    }

    @Test
    public void editAppointmentsWithPerson_editPerson_appointmentsEdited() {
        Patient editedAlice = new PatientBuilder(ALICE).withAddress(VALID_ADDRESS_BOB).withTags(VALID_TAG_HUSBAND)
                .build();
        Appointment editedPatientAppointment = new AppointmentBuilder(TWO_HOUR_APPOINTMENT).withPatient(editedAlice)
                .build();

        Doctor editedDoctor = new DoctorBuilder(DR_ALICE).withAddress(VALID_ADDRESS_BOB).withTags(VALID_TAG_HUSBAND)
                .build();
        Appointment editedDoctorAppointment = new AppointmentBuilder(ANOTHER_TWO_HOUR_APPOINTMENT)
                .withDoctor(editedDoctor).build();

        modelManager.addAppointment(TWO_HOUR_APPOINTMENT);
        modelManager.addAppointment(ANOTHER_TWO_HOUR_APPOINTMENT);

        modelManager.editAppointmentsWithPerson(ALICE, editedAlice);

        assertTrue(modelManager.hasAppointment(editedPatientAppointment));
        assertFalse(modelManager.hasAppointment(TWO_HOUR_APPOINTMENT));

        modelManager.editAppointmentsWithPerson(DR_ALICE, editedDoctor);

        assertTrue(modelManager.hasAppointment(editedDoctorAppointment));
        assertFalse(modelManager.hasAppointment(ANOTHER_TWO_HOUR_APPOINTMENT));
    }

    @Test
    public void equals() {
        PlannerMd plannerMd = new PlannerMdBuilder()
                .withPatient(ALICE).withPatient(BENSON)
                .withDoctor(DR_ALICE).withDoctor(DR_BENSON).build();

        PlannerMd differentPlannerMd = new PlannerMd();
        UserPrefs userPrefs = new UserPrefs();

        // same values -> returns true
        modelManager = new ModelManager(plannerMd, userPrefs);
        ModelManager modelManagerCopy = new ModelManager(plannerMd, userPrefs);
        assertTrue(modelManager.equals(modelManagerCopy));

        // same object -> returns true
        assertTrue(modelManager.equals(modelManager));

        // null -> returns false
        assertFalse(modelManager.equals(null));

        // different types -> returns false
        assertFalse(modelManager.equals(5));

        // different plannerMd -> returns false
        assertFalse(modelManager.equals(new ModelManager(differentPlannerMd, userPrefs)));

        // different filteredPatientList -> returns false
        String[] keywords = ALICE.getName().fullName.split("\\s+");
        modelManager.updateFilteredPatientList(new NameContainsKeywordsPredicate(Arrays.asList(keywords)));
        assertFalse(modelManager.equals(new ModelManager(plannerMd, userPrefs)));

        // resets modelManager to initial state for upcoming tests
        modelManager.updateFilteredPatientList(PREDICATE_SHOW_ALL_PERSONS);

        // different filteredDoctorList -> returns false
        String[] keywordsDoctor = DR_ALICE.getName().fullName.split("\\s+");
        modelManager.updateFilteredDoctorList(new NameContainsKeywordsPredicate(Arrays.asList(keywordsDoctor)));
        assertFalse(modelManager.equals(new ModelManager(plannerMd, userPrefs)));

        // resets modelManager to initial state for upcoming tests
        modelManager.updateFilteredDoctorList(PREDICATE_SHOW_ALL_PERSONS);

        // different userPrefs -> returns false
        UserPrefs differentUserPrefs = new UserPrefs();
        differentUserPrefs.setPlannerMdFilePath(Paths.get("differentFilePath"));
        assertFalse(modelManager.equals(new ModelManager(plannerMd, differentUserPrefs)));
    }
}
