package seedu.address.logic.commands;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class CommandDetailsTest {

    @Test
    public void commandDetails_aValidStringCombo_noException() {
        String commandName = "Test";
        String commandStructure = "Test";
        CommandDetails commandDetails = new CommandDetails(commandName, commandStructure);

        // If no error occurred.
        Assertions.assertTrue(true);
    }

    @Test
    public void commandDetails_noInputs_noException() {
        CommandDetails commandDetails = new CommandDetails();

        // If no error occurred.
        Assertions.assertTrue(true);
    }

    @Test
    public void getCommandName_noInputs_noException() {
        String commandName = "Test";
        String commandStructure = "Test";
        CommandDetails commandDetails = new CommandDetails(commandName, commandStructure);

        Assertions.assertEquals(commandName, commandDetails.getCommandName());
    }

    @Test
    public void getCommandStructure_noInputs_noException() {
        String commandName = "Test";
        String commandStructure = "Test";
        CommandDetails commandDetails = new CommandDetails(commandName, commandStructure);

        Assertions.assertEquals(commandStructure, commandDetails.getCommandStructure());
    }

    @Test
    public void setCommandName_aValidString_noException() {
        String commandName = "Test";
        String commandStructure = "Test";
        CommandDetails commandDetails = new CommandDetails(commandName, commandStructure);

        String newCommandName = "Testing";
        commandDetails.setCommandName(newCommandName);

        Assertions.assertEquals(newCommandName, commandDetails.getCommandName());
    }

    @Test
    public void setCommandStructure_aValidString_noException() {
        String commandName = "Test";
        String commandStructure = "Test";
        CommandDetails commandDetails = new CommandDetails(commandName, commandStructure);

        String newCommandStructure = "Testing";
        commandDetails.setCommandStructure(newCommandStructure);

        Assertions.assertEquals(newCommandStructure, commandDetails.getCommandStructure());
    }
}
