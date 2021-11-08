package seedu.address.logic.position;

import static java.util.Objects.requireNonNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.position.CommandTestUtil.DESC_ADMIN_ASSISTANT;
import static seedu.address.logic.position.CommandTestUtil.DESC_BOOKKEEPER;
import static seedu.address.logic.position.CommandTestUtil.VALID_STATUS_OPEN;
import static seedu.address.logic.position.CommandTestUtil.VALID_TITLE_ADMIN_ASSISTANT;
import static seedu.address.logic.position.CommandTestUtil.VALID_TITLE_BOOKKEEPER;
import static seedu.address.logic.position.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.position.CommandTestUtil.assertEditPositionCommandSuccess;
import static seedu.address.logic.position.CommandTestUtil.showPositionAtIndex;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_POSITION;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_POSITION;
import static seedu.address.testutil.TypicalPersons.JOHN;
import static seedu.address.testutil.TypicalPersons.getTypicalHrManager;
import static seedu.address.testutil.TypicalPositions.BOOKKEEPER;
import static seedu.address.testutil.TypicalPositions.CLOSED_POSITION_CLERK;

import java.util.function.Predicate;

import org.junit.jupiter.api.Test;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.CommandResult;
import seedu.address.logic.candidate.exceptions.CommandException;
import seedu.address.logic.general.ClearCommand;
import seedu.address.model.HrManager;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.ReadOnlyHrManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.interview.Interview;
import seedu.address.model.person.Person;
import seedu.address.model.position.Position;
import seedu.address.testutil.EditPositionDescriptorBuilder;
import seedu.address.testutil.ModelStub;
import seedu.address.testutil.PersonBuilder;
import seedu.address.testutil.PositionBuilder;

/**
 * Contains integration tests (interaction with the Model) and unit tests for EditPositionCommand.
 */
public class EditPositionCommandTest {

    private Model model = new ModelManager(getTypicalHrManager(), new UserPrefs());

