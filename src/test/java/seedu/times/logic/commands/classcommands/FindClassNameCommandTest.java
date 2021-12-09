package seedu.times.logic.commands.classcommands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.times.commons.core.Messages.MESSAGE_CLASSES_LISTED_OVERVIEW;
import static seedu.times.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.times.logic.parser.ParserUtil.FIND_REGEX_WITH_COMMA_DELIMITER;
import static seedu.times.testutil.TypicalTimestable.getTypicalTimesTable;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

import seedu.times.model.Model;
import seedu.times.model.ModelManager;
import seedu.times.model.UserPrefs;
import seedu.times.model.tuitionclass.predicates.ClassNameContainsKeywordsPredicate;

public class FindClassNameCommandTest {
    private Model model = new ModelManager(getTypicalTimesTable(), new UserPrefs());
    private Model expectedModel = new ModelManager(getTypicalTimesTable(), new UserPrefs());

    @Test
    public void execute_singleKeyword_noClassFound() {
        String expectedMessage = String.format(MESSAGE_CLASSES_LISTED_OVERVIEW, 0);
        ClassNameContainsKeywordsPredicate predicate = preparePredicate("Geography");
        FindClassNameCommand command = new FindClassNameCommand(predicate);
        expectedModel.updateFilteredClassList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_singleKeyword_multipleClassFound() {
        String expectedMessage = String.format(MESSAGE_CLASSES_LISTED_OVERVIEW, 2);
        ClassNameContainsKeywordsPredicate predicate = preparePredicate("Physics");
        FindClassNameCommand command = new FindClassNameCommand(predicate);
        expectedModel.updateFilteredClassList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_multipleTerms_multipleClassFound() {
        String expectedMessage = String.format(MESSAGE_CLASSES_LISTED_OVERVIEW, 6);
        ClassNameContainsKeywordsPredicate predicate = preparePredicate("sec, jc");
        FindClassNameCommand command = new FindClassNameCommand(predicate);
        expectedModel.updateFilteredClassList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_multipleKeywords_singleClassFound() {
        String expectedMessage = String.format(MESSAGE_CLASSES_LISTED_OVERVIEW, 1);
        ClassNameContainsKeywordsPredicate predicate = preparePredicate("jc physics");
        FindClassNameCommand command = new FindClassNameCommand(predicate);
        expectedModel.updateFilteredClassList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
    }

    @Test
    public void equals() {

        //same obj
        FindClassNameCommand command =
                new FindClassNameCommand(new ClassNameContainsKeywordsPredicate(Arrays.asList("Physics", "Maths")));
        assertTrue(command.equals(command));

        //same values
        FindClassNameCommand command2 =
                new FindClassNameCommand(new ClassNameContainsKeywordsPredicate(Arrays.asList("Physics", "Maths")));
        assertTrue(command.equals(command2));

        //different values
        command2 =
                new FindClassNameCommand(new ClassNameContainsKeywordsPredicate(Arrays.asList("Chemistry", "Maths")));
        assertFalse(command.equals(command2));
    }

    private ClassNameContainsKeywordsPredicate preparePredicate(String userInput) {
        return new ClassNameContainsKeywordsPredicate(Arrays.asList(userInput.trim()
                .split(FIND_REGEX_WITH_COMMA_DELIMITER)));
    }
}
