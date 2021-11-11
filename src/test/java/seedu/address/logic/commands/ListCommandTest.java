package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.showPersonAtIndex;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.commons.core.listtype.ListType;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.room.RoomIsOccupiedPredicate;
import seedu.address.model.room.RoomIsVacantPredicate;
import seedu.address.testutil.TypicalRecordsBook;

/**
 * Contains integration tests (interaction with the Model) and unit tests for ListCommand.
 */
public class ListCommandTest {

    private Model model;
    private Model expectedModel;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
        expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
    }

    @Test
    public void execute_listIsNotFiltered_showsSameList() {
        assertCommandSuccess(new ListCommand(new ListType("guests")), model,
                ListCommand.MESSAGE_SUCCESS_GUESTS, expectedModel);
    }

    @Test
    public void execute_listIsFiltered_showsEverything() {
        showPersonAtIndex(model, INDEX_FIRST_PERSON);
        assertCommandSuccess(new ListCommand(new ListType("guests")), model,
                ListCommand.MESSAGE_SUCCESS_GUESTS, expectedModel);
    }

    @Test
    public void execute_listVacantRooms_showsVacantRooms() {
        assertCommandSuccess(new ListCommand(new ListType("rooms"), new RoomIsVacantPredicate()),
                model, ListCommand.MESSAGE_SUCCESS_ROOMS_TYPE, expectedModel);
    }

    @Test
    public void execute_listOccupiedRooms_showsOccupiedRooms() {
        assertCommandSuccess(new ListCommand(new ListType("rooms"), new RoomIsOccupiedPredicate()),
                model, ListCommand.MESSAGE_SUCCESS_ROOMS_TYPE, expectedModel);
    }

    @Test
    public void execute_listAllRecords_showsAllRecords() {
        model.setAddressBook(TypicalRecordsBook.getTypicalAddressBook());
        expectedModel.setAddressBook(TypicalRecordsBook.getTypicalAddressBook());
        assertCommandSuccess(new ListCommand(new ListType("records")), model,
                ListCommand.MESSAGE_SUCCESS_RECORDS, expectedModel);
    }

    @Test
    public void listRoomsCommand_equalsListRoomsCommand() {
        ListCommand lc = new ListCommand(new ListType("rooms"));
        assertEquals(new ListCommand(new ListType("rooms")), lc);
    }

    @Test
    public void listGuestsCommand_equalsListGuestsCommand() {
        ListCommand lc = new ListCommand(new ListType("guests"));
        assertEquals(new ListCommand(new ListType("guests")), lc);
    }

    @Test
    public void listGuestsCommand_notEqualsListRoomsCommand() {
        ListCommand lc = new ListCommand(new ListType("guests"));
        assertNotEquals(lc, new ListCommand(new ListType("rooms")));
    }

    @Test
    public void listVacantRoomsCommand_equalsListVacantRoomsCommand() {
        ListCommand lc = new ListCommand(new ListType("rooms"), new RoomIsVacantPredicate());
        assertEquals(new ListCommand(new ListType("rooms"), new RoomIsVacantPredicate()), lc);
    }

    @Test
    public void listOccupiedRoomsCommand_equalsListOccupiedRoomsCommand() {
        ListCommand lc = new ListCommand(new ListType("rooms"), new RoomIsOccupiedPredicate());
        assertEquals(new ListCommand(new ListType("rooms"), new RoomIsOccupiedPredicate()), lc);
    }

    @Test
    public void listVacantRoomsCommand_notEqualsListOccupiedRoomsCommand() {
        ListCommand lc = new ListCommand(new ListType("rooms"), new RoomIsVacantPredicate());
        assertNotEquals(new ListCommand(new ListType("rooms"), new RoomIsOccupiedPredicate()), lc);
    }

    @Test
    public void listRecordsCommand_equalsListRecordsCommand() {
        ListCommand lc = new ListCommand(new ListType("records"));
        assertEquals(new ListCommand(new ListType("records")), lc);
    }
}