    @Test
    public void execute_titleSpecifiedUnfilteredList_success() {
        Position editedPosition =
                new PositionBuilder().withTitle("Business Analyst").withStatus(VALID_STATUS_OPEN).build();
        EditPositionCommand.EditPositionDescriptor descriptor =
                new EditPositionDescriptorBuilder().withTitle(editedPosition.getTitle().fullTitle).build();
        EditPositionCommand editPositionCommand = new EditPositionCommand(INDEX_FIRST_POSITION, descriptor);

        String expectedMessage = String.format(EditPositionCommand.MESSAGE_EDIT_POSITION_SUCCESS, editedPosition);

        Model expectedModel = new ModelManager(new HrManager(model.getHrManager()), new UserPrefs());
        expectedModel.setPosition(model.getFilteredPositionList().get(0), editedPosition);

        assertEditPositionCommandSuccess(editPositionCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_statusSpecifiedUnfilteredList_success() {
        Position editedPosition =
                new PositionBuilder().withTitle("Administrative Assistant").withStatus(VALID_STATUS_OPEN).build();
        EditPositionCommand.EditPositionDescriptor descriptor =
                new EditPositionDescriptorBuilder().withPositionStatus(editedPosition.getStatus()).build();
        EditPositionCommand editPositionCommand = new EditPositionCommand(INDEX_FIRST_POSITION, descriptor);

        String expectedMessage = String.format(EditPositionCommand.MESSAGE_EDIT_POSITION_SUCCESS, editedPosition);

        Model expectedModel = new ModelManager(new HrManager(model.getHrManager()), new UserPrefs());
        expectedModel.setPosition(model.getFilteredPositionList().get(0), editedPosition);

        assertEditPositionCommandSuccess(editPositionCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_noFieldSpecifiedUnfilteredList_success() {
        EditPositionCommand editPositionCommand = new EditPositionCommand(INDEX_FIRST_POSITION,
                new EditPositionCommand.EditPositionDescriptor());
        Position editedPosition = model.getFilteredPositionList().get(INDEX_FIRST_POSITION.getZeroBased());

        String expectedMessage = String.format(EditPositionCommand.MESSAGE_EDIT_POSITION_SUCCESS, editedPosition);

        Model expectedModel = new ModelManager(new HrManager(model.getHrManager()), new UserPrefs());

        assertEditPositionCommandSuccess(editPositionCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_filteredList_success() {
        showPositionAtIndex(model, INDEX_FIRST_POSITION);

        Position positionInFilteredList = model.getFilteredPositionList().get(INDEX_FIRST_POSITION.getZeroBased());
        Position editedPosition =
                new PositionBuilder(positionInFilteredList).withTitle(VALID_TITLE_ADMIN_ASSISTANT).build();
        EditPositionCommand editPositionCommand = new EditPositionCommand(INDEX_FIRST_POSITION,
                new EditPositionDescriptorBuilder().withTitle(VALID_TITLE_ADMIN_ASSISTANT).build());

        String expectedMessage = String.format(EditPositionCommand.MESSAGE_EDIT_POSITION_SUCCESS, editedPosition);

        Model expectedModel = new ModelManager(new HrManager(model.getHrManager()), new UserPrefs());
        expectedModel.setPosition(model.getFilteredPositionList().get(0), editedPosition);
        showPositionAtIndex(expectedModel, INDEX_FIRST_POSITION);

        assertEditPositionCommandSuccess(editPositionCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_duplicatePositionUnfilteredList_failure() {
        Position firstPosition = model.getFilteredPositionList().get(INDEX_FIRST_POSITION.getZeroBased());
        EditPositionCommand.EditPositionDescriptor descriptor =
                new EditPositionDescriptorBuilder().withTitle(firstPosition.getTitle().fullTitle).build();
        EditPositionCommand editPositionCommand = new EditPositionCommand(INDEX_SECOND_POSITION, descriptor);

        assertCommandFailure(editPositionCommand, model, EditPositionCommand.MESSAGE_DUPLICATE_POSITION);
    }

    @Test
    public void execute_duplicatePositionFilteredList_failure() {
        showPositionAtIndex(model, INDEX_FIRST_POSITION);

        // edit position in filtered list into a duplicate in HR Manager
        Position positionInList = model.getHrManager().getPositionList().get(INDEX_SECOND_POSITION.getZeroBased());
        EditPositionCommand editPositionCommand = new EditPositionCommand(INDEX_FIRST_POSITION,
                new EditPositionDescriptorBuilder().withTitle(positionInList.getTitle().fullTitle).build());

        assertCommandFailure(editPositionCommand, model, EditPositionCommand.MESSAGE_DUPLICATE_POSITION);
    }

    @Test
    public void execute_invalidPositionIndexUnfilteredList_failure() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredPositionList().size() + 1);
        EditPositionCommand.EditPositionDescriptor descriptor =
                new EditPositionDescriptorBuilder().withTitle(VALID_TITLE_BOOKKEEPER).build();
        EditPositionCommand editPositionCommand = new EditPositionCommand(outOfBoundIndex, descriptor);

        assertCommandFailure(editPositionCommand, model, Messages.MESSAGE_INVALID_POSITION_DISPLAYED_INDEX);
    }

    /**
     * Edit filtered list where index is larger than size of filtered list,
     * but smaller than size of position list
     */
    @Test
    public void execute_invalidPositionIndexFilteredList_failure() {
        showPositionAtIndex(model, INDEX_FIRST_POSITION);
        Index outOfBoundIndex = INDEX_SECOND_POSITION;
        // ensures that outOfBoundIndex is still in bounds of position list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getHrManager().getPositionList().size());

        EditPositionCommand editPositionCommand = new EditPositionCommand(outOfBoundIndex,
                new EditPositionDescriptorBuilder().withTitle(VALID_TITLE_BOOKKEEPER).build());

        assertCommandFailure(editPositionCommand, model, Messages.MESSAGE_INVALID_POSITION_DISPLAYED_INDEX);
    }

    @Test
    public void execute_bothFieldsEdited_failure() {
        EditPositionCommand.EditPositionDescriptor descriptor = new EditPositionDescriptorBuilder()
                .withTitle("Business Analyst")
                .withPositionStatus(VALID_STATUS_OPEN).build();
        EditPositionCommand editPositionCommand = new EditPositionCommand(INDEX_FIRST_POSITION, descriptor);
        assertCommandFailure(editPositionCommand, model, EditPositionCommand.MESSAGE_BOTH_FIELDS_EDITED);
    }

    @Test
    public void equals() {
        final EditPositionCommand standardCommand = new EditPositionCommand(INDEX_FIRST_POSITION, DESC_ADMIN_ASSISTANT);

        // same values -> returns true
        EditPositionCommand.EditPositionDescriptor copyDescriptor =
                new EditPositionCommand.EditPositionDescriptor(DESC_ADMIN_ASSISTANT);
        EditPositionCommand commandWithSameValues = new EditPositionCommand(INDEX_FIRST_POSITION, copyDescriptor);
        assertTrue(standardCommand.equals(commandWithSameValues));

        // same object -> returns true
        assertTrue(standardCommand.equals(standardCommand));

        // null -> returns false
        assertFalse(standardCommand.equals(null));

        // different types -> returns false
        assertFalse(standardCommand.equals(new ClearCommand()));

        // different index -> returns false
        assertFalse(standardCommand.equals(new EditPositionCommand(INDEX_SECOND_POSITION, DESC_ADMIN_ASSISTANT)));

        // different descriptor -> returns false
        assertFalse(standardCommand.equals(new EditPositionCommand(INDEX_FIRST_POSITION, DESC_BOOKKEEPER)));
    }

    @Test
    public void execute_someFieldsSpecified_success() {
        ModelStubAcceptingPositionEdited modelStub = new ModelStubAcceptingPositionEdited();

        Position editedPosition =
                new PositionBuilder().withTitle("Business Analyst").build();
        EditPositionCommand.EditPositionDescriptor descriptor =
                new EditPositionDescriptorBuilder().withTitle(editedPosition.getTitle().fullTitle).build();
        EditPositionCommand editPositionCommand = new EditPositionCommand(INDEX_FIRST_POSITION, descriptor);

        String expectedMessage = String.format(EditPositionCommand.MESSAGE_EDIT_POSITION_SUCCESS, editedPosition);

        assertEditSuccess(editPositionCommand, expectedMessage, modelStub);

        //check that person with corresponding position is edited
        Person expectedPerson = new PersonBuilder(JOHN).withPositions("Business Analyst",
                CLOSED_POSITION_CLERK.getTitle().fullTitle).build();
        assertEquals(expectedPerson, modelStub.getPerson(INDEX_FIRST_PERSON));
    }

    private void assertEditSuccess(EditPositionCommand command, String expectedMessage, Model model) {
        try {
            CommandResult result = command.execute(model);
            //because standard constructor of CommandResult does not update isListP to true
            assertEquals(result, new CommandResult(expectedMessage,
                    CommandResult.CommandType.POSITION));
        } catch (CommandException ce) {
            throw new AssertionError("Execution of command should not fail.", ce);
        }
    }

    /**
     * A Model stub that always accept the person being added.
     */
    private class ModelStubAcceptingPositionEdited extends ModelStub {
        final ObservableList<Position> positions = FXCollections.observableArrayList();
        final ObservableList<Person> persons = FXCollections.observableArrayList();
        final ObservableList<Interview> interviews = FXCollections.observableArrayList();

        ModelStubAcceptingPositionEdited() {
            persons.add(JOHN);
            positions.add(BOOKKEEPER);
            positions.add(CLOSED_POSITION_CLERK);
        }

        @Override
        public boolean hasPosition(Position position) {
            requireNonNull(position);
            return positions.stream().anyMatch(position::isSamePosition);
        }

        @Override
        public Position getPositionReference(Position position) {
            return position;
        }

        @Override
        public void addPosition(Position position) {
            requireNonNull(position);
            positions.add(position);
        }

        @Override
        public void setPosition(Position target, Position editedPosition) {
            int index = positions.indexOf(target);
            positions.set(index, editedPosition);
        }

        @Override
        public Person getPerson(Index index) {
            return persons.get(index.getZeroBased());
        }

        @Override
        public ObservableList<Position> getFilteredPositionList() {
            return positions;
        }

        @Override
        public ObservableList<Person> getFilteredPersonList() {
            return persons;
        }

        @Override
        public ObservableList<Interview> getFilteredInterviewList() {
            return interviews;
        }

        @Override
        public void setPerson(Person target, Person editedPerson) {
            int index = persons.indexOf(target);
            persons.set(index, editedPerson);
        }

        @Override
        public ReadOnlyHrManager getHrManager() {
            return new HrManager();
        }

        @Override
        public void updateFilteredPositionList(Predicate<Position> predicate) {
        }

        @Override
        public void updateFilteredPersonList(Predicate<Person> predicate) {
        }
    }

}
