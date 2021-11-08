package safeforhall.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static safeforhall.testutil.TypicalPersons.ALICE;
import static safeforhall.testutil.TypicalPersons.BENSON;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import safeforhall.commons.core.index.Index;
import safeforhall.logic.commands.exceptions.CommandException;
import safeforhall.model.Model;
import safeforhall.model.ModelManager;
import safeforhall.model.UserPrefs;
import safeforhall.model.event.ResidentList;
import safeforhall.model.person.Person;
import safeforhall.testutil.TypicalEvents;
import safeforhall.testutil.TypicalPersons;

public class IncludeCommandTest {
    private Model model = new ModelManager(TypicalPersons.getTypicalAddressBook(), new UserPrefs());
    private Model expectedModel = new ModelManager(TypicalPersons.getTypicalAddressBook(), new UserPrefs());

    @Test
    public void checkDuplicatesTrue() {
        ArrayList<Person> toAdd = new ArrayList<>();
        toAdd.add(ALICE);
        toAdd.add(BENSON);
        ArrayList<Person> current = new ArrayList<>();
        current.add(BENSON);
        IncludeCommand command = new IncludeCommand(Index.fromOneBased(1),
                new ResidentList(BENSON.getName().toString(), BENSON.toString()));
        assertThrows(CommandException.class, () -> command.checkForDuplicates(toAdd, current));
    }

    @Test
    public void addOneRoomTest() throws CommandException {
        ArrayList<Person> toAdd = model.toPersonList(new ResidentList(TypicalPersons.FIONA.getRoom().toString(),
                TypicalPersons.FIONA.toString()));
        CommandResult expectedMessage = new CommandResult(String.format(IncludeCommand.MESSAGE_SUCCESS, toAdd.stream()
                .map(p -> p.getName().toString()).reduce((x, y) -> x + ", " + y).get(),
                TypicalEvents.BASKETBALL.getEventName()));
        model.addEvent(TypicalEvents.BASKETBALL);
        IncludeCommand command = new IncludeCommand(Index.fromOneBased(1),
                new ResidentList(TypicalPersons.FIONA.getRoom().toString(), TypicalPersons.FIONA.toString()));
        CommandResult result = command.execute(model);
        assertEquals(result, expectedMessage);
    }

    @Test
    public void addMultipleRoomsTest() throws CommandException {
        String constructor1 = TypicalPersons.ELLE.getRoom().toString() + ", "
                + TypicalPersons.FIONA.getRoom().toString();
        String constructor2 = TypicalPersons.ELLE.toString() + ", " + TypicalPersons.FIONA.toString();
        ArrayList<Person> toAdd = model.toPersonList(new ResidentList(constructor1, constructor2));
        CommandResult expectedMessage = new CommandResult(String.format(IncludeCommand.MESSAGE_SUCCESS, toAdd.stream()
                        .map(p -> p.getName().toString()).reduce((x, y) -> x + ", " + y).get(),
                TypicalEvents.VOLLEYBALL.getEventName()));
        model.addEvent(TypicalEvents.VOLLEYBALL);
        IncludeCommand command = new IncludeCommand(Index.fromOneBased(1),
                new ResidentList(constructor1, constructor2));
        CommandResult result = command.execute(model);
        assertEquals(result, expectedMessage);
    }

    @Test
    public void addOneNameTest() throws CommandException {
        ArrayList<Person> toAdd = model.toPersonList(new ResidentList(TypicalPersons.DANIEL.getName().toString()));
        CommandResult expectedMessage = new CommandResult(String.format(IncludeCommand.MESSAGE_SUCCESS, toAdd.stream()
                        .map(p -> p.getName().toString()).reduce((x, y) -> x + ", " + y).get(),
                TypicalEvents.BASKETBALL.getEventName()));
        model.addEvent(TypicalEvents.BASKETBALL);
        IncludeCommand command = new IncludeCommand(Index.fromOneBased(1),
                new ResidentList(TypicalPersons.DANIEL.getName().toString(), TypicalPersons.DANIEL.toString()));
        CommandResult result = command.execute(model);
        assertEquals(result, expectedMessage);
    }

