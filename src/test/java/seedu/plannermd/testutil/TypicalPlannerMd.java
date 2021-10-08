package seedu.plannermd.testutil;

import static seedu.plannermd.testutil.doctor.TypicalDoctors.getTypicalDoctors;
import static seedu.plannermd.testutil.patient.TypicalPatients.getTypicalPatients;

import seedu.plannermd.model.PlannerMd;
import seedu.plannermd.model.doctor.Doctor;
import seedu.plannermd.model.patient.Patient;

public class TypicalPlannerMd {

    /**
     * Returns an {@code PlannerMd} with all the typical patients.
     */
    public static PlannerMd getTypicalPlannerMd() {
        PlannerMd pm = new PlannerMd();
        for (Patient patient : getTypicalPatients()) {
            pm.addPatient(patient);
        }
        for (Doctor doctor: getTypicalDoctors()) {
            pm.addDoctor(doctor);
        }
        return pm;
    }

}
