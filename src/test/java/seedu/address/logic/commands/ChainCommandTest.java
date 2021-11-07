package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;


public class ChainCommandTest {
    private Model model = new ModelManager();
    private Model expectedModel = new ModelManager();

    @Test
    public void equals() throws Exception {

        ChainCommand chainStubCommand = new ChainCommand(new BasicCommandStub(1), new BasicCommandStub(2));
        // same object -> returns true
        assertTrue(chainStubCommand.equals(chainStubCommand));

        // same values -> returns true
        ChainCommand chainStubCommandCopy = new ChainCommand(new BasicCommandStub(1), new BasicCommandStub(2));
        assertTrue(chainStubCommand.equals(chainStubCommandCopy));

        // different types -> returns false
        assertFalse(chainStubCommand.equals(1));

        // null -> returns false
        assertFalse(chainStubCommand.equals(null));

        ChainCommand chainStubCommandDiff = new ChainCommand(new BasicCommandStub(2), new BasicCommandStub(1));

        // different commands -> returns false
        assertFalse(chainStubCommand.equals(chainStubCommandDiff));
    }

    @Test
    public void executeCommands() {
        //execute 2 commands
        ChainCommand chainStubCommand = new ChainCommand(new BasicCommandStub(1), new BasicCommandStub(2));
        CommandResult expectedCommandResult = new CommandResult(BasicCommandStub.STUB_MESSAGE + "1\n"
                + BasicCommandStub.STUB_MESSAGE + "2", true, false, false);
        assertCommandSuccess(chainStubCommand, model, expectedCommandResult, expectedModel);
        //execute 3 commands
        ChainCommand tripleChainStubCommand = new ChainCommand(chainStubCommand, new BasicCommandStub(3));
        expectedCommandResult = new CommandResult(BasicCommandStub.STUB_MESSAGE + "1\n"
                + BasicCommandStub.STUB_MESSAGE + "2\n"
                + BasicCommandStub.STUB_MESSAGE + "3", true, false, false);
        assertCommandSuccess(tripleChainStubCommand, model, expectedCommandResult, expectedModel);
    }

    /**
     * A Basic Command stub.
     */
    private class BasicCommandStub extends Command {
        public static final String STUB_MESSAGE = "STUB MESSAGE";
        public final int stubIndex;

        public BasicCommandStub(int stubIndex) {
            this.stubIndex = stubIndex;
        }

        @Override
        public CommandResult execute(Model model) throws CommandException {
            return new CommandResult(STUB_MESSAGE + stubIndex, true, false, false);
        }

        @Override
        public boolean equals(Object other) {
            return other == this // short circuit if same object
                    || (other instanceof BasicCommandStub // instanceof handles nulls
                    && stubIndex == ((BasicCommandStub) other).stubIndex); // state
        }
    }

}