    @Test
    public void addMultipleNamesTest() throws CommandException {
        String constructor1 = TypicalPersons.FIONA.getName().toString() + ", "
                + TypicalPersons.ELLE.getName().toString();
        String constructor2 = TypicalPersons.FIONA.toString() + ", " + TypicalPersons.ELLE.toString();
        ArrayList<Person> toAdd = model.toPersonList(new ResidentList(constructor1, constructor2));
        CommandResult expectedMessage = new CommandResult(String.format(IncludeCommand.MESSAGE_SUCCESS, toAdd.stream()
                        .map(p -> p.getName().toString()).reduce((x, y) -> x + ", " + y).get(),
                TypicalEvents.VOLLEYBALL.getEventName()));
        model.addEvent(TypicalEvents.VOLLEYBALL);
        IncludeCommand command = new IncludeCommand(Index.fromOneBased(1),
                new ResidentList(constructor1, constructor2));
        CommandResult result = command.execute(model);
        assertEquals(result, expectedMessage);
    }

    @Test
    public void addExistingRoomTest() {
        model.addEvent(TypicalEvents.VOLLEYBALL);
        IncludeCommand command = new IncludeCommand(Index.fromOneBased(1),
                new ResidentList(TypicalPersons.DANIEL.getRoom().toString(), TypicalPersons.DANIEL.toString()));
        assertThrows(CommandException.class, () -> command.execute(model));
    }

    @Test
    public void addExistingNameTest() {
        model.addEvent(TypicalEvents.VOLLEYBALL);
        IncludeCommand command = new IncludeCommand(Index.fromOneBased(1),
                new ResidentList(TypicalPersons.DANIEL.getName().toString(), TypicalPersons.DANIEL.toString()));
        assertThrows(CommandException.class, () -> command.execute(model));
    }

    @Test
    public void addInvalidNameTest() {
        model.addEvent(TypicalEvents.VOLLEYBALL);
        IncludeCommand command = new IncludeCommand(Index.fromOneBased(1),
                new ResidentList(ResidentList.DEFAULT_LIST, TypicalPersons.DANIEL.toString()));
        assertThrows(CommandException.class, () -> command.execute(model));
    }

    @Test
    public void invalidIndex_fails() {
        model.addEvent(TypicalEvents.VOLLEYBALL);
        IncludeCommand command = new IncludeCommand(Index.fromOneBased(10),
                new ResidentList(ResidentList.DEFAULT_LIST, TypicalPersons.DANIEL.toString()));
        assertThrows(CommandException.class, () -> command.execute(model));
    }

    @Test
    public void exceedCapacity_fails() {
        model.addEvent(TypicalEvents.BASKETBALL);
        IncludeCommand command = new IncludeCommand(Index.fromOneBased(1),
                new ResidentList(TypicalPersons.DANIEL.getName().toString() + ", "
                        + TypicalPersons.ELLE.getName().toString(), TypicalPersons.DANIEL.toString() + ", "
                        + TypicalPersons.ELLE.toString()));
        assertThrows(CommandException.class, () -> command.execute(model));
    }

    @Test
    public void equals() {
        IncludeCommand firstCommand = new IncludeCommand(Index.fromOneBased(1), new ResidentList("Alex"));
        IncludeCommand secondCommand = new IncludeCommand(Index.fromOneBased(2), new ResidentList("Bernice"));

        // same object -> returns true
        assertTrue(firstCommand.equals(firstCommand));

        // same values -> returns true
        IncludeCommand firstCommandCopy = new IncludeCommand(Index.fromOneBased(1), new ResidentList("Alex"));
        assertTrue(firstCommand.equals(firstCommandCopy));

        // different types -> returns false
        assertFalse(firstCommand.equals(1));

        // null -> returns false
        assertFalse(firstCommand.equals(null));

        // different command -> returns false
        assertFalse(firstCommand.equals(secondCommand));
    }
}
