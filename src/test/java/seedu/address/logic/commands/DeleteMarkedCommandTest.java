package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.showPersonAtIndex;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.Person;


public class DeleteMarkedCommandTest {

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void execute_listIsNotFilteredAndNoDone_success() {
        DeleteMarkedCommand deleteMarkedCommand = new DeleteMarkedCommand();
        String expectedMessage = String.format(DeleteMarkedCommand.MESSAGE_SUCCESS,
                DeleteMarkedCommand.MESSAGE_NONE_DELETED);

        ModelManager expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        assertCommandSuccess(deleteMarkedCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_listIsFilteredAndNoDone_success() {
        showPersonAtIndex(model, INDEX_FIRST_PERSON);
        DeleteMarkedCommand deleteMarkedCommand = new DeleteMarkedCommand();
        String expectedMessage = String.format(DeleteMarkedCommand.MESSAGE_SUCCESS,
                DeleteMarkedCommand.MESSAGE_NONE_DELETED);

        ModelManager expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        assertCommandSuccess(deleteMarkedCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_listIsNotFilteredAndHasDone_success() {
        Person personToDone = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        model.markPerson(personToDone);
        DeleteMarkedCommand deleteMarkedCommand = new DeleteMarkedCommand();
        String expectedMessage = String.format(DeleteMarkedCommand.MESSAGE_SUCCESS, personToDone);

        ModelManager expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        expectedModel.deletePerson(expectedModel.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased()));
        assertCommandSuccess(deleteMarkedCommand, model, expectedMessage, expectedModel);
        personToDone.getDone().setAsUndone();
    }

    @Test
    public void execute_listIsFilteredAndHasDone_success() {
        showPersonAtIndex(model, INDEX_FIRST_PERSON);
        Person personToDone = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        model.markPerson(personToDone);
        DeleteMarkedCommand deleteMarkedCommand = new DeleteMarkedCommand();
        String expectedMessage = String.format(DeleteMarkedCommand.MESSAGE_SUCCESS, personToDone);

        ModelManager expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        expectedModel.deletePerson(expectedModel.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased()));
        assertCommandSuccess(deleteMarkedCommand, model, expectedMessage, expectedModel);
        personToDone.getDone().setAsUndone();
    }

}
