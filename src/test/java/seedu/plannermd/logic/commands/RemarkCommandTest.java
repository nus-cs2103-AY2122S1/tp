package seedu.plannermd.logic.commands;

import org.junit.jupiter.api.Test;
import seedu.plannermd.model.PlannerMd;
import seedu.plannermd.model.Model;
import seedu.plannermd.model.ModelManager;
import seedu.plannermd.model.UserPrefs;
import seedu.plannermd.model.person.Person;
import seedu.plannermd.logic.commands.EditCommand.EditPersonDescriptor;
import seedu.plannermd.model.person.Remark;
import seedu.plannermd.testutil.EditPersonDescriptorBuilder;
import seedu.plannermd.testutil.PersonBuilder;

import static seedu.plannermd.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.plannermd.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.plannermd.testutil.TypicalPersons.getTypicalPlannerMd;

class RemarkCommandTest {

    private static final String SAMPLE_REMARK = "xd";

    private Model model = new ModelManager(getTypicalPlannerMd(), new UserPrefs());

    @Test
    void execute_addRemarkUnfilteredList_success() {
        Person firstPerson = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        Person editedPerson = new PersonBuilder(firstPerson).withRemark(SAMPLE_REMARK).build();

        RemarkCommand remarkCommand = new RemarkCommand(INDEX_FIRST_PERSON, new Remark(SAMPLE_REMARK));

        String expectedMessage = String.format(RemarkCommand.MESSAGE_ADD_REMARK_SUCCESS, editedPerson);

        Model expectedModel = new ModelManager(new PlannerMd(model.getPlannerMd()), new UserPrefs());
        expectedModel.setPerson(model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased()), editedPerson);

        assertCommandSuccess(remarkCommand, model, expectedMessage, expectedModel);
    }
}
