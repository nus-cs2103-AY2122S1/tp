package seedu.plannermd.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.plannermd.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
import static seedu.plannermd.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static seedu.plannermd.testutil.Assert.assertThrows;
import static seedu.plannermd.testutil.TypicalPlannerMd.getTypicalPlannerMd;
import static seedu.plannermd.testutil.doctor.TypicalDoctors.DR_ALICE;
import static seedu.plannermd.testutil.patient.TypicalPatients.ALICE;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.plannermd.model.doctor.Doctor;
import seedu.plannermd.model.patient.Patient;
import seedu.plannermd.model.person.exceptions.DuplicatePersonException;
import seedu.plannermd.testutil.doctor.DoctorBuilder;
import seedu.plannermd.testutil.patient.PatientBuilder;

public class PlannerMdTest {

    private final PlannerMd plannerMd = new PlannerMd();

    @Test
    public void constructor() {
        assertEquals(Collections.emptyList(), plannerMd.getPatientList());
        assertEquals(Collections.emptyList(), plannerMd.getDoctorList());
    }

    @Test
    public void resetData_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> plannerMd.resetData(null));
    }

    @Test
    public void resetData_withValidReadOnlyPlannerMd_replacesData() {
        PlannerMd newData = getTypicalPlannerMd();
        plannerMd.resetData(newData);
        assertEquals(newData, plannerMd);
    }

    @Test
    public void resetData_withDuplicatePerson_throwsDuplicatePersonException() {
        // Two persons with the same identity fields
        Patient editedAlice = new PatientBuilder(ALICE).withAddress(VALID_ADDRESS_BOB).withTags(VALID_TAG_HUSBAND)
                .build();
        List<Patient> newPatients = Arrays.asList(ALICE, editedAlice);

        PlannerMdStub newData = new PlannerMdStub().setPatients(newPatients);

        assertThrows(DuplicatePersonException.class, () -> plannerMd.resetData(newData));

        // Two doctors with the same identity fields
        Doctor editedDoctorAlice = new DoctorBuilder(DR_ALICE).withAddress(VALID_ADDRESS_BOB)
                .withTags(VALID_TAG_HUSBAND)
                .build();
        List<Doctor> newDoctors = Arrays.asList(DR_ALICE, editedDoctorAlice);

        PlannerMdStub newDoctorData = new PlannerMdStub().setDoctors(newDoctors);

        assertThrows(DuplicatePersonException.class, () -> plannerMd.resetData(newDoctorData));
    }


    @Test
    public void hasPerson_nullPerson_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> plannerMd.hasPatient(null));
        assertThrows(NullPointerException.class, () -> plannerMd.hasDoctor(null));
    }

    @Test
    public void hasPerson_personNotInPlannerMd_returnsFalse() {
        assertFalse(plannerMd.hasPatient(ALICE));
        assertFalse(plannerMd.hasDoctor(DR_ALICE));
    }

    @Test
    public void hasPerson_personInPlannerMd_returnsTrue() {
        plannerMd.addPatient(ALICE);
        assertTrue(plannerMd.hasPatient(ALICE));

        plannerMd.addDoctor(DR_ALICE);
        assertTrue(plannerMd.hasDoctor(DR_ALICE));
    }

    @Test
    public void hasPerson_personWithSameIdentityFieldsInPlannerMd_returnsTrue() {
        plannerMd.addPatient(ALICE);
        Patient editedAlice = new PatientBuilder(ALICE).withAddress(VALID_ADDRESS_BOB).withTags(VALID_TAG_HUSBAND)
                .build();
        assertTrue(plannerMd.hasPatient(editedAlice));

        plannerMd.addDoctor(DR_ALICE);
        Doctor editedDoctor = new DoctorBuilder(DR_ALICE).withAddress(VALID_ADDRESS_BOB).withTags(VALID_TAG_HUSBAND)
                .build();
        assertTrue(plannerMd.hasDoctor(editedDoctor));
    }

    @Test
    public void getPersonList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> plannerMd.getPatientList().remove(0));
        assertThrows(UnsupportedOperationException.class, () -> plannerMd.getDoctorList().remove(0));
    }

    /**
     * A stub ReadOnlyPlannerMd whose persons list can violate interface constraints.
     */
    private static class PlannerMdStub implements ReadOnlyPlannerMd {
        private final ObservableList<Patient> patients = FXCollections.observableArrayList();
        private final ObservableList<Doctor> doctors = FXCollections.observableArrayList();

        PlannerMdStub() {
            this.patients.setAll(patients);
            this.doctors.setAll(doctors);
        }

        public PlannerMdStub setPatients(Collection<Patient> patients) {
            this.patients.setAll(patients);
            return this;
        }

        public PlannerMdStub setDoctors(Collection<Doctor> doctors) {
            this.doctors.setAll(doctors);
            return this;
        }

        @Override
        public ObservableList<Patient> getPatientList() {
            return patients;
        }

        @Override
        public ObservableList<Doctor> getDoctorList() {
            return doctors;
        }
    }

}
