package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.Person;
import seedu.address.testutil.PersonBuilder;

/**
 * Contains integration tests (interaction with the Model) and unit tests for ListCommand.
 */
public class StatCommandTest {

    private static final String EXPECTED_MESSAGE = "Tag Count: \n"
        + "[friends] : 3\n"
        + "[owesMoney] : 1\n"
        + "\n"
        + "Type Count: \n"
        + "student : 8\n"
        + "\n"
        + "Tutorial ID Count: \n"
        + "00 : 1\n"
        + "01 : 1\n"
        + "02 : 2\n"
        + "03 : 1\n"
        + "04 : 1\n"
        + "05 : 1\n"
        + "06 : 1\n";

    private Model model;
    private Model expectedModel;

    private Person carol = new PersonBuilder()
        .withName("Carol Heinz")
        .withPhone("97897897")
        .withEmail("e0000009@u.nus.edu")
        .withAddress("wall street")
        .withGitHubId("carol-heinz")
        .withNusNetworkId("e0000009")
        .withType("student")
        .withStudentId("A0001000X")
        .withTutorialId("02")
        .build();

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
        model.addPerson(carol);
        expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
    }

    @Test
    public void execute_showsExpectedMessage() {
        assertCommandSuccess(new StatCommand(), model, EXPECTED_MESSAGE, expectedModel);
    }

}
