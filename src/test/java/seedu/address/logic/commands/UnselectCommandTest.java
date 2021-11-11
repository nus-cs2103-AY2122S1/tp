package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_PERSON;
import static seedu.address.testutil.TypicalIndexes.INDEX_THIRD_PERSON;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.Person;

public class UnselectCommandTest {

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void execute_validIndexEmptySelectedList_success() {
        Person personToUnselect = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        List<Index> indexes = new ArrayList<>();
        indexes.add(INDEX_FIRST_PERSON);
        List<Person> persons = new ArrayList<>();
        persons.add(personToUnselect);

        UnselectCommand selectCommand = new UnselectCommand(indexes, true);

        String expectedMessage = "1 persons unselected!";

        ModelManager expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        model.addSelected(persons);

        assertCommandSuccess(selectCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_inclusionValidIndexNonEmptySelectedList_success() {
        Person firstToUnselect = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        Person secondToUnselect = model.getFilteredPersonList().get(INDEX_SECOND_PERSON.getZeroBased());
        List<Index> indexes = new ArrayList<>();
        indexes.add(INDEX_FIRST_PERSON);
        indexes.add(INDEX_SECOND_PERSON);
        List<Person> persons = new ArrayList<>();
        persons.add(firstToUnselect);
        persons.add(secondToUnselect);

        UnselectCommand unselectCommand = new UnselectCommand(indexes, true);

        String expectedMessage = "2 persons unselected!";

        ModelManager expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        model.addSelected(persons);

        assertCommandSuccess(unselectCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_exclusionValidIndexNonEmptySelectedList_success() {
        Person firstToUnselect = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        Person secondToUnselect = model.getFilteredPersonList().get(INDEX_SECOND_PERSON.getZeroBased());
        Person toNotUnselect = model.getFilteredPersonList().get(INDEX_THIRD_PERSON.getZeroBased());
        List<Index> indexes = new ArrayList<>();
        indexes.add(INDEX_THIRD_PERSON);
        List<Person> persons = new ArrayList<>();
        persons.add(firstToUnselect);
        persons.add(secondToUnselect);
        persons.add(toNotUnselect);

        List<Person> remainSelectedPersons = new ArrayList<>();
        remainSelectedPersons.add(toNotUnselect);


        UnselectCommand unselectCommand = new UnselectCommand(indexes, false);

        String expectedMessage = "2 persons unselected!";

        ModelManager expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        expectedModel.addSelected(remainSelectedPersons);
        model.addSelected(persons);

        assertCommandSuccess(unselectCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexSelectedList_throwsCommandException() {
        Index outOfBoundIndex = Index.fromOneBased(model.getSelectedPersonList().size() + 1);
        List<Index> indexes = new ArrayList<>();
        indexes.add(outOfBoundIndex);
        UnselectCommand unselectCommand = new UnselectCommand(indexes, false);

        assertCommandFailure(unselectCommand, model, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    }

}
