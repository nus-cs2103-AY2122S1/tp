package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.commons.core.Messages.MESSAGE_CLASS_DOES_NOT_EXIST;
import static seedu.address.commons.core.Messages.MESSAGE_TUTORIAL_CLASSES_LISTED_OVERVIEW;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalTutorialClasses.G01;
import static seedu.address.testutil.TypicalTutorialClasses.G02;
import static seedu.address.testutil.TypicalTutorialClasses.G06;
import static seedu.address.testutil.TypicalTutorialClasses.getTypicalClassmate;

import java.util.Arrays;
import java.util.Collections;

import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.tutorialclass.ClassCodeContainsKeywordsPredicate;

/**
 * Contains integration tests (interaction with the Model) for {@code FindClassCommand}.
 */
public class FindClassCommandTest {
    private Model model = new ModelManager(getTypicalClassmate(), new UserPrefs());
    private Model expectedModel = new ModelManager(getTypicalClassmate(), new UserPrefs());

    @Test
    public void equals() {
        ClassCodeContainsKeywordsPredicate firstPredicate =
                new ClassCodeContainsKeywordsPredicate(Collections.singletonList("first"));
        ClassCodeContainsKeywordsPredicate secondPredicate =
                new ClassCodeContainsKeywordsPredicate(Collections.singletonList("second"));

        FindClassCommand findFirstCommand = new FindClassCommand(firstPredicate);
        FindClassCommand findSecondCommand = new FindClassCommand(secondPredicate);

        // same object -> returns true
        assertTrue(findFirstCommand.equals(findFirstCommand));

        // same values -> returns true
        FindClassCommand findFirstCommandCopy = new FindClassCommand(firstPredicate);
        assertTrue(findFirstCommand.equals(findFirstCommandCopy));

        // different types -> returns false
        assertFalse(findFirstCommand.equals(1));

        // null -> returns false
        assertFalse(findFirstCommand.equals(null));

        // different tutorial -> returns false
        assertFalse(findFirstCommand.equals(findSecondCommand));
    }

    @Test
    public void execute_zeroKeywords_noTutorialClassFound() {
        String expectedMessage = String.format(MESSAGE_CLASS_DOES_NOT_EXIST);
        ClassCodeContainsKeywordsPredicate predicate = preparePredicate(" ");

        FindClassCommand command = new FindClassCommand(predicate);
        expectedModel.updateFilteredTutorialClassList(predicate);
        assertCommandFailure(command, model, expectedMessage);
        assertEquals(Collections.emptyList(), model.getFilteredTutorialClassList());
    }

    @Test
    public void execute_multipleKeywords_multipleTutorialClassesFound() {
        String expectedMessage = String.format(MESSAGE_TUTORIAL_CLASSES_LISTED_OVERVIEW, 3);
        ClassCodeContainsKeywordsPredicate predicate = preparePredicate("G01 G02 G06");
        FindClassCommand command = new FindClassCommand(predicate);
        expectedModel.updateFilteredTutorialClassList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(G01, G02, G06), model.getFilteredTutorialClassList());
    }

    /**
     * Parses {@code userInput} into a {@code ClassCodeContainsKeywordsPredicate}.
     */
    private ClassCodeContainsKeywordsPredicate preparePredicate(String userInput) {
        return new ClassCodeContainsKeywordsPredicate(Arrays.asList(userInput.split("\\s+")));
    }
}
