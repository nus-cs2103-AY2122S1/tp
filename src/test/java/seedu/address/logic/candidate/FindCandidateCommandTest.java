package seedu.address.logic.candidate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.commons.core.Messages.MESSAGE_PERSONS_LISTED_OVERVIEW;
import static seedu.address.testutil.TypicalPersons.CARL;
import static seedu.address.testutil.TypicalPersons.ELLE;
import static seedu.address.testutil.TypicalPersons.FIONA;
import static seedu.address.testutil.TypicalPersons.getTypicalHrManager;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.logic.Command;
import seedu.address.logic.CommandResult;
import seedu.address.logic.candidate.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.FindCandidateCommandPredicate;

/**
 * Contains integration tests (interaction with the Model) for {@code FindCommand}.
 */
public class FindCandidateCommandTest {
    private Model model = new ModelManager(getTypicalHrManager(), new UserPrefs());
    private Model expectedModel = new ModelManager(getTypicalHrManager(), new UserPrefs());

    @Test
    public void equals() {
        FindCandidateCommandPredicate firstPredicate =
                new FindCandidateCommandPredicate(Collections.singletonList("first"));
        FindCandidateCommandPredicate secondPredicate =
                new FindCandidateCommandPredicate(Collections.singletonList("second"));

        FindCandidateCommand findFirstCommand = new FindCandidateCommand(firstPredicate);
        FindCandidateCommand findSecondCommand = new FindCandidateCommand(secondPredicate);

        // same object -> returns true
        assertTrue(findFirstCommand.equals(findFirstCommand));

        // same values -> returns true
        FindCandidateCommand findFirstCommandCopy = new FindCandidateCommand(firstPredicate);
        assertTrue(findFirstCommand.equals(findFirstCommandCopy));

        // different types -> returns false
        assertFalse(findFirstCommand.equals(1));

        // null -> returns false
        assertFalse(findFirstCommand.equals(null));

        // different person -> returns false
        assertFalse(findFirstCommand.equals(findSecondCommand));
    }

    @Test
    public void execute_multipleKeywords_multiplePersonsFound() {
        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 3);
        FindCandidateCommandPredicate predicate = preparePredicate("Kurz Elle Kunz");
        Command command = new FindCandidateCommand(predicate);
        expectedModel.updateFilteredPersonList(predicate);
        assertEquals(Arrays.asList(CARL, ELLE, FIONA), expectedModel.getFilteredPersonList());

        //have to create a CommandResult manually because assertSuccess uses single parameter constructor
        CommandResult expectedCommandResult = new CommandResult(expectedMessage,
                CommandResult.CommandType.FIND_C);

        assertCommandSuccess(command, model, expectedCommandResult, expectedModel);
    }


    @Test
    public void execute_personDoesNotExist_noPersonFound() {
        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 0);
        FindCandidateCommandPredicate predicate = preparePredicate("John");
        Command command = new FindCandidateCommand(predicate);
        expectedModel.updateFilteredPersonList(predicate);
        assertEquals(Arrays.asList(), expectedModel.getFilteredPersonList());

        //have to create a CommandResult manually because assertSuccess uses single parameter constructor
        CommandResult expectedCommandResult = new CommandResult(expectedMessage,
                CommandResult.CommandType.FIND_C);

        assertCommandSuccess(command, model, expectedCommandResult, expectedModel);
    }


    /**
     * Parses {@code userInput} into a {@code NameContainsKeywordsPredicate}.
     */
    private FindCandidateCommandPredicate preparePredicate(String userInput) {
        List<String> output = new ArrayList<String>(Arrays.asList(userInput.split("\\s+")));
        output.removeAll(Arrays.asList("", null));
        return new FindCandidateCommandPredicate(output);
    }


    /**
     * Overrides the method in CommandTestUtil for CommandResult created by find_c
     */
    private void assertCommandSuccess(Command command, Model actualModel, CommandResult expectedCommandResult,
                                 Model expectedModel) {
        try {
            CommandResult result = command.execute(actualModel);
            assertEquals(expectedCommandResult, result);
            assertEquals(expectedModel, actualModel);
        } catch (CommandException ce) {
            throw new AssertionError("Execution of command should not fail.", ce);
        }
    }
}
