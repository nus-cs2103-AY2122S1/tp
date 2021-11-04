package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import java.util.Collections;
import java.util.List;
import java.util.Locale;
import java.util.function.Predicate;

import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.NameContainsKeywordsPredicate;
import seedu.address.model.person.Person;
import seedu.address.testutil.TypicalIndexes;
import seedu.address.testutil.TypicalPersons;

/**
 * Contains integration tests (interaction with the Model) for {@code FindCommand}.
 */
public class ShowCommandTest {

    private final Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs(), null);
    private final Model expectedModel = new ModelManager(getTypicalAddressBook(), new UserPrefs(), null);

    @Test
    public void equals() {
        NameContainsKeywordsPredicate firstPredicate =
                new NameContainsKeywordsPredicate(Collections.singletonList("first"));
        NameContainsKeywordsPredicate secondPredicate =
                new NameContainsKeywordsPredicate(Collections.singletonList("second"));

        ShowCommand showFirstCommand = new ShowCommand(firstPredicate, "Name");
        ShowCommand showSecondCommand = new ShowCommand(secondPredicate, "Name");

        // same object -> returns true
        assertTrue(showFirstCommand.equals(showFirstCommand));

        // same values -> returns true
        ShowCommand showFirstCommandCopy = new ShowCommand(firstPredicate, "Name");
        assertTrue(showFirstCommandCopy.equals(showFirstCommand));

        // different types -> returns false
        assertFalse(showFirstCommand.equals(1));

        // null -> returns false
        assertFalse(showFirstCommand.equals(null));

        // different person -> returns false
        assertFalse(showSecondCommand.equals(showFirstCommand));

        // different indexes -> return false
        assertFalse(new ShowCommand(TypicalIndexes.INDEX_FIRST_PERSON)
                .equals(new ShowCommand(TypicalIndexes.INDEX_SECOND_PERSON)));

        // same index -> return true
        assertTrue(new ShowCommand(TypicalIndexes.INDEX_FIRST_PERSON)
                .equals(new ShowCommand(TypicalIndexes.INDEX_FIRST_PERSON)));
    }

    @Test
    public void execute_nullIndex_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, ShowCommand.MESSAGE_USAGE);
        ShowCommand command = new ShowCommand(null);
        assertCommandFailure(command, model, expectedMessage);
    }

    @Test
    public void execute_nullPredicate_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, ShowCommand.MESSAGE_USAGE);
        ShowCommand command = new ShowCommand(null, "Name");
        assertCommandFailure(command, model, expectedMessage);
    }

    @Test
    public void execute_showNone_exceptionThrown() {
        String expectedMessage = String.format(ShowCommand.MESSAGE_NO_TARGET, "Name");
        Predicate<Person> predicate = p -> p.getName().fullName.toLowerCase(Locale.ROOT).contains("janice");
        ShowCommand command = new ShowCommand(predicate, "Name");
        assertCommandFailure(command, model, expectedMessage);
    }


    @Test
    public void execute_showOnePerson_onePersonShown() {
        String expectedMessage = String.format(ShowCommand.MESSAGE_SUCCESS, TypicalPersons.ALICE.getName().fullName);
        Predicate<Person> predicate = p -> p.getName().fullName.toLowerCase(Locale.ROOT).contains("alice");
        ShowCommand command = new ShowCommand(predicate, "Name");
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_showOnePersonNotInFiltered_onePersonShown() {
        String expectedMessage = String.format(ShowCommand.MESSAGE_SUCCESS, TypicalPersons.CARL.getName().fullName);
        Predicate<Person> predicate = p -> p.getName().fullName.toLowerCase(Locale.ROOT).contains("alice");
        model.updateFilteredPersonList(predicate);
        ShowCommand command = new ShowCommand(new NameContainsKeywordsPredicate(List.of("Carl")), "Name");
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_commonKeyWord_multiplePersonsShown() {
        String expectedMessage = String.format(ShowCommand.MESSAGE_MULTIPLE_NAME, 2, "Name");
        Predicate<Person> predicate = p -> p.getName().fullName.toLowerCase(Locale.ROOT).contains("meier");
        ShowCommand command = new ShowCommand(predicate, "Name");
        expectedModel.updateFilteredPersonList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_showIndex_success() {
        String expectedMessage = String.format(ShowCommand.MESSAGE_SUCCESS, TypicalPersons.ALICE.getName().fullName);
        ShowCommand command = new ShowCommand(TypicalIndexes.INDEX_FIRST_PERSON);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_showIndexOutOfBounds_failure() {
        Predicate<Person> predicate = p -> p.getName().fullName.toLowerCase(Locale.ROOT).contains("alice");
        model.updateFilteredPersonList(predicate);
        ShowCommand command = new ShowCommand(TypicalIndexes.INDEX_THIRD_PERSON);
        assertCommandFailure(command, model, MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    }
}
