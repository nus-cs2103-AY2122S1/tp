package seedu.plannermd.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.plannermd.logic.commands.CommandTestUtil.VALID_TAG_FRIEND;
import static seedu.plannermd.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static seedu.plannermd.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.plannermd.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.plannermd.logic.commands.CommandTestUtil.showPatientAtIndex;
import static seedu.plannermd.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.plannermd.testutil.TypicalIndexes.INDEX_SECOND_PERSON;
import static seedu.plannermd.testutil.TypicalPlannerMd.getTypicalPlannerMd;

import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Test;

import seedu.plannermd.commons.core.Messages;
import seedu.plannermd.commons.core.index.Index;
import seedu.plannermd.logic.commands.tagcommand.AddPatientTagCommand;
import seedu.plannermd.model.Model;
import seedu.plannermd.model.ModelManager;
import seedu.plannermd.model.PlannerMd;
import seedu.plannermd.model.UserPrefs;
import seedu.plannermd.model.patient.Patient;
import seedu.plannermd.model.tag.Tag;
import seedu.plannermd.testutil.patient.PatientBuilder;

/**
 * Contains integration tests (interaction with the Model) and unit tests for TagCommand.
 */
class AddPatientTagCommandTest {

    private final Model model = new ModelManager(getTypicalPlannerMd(), new UserPrefs());

    private final Tag tag = new Tag(VALID_TAG_FRIEND);

    @Test
    void execute_validTagUnfilteredList_success() {
        Patient firstPatient = model.getFilteredPatientList().get(INDEX_FIRST_PERSON.getZeroBased());
        Set<Tag> newTags = new HashSet<>(firstPatient.getTags());
        newTags.add(tag);
        Patient editedPatient = new PatientBuilder(firstPatient).withTags(
                        newTags.stream().map(t -> t.tagName).toArray(String[]::new))
                .build();

        AddPatientTagCommand addPatientTagCommand = new AddPatientTagCommand(INDEX_FIRST_PERSON, tag);

        String expectedMessage = String.format(AddPatientTagCommand.MESSAGE_ADD_TAG_SUCCESS, editedPatient);

        Model expectedModel = new ModelManager(new PlannerMd(model.getPlannerMd()), new UserPrefs());
        expectedModel.setPatient(model.getFilteredPatientList().get(INDEX_FIRST_PERSON.getZeroBased()), editedPatient);

        assertCommandSuccess(addPatientTagCommand, model, expectedMessage, expectedModel);
    }

    @Test
    void execute_validTagFilteredList_success() {
        showPatientAtIndex(model, INDEX_FIRST_PERSON);

        Patient patientInFilteredList = model.getFilteredPatientList().get(INDEX_FIRST_PERSON.getZeroBased());
        Set<Tag> newTags = new HashSet<>(patientInFilteredList.getTags());
        newTags.add(tag);
        Patient editedPatient = new PatientBuilder(patientInFilteredList).withTags(
                        newTags.stream().map(t -> t.tagName).toArray(String[]::new))
                .build();

        AddPatientTagCommand addPatientTagCommand = new AddPatientTagCommand(INDEX_FIRST_PERSON, tag);

        String expectedMessage = String.format(AddPatientTagCommand.MESSAGE_ADD_TAG_SUCCESS, editedPatient);

        Model expectedModel = new ModelManager(new PlannerMd(model.getPlannerMd()), new UserPrefs());
        expectedModel.setPatient(model.getFilteredPatientList().get(INDEX_FIRST_PERSON.getZeroBased()), editedPatient);

        assertCommandSuccess(addPatientTagCommand, model, expectedMessage, expectedModel);
    }

    @Test
    void execute_invalidIndexUnfilteredList_failure() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredPatientList().size() + 1);
        AddPatientTagCommand addPatientTagCommand = new AddPatientTagCommand(outOfBoundIndex, tag);

        assertCommandFailure(addPatientTagCommand, model, Messages.MESSAGE_INVALID_PATIENT_DISPLAYED_INDEX);
    }

    @Test
    void execute_invalidIndexFilteredList_failure() {
        showPatientAtIndex(model, INDEX_FIRST_PERSON);

        Index outOfBoundIndex = INDEX_SECOND_PERSON;
        // ensures that outOfBoundIndex is still in bounds of plannermd list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getPlannerMd().getPatientList().size());

        AddPatientTagCommand addPatientTagCommand = new AddPatientTagCommand(outOfBoundIndex, tag);

        assertCommandFailure(addPatientTagCommand, model, Messages.MESSAGE_INVALID_PATIENT_DISPLAYED_INDEX);
    }

    @Test
    void equals() {
        final AddPatientTagCommand standardCommand = new AddPatientTagCommand(INDEX_FIRST_PERSON, tag);

        // same values -> returns true
        Tag copyTag = new Tag(VALID_TAG_FRIEND);
        AddPatientTagCommand commandWithSameValues = new AddPatientTagCommand(INDEX_FIRST_PERSON, copyTag);
        assertEquals(standardCommand, commandWithSameValues);

        // same object -> returns true
        assertEquals(standardCommand, standardCommand);

        // null -> returns false
        assertNotEquals(null, standardCommand);

        // different types -> returns false
        assertNotEquals(standardCommand, new ClearCommand());

        // different index -> returns false
        assertNotEquals(standardCommand, new AddPatientTagCommand(INDEX_SECOND_PERSON, tag));

        // different tag -> returns false
        assertNotEquals(standardCommand, new AddPatientTagCommand(INDEX_FIRST_PERSON, new Tag(VALID_TAG_HUSBAND)));
    }
}
