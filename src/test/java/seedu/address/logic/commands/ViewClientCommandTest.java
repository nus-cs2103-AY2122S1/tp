package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.fail;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalClients.getTypicalAddressBook;

import java.util.Arrays;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.client.Client;
import seedu.address.model.client.ClientContainsIdPredicate;
import seedu.address.model.client.PhoneNumber;
import seedu.address.model.commons.Name;
/**
 * Contains integration tests (interaction with the Model) and unit tests for ListClientCommand.
 */
public class ViewClientCommandTest {
    private Model model;
    private Model expectedModel;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
        expectedModel = new ModelManager(getTypicalAddressBook(), new UserPrefs());
        Client client1 = new Client(new Name("Test1"), new PhoneNumber("1"), null, null);
        Client client2 = new Client(new Name("Test2"), new PhoneNumber("2"), null, null);
        Client client3 = new Client(new Name("Test3"), new PhoneNumber("3"), null, null);
        model.addClient(client1);
        model.addClient(client2);
        model.addClient(client3);
        expectedModel.addClient(client1);
        expectedModel.addClient(client2);
        expectedModel.addClient(client3);
    }

    @Test
    public void execute_viewZeroIndex_showsInvalidIndex() {
        String[] index = {"0"};
        ViewClientCommand viewClientCommand = new ViewClientCommand(
                new ClientContainsIdPredicate(Arrays.asList(index))
        );
        assertCommandFailure(viewClientCommand, model, Messages.MESSAGE_INVALID_CLIENT_DISPLAYED_INDEX);
    }

    @Test
    public void execute_viewInvalidIndex_showsInvalidIndex() {
        int outOfBoundIndex = model.getFilteredClientList().size() + 1;
        String[] index = {String.valueOf(outOfBoundIndex)};
        ViewClientCommand viewClientCommand = new ViewClientCommand(
                new ClientContainsIdPredicate(Arrays.asList(index))
        );
        assertCommandFailure(viewClientCommand, model, Messages.MESSAGE_INVALID_CLIENT_DISPLAYED_INDEX);
    }

    @Test
    public void execute_viewFirstValidIndex_success() {
        String[] index = {"1"};
        ViewClientCommand viewClientCommand = new ViewClientCommand(
                new ClientContainsIdPredicate(Arrays.asList(index))
        );
        try {
            CommandResult commandResult = viewClientCommand.execute(model);
            assertCommandSuccess(viewClientCommand, model, commandResult, expectedModel);
        } catch (CommandException e) {
            fail();
        }
    }

    @Test
    public void execute_viewLastValidIndex_success() {
        String[] index = {"3"};
        ViewClientCommand viewClientCommand = new ViewClientCommand(
                new ClientContainsIdPredicate(Arrays.asList(index))
        );
        try {
            CommandResult commandResult = viewClientCommand.execute(model);
            assertCommandSuccess(viewClientCommand, model, commandResult, expectedModel);
        } catch (CommandException e) {
            fail();
        }
    }

    @Test
    public void execute_viewMiddleValidIndex_success() {
        String[] index = {"2"};
        ViewClientCommand viewClientCommand = new ViewClientCommand(
                new ClientContainsIdPredicate(Arrays.asList(index))
        );
        try {
            CommandResult commandResult = viewClientCommand.execute(model);
            assertCommandSuccess(viewClientCommand, model, commandResult, expectedModel);
        } catch (CommandException e) {
            fail();
        }
    }
}
