package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandResult.SpecialCommandResult.CLEAR;
import static seedu.address.logic.commands.CommandResult.SpecialCommandResult.CREATE_ADDRESSBOOK;
import static seedu.address.logic.commands.CommandResult.SpecialCommandResult.EXIT;
import static seedu.address.logic.commands.CommandResult.SpecialCommandResult.NORMAL;
import static seedu.address.logic.commands.CommandResult.SpecialCommandResult.SHOW_HELP;
import static seedu.address.logic.commands.CommandResult.SpecialCommandResult.SWITCH_ADDRESSBOOK;

import org.junit.jupiter.api.Test;

public class CommandResultTest {
    private final CommandResult normalCommandResult = new CommandResult("feedback", NORMAL);
    private final CommandResult showHelpCommandResult = new CommandResult("feedback", SHOW_HELP);
    private final CommandResult exitCommandResult = new CommandResult("feedback", EXIT);
    private final CommandResult switchAddressBookCommandResult = new CommandResult("feedback", SWITCH_ADDRESSBOOK);
    private final CommandResult createAddressBookCommandResult = new CommandResult("feedback", CREATE_ADDRESSBOOK);
    private final CommandResult clearCommandResult = new CommandResult("feedback", CLEAR);


    @Test
    public void equals() {
        CommandResult commandResult = new CommandResult("feedback");

        // same values -> returns true
        assertTrue(commandResult.equals(new CommandResult("feedback")));
        assertTrue(commandResult.equals(normalCommandResult));

        // same object -> returns true
        assertTrue(commandResult.equals(commandResult));

        // null -> returns false
        assertFalse(commandResult.equals(null));

        // different types -> returns false
        assertFalse(commandResult.equals(0.5f));

        // different feedbackToUser value -> returns false
        assertFalse(commandResult.equals(new CommandResult("different")));

        // different showHelp value -> returns false
        assertFalse(commandResult.equals(showHelpCommandResult));

        // different exit value -> returns false
        assertFalse(commandResult.equals(exitCommandResult));

        // different switch value -> returns false
        assertFalse(commandResult.equals(switchAddressBookCommandResult));

        // different create value -> returns false
        assertFalse(commandResult.equals(createAddressBookCommandResult));

        // different clear value -> returns false
        assertFalse(commandResult.equals(clearCommandResult));
    }

    @Test
    public void hashcode() {
        CommandResult commandResult = new CommandResult("feedback");

        // same values -> returns same hashcode
        assertEquals(commandResult.hashCode(), new CommandResult("feedback").hashCode());
        assertEquals(commandResult.hashCode(), normalCommandResult.hashCode());

        // different feedbackToUser value -> returns different hashcode
        assertNotEquals(commandResult.hashCode(), new CommandResult("different").hashCode());

        // different showHelp value -> returns different hashcode
        assertNotEquals(commandResult.hashCode(), showHelpCommandResult.hashCode());

        // different exit value -> returns different hashcode
        assertNotEquals(commandResult.hashCode(), exitCommandResult.hashCode());
    }

    @Test
    public void isShowHelp() {
        // CommandResult of SHOW_HELP
        assertTrue(showHelpCommandResult.isShowHelp());
        assertTrue(new CommandResult("another", SHOW_HELP).isShowHelp());

        // CommandResult of non-SHOW_HELP
        assertFalse(normalCommandResult.isShowHelp());
        assertFalse(exitCommandResult.isShowHelp());
        assertFalse(clearCommandResult.isShowHelp());
        assertFalse(switchAddressBookCommandResult.isShowHelp());
        assertFalse(createAddressBookCommandResult.isShowHelp());
    }

    @Test
    public void isExit() {
        // CommandResult of EXIT
        assertTrue(exitCommandResult.isExit());
        assertTrue(new CommandResult("another", EXIT).isExit());

        // CommandResult of non-EXIT
        assertFalse(normalCommandResult.isExit());
        assertFalse(showHelpCommandResult.isExit());
        assertFalse(clearCommandResult.isExit());
        assertFalse(switchAddressBookCommandResult.isExit());
        assertFalse(createAddressBookCommandResult.isExit());
    }

    @Test
    public void isSwitchAddressBook() {
        // CommandResult of EXIT
        assertTrue(switchAddressBookCommandResult.isSwitchAddressBook());
        assertTrue(new CommandResult("another", SWITCH_ADDRESSBOOK).isSwitchAddressBook());

        // CommandResult of non-EXIT
        assertFalse(normalCommandResult.isSwitchAddressBook());
        assertFalse(showHelpCommandResult.isSwitchAddressBook());
        assertFalse(clearCommandResult.isSwitchAddressBook());
        assertFalse(exitCommandResult.isSwitchAddressBook());
        assertFalse(createAddressBookCommandResult.isSwitchAddressBook());
    }

    @Test
    public void isCreateAddressBook() {
        // CommandResult of EXIT
        assertTrue(createAddressBookCommandResult.isCreateAddressBook());
        assertTrue(new CommandResult("another", CREATE_ADDRESSBOOK).isCreateAddressBook());

        // CommandResult of non-EXIT
        assertFalse(normalCommandResult.isCreateAddressBook());
        assertFalse(showHelpCommandResult.isCreateAddressBook());
        assertFalse(clearCommandResult.isCreateAddressBook());
        assertFalse(exitCommandResult.isCreateAddressBook());
        assertFalse(switchAddressBookCommandResult.isCreateAddressBook());
    }

    @Test
    public void isClearing() {
        // CommandResult of CLEAR
        assertTrue(clearCommandResult.isClearing());
        assertTrue(new CommandResult("another", CLEAR).isClearing());

        // CommandResult of non-CLEAR
        assertFalse(normalCommandResult.isClearing());
        assertFalse(showHelpCommandResult.isClearing());
        assertFalse(exitCommandResult.isClearing());
        assertFalse(createAddressBookCommandResult.isClearing());
        assertFalse(switchAddressBookCommandResult.isClearing());
    }
}
