package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;
import static seedu.address.logic.commands.AddClientCommand.MESSAGE_SUCCESS;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.ModelManager;
import seedu.address.model.client.Client;
import seedu.address.model.client.PhoneNumber;
import seedu.address.model.commons.Name;

public class AddClientCommandTest {
    private Name name = new Name("John Doe");
    private PhoneNumber phoneNumber = new PhoneNumber("12345678");
    private AddClientCommand.AddClientDescriptor descriptor =
            new AddClientCommand.AddClientDescriptor(name, phoneNumber);
    private AddClientCommand addClientCommand = new AddClientCommand(descriptor);

    @Test
    public void constructor_nullDescriptor_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new AddClientCommand(null));
    }

    @Test
    public void execute_nullModel_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> addClientCommand.execute(null));
    }

    @Test
    public void execute_duplicateClient_throwsCommandException() {
        assertThrows(CommandException.class, () -> addClientCommand.execute(new ModelDuplicateClientStub()));
    }

    @Test
    public void execute_newClient_returnsCommandResult() {
        Client clientToAdd = new Client(name, phoneNumber, null, null);
        CommandResult expectedResult = new CommandResult(String.format(MESSAGE_SUCCESS, clientToAdd));
        try {
            CommandResult actualResult = addClientCommand.execute(new ModelStub());
            // compare the feedback to user excluding the id.
            String actualString = actualResult.getFeedbackToUser();
            actualString = actualString.substring(actualString.indexOf("Name"));
            String expectedString = expectedResult.getFeedbackToUser();
            expectedString = expectedString.substring(expectedString.indexOf("Name"));
            assertEquals(expectedString, actualString);
            assertEquals(expectedResult.isShowHelp(), actualResult.isShowHelp());
            assertEquals(expectedResult.isExit(), actualResult.isExit());
        } catch (CommandException e) {
            fail();
        }
    }

    @Test
    public void equals_null_returnsFalse() {
        assertFalse(addClientCommand.equals(null));
    }

    @Test void equals_itself_returnsTrue() {
        assertTrue(addClientCommand.equals(addClientCommand));
    }

    public class ModelDuplicateClientStub extends ModelManager {
        /**
         * Assume there are duplicate clients, return true.
         *
         * @param client the client to be checked.
         * @return True.
         */
        @Override
        public boolean hasClient(Client client) {
            return true;
        }
    }

    public class ModelStub extends ModelManager {
        /**
         * Assume there are no duplicate clients, return false.
         *
         * @param client the client to be checked.
         * @return False.
         */
        @Override
        public boolean hasClient(Client client) {
            return false;
        }
    }
}
