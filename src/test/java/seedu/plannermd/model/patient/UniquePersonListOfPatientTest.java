package seedu.plannermd.model.patient;

import static seedu.plannermd.testutil.patient.TypicalPatients.ALICE;
import static seedu.plannermd.testutil.patient.TypicalPatients.BOB;

import seedu.plannermd.model.person.UniquePersonList;
import seedu.plannermd.model.person.UniquePersonListTest;

public class UniquePersonListOfPatientTest extends UniquePersonListTest<Patient> {

    @Override
    protected UniquePersonList<Patient> createSamplePersonList() {
        return new UniquePersonList<>();
    }

    @Override
    protected Patient typicalPersonAlice() {
        System.out.println("Patient");
        return ALICE;
    }

    @Override
    protected Patient typicalPersonBob() {
        return BOB;
    }
}
