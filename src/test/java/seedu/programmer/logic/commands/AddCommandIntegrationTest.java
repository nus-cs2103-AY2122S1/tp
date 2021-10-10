package seedu.programmer.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static seedu.programmer.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.programmer.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.programmer.testutil.TypicalPersons.getTypicalAddressBook;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.programmer.model.Model;
import seedu.programmer.model.ModelManager;
import seedu.programmer.model.UserPrefs;
import seedu.programmer.model.person.Person;
import seedu.programmer.testutil.PersonBuilder;

/**
 * Contains integration tests (interaction with the Model) for {@code AddCommand}.
 */
public class AddCommandIntegrationTest {
    private static Person validPerson;
    private static Person samplePersonA;
    private static AddCommand sampleCommandA;
    private static AddCommand sampleCommandB;
    private Model model;

    @BeforeAll
    public static void oneTimeSetUp() {
        // Initialize sample Persons and Commands once before all tests
        validPerson = new PersonBuilder().build();
        samplePersonA = new PersonBuilder().withName("Alice").build();
        Person samplePersonB = new PersonBuilder().withName("Bob").build();
        sampleCommandA = new AddCommand(samplePersonA);
        sampleCommandB = new AddCommand(samplePersonB);
    }

    @BeforeEach
    public void setUp() {
        // Re-initialize model before each test
        model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    }

    @Test
    public void execute_newPerson_success() {
        Model expectedModel = new ModelManager(model.getProgrammerError(), new UserPrefs());
        expectedModel.addPerson(validPerson);

        assertCommandSuccess(new AddCommand(validPerson), model,
                String.format(AddCommand.MESSAGE_SUCCESS, validPerson), expectedModel);
    }

    @Test
    public void execute_duplicatePerson_throwsCommandException() {
        Person personInList = model.getProgrammerError().getPersonList().get(0);
        assertCommandFailure(new AddCommand(personInList), model, AddCommand.MESSAGE_DUPLICATE_PERSON);
    }

    @Test
    public void equals_sameValues_returnsTrue() {
        AddCommand sampleCommandACopy = new AddCommand(samplePersonA);
        assertEquals(sampleCommandA, sampleCommandACopy);
    }

    @Test
    public void equals_differentTypes_returnsFalse() {
        assertNotEquals(1, sampleCommandA);
    }

    @Test
    public void equals_nullValue_returnsFalse() {
        assertNotEquals(null, sampleCommandA);
    }

    @Test
    public void equals_differentPerson_returnsFalse() {
        assertNotEquals(sampleCommandA, sampleCommandB);
    }
}
