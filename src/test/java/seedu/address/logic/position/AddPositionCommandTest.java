package seedu.address.logic.position;

import static java.util.Objects.requireNonNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.testutil.Assert.assertThrows;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.jupiter.api.Test;

import seedu.address.logic.CommandResult;
import seedu.address.logic.candidate.exceptions.CommandException;
import seedu.address.model.HrManager;
import seedu.address.model.ReadOnlyHrManager;
import seedu.address.model.position.Position;
import seedu.address.testutil.ModelStub;
import seedu.address.testutil.PositionBuilder;


public class AddPositionCommandTest {

    @Test
    public void constructor_nullPosition_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new AddPositionCommand(null));
    }

    @Test
    public void execute_positionAcceptedByModel_addSuccessful() throws Exception {
        ModelStubAcceptingPositionAdded modelStub = new AddPositionCommandTest
                .ModelStubAcceptingPositionAdded();
        Position validPosition = new PositionBuilder().build();

        CommandResult commandResult = new AddPositionCommand(validPosition).execute(modelStub);

        assertEquals(String.format(AddPositionCommand.MESSAGE_SUCCESS, validPosition),
                commandResult.getFeedbackToUser());
        assertEquals(Arrays.asList(validPosition), modelStub.positionsAdded);
    }

    @Test
    public void execute_duplicatePerson_throwsCommandException() {
        Position validPosition = new PositionBuilder().build();
        AddPositionCommand addPositionCommand = new AddPositionCommand(validPosition);
        ModelStubWithPosition modelStub = new ModelStubWithPosition(validPosition);

        assertThrows(CommandException.class, AddPositionCommand.MESSAGE_DUPLICATE_PERSON, () ->
                addPositionCommand.execute(modelStub));
    }

    /**
     * A Model stub that contains a single person.
     */
    private class ModelStubWithPosition extends ModelStub {
        private final Position position;

        ModelStubWithPosition(Position position) {
            requireNonNull(position);
            this.position = position;
        }

        @Override
        public boolean hasPosition(Position position) {
            requireNonNull(position);
            return this.position.isSamePosition(position);
        }
    }

    /**
     * A Model stub that always accept the person being added.
     */
    private class ModelStubAcceptingPositionAdded extends ModelStub {
        final ArrayList<Position> positionsAdded = new ArrayList<>();

        @Override
        public boolean hasPosition(Position position) {
            requireNonNull(position);
            return positionsAdded.stream().anyMatch(position::isSamePosition);
        }

        @Override
        public void addPosition(Position position) {
            requireNonNull(position);
            positionsAdded.add(position);
        }

        @Override
        public ReadOnlyHrManager getHrManager() {
            return new HrManager();
        }
    }
}
