package seedu.plannermd.testutil;

import seedu.plannermd.model.PlannerMd;
import seedu.plannermd.model.doctor.Doctor;
import seedu.plannermd.model.patient.Patient;

/**
 * A utility class to help with building PlannerMd objects.
 * Example usage: <br>
 *     {@code PlannerMd ab = new PlannerMdBuilder().withPatient("John", "Doe").build();}
 */
public class PlannerMdBuilder {

    private PlannerMd plannerMd;

    public PlannerMdBuilder() {
        plannerMd = new PlannerMd();
    }

    public PlannerMdBuilder(PlannerMd plannerMd) {
        this.plannerMd = plannerMd;
    }

    /**
     * Adds a new {@code Patient} to the {@code PlannerMd} that we are building.
     */
    public PlannerMdBuilder withPatient(Patient patient) {
        plannerMd.addPatient(patient);
        return this;
    }

    /**
     * Adds a new {@code Doctor} to the {@code PlannerMd} that we are building.
     */
    public PlannerMdBuilder withDoctor(Doctor doctor) {
        plannerMd.addDoctor(doctor);
        return this;
    }

    public PlannerMd build() {
        return plannerMd;
    }
}
