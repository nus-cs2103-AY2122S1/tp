package seedu.anilist.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.jupiter.api.Test;

public class CommandsListTest {

    private String listOfCommandsToString(String[] listOfCommands) {
        String result = "";
        for (int i = 0; i < listOfCommands.length; i++) {
            result += listOfCommands[i];
            result += ", ";
        }
        result = result.substring(0, result.length() - 2);
        return result;
    }

    @Test
    public void getListOfCommandsAsString_none_correctString() {
        String[] listOfCommandsCopy = CommandsList.getListOfCommands();
        String correctString = listOfCommandsToString(listOfCommandsCopy);

        String actualString = CommandsList.getListOfCommandsAsString();
        assertEquals(correctString, actualString);
    }

    /**
     * getListOfCommands should return a copy of the original array
     * modifying it's contents should not affect original array.
     */
    @Test
    public void getListOfCommands_none_copyOfActualArray() {
        String[] listOfCommandsCopy = CommandsList.getListOfCommands();
        if (listOfCommandsCopy.length > 0) {
            for (int i = 0; i < listOfCommandsCopy.length; i++) {
                listOfCommandsCopy[i] = "foobar foobar";
            }

            String wrongString = listOfCommandsToString(listOfCommandsCopy);
            String actualString = CommandsList.getListOfCommandsAsString();
            assertNotEquals(wrongString, actualString);
        }
    }
}
