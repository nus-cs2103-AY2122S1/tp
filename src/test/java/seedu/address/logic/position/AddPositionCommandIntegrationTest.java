package seedu.address.logic.position;

import static seedu.address.logic.position.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.position.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalPersons.getTypicalHrManager;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.position.Position;
import seedu.address.testutil.PositionBuilder;

public class AddPositionCommandIntegrationTest {
    private Model model;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalHrManager(), new UserPrefs());
    }

    @Test
    public void execute_newPosition_success() {
        Position validPosition = new PositionBuilder().build();
        Model expectedModel = new ModelManager(model.getHrManager(), new UserPrefs());
        expectedModel.addPosition(validPosition);
        assertCommandSuccess(new AddPositionCommand(validPosition), model,
                String.format(AddPositionCommand.MESSAGE_SUCCESS, validPosition), expectedModel);
    }

    @Test
    public void execute_duplicatePerson_throwsCommandException() {
        Position positionInList = model.getHrManager().getPositionList().get(0);
        assertCommandFailure(new AddPositionCommand(positionInList), model,
                AddPositionCommand.MESSAGE_DUPLICATE_PERSON);
    }
}
