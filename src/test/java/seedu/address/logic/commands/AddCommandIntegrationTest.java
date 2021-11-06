package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalClients.getTypicalAddressBook;

import java.util.function.Function;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.client.Client;
import seedu.address.model.client.ClientId;
import seedu.address.testutil.ClientBuilder;

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
    public void execute_newClient_success() {
        String testClientId = "7";

        Function<ClientId, Client> validClientFunction = new ClientBuilder().buildFunction();
        Client validClient = new ClientBuilder().withClientId(testClientId).build();

        Model expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        expectedModel.addClient(new ClientBuilder().withClientId(testClientId).build());
        assertCommandSuccess(new AddCommand(validClientFunction), model,
            String.format(AddCommand.MESSAGE_SUCCESS, validClient), expectedModel);
    }

    @Test
    public void execute_duplicateClient_throwsCommandException() {
        Client clientInList = model.getAddressBook().getClientList().get(0);
        assertCommandFailure(new AddCommand(x -> clientInList), model, AddCommand.MESSAGE_DUPLICATE_CLIENT);
    }

}
