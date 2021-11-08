package seedu.address.logic.interview;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.commons.core.Messages.MESSAGE_INTERVIEW_LISTED_OVERVIEW;
import static seedu.address.testutil.TypicalInterviews.ACCOUNTANT_INTERVIEW;
import static seedu.address.testutil.TypicalInterviews.BOOKKEEPER_INTERVIEW_2;
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
import seedu.address.model.interview.FindInterviewCommandPredicate;



/**
 * Contains integration tests (interaction with the Model) for {@code FindCommand}.
 */
public class FindInterviewCommandTest {
    private Model model = new ModelManager(getTypicalHrManager(), new UserPrefs());
    private Model expectedModel = new ModelManager(getTypicalHrManager(), new UserPrefs());

    @Test
    public void equals() {
        FindInterviewCommandPredicate firstPredicate =
                new FindInterviewCommandPredicate(Collections.singletonList("first"), Arrays.asList(),
                        Arrays.asList(), Arrays.asList(), Arrays.asList());
        FindInterviewCommandPredicate secondPredicate =
                new FindInterviewCommandPredicate(Collections.singletonList("second"), Arrays.asList(),
                        Arrays.asList(), Arrays.asList(), Arrays.asList());

        FindInterviewCommand findFirstCommand = new FindInterviewCommand(firstPredicate);
        FindInterviewCommand findSecondCommand = new FindInterviewCommand(secondPredicate);

        // same object -> returns true
        assertTrue(findFirstCommand.equals(findFirstCommand));

        // same values -> returns true
        FindInterviewCommand findFirstCommandCopy = new FindInterviewCommand(firstPredicate);
        assertTrue(findFirstCommand.equals(findFirstCommandCopy));

        // different types -> returns false
        assertFalse(findFirstCommand.equals(1));

        // null -> returns false
        assertFalse(findFirstCommand.equals(null));

        // different interview -> returns false
        assertFalse(findFirstCommand.equals(findSecondCommand));
    }

    @Test
    public void execute_multipleKeywords_multipleInterviewsFound() {
        String expectedMessage = String.format(MESSAGE_INTERVIEW_LISTED_OVERVIEW, 2);
        FindInterviewCommandPredicate predicate = preparePredicate("pending completed");
        Command command = new FindInterviewCommand(predicate);
        expectedModel.updateFilteredInterviewList(predicate);
        assertEquals(Arrays.asList(ACCOUNTANT_INTERVIEW, BOOKKEEPER_INTERVIEW_2),
                expectedModel.getFilteredInterviewList());

        //have to create a CommandResult manually because assertSuccess uses single parameter constructor
        CommandResult expectedCommandResult = new CommandResult(expectedMessage,
                CommandResult.CommandType.FIND_I);

        assertCommandSuccess(command, model, expectedCommandResult, expectedModel);
    }


    @Test
    public void execute_interviewDoesNotExist_noInterviewFound() {
        String expectedMessage = String.format(MESSAGE_INTERVIEW_LISTED_OVERVIEW, 0);
        FindInterviewCommandPredicate predicate = preparePredicate("THISSTATSDOESNOTEXIST");
        Command command = new FindInterviewCommand(predicate);
        expectedModel.updateFilteredInterviewList(predicate);
        assertEquals(Arrays.asList(), expectedModel.getFilteredInterviewList());

        //have to create a CommandResult manually because assertSuccess uses single parameter constructor
        CommandResult expectedCommandResult = new CommandResult(expectedMessage,
                CommandResult.CommandType.FIND_I);

        assertCommandSuccess(command, model, expectedCommandResult, expectedModel);
    }


    /**
     * Parses {@code userInput} into a {@code NameContainsKeywordsPredicate}.
     */
    private FindInterviewCommandPredicate preparePredicate(String userInput) {
        List<String> output = new ArrayList<String>(Arrays.asList(userInput.split("\\s+")));
        output.removeAll(Arrays.asList("", null));
        return new FindInterviewCommandPredicate(Arrays.asList(), Arrays.asList(),
                output, Arrays.asList(), Arrays.asList());
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
