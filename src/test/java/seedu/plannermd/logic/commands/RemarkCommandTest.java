package seedu.plannermd.logic.commands;

import org.junit.jupiter.api.Test;
import seedu.plannermd.commons.core.Messages;
import seedu.plannermd.commons.core.index.Index;
import seedu.plannermd.model.PlannerMd;
import seedu.plannermd.model.Model;
import seedu.plannermd.model.ModelManager;
import seedu.plannermd.model.UserPrefs;
import seedu.plannermd.model.person.Person;
import seedu.plannermd.model.person.Remark;
import seedu.plannermd.testutil.PersonBuilder;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.plannermd.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.plannermd.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.plannermd.logic.commands.CommandTestUtil.showPersonAtIndex;
import static seedu.plannermd.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.plannermd.testutil.TypicalIndexes.INDEX_SECOND_PERSON;
import static seedu.plannermd.testutil.TypicalPersons.getTypicalPlannerMd;

/**
 * Contains integration tests (interaction with the Model) and unit tests for RemarkCommand.
 */
class RemarkCommandTest {

    private static final String SAMPLE_REMARK = "Monthly insulin prescription";

    private Model model = new ModelManager(getTypicalPlannerMd(), new UserPrefs());

    @Test
    void execute_sampleRemarkUnfilteredList_success() {
        Person firstPerson = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        Person editedPerson = new PersonBuilder(firstPerson).withRemark(SAMPLE_REMARK).build();

        RemarkCommand remarkCommand = new RemarkCommand(INDEX_FIRST_PERSON, new Remark(SAMPLE_REMARK));

        String expectedMessage = String.format(RemarkCommand.MESSAGE_ADD_REMARK_SUCCESS, editedPerson);

        Model expectedModel = new ModelManager(new PlannerMd(model.getPlannerMd()), new UserPrefs());
        expectedModel.setPerson(model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased()), editedPerson);

        assertCommandSuccess(remarkCommand, model, expectedMessage, expectedModel);
    }

    /**
     * Add Empty Remark/ Remove current remarks.
     */
    @Test
    public void execute_emptyRemarkUnfilteredList_success() {
        Index indexLastPerson = Index.fromOneBased(model.getFilteredPersonList().size());
        Person lastPerson = model.getFilteredPersonList().get(indexLastPerson.getZeroBased());
        Person editedPerson = new PersonBuilder(lastPerson).withRemark(Remark.getEmptyRemark().value).build();

        RemarkCommand remarkCommand = new RemarkCommand(indexLastPerson, new Remark(""));

        String expectedMessage = String.format(RemarkCommand.MESSAGE_DELETE_REMARK_SUCCESS, editedPerson);

        Model expectedModel = new ModelManager(new PlannerMd(model.getPlannerMd()), new UserPrefs());
        expectedModel.setPerson(model.getFilteredPersonList().get(indexLastPerson.getZeroBased()), editedPerson);

        assertCommandSuccess(remarkCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_filteredList_success() {
        showPersonAtIndex(model, INDEX_FIRST_PERSON);

        Person personInFilteredList = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        Person editedPerson = new PersonBuilder(personInFilteredList).withRemark(SAMPLE_REMARK).build();

        RemarkCommand remarkCommand = new RemarkCommand(INDEX_FIRST_PERSON, new Remark(SAMPLE_REMARK));

        String expectedMessage = String.format(RemarkCommand.MESSAGE_ADD_REMARK_SUCCESS, editedPerson);

        Model expectedModel = new ModelManager(new PlannerMd(model.getPlannerMd()), new UserPrefs());
        expectedModel.setPerson(model.getFilteredPersonList().get(0), editedPerson);

        assertCommandSuccess(remarkCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidPersonIndexUnfilteredList_failure() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredPersonList().size() + 1);
        RemarkCommand remarkCommand = new RemarkCommand(outOfBoundIndex, new Remark(SAMPLE_REMARK));

        assertCommandFailure(remarkCommand, model, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    }

    /**
     * Remark Person in filtered list where index is larger than size of filtered list,
     * but smaller than size of plannermd
     */
    @Test
    public void execute_invalidPersonIndexFilteredList_failure() {
        showPersonAtIndex(model, INDEX_FIRST_PERSON);
        Index outOfBoundIndex = INDEX_SECOND_PERSON;

        // ensures that outOfBoundIndex is still in bounds of plannermd list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getPlannerMd().getPersonList().size());

        RemarkCommand remarkCommand = new RemarkCommand(outOfBoundIndex,
                new Remark(SAMPLE_REMARK));

        assertCommandFailure(remarkCommand, model, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        final RemarkCommand standardCommand = new RemarkCommand(INDEX_FIRST_PERSON, new Remark(SAMPLE_REMARK));

        // same values -> returns true
        RemarkCommand commandWithSameValues = new RemarkCommand(INDEX_FIRST_PERSON, new Remark(SAMPLE_REMARK));
        assertTrue(standardCommand.equals(commandWithSameValues));

        // same object -> returns true
        assertTrue(standardCommand.equals(standardCommand));

        // null -> returns false
        assertFalse(standardCommand.equals(null));

        // different types -> returns false
        assertFalse(standardCommand.equals(new ClearCommand()));

        // different index -> returns false
        assertFalse(standardCommand.equals(new RemarkCommand(INDEX_SECOND_PERSON, new Remark(SAMPLE_REMARK))));

        // different descriptor -> returns false
        assertFalse(standardCommand.equals(new RemarkCommand(INDEX_FIRST_PERSON, new Remark("Different Remark"))));
    }
}
