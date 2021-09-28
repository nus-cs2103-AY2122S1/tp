package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalParticipants.getTypicalAddressBook;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.participant.Participant;
import seedu.address.testutil.ParticipantBuilder;

/**
 * Contains integration tests (interaction with the Model) for {@code AddCommand}.
 */
public class AddCommandIntegrationTest {

    private Model model;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    }

    @Test
    public void execute_newParticipant_success() {
        Participant validParticipant = new ParticipantBuilder().build();

        Model expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        expectedModel.addParticipant(validParticipant);

        assertCommandSuccess(new AddCommand(validParticipant), model,
                String.format(AddCommand.MESSAGE_SUCCESS, validParticipant), expectedModel);
    }

    @Test
    public void execute_duplicateParticipant_throwsCommandException() {
        Participant personInList = model.getAddressBook().getParticipantList().get(0);
        assertCommandFailure(new AddCommand(personInList), model, AddCommand.MESSAGE_DUPLICATE_PARTICIPANT);
    }

}
