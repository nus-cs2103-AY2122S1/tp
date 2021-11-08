package seedu.plannermd.logic.commands.addcommand;

import static seedu.plannermd.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.plannermd.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.plannermd.testutil.TypicalPlannerMd.getTypicalPlannerMd;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.plannermd.model.Model;
import seedu.plannermd.model.ModelManager;
import seedu.plannermd.model.UserPrefs;
import seedu.plannermd.model.doctor.Doctor;
import seedu.plannermd.testutil.doctor.DoctorBuilder;

public class AddDoctorCommandIntegrationTest {
    private Model model;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalPlannerMd(), new UserPrefs());
    }

    @Test
    public void execute_newDoctor_success() {
        Doctor validDoctor = new DoctorBuilder().build();

        Model expectedModel = new ModelManager(model.getPlannerMd(), new UserPrefs());
        expectedModel.addDoctor(validDoctor);

        assertCommandSuccess(new AddDoctorCommand(validDoctor), model,
                String.format(AddDoctorCommand.MESSAGE_SUCCESS, validDoctor), expectedModel);
    }

    @Test
    public void execute_duplicateDoctor_throwsCommandException() {
        Doctor doctorInList = model.getPlannerMd().getDoctorList().get(0);
        assertCommandFailure(new AddDoctorCommand(doctorInList), model, AddDoctorCommand.MESSAGE_DUPLICATE_DOCTOR);
    }
}
