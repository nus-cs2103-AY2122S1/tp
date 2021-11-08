package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailureWithoutException;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalClasses.getAddressBookWithTypicalClasses;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND;
import static seedu.address.testutil.TypicalIndexes.INDEX_TENTH;
import static seedu.address.testutil.TypicalIndexes.INDEX_THIRD;

import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.tuition.TuitionClass;

/**
 * Contains integration tests (interaction with the Model) and unit tests for DeleteClassCommand.
 */
public class DeleteClassCommandTest {

    private Model model = new ModelManager(getAddressBookWithTypicalClasses(), new UserPrefs());

    @Test
    public void execute_deleteOneClass_success() {
        DeleteClassCommand deleteCommand = new DeleteClassCommand(List.of(INDEX_FIRST));
        TuitionClass classToDelete = model.getFilteredTuitionList().get(INDEX_FIRST.getZeroBased());

        String expectedMessage = String.format(DeleteClassCommand.MESSAGE_DELETE_CLASSES_SUCCESS,
                List.of(classToDelete.getName() + "|" + classToDelete.getTimeslot()));

        ModelManager expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        expectedModel.deleteTuition(classToDelete);

        assertCommandSuccess(deleteCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_deleteValidWithInvalidClass_failure() {
        TuitionClass firstClass = model.getTuitionClass(INDEX_FIRST);

        DeleteClassCommand deleteClassCommand = new DeleteClassCommand(List.of(INDEX_TENTH, INDEX_FIRST));
        String expectedMessage = String.format(DeleteClassCommand.MESSAGE_DELETE_CLASSES_SUCCESS,
                List.of(firstClass.getName() + "|" + firstClass.getTimeslot()))
                + String.format(DeleteClassCommand.MESSAGE_DELETE_CLASSES_FAILURE, List.of(INDEX_TENTH.getOneBased()));

        ModelManager expectedModel = new ModelManager(getAddressBookWithTypicalClasses(), new UserPrefs());
        assertCommandFailureWithoutException(deleteClassCommand, expectedModel, expectedMessage);
    }

    @Test
    public void execute_outOfBoundsIndex_failure() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredTuitionList().size() + 1);
        DeleteClassCommand deleteCommand = new DeleteClassCommand(List.of(outOfBoundIndex));
        String expectedMessage = String.format(DeleteClassCommand.MESSAGE_DELETE_CLASSES_FAILURE,
                List.of(outOfBoundIndex.getOneBased()));

        assertCommandFailure(deleteCommand, model, expectedMessage);
    }

    @Test
    public void execute_invalidIndex_failure() {
        Model expectedModel = new ModelManager(new AddressBook(), new UserPrefs());
        List<Index> outOfBoundIndex = List.<Index>of(INDEX_THIRD, INDEX_SECOND);

        DeleteClassCommand deleteClassCommand = new DeleteClassCommand(outOfBoundIndex);
        String expectedMessage = String.format(DeleteClassCommand.MESSAGE_DELETE_CLASSES_FAILURE,
                outOfBoundIndex.stream().map(x -> x.getOneBased()).collect(Collectors.toList()));

        assertCommandFailure(deleteClassCommand, expectedModel, expectedMessage);
    }

    @Test
    public void execute_deleteMultipleClass_success() {
        List<TuitionClass> classList = List.of(model.getTuitionClass(INDEX_SECOND), model.getTuitionClass(INDEX_FIRST));
        List<String> classes = classList.stream()
                .map(c -> c.getName() + "|" + c.getTimeslot()).collect(Collectors.toList());

        DeleteClassCommand deleteCommand = new DeleteClassCommand(List.of(INDEX_SECOND, INDEX_FIRST));
        String expectedMessage = String.format(DeleteClassCommand.MESSAGE_DELETE_CLASSES_SUCCESS, classes);

        ModelManager expectedModel = new ModelManager(getAddressBookWithTypicalClasses(), new UserPrefs());
        assertCommandSuccess(deleteCommand, expectedModel, expectedMessage);
    }

    @Test
    public void equals() {
        DeleteClassCommand firstCommand = new DeleteClassCommand(List.of(INDEX_SECOND, INDEX_FIRST));
        DeleteClassCommand secondCommand = new DeleteClassCommand(List.of(INDEX_THIRD, INDEX_SECOND));

        // same object -> returns true
        assertTrue(firstCommand.equals(firstCommand));

        // different values -> returns false
        assertFalse(firstCommand.equals(secondCommand));

        //same values and different order -> returns false
        DeleteClassCommand copy = new DeleteClassCommand(List.of(INDEX_FIRST, INDEX_SECOND));
        assertFalse(firstCommand.equals(copy));

        // same values and same order -> returns true
        DeleteClassCommand firstCommandCopy = new DeleteClassCommand(List.of(INDEX_SECOND, INDEX_FIRST));
        assertTrue(firstCommand.equals(firstCommandCopy));

        // different types -> returns false
        assertFalse(firstCommand.equals(1));

        // null -> returns false
        assertFalse(firstCommand.equals(null));
    }

}
