package seedu.plannermd.logic.commands;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.plannermd.logic.commands.CommandTestUtil.VALID_TAG_FRIEND;
import static seedu.plannermd.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static seedu.plannermd.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.plannermd.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.plannermd.logic.commands.CommandTestUtil.showDoctorAtIndex;
import static seedu.plannermd.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.plannermd.testutil.TypicalIndexes.INDEX_SECOND_PERSON;
import static seedu.plannermd.testutil.TypicalPlannerMd.getTypicalPlannerMd;

import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Test;

import seedu.plannermd.commons.core.Messages;
import seedu.plannermd.commons.core.index.Index;
import seedu.plannermd.logic.commands.tagcommand.DeleteDoctorTagCommand;
import seedu.plannermd.logic.commands.tagcommand.DeletePatientTagCommand;
import seedu.plannermd.model.Model;
import seedu.plannermd.model.ModelManager;
import seedu.plannermd.model.PlannerMd;
import seedu.plannermd.model.UserPrefs;
import seedu.plannermd.model.doctor.Doctor;
import seedu.plannermd.model.tag.Tag;
import seedu.plannermd.testutil.doctor.DoctorBuilder;

class DeleteDoctorTagCommandTest {

    private final Model model = new ModelManager(getTypicalPlannerMd(), new UserPrefs());

    private final Tag modelFirstPersonTag = new Tag("friends");
    private final Tag tag = new Tag(VALID_TAG_FRIEND);

    @Test
    void execute_validTagUnfilteredList_success() {
        Doctor firstDoctor = model.getFilteredDoctorList().get(INDEX_FIRST_PERSON.getZeroBased());
        Set<Tag> newTags = new HashSet<>(firstDoctor.getTags());
        newTags.remove(modelFirstPersonTag);
        Doctor editedDoctor = new DoctorBuilder(firstDoctor).withTags(
                newTags.stream().map(t -> t.tagName).toArray(String[]::new))
                .build();

        DeleteDoctorTagCommand deleteDoctorTagCommand =
                new DeleteDoctorTagCommand(INDEX_FIRST_PERSON, modelFirstPersonTag);

        String expectedMessage = String.format(DeleteDoctorTagCommand.MESSAGE_DELETE_TAG_SUCCESS, editedDoctor);

        Model expectedModel = new ModelManager(new PlannerMd(model.getPlannerMd()), new UserPrefs());
        expectedModel.setDoctor(model.getFilteredDoctorList().get(INDEX_FIRST_PERSON.getZeroBased()), editedDoctor);

        assertCommandSuccess(deleteDoctorTagCommand, model, expectedMessage, expectedModel);
    }

    @Test
    void execute_validTagFilteredList_success() {
        showDoctorAtIndex(model, INDEX_FIRST_PERSON);

        Doctor doctorInFilteredList = model.getFilteredDoctorList().get(INDEX_FIRST_PERSON.getZeroBased());
        Set<Tag> newTags = new HashSet<>(doctorInFilteredList.getTags());
        newTags.remove(modelFirstPersonTag);
        Doctor editedDoctor = new DoctorBuilder(doctorInFilteredList).withTags(
                newTags.stream().map(t -> t.tagName).toArray(String[]::new))
                .build();

        DeleteDoctorTagCommand deleteDoctorTagCommand =
                new DeleteDoctorTagCommand(INDEX_FIRST_PERSON, modelFirstPersonTag);

        String expectedMessage = String.format(DeleteDoctorTagCommand.MESSAGE_DELETE_TAG_SUCCESS, editedDoctor);

        Model expectedModel = new ModelManager(new PlannerMd(model.getPlannerMd()), new UserPrefs());
        expectedModel.setDoctor(model.getFilteredDoctorList().get(INDEX_FIRST_PERSON.getZeroBased()), editedDoctor);

        assertCommandSuccess(deleteDoctorTagCommand, model, expectedMessage, expectedModel);
    }

    @Test
    void execute_invalidIndexUnfilteredList_failure() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredDoctorList().size() + 1);
        DeleteDoctorTagCommand deleteDoctorTagCommand =
                new DeleteDoctorTagCommand(outOfBoundIndex, modelFirstPersonTag);

        assertCommandFailure(deleteDoctorTagCommand, model, Messages.MESSAGE_INVALID_PATIENT_DISPLAYED_INDEX);
    }

    @Test
    void execute_invalidIndexFilteredList_failure() {
        showDoctorAtIndex(model, INDEX_FIRST_PERSON);

        Index outOfBoundIndex = INDEX_SECOND_PERSON;
        // ensures that outOfBoundIndex is still in bounds of plannermd list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getPlannerMd().getDoctorList().size());

        DeleteDoctorTagCommand deleteDoctorTagCommand =
                new DeleteDoctorTagCommand(outOfBoundIndex, modelFirstPersonTag);

        assertCommandFailure(deleteDoctorTagCommand, model, Messages.MESSAGE_INVALID_PATIENT_DISPLAYED_INDEX);
    }

    @Test
    void equals() {
        final DeleteDoctorTagCommand standardCommand = new DeleteDoctorTagCommand(INDEX_FIRST_PERSON, tag);

        // same values -> returns true
        Tag copyTag = new Tag(VALID_TAG_FRIEND);
        DeleteDoctorTagCommand commandWithSameValues = new DeleteDoctorTagCommand(INDEX_FIRST_PERSON, copyTag);
        assertEquals(standardCommand, commandWithSameValues);

        // same object -> returns true
        assertEquals(standardCommand, standardCommand);

        // null -> returns false
        assertNotEquals(null, standardCommand);

        // different types -> returns false
        assertNotEquals(standardCommand, new ClearCommand());
        assertNotEquals(standardCommand, new DeletePatientTagCommand(INDEX_FIRST_PERSON, tag));

        // different index -> returns false
        assertNotEquals(standardCommand, new DeleteDoctorTagCommand(INDEX_SECOND_PERSON, tag));

        // different tag -> returns false
        assertNotEquals(standardCommand, new DeleteDoctorTagCommand(INDEX_FIRST_PERSON, new Tag(VALID_TAG_HUSBAND)));
    }
}
