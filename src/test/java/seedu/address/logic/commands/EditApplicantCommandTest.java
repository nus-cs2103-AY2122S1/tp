package seedu.address.logic.commands;

import org.junit.jupiter.api.Test;
import seedu.address.logic.descriptors.EditApplicantDescriptor;
import seedu.address.model.ApplicantBook;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.PositionBook;
import seedu.address.model.UserPrefs;
import seedu.address.model.applicant.Applicant;
import seedu.address.model.position.Position;
import seedu.address.testutil.ApplicantBuilder;
import seedu.address.testutil.EditApplicantDescriptorBuilder;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalApplicants.getTypicalApplicantBook;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_APPLICANT;
import static seedu.address.testutil.TypicalPositions.getTypicalPositionBook;

public class EditApplicantCommandTest {
    private Model model = new ModelManager(getTypicalApplicantBook(), getTypicalPositionBook(), new UserPrefs());

    @Test
    public void execute_allFieldsSpecifiedUnfilteredList_success() {
        Position validPosition = model.getFilteredPositionList().get(0);
        Applicant editApplicant = new ApplicantBuilder().withPosition(validPosition).build();
        EditApplicantDescriptor descriptor = new EditApplicantDescriptorBuilder(editApplicant).build();
        EditApplicantCommand editApplicantCommand = new EditApplicantCommand(INDEX_FIRST_APPLICANT, descriptor);

        String expectedMessage = String.format(EditApplicantCommand.MESSAGE_EDIT_APPLICANT_SUCCESS, editApplicant);

        Model expectedModel = new ModelManager(new ApplicantBook(model.getApplicantBook()),
                new PositionBook(model.getPositionBook()), new UserPrefs());

        expectedModel.setApplicant(model.getFilteredApplicantList().get(0), editApplicant);

        assertCommandSuccess(editApplicantCommand, model, expectedMessage, expectedModel);
    }



}
