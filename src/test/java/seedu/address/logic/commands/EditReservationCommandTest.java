package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.ReservationCommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.ReservationCommandTestUtil.showReservationAtIndex;
import static seedu.address.testutil.TypicalReservation.getTypicalRhrh;

import java.util.Set;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.EditReservationCommand.EditReservationDescriptor;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.Rhrh;
import seedu.address.model.UserPrefs;
import seedu.address.model.reservation.Remark;
import seedu.address.model.reservation.Reservation;
import seedu.address.model.tag.Tag;

public class EditReservationCommandTest {

    private static final Remark NEW_REMARK = new Remark("new remark test");
    private static final Set<Tag> NEW_TAGS = Set.of(new Tag("new tag test"));

    private Model model = new ModelManager(getTypicalRhrh(), new UserPrefs());

    @Test
    public void execute_allFieldsSpecifiedUnfilteredList_success() {
        Index targetIndex = Index.fromOneBased(1);
        Reservation targetReservation = model.getFilteredReservationList().get(targetIndex.getZeroBased());
        Reservation editedReservation = new Reservation(
                targetReservation.getPhone(),
                targetReservation.getNumberOfPeople(),
                targetReservation.getDateTime(),
                targetReservation.getTable(),
                NEW_REMARK,
                NEW_TAGS
        );

        EditReservationDescriptor descriptor = new EditReservationDescriptor();
        descriptor.setRemark(NEW_REMARK);
        descriptor.setTags(NEW_TAGS);

        EditReservationCommand command = new EditReservationCommand(targetIndex, descriptor);
        String expectedMessage = String.format(
                EditReservationCommand.MESSAGE_EDIT_RESERVATION_SUCCESS, editedReservation
        );

        Model expectedModel = new ModelManager(new Rhrh(model.getRhrh()), new UserPrefs());
        expectedModel.setReservation(targetReservation, editedReservation);

        assertCommandSuccess(command, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_remarkSpecifiedUnfilteredList_success() {
        Index targetIndex = Index.fromOneBased(1);
        Reservation targetReservation = model.getFilteredReservationList().get(targetIndex.getZeroBased());

        // only remark edited
        Reservation editedRemarkReservation = new Reservation(
                targetReservation.getPhone(),
                targetReservation.getNumberOfPeople(),
                targetReservation.getDateTime(),
                targetReservation.getTable(),
                NEW_REMARK,
                targetReservation.getTags()
        );

        EditReservationDescriptor remarkEditedDescriptor = new EditReservationDescriptor();
        remarkEditedDescriptor.setRemark(NEW_REMARK);

        EditReservationCommand remarkEditedCommand = new EditReservationCommand(targetIndex, remarkEditedDescriptor);
        String remarkEditedExpectedMessage = String.format(
                EditReservationCommand.MESSAGE_EDIT_RESERVATION_SUCCESS, editedRemarkReservation
        );

        Model remarkEditedExpectedModel = new ModelManager(new Rhrh(model.getRhrh()), new UserPrefs());
        remarkEditedExpectedModel.setReservation(targetReservation, editedRemarkReservation);

        assertCommandSuccess(remarkEditedCommand, model, remarkEditedExpectedMessage, remarkEditedExpectedModel);
    }

    @Test
    public void execute_tagsSpecifiedUnfilteredList_success() {
        Index targetIndex = Index.fromOneBased(1);
        Reservation targetReservation = model.getFilteredReservationList().get(targetIndex.getZeroBased());

        // only tags edited
        Reservation editedTagsReservation = new Reservation(
                targetReservation.getPhone(),
                targetReservation.getNumberOfPeople(),
                targetReservation.getDateTime(),
                targetReservation.getTable(),
                targetReservation.getRemark(),
                NEW_TAGS
        );

        EditReservationDescriptor tagsEditedDescriptor = new EditReservationDescriptor();
        tagsEditedDescriptor.setTags(NEW_TAGS);

        EditReservationCommand tagsEditedCommand = new EditReservationCommand(targetIndex, tagsEditedDescriptor);
        String tagEditedExpectedMessage = String.format(
                EditReservationCommand.MESSAGE_EDIT_RESERVATION_SUCCESS, editedTagsReservation
        );

        Model tagsEditedExpectedModel = new ModelManager(new Rhrh(model.getRhrh()), new UserPrefs());
        tagsEditedExpectedModel.setReservation(targetReservation, editedTagsReservation);

        assertCommandSuccess(tagsEditedCommand, model, tagEditedExpectedMessage, tagsEditedExpectedModel);
    }

    @Test
    public void execute_noFieldSpecifiedUnfilteredList_success() {
        Index targetIndex = Index.fromOneBased(1);
        EditReservationCommand command = new EditReservationCommand(targetIndex, new EditReservationDescriptor());
        Reservation editedReservation = model.getFilteredReservationList().get(targetIndex.getZeroBased());

        String expectedMessage = String.format(
                EditReservationCommand.MESSAGE_EDIT_RESERVATION_SUCCESS,
                editedReservation
        );

        Model expectedModel = new ModelManager(new Rhrh(model.getRhrh()), new UserPrefs());

        assertCommandSuccess(command, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_filteredList_success() {
        Index targetIndex = Index.fromOneBased(1);
        showReservationAtIndex(model, targetIndex);
        Reservation targetReservation = model.getFilteredReservationList().get(0);

        Reservation editedReservation = new Reservation(
                targetReservation.getPhone(),
                targetReservation.getNumberOfPeople(),
                targetReservation.getDateTime(),
                targetReservation.getTable(),
                NEW_REMARK,
                NEW_TAGS
        );
        EditReservationDescriptor descriptor = new EditReservationDescriptor();
        descriptor.setRemark(NEW_REMARK);
        descriptor.setTags(NEW_TAGS);
        EditReservationCommand command = new EditReservationCommand(targetIndex, descriptor);
        String expectedMessage = String.format(
                EditReservationCommand.MESSAGE_EDIT_RESERVATION_SUCCESS,
                editedReservation
        );

        Model expectedModel = new ModelManager(model.getRhrh(), new UserPrefs());
        expectedModel.setReservation(targetReservation, editedReservation);

        assertCommandSuccess(command, model, expectedMessage, expectedModel);
    }

    @Test
    public void equals() {
        EditReservationDescriptor descriptor = new EditReservationDescriptor();
        descriptor.setRemark(NEW_REMARK);
        descriptor.setTags(NEW_TAGS);
        final EditReservationCommand command = new EditReservationCommand(Index.fromOneBased(1), descriptor);

        // same values -> returns true
        EditReservationDescriptor copyDescriptor = new EditReservationDescriptor(descriptor);
        EditReservationCommand sameCommand = new EditReservationCommand(Index.fromOneBased(1), copyDescriptor);
        assertTrue(command.equals(sameCommand));

        // same object -> returns true
        assertTrue(command.equals(command));

        // null -> returns false
        assertFalse(command.equals(null));

        // different types -> returns false
        assertFalse(command.equals(new ClearCommand()));

        // different index -> returns false
        assertFalse(command.equals(new EditReservationCommand(Index.fromOneBased(2), descriptor)));

        // different descriptor
        EditReservationDescriptor differentDescriptor = new EditReservationDescriptor();
        differentDescriptor.setTags(NEW_TAGS);
        assertFalse(command.equals(new EditReservationCommand(Index.fromOneBased(1), differentDescriptor)));
    }
}
