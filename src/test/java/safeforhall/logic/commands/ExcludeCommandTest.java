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

public class ExcludeCommandTest {
    private Model model = new ModelManager(TypicalPersons.getTypicalAddressBook(), new UserPrefs());
    private Model expectedModel = new ModelManager(TypicalPersons.getTypicalAddressBook(), new UserPrefs());

    @Test
    public void checkAllExistsFalse() {
        ArrayList<Person> toRemove = new ArrayList<>();
        toRemove.add(ALICE);
        toRemove.add(BENSON);
        ArrayList<Person> current = new ArrayList<>();
        current.add(BENSON);
        ExcludeCommand command = new ExcludeCommand(Index.fromOneBased(1), new ResidentList("Benson"));
        assertThrows(CommandException.class, () -> command.checkAllExists(toRemove, current));
    }

    @Test
    public void removeOneRoomTest() throws CommandException {
        ArrayList<Person> toRemove = model.toPersonList(new ResidentList(TypicalPersons.DANIEL
                .getRoom().toString(), TypicalPersons.DANIEL.toString()));
        CommandResult expectedMessage = new CommandResult(String.format(ExcludeCommand.MESSAGE_SUCCESS,
                toRemove.stream().map(p -> p.getName().toString()).reduce((x, y) -> x + ", " + y).get(),
                TypicalEvents.VOLLEYBALL.getEventName()));
        model.addEvent(TypicalEvents.VOLLEYBALL);
        ExcludeCommand command = new ExcludeCommand(Index.fromOneBased(1),
                new ResidentList(TypicalPersons.DANIEL.getRoom().toString()));
        CommandResult result = command.execute(model);
        assertEquals(result, expectedMessage);
    }

    @Test
    public void removeMultipleRoomsTest() throws CommandException {
        String constructor1 = TypicalPersons.CARL.getRoom().toString() + ", " + BENSON
                .getRoom().toString();
        String constructor2 = TypicalPersons.CARL.toString() + ", " + BENSON.toString();
        ArrayList<Person> toRemove = model.toPersonList(new ResidentList(constructor1, constructor2));
        CommandResult expectedMessage = new CommandResult(String.format(ExcludeCommand.MESSAGE_SUCCESS,
                toRemove.stream().map(p -> p.getName().toString()).reduce((x, y) -> x + ", " + y).get(),
                TypicalEvents.BAND.getEventName()));
        model.addEvent(TypicalEvents.BAND);
        ExcludeCommand command = new ExcludeCommand(Index.fromOneBased(1), new ResidentList(constructor1,
                constructor2));
        CommandResult result = command.execute(model);
        assertEquals(result, expectedMessage);
    }

    @Test
    public void removeOneNameTest() throws CommandException {
        ArrayList<Person> toRemove = model.toPersonList(new ResidentList(TypicalPersons.DANIEL
                .getName().toString(), TypicalPersons.DANIEL.toString()));
        CommandResult expectedMessage = new CommandResult(String.format(ExcludeCommand.MESSAGE_SUCCESS,
                toRemove.stream().map(p -> p.getName().toString()).reduce((x, y) -> x + ", " + y).get(),
                TypicalEvents.VOLLEYBALL.getEventName()));
        model.addEvent(TypicalEvents.VOLLEYBALL);
        ExcludeCommand command = new ExcludeCommand(Index.fromOneBased(1),
                new ResidentList(TypicalPersons.DANIEL.getName().toString()));
        CommandResult result = command.execute(model);
        assertEquals(result, expectedMessage);
    }

    @Test
    public void removeMultipleNamesTest() throws CommandException {
        String constructor1 = TypicalPersons.CARL.getName().toString() + ", " + BENSON
                .getName().toString();
        String constructor2 = TypicalPersons.CARL.toString() + ", " + BENSON.toString();
        ArrayList<Person> toRemove = model.toPersonList(new ResidentList(constructor1, constructor2));
        CommandResult expectedMessage = new CommandResult(String.format(ExcludeCommand.MESSAGE_SUCCESS,
                toRemove.stream().map(p -> p.getName().toString()).reduce((x, y) -> x + ", " + y).get(),
                TypicalEvents.BAND.getEventName()));
        model.addEvent(TypicalEvents.BAND);
        ExcludeCommand command = new ExcludeCommand(Index.fromOneBased(1), new ResidentList(constructor1,
                constructor2));
        CommandResult result = command.execute(model);
        assertEquals(result, expectedMessage);
    }

    @Test
    public void removeNonExistentRoomTest() {
        model.addEvent(TypicalEvents.BASKETBALL);
        ExcludeCommand command = new ExcludeCommand(Index.fromOneBased(1),
                new ResidentList("A103", "David Li; Room: C112; Phone: 91031282; "
                        + "Email: lidavid@example.com; Vaccinated: T; Faculty: SDE; Last Fet Date: 02-10-2021; "
                        + "Last Collection Date: 01-10-2021"));
        assertThrows(CommandException.class, () -> command.execute(model));
    }

    @Test
    public void removeNonExistentNameTest() {
        model.addEvent(TypicalEvents.BASKETBALL);
        ExcludeCommand command = new ExcludeCommand(Index.fromOneBased(1),
                new ResidentList("Daniel Meier", "David Li; Room: C112; Phone: 91031282; "
                        + "Email: lidavid@example.com; Vaccinated: T; Faculty: SDE; Last Fet Date: 02-10-2021; "
                        + "Last Collection Date: 01-10-2021"));
        assertThrows(CommandException.class, () -> command.execute(model));
    }

    @Test
    public void equals() {
        ExcludeCommand firstCommand = new ExcludeCommand(Index.fromOneBased(1),
                new ResidentList("Alex"));
        ExcludeCommand secondCommand = new ExcludeCommand(Index.fromOneBased(2),
                new ResidentList("Bernice"));

        // same object -> returns true
        assertTrue(firstCommand.equals(firstCommand));

        // same values -> returns true
        ExcludeCommand firstCommandCopy = new ExcludeCommand(Index.fromOneBased(1),
                new ResidentList("Alex"));
        assertTrue(firstCommand.equals(firstCommandCopy));

        // different types -> returns false
        assertFalse(firstCommand.equals(1));

        // null -> returns false
        assertFalse(firstCommand.equals(null));

        // different command -> returns false
        assertFalse(firstCommand.equals(secondCommand));
    }
}
