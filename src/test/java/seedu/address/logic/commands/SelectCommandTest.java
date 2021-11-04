package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIFTH_PERSON;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.address.testutil.TypicalIndexes.INDEX_FOURTH_PERSON;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_PERSON;
import static seedu.address.testutil.TypicalIndexes.INDEX_SEVENTH_PERSON;
import static seedu.address.testutil.TypicalIndexes.INDEX_SIXTH_PERSON;
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

public class SelectCommandTest {

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void execute_validIndexEmptySelectedList_success() {
        Person personToSelect = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        List<Index> indexes = new ArrayList<>();
        indexes.add(INDEX_FIRST_PERSON);
        List<Person> persons = new ArrayList<>();
        persons.add(personToSelect);

        SelectCommand selectCommand = new SelectCommand(indexes, true);

        String expectedMessage = "1 persons selected!";

        ModelManager expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        expectedModel.addSelected(persons);

        assertCommandSuccess(selectCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_inclusionValidIndexNonEmptySelectedList_success() {
        Person firstToSelect = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        Person secondToSelect = model.getFilteredPersonList().get(INDEX_SECOND_PERSON.getZeroBased());
        List<Index> indexes = new ArrayList<>();
        indexes.add(INDEX_FIRST_PERSON);
        indexes.add(INDEX_SECOND_PERSON);
        List<Person> persons = new ArrayList<>();
        persons.add(firstToSelect);
        persons.add(secondToSelect);

        SelectCommand selectCommand = new SelectCommand(indexes, true);

        String expectedMessage = "2 persons selected!";

        ModelManager expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        expectedModel.addSelected(persons);

        assertCommandSuccess(selectCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_exclusionValidIndexNonEmptySelectedList_success() {
        Person firstToSelect = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        Person secondToSelect = model.getFilteredPersonList().get(INDEX_SECOND_PERSON.getZeroBased());
        List<Index> indexes = new ArrayList<>();
        indexes.add(INDEX_THIRD_PERSON);
        indexes.add(INDEX_FOURTH_PERSON);
        indexes.add(INDEX_FIFTH_PERSON);
        indexes.add(INDEX_SIXTH_PERSON);
        indexes.add(INDEX_SEVENTH_PERSON);
        List<Person> persons = new ArrayList<>();
        persons.add(firstToSelect);
        persons.add(secondToSelect);


        SelectCommand selectCommand = new SelectCommand(indexes, false);

        String expectedMessage = "2 persons selected!";

        ModelManager expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        expectedModel.addSelected(persons);

        assertCommandSuccess(selectCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexSelectedList_throwsCommandException() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredPersonList().size() + 1);
        List<Index> indexes = new ArrayList<>();
        indexes.add(outOfBoundIndex);
        SelectCommand selectCommand = new SelectCommand(indexes, false);

        assertCommandFailure(selectCommand, model, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    }

}
