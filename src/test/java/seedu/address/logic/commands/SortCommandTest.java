package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.logic.parser.Prefix;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.Person;
import seedu.address.testutil.PersonBuilder;

public class SortCommandTest {

    private Person alex = new PersonBuilder()
            .withName("Alex Marcus")
            .withPhone("91234567")
            .withEmail("e0000007@u.nus.edu")
            .withAddress("123, Jurong West Ave 6, #08-111")
            .withTags("friends")
            .withGitHubId("alex-marcus")
            .withNusNetworkId("e0000007")
            .withType("student")
            .withStudentId("A0000010X")
            .withTutorialId("00")
            .build();

    private Person alice = new PersonBuilder()
            .withName("Alice Pauline")
            .withPhone("91234567")
            .withEmail("e0000007@u.nus.edu")
            .withAddress("123, Jurong West Ave 6, #08-111")
            .withTags("friends")
            .withGitHubId("alex-marcus")
            .withNusNetworkId("e0000007")
            .withType("student")
            .withStudentId("A0000010X")
            .withTutorialId("00")
            .build();

    private Person carol = new PersonBuilder()
            .withName("Carol Heinz")
            .withPhone("97897897")
            .withEmail("e0000009@u.nus.edu")
            .withAddress("wall street")
            .withGitHubId("carol-heinz")
            .withNusNetworkId("e0000009")
            .withType("tutor")
            .withStudentId("A0001000X")
            .withTutorialId("01")
            .build();

    private List<Person> unsortedList = Arrays.asList(alex, carol, alice);

    private Model setUnsortedModel() {
        AddressBook unsortedAB = new AddressBook();
        unsortedAB.setPersons(unsortedList);
        return new ModelManager(unsortedAB, new UserPrefs());
    }

    private Model setSortedModel(List<Person> sortedList) {
        AddressBook sortedAB = new AddressBook();
        sortedAB.setPersons(sortedList);
        return new ModelManager(sortedAB, new UserPrefs());
    }

    @Test
    public void execute_sortNameReverseFalse_sortSuccessful() {
        Model unsortedModel = setUnsortedModel();
        SortCommand sortCommand = new SortCommand(new Prefix("n/"), false);
        List<Person> sortedList = Arrays.asList(alex, alice, carol);
        Model sortedModel = setSortedModel(sortedList);
        String expectedMessage = SortCommand.MESSAGE_SUCCESS;
        assertCommandSuccess(sortCommand, unsortedModel, expectedMessage, sortedModel);
    }

    @Test
    public void execute_sortNameReverseTrue_sortSuccessful() {
        Model unsortedModel = setUnsortedModel();
        SortCommand sortCommand = new SortCommand(new Prefix("n/"), true);
        List<Person> sortedList = Arrays.asList(carol, alice, alex);
        Model sortedModel = setSortedModel(sortedList);
        String expectedMessage = SortCommand.MESSAGE_SUCCESS;
        assertCommandSuccess(sortCommand, unsortedModel, expectedMessage, sortedModel);
    }

    @Test
    public void execute_sortEmailReverseFalse_sortSuccessful() {
        Model unsortedModel = setUnsortedModel();
        SortCommand sortCommand = new SortCommand(new Prefix("e/"), false);
        List<Person> sortedList = Arrays.asList(alex, alice, carol);
        Model sortedModel = setSortedModel(sortedList);
        String expectedMessage = SortCommand.MESSAGE_SUCCESS;
        assertCommandSuccess(sortCommand, unsortedModel, expectedMessage, sortedModel);
    }

    @Test
    public void execute_sortPhoneReverseFalse_sortSuccessful() {
        Model unsortedModel = setUnsortedModel();
        SortCommand sortCommand = new SortCommand(new Prefix("p/"), false);
        List<Person> sortedList = Arrays.asList(alex, alice, carol);
        Model sortedModel = setSortedModel(sortedList);
        String expectedMessage = SortCommand.MESSAGE_SUCCESS;
        assertCommandSuccess(sortCommand, unsortedModel, expectedMessage, sortedModel);
    }

    @Test
    public void execute_sortTagsReverseFalse_sortSuccessful() {
        Model unsortedModel = setUnsortedModel();
        SortCommand sortCommand = new SortCommand(new Prefix("t/"), false);
        List<Person> sortedList = Arrays.asList(carol, alex, alice);
        Model sortedModel = setSortedModel(sortedList);
        String expectedMessage = SortCommand.MESSAGE_SUCCESS;
        assertCommandSuccess(sortCommand, unsortedModel, expectedMessage, sortedModel);
    }

    @Test
    public void execute_sortGithubIdReverseFalse_sortSuccessful() {
        Model unsortedModel = setUnsortedModel();
        SortCommand sortCommand = new SortCommand(new Prefix("g/"), false);
        List<Person> sortedList = Arrays.asList(alex, alice, carol);
        Model sortedModel = setSortedModel(sortedList);
        String expectedMessage = SortCommand.MESSAGE_SUCCESS;
        assertCommandSuccess(sortCommand, unsortedModel, expectedMessage, sortedModel);
    }

    @Test
    public void execute_sortTutorialIdReverseFalse_sortSuccessful() {
        Model unsortedModel = setUnsortedModel();
        SortCommand sortCommand = new SortCommand(new Prefix("T/"), false);
        List<Person> sortedList = Arrays.asList(alex, alice, carol);
        Model sortedModel = setSortedModel(sortedList);
        String expectedMessage = SortCommand.MESSAGE_SUCCESS;
        assertCommandSuccess(sortCommand, unsortedModel, expectedMessage, sortedModel);
    }

    @Test
    public void execute_sortTypeReverseFalse_sortSuccessful() {
        Model unsortedModel = setUnsortedModel();
        SortCommand sortCommand = new SortCommand(new Prefix("r/"), false);
        List<Person> sortedList = Arrays.asList(alex, alice, carol);
        Model sortedModel = setSortedModel(sortedList);
        String expectedMessage = SortCommand.MESSAGE_SUCCESS;
        assertCommandSuccess(sortCommand, unsortedModel, expectedMessage, sortedModel);
    }

    public void equals() {
        final SortCommand standardCommand = new SortCommand(new Prefix("n/"), false);

        // same values -> returns true
        assertTrue(standardCommand.equals(new SortCommand(new Prefix("n/"), false)));

        // same object -> returns true
        assertTrue(standardCommand.equals(standardCommand));

        // null -> returns false
        assertFalse(standardCommand.equals(null));

        // different prefix -> returns false
        assertFalse(standardCommand.equals(new SortCommand(new Prefix("t/"), false)));

        //different reverse -> returns false
        assertFalse(standardCommand.equals(new SortCommand(new Prefix("n/"), true)));

        //different prefix and reverse -> returns false
        assertFalse(standardCommand.equals(new SortCommand(new Prefix("t/"), true)));

        //different command -> returns false
        assertFalse(standardCommand.equals(new HelpCommand()));
    }
}
